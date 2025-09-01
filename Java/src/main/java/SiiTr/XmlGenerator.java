package SiiTr;

import SiiTr.ReciboDefType;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.GregorianCalendar;

public class XmlGenerator {
    public ReciboDefType.DocumentoRecibo makeDoc(BigInteger folio,String rutRecept,BigInteger mntTotal, String rutFirma, String id) throws DatatypeConfigurationException {
        ReciboDefType.DocumentoRecibo doc = new ReciboDefType.DocumentoRecibo();
        doc.setTipoDoc(BigInteger.valueOf(33));
        doc.setFolio(folio);//
        GregorianCalendar calendar = new GregorianCalendar();
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        doc.setFchEmis(xmlDate);//
        doc.setRUTEmisor("60910000-1");//inventado
        doc.setRUTRecep(rutRecept);//
        doc.setMntTotal(mntTotal);//
        doc.setRecinto("DCC Uchile");
        doc.setRutFirma(rutFirma);//
        doc.setDeclaracion("El acuse de recibo que se declara en este acto, de acuerdo a lo dispuesto en la letra b) del Art. 4, y la letra c) del Art. 5 de la Ley 19.983, acredita que la entrega de mercaderias o servicio(s) prestado(s) ha(n) sido recibido(s).");
        doc.setTmstFirmaRecibo();//
        doc.setID(id);//
        return doc;
    }
    public ReciboDefType makeDocF(ReciboDefType.DocumentoRecibo doc, SignatureType signature) throws DatatypeConfigurationException {
        ReciboDefType obj = new ReciboDefType();
        obj.setDocumentoRecibo(doc);
        obj.setSignature(signature);
        obj.setVersion(new BigDecimal(1));
        return obj;
    }
    public static void main(String[] args) throws JAXBException, DatatypeConfigurationException {
        // 1. Create and populate your object
        ReciboDefType.DocumentoRecibo Doc = new ReciboDefType.DocumentoRecibo();
        set
        ReciboDefType obj = new ReciboDefType();
        // ...set other fields...

        // 2. Initialize JAXBContext
        JAXBContext context = JAXBContext.newInstance(ReciboDefType.class);

        // 3. Create marshaller and enable pretty-print
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        // 4. Marshal to a file or System.out
        new File("src/out").mkdirs();
        File output = new File("src/out/FacturaOutXD.xml");
        marshaller.marshal(obj, output);
        // or: marshaller.marshal(obj, System.out);
    }
}