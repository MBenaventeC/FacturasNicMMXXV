package SiiBoletaTest;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BarcodePDF417;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import javax.xml.namespace.QName;
import java.io.FileNotFoundException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class TED {

    public static DTEDefType.Documento.TED makeTED(DTEDefType.Documento.TED.DD dd,
                                                   DTEDefType.Documento.TED.FRMT frmt) {

        DTEDefType.Documento.TED ted= new DTEDefType.Documento.TED();

        ted.setDD(dd);
        ted.setFRMT(frmt);
        ted.setVersion("1.0");

        return ted;
    }

    public static String serializeTedString(DTEDefType.Documento.TED ted) throws JAXBException, DocumentException, FileNotFoundException, UnsupportedEncodingException {
        JAXBContext context = JAXBContext.newInstance(DTEDefType.Documento.TED.class);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

        QName qName = new QName("TED");
        JAXBElement<DTEDefType.Documento.TED> jaxbElement =
                new JAXBElement<>(qName, DTEDefType.Documento.TED.class, ted);

        StringWriter sw = new StringWriter();
        marshaller.marshal(jaxbElement, sw);

        // Verificar si es necesario quitar el primer contenido de información respecto a xml del string
        String tedString = sw.toString()
                .replace("&#8220;", "&quot;")
                .replace("&#8216;", "&apos;");
        return tedString.replace("&#8220;", "&quot;").replace("&#8216;", "&apos;");
    }


    //Se le cambio el tipo de retorno de la función de Image a Barcode417
    public static BarcodePDF417 makeBarcode(DTEDefType.Documento.TED ted) throws JAXBException, DocumentException, FileNotFoundException, UnsupportedEncodingException {
        JAXBContext context = JAXBContext.newInstance(DTEDefType.Documento.TED.class);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

        QName qName = new QName("TED");
        JAXBElement<DTEDefType.Documento.TED> jaxbElement =
                new JAXBElement<>(qName, DTEDefType.Documento.TED.class, ted);

        StringWriter sw = new StringWriter();
        marshaller.marshal(jaxbElement, sw);

        // Verificar si es necesario quitar el primer contenido de información respecto a xml del string
        String tedString = sw.toString()
                .replace("&#8220;", "&quot;")
                .replace("&#8216;", "&apos;");

        //System.out.print(tedString);

        BarcodePDF417 pdf417 = new BarcodePDF417();
        pdf417.setText(new String(tedString.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.ISO_8859_1)); // Ojo con charset
        return pdf417; // .getImage();
    }
}
