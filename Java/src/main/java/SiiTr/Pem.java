package SiiTr;

import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.pkcs.RSAPrivateKey;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pem {

    // Llamar esto una sola vez (por ejemplo en main) para registrar el provider.
    public static void ensureBC() {
        if (Security.getProvider("BC") == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }

    /* ===================== API PRINCIPAL ===================== */

    /** Carga la llave privada desde el CAF (XML) */
    public static PrivateKey loadPrivateKeyFromCaf(Path cafXmlPath) throws Exception {
        String xml = Files.readString(cafXmlPath, StandardCharsets.UTF_8);
        String pem = extractTagInnerPem(xml, "RSASK");     // solo el PEM interno
        return parsePrivateKeySmart(pem);                   // PKCS#1 o PKCS#8
    }

    /** Carga la llave pública desde el CAF (XML) */
    public static PublicKey loadPublicKeyFromCaf(Path cafXmlPath) throws Exception {
        String xml = Files.readString(cafXmlPath, StandardCharsets.UTF_8);
        String pem = extractTagInnerPem(xml, "RSAPUBK");   // solo el PEM interno
        return parsePublicKeyFromPem(pem);
    }

    /** Verifica que privada y pública correspondan comparando el modulus (n). */
    public static boolean privateMatchesPublic(PrivateKey priv, PublicKey pub) {
        RSAPrivateCrtKey rsaPriv = (RSAPrivateCrtKey) priv;
        RSAPublicKey rsaPub = (RSAPublicKey) pub;
        return rsaPriv.getModulus().equals(rsaPub.getModulus());
    }

    /* ===================== EXTRACCIÓN DEL XML ===================== */

    /** Extrae SOLO el contenido interno PEM dentro de <TAG> ... </TAG> (sin las etiquetas XML). */
    private static String extractTagInnerPem(String xml, String tagName) {
        Pattern p = Pattern.compile("<" + tagName + "[^>]*>\\s*([\\s\\S]*?)\\s*</" + tagName + ">", Pattern.DOTALL);
        Matcher m = p.matcher(xml);
        if (!m.find()) {
            throw new IllegalArgumentException("No se encontró <" + tagName + "> en el CAF");
        }
        return normalizePemText(m.group(1));
    }

    private static String normalizePemText(String pem) {
        return pem.replace("\uFEFF", "")
                .replace("\r\n", "\n")
                .replace("\r", "\n")
                .trim() + "\n";
    }

    /* ===================== PARSEO DE CLAVES ===================== */

    /** Detecta PKCS#1 o PKCS#8 y retorna PrivateKey. Robusto ante PEM “caprichosos”. */
    public static PrivateKey parsePrivateKeySmart(String pemText) throws Exception {
        ensureBC();

        // 1) Intento directo con PEMParser (acepta PKCS#1 y PKCS#8)
        try (PEMParser parser = new PEMParser(new StringReader(pemText))) {
            Object obj = parser.readObject();
            if (obj != null) {
                JcaPEMKeyConverter conv = new JcaPEMKeyConverter().setProvider("BC");
                if (obj instanceof PEMKeyPair) {                 // PKCS#1 (RSA PRIVATE KEY)
                    return conv.getKeyPair((PEMKeyPair) obj).getPrivate();
                } else if (obj instanceof PrivateKeyInfo) {      // PKCS#8 (PRIVATE KEY)
                    return conv.getPrivateKey((PrivateKeyInfo) obj);
                }
            }
        } catch (Exception ignore) {
            // Seguimos al plan B
        }

        // 2) Plan B: inspeccionar DER decodificando el base64 del cuerpo
        String base64 = pemBodyBase64(pemText);
        byte[] der = Base64.getDecoder().decode(base64);
        ASN1Sequence seq = ASN1Sequence.getInstance(der);

        // Heurística: PKCS#8 = SEQUENCE { version, AlgorithmIdentifier(SEQUENCE), OCTET STRING }
        boolean looksPkcs8 = seq.size() >= 3 && (seq.getObjectAt(1) instanceof ASN1Sequence);
        JcaPEMKeyConverter conv = new JcaPEMKeyConverter().setProvider("BC");
        if (looksPkcs8) {
            PrivateKeyInfo pki = PrivateKeyInfo.getInstance(seq);
            return conv.getPrivateKey(pki);
        } else {
            RSAPrivateKey rsa = RSAPrivateKey.getInstance(seq); // PKCS#1
            PrivateKeyInfo pki = new PrivateKeyInfo(
                    new AlgorithmIdentifier(PKCSObjectIdentifiers.rsaEncryption, DERNull.INSTANCE),
                    rsa);
            return conv.getPrivateKey(pki);
        }
    }

    /** Parsea una llave pública RSA desde un PEM 'BEGIN PUBLIC KEY' (X.509 SubjectPublicKeyInfo). */
    public static PublicKey parsePublicKeyFromPem(String pem) throws Exception {
        String base64 = pemBodyBase64(pem);
        byte[] der = Base64.getDecoder().decode(base64);
        X509EncodedKeySpec x509 = new X509EncodedKeySpec(der);
        return KeyFactory.getInstance("RSA").generatePublic(x509);
    }

    private static String pemBodyBase64(String pem) {
        return normalizePemText(pem)
                .replaceAll("-----BEGIN [^-]+-----", "")
                .replaceAll("-----END [^-]+-----", "")
                .replaceAll("\\s+", "");
    }

    /* ===================== DEMO ===================== */

    public static void main(String[] args) throws Exception {
        ensureBC();

        Path caf = Path.of("C:/universidad/proyecto_de_software/FacturasNicMMXXV/Java/Autorizacion.xml");

        PrivateKey priv = loadPrivateKeyFromCaf(caf);
        PublicKey  pub  = loadPublicKeyFromCaf(caf);

        boolean ok = privateMatchesPublic(priv, pub);
        System.out.println("Privada == Pública: " + (ok ? "COINCIDEN" : "NO COINCIDEN"));

    }
}
