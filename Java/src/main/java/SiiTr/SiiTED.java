package SiiTr;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.Signature;

import java.util.*;
import java.util.Base64;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

import com.itextpdf.barcodes.BarcodePDF417;

import java.io.StringReader;

/**
 * Esta clase contiene métodos necesarios para crear el bloque DD y FRMT necesarios para poder obtener el TED
 * y su representación en PDF417 como se indica en el documento "Instructivo Tecnico Factura Eléctronica", anexo 2.
 */
public class SiiTED {

    private static final Charset ISO_8859_1 = StandardCharsets.ISO_8859_1;

    /**
     * Este metodo construye el bloque DD
     * @param datos: Los datos que necesita el bloque DD para construirse
     * @param cafXml: String obtenido desde el XML de autorizacion. Es especificamente es toda la etiqueta CAF.
     * @return Retorna el bloque DD como string, para luego poder hashearlo en el metodo 'signDD'.
     */
    public static String buildDD(Map<String,String> datos, String cafXml) {
        // Campos requeridos según el instructivo
        String[] requeridos = {"RE","TD","F","FE","RR","RSR","MNT","IT1","TSTED"};
        for (String k : requeridos) {
            if (!datos.containsKey(k)) {
                throw new IllegalArgumentException("Falta el campo requerido: " + k);
            }
        }
        if (cafXml == null || cafXml.isBlank()) {
            throw new IllegalArgumentException("CAF vacío o nulo");
        }

        return "<DD>" +
                "<RE>" + datos.get("RE") + "</RE>" +
                "<TD>" + datos.get("TD") + "</TD>" +
                "<F>" + datos.get("F") + "</F>" +
                "<FE>" + datos.get("FE") + "</FE>" +
                "<RR>" + datos.get("RR") + "</RR>" +
                "<RSR>" + datos.get("RSR") + "</RSR>" +
                "<MNT>" + datos.get("MNT") + "</MNT>" +
                "<IT1>" + datos.get("IT1") + "</IT1>" +
                cafXml +
                "<TSTED>" + datos.get("TSTED") + "</TSTED>" +
                "</DD>";
    }

    /**
     * Firma (aplica hash) al string dd, que representa el bloque DD obtenido en el metodo buildDD
     * @param dd: String que representa el bloque DD construido
     * @param rsaPrivateKey: LLave privada ya cargada con anterioridad (recibe lo que entrega el metodo loadPkcs1Key)
     * @return La firma obtenida al aplicar SHA1 al string dd.
     * @throws Exception
     */
    public static String signDD(String dd, PrivateKey rsaPrivateKey) throws Exception {
        String ddMin = minificarEntreTags(dd); // quitar \s entre '><' (ver anexo 2.4)
        byte[] bytes = ddMin.getBytes(ISO_8859_1);

        Signature sig = Signature.getInstance("SHA1withRSA");
        sig.initSign(rsaPrivateKey);
        sig.update(bytes);
        byte[] firma = sig.sign();
        return Base64.getEncoder().encodeToString(firma); // sin saltos de línea
    }


    /**
     * Construye el TED final según como indica el SII.
     */
    public static String buildTED(String dd, String frmtBase64) {
        return "<TED version=\"1.0\">" + dd + "<FRMT algoritmo=\"SHA1withRSA\">" + frmtBase64 + "</FRMT></TED>";
    }


    /** Quita espacios/saltos entre '><' (según reglas del anexo 2.4 para el cálculo del digest/firma). */
    private static String minificarEntreTags(String xml) {
        // elimina espacios/tab/nuevas líneas entre cierre y apertura de tag
        return xml.replaceAll(">\\s+<", "><");
    }

    /** Metodo para cargar la llave privada, que luego será entregada a la funcion signDD. Es necesario
     * agregar la dependencia bouncycastle
     */
    public static PrivateKey loadPkcs1Key(String rsaskPem) throws Exception {
        try (PEMParser parser = new PEMParser(new StringReader(rsaskPem))) {
            Object obj = parser.readObject();
            return new JcaPEMKeyConverter().getKeyPair((PEMKeyPair) obj).getPrivate();
        }
    }


    /** Metodo que recibe el TED construido en el metodo buildTED y entrega la representacion
     * en PDF417, de tipo imagen Java AWT. (Es necesario dibujar la representacion en un pdf mas adelante).
     */
    public static java.awt.Image generateBarcode(String ted) throws UnsupportedEncodingException {

        // Generar el código PDF417
        BarcodePDF417 pdf417 = new BarcodePDF417();
        pdf417.setText(ted.getBytes("ISO-8859-1")); // Ojo con charset
        return pdf417.getImage();
    }


}
