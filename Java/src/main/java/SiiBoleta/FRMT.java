package SiiBoleta;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.Marshaller;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

import javax.xml.namespace.QName;
import java.io.*;

import java.security.*;


public class FRMT {

    public static PrivateKey extractPrivateKey(String pem) throws Exception {
        PEMParser parser = new PEMParser(new StringReader(pem));
        Object obj = parser.readObject();
        parser.close();

        PEMKeyPair keyPair = (PEMKeyPair) obj;
        return new JcaPEMKeyConverter().getPrivateKey(keyPair.getPrivateKeyInfo());
    }


    public static DTEDefType.Documento.TED.FRMT makeFRMT(DTEDefType.Documento.TED.DD DD,String folios) throws Exception {

        DTEDefType.Documento.TED.FRMT frmt = new DTEDefType.Documento.TED.FRMT();

        // Marshal DD to a string fragment, no XML declaration, ISO-8859-1 encoding
        JAXBContext context = JAXBContext.newInstance(DTEDefType.Documento.TED.DD.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

        JAXBElement<DTEDefType.Documento.TED.DD> jaxbElement = new JAXBElement<>(
                new QName("DD"), DTEDefType.Documento.TED.DD.class, DD);

        StringWriter writer = new StringWriter();
        marshaller.marshal(jaxbElement, writer);
        String xmlString = writer.toString();

        // Remove unnecessary whitespace between tags
        xmlString = xmlString.replaceAll(">\\s+<", "><");

        // Load CAF and private key
        //This method can be modified to receive autorizacion as input when used in dteGenerator
        JAXBContext cafContext = JAXBContext.newInstance(AUTORIZACION.class);
        File cafFile = new File(folios);
        AUTORIZACION autorizacion = (AUTORIZACION) cafContext.createUnmarshaller().unmarshal(cafFile);

        String pemPrivateKey = autorizacion.getRSASK();
        PrivateKey privateKey = extractPrivateKey(pemPrivateKey);

        // Sign the canonicalized DD XML
        Signature signature = Signature.getInstance("SHA1withRSA");
        signature.initSign(privateKey);
        //System.out.println(xmlString);
        signature.update(xmlString.getBytes("ISO-8859-1")); // must match encoding
        byte[] signedBytes = signature.sign();

        // Set raw bytes in FRMT
        frmt.setValue(signedBytes);
        frmt.setAlgoritmo("SHA1withRSA");

        return frmt;
    }
}