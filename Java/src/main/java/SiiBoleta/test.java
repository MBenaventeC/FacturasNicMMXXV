package SiiBoleta;

import SIIEnvio.SetDTEGenerator;
import SIIEnvio.envioGenerator;
import SIIEnvio.insertDTEs;
import SIIEnvio.signEnvio;

public class test {
    public static void exmain(String[] args) throws Exception {
        //Genera DTE
        //DTEGenerator.main(args);
        //firma DTE
        //DTESign.main(args);
        //Genera Envio
        envioGenerator.main(args);
        //Firma envio
        //signEnvio.main(args);
    }
    public static void main(String[] args) throws Exception {
        //System.setProperty("org.apache.xml.security.ignoreLineBreaks", "true");
        //Genera DTE
        String DTE = DTEGenerator.Generate("DTE");
        //firma DTE
        String SignedDTE = DTESign.Sign4("signedDTE",DTE,"DTE-34-22295");
        //Genera SetDTE vacío
        String SetDTE = SetDTEGenerator.Generate("SetDTE");
        //inserta DTEs y añade a envíoDTE
        String envio = insertDTEs.Insert(SetDTE,SignedDTE,"envio");
        //Firma envio
        String signedEnv = signEnvio.sign4(envio,"out/signedEnv.xml","EnvDte-63130");

        System.out.println("Envio DTE firmado: "+ signedEnv);
    }
}
