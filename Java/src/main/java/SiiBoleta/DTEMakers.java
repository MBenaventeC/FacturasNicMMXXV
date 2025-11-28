package SiiBoleta;

import javax.xml.crypto.MarshalException;
import javax.xml.crypto.XMLStructure;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.*;
import java.security.cert.X509Certificate;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.*;
import javax.xml.crypto.dsig.spec.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.xml.security.c14n.Canonicalizer;
import org.apache.xml.security.Init;
import SIIEnvio.EnvioDTE;
import SiiSignature.SignatureType;
import jakarta.xml.bind.*;

import org.w3c.dom.Element;
import org.bouncycastle.operator.OperatorCreationException;
import org.w3c.dom.*;

import org.json.JSONArray;
import org.json.JSONObject;


public class DTEMakers {

    /** Maker for < IdDoc >, which belongs to < Encabezado >
     *
     * @param folio Number of Document authorized by SII
     * @param indServicio Type of Service provided:
     *                      -1 for "Domestic Services" (Electricity Bill, Water Bill, Gas Bill, Phone Bill, Internet Bill, etc.)
     *                      -2 for "Other Periodical Services" (Subscriptions, Rent, etc.)
     *                      -3 for "Services Invoice"
     *                      Only for Export Invoice:
     *                      -4 for "Hospitality Services"
     *                      -5 for "International Land Transport"
     * @param fmaPago Payment Method:
     *                -1 for "Efectivo" / "Cash"
     *                -2 for "Tarjeta de Crédito" / "Credit Card"
     *                -3 for "Gratuito" / "For Free"
     *                According to SII
     * @param medioPago Description for Payment Method:
     *                  -"Efectivo" / "Cash"
     *                  -"Tarjeta de Crédito" / "Credit Card"
     *                  -"Transferencia bancaria" / "Bank Transfer"
     *                  -"Cheque" / "Check"
     * @return Object containing all < IdDoc > information.
     * @throws DatatypeConfigurationException
     */
    public static DTEDefType.Documento.Encabezado.IdDoc makeIdDoc
            (int tipoDoc,
             int folio,
             int indServicio,
             int fmaPago,
             MedioPagoType medioPago
            ) throws DatatypeConfigurationException {
        DTEDefType.Documento.Encabezado.IdDoc  idDoc = new DTEDefType.Documento.Encabezado.IdDoc();

        /**
         * All the documents refered here correspond to different types of Electronic Invoice:
         * - 33 for "Electronic Invoice"
         * - 34 for "Tax-Exempt Invoice"
         * - 43 for "Settlement Invoice"
         * - 46 for "Purchase Invoice"
         * - 52 for "Dispatch Guide"
         * - 56 for "Debit Note"
         * - 61 for "Credit Note"
         * - 110 for "Export Invoice"
         * - 111 for "Export Debit Note"
         * - 112 for "Export Credit Note"
         */
        // Sii DTE Document Type set to 34, representing a 'Factura Exenta' or an 'Electronic Tax-Exempt Invoice'
        idDoc.setTipoDTE(BigInteger.valueOf(tipoDoc));

        // @params folio
        idDoc.setFolio(BigInteger.valueOf(folio));

        // Initializing a GregorianCalender
        GregorianCalendar calendar = new GregorianCalendar();
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        xmlDate.setTimezone( DatatypeConstants.FIELD_UNDEFINED );

            // Issuing Date
        idDoc.setFchEmis(xmlDate);

        // @params indServicio
        idDoc.setIndServicio(BigInteger.valueOf(indServicio));

        // @params fmaPago
        idDoc.setFmaPago(BigInteger.valueOf(fmaPago));

            // Payment Date
        idDoc.setFchCancel(xmlDate);

        // @params medioPago
        idDoc.setMedioPago(medioPago);

            // Expiring Date
        idDoc.setFchVenc(xmlDate);

        return idDoc;
    }

    /** Default maker for < Emisor > or 'Issuing' object, containing default values for NIC Chile.
     * < Emisor > belongs to < Encabezado >
     *
     * All setters arguments can be edited to create a new 'Emisor'.
     *
     * @return Instance of 'Emisor' class
     */
    public static DTEDefType.Documento.Encabezado.Emisor makeEmisor
            (){
        DTEDefType.Documento.Encabezado.Emisor emisor = new DTEDefType.Documento.Encabezado.Emisor();
        emisor.setRUTEmisor("60910000-1");
        emisor.setRznSoc("Universidad de Chile");
        emisor.setGiroEmis("Corporación Educacional y Servicios Profesionales");
        emisor.setActeco(new BigInteger("803010"));
        emisor.setSucursal("NIC Chile");
        emisor.setCdgSIISucur(new BigInteger("67051191"));
        emisor.setDirOrigen("Miraflores 222, Piso 14");
        emisor.setCmnaOrigen("Santiago");
        emisor.setCiudadOrigen("Santiago");
        return emisor;
    }

    /** Maker for a < Receptor > or 'Receiver' object, which belongs to < Encabezado >
     *
     * @param RUTRecep Receptor/Receiver's RUT
     * @param rznSoc Company Name
     * @param giroRecep Business Line
     * @param Contacto Receiver Company Name and Phone Number
     * @param dirRecap Receiver Address
     * @param cmnaRecap Receiver City Municipality
     * @param ciudadRecep Receiver City
     * @return Receptor Object containing all information required.
     */
    public static DTEDefType.Documento.Encabezado.Receptor makeReceptor(
            String RUTRecep,
            String rznSoc,
            String giroRecep,
            String Contacto,
            String dirRecap,
            String cmnaRecap,
            String ciudadRecep
    ){
        DTEDefType.Documento.Encabezado.Receptor receptor = new DTEDefType.Documento.Encabezado.Receptor();
        receptor.setRUTRecep(RUTRecep);
        receptor.setRznSocRecep(rznSoc);
        receptor.setGiroRecep(giroRecep);
        receptor.setContacto(Contacto);
        receptor.setDirRecep(dirRecap);
        receptor.setCmnaRecep(cmnaRecap);
        receptor.setCiudadRecep(ciudadRecep);
        return receptor;
    }

    /** Maker for a < Totales > section, which belongs to < Encabezado >.
     *
     * @param total Net Total Amount
     * @return
     */
    public static DTEDefType.Documento.Encabezado.Totales makeTotales
            (int total){
        DTEDefType.Documento.Encabezado.Totales totales = new DTEDefType.Documento.Encabezado.Totales();

        // Total Sum for Tax-Exempt Items
        totales.setMntExe(BigInteger.valueOf(total));//totales.setMntExe(mntExe);

        // Net Total Amount
        totales.setMntTotal(BigInteger.valueOf(total));//totales.setMntTotal(mntTotal);
        return totales;
    }

    /** Maker for < Encabezado > section, which belongs to < Documento > section.
     *
     * @param idDoc Document ID
     * @param emisor Emisor Object containing all < Emisor > information.
     * @param receptor Receptor Object containing all < Receptor > information.
     * @param totales Totales Object containing all < Totales > information.
     * @return Encabezado object that contains all its subsections information.
     */
    public static DTEDefType.Documento.Encabezado makeEncabezado(
            DTEDefType.Documento.Encabezado.IdDoc idDoc,
            DTEDefType.Documento.Encabezado.Emisor emisor,
            DTEDefType.Documento.Encabezado.Receptor receptor,
            DTEDefType.Documento.Encabezado.Totales totales){
        DTEDefType.Documento.Encabezado encabezado = new DTEDefType.Documento.Encabezado();
        encabezado.setIdDoc(idDoc);
        encabezado.setEmisor(emisor);
        encabezado.setReceptor(receptor);
        encabezado.setTotales(totales);
        return encabezado;
    }

    //se deberia calcular?
    public static DTEDefType.Documento.Detalle makeDetalle(
            int nroLinDet,
            String nmbItem,
            int qtyItem,
            double prcItem){
        DTEDefType.Documento.Detalle detalle = new DTEDefType.Documento.Detalle();
        detalle.setNroLinDet(nroLinDet); //Cantidad de lineas de detalle x es el numero de esta linea en especifico
        detalle.setNmbItem("dominio "+nmbItem); //Nombre del dominio, debe empezar por "dominio "
        detalle.setQtyItem(BigDecimal.valueOf(qtyItem));// cantidad del item
        detalle.setPrcItem(BigDecimal.valueOf(prcItem));// precio del item
        detalle.setMontoItem(BigDecimal.valueOf(qtyItem).multiply(BigDecimal.valueOf(prcItem)).toBigInteger());//qty*prc
        return detalle;
    }

    /** Idea: Add n detalles to < Detalle > section, from a JSON Array
     * @param detallesJson JSON Array containing n detalles values
     * @return List of Detalle objects
     */
    public static List<DTEDefType.Documento.Detalle> makeDetalles(JSONArray detallesJson) {
        List<DTEDefType.Documento.Detalle> detalles = new ArrayList<>();
        for (int i = 0; i < detallesJson.length(); i++) {
            JSONObject detalleJson = detallesJson.getJSONObject(i);
            int nroLinDet = i+1;
            String nmbItem = detalleJson.getString("nmbItem");
            int qtyItem = detalleJson.getInt("qtyItem");
            double prcItem = detalleJson.getDouble("prcItem");

            DTEDefType.Documento.Detalle detalle = makeDetalle(nroLinDet, nmbItem, qtyItem, prcItem);
            detalles.add(detalle);
        }
        return detalles;
    }

    public static JSONArray leerJsonArray(String ruta) throws Exception {
        String contenido = new String(Files.readAllBytes(Paths.get(ruta)));
        return new JSONArray(contenido);
    }

    public static DTEDefType.Documento makeDocumento(DTEDefType.Documento.Encabezado encabezado, List<DTEDefType.Documento.Detalle> detalles,DTEDefType.Documento.TED ted,String id) throws DatatypeConfigurationException {
        DTEDefType.Documento documento = new DTEDefType.Documento();
        documento.setEncabezado(encabezado);
        documento.initializeDetalle();
        for (int i = 0; i < detalles.size(); i++) {
            documento.addDetalle(detalles.get(i));
        }
        GregorianCalendar calendar = new GregorianCalendar();
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        xmlDate.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
        xmlDate.setTimezone( DatatypeConstants.FIELD_UNDEFINED );
        documento.setTmstFirma(xmlDate);
        documento.setID(id);
        documento.setTED(ted);
        return documento;
    }

