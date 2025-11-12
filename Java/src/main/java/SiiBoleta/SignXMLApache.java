package SiiBoleta;

import org.apache.xml.security.Init;
import org.apache.xml.security.c14n.Canonicalizer;
import org.apache.xml.security.keys.KeyInfo;
import org.apache.xml.security.keys.content.X509Data;
import org.apache.xml.security.signature.ReferenceNotInitializedException;
import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.transforms.Transforms;
import org.apache.xml.security.utils.Constants;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
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

    public static Object[] loadKeyAndChainFromPfx(String pfxPath, char[] password) throws Exception {
        KeyStore ks = KeyStore.getInstance("PKCS12");
        try (FileInputStream fis = new FileInputStream(pfxPath)) {
            ks.load(fis, password);
        }
        String alias = ks.aliases().nextElement();

        PrivateKey privateKey = (PrivateKey) ks.getKey(alias, password);
        // CRUCIAL: Get the entire chain, not just the single cert
        Certificate[] chain = ks.getCertificateChain(alias);

        if (privateKey == null || chain == null || chain.length == 0) {
            throw new Exception("No se pudo cargar la clave privada o la cadena de certificados del PFX.");
        }

        return new Object[]{privateKey, chain};
    }

    public static void signXMLTS(Document doc, File pkcs12File, String keyPassword,String ID) throws Exception {

        // Load the keystore (PKCS12)
        KeyStore ks = KeyStore.getInstance("PKCS12");
        try (FileInputStream fis = new FileInputStream(pkcs12File)) {
            ks.load(fis, keyPassword.toCharArray());
        }

        //DTEMakers.printNode(doc.getDocumentElement());

        findAndRegisterFirstChildId(doc,ID,"ID");

        // Get private key and certificate
        String alias = ks.aliases().nextElement();
        PrivateKey  privateKey = (PrivateKey)ks.getKey(alias, keyPassword.toCharArray());
        X509Certificate cert = (X509Certificate) ks.getCertificate(alias);
        Certificate[] chain = ks.getCertificateChain(alias);

        //Object[] keyData = loadKeyAndChainFromPfx("Java/certificado.pfx", keyPassword.toCharArray());
        //PrivateKey privateKey = (PrivateKey) keyData[0];
        //Certificate[] certChain = (Certificate[]) keyData[1];

        org.apache.xml.security.Init.init();
        XMLSignature signature = new XMLSignature(doc, null, XMLSignature.ALGO_ID_SIGNATURE_RSA_SHA1);

        Transforms transforms = new Transforms(doc);
        //transforms.addTransform(Transforms.TRANSFORM_ENVELOPED_SIGNATURE);
        transforms.addTransform(Transforms.TRANSFORM_C14N_OMIT_COMMENTS);
        signature.addDocument("#"+ID, transforms, Constants.ALGO_ID_DIGEST_SHA1);
        doc.getDocumentElement().appendChild(signature.getElement());
        //PublicKey publicKey = certChain[0].getPublicKey();
        signature.addKeyInfo(cert.getPublicKey());
        signature.addKeyInfo(cert);
        //for (Certificate cert : certChain) {
        //    if (cert instanceof X509Certificate) {
        //        signature.addKeyInfo((X509Certificate) cert);
        //    }
        //}
        signature.sign(privateKey);
    }
    public static void saveDocumentToFile(Document document, String outputFilePath)
            throws Exception {

        // Use the standard JAXP Transformer to write the DOM to a file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");

        // Optional: Add indentation for better readability
        transformer.setOutputProperty(OutputKeys.INDENT, "no");
        // Note: For production, canonicalization is crucial, but for simple saving, indent is fine.

        DOMSource source = new DOMSource(document);

        // Define the output location
        File outputFile = new File(outputFilePath);
        OutputStream os = new FileOutputStream(outputFile);
        StreamResult result = new StreamResult(os);

        // Transform (write) the source DOM to the result output stream
        transformer.transform(source, result);

        os.close();
        System.out.println("Signed XML document successfully saved to: " + outputFilePath);
    }
    public static Element findAndRegisterFirstChildId(Document document, String elementIdValue, String attributeName)
            throws Exception {

        // 1. Get the root element
        Element root = document.getDocumentElement();

        // 2. Get the first child element (skipping potential text/comment nodes)
        Element elementToSign = null;
        Node child = root.getFirstChild();
        while (child != null) {
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                elementToSign = (Element) child;
                break; // Found the first element child
            }
            child = child.getNextSibling();
        }

        if (elementToSign == null) {
            throw new IllegalStateException("No first child element found under the root element.");
        }

        // 3. Verify it has the correct ID value and register it
        Attr attributeNode = elementToSign.getAttributeNode(attributeName);

        if (attributeNode != null && attributeNode.getValue().equals(elementIdValue)) {
            // Crucial step: Mark this specific attribute as an official DOM ID type
            elementToSign.setIdAttributeNode(attributeNode, true);
            System.out.println("Registered ID attribute '" + attributeName + "' for the first child element: " + elementIdValue);
            return elementToSign;
        } else if (attributeNode == null) {
            throw new IllegalStateException("First child element is missing the expected attribute '" + attributeName + "'.");
        } else {
            throw new IllegalStateException("First child element attribute value did not match the expected ID: " + elementIdValue);
        }
    }
}
