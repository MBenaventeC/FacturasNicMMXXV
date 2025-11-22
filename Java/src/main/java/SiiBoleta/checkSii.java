package SiiBoleta;

import SIIEnvio.signEnvio;

public class checkSii {
    public static void main(String[] args) throws Exception {
        String SignedDTE = DTESign.Sign4("SIICheck","out/SIIDTE.xml","F60T33");
        String signedEnv = signEnvio.sign("out/SII.xml","out/SIIEnvCheck.xml","SetDoc");
    }
}