    /** Creates SignatureType object based on a Document Class Object Information.
     *
     * @param documento
     * @return
     * @throws Exception
     *
    public static SignatureType makeSignature(DTEDefType.Documento documento) throws Exception {
        // Initialize Apache XML Security library
        Init.init();

        // 1. Load certificate and private key
        String rutaPFX = "Java/certificado.pfx";
        String password = Files.readString(Paths.get("password.txt"));
        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(new FileInputStream(rutaPFX), password.toCharArray());
        String alias = ks.aliases().nextElement();
        PrivateKey privateKey = (PrivateKey) ks.getKey(alias, password.toCharArray());
        X509Certificate cert = (X509Certificate) ks.getCertificate(alias);
        PublicKey publicKey = cert.getPublicKey();

        // 2. Prepare JAXB marshaller
        Map<String, Object> props = new HashMap<>();
        props.put("eclipselink.namespace-prefix-mapper", new XmlGenerator.CustomNamespacePrefixMapper3());
        JAXBContext context = org.eclipse.persistence.jaxb.JAXBContextFactory.createContext(
                new Class[]{DTEDefType.Documento.class}, props);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
        marshaller.setProperty("eclipselink.namespace-prefix-mapper", new XmlGenerator.CustomNamespacePrefixMapper3());
        //marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

        // 3. Build namespace-aware DOM
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        Document doc = dbf.newDocumentBuilder().newDocument();

        // 4. Marshal JAXB → DOM
        JAXBElement<DTEDefType.Documento> jaxbElement = new JAXBElement<>(
                new QName(""/*http://www.sii.cl/SiiDte*""/, "Documento"),
                DTEDefType.Documento.class,
                documento);
        marshaller.marshal(jaxbElement, doc);

        /*Element root = doc.getDocumentElement();
        if (root.getPrefix() != null) {
            root.setPrefix(null);
        }
        root.removeAttribute("xmlns:ns0");
        root.setAttribute("xmlns", "http://www.sii.cl/SiiDte");*""/

        // 5. Mark ID attribute
        doc.getDocumentElement().setIdAttribute("ID", true);

        // Initialize XML security
        org.apache.xml.security.Init.init();

        // 6. Canonicalize the <Documento> node BEFORE signing
        Canonicalizer canon = Canonicalizer.getInstance("http://www.w3.org/TR/2001/REC-xml-c14n-20010315");
        // In 3.x, use .canonicalize(byte[]) or .canonicalize(XMLSignatureInput)
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.transform(new DOMSource(doc.getDocumentElement()), new StreamResult(baos));
        byte[] xmlBytes = baos.toByteArray();

        // New API: canonicalize(byte[] input, OutputStream output, boolean includeComments)
        ByteArrayOutputStream canonicalizedOut = new ByteArrayOutputStream();
        canon.canonicalize(xmlBytes, canonicalizedOut, false);
        byte[] canonicalBytes = canonicalizedOut.toByteArray();


        // 7. Parse canonicalized bytes back to a normalized DOM
        DocumentBuilder builder = dbf.newDocumentBuilder();
        Document canonicalDoc = builder.parse(new ByteArrayInputStream(canonicalBytes));
        canonicalDoc.getDocumentElement().setIdAttribute("ID", true);

        // 8. Prepare XMLSignatureFactory and transforms
        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");
        Transform enveloped = fac.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null);
        Transform c14n = fac.newTransform("http://www.w3.org/TR/2001/REC-xml-c14n-20010315", (TransformParameterSpec) null);

        Reference ref = fac.newReference(
                "#" + documento.getID(),
                fac.newDigestMethod(DigestMethod.SHA1, null),
                Arrays.asList(enveloped, c14n),
                null, null);

        SignedInfo si = fac.newSignedInfo(
                fac.newCanonicalizationMethod("http://www.w3.org/TR/2001/REC-xml-c14n-20010315", (C14NMethodParameterSpec) null),
                fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
                Collections.singletonList(ref));

        // 9. Build KeyInfo
        KeyInfoFactory kif = fac.getKeyInfoFactory();
        KeyValue keyValue = kif.newKeyValue(publicKey);
        X509Data x509Data = kif.newX509Data(Collections.singletonList(cert));
        KeyInfo ki = kif.newKeyInfo(Arrays.asList(keyValue, x509Data));

        //printNode(canonicalDoc);
        //System.out.println("");

        // 10. Sign canonicalized document
        DOMSignContext dsc = new DOMSignContext(privateKey, canonicalDoc.getDocumentElement());
        dsc.setDefaultNamespacePrefix("ds");
        XMLSignature signature = fac.newXMLSignature(si, ki);
        signature.sign(dsc);

        // 11. Fix FRMT if necessary
        NodeList frmt = canonicalDoc.getElementsByTagNameNS("*", "FRMT");
        FRMTFixSig(frmt, 64, canonicalDoc);

        // 12. Extract Signature as JAXB
        NodeList sigList = canonicalDoc.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");
        Element signatureElement = (Element) sigList.item(0);
        Unmarshaller unmarshaller = JAXBContext.newInstance(SignatureType.class).createUnmarshaller();
        return (SignatureType) unmarshaller.unmarshal(new DOMSource(signatureElement));
    }*/


    /**
    public static SignatureType makeSignatureO(DTEDefType.Documento documento) throws Exception {
        /*2. Load the XML Document
          Use a DOM parser to load your XML into a Document object:
        *""/
        /*DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        Document doc = dbf.newDocumentBuilder().parse(new FileInputStream("input.xml"));*""/

        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        // 1) Cargar el archivo .p12 o .pfx ===
        /**
         * AÑADAN SU ARCHIVO .PFX A LA CARPETA JAVA
         * (CUIDADO CON SUBIRLA A GITHUB, PORFAVOR AÑADIRLA A SU GITIGNORE O ALGO)
         * Y PEGAR SU RUTA A rutaPFX
         *""/
        String rutaPFX = "Java/certificado.pfx";
        /**
         * LA CONTRASEÑA ES LA MISMA QUE SE CREÓ CUANDO SE GENERÓ EL CERTIFICADO -- no la suban al discord
         *""/
        Path filePath = Paths.get("password.txt");
        String password = Files.readString(filePath);

        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(new FileInputStream(rutaPFX), password.toCharArray());

        // Obtener el alias (normalmente el primero)
        String alias = ks.aliases().nextElement();

        // Obtener la clave privada y el certificado
        PrivateKey privateKey = (PrivateKey) ks.getKey(alias, password.toCharArray());
        X509Certificate cert = (X509Certificate) ks.getCertificate(alias);
        PublicKey publicKey = cert.getPublicKey();

        //JAXBContext context = JAXBContext.newInstance(DTEDefType.Documento.class);
        Map<String, Object> props = new HashMap<>();
        props.put("eclipselink.namespace-prefix-mapper", new XmlGenerator.CustomNamespacePrefixMapper3());

        JAXBContext context = org.eclipse.persistence.jaxb.JAXBContextFactory.createContext(
                new Class[] { DTEDefType.Documento.class },
                props
        );
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty("eclipselink.namespace-prefix-mapper", new XmlGenerator.CustomNamespacePrefixMapper3());

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        //dbf.setNamespaceAware(true);
        Document doc = dbf.newDocumentBuilder().newDocument();

        // Marshal directly into the DOM
        JAXBElement<DTEDefType.Documento> jaxbElement = new JAXBElement<>(
                new QName(/*"http://www.sii.cl/SiiDte",*""/"Documento"),
                DTEDefType.Documento.class,
                documento
        );
        marshaller.marshal(jaxbElement, doc);

        doc.getDocumentElement().setIdAttribute("ID", true);

        //3. Initialize the Signature Factory
        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

        Transform envelopedTransform = fac.newTransform(
                Transform.ENVELOPED,
                (TransformParameterSpec) null
        );
        Transform c14nTransform = fac.newTransform(
                "http://www.w3.org/TR/2001/REC-xml-c14n-20010315",
                (TransformParameterSpec) null
        );

        //4. Create the Reference and SignedInfo
        Reference ref = fac.newReference(
                "#"+documento.getID(),
                fac.newDigestMethod(DigestMethod.SHA1, null),
                Arrays.asList(envelopedTransform, c14nTransform),
                null,
                null
        );
        String method = SignatureMethod.RSA_SHA1;
        if ("DSA".equals(cert.getPublicKey().getAlgorithm())) method = SignatureMethod.DSA_SHA1;
        else if ("HMAC".equals(cert.getPublicKey().getAlgorithm())) method = SignatureMethod.HMAC_SHA1;
        SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod((
                CanonicalizationMethod.INCLUSIVE),
                        (C14NMethodParameterSpec) null),
                fac.newSignatureMethod(method, null),
                Collections.singletonList(ref)
        );
        //ehPI6huYVtrWpk6/QX1xi/iw2cvP3/AOZGRyYgX7RM7NV54PXtE9XcrsNWUVpxAEvWuthoa4nTXz&#13;
        //68uIW68IoqpO3U2yCYdZx7HwpIMVa1eW6YHFKfCt9pjZTRSSRlFjk6GxRfQPqbuZ58AXXMbEOops&#13;
        //4cNtdbiEPTdgzw6HGVapz/rmhJ/Am8MX8ILOpZ5gbqkzJZr/gG7tetsKUhyLaXl5D+RRF3fhn/4D&#13;
        //eBiQp1VGTj6j0gpQ6EpE/6oWF+SiA+EytTZRgklVvfRGJNoZDDU5ABU48xkOZqUQu6ItuLXL1/K4&#13;
        //Nqf2Gih1ENSV4es6+cO7yARLKmaOR49Stgg7Eg==

        //5. Load the Key and Certificat
        /*KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(new FileInputStream("keystore.p12"), "keystorePassword".toCharArray());
        PrivateKey pk = (PrivateKey) ks.getKey("alias", "keyPassword".toCharArray());
        X509Certificate cert = (X509Certificate) ks.getCertificate("alias");

        //6. Create KeyInfo
        KeyInfoFactory kif = fac.getKeyInfoFactory();
        KeyInfo ki = kif.newKeyInfo(Collections.singletonList(kif.newX509Data(Collections.singletonList(cert))));*""/

        //7. Sign the Document
        /*DOMSignContext dsc = new DOMSignContext(pk, doc.getDocumentElement());
        XMLSignature signature = fac.newXMLSignature(si, ki);
        signature.sign(dsc);*""/
        //DTEMakers.formatKeyValueElements(doc,64,0);
        NodeList FRMT = doc.getElementsByTagNameNS("*", "FRMT");
        FRMTFixSig(FRMT, 64,doc);

        DOMSignContext dsc = new DOMSignContext(privateKey, doc.getDocumentElement());
        /*KeyInfo ki = fac.getKeyInfoFactory().newKeyInfo(
                Collections.singletonList(
                        fac.getKeyInfoFactory().newX509Data(Collections.singletonList(cert))
                )
        );*""/
        KeyInfoFactory kif = fac.getKeyInfoFactory();

        KeyValue keyValue = kif.newKeyValue(publicKey);

        // Create X509Data from certificate
        X509Data x509Data = kif.newX509Data(Collections.singletonList(cert));

        // Combine both into KeyInfo
        KeyInfo ki = kif.newKeyInfo(Arrays.asList(keyValue, x509Data));

        XMLSignature signature = fac.newXMLSignature(si, ki);
        signature.sign(dsc);

        NodeList sigList = doc.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");
        Element signatureElement = (Element) sigList.item(0);

        Unmarshaller unmarshaller = JAXBContext.newInstance(SignatureType.class).createUnmarshaller();
        SignatureType signatureJaxb = (SignatureType) unmarshaller.unmarshal(new DOMSource((Node) signatureElement));
        //DTEDefType dte = makeDTE(documento, signatureJaxb);           // your manually built SignatureType
        //signatureJaxb.setURI(documento.getID());

        return signatureJaxb;
    }*/

