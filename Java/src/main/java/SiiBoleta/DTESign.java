package SiiBoleta;

import SiiSignature.SignatureType;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.Marshaller;
import org.apache.xml.security.c14n.Canonicalizer;
//import org.eclipse.persistence.oxm.NamespacePrefixMapper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import SiiBoleta.SignXMLApache;

public class DTESign {
    public static String Sign(String name,String in) throws Exception {
        File inputXml = new File(in);
        File pkcs12 = new File("Java/certificado.pfx");
        Path filePath = Paths.get("password.txt");
        String password = Files.readString(filePath);
        String out = "out/"+name+".xml";
        File outputXml = new File(out);
        //GgKBfGF01GXzoUt4ksLpKVNhFkk=
        DTEMakers.signXML(inputXml, pkcs12, password, outputXml);
        return out;
    }
    public static void Sign2(String name,Document in) throws Exception {
        File pkcs12 = new File("Java/certificado.pfx");
        Path filePath = Paths.get("password.txt");
        String password = Files.readString(filePath);
        String out = "out/"+name+".xml";
        File outputXml = new File(out);
        //GgKBfGF01GXzoUt4ksLpKVNhFkk=
        DTEMakers.signXMLD(in, pkcs12, password, outputXml);
        return ;
    }
    public static void Sign3(String name,Document in) throws Exception {
        File pkcs12 = new File("Java/certificado.pfx");
        Path filePath = Paths.get("password.txt");
        String password = Files.readString(filePath);
        String out = "out/"+name+".xml";
        File outputXml = new File(out);
        //GgKBfGF01GXzoUt4ksLpKVNhFkk=
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
        File pkcs12 = new File("certificado.pfx");
        Path filePath = Paths.get("password.txt");
        String password = Files.readString(filePath);
        String out = "out/"+name+".xml";
        //GgKBfGF01GXzoUt4ksLpKVNhFkk=
        SignXMLApache.signXMLTS(doc, pkcs12, password, ID);
        SignXMLApache.saveDocumentToFile(doc,out);
        return out;
    }
}
