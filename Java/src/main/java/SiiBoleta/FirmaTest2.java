package SiiBoleta;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.w3c.dom.Element;

public class FirmaTest2 {
    private static void removeWhitespaceNodes(Element e) {
        NodeList children = e.getChildNodes();
        for (int i = children.getLength() - 1; i >= 0; i--) {
            Node n = children.item(i);
            if (n.getNodeType() == Node.TEXT_NODE) {
                if (n.getTextContent().trim().isEmpty()) {
                    e.removeChild(n);
                }
            } else if (n.getNodeType() == Node.ELEMENT_NODE) {
                removeWhitespaceNodes((Element) n);
            }
        }
    }

    public static void main(String[] args) throws Exception {

        File xmlFile = new File("out/DTE.xml"); // <----------

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        Document doc = dbf.newDocumentBuilder().parse(xmlFile);
        removeWhitespaceNodes(doc.getDocumentElement());
        doc.normalizeDocument();

        XmlGenerator.printNode(doc);

        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

        String uri = "#DTE-34-22295";

        String pfxPath = "Java/certificado.pfx"; // <----------
        Path filePath = Paths.get("password.txt");
        String pfxPassword = Files.readString(filePath); // <----------

        KeyStore ks = KeyStore.getInstance("PKCS12");

        try (FileInputStream fis = new FileInputStream(pfxPath)) {
            ks.load(fis, pfxPassword.toCharArray());
        }

        String alias = ks.aliases().nextElement();

        PrivateKey pKey = (PrivateKey) ks.getKey(alias, pfxPassword.toCharArray());

        X509Certificate cert = (X509Certificate) ks.getCertificate(alias);

        Reference ref = fac.newReference(uri, fac.newDigestMethod(
                DigestMethod.SHA1,null), Collections.singletonList(fac
                .newTransform(Transform.ENVELOPED,
                        (TransformParameterSpec) null)), null, null);

        String method = SignatureMethod.RSA_SHA1;

        if ("DSA".equals(cert.getPublicKey().getAlgorithm())) method = SignatureMethod.DSA_SHA1;
        else if ("HMAC".equals(cert.getPublicKey().getAlgorithm())) method = SignatureMethod.HMAC_SHA1;

        SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod((
                        CanonicalizationMethod.INCLUSIVE),
                (C14NMethodParameterSpec) null), fac.newSignatureMethod(method,
                null), Collections.singletonList(ref));

        KeyInfoFactory kif = fac.getKeyInfoFactory();
        KeyValue kv = kif.newKeyValue(cert.getPublicKey());

        List<XMLStructure> kidata = new ArrayList<XMLStructure>();
        kidata.add(kv);
        kidata.add(kif.newX509Data(Collections.singletonList(cert)));
        KeyInfo ki = kif.newKeyInfo(kidata);

        doc.getElementsByTagName("Documento");

        //XmlGenerator.printNode(doc);

        DOMSignContext dsc = new DOMSignContext(pKey, doc.getDocumentElement());

        XMLSignature signature = fac.newXMLSignature(si, ki);

        signature.sign(dsc);
        //XmlGenerator.printNode(doc);
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");



        File outputFile = new File("out/DTESignedTest.xml");
        outputFile.getParentFile().mkdirs();

        Result output = new StreamResult(outputFile);
        Source input = new DOMSource(doc);
        transformer.transform(input, output);
    }
}
