package SiiBoleta;

//import org.apache.xml.serialize.OutputFormat;
//import org.apache.xml.serialize.XMLSerializer;
import SIIEnvio.EnvioDTE;
import SiiSignature.SignatureType;
import org.eclipse.persistence.oxm.NamespacePrefixMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.*;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    public static class CustomNamespacePrefixMapper2 extends NamespacePrefixMapper {
        @Override
        public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
            if ("http://www.sii.cl/SiiDte".equals(namespaceUri)) {
                return ""; // default namespace
            }
            if ("http://www.w3.org/2000/09/xmldsig#".equals(namespaceUri)) {
                return null; // let it be declared locally
            }
            if ("".equals(namespaceUri)) {
                return null; // let it be declared locally
            }
            return suggestion;
        }

        @Override
        public String[] getPreDeclaredNamespaceUris() {
            return new String[] {
                    "http://www.sii.cl/SiiDte"
            };
        }

        public boolean shouldDeclareNamespace(String namespaceUri) {
            return !"http://www.w3.org/2000/09/xmldsig#".equals(namespaceUri);
        }
    }

    public static class CustomNamespacePrefixMapper3 extends NamespacePrefixMapper {
        @Override
        public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
            if ("http://www.sii.cl/SiiDte".equals(namespaceUri)) {
                return null; // default namespace
            }
            if ("http://www.w3.org/2000/09/xmldsig#".equals(namespaceUri)) {
                return null; // let it be declared locally
            }
            if ("".equals(namespaceUri)) {
                return null; // let it be declared locally
            }
            return suggestion;
        }

        @Override
        public String[] getPreDeclaredNamespaceUris() {
            return new String[] {
                    //"http://www.sii.cl/SiiDte"
            };
        }

        public boolean shouldDeclareNamespace(String namespaceUri) {
            return !"http://www.w3.org/2000/09/xmldsig#".equals(namespaceUri);
        }
    }

    public static void printNode(Node node) throws Exception {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
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


   /** public static void main(String[] args) throws Exception {
        // Search for a JSON array, with all DTEs to process
        String jsonRuta = Files.readString(Paths.get("jsonTemplate.json"));
        JSONArray jsonDTEs = DTEMakers.leerJsonArray(jsonRuta);

        // DTEs
        JAXBContext cafContext = JAXBContext.newInstance(AUTORIZACION.class);
        File cafFile = new File("Java/FoliosSII609100003422295202510171815.xml");
        AUTORIZACION autorizacion = (AUTORIZACION) cafContext.createUnmarshaller().unmarshal(cafFile);
        DTEDefType.Documento.TED.DD.CAF cafFromXml = autorizacion.getCAF();
        for (int i = 0; i < jsonDTEs.length(); i++) {
            JSONObject jsonDTE = jsonDTEs.getJSONObject(i);
            // DTE
            // 1. Create and populate your object
            // First extract <idDoc> values from JSON
            JSONObject idoc = jsonDTE.getJSONObject("documento");
            int tipoDoc = idoc.getInt("tipoDocumento");
            int folio = idoc.getInt("folio");
            int indSer = idoc.getInt("indServicio");
            int fmaPago = idoc.getInt("fmaPago");
            String medioPago = idoc.getString("medioPago");
            MedioPagoType mdPago = MedioPagoType.fromValue(medioPago);
            DTEDefType.Documento.Encabezado.IdDoc idDoc = DTEMakers.makeIdDoc(tipoDoc,folio,indSer,fmaPago,mdPago);

            // repeat process for <Emisor> (thou right now we use a fix <Emisor>)
            JSONObject emi = jsonDTE.getJSONObject("emisor");
            String rutEm = emi.getString("rutEmisor");
            String rznEm = emi.getString("rznSoc");
            String girEm = emi.getString("giroEmis");
            String suc = emi.getString("sucursal");
            String dirOr = emi.getString("dirOrigen");
            String cmnaOr = emi.getString("cmnaOrigen");
            String ciudadOr = emi.getString("ciudadOrigen");
            DTEDefType.Documento.Encabezado.Emisor emisor = DTEMakers.makeEmisor();

            // Extract values of <Receptor> from json to <Receptor>
            JSONObject recep = jsonDTE.getJSONObject("receptor");
            String rutRe = recep.getString("rutReceptor");
            String rznScR = recep.getString("rznSocRecep");
            String girRec = recep.getString("giroRecep");
            String contact = recep.getString("contacto");
            String dirR = recep.getString("dirRecep");
            String cmnaR = recep.getString("cmnaRecep");
            String ciudadR = recep.getString("ciudadRecep");
            DTEDefType.Documento.Encabezado.Receptor receptor = DTEMakers.makeReceptor(rutRe,rznScR,girRec,contact,dirR,cmnaR,ciudadR);

            // Extract values of <Totales> from json to <Receptor>
            JSONObject tot = jsonDTE.getJSONObject("totales");
            int mnttotal = tot.getInt("montoTotal");
            DTEDefType.Documento.Encabezado.Totales totales = DTEMakers.makeTotales(mnttotal);
            DTEDefType.Documento.Encabezado encabezado = DTEMakers.makeEncabezado(idDoc,emisor,receptor,totales);

            JSONArray detallesJson = jsonDTE.getJSONArray("detalles");
            JSONObject detalleJson = detallesJson.getJSONObject(1);
            String IT1 = detalleJson.getString("nmbItem");
            List<DTEDefType.Documento.Detalle> detalles = DTEMakers.makeDetalles(detallesJson);
            DTEDefType.Documento.Detalle detalle = DTEMakers.makeDetalle(1,"ServiciodeConsultoriaenTI/1/",1,100000.0);
            GregorianCalendar calendar = new GregorianCalendar(2025, Calendar.APRIL, 9);

            DTEDefType.Documento.TED.DD dd = DD.makeDD(rutEm,tipoDoc,folio,rutRe,rznScR,mnttotal,IT1,cafFromXml);

            //DTEDefType.Documento.TED.FRMT frmt = FRMT.makeFRMT("S1QA/yHpklCZ8Xog2UJrV/GeFzO80pPYhwclyoHM0lFSJrwPaACEXto03H1NJlN9FiZLr5RjYFwaBrVwIwjFRA==".getBytes());
            DTEDefType.Documento.TED.FRMT frmt = FRMT.makeFRMT(dd);
            DTEDefType.Documento.TED ted= TED.makeTED(dd,frmt);

            // Se genera el codigo de barras
            //Image barcode = TED.makeBarcode(ted);

            String id = "DTE-"+tipoDoc+"-"+folio;
            DTEDefType.Documento documento = DTEMakers.makeDocumento(encabezado,detalles,ted,id);
            //DTEMakers.makeSignature2(documento);
            SignatureType signature = DTEMakers.makeSignature(documento);
            DTEDefType dte = DTEMakers.makeDTE(documento,null);
            // ...set other fields...

            // 2. Initialize JAXBContext
            //JAXBContext context = JAXBContext.newInstance(EnvioDTE.class);

            // 3. Create marshaller and enable pretty-print
            Map<String, Object> props = new HashMap<>();
            props.put("eclipselink.namespace-prefix-mapper", new XmlGenerator.CustomNamespacePrefixMapper3());

            JAXBContext context = org.eclipse.persistence.jaxb.JAXBContextFactory.createContext(
                    new Class[] { DTEDefType.class },
                    props
            );

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
            marshaller.setProperty("eclipselink.namespace-prefix-mapper", new XmlGenerator.CustomNamespacePrefixMapper3());

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            Document doc = dbf.newDocumentBuilder().newDocument();

            JAXBElement<DTEDefType> jaxbElement2 = new JAXBElement<>(
                    new QName("DTE"),
                    DTEDefType.class,
                    dte
            );

            marshaller.marshal(jaxbElement2, doc);
            // return doc

            // Sign DTE
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
        }

        //sig2
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

        //printNode(doc);

        /*Document doct = dbf.newDocumentBuilder().newDocument();
        Node importedRoot = doct.importNode(doc.getDocumentElement(), true);
        doct.appendChild(importedRoot);*""/

        /*Element setDTEElementDTE = (Element) doc.getElementsByTagName("Documento").item(0);
        setDTEElementDTE.removeAttribute("xmlns:ns0");

        Element setDTEElementDTE2 = (Element) doc.getElementsByTagName("DTE").item(0);
        setDTEElementDTE2.removeAttribute("xmlns:xsi");
        setDTEElementDTE2.removeAttribute("xmlns");*""/

        /*printNode(doct);*""/
        /*Element oldElement = (Element) doc.getElementsByTagName("DTE").item(0);

        Element newElement = doc.createElementNS("http://www.sii.cl/SiiDte", oldElement.getLocalName());
        while (oldElement.hasChildNodes()) {
            newElement.appendChild(oldElement.getFirstChild());
        }

        printNode(newElement);*""/

        /*Node parent = oldElement.getParentNode();
        parent.replaceChild(newElement, oldElement);*""/

        DocumentBuilderFactory dbf2 = DocumentBuilderFactory.newInstance();
        dbf2.setNamespaceAware(true);
        /*DTEMakers.formatKeyValueElements(doc,64,0);
        NodeList FRMT = doc.getElementsByTagNameNS("*", "FRMT");
        DTEMakers.FRMTFix(FRMT, 64,doc,0);*""/

        /*DocumentBuilder builder = dbf.newDocumentBuilder();

        Document newDoc = builder.newDocument();

        // Suppose `oldDoc` is your existing document:
        Element oldRoot = doc.getDocumentElement();

        // Rebuild it (and its descendants) with the SII namespace
        Node newRoot = renameNodeWithNamespace(newDoc, oldRoot, "http://www.sii.cl/SiiDte");
        newDoc.appendChild(newRoot);*""/

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
        EnvioDTE.SetDTE.Caratula caratula = DTEMakers.makeCaratula("19791396-6","60803000-K",0,subTotDTEList);
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
                new Class[] { EnvioDTE.class,EnvioDTE.SetDTE.class/*, SignatureType.class*""/}, // include SignatureType to isolate its namespace
                props
        );



        Marshaller marshaller2 = context3.createMarshaller();
        marshaller2.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller2.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
        marshaller2.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://www.sii.cl/SiiDte EnvioDTE_v10.xsd");
        marshaller2.setProperty("eclipselink.namespace-prefix-mapper", new CustomNamespacePrefixMapper3());

        DocumentBuilderFactory dbfnew = DocumentBuilderFactory.newInstance();
        dbfnew.setNamespaceAware(true);
        Document doc2 = dbf2.newDocumentBuilder().newDocument();


        JAXBElement<EnvioDTE> jaxbElement3 = new JAXBElement<>(
                new QName("http://www.sii.cl/SiiDte", "EnvioDTE"),
                EnvioDTE.class,
                envioDTE
        );

        marshaller2.marshal(jaxbElement3, doc2);
        //Element setDTEElement = (Element) doc2.getElementsByTagName("SetDTE").item(0);
        //setDTEElement.removeAttribute("xmlns:ns0");

        Element dteElement = (Element) doc.getElementsByTagName("DTE").item(0);
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

        /*DTEMakers.formatKeyValueElements(doc2,64,0);
        NodeList FRMT = doc2.getElementsByTagNameNS("*", "FRMT");
        DTEMakers.FRMTFix(FRMT, 64,doc2,0);

        FRMT = doc2.getElementsByTagNameNS("*", "FRMT");
        //DTEMakers.FRMTFix(FRMT, 64,doc2,1);
        DTEMakers.fixKeyValueElements(doc2,0);*""/
        SignatureType signatureEnv = DTEMakers.makeSignatureEnv(dteE2);

        // 1. Marshal signatureEnv into a DOM node
        JAXBContext sigEnvContext = JAXBContext.newInstance(SignatureType.class);
        Marshaller sigEnvMarshaller = sigEnvContext.createMarshaller();
        sigEnvMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        sigEnvMarshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");

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

        DTEMakers.formatKeyValueElements(doc2,64,0);
        NodeList FRMT = doc2.getElementsByTagNameNS("*", "FRMT");
        DTEMakers.FRMTFix(FRMT, 64,doc2,0);

        FRMT = doc2.getElementsByTagNameNS("*", "FRMT");
        //DTEMakers.FRMTFix(FRMT, 64,doc2,1);
        DTEMakers.fixKeyValueElements(doc2,0);

        DTEMakers.formatKeyValueElements(doc,64,0);
        /*NodeList *""/FRMT = doc.getElementsByTagNameNS("*", "FRMT");
        DTEMakers.FRMTFix(FRMT, 64,doc,0);

        /*Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");



        File outputFile = new File("out/DTE.xml");
        outputFile.getParentFile().mkdirs();

        Result output = new StreamResult(outputFile);
        Source input = new DOMSource(doc);
        transformer.transform(input, output);*""/

        TransformerFactory transformerf2 = TransformerFactory.newInstance();
        //transformerf2.setAttribute("indent-number", 4);
        Transformer transformer2 = transformerf2.newTransformer();
        transformer2.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer2.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
        DTEMakers.fixKeyValueElements(doc2,0);
        DTEMakers.formatKeyValueElements(doc2,64,1);

        Result output2 = new StreamResult(new File("out/EnvioDTE.xml"));
        Source input2 = new DOMSource(doc2);
        transformer2.transform(input2, output2);
    }*/
}