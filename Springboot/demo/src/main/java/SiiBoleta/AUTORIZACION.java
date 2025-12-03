package SiiBoleta;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Class that represents the "folios" file, used for unmarshalling
 */
@XmlRootElement(name = "AUTORIZACION")
public class AUTORIZACION {
    @XmlElement(name = "CAF")
    private DTEDefType.Documento.TED.DD.CAF caf;
    @XmlElement(name = "RSASK")
    private String RSASK;
    @XmlElement(name = "RSAPUBK")
    private byte[] RSAPUBK;

    /**
     * Get CAF from AUTORIZACION
     * @return
     */
    public DTEDefType.Documento.TED.DD.CAF getCAF() { return caf; }

    /**
     * Set CAF
     * @param caf
     */
    public void setCAF(DTEDefType.Documento.TED.DD.CAF caf) { this.caf = caf; }

    /**
     * Get RSASK from AUTORIZACION
     * @return
     */
    public String getRSASK() { return RSASK; }


}