    public static void printNode(Node node) throws Exception {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        //transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        //transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");

        transformer.transform(new DOMSource(node), new StreamResult(System.out));
    }

    public static SignatureType makeSignatureEnv(Element documento) throws Exception {
        // Init xmlsec
        Init.init();
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

        // 1) Load PKCS12
        String rutaPFX = "Java/certificado.pfx";
        Path filePath = Paths.get("password.txt");
        String password = Files.readString(filePath);

        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(new FileInputStream(rutaPFX), password.toCharArray());

        String alias = ks.aliases().nextElement();
        PrivateKey privateKey = (PrivateKey) ks.getKey(alias, password.toCharArray());
        X509Certificate cert = (X509Certificate) ks.getCertificate(alias);
        PublicKey publicKey = cert.getPublicKey();

        // Build a namespace-aware DOM and import the provided element
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        Document tempDoc = dbf.newDocumentBuilder().newDocument();
        Element imported = (Element) tempDoc.importNode(documento, true);
        tempDoc.appendChild(imported);

        // Ensure ID attribute present and registered
        String idVal = imported.getAttribute("ID");
        if (idVal == null || idVal.isEmpty()) {
            throw new IllegalArgumentException("Provided element must have an ID attribute");
        }
        imported.setIdAttribute("ID", true);

        // --- Canonicalize the imported node BEFORE signing (Santuario 3.x) ---
        // serialize imported node to bytes
        ByteArrayOutputStream serOut = new ByteArrayOutputStream();
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.transform(new DOMSource(imported), new StreamResult(serOut));
        byte[] serialized = serOut.toByteArray();

        // Canonicalize using exclusive C14N (recommended for DTE)
        Canonicalizer canon = Canonicalizer.getInstance(
                "http://www.w3.org/TR/2001/REC-xml-c14n-20010315"
        );
        ByteArrayOutputStream canonicalizedOut = new ByteArrayOutputStream();
        canon.canonicalize(serialized, canonicalizedOut, false);
        byte[] canonicalBytes = canonicalizedOut.toByteArray();

        // Parse canonicalized bytes back into a DOM
        Document canonicalDoc = dbf.newDocumentBuilder().parse(new ByteArrayInputStream(canonicalBytes));
        // Make sure ID is registered on the canonicalized root element too
        Element canonicalRoot = canonicalDoc.getDocumentElement();
        canonicalRoot.setIdAttribute("ID", true);

        // 3. Prepare XML SignatureFactory and transforms
        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");
        Transform envelopedTransform = fac.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null);
        // Use exclusive canonicalization transform URI via factory
        Transform c14nTransform = fac.newTransform(
                "http://www.w3.org/TR/2001/REC-xml-c14n-20010315",
                (TransformParameterSpec) null
        );

        // 4. Create the Reference (to the ID) and SignedInfo - using exclusive C14N
        /*Reference ref = fac.newReference(
                "#" + idVal,
                fac.newDigestMethod(DigestMethod.SHA1, null),
                Arrays.asList(envelopedTransform, c14nTransform),
                null,
                null
        );


        SignedInfo si = fac.newSignedInfo(
                fac.newCanonicalizationMethod(
                        "http://www.w3.org/TR/2001/REC-xml-c14n-20010315",
                        (C14NMethodParameterSpec) null
                ),
                fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
                Collections.singletonList(ref)
        );*/

        Reference ref = fac.newReference("#"+idVal, fac.newDigestMethod(
                DigestMethod.SHA1, null), Collections.singletonList(fac
                .newTransform(Transform.ENVELOPED,
                        (TransformParameterSpec) null)), null, null);


        // Create SignedInfo
        SignedInfo signedInfo = fac.newSignedInfo(
                fac.newCanonicalizationMethod(
                        CanonicalizationMethod.INCLUSIVE,
                        (C14NMethodParameterSpec) null
                ),
                fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
                java.util.Collections.singletonList(ref)
        );

        // 5. KeyInfo
        KeyInfoFactory kif = fac.getKeyInfoFactory();
        KeyValue kv = kif.newKeyValue(publicKey);
        X509Data xd = kif.newX509Data(Collections.singletonList(cert));
        KeyInfo ki = kif.newKeyInfo(Arrays.asList(kv, xd));

        //printNode(canonicalRoot);

        // 6. Sign the canonicalized document
        DOMSignContext dsc = new DOMSignContext(privateKey, canonicalRoot);
        dsc.setDefaultNamespacePrefix("ds");
        XMLSignature signature = fac.newXMLSignature(signedInfo, ki);
        signature.sign(dsc);

        // 7. Extract Signature element from canonicalRoot (there should be exactly one)
        NodeList sigNodes = canonicalRoot.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");
        if (sigNodes.getLength() == 0) {
            throw new IllegalStateException("Signature element not found after signing");
        }
        // use the first Signature node
        Element sigElem = (Element) sigNodes.item(1);

