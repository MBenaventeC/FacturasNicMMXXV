package SIIEnvio;

import SiiBoleta.DTEMakers;
import SiiBoleta.SignXMLApache;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class signEnvio {
    public static String sign(String envio,String out) throws Exception {
        File inputXml = new File(envio);
        File pkcs12 = new File("Java/certificado.pfx");
        Path filePath = Paths.get("password.txt");
        String password = Files.readString(filePath);
        File outputXml = new File("out/"+out+".xml");
        DTEMakers.signXMLT(inputXml, pkcs12, password, outputXml);
        return "out/"+out+".xml";
    }
    public static String sign2(Document envio, String out) throws Exception {
        File pkcs12 = new File("Java/certificado.pfx");
        Path filePath = Paths.get("password.txt");
        String password = Files.readString(filePath);
        File outputXml = new File("out/"+out+".xml");
        DTEMakers.signXMLTD(envio, pkcs12, password, outputXml);
        return "out/"+out+".xml";
    }
    public static String sign3(Document in,String name) throws Exception {
        File pkcs12 = new File("Java/certificado.pfx");
        Path filePath = Paths.get("password.txt");
        String password = Files.readString(filePath);
        String out = "out/" + name + ".xml";
        File outputXml = new File(out);
        //GgKBfGF01GXzoUt4ksLpKVNhFkk=
        SignXMLApache.signXMLTS(in, pkcs12, password, "EnvDte-63130");
        SignXMLApache.saveDocumentToFile(in,out);
        return "out/"+out+".xml";
    }
    public static String sign4(String envio,String out,String ID) throws Exception {
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
        SignXMLApache.signXMLTS(doc, pkcs12, password,ID);
        SignXMLApache.saveDocumentToFile(doc,out);
        return "out/"+out+".xml";
    }
    }
