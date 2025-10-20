package SiiBoleta;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.Marshaller;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

import javax.xml.namespace.QName;
import java.io.*;
import java.nio.charset.StandardCharsets;

import java.security.*;


public class FRMT {

    public static PrivateKey extractPrivateKey(String pem) throws Exception {
        PEMParser parser = new PEMParser(new StringReader(pem));
        Object obj = parser.readObject();
        parser.close();

        PEMKeyPair keyPair = (PEMKeyPair) obj;
        return new JcaPEMKeyConverter().getPrivateKey(keyPair.getPrivateKeyInfo());
    }



    public static DTEDefType.Documento.TED.FRMT makeFRMT2(byte[] value) {
        DTEDefType.Documento.TED.FRMT frmt = new DTEDefType.Documento.TED.FRMT();
        frmt.setValue(value);
        frmt.setAlgoritmo("SHA1withRSA");
        return frmt;
    }

    public static DTEDefType.Documento.TED.FRMT makeFRMT(DTEDefType.Documento.TED.DD DD) throws Exception {

        DTEDefType.Documento.TED.FRMT frmt = new DTEDefType.Documento.TED.FRMT();

        JAXBContext context = JAXBContext.newInstance(DTEDefType.Documento.TED.DD.class);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

        //Para crear fragmentos:
        JAXBElement<DTEDefType.Documento.TED.DD> jaxbElement = new JAXBElement<>(
                new QName("DD"), DTEDefType.Documento.TED.DD.class, DD);

        StringWriter writer = new StringWriter();
        marshaller.marshal(jaxbElement, writer);
        String xmlString = writer.toString();

        xmlString = xmlString.replaceAll(">\\s+<", "><");

        // Para generar clave de juguete
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();

        //Para llave de verdad
        JAXBContext cafContext = JAXBContext.newInstance(AUTORIZACION.class);
        File cafFile = new File("Java/FoliosSII609100003422295202510171815.xml");
        AUTORIZACION autorizacion = (AUTORIZACION) cafContext.createUnmarshaller().unmarshal(cafFile);
        DTEDefType.Documento.TED.DD.CAF cafFromXml = autorizacion.getCAF();
        String pemPrivateKey = autorizacion.getRSASK();

        PrivateKey privateKey = extractPrivateKey(pemPrivateKey);

        byte[] data = xmlString.getBytes(StandardCharsets.ISO_8859_1);
        Signature signature = Signature.getInstance("SHA1withRSA");
        signature.initSign(privateKey);
        signature.update(data);
        byte[] signedBytes = signature.sign();

        frmt.setValue(signedBytes);
        frmt.setAlgoritmo("SHA1withRSA");

        return frmt;
    }

    /*public static void main(String[] args) throws JAXBException, DatatypeConfigurationException, IOException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        JAXBContext cafContext = JAXBContext.newInstance(AUTORIZACION.class);
        File cafFile = new File("Java/Autorizacion.xml");
        AUTORIZACION autorizacion = (AUTORIZACION) cafContext.createUnmarshaller().unmarshal(cafFile);
        DTEDefType.Documento.TED.DD.CAF cafFromXml = autorizacion.getCAF();
        String RSASK = autorizacion.getRSASK();
        System.out.println(RSASK);
        DTEDefType.Documento.TED.DD dd = DD.makeDD("60910000-1",33,994321,"12345678-9","Empresa Ejemplo S.A.",100000,"Servicio de Consultoría en TI",cafFromXml);
        makeFRMT(dd);
    }*/
}
