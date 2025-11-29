package SiiBoleta;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.Marshaller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

//Imports para PDF
import SiiBoleta.SiiPDF.Utilities.generatePDFclass;

public class DTEGenerator {
    public static void main(String[] args) throws Exception {
        String ruta= Files.readString(Paths.get("jsonTemplate.json"));
        String contenido = new String(Files.readAllBytes(Paths.get(ruta)));
        Generate("TEST",new JSONObject(contenido),"Java/FoliosSII609100003422295202510171815.xml");
    }
    public static String Generate(String name, JSONObject jsonDoc, String folios) throws Exception {
        // 1. Create and populate your object
        // First take the idoc Json and fill <idDoc>
        JSONObject idoc = jsonDoc.getJSONObject("documento");
        int tipoDoc = idoc.getInt("tipoDocumento");
        int folio = idoc.getInt("folio");
        int indSer = idoc.getInt("indServicio");
        int fmaPago = idoc.getInt("fmaPago");
        String medioPago = idoc.getString("medioPago");
        MedioPagoType mdPago = MedioPagoType.fromValue(medioPago);
        DTEDefType.Documento.Encabezado.IdDoc idDoc = DTEMakers.makeIdDoc(tipoDoc,folio,indSer,fmaPago,mdPago);

        // repeat process for <Emisor> (thou right now we use a fix <Emisor>)
        JSONObject emi = jsonDoc.getJSONObject("emisor");
        String rutEm = emi.getString("rutEmisor");
        // Datos permanentes del NIC, en caso de querer usarlos pueden ponerse en jsonTemplate.json
        // String rznEm = emi.getString("rznSoc");
        // String girEm = emi.getString("giroEmis");
        // String suc = emi.getString("sucursal");
        // String dirOr = emi.getString("dirOrigen");
        // String cmnaOr = emi.getString("cmnaOrigen");
        // String ciudadOr = emi.getString("ciudadOrigen");
        DTEDefType.Documento.Encabezado.Emisor emisor = DTEMakers.makeEmisor();

        // Extract values from json to <Receptor>
        JSONObject recep = jsonDoc.getJSONObject("receptor");
        String rutRe = recep.getString("rutReceptor");
        String rznScR = recep.getString("rznSocRecep");
        String girRec = recep.getString("giroRecep");
        String contact = recep.getString("contacto");
        String dirR = recep.getString("dirRecep");
        String cmnaR = recep.getString("cmnaRecep");
        String ciudadR = recep.getString("ciudadRecep");
        DTEDefType.Documento.Encabezado.Receptor receptor = DTEMakers.makeReceptor(rutRe,rznScR,girRec,contact,dirR,cmnaR,ciudadR);

        //
        JSONObject tot = jsonDoc.getJSONObject("totales");
        int mnttotal = tot.getInt("montoTotal");
        DTEDefType.Documento.Encabezado.Totales totales = DTEMakers.makeTotales(mnttotal);
        DTEDefType.Documento.Encabezado encabezado = DTEMakers.makeEncabezado(idDoc,emisor,receptor,totales);

        JSONArray detallesJson = jsonDoc.getJSONArray("detalles");
        JSONObject detalleJson = detallesJson.getJSONObject(0);
        String IT1 = detalleJson.getString("nmbItem");
        List<DTEDefType.Documento.Detalle> detalles = DTEMakers.makeDetalles(detallesJson);

        JAXBContext cafContext = JAXBContext.newInstance(AUTORIZACION.class);
        File cafFile = new File(folios);
        AUTORIZACION autorizacion = (AUTORIZACION) cafContext.createUnmarshaller().unmarshal(cafFile);
        DTEDefType.Documento.TED.DD.CAF cafFromXml = autorizacion.getCAF();
        DTEDefType.Documento.TED.DD dd = DD.makeDD(rutEm,tipoDoc,folio,rutRe,rznScR,mnttotal,IT1,cafFromXml);

        DTEDefType.Documento.TED.FRMT frmt = FRMT.makeFRMT(dd,folios);
        DTEDefType.Documento.TED ted= TED.makeTED(dd,frmt);

        String id = name+"-"+tipoDoc+"-"+folio;
        DTEDefType.Documento documento = DTEMakers.makeDocumento(encabezado,detalles,ted,id);
        DTEDefType dte = DTEMakers.makeDTE(documento,null);
        // ...set other fields...

        // 2. Create marshaller and enable pretty-print
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

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "0");
        transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
        String out = "out/"+name+".xml";
        Result output = new StreamResult(new File(out));
        Source input = new DOMSource(doc);
        transformer.transform(input, output);


        //Aprovechamos de hacer un PDF
        InputStream xmlFile = new FileInputStream("out/DTE.xml");
        InputStream xslFile = new FileInputStream("Java/In/plantillas/plantilla_PDF_FExE.xsl");
        OutputStream pdfFile = new FileOutputStream("out/PDFMuestra.pdf");
        generatePDFclass.generatePDFWithTED(xmlFile, xslFile, pdfFile,ted);

        return out;
    }
}
