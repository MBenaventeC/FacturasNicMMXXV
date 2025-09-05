package SiiBoleta;

import java.math.BigInteger;

public class RNG {

    public static DTEDefType.Documento.TED.DD.CAF.DA.RNG makeRNG(BigInteger d,
                                                   BigInteger h) {

        DTEDefType.Documento.TED.DD.CAF.DA.RNG rng = new DTEDefType.Documento.TED.DD.CAF.DA.RNG();
        rng.setD(d);
        rng.setH(h);
        return rng;
    }
}
