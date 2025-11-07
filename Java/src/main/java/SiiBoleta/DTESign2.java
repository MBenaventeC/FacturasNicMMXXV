package SiiBoleta;

import SiiSignature.SignatureType;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.eclipse.persistence.oxm.NamespacePrefixMapper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class DTESign2 {
    /*public static void main(String[] args) throws JAXBException, ParserConfigurationException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        Document doc = dbf.newDocumentBuilder().newDocument();

        JAXBContext sigContext = JAXBContext.newInstance(SignatureType.class);
        Marshaller sigMarshaller = sigContext.createMarshaller();
        sigMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        sigMarshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");

        Document sigDoc = dbf.newDocumentBuilder().newDocument();
        sigMarshaller.marshal(signature, sigDoc);

        Node signatureNode = doc.importNode(sigDoc.getDocumentElement(), true);

        NodeList dteList = doc.getElementsByTagName("DTE");
        if (dteList.getLength() > 0) {
            Element dteE = (Element) dteList.item(0);
            dteE.appendChild(signatureNode); // or insertBefore if needed
        }
        DocumentBuilderFactory dbf2 = DocumentBuilderFactory.newInstance();
        dbf2.setNamespaceAware(true);
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");



        File outputFile = new File("out/DTE.xml");
        outputFile.getParentFile().mkdirs();

        Result output = new StreamResult(outputFile);
        Source input = new DOMSource(doc);
        transformer.transform(input, output);
    }*/
}
