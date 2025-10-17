package SiiBoleta;

import java.math.BigInteger;

public class RNG {

    public static DTEDefType.Documento.TED.DD.CAF.DA.RNG makeRNG(int d,
                                                   int h) {

        DTEDefType.Documento.TED.DD.CAF.DA.RNG rng = new DTEDefType.Documento.TED.DD.CAF.DA.RNG();
        rng.setD(BigInteger.valueOf(d));
        rng.setH(BigInteger.valueOf(h));
        return rng;
    }
}
