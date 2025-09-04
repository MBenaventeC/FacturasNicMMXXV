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
        DTEDefType.Documento.Encabezado.Emisor obj = DTEMakers.makeEmisor();
        // ...set other fields...

        // 2. Initialize JAXBContext
        JAXBContext context = JAXBContext.newInstance(DTEDefType.Documento.Encabezado.Emisor.class);

        // 3. Create marshaller and enable pretty-print
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        // 4. Marshal to a file or System.out
        new File("src/out").mkdirs();
        File output = new File("DTE.xml");

        //Para crear fragmentos:
        JAXBElement<DTEDefType.Documento.Encabezado.Emisor> jaxbElement = new JAXBElement<>(
                new QName("Emisor"), DTEDefType.Documento.Encabezado.Emisor.class, obj);

        marshaller.marshal(jaxbElement, output);
        // or: marshaller.marshal(obj, System.out);
    }
}