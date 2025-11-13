package SIIEnvio;

import SiiBoleta.DTEMakers;
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
    public static String sign(String envio, String out) throws Exception {
        File inputXml = new File(envio);
        File pkcs12 = new File("Java/certificado.pfx");
        Path filePath = Paths.get("password.txt");
        String password = Files.readString(filePath);
        File outputXml = new File("out/" + out + ".xml");
        DTEMakers.signXMLT(inputXml, pkcs12, password, outputXml);
        return "out/" + out + ".xml";
    }

    public static String sign2(Document envio, String out) throws Exception {
        File pkcs12 = new File("Java/certificado.pfx");
        Path filePath = Paths.get("password.txt");
        String password = Files.readString(filePath);
        File outputXml = new File("out/" + out + ".xml");
        DTEMakers.signXMLTD(envio, pkcs12, password, outputXml);
        return "out/" + out + ".xml";
    }

    public static String sign3(Document in, String name) throws Exception {
        File pkcs12 = new File("Java/certificado.pfx");
        Path filePath = Paths.get("password.txt");
        String password = Files.readString(filePath);
        String out = "out/" + name + ".xml";
        File outputXml = new File(out);
        //GgKBfGF01GXzoUt4ksLpKVNhFkk=
        SignXMLApache.signXMLTS(in, pkcs12, password, "EnvDte-63130");
        SignXMLApache.saveDocumentToFile(in, out);
        return "out/" + out + ".xml";
    }

    public static String sign4(String envio, String out, String ID) throws Exception {
        File inputXml = new File(envio);
        // Load XML document
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        dbf.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder = dbf.newDocumentBuilder();
        Document doc = builder.parse(inputXml);
        File pkcs12 = new File("Java/certificado.pfx");
        Path filePath = Paths.get("password.txt");
        String password = Files.readString(filePath);
        SignXMLApache.signXMLTS(doc, pkcs12, password, ID);
        SignXMLApache.saveDocumentToFile(doc, out);
        return out;
    }

    public static String Fix(String base, String out) throws IOException {
        File Xml = new File(base);
        File output = new File("out/"+out+".xml");

        String baseContent = Files.readString(Xml.toPath(), StandardCharsets.ISO_8859_1);

        baseContent = baseContent.replace("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"no\"?>", "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\r\n");

        //System.out.println("baseContent: " + baseContent);

        // Remove XML declaration from the second file if present
        // Write output
        Files.writeString(output.toPath(), baseContent, StandardCharsets.ISO_8859_1);

        return "out/" + out + ".xml";
    }
}
