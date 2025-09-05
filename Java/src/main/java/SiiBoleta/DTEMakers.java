package SiiBoleta;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.GregorianCalendar;

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

    public static SignatureType makeSignature(DTEDefType.Documento documento){
        SignatureType signature = new SignatureType();

        /* INFO **/
        SignatureType.SignedInfo signedInfo = new SignatureType.SignedInfo();

        SignatureType.SignedInfo.CanonicalizationMethod canon = new SignatureType.SignedInfo.CanonicalizationMethod();
        canon.setAlgorithm("http://www.w3.org/TR/2001/REC-xml-c14n-20010315");

        SignatureType.SignedInfo.SignatureMethod signatureMethod = new SignatureType.SignedInfo.SignatureMethod();
        signatureMethod.setAlgorithm("http://www.w3.org/2000/09/xmldsig#rsa-sha1");

        SignatureType.SignedInfo.Reference reference = new SignatureType.SignedInfo.Reference();
        reference.setURI("#"+documento.getID());

        SignatureType.SignedInfo.Reference.DigestMethod digest =  new SignatureType.SignedInfo.Reference.DigestMethod();
        digest.setAlgorithm("http://www.w3.org/2000/09/xmldsig#sha1");

        reference.setDigestMethod(digest);
        /* FALTA CALCULAR EL DIGEST VALUE DE ACUERDO AL DOM
         */
        /*
        CAMBIAR POR EL CALCULO REAL DE DIGESTVALUE
         */
        reference.setDigestValue("SOyUNEjemplo".getBytes());

        signedInfo.setCanonicalizationMethod(canon);
        signedInfo.setSignatureMethod(signatureMethod);
        signedInfo.setReference(reference);

        signature.setSignedInfo(signedInfo);

        // SignatureValue
        /*
        CAMBIAR POR EL CALCULO REAL DE SIGNATURE VALUE
         */
        signature.setSignatureValue("SoyUnaFirmaSuperSecreta".getBytes());

        // KeyInfo
        SignatureType.KeyInfo keyInfo = new SignatureType.KeyInfo();
        SignatureType.KeyInfo.KeyValue keyValue = new SignatureType.KeyInfo.KeyValue();

        SignatureType.KeyInfo.KeyValue.RSAKeyValue rsaKeyValue = new SignatureType.KeyInfo.KeyValue.RSAKeyValue();

        /*
        CAMBIAR POR EL CALCULO REAL DE MODULUS
         */
        rsaKeyValue.setModulus("TIENESQUECAMBIARELMODULUS".getBytes());
        /*
        CAMBIAR POR EL CALCULO REAL DE EXPONENT
         */
        rsaKeyValue.setExponent("TIENESQUECAMBIARELEXPONENT".getBytes());

        keyValue.setRSAKeyValue(rsaKeyValue);
        keyInfo.setKeyValue(keyValue);

        SignatureType.KeyInfo.X509Data x509Data = new SignatureType.KeyInfo.X509Data();
        /*
        CAMBIAR POR EL CALCULO REAL DE X509
         */
        x509Data.setX509Certificate("SoyUnEjemplo".getBytes());



        signature.setKeyInfo(keyInfo);
        signature.keyInfo.setX509Data(x509Data);

        return signature;
    };

    public static DTEDefType makeDTE(DTEDefType.Documento documento, SignatureType signature) {
        DTEDefType dte = new DTEDefType();
        dte.setDocumento(documento);
        dte.setSignature(signature);
        return dte;
    }
}
