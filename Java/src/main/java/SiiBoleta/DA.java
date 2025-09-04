package SiiBoleta;

import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;

public class DA {

    DTEDefType.Documento.TED.DD.CAF.DA makeDA(String re,
                                              String rs,
                                              BigInteger td,
                                              DTEDefType.Documento.TED.DD.CAF.DA.RNG rng,
                                              XMLGregorianCalendar fa,
                                              DTEDefType.Documento.TED.DD.CAF.DA.RSAPK rsapk,
                                              DTEDefType.Documento.TED.DD.CAF.DA.DSAPK dsapk,
                                              long idk) {

        DTEDefType.Documento.TED.DD.CAF.DA da = new DTEDefType.Documento.TED.DD.CAF.DA();

        da.setRE(re);
        da.setRS(rs);
        da.setTD(td);
        da.setRNG(rng);
        da.setFA(fa);
        da.setRSAPK(rsapk);
        da.setDSAPK(dsapk);
        da.setIDK(idk);

        return da;
    }
}
