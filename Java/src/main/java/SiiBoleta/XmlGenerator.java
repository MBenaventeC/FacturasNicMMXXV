package SiiBoleta;

import jakarta.xml.bind.*;
import org.eclipse.persistence.oxm.NamespacePrefixMapper;
import org.w3c.dom.*;

import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.*;

// JAXB core
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.Marshaller;
import javax.xml.namespace.QName;

// DOM and XML parsing
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

// XML transformation (writing to file)
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

// File I/O
import java.io.File;

// Collections
import java.util.HashMap;
import java.util.Map;



public class XmlGenerator {

    public static class CustomNamespacePrefixMapper extends NamespacePrefixMapper {
        @Override
        public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
            if ("http://www.sii.cl/SiiDte".equals(namespaceUri)) {
                return ""; // default namespace
            }
            if ("http://www.w3.org/2001/XMLSchema-instance".equals(namespaceUri)) {
                return "xsi";
            }
            if ("http://www.w3.org/2000/09/xmldsig#".equals(namespaceUri)) {
                return null; // let it be declared locally
            }
            return suggestion;
        }

        @Override
        public String[] getPreDeclaredNamespaceUris() {
            return new String[] {
                    "http://www.sii.cl/SiiDte",
                    "http://www.w3.org/2001/XMLSchema-instance"
            };
        }

