import org.apache.xml.security.Init;
import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.transforms.Transforms;
import org.apache.xml.security.keys.KeyInfo;
import org.apache.xml.security.algorithms.MessageDigestAlgorithm;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class FirmarXML {
    public static void main(String[] args) throws Exception {

        // 1) Cargar el archivo .p12 o .pfx ===
        /**
         * AÑADAN SU ARCHIVO .PFX A LA CARPETA JAVA
         * (CUIDADO CON SUBIRLA A GITHUB, PORFAVOR AÑADIRLA A SU GITIGNORE O ALGO)
         * Y PEGAR SU RUTA A rutaPFX
         */
        String rutaPFX = "C:\\universidad\\proyecto_de_software\\FacturasNicMMXXV\\Java\\certificado.pfx";
        /**
         * LA CONTRASEÑA ES LA MISMA QUE SE CREÓ CUANDO SE GENERÓ EL CERTIFICADO
         */
        String password = "";

        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(new FileInputStream(rutaPFX), password.toCharArray());

        // Obtener el alias (normalmente el primero)
        String alias = ks.aliases().nextElement();

        // Obtener la clave privada y el certificado
        PrivateKey privateKey = (PrivateKey) ks.getKey(alias, password.toCharArray());
        X509Certificate cert = (X509Certificate) ks.getCertificate(alias);

        // 2) Cargar el XML a firmar ===
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true); // importante para firma XML
        Document doc = dbf.newDocumentBuilder().parse(new File("FACTURA_PARA_FIRMAR_TEST.xml"));

        // Hallar el elemento Documento y asegurar el atributo ID como tipo ID
        Element documento = (Element) doc.getElementsByTagName("Documento").item(0);
        String docId = documento.getAttribute("ID");
        if (docId.isEmpty()) {
            throw new IllegalStateException("El <Documento> debe tener atributo ID.");
        }
        // Marcar atributo como tipo ID para Santuario
        documento.setIdAttribute("ID", true);

        // 3) Inicializar Apache Santuario ===
        Init.init();

        // 4) Crear la firma ===
        String baseURI = new File("documento.xml").toURI().toString();
        XMLSignature firma = new XMLSignature(
                doc,
                baseURI,
                XMLSignature.ALGO_ID_SIGNATURE_RSA_SHA1);

        // Transforms: indicar que se aplica canonización y referencia local
        Transforms transforms = new Transforms(doc);
        transforms.addTransform(Transforms.TRANSFORM_ENVELOPED_SIGNATURE);
        transforms.addTransform(Transforms.TRANSFORM_C14N_OMIT_COMMENTS);

        // 5) Agregar referencia al nodo por ID y digest sha1
        firma.addDocument("#" + docId, transforms, MessageDigestAlgorithm.ALGO_ID_DIGEST_SHA1);

        // 6) Agregar información de la clave/certificado ===
        firma.addKeyInfo(cert);
        firma.addKeyInfo(cert.getPublicKey());

        // 7) Insertar <Signature> en <DTE> (hermano de <Documento>)
        Element dte = (Element) doc.getElementsByTagName("DTE").item(0);
        dte.appendChild(firma.getElement());

        // 8) Firmar el XML ===
        firma.sign(privateKey);

        // 9) Serializar con ISO-8859-1 y saltos de línea y Guardar el XML firmado ===
        Transformer tf = TransformerFactory.newInstance().newTransformer();
        tf.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        tf.transform(new DOMSource(doc), new StreamResult(new File("DTE_firmado.xml")));

        System.out.println("✅ Documento firmado correctamente: documento_firmado.xml");
    }
}
