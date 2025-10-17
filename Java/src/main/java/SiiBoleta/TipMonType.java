
package SiiBoleta;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TipMonType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <pre>
 * &lt;simpleType name="TipMonType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;maxLength value="15"/&gt;
 *     &lt;enumeration value="BOLIVAR"/&gt;
 *     &lt;enumeration value="BOLIVIANO"/&gt;
 *     &lt;enumeration value="CHELIN"/&gt;
 *     &lt;enumeration value="CORONA DIN"/&gt;
 *     &lt;enumeration value="CORONA NOR"/&gt;
 *     &lt;enumeration value="CORONA SC"/&gt;
 *     &lt;enumeration value="CRUZEIRO REAL"/&gt;
 *     &lt;enumeration value="DIRHAM"/&gt;
 *     &lt;enumeration value="DOLAR AUST"/&gt;
 *     &lt;enumeration value="DOLAR CAN"/&gt;
 *     &lt;enumeration value="DOLAR HK"/&gt;
 *     &lt;enumeration value="DOLAR NZ"/&gt;
 *     &lt;enumeration value="DOLAR SIN"/&gt;
 *     &lt;enumeration value="DOLAR TAI"/&gt;
 *     &lt;enumeration value="DOLAR USA"/&gt;
 *     &lt;enumeration value="DRACMA"/&gt;
 *     &lt;enumeration value="ESCUDO"/&gt;
 *     &lt;enumeration value="EURO"/&gt;
 *     &lt;enumeration value="FLORIN"/&gt;
 *     &lt;enumeration value="FRANCO BEL"/&gt;
 *     &lt;enumeration value="FRANCO FR"/&gt;
 *     &lt;enumeration value="FRANCO SZ"/&gt;
 *     &lt;enumeration value="GUARANI"/&gt;
 *     &lt;enumeration value="LIBRA EST"/&gt;
 *     &lt;enumeration value="LIRA"/&gt;
 *     &lt;enumeration value="MARCO AL"/&gt;
 *     &lt;enumeration value="MARCO FIN"/&gt;
 *     &lt;enumeration value="NUEVO SOL"/&gt;
 *     &lt;enumeration value="OTRAS MONEDAS"/&gt;
 *     &lt;enumeration value="PESETA"/&gt;
 *     &lt;enumeration value="PESO"/&gt;
 *     &lt;enumeration value="PESO CL"/&gt;
 *     &lt;enumeration value="PESO COL"/&gt;
 *     &lt;enumeration value="PESO MEX"/&gt;
 *     &lt;enumeration value="PESO URUG"/&gt;
 *     &lt;enumeration value="RAND"/&gt;
 *     &lt;enumeration value="RENMINBI"/&gt;
 *     &lt;enumeration value="RUPIA"/&gt;
 *     &lt;enumeration value="SUCRE"/&gt;
 *     &lt;enumeration value="YEN"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "TipMonType", namespace = "http://www.sii.cl/SiiDte")
@XmlEnum
public enum TipMonType {

    BOLIVAR("BOLIVAR"),
    BOLIVIANO("BOLIVIANO"),
    CHELIN("CHELIN"),
    @XmlEnumValue("CORONA DIN")
    CORONA_DIN("CORONA DIN"),
    @XmlEnumValue("CORONA NOR")
    CORONA_NOR("CORONA NOR"),
    @XmlEnumValue("CORONA SC")
    CORONA_SC("CORONA SC"),
    @XmlEnumValue("CRUZEIRO REAL")
    CRUZEIRO_REAL("CRUZEIRO REAL"),
    DIRHAM("DIRHAM"),
    @XmlEnumValue("DOLAR AUST")
    DOLAR_AUST("DOLAR AUST"),
    @XmlEnumValue("DOLAR CAN")
    DOLAR_CAN("DOLAR CAN"),
    @XmlEnumValue("DOLAR HK")
    DOLAR_HK("DOLAR HK"),
    @XmlEnumValue("DOLAR NZ")
    DOLAR_NZ("DOLAR NZ"),
    @XmlEnumValue("DOLAR SIN")
    DOLAR_SIN("DOLAR SIN"),
    @XmlEnumValue("DOLAR TAI")
    DOLAR_TAI("DOLAR TAI"),
    @XmlEnumValue("DOLAR USA")
    DOLAR_USA("DOLAR USA"),
    DRACMA("DRACMA"),
    ESCUDO("ESCUDO"),
    EURO("EURO"),
    FLORIN("FLORIN"),
    @XmlEnumValue("FRANCO BEL")
    FRANCO_BEL("FRANCO BEL"),
    @XmlEnumValue("FRANCO FR")
    FRANCO_FR("FRANCO FR"),
    @XmlEnumValue("FRANCO SZ")
    FRANCO_SZ("FRANCO SZ"),
    GUARANI("GUARANI"),
    @XmlEnumValue("LIBRA EST")
    LIBRA_EST("LIBRA EST"),
    LIRA("LIRA"),
    @XmlEnumValue("MARCO AL")
    MARCO_AL("MARCO AL"),
    @XmlEnumValue("MARCO FIN")
    MARCO_FIN("MARCO FIN"),
    @XmlEnumValue("NUEVO SOL")
    NUEVO_SOL("NUEVO SOL"),
    @XmlEnumValue("OTRAS MONEDAS")
    OTRAS_MONEDAS("OTRAS MONEDAS"),
    PESETA("PESETA"),
    PESO("PESO"),
    @XmlEnumValue("PESO CL")
    PESO_CL("PESO CL"),
    @XmlEnumValue("PESO COL")
    PESO_COL("PESO COL"),
    @XmlEnumValue("PESO MEX")
    PESO_MEX("PESO MEX"),
    @XmlEnumValue("PESO URUG")
    PESO_URUG("PESO URUG"),
    RAND("RAND"),
    RENMINBI("RENMINBI"),
    RUPIA("RUPIA"),
    SUCRE("SUCRE"),
    YEN("YEN");
    private final String value;

    TipMonType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TipMonType fromValue(String v) {
        for (TipMonType c: TipMonType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
