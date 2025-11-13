// package SiiEnvio.util;

// import cl.sii.siiDte.GetToken;
// import jakarta.xml.bind.JAXBContext;
// import jakarta.xml.bind.Marshaller;
// import org.w3c.dom.Document;
// import javax.xml.crypto.dsig.*;
// import javax.xml.crypto.dsig.dom.DOMSignContext;
// import javax.xml.crypto.dsig.keyinfo.*;
// import javax.xml.parsers.DocumentBuilderFactory;
// import javax.xml.transform.*;
// import javax.xml.transform.dom.DOMSource;
// import javax.xml.transform.stream.StreamResult;
// import java.io.StringWriter;
// import java.security.PrivateKey;
// import java.security.cert.X509Certificate;
// import java.util.Collections;
// import SiiEnvio.generatedClasses.cl.sii.siiDte.GetToken;

// public final class GetTokenSigner {
//     private GetTokenSigner(){}

//     public static String sign(GetToken getToken, PrivateKey key, X509Certificate cert) throws Exception {
//         JAXBContext jc = JAXBContext.newInstance(GetToken.class);
//         Marshaller m = jc.createMarshaller();
//         m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
//         Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
//         m.marshal(getToken, doc);

//         XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");
//         Reference ref = fac.newReference("", fac.newDigestMethod(DigestMethod.SHA1, null),
//                 Collections.singletonList(fac.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null)),
//                 null, null);
//         SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE, (C14NMethodParameterSpec)null),
//                 fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null), Collections.singletonList(ref));

//         KeyInfoFactory kif = fac.getKeyInfoFactory();
//         X509Data xd = kif.newX509Data(Collections.singletonList(cert));
//         KeyInfo ki = kif.newKeyInfo(Collections.singletonList(xd));

//         DOMSignContext dsc = new DOMSignContext(key, doc.getDocumentElement());
//         XMLSignature signature = fac.newXMLSignature(si, ki);
//         signature.sign(dsc);

//         Transformer t = TransformerFactory.newInstance().newTransformer();
//         StringWriter sw = new StringWriter();
//         t.transform(new DOMSource(doc), new StreamResult(sw));
//         return sw.toString();
//     }
// }
