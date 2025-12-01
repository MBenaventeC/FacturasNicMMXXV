package SiiBoleta;

import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;

public class DA {

    /**
     * Creates DA with given values and returns it
     * @param re
     * @param rs
     * @param td
     * @param rng
     * @param fa
     * @param rsapk
     * @param idk
     * @return
     */
    public static DTEDefType.Documento.TED.DD.CAF.DA makeDA(String re,
                                              String rs,
                                              int td,
                                              DTEDefType.Documento.TED.DD.CAF.DA.RNG rng,
                                              XMLGregorianCalendar fa,
                                              DTEDefType.Documento.TED.DD.CAF.DA.RSAPK rsapk,
                                              long idk) {

        DTEDefType.Documento.TED.DD.CAF.DA da = new DTEDefType.Documento.TED.DD.CAF.DA();


        da.setRE(re);
        da.setRS(rs);
        da.setTD(BigInteger.valueOf(td));
        da.setRNG(rng);
        da.setFA(fa);
        da.setRSAPK(rsapk);
        da.setIDK(idk);

        return da;
    }
}
