import org.apache.xml.security.Init;
import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.keys.KeyInfo;
import org.apache.xml.security.keys.content.KeyValue;
import org.apache.xml.security.keys.content.X509Data;
import org.apache.xml.security.keys.content.x509.XMLX509Certificate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class FirmarXML {

    static { Init.init(); }

    public static void main(String[] args) throws Exception {

        String password = "";
        String nombreDocumento = "FACTURA_PARA_FIRMAR_TEST.xml";

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        Document doc = dbf.newDocumentBuilder().parse(new File(nombreDocumento));

        // Marcar ID del Documento
        Element documento = (Element) doc.getElementsByTagName("Documento").item(0);
        documento.setIdAttribute("ID", true);

        // Cargar certificado y clave privada
        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(new File("certificado.pfx").toURI().toURL().openStream(), password.toCharArray());
        String alias = ks.aliases().nextElement();
        PrivateKey privateKey = (PrivateKey) ks.getKey(alias, password.toCharArray());
        X509Certificate cert = (X509Certificate) ks.getCertificate(alias);

        // Crear XMLSignature con SHA1+RSA y C14N estándar
        XMLSignature signature = new XMLSignature(
                doc,
                null,
                XMLSignature.ALGO_ID_SIGNATURE_RSA_SHA1,
                "http://www.w3.org/TR/2001/REC-xml-c14n-20010315"
        );

        // Añadir referencia al Documento por ID
        signature.addDocument("#" + documento.getAttribute("ID"), null,
                "http://www.w3.org/2000/09/xmldsig#sha1");

        // Crear KeyInfo y agregar KeyValue + X509Data
        KeyInfo keyInfo = new KeyInfo(doc);

        // KeyValue
        KeyValue keyValue = new KeyValue(doc, cert.getPublicKey());
        keyInfo.add(keyValue);

        // X509Data
        X509Data x509Data = new X509Data(doc);
        x509Data.add(new XMLX509Certificate(doc, cert));
        keyInfo.add(x509Data);

        // Asociar KeyInfo al XMLSignature
        signature.getElement().appendChild(keyInfo.getElement());

        // Insertar Signature después de <Documento>
        Element dte = (Element) doc.getElementsByTagName("DTE").item(0);
        dte.appendChild(signature.getElement());

        // Firmar con la clave privada
        signature.sign(privateKey);

        // Limpiar saltos de línea en SignatureValue
        Element sigValue = (Element) doc.getElementsByTagName("SignatureValue").item(0);
        if(sigValue != null) {
            sigValue.setTextContent(sigValue.getTextContent().replace("\r","").replace("\n",""));
        }

        // Limpiar saltos de línea en Modulus
        Element modulus = (Element) doc.getElementsByTagName("Modulus").item(0);
        if(modulus != null) {
            modulus.setTextContent(modulus.getTextContent().replace("\r","").replace("\n",""));
        }

        // Guardar XML final sin indentación
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "no");
        transformer.transform(new DOMSource(doc), new StreamResult(new File("dte_firmado.xml")));

        System.out.println("DTE firmado correctamente, listo para SII.");
    }
}
