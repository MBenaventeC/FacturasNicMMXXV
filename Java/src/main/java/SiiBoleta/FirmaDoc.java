/**package SiiBoleta;

import SIIEnvio.EnvioDTE;
import SiiSignature.SignatureType;
import jakarta.xml.bind.*;
import org.eclipse.persistence.oxm.NamespacePrefixMapper;
import org.w3c.dom.*;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.*;

// JAXB core

// DOM and XML parsing
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

// XML transformation (writing to file)
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

// File I/O
import java.io.File;

// Collections
import java.util.HashMap;
import java.util.Map;

public class FirmaDoc {
    public static void main(String[] args) throws Exception {
        JAXBContext docContext = JAXBContext.newInstance(DTEDefType.Documento.class);
        File docFile = new File("out/DTE.xml");
        //DTEDefType.Documento documento = (DTEDefType.Documento) docContext.createUnmarshaller().unmarshal(docFile);

        Unmarshaller um = docContext.createUnmarshaller();
        JAXBElement<DTEDefType.Documento> je =
                um.unmarshal(new javax.xml.transform.stream.StreamSource(docFile), DTEDefType.Documento.class);
        DTEDefType.Documento documento = je.getValue();


        SignatureType signature = DTEMakers.makeSignature(documento);
        DTEDefType dte = DTEMakers.makeDTE(documento,null);

        Map<String, Object> props = new HashMap<>();
        props.put("eclipselink.namespace-prefix-mapper", new XmlGenerator.CustomNamespacePrefixMapper2());

        JAXBContext context = org.eclipse.persistence.jaxb.JAXBContextFactory.createContext(
                new Class[] { DTEDefType.class },
                props
        );

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
        marshaller.setProperty("eclipselink.namespace-prefix-mapper", new XmlGenerator.CustomNamespacePrefixMapper2());

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        Document doc = dbf.newDocumentBuilder().newDocument();

        JAXBElement<DTEDefType> jaxbElement2 = new JAXBElement<>(
                new QName("http://www.sii.cl/SiiDte","DTE"),
                DTEDefType.class,
                dte
        );

        marshaller.marshal(jaxbElement2, doc);

        //sig2
        JAXBContext sigContext = JAXBContext.newInstance(SignatureType.class);
        Marshaller sigMarshaller = sigContext.createMarshaller();
        sigMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

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
        DTEMakers.formatKeyValueElements(doc,64,0);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");



        File outputFile = new File("out/DTESigned2.xml");
        outputFile.getParentFile().mkdirs();

        Result output = new StreamResult(outputFile);
        Source input = new DOMSource(doc);
        transformer.transform(input, output);
    }
}
*/