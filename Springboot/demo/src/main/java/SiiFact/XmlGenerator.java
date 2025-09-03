package SiiFact;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.File;
import java.math.BigDecimal;
import java.util.GregorianCalendar;

public class XmlGenerator {
    public static void generateFacturaXML(String emisor, String receptor, String total) throws JAXBException, DatatypeConfigurationException {
        Factura factura = new Factura();
        factura.setEmisor(emisor);
        factura.setReceptor(receptor);

        GregorianCalendar calendar = new GregorianCalendar();
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        factura.setFecha(xmlDate);

        factura.setTotal(new BigDecimal(total));

        JAXBContext context = JAXBContext.newInstance(Factura.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        File output = new File("test_files/test_xml2.xml");
        //output.getParentFile().mkdirs(); // crea la carpeta si no existe
        marshaller.marshal(factura, output);
    }
}