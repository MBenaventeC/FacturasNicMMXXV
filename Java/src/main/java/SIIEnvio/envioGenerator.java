package SIIEnvio;

import SiiBoleta.DTEDefType;
import SiiBoleta.DTEMakers;
import SiiBoleta.XmlGenerator;
import SiiSignature.SignatureType;
import jakarta.xml.bind.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import SIIEnvio.EnvioDTE;
import SiiSignature.SignatureType;
import org.eclipse.persistence.oxm.NamespacePrefixMapper;
import org.w3c.dom.*;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.*;

// JAXB core
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.Marshaller;

// DOM and XML parsing
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

// XML transformation (writing to file)
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

// File I/O
import java.io.File;

// Collections
import java.util.HashMap;
import java.util.Map;

public class envioGenerator {
    public static void exmain(String[] args) throws DatatypeConfigurationException, JAXBException, ParserConfigurationException, IOException, SAXException, TransformerException {
        DocumentBuilderFactory dbf2 = DocumentBuilderFactory.newInstance();
        dbf2.setNamespaceAware(true);

        EnvioDTE.SetDTE.Caratula.SubTotDTE subTot = DTEMakers.makeSubTotDTE(34,1);
        List<EnvioDTE.SetDTE.Caratula.SubTotDTE> subTotDTEList = new ArrayList<>();
        subTotDTEList.add(subTot);
        EnvioDTE.SetDTE.Caratula caratula = DTEMakers.makeCaratula("20848882-1","60803000-K",0,subTotDTEList);
        List<DTEDefType> DTEList = new ArrayList<>();
        EnvioDTE.SetDTE setDTE = DTEMakers.makeSetDTE(caratula,null,"SetDoc");
        EnvioDTE envioDTE = DTEMakers.makeEnvioDTE(setDTE,null);
        // ...set other fields...

        // 2. Initialize JAXBContext
        //JAXBContext context = JAXBContext.newInstance(EnvioDTE.class);

        // 3. Create marshaller and enable pretty-print

        // Create MOXy JAXBContext with both EnvioDTE and SignatureType
        Map<String, Object> props = new HashMap<>();
        props.put("eclipselink.namespace-prefix-mapper", new XmlGenerator.CustomNamespacePrefixMapper());
        JAXBContext context3 = org.eclipse.persistence.jaxb.JAXBContextFactory.createContext(
                new Class[] { EnvioDTE.class,EnvioDTE.SetDTE.class/*, SignatureType.class*/}, // include SignatureType to isolate its namespace
                props
        );



        Marshaller marshaller2 = context3.createMarshaller();
        marshaller2.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller2.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
        marshaller2.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://www.sii.cl/SiiDte EnvioDTE_v10.xsd");
        marshaller2.setProperty("eclipselink.namespace-prefix-mapper", new XmlGenerator.CustomNamespacePrefixMapper3());

        DocumentBuilderFactory dbfnew = DocumentBuilderFactory.newInstance();
        dbfnew.setNamespaceAware(true);
        Document doc2 = dbf2.newDocumentBuilder().newDocument();


        JAXBElement<EnvioDTE> jaxbElement3 = new JAXBElement<>(
                new QName("http://www.sii.cl/SiiDte", "EnvioDTE"),
                EnvioDTE.class,
                envioDTE
        );

        marshaller2.marshal(jaxbElement3, doc2);

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder builder = dbf.newDocumentBuilder();
        File inputXml = new File("out/signed.xml");
        Document doc = builder.parse(inputXml);

        Element dteElement = (Element) doc.getElementsByTagName("DTE").item(0);
        Node importedDTE = doc2.importNode(dteElement, true);

        NodeList dteList2 = doc2.getElementsByTagName("SetDTE");
        Element dteE2 = (Element) dteList2.item(0);
        dteE2.appendChild(importedDTE);

        dteE2.setAttribute("ID", "SetDoc");
        dteE2.setIdAttribute("ID", true);

        TransformerFactory transformerf2 = TransformerFactory.newInstance();
        //transformerf2.setAttribute("indent-number", 4);
        Transformer transformer2 = transformerf2.newTransformer();
        transformer2.setOutputProperty(OutputKeys.INDENT, "no");
        transformer2.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        transformer2.transform(new DOMSource(doc2), new StreamResult(baos));

        // Convert to string safely, removing only carriage return entities
        String xmlClean = baos.toString("ISO-8859-1")
                .replace("ns0:EnvioDTE", "EnvioDTE")
                .replace("xmlns:ns0=\"http://www.sii.cl/SiiDte\"", "xmlns=\"http://www.sii.cl/SiiDte\"")
                .replaceAll("><", ">\n<"); // only remove encoded CRs
        System.out.println(xmlClean);
        // Write back to file preserving your indentation
        File outputXml = new File("out/EnvioDTE.xml");
        Files.write(outputXml.toPath(), xmlClean.getBytes("ISO-8859-1"));

        /*Result output2 = new StreamResult(new File("out/EnvioDTE.xml"));
        Source input2 = new DOMSource(doc2);
        transformer2.transform(input2, output2);*/
    }

