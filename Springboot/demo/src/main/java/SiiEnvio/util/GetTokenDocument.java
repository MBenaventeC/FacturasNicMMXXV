package SiiEnvio.util;

import SiiEnvio.generatedClasses.cl.sii.siiDte.GetToken;
import jakarta.xml.bind.*;
import org.w3c.dom.Document;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.*;
import javax.xml.crypto.dsig.spec.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.HashMap;

//Nuevo para reemplazo XMLOptions
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.xml.namespace.QName;

/**
 * Wrapper para GetToken que replica la funcionalidad de GetTokenDocument de XMLBeans
 */
public class GetTokenDocument {
    
    private GetToken getToken;
    private PrivateKey privateKey;
    private X509Certificate certificate;
    private final Map<String,String> namespaces = new HashMap<>();
    
    private GetTokenDocument() {
        this.getToken = new GetToken();
    }
    
    /**
     * Factory para crear nueva instancia (símil de XMLBeans)
     */
    public static GetTokenDocument newInstance() {
        return new GetTokenDocument();
    }
    
    /**
     * Agrega elemento getToken y retorna builder (símil de XMLBeans)
     */
    public ItemBuilder addNewGetToken() {
        return new ItemBuilder(this);
    }
    
    /**
     * Firma el documento con clave privada y certificado
     */
    public void sign(PrivateKey pKey, X509Certificate cert) throws Exception {
        this.privateKey = pKey;
        this.certificate = cert;
        namespaces.clear();
        namespaces.put("", "http://www.sii.cl/SiiDte");
    }
    
    /**
     * Retorna el XML como String
     */
    public String xmlText() throws Exception {
        if (privateKey != null && certificate != null) {
            return signAndGetXml();
        } else {
            return marshalToXml();
        }
    }
    
    /**
     * Obtiene el objeto GetToken interno
     */
    public GetToken getGetToken() {
        return getToken;
    }
    
    // Métodos privados auxiliares
    
    private String marshalToXml() throws Exception {
        JAXBContext context = JAXBContext.newInstance(GetToken.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        
        StringWriter sw = new StringWriter();
        marshaller.marshal(getToken, sw);
        return sw.toString();
    }
    
    private String signAndGetXml() throws Exception {
        // 1. Marshal GetToken a DOM
        JAXBContext context = JAXBContext.newInstance(GetToken.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        Document doc = dbf.newDocumentBuilder().newDocument();
        
        marshaller.marshal(getToken, doc);
        QName q = new QName(namespaces.getOrDefault("", "http://www.sii.cl/SiiDte"), "getToken");
        jakarta.xml.bind.JAXBElement<GetToken> jaxb = new jakarta.xml.bind.JAXBElement<>(q, GetToken.class, getToken);
        marshaller.marshal(jaxb, doc);

        
        // 2. Crear firma XML
        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");
        
        Reference ref = fac.newReference(
            "",
            fac.newDigestMethod(DigestMethod.SHA1, null),
            Collections.singletonList(
                fac.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null)
            ),
            null,
            null
        );
        
        SignedInfo si = fac.newSignedInfo(
            fac.newCanonicalizationMethod(
                CanonicalizationMethod.INCLUSIVE,
                (C14NMethodParameterSpec) null
            ),
            fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
            Collections.singletonList(ref)
        );
        
        KeyInfoFactory kif = fac.getKeyInfoFactory();
        X509Data xd = kif.newX509Data(Collections.singletonList(certificate));
        KeyInfo ki = kif.newKeyInfo(Collections.singletonList(xd));
        
        // 3. Firmar
        DOMSignContext dsc = new DOMSignContext(privateKey, doc.getDocumentElement());
        XMLSignature signature = fac.newXMLSignature(si, ki);
        signature.sign(dsc);
        
        // 4. Convertir a String
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer trans = tf.newTransformer();
        trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        
        StringWriter sw = new StringWriter();
        trans.transform(new DOMSource(doc), new StreamResult(sw));
        
        return sw.toString();
    }
    
    /**
     * Builder interno para construir Item (símil de XMLBeans)
     */
    public static class ItemBuilder {
        private final GetTokenDocument parent;
        private final GetToken.Item item;
        
        ItemBuilder(GetTokenDocument parent) {
            this.parent = parent;
            this.item = new GetToken.Item();
            parent.getToken.setItem(item);
        }
        
        /**
         * Agrega nuevo Item y retorna this para encadenar
         */
        public ItemBuilder addNewItem() {
            return this;
        }
        
        /**
         * Establece la semilla
         */
        public void setSemilla(String semilla) {
            item.setSemilla(semilla);
        }
    }
}
