package SiiBoleta;

import SIIEnvio.SetDTEGenerator;
import SIIEnvio.envioGenerator;
import SIIEnvio.insertDTEs;
import SIIEnvio.signEnvio;
import org.w3c.dom.Document;
import SiiBoleta.SignXMLApache;

public class test2 {
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
        //Genera DTE
        Document DTE = DTEGenerator2.Generate("DTE");
        //firma DTE
        DTESign.Sign3("signedDTE",DTE);
        //Genera SetDTE vacío
        Document SetDTE = SetDTEGenerator.Generate2("SetDTE");
        //inserta DTEs y añade a envíoDTE
        insertDTEs.Insert2(SetDTE,DTE,"envio");
        //Firma envio
        String signedEnv = signEnvio.sign3(SetDTE,"signedEnv");

        //System.out.println("Envio DTE firmado: "+ signedEnv);
    }
}
