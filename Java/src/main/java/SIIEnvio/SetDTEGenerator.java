package SIIEnvio;

import SiiBoleta.DTEDefType;
import SiiBoleta.DTEMakers;
import SiiBoleta.XmlGenerator;
import SiiBoleta.test;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SetDTEGenerator {

    /** Given an Array of DTEs, the method returns a counter for every type of document.
     *
     * @param dtes
     * @return
     */
    public static Map<Integer, Integer> countByDocType(JSONArray dtes) {
        Map<Integer, Integer> conteo = new HashMap<>();
        for (int i = 0; i < dtes.length(); i++) {
            JSONObject dte = dtes.getJSONObject(i);
            JSONObject jsonDoc = dte.getJSONObject("documento");
            int tipo = jsonDoc.getInt("tipoDocumento");
            // Increase counter
            conteo.put(tipo, conteo.getOrDefault(tipo, 0) + 1);
        }
        return conteo;
    }

    /** Generates a SetDTE file from a Array of DTEs
     *
     * -Creates the EnvioDTE's Caratula
     * -Creates the SetDTE
     * -Converts everything to XML, using a SII validated format
     * -Returns path of the XML Generated ready to be sent to SII
     *
     * @param out XML name
     * @param DTEs
     * @return
     * @throws DatatypeConfigurationException
     * @throws JAXBException
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     * @throws TransformerException
     */
    public static String Generate(String out, JSONArray DTEs) throws DatatypeConfigurationException, JAXBException, ParserConfigurationException, IOException, SAXException, TransformerException {
        DocumentBuilderFactory dbf2 = DocumentBuilderFactory.newInstance();
        dbf2.setNamespaceAware(true);

        // Mapeo que asocia un tipo DTE a una cantidad del mismo
        // Map that asociates DTE type and their amount
        Map<Integer, Integer> conteo = countByDocType(DTEs);
        List<EnvioDTE.SetDTE.Caratula.SubTotDTE> subTotDTEList = new ArrayList<>();
        // Por cada (tipoDTE,value) crea y añade a nuestra lista subTotales DTE
        // For every (tipoDTE,value), create and add it to our subTotales DTE list
        for (Map.Entry<Integer, Integer> entry : conteo.entrySet()) {
            Integer tipoDte = entry.getKey();
            Integer value = entry.getValue();
            EnvioDTE.SetDTE.Caratula.SubTotDTE subTot = DTEMakers.makeSubTotDTE(tipoDte, value);
            subTotDTEList.add(subTot);
        }

        // Rut del enviador, debe existir una archivo texto con el rut de la misma persona que tenga
        // el certificado para validar en el SII (en target, añadir al git ignore).
        // "rutEnvia.txt" must be a txt file contaning the Rut of the person the SII-validated certificate belongs to.
        // RECOMMENDED: in target, add it to .gitignore
        String rutEnv = new String(
                test.class.getClassLoader().getResourceAsStream("rutEnvia.txt").readAllBytes(),
                StandardCharsets.UTF_8
        );
        JSONObject dte = DTEs.getJSONObject(0);
        JSONObject recep = dte.getJSONObject("receptor");
        String rutRec = recep.getString("rutReceptor");
        JSONObject emiso = dte.getJSONObject("emisor");
        String rutEmi = emiso.getString("rutEmisor");

        //String rutEnv = Files.readString(Paths.get("rutEnvia.txt"));
        EnvioDTE.SetDTE.Caratula caratula = DTEMakers.makeCaratula(rutEnv,rutEmi,rutRec,0,subTotDTEList);
        List<DTEDefType> DTEList = new ArrayList<>();
        EnvioDTE.SetDTE setDTE = DTEMakers.makeSetDTE(caratula,null,"SetDoc");

        // Create MOXy JAXBContext with both EnvioDTE and SignatureType
        Map<String, Object> props = new HashMap<>();
        props.put("eclipselink.namespace-prefix-mapper", new XmlGenerator.CustomNamespacePrefixMapper3());
        JAXBContext context3 = org.eclipse.persistence.jaxb.JAXBContextFactory.createContext(
                new Class[] { EnvioDTE.SetDTE.class/*, SignatureType.class*/}, // include SignatureType to isolate its namespace
                props
        );

        Marshaller marshaller2 = context3.createMarshaller();
        marshaller2.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller2.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
        marshaller2.setProperty("eclipselink.namespace-prefix-mapper", new XmlGenerator.CustomNamespacePrefixMapper3());

        DocumentBuilderFactory dbfnew = DocumentBuilderFactory.newInstance();
        dbfnew.setNamespaceAware(true);
        Document doc2 = dbf2.newDocumentBuilder().newDocument();

        JAXBElement<EnvioDTE.SetDTE> jaxbElement3 = new JAXBElement<>(
                new QName("", "SetDTE"),
                EnvioDTE.SetDTE.class,
                setDTE
        );

        marshaller2.marshal(jaxbElement3, doc2);

        TransformerFactory transformerf2 = TransformerFactory.newInstance();
        //transformerf2.setAttribute("indent-number", 4);
        Transformer transformer2 = transformerf2.newTransformer();
        transformer2.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer2.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "0");
        transformer2.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
        transformer2.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        //transformer2.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "1");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        transformer2.transform(new DOMSource(doc2), new StreamResult(baos));

        // Convert to string safely, removing only carriage return entities
        String xmlClean = baos.toString("ISO-8859-1"); // only remove encoded CRs
        //System.out.println(xmlClean);
        // Write back to file preserving your indentation
        File outputXml = new File("out/"+out+".xml");
        // Crear carpeta "out" si no existe
        outputXml.getParentFile().mkdirs();
        Files.write(outputXml.toPath(), xmlClean.getBytes("ISO-8859-1"));

        return "out/"+out+".xml";
    }
}
