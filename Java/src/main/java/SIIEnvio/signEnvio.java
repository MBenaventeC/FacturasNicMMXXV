package SIIEnvio;

import SiiBoleta.SignXMLApache;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class signEnvio {
    /** Given a XML file, sign XML file using SII-validated certificate certificate.
     *
     * @param envio path to XML file to be signed.
     * @param out path to save Signed XML file into.
     * @param ID Section ID to be signed (ex.: "SetDoc").
     * @return path to where Signed XML file was save.
     * @throws Exception
     */
    public static String sign(String envio, String out, String ID) throws Exception {
        File inputXml = new File(envio);
        // Load XML document
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        dbf.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder = dbf.newDocumentBuilder();
        Document doc = builder.parse(inputXml);
        // write path to SII-validated certificate
        File pkcs12 = new File("Java/certificado.pfx");
        // write path to password of SII-validated certificate
        Path filePath = Paths.get("password.txt");
        String password = Files.readString(filePath);
        SignXMLApache.signXMLTS(doc, pkcs12, password, ID);
        SignXMLApache.saveDocumentToFile(doc, out);
        return out;
    }

    /** Given a XML file, the method fixes the format to satisfy SII-valid format.
     *
     * @param base path to XML file to be fixed
     * @param out path to save Fixed XML into
     * @return path to where Fixed XML was save into
     * @throws IOException
     */
    public static String Fix(String base, String out) throws IOException {
        File Xml = new File(base);
        //File output = new File("out/"+out+".xml");
        File output = new File(out+".xml");
        

        String baseContent = Files.readString(Xml.toPath(), StandardCharsets.ISO_8859_1);

        // baseContent = baseContent.replace("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"no\"?>", "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\r\n");
        // baseContent = baseContent.replace("</EnvioDTE>", "\r\n</EnvioDTE>");
        baseContent = baseContent.replaceFirst("(<\\?xml[^>]*\\?>)\\s*(<)", "$1\r\n$2");
        baseContent = baseContent.replaceFirst("\\s*</EnvioDTE>\\s*$", "\r\n</EnvioDTE>");
        // Remove XML declaration from the second file if present
        // Write output
        Files.writeString(output.toPath(), baseContent, StandardCharsets.ISO_8859_1);
        return out + ".xml";
    }
}
