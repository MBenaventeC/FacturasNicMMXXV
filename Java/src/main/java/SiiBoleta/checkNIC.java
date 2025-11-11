package SiiBoleta;

import SIIEnvio.signEnvio;

public class checkNIC {
    public static void main(String[] args) throws Exception {
        String SignedDTE = DTESign.Sign4("NICCheckA","out/NIC.xml","DTE-34-993263");
        String signedEnv = signEnvio.sign4("out/NICEnv.xml","out/NICEnvCheckA.xml","EnvDte-63130");
    }
}
