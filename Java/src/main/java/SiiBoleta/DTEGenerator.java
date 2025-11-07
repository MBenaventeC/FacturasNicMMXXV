package SiiBoleta;

import SiiSignature.SignatureType;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.Marshaller;
import org.eclipse.persistence.oxm.NamespacePrefixMapper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.*;

public class DTEGenerator {

    public static void main(String[] args) throws Exception {
        // 1. Create and populate your object
        DTEDefType.Documento.Encabezado.IdDoc idDoc = DTEMakers.makeIdDoc(1,2,1,MedioPagoType.EF);
        DTEDefType.Documento.Encabezado.Emisor emisor = DTEMakers.makeEmisor();
        DTEDefType.Documento.Encabezado.Receptor receptor = DTEMakers.makeReceptor("12345678-9","H&M","Comercio al por mayo","Juan Pérez","Av. Siempre Viva 123, Oficina 4B Tel:+56.22333444","Providencia","Santiago");
        DTEDefType.Documento.Encabezado.Totales totales = DTEMakers.makeTotales(100000);
        DTEDefType.Documento.Encabezado encabezado = DTEMakers.makeEncabezado(idDoc,emisor,receptor,totales);
        DTEDefType.Documento.Detalle detalle = DTEMakers.makeDetalle(1,"ServiciodeConsultoriaenTI/1/",100000.0);
        GregorianCalendar calendar = new GregorianCalendar(2025, Calendar.APRIL, 9);

        JAXBContext cafContext = JAXBContext.newInstance(AUTORIZACION.class);
        File cafFile = new File("Java/FoliosSII609100003410743962025729929.xml");
        AUTORIZACION autorizacion = (AUTORIZACION) cafContext.createUnmarshaller().unmarshal(cafFile);
        DTEDefType.Documento.TED.DD.CAF cafFromXml = autorizacion.getCAF();
        DTEDefType.Documento.TED.DD dd = DD.makeDD("60910000-1",33,994321,"12345678-9","Hola",100000,"Servicio de Consultoría en TI",cafFromXml);



        //DTEDefType.Documento.TED.FRMT frmt = FRMT.makeFRMT("S1QA/yHpklCZ8Xog2UJrV/GeFzO80pPYhwclyoHM0lFSJrwPaACEXto03H1NJlN9FiZLr5RjYFwaBrVwIwjFRA==".getBytes());
        DTEDefType.Documento.TED.FRMT frmt = FRMT.makeFRMT(dd);
        DTEDefType.Documento.TED ted= TED.makeTED(dd,frmt);

        // Se genera el codigo de barras
        //Image barcode = TED.makeBarcode(ted);

        DTEDefType.Documento documento = DTEMakers.makeDocumento(encabezado,detalle,ted,"DTE-34-994321");
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

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");

        Result output = new StreamResult(new File("out/DTE.xml"));
        Source input = new DOMSource(doc);
        transformer.transform(input, output);
    }
}
