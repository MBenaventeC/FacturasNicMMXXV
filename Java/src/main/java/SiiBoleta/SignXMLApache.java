package SiiBoleta;

import org.apache.xml.security.Init;
import org.apache.xml.security.c14n.Canonicalizer;
import org.apache.xml.security.keys.KeyInfo;
import org.apache.xml.security.keys.content.X509Data;
import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.transforms.Transforms;
import org.apache.xml.security.utils.Constants;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Arrays;

public class SignXMLApache {

    public static void signXML(File inputXml, File pkcs12File, String keyPassword, File outputXml) throws Exception {
        // Initialize Apache XML Security
        Init.init();

        // Parse XML
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        dbf.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder = dbf.newDocumentBuilder();
        Document doc = builder.parse(inputXml);

        // Load the PKCS#12 keystore
        KeyStore ks = KeyStore.getInstance("PKCS12");
        try (FileInputStream fis = new FileInputStream(pkcs12File)) {
            ks.load(fis, keyPassword.toCharArray());
        }

        String alias = ks.aliases().nextElement();
        PrivateKey privateKey = (PrivateKey) ks.getKey(alias, keyPassword.toCharArray());
        X509Certificate cert = (X509Certificate) ks.getCertificate(alias);

        // Find the <Documento> element
        Element documento = (Element) doc.getElementsByTagName("Documento").item(0);
        if (documento == null)
            throw new Exception("No <Documento> element found!");
        Attr idAttr = documento.getAttributeNode("ID");
        if (idAttr == null)
            throw new Exception("<Documento> element has no ID attribute!");

        String referenceId = idAttr.getValue();
        documento.setIdAttribute("ID", true);

        // Create the Signature (RSA-SHA1)
        String baseURI = inputXml.toURI().toString();
        XMLSignature sig = new XMLSignature(
                doc,
                baseURI,
                XMLSignature.ALGO_ID_SIGNATURE_RSA_SHA1,
                Canonicalizer.ALGO_ID_C14N_OMIT_COMMENTS
        );

        // Add the transforms: enveloped + canonicalization
        Transforms transforms = new Transforms(doc);
        transforms.addTransform(Transforms.TRANSFORM_ENVELOPED_SIGNATURE);
        transforms.addTransform(Canonicalizer.ALGO_ID_C14N_OMIT_COMMENTS);

        // Add reference to the element by ID
        sig.addDocument("#" + referenceId, transforms, Constants.ALGO_ID_DIGEST_SHA1);

        // Add KeyInfo (certificate)
        KeyInfo ki = sig.getKeyInfo();
        X509Data x509Data = new X509Data(doc);
        x509Data.addCertificate(cert);
        ki.add(x509Data);

        // Append Signature to the root (not inside Documento)
        Element root = doc.getDocumentElement();
        root.appendChild(sig.getElement());

        // Sign the document
        sig.sign(privateKey);

        // --- Diagnostic: compute canonicalized bytes & SHA-1 and compare to Reference DigestValue ---
// (Place after sig.sign(privateKey); )

// Ensure XML Security initialized (you already did earlier)
        Init.init();

// 1) find Reference and DigestValue inside the generated Signature element
        NodeList sigNodes = doc.getElementsByTagNameNS(Constants.SignatureSpecNS, "Signature");
        if (sigNodes.getLength() == 0) {
            System.err.println("No Signature element found in result!");
        } else {
            Element signatureElem = (Element) sigNodes.item(sigNodes.getLength() - 1); // last signature
            Element refElem = (Element) signatureElem.getElementsByTagNameNS(Constants.SignatureSpecNS, "Reference").item(0);
            if (refElem == null) {
                System.err.println("No Reference element found in Signature!");
            } else {
                String uri = refElem.getAttribute("URI"); // e.g. "#DTE-34-22295"
                String digestValueInXml = refElem.getElementsByTagNameNS(Constants.SignatureSpecNS, "DigestValue")
                        .item(0).getTextContent().trim();
                System.out.println("Reference URI: " + uri);
                System.out.println("DigestValue in XML: " + digestValueInXml);

                // 2) resolve ID
                String id = uri.startsWith("#") ? uri.substring(1) : uri;

                // try to find element by attribute ID (robust search)
                Element target = null;
                NodeList all = doc.getElementsByTagName("*");
                for (int i = 0; i < all.getLength(); i++) {
                    Element e = (Element) all.item(i);
                    if (e.hasAttribute("ID") && id.equals(e.getAttribute("ID"))) {
                        target = e;
                        break;
                    }
                }
                if (target == null) {
                    System.err.println("Referenced element with ID=" + id + " not found in document.");
                } else {
                    // 3) clone into fresh Document to avoid side effects
                    DocumentBuilderFactory dbf2 = DocumentBuilderFactory.newInstance();
                    dbf2.setNamespaceAware(true);
                    DocumentBuilder builder2 = dbf2.newDocumentBuilder();
                    Document tmpDoc = builder2.newDocument();
                    Node imported = tmpDoc.importNode(target, true);
                    tmpDoc.appendChild(imported);

                    // 4) check the transforms declared in Reference (to determine enveloped/c14n)
                    NodeList transformElems = refElem.getElementsByTagNameNS(Constants.SignatureSpecNS, "Transform");
                    boolean hasEnveloped = false;
                    String c14nAlgURI = Canonicalizer.ALGO_ID_C14N_OMIT_COMMENTS; // default
                    for (int i = 0; i < transformElems.getLength(); i++) {
                        Element t = (Element) transformElems.item(i);
                        String alg = t.getAttribute("Algorithm");
                        if (Transforms.TRANSFORM_ENVELOPED_SIGNATURE.equals(alg)) hasEnveloped = true;
                        if ("http://www.w3.org/TR/2001/REC-xml-c14n-20010315".equals(alg)) {
                            c14nAlgURI = Canonicalizer.ALGO_ID_C14N_OMIT_COMMENTS;
                        } else if ("http://www.w3.org/2001/10/xml-exc-c14n#".equals(alg)) {
                            c14nAlgURI = Canonicalizer.ALGO_ID_C14N_EXCL_OMIT_COMMENTS;
                        }
                    }

                    // 5) If enveloped transform present, remove any Signature elements inside the cloned subtree
                    if (hasEnveloped) {
                        NodeList innerSigs = tmpDoc.getDocumentElement().getElementsByTagNameNS(Constants.SignatureSpecNS, "Signature");
                        for (int i = innerSigs.getLength() - 1; i >= 0; i--) {
                            Node sigNode = innerSigs.item(i);
                            sigNode.getParentNode().removeChild(sigNode);
                        }
                    }

                    // 6) canonicalize using Apache Canonicalizer with chosen algorithm
                    Canonicalizer debugCanon = Canonicalizer.getInstance(c14nAlgURI);
                    ByteArrayOutputStream debugBaos = new ByteArrayOutputStream();
                    debugCanon.canonicalizeSubtree(tmpDoc.getDocumentElement(), debugBaos);
                    byte[] canonicalBytes = debugBaos.toByteArray();

                    // write canonical bytes for inspection
                    File outdir = new File("out");
                    if (!outdir.exists()) outdir.mkdirs();
                    try (FileOutputStream fos = new FileOutputStream(new File(outdir, "documento_c14n_debug.xml"))) {
                        fos.write(canonicalBytes);
                    }
                    System.out.println("Canonicalized bytes written to out/documento_c14n_debug.xml");
                    System.out.println("Canonicalized (preview):");
                    System.out.println(new String(canonicalBytes, java.nio.charset.StandardCharsets.UTF_8));

                    // 7) compute SHA-1 and base64
                    java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-1");
                    byte[] digest = md.digest(canonicalBytes);
                    String computed = java.util.Base64.getEncoder().encodeToString(digest);
                    System.out.println("Computed SHA-1 (base64): " + computed);
                    System.out.println("Digest match? " + computed.equals(digestValueInXml));
                }
            }
        }


        // Optional: canonicalize the Documento and write it out for debugging
        Canonicalizer canon = Canonicalizer.getInstance(Canonicalizer.ALGO_ID_C14N_OMIT_COMMENTS);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        canon.canonicalizeSubtree(documento, baos);
        byte[] canonicalBytes = baos.toByteArray();

        try (FileOutputStream fos = new FileOutputStream("out/documento_c14n.xml")) {
            fos.write(canonicalBytes);
        }
        System.out.println("✅ Canonicalized Documento written to documento_c14n.xml");

        // Write the signed document
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer trans = tf.newTransformer();
        trans.setOutputProperty(OutputKeys.INDENT, "yes");
        trans.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
        trans.transform(new DOMSource(doc), new StreamResult(outputXml));

        System.out.println("✅ Signed XML written to " + outputXml.getAbsolutePath());
    }
}
