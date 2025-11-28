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

    public static class CustomNamespacePrefixMapper extends NamespacePrefixMapper {
        @Override
        public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
            if ("http://www.sii.cl/SiiDte".equals(namespaceUri)) {
                return ""; // default namespace
            }
            if ("http://www.w3.org/2001/XMLSchema-instance".equals(namespaceUri)) {
                return "xsi";
            }
            if ("http://www.w3.org/2000/09/xmldsig#".equals(namespaceUri)) {
                return null; // let it be declared locally
            }
            return suggestion;
        }

        @Override
        public String[] getPreDeclaredNamespaceUris() {
            return new String[] {
                    "http://www.sii.cl/SiiDte",
                    "http://www.w3.org/2001/XMLSchema-instance"
            };
        }

        public boolean shouldDeclareNamespace(String namespaceUri) {
            return !"http://www.w3.org/2000/09/xmldsig#".equals(namespaceUri);
        }
    }
    public static class CustomNamespacePrefixMapper2 extends NamespacePrefixMapper {
        @Override
        public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
            if ("http://www.sii.cl/SiiDte".equals(namespaceUri)) {
                return ""; // default namespace
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
                    "http://www.sii.cl/SiiDte"
            };
        }

        public boolean shouldDeclareNamespace(String namespaceUri) {
            return !"http://www.w3.org/2000/09/xmldsig#".equals(namespaceUri);
        }
    }

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

    public static void printNode(Node node) throws Exception {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
        transformer.transform(new DOMSource(node), new StreamResult(System.out));
    }

    public static Node renameNodeWithNamespace(Document doc, Node node, String newNamespaceURI) {
        return renameNodeWithNamespace(doc, node, newNamespaceURI, false);
    }

    private static Node renameNodeWithNamespace(Document doc, Node node, String newNamespaceURI, boolean insideSignature) {
        switch (node.getNodeType()) {
            case Node.ELEMENT_NODE:
                Element oldEl = (Element) node;
                String localName = oldEl.getLocalName();

                // Determine if this node or its descendants are part of <Signature>
                boolean isSignature = insideSignature || "Signature".equals(localName);

                // Choose namespace
                String ns = isSignature
                        ? oldEl.getNamespaceURI() // keep XMLDSig namespace
                        : newNamespaceURI;        // assign SII namespace

                // Create the new element
                Element newEl = doc.createElementNS(ns, localName);

                // Copy attributes (excluding xmlns declarations)
                NamedNodeMap attrs = oldEl.getAttributes();
                for (int i = 0; i < attrs.getLength(); i++) {
                    Attr attr = (Attr) attrs.item(i);
                    String name = attr.getName();
                    if (!name.startsWith("xmlns")) {
                        newEl.setAttributeNS(attr.getNamespaceURI(), name, attr.getValue());
                    }
                }

                // Recursively copy children
                Node child = oldEl.getFirstChild();
                while (child != null) {
                    Node importedChild = renameNodeWithNamespace(doc, child, newNamespaceURI, isSignature);
                    newEl.appendChild(importedChild);
                    child = child.getNextSibling();
                }
                return newEl;

            case Node.TEXT_NODE:
                return doc.createTextNode(node.getNodeValue());

            case Node.COMMENT_NODE:
                return doc.createComment(node.getNodeValue());

            case Node.CDATA_SECTION_NODE:
                return doc.createCDATASection(node.getNodeValue());

            default:
                return doc.importNode(node, true); // fallback
        }
    }
}