package SiiBoleta;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SignatureException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class XmlGenerator {

    public static void main(String[] args) throws JAXBException, DatatypeConfigurationException, UnsupportedEncodingException, NoSuchAlgorithmException, SignatureException, InvalidKeyException, DocumentException, FileNotFoundException {
        // 1. Create and populate your object
        DTEDefType.Documento.Encabezado.IdDoc idDoc = DTEMakers.makeIdDoc(1,2,1,MedioPagoType.EF);
        DTEDefType.Documento.Encabezado.Emisor emisor = DTEMakers.makeEmisor();
        DTEDefType.Documento.Encabezado.Receptor receptor = DTEMakers.makeReceptor("12345678-9","H&M","Comercio al por mayo","Juan Pérez","Av. Siempre Viva 123, Oficina 4B Tel:+56.22333444","Providencia","Santiago");
        DTEDefType.Documento.Encabezado.Totales totales = DTEMakers.makeTotales(100000);
        DTEDefType.Documento.Encabezado encabezado = DTEMakers.makeEncabezado(idDoc,emisor,receptor,totales);
        DTEDefType.Documento.Detalle detalle = DTEMakers.makeDetalle(1,"ServiciodeConsultoriaenTI/1/",100000.0);
        GregorianCalendar calendar = new GregorianCalendar(2025, Calendar.APRIL, 9);
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        xmlDate.setTimezone( DatatypeConstants.FIELD_UNDEFINED );

        JAXBContext cafContext = JAXBContext.newInstance(AUTORIZACION.class);
        File cafFile = new File("Java/Autorizacion.xml");
        AUTORIZACION autorizacion = (AUTORIZACION) cafContext.createUnmarshaller().unmarshal(cafFile);
        DTEDefType.Documento.TED.DD.CAF cafFromXml = autorizacion.getCAF();
        DTEDefType.Documento.TED.DD dd = DD.makeDD("60910000-1",33,994321,"12345678-9","Hola",100000,"Servicio de Consultoría en TI",cafFromXml);



        //DTEDefType.Documento.TED.FRMT frmt = FRMT.makeFRMT("S1QA/yHpklCZ8Xog2UJrV/GeFzO80pPYhwclyoHM0lFSJrwPaACEXto03H1NJlN9FiZLr5RjYFwaBrVwIwjFRA==".getBytes());
        DTEDefType.Documento.TED.FRMT frmt = FRMT.makeFRMT(dd);
        DTEDefType.Documento.TED ted= TED.makeTED(dd,frmt);

        // Se genera el codigo de barras
        //Image barcode = TED.makeBarcode(ted);

        /*

        DTEDefType.Documento documento = DTEMakers.makeDocumento(encabezado,detalle,ted,"DTE-33-994321");
        SignatureType signature = DTEMakers.makeSignature(documento);
        DTEDefType dte = DTEMakers.makeDTE(documento,signature);
        // ...set other fields...

        // 2. Initialize JAXBContext
        JAXBContext context = JAXBContext.newInstance(DTEDefType.class);

        // 3. Create marshaller and enable pretty-print
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");

        // 4. Marshal to a file or System.out
        new File("out").mkdirs();
        File output = new File("out/DTE4.xml");

        //Para crear fragmentos:
        JAXBElement<DTEDefType> jaxbElement = new JAXBElement<>(
                new QName("DTE"), DTEDefType.class, dte);

        marshaller.marshal(jaxbElement, output);
        // or: marshaller.marshal(obj, System.out);

         */
    }
}