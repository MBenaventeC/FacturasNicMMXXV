/**
 * Copyright [2009] [NIC Labs]
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the 	License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or 
 * agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * 
 **/

package SiiEnvio;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

import SiiEnvio.net.ConexionSii;
import SiiEnvio.util.Utilities;
import jakarta.xml.bind.JAXBContext;
import SiiEnvio.util.RECEPCIONDTEDocument;
import SiiEnvio.util.RESPUESTADocument;

import org.apache.commons.cli.*;

import SiiBoleta.AUTORIZACION;
import SiiBoleta.DTEDefType;
import SiiBoleta.DTEMakers;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.io.IOException;



/**
 * Esta clase se encarga de enviar al SII un archivo XML que cumple con el formato de EnvioDTE. 
 */
public class EnviaDocumento {

    private static void printUsage(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("EnviaDocumento", options);
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {

        // Crear opciones
        Options options = new Options();
        options.addOption("c", "cert", true, "Certificado digital PKCS12");
        options.addOption("s", "password", true, "Contraseña del certificado");
        options.addOption("f", "compania", true, "RUT compañía");

        // Parsear argumentos
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;
        
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            printUsage(options);
            System.exit(2);
            return;
        }

        String certS = cmd.getOptionValue("c");
        String passS = cmd.getOptionValue("s");
        String compaS = cmd.getOptionValue("f");

        if (certS == null || passS == null || compaS == null) {
            printUsage(options);
            System.exit(2);
        }

        String[] otherArgs = cmd.getArgs();

        if (otherArgs.length != 1) {
            printUsage(options);
            System.exit(2);
        }

        ConexionSii con = new ConexionSii();

        // leo certificado y llave privada del archivo pkcs12
        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(new FileInputStream(certS), passS.toCharArray());
        String alias = ks.aliases().nextElement();
        System.out.println("Usando certificado " + alias
                + " del archivo PKCS12: " + certS);

        X509Certificate x509 = (X509Certificate) ks.getCertificate(alias);
        PrivateKey pKey = (PrivateKey) ks.getKey(alias, passS.toCharArray());

        String token = con.getToken(pKey, x509);
        System.out.println("Token: " + token);

        String enviadorS = Utilities.getRutFromCertificate(x509);
        
        System.out.println(enviadorS);

        // Envio con reintento
        RECEPCIONDTEDocument recp = null;
        int maxRetries = 3;
        Exception lastException = null;

        for (int i = 0; i < maxRetries; i++) {
            try {
                System.out.println("Intento " + (i + 1) + " de " + maxRetries + "...");
                
                // Si es retry, obtener nuevo token
                if (i > 0) {
                    System.out.println("Obteniendo nuevo token para retry...");
                    token = con.getToken(pKey, x509);
                    System.out.println("Nuevo token: " + token);
                }
                
                recp = con.uploadEnvioCertificacion(enviadorS, compaS,
                        new File(otherArgs[0]), token);
                
                System.out.println("Envio exitoso!");
                break;  // Éxito, salir del loop
                
            } catch (IOException e) {
                lastException = e;
                
                if (i < maxRetries - 1) {
                    System.err.println("Error en intento " + (i + 1) + ": " + e.getMessage());
                    System.err.println("Esperando " + (3 * (i + 1)) + " segundos antes de reintentar...");
                    Thread.sleep(3000 * (i + 1));  // Backoff: 3s, 6s, 9s
                } else {
                    System.err.println("Falló después de " + maxRetries + " intentos");
                    throw e;
                }
            }
        }

        if (recp == null) {
            throw new Exception("No se pudo enviar el documento al SII después de " + 
                maxRetries + " intentos", lastException);
        }

        System.out.println(recp.xmlText());

        // List<Object> trackId = recp.getRECEPCIONDTE().getTRACKIDOrDETAIL();
        // if (trackId.stream().anyMatch(e -> e instanceof Integer)){
        //     String token2 = con.getToken(pKey, x509);
        //     RESPUESTADocument estado = getEstadoDTECertificacion("20829287-0"); 
        // }

    }

}
