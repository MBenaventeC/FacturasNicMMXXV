package SiiBoleta;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DTESign {
    public static void Sign3(String name,Document in) throws Exception {
        File pkcs12 = new File("Java/certificado.pfx");
        Path filePath = Paths.get("password.txt");
        String password = Files.readString(filePath);
        Element documento = (Element) in.getElementsByTagName("Documento").item(0);
        String id = documento.getAttribute("ID");
        SignXMLApache.signXMLTS(in, pkcs12, password,id);
        return ;
    }
    public static String Sign4(String name,String in,String ID) throws Exception {
        File inputXml = new File(in);
        // Load XML document
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        dbf.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder = dbf.newDocumentBuilder();
        Document doc = builder.parse(inputXml);
        File pkcs12 = new File("Java/certificado.pfx");
        Path filePath = Paths.get("password.txt");
        String password = Files.readString(filePath);
        String out = "out/"+name+".xml";
        SignXMLApache.signXMLTS(doc, pkcs12, password, ID);
        SignXMLApache.saveDocumentToFile(doc,out);
        return out;
    }
}
