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

// package SiiEnvio;

// import org.apache.commons.cli.*;

// import java.io.FileInputStream;
// import java.security.KeyStore;
// import java.security.PrivateKey;
// import java.security.cert.X509Certificate;
// import java.util.HashMap;

// import org.apache.xmlbeans.XmlOptions;

// import SiiEnvio.net.ConexionSii;
// import SiiEnvio.Utilities;
// import SiiEnvio.schemas.DTEDocument;
// import SiiEnvio.schemas.RESPUESTADocument;

// public class VerificaEstadoEnSII {

//     private static void printUsage(Options options) {
//         HelpFormatter formatter = new HelpFormatter();
//         formatter.printHelp("VerificaEstadoEnSII", options);
//     }

//     public static void main(String[] args) throws Exception {
//         // Crear opciones
//         Options options = new Options();
//         options.addOption("c", "cert", true, "Certificado digital PKCS12");
//         options.addOption("s", "password", true, "Contraseña del certificado");

//         // Parsear argumentos
//         CommandLineParser parser = new DefaultParser();
//         CommandLine cmd;
        
//         try {
//             cmd = parser.parse(options, args);
//         } catch (ParseException e) {
//             printUsage(options);
//             System.exit(2);
//             return;
//         }

//         String certS = cmd.getOptionValue("c");
//         String passS = cmd.getOptionValue("s");

//         if (certS == null || passS == null) {
//             printUsage(options);
//             System.exit(2);
//         }

//         String[] otherArgs = cmd.getArgs();

//         if (otherArgs.length != 1) {
//             printUsage(options);
//             System.exit(2);
//         }

//         HashMap<String, String> namespaces = new HashMap<String, String>();
//         namespaces.put("", "http://www.sii.cl/SiiDte");
//         XmlOptions opts = new XmlOptions();
//         opts.setLoadSubstituteNamespaces(namespaces);

//         DTEDocument doc = DTEDocument.Factory.parse(new FileInputStream(
//                 otherArgs[0]), opts);

//         ConexionSii con = new ConexionSii();

//         KeyStore ks = KeyStore.getInstance("PKCS12");
//         ks.load(new FileInputStream(certS), passS.toCharArray());
//         String alias = ks.aliases().nextElement();
//         System.out.println("Usando certificado " + alias
//                 + " del archivo PKCS12: " + certS);

//         X509Certificate x509 = (X509Certificate) ks.getCertificate(alias);
//         PrivateKey pKey = (PrivateKey) ks.getKey(alias, passS.toCharArray());

//         String token = con.getToken(pKey, x509);

//         System.out.println("Token: " + token);

//         String enviadorS = Utilities.getRutFromCertificate(x509);
        
//         RESPUESTADocument resp = con.getEstadoDTECertificacion(enviadorS, doc.getDTE()
//                 .getDocumento(), token);
//         opts.setSavePrettyPrintIndent(2);
//         opts.setSavePrettyPrint();
//         resp.save(System.out, opts);
//     }

// }
