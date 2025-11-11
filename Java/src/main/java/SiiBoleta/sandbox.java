package SiiBoleta;

import SIIEnvio.SetDTEGenerator;
import SIIEnvio.insertDTEs;
import SIIEnvio.signEnvio;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class sandbox {
    public static void main(String[] args) throws Exception {
        /*String baseContent = Files.readString(Path.of("out/envio.xml"), StandardCharsets.ISO_8859_1);

        baseContent = baseContent.replaceAll("\r","\rr")

                .replaceAll("\n","\nn");

        Files.writeString(Path.of("out/envioanalisis.xml"), baseContent, StandardCharsets.ISO_8859_1);*/
        byte[] bytes = Files.readAllBytes(Path.of("out/signedEnv.xml"));
        System.out.println("First 100 bytes: " + Arrays.toString(Arrays.copyOf(bytes, 100)));

    }
}
