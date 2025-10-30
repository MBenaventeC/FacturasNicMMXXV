package SiiBoleta;

import javax.xml.crypto.MarshalException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.*;
import java.io.FileInputStream;
import java.security.cert.X509Certificate;

import javax.xml.namespace.QName;
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

import SIIEnvio.EnvioDTE;
import SiiSignature.SignatureType;
import jakarta.xml.bind.*;
import org.bouncycastle.operator.OperatorCreationException;
import org.w3c.dom.*;
import org.w3c.dom.Element;

public class DTEMakers {
    public static DTEDefType.Documento.Encabezado.IdDoc makeIdDoc
            (int folio,
             int indServicio,
             int fmaPago,
             MedioPagoType medioPago
            ) throws DatatypeConfigurationException {
        DTEDefType.Documento.Encabezado.IdDoc  idDoc = new DTEDefType.Documento.Encabezado.IdDoc();
        idDoc.setTipoDTE(new BigInteger("34"));
        idDoc.setFolio(BigInteger.valueOf(folio));
        GregorianCalendar calendar = new GregorianCalendar();
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        xmlDate.setTimezone( DatatypeConstants.FIELD_UNDEFINED );
        idDoc.setFchEmis(xmlDate);
        idDoc.setIndServicio(BigInteger.valueOf(indServicio));
        idDoc.setFmaPago(BigInteger.valueOf(fmaPago));
        idDoc.setFchCancel(xmlDate);
        idDoc.setMedioPago(medioPago);
        idDoc.setFchVenc(xmlDate);
        return idDoc;
    }

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

    public static DTEDefType.Documento.Encabezado.Totales makeTotales
            (int total){
        DTEDefType.Documento.Encabezado.Totales totales = new DTEDefType.Documento.Encabezado.Totales();
        totales.setMntExe(BigInteger.valueOf(total));//totales.setMntExe(mntExe);
        totales.setMntTotal(BigInteger.valueOf(total));//totales.setMntTotal(mntTotal);
        return totales;
    }

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
            double prcItem){
        DTEDefType.Documento.Detalle detalle = new DTEDefType.Documento.Detalle();
        detalle.setNroLinDet(nroLinDet); //Cantidad de lineas de detalle x es el numero de esta linea en especifico
        detalle.setNmbItem("dominio "+nmbItem); //Nombre del dominio, debe empezar por "dominio "
        detalle.setQtyItem(BigDecimal.valueOf(1));// cantidad del item
        detalle.setPrcItem(BigDecimal.valueOf(prcItem));// precio del item
        detalle.setMontoItem(BigDecimal.valueOf(1).multiply(BigDecimal.valueOf(prcItem)).toBigInteger());//qty*prc
        return detalle;
    }

    public static DTEDefType.Documento makeDocumento(DTEDefType.Documento.Encabezado encabezado, DTEDefType.Documento.Detalle detalle,DTEDefType.Documento.TED ted,String id) throws DatatypeConfigurationException {
        DTEDefType.Documento documento = new DTEDefType.Documento();
        documento.setEncabezado(encabezado);
        documento.initializeDetalle();
        documento.addDetalle(detalle);
        GregorianCalendar calendar = new GregorianCalendar();
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        documento.setTmstFirma(xmlDate);
        documento.setID(id);
        documento.setTED(ted);
        return documento;
    }

    public static SignatureType makeSignature(DTEDefType.Documento documento) throws Exception {
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
                new QName(/*"http://www.sii.cl/SiiDte",*/"Documento"),
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
        KeyInfo ki = kif.newKeyInfo(Collections.singletonList(kif.newX509Data(Collections.singletonList(cert))));*/

        //7. Sign the Document
        /*DOMSignContext dsc = new DOMSignContext(pk, doc.getDocumentElement());
        XMLSignature signature = fac.newXMLSignature(si, ki);
        signature.sign(dsc);*/
        //DTEMakers.formatKeyValueElements(doc,64,0);
        NodeList FRMT = doc.getElementsByTagNameNS("*", "FRMT");
        FRMTFixSig(FRMT, 64,doc);

        //LyTHVELKLU0aDP+MYHjuYmBsKXU=

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
        Element signatureElement = (Element) sigList.item(0);

        Unmarshaller unmarshaller = JAXBContext.newInstance(SignatureType.class).createUnmarshaller();
        SignatureType signatureJaxb = (SignatureType) unmarshaller.unmarshal(new DOMSource((Node) signatureElement));
        //DTEDefType dte = makeDTE(documento, signatureJaxb);           // your manually built SignatureType
        //signatureJaxb.setURI(documento.getID());

        return signatureJaxb;
    }

    public static void printNode(Node node) throws Exception {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");

        transformer.transform(new DOMSource(node), new StreamResult(System.out));
    }

    public static SignatureType makeSignatureEnv(Element documento) throws Exception {
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

        XmlGenerator.printNode(imported);

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

    public static DTEDefType makeDTE(DTEDefType.Documento documento, SignatureType signature) {
        DTEDefType dte = new DTEDefType();
        dte.setDocumento(documento);
        dte.setSignature(signature);
        dte.setVersion(BigDecimal.valueOf(1.0));
        return dte;
    }

    public static EnvioDTE.SetDTE.Caratula makeCaratula(String rutEnvia, String rutReceptor,
                                                        /*XMLGregorianCalendar fchResol,*/ int nroResol,
                                                        /*XMLGregorianCalendar tmstFirmaEnv,*/
                                                        List<EnvioDTE.SetDTE.Caratula.SubTotDTE> subTotDTE) throws DatatypeConfigurationException {
        EnvioDTE.SetDTE.Caratula caratula = new EnvioDTE.SetDTE.Caratula();
        caratula.setRutEmisor("60910000-1");
        caratula.setRutEnvia(rutEnvia);
        caratula.setRutReceptor(rutReceptor);
        GregorianCalendar calendar = new GregorianCalendar(2002, GregorianCalendar.OCTOBER,20);
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        xmlDate.setTimezone( DatatypeConstants.FIELD_UNDEFINED );
        caratula.setFchResol(xmlDate);
        caratula.setNroResol(BigInteger.valueOf(nroResol));
        XMLGregorianCalendar xmlDate2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        caratula.setTmstFirmaEnv(xmlDate2);
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
