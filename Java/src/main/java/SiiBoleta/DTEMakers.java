package SiiBoleta;

import javax.xml.crypto.MarshalException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.io.FileInputStream;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.*;
import javax.xml.crypto.dsig.spec.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;

import jakarta.xml.bind.*;
import org.bouncycastle.operator.OperatorCreationException;
import org.w3c.dom.Document;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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

    public static SignatureType makeSignature(DTEDefType.Documento documento) throws JAXBException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, KeyStoreException, IOException, UnrecoverableKeyException, CertificateException, MarshalException, XMLSignatureException, TransformerException, ParserConfigurationException, OperatorCreationException, KeyException {
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
        String password = "";

        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(new FileInputStream(rutaPFX), password.toCharArray());

        // Obtener el alias (normalmente el primero)
        String alias = ks.aliases().nextElement();

        // Obtener la clave privada y el certificado
        PrivateKey privateKey = (PrivateKey) ks.getKey(alias, password.toCharArray());
        X509Certificate cert = (X509Certificate) ks.getCertificate(alias);
        PublicKey publicKey = cert.getPublicKey();

        JAXBContext context = JAXBContext.newInstance(DTEDefType.Documento.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        Document doc = dbf.newDocumentBuilder().newDocument();

        // Marshal directly into the DOM
        JAXBElement<DTEDefType.Documento> jaxbElement = new JAXBElement<>(
                new QName("Documento"), DTEDefType.Documento.class, documento);
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
        Element signatureElement = (Element) sigList.item(0);

        Unmarshaller unmarshaller = JAXBContext.newInstance(SignatureType.class).createUnmarshaller();
        SignatureType signatureJaxb = (SignatureType) unmarshaller.unmarshal(new DOMSource((Node) signatureElement));
        //DTEDefType dte = makeDTE(documento, signatureJaxb);           // your manually built SignatureType
        //signatureJaxb.setURI(documento.getID());

        return signatureJaxb;
    }

    public static SignatureType makeSignatureEnv(EnvioDTE.SetDTE documento) throws JAXBException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, KeyStoreException, IOException, UnrecoverableKeyException, CertificateException, MarshalException, XMLSignatureException, TransformerException, ParserConfigurationException, OperatorCreationException, KeyException {
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
        String password = "";

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
        GregorianCalendar calendar = new GregorianCalendar();
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

    public static EnvioDTE makeEnvioDTE(EnvioDTE.SetDTE setDTE,SiiBoleta.SignatureType signature){
        EnvioDTE envioDTE = new EnvioDTE();
        envioDTE.setSetDTE(setDTE);
        envioDTE.setSignature(signature);
        envioDTE.setVersion(BigDecimal.valueOf(1.0));
        return envioDTE;
    }
}
