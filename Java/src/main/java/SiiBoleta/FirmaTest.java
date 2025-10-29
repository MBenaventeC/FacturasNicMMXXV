package SiiBoleta;

import SIIEnvio.EnvioDTE;
import SiiSignature.SignatureType;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.Marshaller;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.*;

public class FirmaTest {
    public static void main(String[] args) throws Exception {

        File xmlFile = new File(""); // <----------

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        Document doc = dbf.newDocumentBuilder().parse(xmlFile);

        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

        String uri = "";

        String pfxPath = "C:/ruta/a/tu_certificado.pfx"; // <----------
        String pfxPassword = "tu_contraseña"; // <----------

        KeyStore ks = KeyStore.getInstance("PKCS12");

        try (FileInputStream fis = new FileInputStream(pfxPath)) {
            ks.load(fis, pfxPassword.toCharArray());
        }

        String alias = ks.aliases().nextElement();

        PrivateKey pKey = (PrivateKey) ks.getKey(alias, pfxPassword.toCharArray());

        X509Certificate cert = (X509Certificate) ks.getCertificate(alias);

        Reference ref = fac.newReference(uri, fac.newDigestMethod(
                DigestMethod.SHA1,null), Collections.singletonList(fac
                .newTransform(Transform.ENVELOPED,
                        (TransformParameterSpec) null)), null, null);

        String method = SignatureMethod.RSA_SHA1;

        if ("DSA".equals(cert.getPublicKey().getAlgorithm())) method = SignatureMethod.DSA_SHA1;
        else if ("HMAC".equals(cert.getPublicKey().getAlgorithm())) method = SignatureMethod.HMAC_SHA1;

        SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod((
                CanonicalizationMethod.INCLUSIVE),
                (C14NMethodParameterSpec) null), fac.newSignatureMethod(method,
                        null), Collections.singletonList(ref));

        KeyInfoFactory kif = fac.getKeyInfoFactory();
        KeyValue kv = kif.newKeyValue(cert.getPublicKey());

        List<XMLStructure> kidata = new ArrayList<XMLStructure>();
        kidata.add(kv);
        kidata.add(kif.newX509Data(Collections.singletonList(cert)));
        KeyInfo ki = kif.newKeyInfo(kidata);

        DOMSignContext dsc = new DOMSignContext(pKey, doc);

        XMLSignature signature = fac.newXMLSignature(si, ki);

        signature.sign(dsc);
    }
}