        public boolean shouldDeclareNamespace(String namespaceUri) {
            return !"http://www.w3.org/2000/09/xmldsig#".equals(namespaceUri);
        }
    }

    public static void printNode(Node node) throws Exception {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
        transformer.transform(new DOMSource(node), new StreamResult(System.out));
    }

    /*public static Node renameNodeWithNamespace(Document doc, Node node, String newNamespaceURI) {
        switch (node.getNodeType()) {
            case Node.ELEMENT_NODE:
                Element oldEl = (Element) node;
                Element newEl = doc.createElementNS(newNamespaceURI, oldEl.getLocalName());

                // Copy attributes (excluding old xmlns declarations)
                NamedNodeMap attrs = oldEl.getAttributes();
                for (int i = 0; i < attrs.getLength(); i++) {
                    Attr attr = (Attr) attrs.item(i);
                    String name = attr.getName();
                    if (!name.startsWith("xmlns")) {
                        newEl.setAttributeNS(attr.getNamespaceURI(), name, attr.getValue());
                    }
                }

                // Recursively copy children
                Node child = oldEl.getFirstChild();
                while (child != null) {
                    Node importedChild = renameNodeWithNamespace(doc, child, newNamespaceURI);
                    newEl.appendChild(importedChild);
                    child = child.getNextSibling();
                }
                return newEl;

            case Node.TEXT_NODE:
                return doc.createTextNode(node.getNodeValue());

            case Node.COMMENT_NODE:
                return doc.createComment(node.getNodeValue());

            case Node.CDATA_SECTION_NODE:
                return doc.createCDATASection(node.getNodeValue());

            default:
                return doc.importNode(node, true); // fallback for other types
        }
    }*/
    public static Node renameNodeWithNamespace(Document doc, Node node, String newNamespaceURI) {
        return renameNodeWithNamespace(doc, node, newNamespaceURI, false);
    }

    private static Node renameNodeWithNamespace(Document doc, Node node, String newNamespaceURI, boolean insideSignature) {
        switch (node.getNodeType()) {
            case Node.ELEMENT_NODE:
                Element oldEl = (Element) node;
                String localName = oldEl.getLocalName();

                // Determine if this node or its descendants are part of <Signature>
                boolean isSignature = insideSignature || "Signature".equals(localName);

                // Choose namespace
                String ns = isSignature
                        ? oldEl.getNamespaceURI() // keep XMLDSig namespace
                        : newNamespaceURI;        // assign SII namespace

                // Create the new element
                Element newEl = doc.createElementNS(ns, localName);

                // Copy attributes (excluding xmlns declarations)
                NamedNodeMap attrs = oldEl.getAttributes();
                for (int i = 0; i < attrs.getLength(); i++) {
                    Attr attr = (Attr) attrs.item(i);
                    String name = attr.getName();
                    if (!name.startsWith("xmlns")) {
                        newEl.setAttributeNS(attr.getNamespaceURI(), name, attr.getValue());
                    }
                }

                // Recursively copy children
                Node child = oldEl.getFirstChild();
                while (child != null) {
                    Node importedChild = renameNodeWithNamespace(doc, child, newNamespaceURI, isSignature);
                    newEl.appendChild(importedChild);
                    child = child.getNextSibling();
                }
                return newEl;

            case Node.TEXT_NODE:
                return doc.createTextNode(node.getNodeValue());

            case Node.COMMENT_NODE:
                return doc.createComment(node.getNodeValue());

            case Node.CDATA_SECTION_NODE:
                return doc.createCDATASection(node.getNodeValue());

            default:
                return doc.importNode(node, true); // fallback
        }
    }



    public static void main(String[] args) throws Exception {
        //DTE
        // 1. Create and populate your object
        DTEDefType.Documento.Encabezado.IdDoc idDoc = DTEMakers.makeIdDoc(22296,2,1,MedioPagoType.EF);
        DTEDefType.Documento.Encabezado.Emisor emisor = DTEMakers.makeEmisor();
        DTEDefType.Documento.Encabezado.Receptor receptor = DTEMakers.makeReceptor("12345678-9","H&M","Comercio al por mayo","Juan Pérez","Av. Siempre Viva 123, Oficina 4B Tel:+56.22333444","Providencia","Santiago");
        DTEDefType.Documento.Encabezado.Totales totales = DTEMakers.makeTotales(100000);
        DTEDefType.Documento.Encabezado encabezado = DTEMakers.makeEncabezado(idDoc,emisor,receptor,totales);
        DTEDefType.Documento.Detalle detalle = DTEMakers.makeDetalle(1,"ServiciodeConsultoriaenTI/1/",100000.0);
        GregorianCalendar calendar = new GregorianCalendar(2025, Calendar.APRIL, 9);

        JAXBContext cafContext = JAXBContext.newInstance(AUTORIZACION.class);
        //System.out.println(System.getProperty("user.dir"));
        File cafFile = new File("Java/FoliosSII609100003422295202510171815.xml");
        AUTORIZACION autorizacion = (AUTORIZACION) cafContext.createUnmarshaller().unmarshal(cafFile);
        DTEDefType.Documento.TED.DD.CAF cafFromXml = autorizacion.getCAF();
        DTEDefType.Documento.TED.DD dd = DD.makeDD("60910000-1",33,22296,"12345678-9","Hola",100000,"Servicio de Consultoría en TI",cafFromXml);



        //DTEDefType.Documento.TED.FRMT frmt = FRMT.makeFRMT("S1QA/yHpklCZ8Xog2UJrV/GeFzO80pPYhwclyoHM0lFSJrwPaACEXto03H1NJlN9FiZLr5RjYFwaBrVwIwjFRA==".getBytes());
        DTEDefType.Documento.TED.FRMT frmt = FRMT.makeFRMT(dd);
        DTEDefType.Documento.TED ted= TED.makeTED(dd,frmt);

        // Se genera el codigo de barras
        //Image barcode = TED.makeBarcode(ted);

        DTEDefType.Documento documento = DTEMakers.makeDocumento(encabezado,detalle,ted,"DTE-34-22296"); // folio
        //DTEMakers.makeSignature2(documento);
        SignatureType signature = DTEMakers.makeSignature(documento);
        DTEDefType dte = DTEMakers.makeDTE(documento,null);
        // ...set other fields...

        // 2. Initialize JAXBContext
        //JAXBContext context = JAXBContext.newInstance(EnvioDTE.class);

        // 3. Create marshaller and enable pretty-print
        Map<String, Object> props = new HashMap<>();
        //props.put("eclipselink.namespace-prefix-mapper", new XmlGenerator.CustomNamespacePrefixMapper());

        JAXBContext context = org.eclipse.persistence.jaxb.JAXBContextFactory.createContext(
                new Class[] { DTEDefType.class },
                props
        );

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
        //marshaller.setProperty("eclipselink.namespace-prefix-mapper", new XmlGenerator.CustomNamespacePrefixMapper());

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        Document doc = dbf.newDocumentBuilder().newDocument();

        JAXBElement<DTEDefType> jaxbElement2 = new JAXBElement<>(
                new QName(/*"http://www.sii.cl/SiiDte",*/"DTE"),
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

        /*Document doct = dbf.newDocumentBuilder().newDocument();
        Node importedRoot = doct.importNode(doc.getDocumentElement(), true);
        doct.appendChild(importedRoot);*/

        /*Element setDTEElementDTE = (Element) doc.getElementsByTagName("Documento").item(0);
        setDTEElementDTE.removeAttribute("xmlns:ns0");

        Element setDTEElementDTE2 = (Element) doc.getElementsByTagName("DTE").item(0);
        setDTEElementDTE2.removeAttribute("xmlns:xsi");
        setDTEElementDTE2.removeAttribute("xmlns");*/

        /*printNode(doct);*/
        /*Element oldElement = (Element) doc.getElementsByTagName("DTE").item(0);

        Element newElement = doc.createElementNS("http://www.sii.cl/SiiDte", oldElement.getLocalName());
        while (oldElement.hasChildNodes()) {
            newElement.appendChild(oldElement.getFirstChild());
        }

        printNode(newElement);*/

        /*Node parent = oldElement.getParentNode();
        parent.replaceChild(newElement, oldElement);*/

        DocumentBuilderFactory dbf2 = DocumentBuilderFactory.newInstance();
        dbf2.setNamespaceAware(true);
        DTEMakers.formatKeyValueElements(doc,64,0);

        DocumentBuilder builder = dbf.newDocumentBuilder();

        Document newDoc = builder.newDocument();

        // Suppose `oldDoc` is your existing document:
        Element oldRoot = doc.getDocumentElement();

        // Rebuild it (and its descendants) with the SII namespace
        Node newRoot = renameNodeWithNamespace(newDoc, oldRoot, "http://www.sii.cl/SiiDte");
        newDoc.appendChild(newRoot);

        //printNode(newDoc);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");

        File outputFile = new File("out/DTE.xml");
        outputFile.getParentFile().mkdirs();

        Result output = new StreamResult(outputFile);
        Source input = new DOMSource(doc);
        transformer.transform(input, output);

        //Envio
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
        props.put("eclipselink.namespace-prefix-mapper", new CustomNamespacePrefixMapper());
        JAXBContext context3 = org.eclipse.persistence.jaxb.JAXBContextFactory.createContext(
                new Class[] { EnvioDTE.class,EnvioDTE.SetDTE.class/*, SignatureType.class*/}, // include SignatureType to isolate its namespace
                props
        );



        Marshaller marshaller2 = context3.createMarshaller();
        marshaller2.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller2.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
        marshaller2.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://www.sii.cl/SiiDte EnvioDTE_v10.xsd");
        marshaller2.setProperty("eclipselink.namespace-prefix-mapper", new CustomNamespacePrefixMapper());

        DocumentBuilderFactory dbfnew = DocumentBuilderFactory.newInstance();
        dbfnew.setNamespaceAware(true);
        Document doc2 = dbf2.newDocumentBuilder().newDocument();


        JAXBElement<EnvioDTE> jaxbElement3 = new JAXBElement<>(
                new QName("http://www.sii.cl/SiiDte", "EnvioDTE"),
                EnvioDTE.class,
                envioDTE
        );

        marshaller2.marshal(jaxbElement3, doc2);
        Element setDTEElement = (Element) doc2.getElementsByTagName("SetDTE").item(0);
        setDTEElement.removeAttribute("xmlns:ns0");

        Element dteElement = (Element) newDoc.getElementsByTagName("DTE").item(0);
        Node importedDTE = doc2.importNode(dteElement, true);
        //Element newElement = doc2.createElementNS("http://www.sii.cl/SiiDte","DTE");

        NodeList dteList2 = doc2.getElementsByTagName("SetDTE");
        Element dteE2 = (Element) dteList2.item(0);
        dteE2.appendChild(importedDTE); // or insertBefore if needed
        //while (dteElement.hasChildNodes()) {
        //    Node importedDTE = doc2.importNode(dteElement, true);
       //     newElement.appendChild(importedDTE);
            //newElement.appendChild(dteElement.getFirstChild());
        //}
        //printNode(doc2);
        dteE2.setAttribute("ID", "SetDoc");
        dteE2.setIdAttribute("ID", true);
        SignatureType signatureEnv = DTEMakers.makeSignatureEnv(dteE2);

        // 1. Marshal signatureEnv into a DOM node
        JAXBContext sigEnvContext = JAXBContext.newInstance(SignatureType.class);
        Marshaller sigEnvMarshaller = sigEnvContext.createMarshaller();
        sigEnvMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        Document sigEnvDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        sigEnvMarshaller.marshal(signatureEnv, sigEnvDoc);

        // 2. Import the signature node into the envioDTE DOM
        Node importedSignature = doc2.importNode(sigEnvDoc.getDocumentElement(), true);

        // 3. Inject it as a sibling of <SetDTE> inside <EnvioDTE>
        NodeList envioList = doc2.getElementsByTagNameNS("http://www.sii.cl/SiiDte", "EnvioDTE");
        if (envioList.getLength() > 0) {
            Element envioRoot = (Element) envioList.item(0);
            envioRoot.appendChild(importedSignature); // or insertBefore if needed
        }

        TransformerFactory transformerf2 = TransformerFactory.newInstance();
        //transformerf2.setAttribute("indent-number", 4);
        Transformer transformer2 = transformerf2.newTransformer();
        transformer2.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer2.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");

        DTEMakers.formatKeyValueElements(doc2,64,1);
        Result output2 = new StreamResult(new File("out/EnvioDTE.xml"));
        Source input2 = new DOMSource(doc2);
        transformer2.transform(input2, output2);
    }
}