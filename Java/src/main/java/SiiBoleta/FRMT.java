package SiiBoleta;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.namespace.QName;
import java.io.File;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import java.security.*;
import java.util.Arrays;
import java.util.Base64;


public class FRMT {

    public static DTEDefType.Documento.TED.FRMT makeFRMT2(byte[] value) {
        DTEDefType.Documento.TED.FRMT frmt = new DTEDefType.Documento.TED.FRMT();
        frmt.setValue(value);
        frmt.setAlgoritmo("SHA1withRSA");
        return frmt;
    }

    public static DTEDefType.Documento.TED.FRMT makeFRMT(DTEDefType.Documento.TED.DD DD) throws JAXBException, UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {

        DTEDefType.Documento.TED.FRMT frmt = new DTEDefType.Documento.TED.FRMT();

        JAXBContext context = JAXBContext.newInstance(DTEDefType.Documento.TED.DD.class);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

        //Para crear fragmentos:
        JAXBElement<DTEDefType.Documento.TED.DD> jaxbElement = new JAXBElement<>(
                new QName("DD"), DTEDefType.Documento.TED.DD.class, DD);

        StringWriter writer = new StringWriter();
        marshaller.marshal(jaxbElement, writer);
        String xmlString = writer.toString();
        String[] xmlStringA= xmlString.split("\n");
        String DDs = "";
        for (int i = 0; i < xmlStringA.length; i++) {
            xmlStringA[i] = xmlStringA[i].replaceAll("\n", "").replaceAll("\t", "").trim();
            DDs+=xmlStringA[i];
        }
        System.out.println(DDs);
        System.out.println(DD.getDDbracket());//Es más eficiente pero actualmente presenta errores, ver comparación en el print

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();

        byte[] data = DDs.getBytes(StandardCharsets.ISO_8859_1);
        Signature signature = Signature.getInstance("SHA1withRSA");
        signature.initSign(keyPair.getPrivate());
        signature.update(data);

        byte[] signedBytes = signature.sign();
        //System.out.println(Arrays.toString(signedBytes));

        frmt.setValue(signedBytes);
        frmt.setAlgoritmo("SHA1withRSA");

        return frmt;
    }

    /*public static void main(String[] args) throws JAXBException, DatatypeConfigurationException, UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        JAXBContext cafContext = JAXBContext.newInstance(AUTORIZACION.class);
        File cafFile = new File("Java/Autorizacion.xml");
        AUTORIZACION autorizacion = (AUTORIZACION) cafContext.createUnmarshaller().unmarshal(cafFile);
        DTEDefType.Documento.TED.DD.CAF cafFromXml = autorizacion.getCAF();
        String RSASK = autorizacion.getRSASK();
        System.out.println(RSASK);
        DTEDefType.Documento.TED.DD dd = DD.makeDD("60910000-1",33,994321,"12345678-9","Empresa Ejemplo S.A.",100000,"Servicio de Consultoría en TI",cafFromXml);
        makeFRMT2(dd);
    }*/
}
