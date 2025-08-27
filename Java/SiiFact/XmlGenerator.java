import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import java.io.File;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.GregorianCalendar;

import java.math.BigDecimal;

public class XmlGenerator {
    public static void main(String[] args) throws JAXBException, DatatypeConfigurationException {
        // 1. Create and populate your object
        SiiFact.Factura obj = new SiiFact.Factura();
        obj.setEmisor("NIC");
        obj.setReceptor("SII");
        GregorianCalendar calendar = new GregorianCalendar();
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        obj.setFecha(xmlDate);
        obj.setTotal(new BigDecimal("1234.56"));
        // ...set other fields...

        // 2. Initialize JAXBContext
        JAXBContext context = JAXBContext.newInstance(SiiFact.Factura.class);

        // 3. Create marshaller and enable pretty-print
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        // 4. Marshal to a file or System.out
        File output = new File("Java/out/FacturaOutXD.xml");
        marshaller.marshal(obj, output);
        // or: marshaller.marshal(obj, System.out);
    }
}