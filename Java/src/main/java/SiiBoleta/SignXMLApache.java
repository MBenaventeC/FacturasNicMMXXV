package SiiBoleta;

import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.transforms.Transforms;
import org.apache.xml.security.utils.Constants;
import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

public class SignXMLApache {

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

        findAndRegisterFirstChildId(doc,ID,"ID");

        // Get private key and certificate
        String alias = ks.aliases().nextElement();
        PrivateKey  privateKey = (PrivateKey)ks.getKey(alias, keyPassword.toCharArray());
        X509Certificate cert = (X509Certificate) ks.getCertificate(alias);

        org.apache.xml.security.Init.init();
        XMLSignature signature = new XMLSignature(doc, null, XMLSignature.ALGO_ID_SIGNATURE_RSA_SHA1);

        Transforms transforms = new Transforms(doc);
        transforms.addTransform(Transforms.TRANSFORM_C14N_OMIT_COMMENTS);
        signature.addDocument("#"+ID, transforms, Constants.ALGO_ID_DIGEST_SHA1);
        doc.getDocumentElement().appendChild(signature.getElement());
        signature.addKeyInfo(cert.getPublicKey());
        signature.addKeyInfo(cert);
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
