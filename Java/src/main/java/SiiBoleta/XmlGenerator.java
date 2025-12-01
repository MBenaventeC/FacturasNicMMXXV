package SiiBoleta;

import org.eclipse.persistence.oxm.NamespacePrefixMapper;
import org.w3c.dom.*;

// DOM and XML parsing
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

// XML transformation (writing to file)
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class XmlGenerator {

    /**
     * prefix mapper for dtes
     */
    public static class CustomNamespacePrefixMapper3 extends NamespacePrefixMapper {
        @Override
        public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
            if ("http://www.sii.cl/SiiDte".equals(namespaceUri)) {
                return null; // default namespace
            }
            if ("http://www.w3.org/2000/09/xmldsig#".equals(namespaceUri)) {
                return null; // let it be declared locally
            }
            if ("".equals(namespaceUri)) {
                return null; // let it be declared locally
            }
            return suggestion;
        }

        @Override
        public String[] getPreDeclaredNamespaceUris() {
            return new String[] {
                    //"http://www.sii.cl/SiiDte"
            };
        }

        public boolean shouldDeclareNamespace(String namespaceUri) {
            return !"http://www.w3.org/2000/09/xmldsig#".equals(namespaceUri);
        }
    }

    /**
     * Prints a node element for debugging
     * @param node
     * @throws Exception
     */
    public static void printNode(Node node) throws Exception {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
        transformer.transform(new DOMSource(node), new StreamResult(System.out));
    }
}