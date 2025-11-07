package SiiBoleta;

import SiiSignature.SignatureType;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.Marshaller;
import org.apache.xml.security.c14n.Canonicalizer;
import org.eclipse.persistence.oxm.NamespacePrefixMapper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.namespace.QName;
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

public class DTESign {
    public static void main(String[] args) throws Exception {
        File inputXml = new File("out/DTE.xml");
        File pkcs12 = new File("Java/certificado.pfx");
        Path filePath = Paths.get("password.txt");
        String password = Files.readString(filePath);
        File outputXml = new File("out/signed.xml");
        //GgKBfGF01GXzoUt4ksLpKVNhFkk=
        DTEMakers.signXML(inputXml, pkcs12, password, outputXml);
    }
}
