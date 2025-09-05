package SiiBoleta;

import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;

public class DD {

    public static DTEDefType.Documento.TED.DD makeDD(String re,
                                                     int td,
                                                     int f,
                                                     XMLGregorianCalendar fe,
                                                     String rr,
                                                     String rsr,
                                                     int mnt,
                                                     String it1,
                                                     DTEDefType.Documento.TED.DD.CAF caf,
                                                     XMLGregorianCalendar tsted) {

        DTEDefType.Documento.TED.DD dd = new DTEDefType.Documento.TED.DD();
        dd.setRE(re);
        dd.setTD(BigInteger.valueOf(td));
        dd.setF(BigInteger.valueOf(f));
        dd.setFE(fe);
        dd.setRR(rr);
        dd.setRSR(rsr);
        dd.setMNT(BigInteger.valueOf(mnt));
        dd.setIT1(it1);
        dd.setCAF(caf);
        dd.setTSTED(tsted);

        return dd;
    }
}
