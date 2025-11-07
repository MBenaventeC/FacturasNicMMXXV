package SIIEnvio;

import SiiBoleta.DTEMakers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class signEnvio {
    public static void main(String[] args) throws Exception {
        File inputXml = new File("out/envio.xml");
        File pkcs12 = new File("Java/certificado.pfx");
        Path filePath = Paths.get("password.txt");
        String password = Files.readString(filePath);
        File outputXml = new File("out/envioSigned.xml");
        //GgKBfGF01GXzoUt4ksLpKVNhFkk=
        DTEMakers.signXML(inputXml, pkcs12, password, outputXml);
    }
}
