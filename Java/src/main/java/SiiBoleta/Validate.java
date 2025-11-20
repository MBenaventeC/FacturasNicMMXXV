package SiiBoleta;

import javax.xml.crypto.*;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dom.*;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.crypto.dsig.keyinfo.*;
import java.io.FileInputStream;
import java.security.*;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.*;

/**
 * This is a simple example of validating an XML
 * Signature using the JSR 105 API. It assumes the key needed to
 * validate the signature is contained in a KeyValue KeyInfo.
 */
public class Validate {

    //
    // Synopsis: java Validate [document]
    //
    //    where "document" is the name of a file containing the XML document
    //    to be validated.
    //
    public static void main(String[] args) throws Exception {

        // Instantiate the document to be validated
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        Document doc =
            dbf.newDocumentBuilder().parse(new FileInputStream("out/EnvSigned.xml"));

        Element root = doc.getDocumentElement();
        //root.normalize();
        //normalizeLineEndings(doc);
        Element elementToSign = null;
        Node child = root.getFirstChild();
        while (child != null) {
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                elementToSign = (Element) child;
                break;
            }
            child = child.getNextSibling();
        }

        if (elementToSign == null) {
            throw new Exception("No child element found under root element!");
        }

        // Get the ID attribute
        Attr idAttr = elementToSign.getAttributeNode("ID");
        if (idAttr == null) {
            throw new Exception("element has no ID attribute!");
        }

        // Register the ID attribute properly (safe way)
        elementToSign.setIdAttributeNode(idAttr, true);

        // Find Signature element
        NodeList nl =
            doc.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");
        if (nl.getLength() == 0) {
            throw new Exception("Cannot find Signature element");
        }

        // Create a DOM XMLSignatureFactory that will be used to unmarshal the
        // document containing the XMLSignature
        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

        // Create a DOMValidateContext and specify a KeyValue KeySelector
        // and document context
        DOMValidateContext valContext = new DOMValidateContext
            (new KeyValueKeySelector(), nl.item(1));

        valContext.setProperty("org.jcp.xml.dsig.secureValidation", Boolean.FALSE);

        // unmarshal the XMLSignature
        XMLSignature signature = fac.unmarshalXMLSignature(valContext);

        // Validate the XMLSignature (generated above)
        boolean coreValidity = signature.validate(valContext);

        // Check core validation status
        if (coreValidity == false) {
            System.err.println("Signature failed core validation");
            boolean sv = signature.getSignatureValue().validate(valContext);
            System.out.println("signature validation status: " + sv);
            // check the validation status of each Reference
            Iterator i = signature.getSignedInfo().getReferences().iterator();
            for (int j=0; i.hasNext(); j++) {
                boolean refValid =
                    ((Reference) i.next()).validate(valContext);
                System.out.println("ref["+j+"] validity status: " + refValid);
            }
        } else {
            System.out.println("Signature passed core validation");
        }
    }

    /**
     * KeySelector which retrieves the public key out of the
     * KeyValue element and returns it.
     * NOTE: If the key algorithm doesn't match signature algorithm,
     * then the public key will be ignored.
     */
    private static class KeyValueKeySelector extends KeySelector {
        public KeySelectorResult select(KeyInfo keyInfo,
                                        KeySelector.Purpose purpose,
                                        AlgorithmMethod method,
                                        XMLCryptoContext context)
            throws KeySelectorException {
            if (keyInfo == null) {
                throw new KeySelectorException("Null KeyInfo object!");
            }
            SignatureMethod sm = (SignatureMethod) method;
            List list = keyInfo.getContent();

            for (int i = 0; i < list.size(); i++) {
                XMLStructure xmlStructure = (XMLStructure) list.get(i);
                if (xmlStructure instanceof KeyValue) {
                    PublicKey pk = null;
                    try {
                        pk = ((KeyValue)xmlStructure).getPublicKey();
                    } catch (KeyException ke) {
                        throw new KeySelectorException(ke);
                    }
                    // make sure algorithm is compatible with method
                    if (algEquals(sm.getAlgorithm(), pk.getAlgorithm())) {
                        return new SimpleKeySelectorResult(pk);
                    }
                }
            }
            throw new KeySelectorException("No KeyValue element found!");
        }

        //@@@FIXME: this should also work for key types other than DSA/RSA
        static boolean algEquals(String algURI, String algName) {
            if (algName.equalsIgnoreCase("DSA") &&
                algURI.equalsIgnoreCase(SignatureMethod.DSA_SHA1)) {
                return true;
            } else if (algName.equalsIgnoreCase("RSA") &&
                       algURI.equalsIgnoreCase(SignatureMethod.RSA_SHA1)) {
                return true;
            } else {
                return false;
            }
        }
    }

    private static class SimpleKeySelectorResult implements KeySelectorResult {
        private PublicKey pk;
        SimpleKeySelectorResult(PublicKey pk) {
            this.pk = pk;
        }

        public Key getKey() { return pk; }
    }


    public static void validateDTEs (JSONArray dtes) throws Exception {
        for (int i = 0; i < dtes.length(); i++) {
            boolean validateDTE = validateDTE(dtes.getJSONObject(i));
            if (!validateDTE) {
                throw new Exception("Uno o más DTE no cumple con las limitaciones exigidas en el input");
            }
        }

    }
    public static boolean validateDTE(JSONObject dte) {
        JSONObject receptor = dte.getJSONObject("receptor");

        // Razon social del receptor debe ser máximo 40 caracteres
        String rsr = receptor.getString("rznSocRecep");

        if (rsr.length() > 40) {
            return false;
        }

        // Descripcion del item en detalles con maximo 40 caracteres
        JSONArray detallesJson = dte.getJSONArray("detalles");

        for (int i = 0; i < detallesJson.length(); i++) {
            JSONObject detalle = detallesJson.getJSONObject(i);
            String nmbItem = detalle.getString("nmbItem");
            if (nmbItem.length() > 40) {
                return false;
            }
        }

        return true;
    }
}
