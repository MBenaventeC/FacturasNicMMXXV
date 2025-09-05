package SiiBoleta;

import SiiBoleta.DTEDefType;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import javax.xml.XMLConstants;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.GregorianCalendar;

import SiiBoleta.DTEMakers;

public class XmlGenerator {

    public static void main(String[] args) throws JAXBException, DatatypeConfigurationException {
        // 1. Create and populate your object
        DTEDefType.Documento.Encabezado.IdDoc idDoc = DTEMakers.makeIdDoc(1,2,1,MedioPagoType.EF);
        DTEDefType.Documento.Encabezado.Emisor emisor = DTEMakers.makeEmisor();
        DTEDefType.Documento.Encabezado.Receptor receptor = DTEMakers.makeReceptor("12345678-9","Empresa Ejemplo S.A.","Comercio al por mayo","Juan Pérez","Av. Siempre Viva 123, Oficina 4B Tel:+56.22333444","Providencia","Santiago");
        DTEDefType.Documento.Encabezado.Totales totales = DTEMakers.makeTotales(100000);
        DTEDefType.Documento.Encabezado encabezado = DTEMakers.makeEncabezado(idDoc,emisor,receptor,totales);
        DTEDefType.Documento.Detalle detalle = DTEMakers.makeDetalle(1,"/ServiciodeConsultoriaenTI/1/",100000.0);
        DTEDefType.Documento.TED.DD.CAF.DA.RNG rng = RNG.makeRNG(992776,1002775);
        DTEDefType.Documento.TED.DD.CAF.DA.RSAPK rsapk = RSAPK.makeRSAPK("0zi/zsPW4uJLazJscD4P1yoge4DF1nxMM3YadV+BAJV4fTHusWAtygDDHotN6mq9yBySaOvAT3Vnkx8a+R7l2w==".getBytes(),"Aw==".getBytes());
        GregorianCalendar calendar = new GregorianCalendar();
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        DTEDefType.Documento.TED.DD.CAF.DA da = DA.makeDA("60910000-1","UNIVERSIDAD DE CHILE",33,rng,xmlDate,rsapk,300);
        DTEDefType.Documento.TED.DD.CAF.FRMA frma = FRMA.makeFRMA("mtMel/DKOyqMfqUOb76bn/QDgkbNU1HUioUhOAcWtphumygxeRLlos48JdhHgZMK4IIuzd5ZGItPH3KHdaQ1uw==".getBytes());
        DTEDefType.Documento.TED.DD.CAF caf = CAF.makeCAF(da,frma);
        DTEDefType.Documento.TED.DD dd = DD.makeDD("60910000-1",33,994321,"12345678-9","Empresa Ejemplo S.A.",100000,"Servicio de Consultoría en TI",caf);
        DTEDefType.Documento.TED.FRMT frmt = FRMT.makeFRMT("S1QA/yHpklCZ8Xog2UJrV/GeFzO80pPYhwclyoHM0lFSJrwPaACEXto03H1NJlN9FiZLr5RjYFwaBrVwIwjFRA==".getBytes());
        DTEDefType.Documento.TED ted= TED.makeTED(dd,frmt);
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
        File output = new File("out/DTE.xml");

        //Para crear fragmentos:
        JAXBElement<DTEDefType> jaxbElement = new JAXBElement<>(
                new QName("DTE"), DTEDefType.class, dte);

        marshaller.marshal(jaxbElement, output);
        // or: marshaller.marshal(obj, System.out);
    }
}