        // 8. Unmarshal into JAXB SignatureType and return
        JAXBContext jctx = JAXBContext.newInstance(SignatureType.class);
        Unmarshaller unmarshaller = jctx.createUnmarshaller();
        JAXBElement<SignatureType> jsig = unmarshaller.unmarshal(sigElem, SignatureType.class);
        return jsig.getValue();
    }

    public static SignatureType makeSignatureEnvO(Element documento) throws Exception {
        /*2. Load the XML Document
          Use a DOM parser to load your XML into a Document object:
        */
        /*DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        Document doc = dbf.newDocumentBuilder().parse(new FileInputStream("input.xml"));*/

        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        // 1) Cargar el archivo .p12 o .pfx ===
        /**
         * AÑADAN SU ARCHIVO .PFX A LA CARPETA JAVA
         * (CUIDADO CON SUBIRLA A GITHUB, PORFAVOR AÑADIRLA A SU GITIGNORE O ALGO)
         * Y PEGAR SU RUTA A rutaPFX
         */
        String rutaPFX = "Java/certificado.pfx";
        /**
         * LA CONTRASEÑA ES LA MISMA QUE SE CREÓ CUANDO SE GENERÓ EL CERTIFICADO -- no la suban al discord
         */
        Path filePath = Paths.get("password.txt");
        String password = Files.readString(filePath);

        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(new FileInputStream(rutaPFX), password.toCharArray());

        // Obtener el alias (normalmente el primero)
        String alias = ks.aliases().nextElement();

        // Obtener la clave privada y el certificado
        PrivateKey privateKey = (PrivateKey) ks.getKey(alias, password.toCharArray());
        X509Certificate cert = (X509Certificate) ks.getCertificate(alias);
        PublicKey publicKey = cert.getPublicKey();

        // Marshal directly into the DOM

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        Document tempDoc = dbf.newDocumentBuilder().newDocument();

        Element imported = (Element) tempDoc.importNode(documento, true);
        tempDoc.appendChild(imported);

        imported.setAttribute("ID", imported.getAttribute("ID"));
        imported.setIdAttribute("ID", true);

        //3. Initialize the Signature Factory
        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");
        Transform envelopedTransform = fac.newTransform(
                "http://www.w3.org/2000/09/xmldsig#enveloped-signature",
                (TransformParameterSpec) null
        );
        Transform c14nTransform = fac.newTransform(
                "http://www.w3.org/TR/2001/REC-xml-c14n-20010315",
                (TransformParameterSpec) null
        );

        //4. Create the Reference and SignedInfo
        Reference ref = fac.newReference(
                "#"+imported.getAttribute("ID"),
                fac.newDigestMethod(DigestMethod.SHA1, null),
                Arrays.asList(envelopedTransform, c14nTransform),
                null,
                null
        );

        SignedInfo si = fac.newSignedInfo(
                fac.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE, (C14NMethodParameterSpec) null),
                fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
                Collections.singletonList(ref)
        );


        /*DOMSignContext dsc = new DOMSignContext(privateKey, documento);

        KeyInfoFactory kif = fac.getKeyInfoFactory();

        KeyValue keyValue = kif.newKeyValue(publicKey);

        // Create X509Data from certificate
        X509Data x509Data = kif.newX509Data(Collections.singletonList(cert));

        // Combine both into KeyInfo
        KeyInfo ki = kif.newKeyInfo(Arrays.asList(keyValue, x509Data));

        XMLSignature signature = fac.newXMLSignature(si, ki);
        signature.sign(dsc);

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        Document tempDoc = dbf.newDocumentBuilder().newDocument();

        DOMSignContext tempCtx = new DOMSignContext(privateKey, tempDoc);
        signature.sign(tempCtx);*/

        //return signature;

        KeyInfoFactory kif = fac.getKeyInfoFactory();
        KeyValue       kv  = kif.newKeyValue(publicKey);
        X509Data       xd  = kif.newX509Data(Collections.singletonList(cert));
        KeyInfo        ki  = kif.newKeyInfo(Arrays.asList(kv, xd));

        XMLSignature signature = fac.newXMLSignature(si, ki);

        // --- sign the imported node ---

        //XmlGenerator.printNode(imported);

        DOMSignContext dsc = new DOMSignContext(privateKey, imported);
        signature.sign(dsc);

        // --- extract the <Signature> child of imported ---
        NodeList sigNodes = imported.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");
        if (sigNodes.getLength() == 0) {
            throw new IllegalStateException("Signature element not found after signing");
        }
        Element sigElem = (Element) sigNodes.item(1);

        // --- unmarshal into JAXB SignatureType ---
        JAXBContext jctx = JAXBContext.newInstance(SignatureType.class);
        Unmarshaller unmarshaller = jctx.createUnmarshaller();
        JAXBElement<SignatureType> jsig = unmarshaller.unmarshal(sigElem, SignatureType.class);
        return jsig.getValue();

    }

    public static SignatureType makeSignatureEnv2(EnvioDTE.SetDTE documento) throws JAXBException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, KeyStoreException, IOException, UnrecoverableKeyException, CertificateException, MarshalException, XMLSignatureException, TransformerException, ParserConfigurationException, OperatorCreationException, KeyException {
        /*2. Load the XML Document
          Use a DOM parser to load your XML into a Document object:
        */
        /*DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        Document doc = dbf.newDocumentBuilder().parse(new FileInputStream("input.xml"));*/

        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        // 1) Cargar el archivo .p12 o .pfx ===
        /**
         * AÑADAN SU ARCHIVO .PFX A LA CARPETA JAVA
         * (CUIDADO CON SUBIRLA A GITHUB, PORFAVOR AÑADIRLA A SU GITIGNORE O ALGO)
         * Y PEGAR SU RUTA A rutaPFX
         */
        String rutaPFX = "Java/certificado.pfx";
        /**
         * LA CONTRASEÑA ES LA MISMA QUE SE CREÓ CUANDO SE GENERÓ EL CERTIFICADO -- no la suban al discord
         */
        Path filePath = Paths.get("password.txt");
        String password = Files.readString(filePath);

        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(new FileInputStream(rutaPFX), password.toCharArray());

        // Obtener el alias (normalmente el primero)
        String alias = ks.aliases().nextElement();

        // Obtener la clave privada y el certificado
        PrivateKey privateKey = (PrivateKey) ks.getKey(alias, password.toCharArray());
        X509Certificate cert = (X509Certificate) ks.getCertificate(alias);
        PublicKey publicKey = cert.getPublicKey();

        JAXBContext context = JAXBContext.newInstance(EnvioDTE.SetDTE.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        Document doc = dbf.newDocumentBuilder().newDocument();

        // Marshal directly into the DOM
        JAXBElement<EnvioDTE.SetDTE> jaxbElement = new JAXBElement<>(
                new QName("SetDTE"), EnvioDTE.SetDTE.class, documento);
        marshaller.marshal(jaxbElement, doc);

        doc.getDocumentElement().setIdAttribute("ID", true);

        //3. Initialize the Signature Factory
        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

        //4. Create the Reference and SignedInfo
        Reference ref = fac.newReference(
                "#"+documento.getID(),
                fac.newDigestMethod(DigestMethod.SHA1, null),
                null,
                null,
                null
        );

        SignedInfo si = fac.newSignedInfo(
                fac.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE, (C14NMethodParameterSpec) null),
                fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
                Collections.singletonList(ref)
        );

        //5. Load the Key and Certificat
        /*KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(new FileInputStream("keystore.p12"), "keystorePassword".toCharArray());
        PrivateKey pk = (PrivateKey) ks.getKey("alias", "keyPassword".toCharArray());
        X509Certificate cert = (X509Certificate) ks.getCertificate("alias");

        //6. Create KeyInfo
        KeyInfoFactory kif = fac.getKeyInfoFactory();
        KeyInfo ki = kif.newKeyInfo(Collections.singletonList(kif.newX509Data(Collections.singletonList(cert))));*/

        //7. Sign the Document
        /*DOMSignContext dsc = new DOMSignContext(pk, doc.getDocumentElement());
        XMLSignature signature = fac.newXMLSignature(si, ki);
        signature.sign(dsc);*/

        DOMSignContext dsc = new DOMSignContext(privateKey, doc.getDocumentElement());
        /*KeyInfo ki = fac.getKeyInfoFactory().newKeyInfo(
                Collections.singletonList(
                        fac.getKeyInfoFactory().newX509Data(Collections.singletonList(cert))
                )
        );*/
        KeyInfoFactory kif = fac.getKeyInfoFactory();

        KeyValue keyValue = kif.newKeyValue(publicKey);

        // Create X509Data from certificate
        X509Data x509Data = kif.newX509Data(Collections.singletonList(cert));

        // Combine both into KeyInfo
        KeyInfo ki = kif.newKeyInfo(Arrays.asList(keyValue, x509Data));

        XMLSignature signature = fac.newXMLSignature(si, ki);
        signature.sign(dsc);

        NodeList sigList = doc.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");
        Element signatureElement = (Element) sigList.item(1);

        Unmarshaller unmarshaller = JAXBContext.newInstance(SignatureType.class).createUnmarshaller();
        SignatureType signatureJaxb = (SignatureType) unmarshaller.unmarshal(new DOMSource((Node) signatureElement));

        return signatureJaxb;
    }

    public static void signXML3(File inputXml, File pkcs12File, String keyPassword, File outputXml) throws Exception {

        // 1️⃣ Parse XML
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder builder = dbf.newDocumentBuilder();
        Document doc = builder.parse(inputXml);

        // 2️⃣ Load PKCS#12 keystore
        KeyStore ks = KeyStore.getInstance("PKCS12");
        try (FileInputStream fis = new FileInputStream(pkcs12File)) {
            ks.load(fis, keyPassword.toCharArray());
        }

        String alias = ks.aliases().nextElement();
        PrivateKey privateKey = (PrivateKey) ks.getKey(alias, keyPassword.toCharArray());
        X509Certificate cert = (X509Certificate) ks.getCertificate(alias);

        // 3️⃣ Create the XMLSignatureFactory
        XMLSignatureFactory sigFactory = XMLSignatureFactory.getInstance("DOM");

        // 4️⃣ Locate <Documento> to sign
        Element elemento = (Element) doc.getElementsByTagName("Documento").item(0);
        if (elemento == null)
            throw new Exception("No <Documento> element found!");

        Attr idAttr = elemento.getAttributeNode("ID");
        if (idAttr == null)
            throw new Exception("<Documento> element has no ID attribute!");

        // Mark the ID attribute so XMLDSig can find it
        elemento.setIdAttributeNode(idAttr, true);
        String referenceId = idAttr.getValue();

        // 5️⃣ Create transforms: enveloped + canonicalization
        List<Transform> transforms = new ArrayList<>();
        transforms.add(sigFactory.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null));
        transforms.add(sigFactory.newTransform(CanonicalizationMethod.INCLUSIVE, (TransformParameterSpec) null));

        // 6️⃣ Reference the Documento element (by ID)
        Reference ref = sigFactory.newReference(
                "#" + referenceId,
                sigFactory.newDigestMethod(DigestMethod.SHA1, null),
                transforms,
                null,
                null
        );

        // 7️⃣ SignedInfo (canonicalize SignedInfo as well)
        SignedInfo signedInfo = sigFactory.newSignedInfo(
                sigFactory.newCanonicalizationMethod(
                        CanonicalizationMethod.INCLUSIVE,
                        (C14NMethodParameterSpec) null
                ),
                sigFactory.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
                Collections.singletonList(ref)
        );

        // 8️⃣ KeyInfo (embed certificate)
        KeyInfoFactory kif = sigFactory.getKeyInfoFactory();
        X509Data x509Data = kif.newX509Data(Collections.singletonList(cert));
        KeyInfo keyInfo = kif.newKeyInfo(Collections.singletonList(x509Data));

        // 9️⃣ DOMSignContext — insert Signature under root (not inside Documento)
        Element root = doc.getDocumentElement();
        DOMSignContext dsc = new DOMSignContext(privateKey, root);

        // Initialize the library once
        org.apache.xml.security.Init.init();

        // 🔟 Create and sign
        XMLSignature signature = sigFactory.newXMLSignature(signedInfo, keyInfo);
        signature.sign(dsc);

        // 11️⃣ Output result
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer trans = tf.newTransformer();
        trans.setOutputProperty(OutputKeys.INDENT, "yes");
        trans.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
        trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        trans.transform(new DOMSource(doc), new StreamResult(outputXml));

        System.out.println("✅ Signed XML created: " + outputXml.getAbsolutePath());
    }

    private static void normalizeLineEndings(Node node) {
        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node child = list.item(i);
            if (child.getNodeType() == Node.TEXT_NODE) {
                // Replace CRLF or CR with LF
                child.setTextContent(child.getTextContent().replace("\r\n", "\n").replace("\r", "\n"));
            } else {
                normalizeLineEndings(child);
            }
        }
    }

    public static void signXML(File inputXml, File pkcs12File, String keyPassword, File outputXml) throws Exception {
        // Load XML document
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        //dbf.setNamespaceAware(true);
        //dbf.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder = dbf.newDocumentBuilder();
        Document doc = builder.parse(inputXml);

        // Load the keystore (PKCS12)
        KeyStore ks = KeyStore.getInstance("PKCS12");
        try (FileInputStream fis = new FileInputStream(pkcs12File)) {
            ks.load(fis, keyPassword.toCharArray());
        }

        // Get private key and certificate
        String alias = ks.aliases().nextElement();
        Key privateKey = ks.getKey(alias, keyPassword.toCharArray());
        X509Certificate cert = (X509Certificate) ks.getCertificate(alias);

        // Create the XML Signature factory
        XMLSignatureFactory sigFactory = XMLSignatureFactory.getInstance("DOM");

        // Find the element to sign (example: <DTE ID="DTE-34-22295">)

        // Find the <Documento> element
        /*NodeList list = doc.getElementsByTagName("Documento");
        if (list.getLength() == 0) {
            throw new Exception("No <Documento> element found in XML!");
        }
        Element elementToSign = (Element) list.item(0);*/

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
            throw new Exception("<Documento> element has no ID attribute!");
        }

        // Register the ID attribute properly (safe way)
        elementToSign.setIdAttributeNode(idAttr, true);


        // Extract its value
        String referenceId = idAttr.getValue();

        List<Transform> transforms = new ArrayList<>();
        //transforms.add(sigFactory.newTransform(CanonicalizationMethod.INCLUSIVE, (TransformParameterSpec) null));

        // Create a Reference to the entire document (enveloped)
        Reference ref = sigFactory.newReference(
                "#"+referenceId, // <-- match your actual ID attribute
                sigFactory.newDigestMethod(DigestMethod.SHA1, null),
                transforms,
                null,
                null
        );

        String method = SignatureMethod.RSA_SHA1; // default by SII

        if ("DSA".equals(cert.getPublicKey().getAlgorithm()))
            method = SignatureMethod.DSA_SHA1;
        else if ("HMAC".equals(cert.getPublicKey().getAlgorithm()))
            method = SignatureMethod.HMAC_SHA1;

        // Create SignedInfo
        SignedInfo signedInfo = sigFactory.newSignedInfo(
                sigFactory.newCanonicalizationMethod(
                        CanonicalizationMethod.INCLUSIVE,
                        (C14NMethodParameterSpec) null
                ),
                sigFactory.newSignatureMethod(method, null),
                java.util.Collections.singletonList(ref)
        );

        // Create KeyInfo (contains the X.509 certificate)
        /*KeyInfoFactory kif = sigFactory.getKeyInfoFactory();
        X509Data x509Data = kif.newX509Data(java.util.Collections.singletonList(cert));
        KeyInfo keyInfo = kif.newKeyInfo(java.util.Collections.singletonList(x509Data));*/

        KeyInfoFactory kif = sigFactory.getKeyInfoFactory();
        List<XMLStructure> kiObjects = new ArrayList<>();

        // Add public key
        PublicKey publicKey = cert.getPublicKey();
        KeyValue keyValue = kif.newKeyValue(publicKey);
        kiObjects.add(keyValue);

        // Add certificate
        X509Data x509Data = kif.newX509Data(Collections.singletonList(cert));
        kiObjects.add(x509Data);

        KeyInfo keyInfo = kif.newKeyInfo(kiObjects);

        // Create the XMLSignature
        XMLSignature signature = sigFactory.newXMLSignature(signedInfo, keyInfo);

        // Create a DOMSignContext (signing context)
        DOMSignContext dsc = new DOMSignContext(privateKey, doc.getDocumentElement());

        // Sign the document
        signature.sign(dsc);


        // Output the signed XML
        //TransformerFactory tf = TransformerFactory.newInstance();
        //Transformer trans = tf.newTransformer();
        //trans.setOutputProperty(OutputKeys.INDENT, "yes");
        //trans.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
        //trans.setOutputProperty(OutputKeys.METHOD, "xml");
        //trans.transform(new DOMSource(doc), new StreamResult(outputXml));

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer trans = tf.newTransformer();
        trans.setOutputProperty(OutputKeys.METHOD, "xml");
        trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        trans.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");

        // Do NOT indent — that can break signatures or add empty lines
        trans.setOutputProperty(OutputKeys.INDENT, "no");

        // Perform the transformation
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        trans.transform(new DOMSource(doc), new StreamResult(baos));

        // Convert to string safely, removing only carriage return entities
        String xmlClean = baos.toString(StandardCharsets.ISO_8859_1)
                //.replace("&#13;", "")
                //.replaceAll("><", ">\r\n<")
                ;
                //.replaceAll("<DigestValue>", "<DigestValue>\r\n")
                //.replaceAll("</DigestValue>", "\r\n</DigestValue>")
                //.replaceAll("<SignatureValue>", "<SignatureValue>\r\n")
                //.replaceAll("</SignatureValue>", "\r\n</SignatureValue>")
                //.replaceAll("<Modulus>", "<Modulus>\r\n")
                //.replaceAll("</Modulus>", "\r\n</Modulus>")
                //.replaceAll("<Exponent>", "<Exponent>\r\n")
                //.replaceAll("</Exponent>", "\r\n</Exponent>")
                //.replaceAll("<X509Certificate>", "<X509Certificate>\r\n")
                //.replaceAll("</X509Certificate>", "\r\n</X509Certificate>");
                //.replaceAll(" standalone=\"no\"","")
                //.replaceAll("</Signature>\n" + "</DTE>","</Signature></DTE>");
                //.replaceAll("[ \\t]+\\r\\n", ""); // only remove encoded CRs
        // Write back to file preserving your indentation
        //Files.write(outputXml.toPath(), xmlClean.getBytes("ISO-8859-1"));
        try (OutputStream os = Files.newOutputStream(outputXml.toPath())) {
            trans.transform(new DOMSource(doc), new StreamResult(os));
        }
        }

    public static void signXMLD(Document doc, File pkcs12File, String keyPassword, File outputXml) throws Exception {

        // Load the keystore (PKCS12)
        KeyStore ks = KeyStore.getInstance("PKCS12");
        try (FileInputStream fis = new FileInputStream(pkcs12File)) {
            ks.load(fis, keyPassword.toCharArray());
        }

        // Get private key and certificate
        String alias = ks.aliases().nextElement();
        Key privateKey = ks.getKey(alias, keyPassword.toCharArray());
        X509Certificate cert = (X509Certificate) ks.getCertificate(alias);

        // Create the XML Signature factory
        XMLSignatureFactory sigFactory = XMLSignatureFactory.getInstance("DOM");

        // Find the element to sign (example: <DTE ID="DTE-34-22295">)

        // Find the <Documento> element
        /*NodeList list = doc.getElementsByTagName("Documento");
        if (list.getLength() == 0) {
            throw new Exception("No <Documento> element found in XML!");
        }
        Element elementToSign = (Element) list.item(0);*/

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
            throw new Exception("<Documento> element has no ID attribute!");
        }

        // Register the ID attribute properly (safe way)
        elementToSign.setIdAttributeNode(idAttr, true);


        // Extract its value
        String referenceId = idAttr.getValue();

        List<Transform> transforms = new ArrayList<>();
        //transforms.add(sigFactory.newTransform(CanonicalizationMethod.INCLUSIVE, (TransformParameterSpec) null));

        // Create a Reference to the entire document (enveloped)
        Reference ref = sigFactory.newReference(
                "#"+referenceId, // <-- match your actual ID attribute
                sigFactory.newDigestMethod(DigestMethod.SHA1, null),
                transforms,
                null,
                null
        );

        String method = SignatureMethod.RSA_SHA1; // default by SII

        if ("DSA".equals(cert.getPublicKey().getAlgorithm()))
            method = SignatureMethod.DSA_SHA1;
        else if ("HMAC".equals(cert.getPublicKey().getAlgorithm()))
            method = SignatureMethod.HMAC_SHA1;

        // Create SignedInfo
        SignedInfo signedInfo = sigFactory.newSignedInfo(
                sigFactory.newCanonicalizationMethod(
                        CanonicalizationMethod.INCLUSIVE,
                        (C14NMethodParameterSpec) null
                ),
                sigFactory.newSignatureMethod(method, null),
                java.util.Collections.singletonList(ref)
        );

        // Create KeyInfo (contains the X.509 certificate)
        /*KeyInfoFactory kif = sigFactory.getKeyInfoFactory();
        X509Data x509Data = kif.newX509Data(java.util.Collections.singletonList(cert));
        KeyInfo keyInfo = kif.newKeyInfo(java.util.Collections.singletonList(x509Data));*/

        KeyInfoFactory kif = sigFactory.getKeyInfoFactory();
        List<XMLStructure> kiObjects = new ArrayList<>();

        // Add public key
        PublicKey publicKey = cert.getPublicKey();
        KeyValue keyValue = kif.newKeyValue(publicKey);
        kiObjects.add(keyValue);

        // Add certificate
        X509Data x509Data = kif.newX509Data(Collections.singletonList(cert));
        kiObjects.add(x509Data);

        KeyInfo keyInfo = kif.newKeyInfo(kiObjects);

        // Create the XMLSignature
        XMLSignature signature = sigFactory.newXMLSignature(signedInfo, keyInfo);

        // Create a DOMSignContext (signing context)
        DOMSignContext dsc = new DOMSignContext(privateKey, doc.getDocumentElement());

        // Sign the document
        signature.sign(dsc);


        // Output the signed XML
        //TransformerFactory tf = TransformerFactory.newInstance();
        //Transformer trans = tf.newTransformer();
        //trans.setOutputProperty(OutputKeys.INDENT, "yes");
        //trans.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
        //trans.setOutputProperty(OutputKeys.METHOD, "xml");
        //trans.transform(new DOMSource(doc), new StreamResult(outputXml));

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer trans = tf.newTransformer();
        trans.setOutputProperty(OutputKeys.METHOD, "xml");
        trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        trans.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