    public static void main(String[] args) throws JAXBException, ParserConfigurationException, IOException, SAXException, TransformerException {
        DocumentBuilderFactory dbf2 = DocumentBuilderFactory.newInstance();
        dbf2.setNamespaceAware(true);
        EnvioDTE envioDTE = DTEMakers.makeEnvioDTE(null,null);
        // Create MOXy JAXBContext with both EnvioDTE and SignatureType
        Map<String, Object> props = new HashMap<>();
        props.put("eclipselink.namespace-prefix-mapper", new XmlGenerator.CustomNamespacePrefixMapper());
        JAXBContext context3 = org.eclipse.persistence.jaxb.JAXBContextFactory.createContext(
                new Class[] { EnvioDTE.class,EnvioDTE.SetDTE.class/*, SignatureType.class*/}, // include SignatureType to isolate its namespace
                props
        );



        Marshaller marshaller2 = context3.createMarshaller();
        marshaller2.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller2.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
        marshaller2.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://www.sii.cl/SiiDte EnvioDTE_v10.xsd");
        marshaller2.setProperty("eclipselink.namespace-prefix-mapper", new XmlGenerator.CustomNamespacePrefixMapper3());

        DocumentBuilderFactory dbfnew = DocumentBuilderFactory.newInstance();
        dbfnew.setNamespaceAware(true);
        Document doc2 = dbf2.newDocumentBuilder().newDocument();


        JAXBElement<EnvioDTE> jaxbElement3 = new JAXBElement<>(
                new QName("http://www.sii.cl/SiiDte", "EnvioDTE"),
                EnvioDTE.class,
                envioDTE
        );

        marshaller2.marshal(jaxbElement3, doc2);

        TransformerFactory transformerf2 = TransformerFactory.newInstance();
        //transformerf2.setAttribute("indent-number", 4);
        Transformer transformer2 = transformerf2.newTransformer();
        transformer2.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer2.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        transformer2.transform(new DOMSource(doc2), new StreamResult(baos));

        // Convert to string safely, removing only carriage return entities
        String xmlClean = baos.toString("ISO-8859-1")
                //.replace("ns0:EnvioDTE", "EnvioDTE")
                //.replace("xmlns:ns0=\"http://www.sii.cl/SiiDte\"", "xmlns=\"http://www.sii.cl/SiiDte\"")
                /*.replaceAll("><", ">\n<")*/; // only remove encoded CRs
        System.out.println(xmlClean);
        // Write back to file preserving your indentation
        File outputXml = new File("out/EnvioEmpty.xml");
        Files.write(outputXml.toPath(), xmlClean.getBytes("ISO-8859-1"));
    }
}