//
        //// Do NOT indent — that can break signatures or add empty lines
        trans.setOutputProperty(OutputKeys.INDENT, "yes");
//
        //// Perform the transformation
        //ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //trans.transform(new DOMSource(doc), new StreamResult(baos));
//
        //// Convert to string safely, removing only carriage return entities
        //String xmlClean = baos.toString(StandardCharsets.ISO_8859_1)
        //        //.replace("&#13;", "")
        //        //.replaceAll("><", ">\r\n<")
        //        ;
        //.replaceAll("<DigestValue>", "<DigestValue>\r\n")
        //.replaceAll("</DigestValue>", "\r\n</DigestValue>")
        //.replaceAll("<SignatureValue>", "<SignatureValue>\r\n")
        //.replaceAll("</SignatureValue>", "\r\n</SignatureValue>")
        //.replaceAll("<Modulus>", "<Modulus>\r\n")
        //.replaceAll("</Modulus>", "\r\n</Modulus>")
        //.replaceAll("<Exponent>", "<Exponent>\r\n")
        //.replaceAll("</Exponent>", "\r\n</Exponent>")
        //.replaceAll("<X509Certificate>", "<X509Certificate>\r\n")
        //.replaceAll("</X509Certificate>", "\r\n</X509Certificate>");
        //.replaceAll(" standalone=\"no\"","")
        //.replaceAll("</Signature>\n" + "</DTE>","</Signature></DTE>");
        //.replaceAll("[ \\t]+\\r\\n", ""); // only remove encoded CRs
        // Write back to file preserving your indentation
        //Files.write(outputXml.toPath(), xmlClean.getBytes("ISO-8859-1"));
        try (OutputStream os = Files.newOutputStream(outputXml.toPath())) {
            trans.transform(new DOMSource(doc), new StreamResult(os));
        }
    }

    public static void signXMLT(File inputXml, File pkcs12File, String keyPassword, File outputXml) throws Exception {
        // Load XML document
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        dbf.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder = dbf.newDocumentBuilder();
        Document doc = builder.parse(inputXml);

        //printNode(doc);

        // Load the keystore (PKCS12)
        KeyStore ks = KeyStore.getInstance("PKCS12");
        try (FileInputStream fis = new FileInputStream(pkcs12File)) {
            ks.load(fis, keyPassword.toCharArray());
        }

        // Get private key and certificate
        String alias = ks.aliases().nextElement();
        Key privateKey = ks.getKey(alias, keyPassword.toCharArray());
        X509Certificate cert = (X509Certificate) ks.getCertificate(alias);

        // Create the XML Signature factory
        XMLSignatureFactory sigFactory = XMLSignatureFactory.getInstance("DOM");

        // Find the element to sign (example: <DTE ID="DTE-34-22295">)

        // Find the <Documento> element
        /*NodeList list = doc.getElementsByTagName("Documento");
        if (list.getLength() == 0) {
            throw new Exception("No <Documento> element found in XML!");
        }
        Element elementToSign = (Element) list.item(0);*/

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

        // Extract its value
        String referenceId = idAttr.getValue();

        //List<Transform> transforms = new ArrayList<>();
        //transforms.add(sigFactory.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null));
        //transforms.add(sigFactory.newTransform(CanonicalizationMethod.INCLUSIVE, (TransformParameterSpec) null));

        // Create a Reference to the entire document (enveloped)
        /*Reference ref = sigFactory.newReference(
                "#"+referenceId, // <-- match your actual ID attribute
                sigFactory.newDigestMethod(DigestMethod.SHA1, null),
                transforms,
                null,
                null
        );*/
        Reference ref = sigFactory.newReference("#"+referenceId, sigFactory.newDigestMethod(
                DigestMethod.SHA1, null), Collections.singletonList(sigFactory
                .newTransform(Transform.ENVELOPED,
                        (TransformParameterSpec) null)), null, null);


        // Create SignedInfo
        SignedInfo signedInfo = sigFactory.newSignedInfo(
                sigFactory.newCanonicalizationMethod(
                        CanonicalizationMethod.INCLUSIVE,
                        (C14NMethodParameterSpec) null
                ),
                sigFactory.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
                java.util.Collections.singletonList(ref)
        );

        // Create KeyInfo (contains the X.509 certificate)
        /*KeyInfoFactory kif = sigFactory.getKeyInfoFactory();
        X509Data x509Data = kif.newX509Data(java.util.Collections.singletonList(cert));
        KeyInfo keyInfo = kif.newKeyInfo(java.util.Collections.singletonList(x509Data));*/

        KeyInfoFactory kif = sigFactory.getKeyInfoFactory();
        List<XMLStructure> kiObjects = new ArrayList<>();

        // Add public key
        PublicKey publicKey = cert.getPublicKey();
        KeyValue keyValue = kif.newKeyValue(publicKey);
        kiObjects.add(keyValue);

        // Add certificate
        X509Data x509Data = kif.newX509Data(Collections.singletonList(cert));
        kiObjects.add(x509Data);

        KeyInfo keyInfo = kif.newKeyInfo(kiObjects);

        // Create the XMLSignature
        XMLSignature signature = sigFactory.newXMLSignature(signedInfo, keyInfo);

        // Create a DOMSignContext (signing context)
        DOMSignContext dsc = new DOMSignContext(privateKey, doc.getDocumentElement());
        dsc.setDefaultNamespacePrefix("ds");

        // Sign the document
        signature.sign(dsc);

        /*NodeList nl = doc.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");
        if (nl.getLength() == 0) {
            throw new Exception("No Signature element found in document.");
        }

// Create a DOMValidateContext
        DOMValidateContext valContext = new DOMValidateContext(cert.getPublicKey(), nl.item(1));

// Create an XMLSignatureFactory
        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

        valContext.setProperty("org.jcp.xml.dsig.secureValidation", Boolean.FALSE);

// Unmarshal the XMLSignature (parse it back into a Signature object)
        XMLSignature signatureCheck = fac.unmarshalXMLSignature(valContext);

// Validate the signature
        boolean isValid = signatureCheck.validate(valContext);
        System.out.println("Signature valid? " + isValid);

// Optional: detailed checks
        boolean sv = signatureCheck.getSignatureValue().validate(valContext);
        System.out.println(" - SignatureValue valid? " + sv);

        Iterator<?> i = signatureCheck.getSignedInfo().getReferences().iterator();
        for (int j = 0; i.hasNext(); j++) {
            boolean refValid = ((Reference) i.next()).validate(valContext);
            System.out.println(" - Reference[" + j + "] valid? " + refValid);
        }*/


        // Output the signed XML
        //TransformerFactory tf = TransformerFactory.newInstance();
        //Transformer trans = tf.newTransformer();
        //trans.setOutputProperty(OutputKeys.INDENT, "yes");
        //trans.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
        //trans.setOutputProperty(OutputKeys.METHOD, "xml");
        //trans.transform(new DOMSource(doc), new StreamResult(outputXml));

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer trans = tf.newTransformer();
        trans.setOutputProperty(OutputKeys.METHOD, "xml");
        trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        trans.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");

        // Do NOT indent — that can break signatures or add empty lines
        //trans.setOutputProperty(OutputKeys.INDENT, "yes");

        //root.normalize();
        //normalizeLineEndings(doc);

        // Perform the transformation
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        trans.transform(new DOMSource(doc), new StreamResult(baos));


        /*org.apache.xml.security.Init.init();

        //printNode(doc);

        Canonicalizer canon = Canonicalizer.getInstance("http://www.w3.org/TR/2001/REC-xml-c14n-20010315");
        ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
        canon.canonicalizeSubtree(doc, baos2);
        byte[] canonicalBytes = baos2.toByteArray();*/

        //System.out.println(new String(canonicalBytes, StandardCharsets.ISO_8859_1));

        // Convert to string safely, removing only carriage return entities
        //String xmlClean = baos.toString(StandardCharsets.ISO_8859_1)
                //.replace("&#13;", "")
        //        .replaceAll("><", ">\r\n<")
                //.replaceAll("<DigestValue>", "<DigestValue>\r\n")
                //.replaceAll("</DigestValue>", "\r\n</DigestValue>")
                //.replaceAll("<SignatureValue>", "<SignatureValue>\r\n")
                //.replaceAll("</SignatureValue>", "\r\n</SignatureValue>")
                //.replaceAll("<Modulus>", "<Modulus>\r\n")
                //.replaceAll("</Modulus>", "\r\n</Modulus>")
                //.replaceAll("<Exponent>", "<Exponent>\r\n")
                //.replaceAll("</Exponent>", "\r\n</Exponent>")
                //.replaceAll("<X509Certificate>", "<X509Certificate>\r\n")
                //.replaceAll("</X509Certificate>", "\r\n</X509Certificate>")
                //.replaceAll("\r\n\r","\r\n")
                //.replaceAll("\r\r","\r")
                //.replaceAll("\n\n","\n")
                ;

                //.replaceAll("\r","\rr")
        //.replaceAll(" standalone=\"no\"","")
        //.replaceAll("</Signature>\n" + "</DTE>","</Signature></DTE>");
        //.replaceAll("[ \\t]+\\r\\n", ""); // only remove encoded CRs
        // Write back to file preserving your indentation
        //Files.write(outputXml.toPath(), xmlClean.getBytes(StandardCharsets.ISO_8859_1));
        try (OutputStream os = Files.newOutputStream(outputXml.toPath())) {
            trans.transform(new DOMSource(doc), new StreamResult(os));
        }
    }

    public static void signXMLTD(Document doc, File pkcs12File, String keyPassword, File outputXml) throws Exception {

        // Load the keystore (PKCS12)
        KeyStore ks = KeyStore.getInstance("PKCS12");
        try (FileInputStream fis = new FileInputStream(pkcs12File)) {
            ks.load(fis, keyPassword.toCharArray());
        }

        // Get private key and certificate
        String alias = ks.aliases().nextElement();
        Key privateKey = ks.getKey(alias, keyPassword.toCharArray());
        X509Certificate cert = (X509Certificate) ks.getCertificate(alias);

        // Create the XML Signature factory
        XMLSignatureFactory sigFactory = XMLSignatureFactory.getInstance("DOM");

        // Find the element to sign (example: <DTE ID="DTE-34-22295">)

        // Find the <Documento> element
        /*NodeList list = doc.getElementsByTagName("Documento");
        if (list.getLength() == 0) {
            throw new Exception("No <Documento> element found in XML!");
        }
        Element elementToSign = (Element) list.item(0);*/

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

        // Extract its value
        String referenceId = idAttr.getValue();

        //List<Transform> transforms = new ArrayList<>();
        //transforms.add(sigFactory.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null));
        //transforms.add(sigFactory.newTransform(CanonicalizationMethod.INCLUSIVE, (TransformParameterSpec) null));

        // Create a Reference to the entire document (enveloped)
        /*Reference ref = sigFactory.newReference(
                "#"+referenceId, // <-- match your actual ID attribute
                sigFactory.newDigestMethod(DigestMethod.SHA1, null),
                transforms,
                null,
                null
        );*/
        Reference ref = sigFactory.newReference("#"+referenceId, sigFactory.newDigestMethod(
                DigestMethod.SHA1, null), Collections.singletonList(sigFactory
                .newTransform(Transform.ENVELOPED,
                        (TransformParameterSpec) null)), null, null);


        // Create SignedInfo
        SignedInfo signedInfo = sigFactory.newSignedInfo(
                sigFactory.newCanonicalizationMethod(
                        CanonicalizationMethod.INCLUSIVE,
                        (C14NMethodParameterSpec) null
                ),
                sigFactory.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
                java.util.Collections.singletonList(ref)
        );

        // Create KeyInfo (contains the X.509 certificate)
        /*KeyInfoFactory kif = sigFactory.getKeyInfoFactory();
        X509Data x509Data = kif.newX509Data(java.util.Collections.singletonList(cert));
        KeyInfo keyInfo = kif.newKeyInfo(java.util.Collections.singletonList(x509Data));*/

        KeyInfoFactory kif = sigFactory.getKeyInfoFactory();
        List<XMLStructure> kiObjects = new ArrayList<>();

        // Add public key
        PublicKey publicKey = cert.getPublicKey();
        KeyValue keyValue = kif.newKeyValue(publicKey);
        kiObjects.add(keyValue);

        // Add certificate
        X509Data x509Data = kif.newX509Data(Collections.singletonList(cert));
        kiObjects.add(x509Data);

        KeyInfo keyInfo = kif.newKeyInfo(kiObjects);

        // Create the XMLSignature
        XMLSignature signature = sigFactory.newXMLSignature(signedInfo, keyInfo);

        // Create a DOMSignContext (signing context)
        DOMSignContext dsc = new DOMSignContext(privateKey, doc.getDocumentElement());
        dsc.setDefaultNamespacePrefix("ds");

        // Sign the document
        signature.sign(dsc);

        /*NodeList nl = doc.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");
        if (nl.getLength() == 0) {
            throw new Exception("No Signature element found in document.");
        }

// Create a DOMValidateContext
        DOMValidateContext valContext = new DOMValidateContext(cert.getPublicKey(), nl.item(1));

// Create an XMLSignatureFactory
        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

        valContext.setProperty("org.jcp.xml.dsig.secureValidation", Boolean.FALSE);

// Unmarshal the XMLSignature (parse it back into a Signature object)
        XMLSignature signatureCheck = fac.unmarshalXMLSignature(valContext);

// Validate the signature
        boolean isValid = signatureCheck.validate(valContext);
        System.out.println("Signature valid? " + isValid);

// Optional: detailed checks
        boolean sv = signatureCheck.getSignatureValue().validate(valContext);
        System.out.println(" - SignatureValue valid? " + sv);

        Iterator<?> i = signatureCheck.getSignedInfo().getReferences().iterator();
        for (int j = 0; i.hasNext(); j++) {
            boolean refValid = ((Reference) i.next()).validate(valContext);
            System.out.println(" - Reference[" + j + "] valid? " + refValid);
        }*/


        // Output the signed XML
        //TransformerFactory tf = TransformerFactory.newInstance();
        //Transformer trans = tf.newTransformer();
        //trans.setOutputProperty(OutputKeys.INDENT, "yes");
        //trans.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
        //trans.setOutputProperty(OutputKeys.METHOD, "xml");
        //trans.transform(new DOMSource(doc), new StreamResult(outputXml));


        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer trans = tf.newTransformer();
        trans.setOutputProperty(OutputKeys.METHOD, "xml");
        trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        trans.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
        trans.setOutputProperty(OutputKeys.STANDALONE, "no");

        // Do NOT indent — that can break signatures or add empty lines
        trans.setOutputProperty(OutputKeys.INDENT, "yes");

        //root.normalize();
        //normalizeLineEndings(doc);

        // Perform the transformation
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        trans.transform(new DOMSource(doc), new StreamResult(baos));


        /*org.apache.xml.security.Init.init();

        //printNode(doc);

        Canonicalizer canon = Canonicalizer.getInstance("http://www.w3.org/TR/2001/REC-xml-c14n-20010315");
        ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
        canon.canonicalizeSubtree(doc, baos2);
        byte[] canonicalBytes = baos2.toByteArray();*/

        //System.out.println(new String(canonicalBytes, StandardCharsets.ISO_8859_1));

        // Convert to string safely, removing only carriage return entities
        //String xmlClean = baos.toString(StandardCharsets.ISO_8859_1)
        //.replace("&#13;", "")
        //        .replaceAll("><", ">\r\n<")
        //.replaceAll("<DigestValue>", "<DigestValue>\r\n")
        //.replaceAll("</DigestValue>", "\r\n</DigestValue>")
        //.replaceAll("<SignatureValue>", "<SignatureValue>\r\n")
        //.replaceAll("</SignatureValue>", "\r\n</SignatureValue>")
        //.replaceAll("<Modulus>", "<Modulus>\r\n")
        //.replaceAll("</Modulus>", "\r\n</Modulus>")
        //.replaceAll("<Exponent>", "<Exponent>\r\n")
        //.replaceAll("</Exponent>", "\r\n</Exponent>")
        //.replaceAll("<X509Certificate>", "<X509Certificate>\r\n")
        //.replaceAll("</X509Certificate>", "\r\n</X509Certificate>")
        //.replaceAll("\r\n\r","\r\n")
        //.replaceAll("\r\r","\r")
        //.replaceAll("\n\n","\n")
        ;

        //.replaceAll("\r","\rr")
        //.replaceAll(" standalone=\"no\"","")
        //.replaceAll("</Signature>\n" + "</DTE>","</Signature></DTE>");
        //.replaceAll("[ \\t]+\\r\\n", ""); // only remove encoded CRs
        // Write back to file preserving your indentation
        //Files.write(outputXml.toPath(), xmlClean.getBytes(StandardCharsets.ISO_8859_1));
        try (OutputStream os = Files.newOutputStream(outputXml.toPath())) {
            trans.transform(new DOMSource(doc), new StreamResult(os));
        }
    }

    public static void signEmbeded(Node doc, String uri, PrivateKey pKey,
                                   X509Certificate cert) throws Exception {

        // Create a DOM XMLSignatureFactory that will be used to generate the
        // enveloped signature
        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

        // Create a Reference to the enveloped document (in this case we are
        // signing the whole document, so a URI of "" signifies that) and
        // also specify the SHA1 digest algorithm and the ENVELOPED Transform.

        Reference ref = fac.newReference(uri, fac.newDigestMethod(
                DigestMethod.SHA1, null), Collections.singletonList(fac
                .newTransform(Transform.ENVELOPED,
                        (TransformParameterSpec) null)), null, null);

        // Create the SignedInfo
        String method = SignatureMethod.RSA_SHA1; // default by SII

        if ("DSA".equals(cert.getPublicKey().getAlgorithm()))
            method = SignatureMethod.DSA_SHA1;
        else if ("HMAC".equals(cert.getPublicKey().getAlgorithm()))
            method = SignatureMethod.HMAC_SHA1;

        SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod(
                CanonicalizationMethod.INCLUSIVE, // Default canonical and
                // default by SII
                (C14NMethodParameterSpec) null), fac.newSignatureMethod(method,
                null), Collections.singletonList(ref));

        KeyInfoFactory kif = fac.getKeyInfoFactory();
        KeyValue kv = kif.newKeyValue(cert.getPublicKey());

        // Create a KeyInfo and add the KeyValue to it
        List<XMLStructure> kidata = new ArrayList<XMLStructure>();
        kidata.add(kv);
        kidata.add(kif.newX509Data(Collections.singletonList(cert)));
        KeyInfo ki = kif.newKeyInfo(kidata);

        //printNode(doc);

        // Create a DOMSignContext and specify the PrivateKey and
        // location of the resulting XMLSignature's parent element
        DOMSignContext dsc = new DOMSignContext(pKey, doc);

        // Create the XMLSignature (but don't sign it yet)
        XMLSignature signature = fac.newXMLSignature(si, ki);

        // Marshal, generate (and sign) the enveloped signature
        signature.sign(dsc);
        //printNode(doc);

    }

    public static void signXMLNIC(File inputXml, File pkcs12File, String keyPassword, File outputXml) throws Exception {
        // Load XML document
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        dbf.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder = dbf.newDocumentBuilder();
        Document doc = builder.parse(inputXml);

        // Load the keystore (PKCS12)
        KeyStore ks = KeyStore.getInstance("PKCS12");
        try (FileInputStream fis = new FileInputStream(pkcs12File)) {
            ks.load(fis, keyPassword.toCharArray());
        }

        // Get private key and certificate
        String alias = ks.aliases().nextElement();
        PrivateKey privateKey = (PrivateKey)ks.getKey(alias, keyPassword.toCharArray());
        X509Certificate cert = (X509Certificate) ks.getCertificate(alias);

        // Create the XML Signature factory
        XMLSignatureFactory sigFactory = XMLSignatureFactory.getInstance("DOM");

        // Find the element to sign (example: <DTE ID="DTE-34-22295">)

        // Find the <Documento> element
        /*NodeList list = doc.getElementsByTagName("Documento");
        if (list.getLength() == 0) {
            throw new Exception("No <Documento> element found in XML!");
        }
        Element elementToSign = (Element) list.item(0);*/

        Element root = doc.getDocumentElement();
        root.normalize();
        normalizeLineEndings(doc);
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

        // Extract its value
        String referenceId = idAttr.getValue();

        signEmbeded(doc.getDocumentElement(),"#"+referenceId,privateKey,cert);

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer trans = tf.newTransformer();
        trans.setOutputProperty(OutputKeys.METHOD, "xml");
        trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        trans.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");

        // Do NOT indent — that can break signatures or add empty lines
        trans.setOutputProperty(OutputKeys.INDENT, "no");

        //root.normalize();
        //normalizeLineEndings(doc);

        // Perform the transformation
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        trans.transform(new DOMSource(doc), new StreamResult(baos));


        /*org.apache.xml.security.Init.init();

        //printNode(doc);

        Canonicalizer canon = Canonicalizer.getInstance("http://www.w3.org/TR/2001/REC-xml-c14n-20010315");
        ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
        canon.canonicalizeSubtree(doc, baos2);
        byte[] canonicalBytes = baos2.toByteArray();*/

        //System.out.println(new String(canonicalBytes, StandardCharsets.ISO_8859_1));

        // Convert to string safely, removing only carriage return entities
        String xmlClean = baos.toString("ISO-8859-1")
                //.replace("&#13;", "")
                //.replaceAll("><", ">\n<")
                ;
        //.replaceAll(" standalone=\"no\"","")
        //.replaceAll("</Signature>\n" + "</DTE>","</Signature></DTE>");
        //.replaceAll("[ \\t]+\\r\\n", ""); // only remove encoded CRs
        // Write back to file preserving your indentation
        Files.write(outputXml.toPath(), xmlClean.getBytes("ISO-8859-1"));
    }

    public static void signXMLT2(File inputXml, File pkcs12File, String keyPassword, File outputXml) throws Exception {
        // Load XML document
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        dbf.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder = dbf.newDocumentBuilder();
        Document doc = builder.parse(inputXml);

        // Load the keystore (PKCS12)
        KeyStore ks = KeyStore.getInstance("PKCS12");
        try (FileInputStream fis = new FileInputStream(pkcs12File)) {
            ks.load(fis, keyPassword.toCharArray());
        }

        // Get private key and certificate
        String alias = ks.aliases().nextElement();
        Key privateKey = ks.getKey(alias, keyPassword.toCharArray());
        X509Certificate cert = (X509Certificate) ks.getCertificate(alias);

        // Create the XML Signature factory
        XMLSignatureFactory sigFactory = XMLSignatureFactory.getInstance("DOM");

        // Find the element to sign (example: <DTE ID="DTE-34-22295">)

        // Find the <Documento> element
        /*NodeList list = doc.getElementsByTagName("Documento");
        if (list.getLength() == 0) {
            throw new Exception("No <Documento> element found in XML!");
        }
        Element elementToSign = (Element) list.item(0);*/

        Element root = doc.getDocumentElement();
        root.normalize();
        normalizeLineEndings(doc);
        Element elementToSign = root;


        // Get the ID attribute
        Attr idAttr = elementToSign.getAttributeNode("ID");
        if (idAttr == null) {
            throw new Exception("<Documento> element has no ID attribute!");
        }

        // Register the ID attribute properly (safe way)
        elementToSign.setIdAttributeNode(idAttr, true);


        // Extract its value
        String referenceId = idAttr.getValue();

        List<Transform> transforms = new ArrayList<>();
        transforms.add(sigFactory.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null));
        //transforms.add(sigFactory.newTransform(CanonicalizationMethod.INCLUSIVE, (TransformParameterSpec) null));

        // Create a Reference to the entire document (enveloped)
        Reference ref = sigFactory.newReference(
                "#"+referenceId, // <-- match your actual ID attribute
                sigFactory.newDigestMethod(DigestMethod.SHA1, null),
                transforms,
                null,
                null
        );

        // Create SignedInfo
        SignedInfo signedInfo = sigFactory.newSignedInfo(
                sigFactory.newCanonicalizationMethod(
                        CanonicalizationMethod.INCLUSIVE,
                        (C14NMethodParameterSpec) null
                ),
                sigFactory.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
                java.util.Collections.singletonList(ref)
        );

        // Create KeyInfo (contains the X.509 certificate)
        /*KeyInfoFactory kif = sigFactory.getKeyInfoFactory();
        X509Data x509Data = kif.newX509Data(java.util.Collections.singletonList(cert));
        KeyInfo keyInfo = kif.newKeyInfo(java.util.Collections.singletonList(x509Data));*/

        KeyInfoFactory kif = sigFactory.getKeyInfoFactory();
        List<XMLStructure> kiObjects = new ArrayList<>();

        // Add public key
        PublicKey publicKey = cert.getPublicKey();
        KeyValue keyValue = kif.newKeyValue(publicKey);
        kiObjects.add(keyValue);

        // Add certificate
        X509Data x509Data = kif.newX509Data(Collections.singletonList(cert));
        kiObjects.add(x509Data);

        KeyInfo keyInfo = kif.newKeyInfo(kiObjects);

        // Create the XMLSignature
        XMLSignature signature = sigFactory.newXMLSignature(signedInfo, keyInfo);

        // Create a DOMSignContext (signing context)
        DOMSignContext dsc = new DOMSignContext(privateKey, doc.getDocumentElement());

        // Sign the document
        signature.sign(dsc);


        // Output the signed XML
        //TransformerFactory tf = TransformerFactory.newInstance();
        //Transformer trans = tf.newTransformer();
        //trans.setOutputProperty(OutputKeys.INDENT, "yes");
        //trans.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
        //trans.setOutputProperty(OutputKeys.METHOD, "xml");
        //trans.transform(new DOMSource(doc), new StreamResult(outputXml));

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer trans = tf.newTransformer();
        trans.setOutputProperty(OutputKeys.METHOD, "xml");
        trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        trans.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");

        // Do NOT indent — that can break signatures or add empty lines
        trans.setOutputProperty(OutputKeys.INDENT, "no");

        // Perform the transformation
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        trans.transform(new DOMSource(doc), new StreamResult(baos));

        // Convert to string safely, removing only carriage return entities
        String xmlClean = baos.toString("ISO-8859-1")
                //.replace("&#13;", "")
                .replaceAll("><", ">\n<");
        //.replaceAll(" standalone=\"no\"","")
        //.replaceAll("</Signature>\n" + "</DTE>","</Signature></DTE>");
        //.replaceAll("[ \\t]+\\r\\n", ""); // only remove encoded CRs
        //System.out.println(xmlClean);
        // Write back to file preserving your indentation
        Files.write(outputXml.toPath(), xmlClean.getBytes("ISO-8859-1"));
    }

    public static byte[] insertLineBreaks(byte[] data) {
        if (data == null) {
            return new byte[0];
        }

        // Number of line breaks to insert
        int breaks = data.length / 64;
        byte[] result = new byte[data.length + breaks];

        int pos = 0;
        for (int i = 0; i < data.length; i++) {
            result[pos++] = data[i];
            if ((i + 1) % 64 == 0) {
                result[pos++] = (byte) '\n'; // insert LF
            }
        }
        return result;
    }



    public static DTEDefType makeDTE(DTEDefType.Documento documento, SignatureType signature) {
        DTEDefType dte = new DTEDefType();
        dte.setDocumento(documento);
        dte.setSignature(signature);
        dte.setVersion(BigDecimal.valueOf(1.0));
        return dte;
    }

    public static EnvioDTE.SetDTE.Caratula makeCaratula(String rutEnvia, String rutEmisor, String rutReceptor,
                                                        /*XMLGregorianCalendar fchResol,*/ int nroResol,
                                                        /*XMLGregorianCalendar tmstFirmaEnv,*/
                                                        List<EnvioDTE.SetDTE.Caratula.SubTotDTE> subTotDTE) throws DatatypeConfigurationException {
        EnvioDTE.SetDTE.Caratula caratula = new EnvioDTE.SetDTE.Caratula();
        // Rut Contribuyente Emisor de los DTE
        caratula.setRutEmisor(rutEmisor);
        // Rut Persona que envía los DTE
        caratula.setRutEnvia(rutEnvia);
        // Rut Contribuyente Receptor de los DTE
        caratula.setRutReceptor(rutReceptor);
        GregorianCalendar calendar = new GregorianCalendar(2002, GregorianCalendar.OCTOBER,20);
        GregorianCalendar calendarActual = new GregorianCalendar();
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        xmlDate.setTimezone( DatatypeConstants.FIELD_UNDEFINED );
        xmlDate.setMillisecond( DatatypeConstants.FIELD_UNDEFINED );
        // Fecha Resolución SII que autoriza al emisor
        caratula.setFchResol(xmlDate);
        // Nº de resolución SII que autoriza al emisor
        caratula.setNroResol(BigInteger.valueOf(nroResol));
        XMLGregorianCalendar xmlDate2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendarActual);
        xmlDate2.setTimezone( DatatypeConstants.FIELD_UNDEFINED );
        xmlDate2.setMillisecond( DatatypeConstants.FIELD_UNDEFINED );
        // Fecha y hora de firma del envío AAAAMMDDHHMMSS
        caratula.setTmstFirmaEnv(xmlDate2);
        // Uno o más Subtotales por tipo de DTE
        caratula.setSubTotDTE(subTotDTE);
        caratula.setVersion(BigDecimal.valueOf(1.0));
        return caratula;
    }

    public static EnvioDTE.SetDTE.Caratula.SubTotDTE makeSubTotDTE(int tpoDTE,int nroDTE) {
        EnvioDTE.SetDTE.Caratula.SubTotDTE subTot =  new EnvioDTE.SetDTE.Caratula.SubTotDTE();
        subTot.setTpoDTE(BigInteger.valueOf(tpoDTE));
        subTot.setNroDTE(BigInteger.valueOf(nroDTE));
        return subTot;
    }

    public static EnvioDTE.SetDTE makeSetDTE(EnvioDTE.SetDTE.Caratula caratula,List<SiiBoleta.DTEDefType> DteList,String id){
        EnvioDTE.SetDTE  setDTE = new EnvioDTE.SetDTE();
        setDTE.setCaratula(caratula);
        setDTE.setDteList(DteList);
        setDTE.setID(id);
        return setDTE;
    }

    public static EnvioDTE makeEnvioDTE(EnvioDTE.SetDTE setDTE, SignatureType signature){
        EnvioDTE envioDTE = new EnvioDTE();
        envioDTE.setSetDTE(setDTE);
        envioDTE.setSignature(signature);
        envioDTE.setVersion(BigDecimal.valueOf(1.0));
        return envioDTE;
    }

    public static void formatKeyValueElements(Document doc, int lineLength,int index) {
        NodeList modulusNodes = doc.getElementsByTagNameNS("*", "Modulus");
        NodeList exponentNodes = doc.getElementsByTagNameNS("*", "Exponent");
        NodeList X509CertificateNodes = doc.getElementsByTagNameNS("*", "X509Certificate");
        NodeList SignatureValue = doc.getElementsByTagNameNS("*", "SignatureValue");
        //NodeList FRMT = doc.getElementsByTagNameNS("*", "FRMT");

        formatBase64Nodes(modulusNodes, lineLength,doc,index,0);
        Fix(exponentNodes,index,"            ");
        Fix(SignatureValue,index,"");
        formatBase64Nodes(X509CertificateNodes, lineLength,doc,index,1);
        //FRMTFix(FRMT, lineLength,doc,index);
    }

    public static void fixKeyValueElements(Document doc,int index) {
        NodeList modulusNodes = doc.getElementsByTagNameNS("*", "Modulus");
        NodeList exponentNodes = doc.getElementsByTagNameNS("*", "Exponent");
        NodeList X509CertificateNodes = doc.getElementsByTagNameNS("*", "X509Certificate");
        NodeList SignatureValue = doc.getElementsByTagNameNS("*", "SignatureValue");
        NodeList FRMT = doc.getElementsByTagNameNS("*", "FRMT");

        Fix2nd(modulusNodes,index);
        Fix2nd(exponentNodes,index);
        Fix2nd(SignatureValue,index);
        Fix2nd(X509CertificateNodes,index);
        Fix2nd(FRMT,index);
    }

    private static void formatBase64Nodes(NodeList nodes, int lineLength,Document document,int index,int type) {
        Node node = nodes.item(index);
        String indent="                ";
        if(type==0) {
            indent=indent+"    ";
        }
        String base64 = node.getTextContent().replaceAll("\\s+", "");
        String formatted = insertLineBreaks(base64, lineLength, indent);
        //node.setTextContent(formatted);
        node.setTextContent(""); // Clear existing
        for (String line : formatted.split("\n")) {
            Text lineNode = document.createTextNode(line);
            node.appendChild(lineNode);
            node.appendChild(document.createTextNode("\n")); // Explicit line break
        }
        node.appendChild(document.createTextNode(indent));
    }
    public static void Fix(NodeList nodes,int index,String extra) {
        Node node = nodes.item(index);
        node.setTextContent("\n            " + extra + node.getTextContent() + "\n        " + extra);
    }
    public static void Fix2nd(NodeList nodes,int index) {
        Node node = nodes.item(index);
        node.setTextContent(node.getTextContent().replace("\n","\n    "));
    }

    private static void FRMTFixSig(NodeList nodes, int lineLength,Document document) {
        Node node = nodes.item(0);
        String indent="        ";
        String base64 = node.getTextContent().replaceAll("\\s+", "");
        String formatted = insertLineBreaks(base64, lineLength, indent);
        //node.setTextContent(formatted);
        node.setTextContent(""); // Clear existing
        for (String line : formatted.split("\n")) {
            Text lineNode = document.createTextNode(line);
            node.appendChild(lineNode);
            node.appendChild(document.createTextNode("\n")); // Explicit line break
        }
        node.appendChild(document.createTextNode(indent));
    }

    public static void FRMTFix(NodeList nodes, int lineLength,Document document,int index) {
        Node node = nodes.item(0);
        if(index==0){
            String indent="            ";
            String base64 = node.getTextContent().replaceAll("\\s+", "");
            String formatted = insertLineBreaks(base64, lineLength, indent);
            //node.setTextContent(formatted);
            node.setTextContent(""); // Clear existing
            for (String line : formatted.split("\n")) {
                Text lineNode = document.createTextNode(line);
                node.appendChild(lineNode);
                node.appendChild(document.createTextNode("\n")); // Explicit line break
            }
            node.appendChild(document.createTextNode(indent));
        }
        else{
            node.setTextContent(node.getTextContent().replace("\n","\n        "));
        }
    }

    private static void formatBase64Nodes2(NodeList nodes, int lineLength,Document document,int index,int type) {
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            String indent="";
            if(i==0){
                if(index==0){
                    indent="                ";
                    if(type==0) {
                        indent=indent+"    ";
                    }else if(type==1) {
                    }
                    String base64 = node.getTextContent().replaceAll("\\s+", "");
                    String formatted = insertLineBreaks(base64, lineLength, indent);
                    //node.setTextContent(formatted);
                    node.setTextContent(""); // Clear existing
                    for (String line : formatted.split("\n")) {
                        Text lineNode = document.createTextNode(line);
                        node.appendChild(lineNode);
                        node.appendChild(document.createTextNode("\n")); // Explicit line break
                    }
                    node.appendChild(document.createTextNode(indent));
                } else if (index==1) {
                    if(type==0) {
                        node.setTextContent(node.getTextContent().replace("\n","\n        "));
                    }else if(type==1) {
                        node.setTextContent(node.getTextContent().replace("\n","\n        "));
                    }
                }
            } else if (i==1) {
                indent="                ";
                if(type==0) {
                    indent=indent+"    ";
                }else if(type==1) {
                    indent=indent+"";
                }
                String base64 = node.getTextContent().replaceAll("\\s+", "");
                String formatted = insertLineBreaks(base64, lineLength, indent);
                //node.setTextContent(formatted);
                node.setTextContent(""); // Clear existing
                for (String line : formatted.split("\n")) {
                    Text lineNode = document.createTextNode(line);
                    node.appendChild(lineNode);
                    node.appendChild(document.createTextNode("\n")); // Explicit line break
                }
                node.appendChild(document.createTextNode(indent));
            }
        }
    }

    private static String insertLineBreaks(String base64, int lineLength, String indent) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < base64.length(); i += lineLength) {
            int end = Math.min(i + lineLength, base64.length());
            if(end!=base64.length()){sb.append(base64, i, end).append("\n").append(indent).append("    ");}
            else sb.append(base64, i, end);
        }
        return sb.toString();
    }

//                    //
}
