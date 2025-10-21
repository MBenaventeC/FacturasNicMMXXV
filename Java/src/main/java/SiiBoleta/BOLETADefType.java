
package SiiBoleta;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;

import SiiSignature.SignatureType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Boleta Electronica
 * 
 * <p>Java class for BOLETADefType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BOLETADefType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Documento"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Encabezado"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="IdDoc"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                     &lt;sequence&gt;
 *                                       &lt;element name="TipoDTE" type="{http://www.sii.cl/SiiDte}DTEType"/&gt;
 *                                       &lt;element name="Folio" type="{http://www.sii.cl/SiiDte}FolioType"/&gt;
 *                                       &lt;element name="FchEmis" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *                                       &lt;element name="IndServicio"&gt;
 *                                         &lt;simpleType&gt;
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
 *                                             &lt;enumeration value="1"/&gt;
 *                                             &lt;enumeration value="2"/&gt;
 *                                             &lt;enumeration value="3"/&gt;
 *                                             &lt;enumeration value="4"/&gt;
 *                                           &lt;/restriction&gt;
 *                                         &lt;/simpleType&gt;
 *                                       &lt;/element&gt;
 *                                       &lt;element name="IndMntNeto" minOccurs="0"&gt;
 *                                         &lt;simpleType&gt;
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *                                             &lt;enumeration value="2"/&gt;
 *                                           &lt;/restriction&gt;
 *                                         &lt;/simpleType&gt;
 *                                       &lt;/element&gt;
 *                                       &lt;element name="PeriodoDesde" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *                                       &lt;element name="PeriodoHasta" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *                                       &lt;element name="FchVenc" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *                                       &lt;element name="MedioPago" minOccurs="0"&gt;
 *                                         &lt;simpleType&gt;
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
 *                                             &lt;enumeration value="1"/&gt;
 *                                             &lt;enumeration value="2"/&gt;
 *                                             &lt;enumeration value="3"/&gt;
 *                                             &lt;enumeration value="4"/&gt;
 *                                             &lt;enumeration value="5"/&gt;
 *                                           &lt;/restriction&gt;
 *                                         &lt;/simpleType&gt;
 *                                       &lt;/element&gt;
 *                                     &lt;/sequence&gt;
 *                                   &lt;/restriction&gt;
 *                                 &lt;/complexContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="Emisor"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                     &lt;sequence&gt;
 *                                       &lt;element name="RUTEmisor" type="{http://www.sii.cl/SiiDte}RUTType"/&gt;
 *                                       &lt;element name="RznSocEmisor" minOccurs="0"&gt;
 *                                         &lt;simpleType&gt;
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                             &lt;maxLength value="100"/&gt;
 *                                           &lt;/restriction&gt;
 *                                         &lt;/simpleType&gt;
 *                                       &lt;/element&gt;
 *                                       &lt;element name="GiroEmisor" minOccurs="0"&gt;
 *                                         &lt;simpleType&gt;
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                             &lt;maxLength value="80"/&gt;
 *                                           &lt;/restriction&gt;
 *                                         &lt;/simpleType&gt;
 *                                       &lt;/element&gt;
 *                                       &lt;element name="CdgSIISucur" minOccurs="0"&gt;
 *                                         &lt;simpleType&gt;
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
 *                                             &lt;totalDigits value="9"/&gt;
 *                                           &lt;/restriction&gt;
 *                                         &lt;/simpleType&gt;
 *                                       &lt;/element&gt;
 *                                       &lt;element name="DirOrigen" minOccurs="0"&gt;
 *                                         &lt;simpleType&gt;
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                             &lt;maxLength value="70"/&gt;
 *                                           &lt;/restriction&gt;
 *                                         &lt;/simpleType&gt;
 *                                       &lt;/element&gt;
 *                                       &lt;element name="CmnaOrigen" minOccurs="0"&gt;
 *                                         &lt;simpleType&gt;
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                             &lt;maxLength value="20"/&gt;
 *                                           &lt;/restriction&gt;
 *                                         &lt;/simpleType&gt;
 *                                       &lt;/element&gt;
 *                                       &lt;element name="CiudadOrigen" minOccurs="0"&gt;
 *                                         &lt;simpleType&gt;
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                             &lt;maxLength value="20"/&gt;
 *                                           &lt;/restriction&gt;
 *                                         &lt;/simpleType&gt;
 *                                       &lt;/element&gt;
 *                                     &lt;/sequence&gt;
 *                                   &lt;/restriction&gt;
 *                                 &lt;/complexContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="Receptor"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                     &lt;sequence&gt;
 *                                       &lt;element name="RUTRecep" type="{http://www.sii.cl/SiiDte}RUTType"/&gt;
 *                                       &lt;element name="CdgIntRecep" minOccurs="0"&gt;
 *                                         &lt;simpleType&gt;
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                             &lt;maxLength value="20"/&gt;
 *                                           &lt;/restriction&gt;
 *                                         &lt;/simpleType&gt;
 *                                       &lt;/element&gt;
 *                                       &lt;element name="RznSocRecep" minOccurs="0"&gt;
 *                                         &lt;simpleType&gt;
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                             &lt;maxLength value="100"/&gt;
 *                                           &lt;/restriction&gt;
 *                                         &lt;/simpleType&gt;
 *                                       &lt;/element&gt;
 *                                       &lt;element name="Contacto" minOccurs="0"&gt;
 *                                         &lt;simpleType&gt;
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                             &lt;maxLength value="80"/&gt;
 *                                           &lt;/restriction&gt;
 *                                         &lt;/simpleType&gt;
 *                                       &lt;/element&gt;
 *                                       &lt;element name="CorreoRecep" type="{http://www.sii.cl/SiiDte}MailType" minOccurs="0"/&gt;
 *                                       &lt;element name="TelefonoRecep" type="{http://www.sii.cl/SiiDte}FonoType" minOccurs="0"/&gt;
 *                                       &lt;element name="DirRecep" minOccurs="0"&gt;
 *                                         &lt;simpleType&gt;
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                             &lt;maxLength value="70"/&gt;
 *                                           &lt;/restriction&gt;
 *                                         &lt;/simpleType&gt;
 *                                       &lt;/element&gt;
 *                                       &lt;element name="CmnaRecep" minOccurs="0"&gt;
 *                                         &lt;simpleType&gt;
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                             &lt;maxLength value="20"/&gt;
 *                                           &lt;/restriction&gt;
 *                                         &lt;/simpleType&gt;
 *                                       &lt;/element&gt;
 *                                       &lt;element name="CiudadRecep" minOccurs="0"&gt;
 *                                         &lt;simpleType&gt;
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                             &lt;maxLength value="20"/&gt;
 *                                           &lt;/restriction&gt;
 *                                         &lt;/simpleType&gt;
 *                                       &lt;/element&gt;
 *                                       &lt;element name="DirPostal" minOccurs="0"&gt;
 *                                         &lt;simpleType&gt;
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                             &lt;maxLength value="70"/&gt;
 *                                           &lt;/restriction&gt;
 *                                         &lt;/simpleType&gt;
 *                                       &lt;/element&gt;
 *                                       &lt;element name="CmnaPostal" minOccurs="0"&gt;
 *                                         &lt;simpleType&gt;
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                             &lt;maxLength value="20"/&gt;
 *                                           &lt;/restriction&gt;
 *                                         &lt;/simpleType&gt;
 *                                       &lt;/element&gt;
 *                                       &lt;element name="CiudadPostal" minOccurs="0"&gt;
 *                                         &lt;simpleType&gt;
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                             &lt;maxLength value="20"/&gt;
 *                                           &lt;/restriction&gt;
 *                                         &lt;/simpleType&gt;
 *                                       &lt;/element&gt;
 *                                     &lt;/sequence&gt;
 *                                   &lt;/restriction&gt;
 *                                 &lt;/complexContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="RUTProvSW" type="{http://www.sii.cl/SiiDte}RUTType" minOccurs="0"/&gt;
 *                             &lt;element name="Totales"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                     &lt;sequence&gt;
 *                                       &lt;element name="MntNeto" type="{http://www.sii.cl/SiiDte}MontoType" minOccurs="0"/&gt;
 *                                       &lt;element name="MntExe" type="{http://www.sii.cl/SiiDte}MontoType" minOccurs="0"/&gt;
 *                                       &lt;element name="IVA" type="{http://www.sii.cl/SiiDte}MontoType" minOccurs="0"/&gt;
 *                                       &lt;element name="MntTotal" type="{http://www.sii.cl/SiiDte}MontoType"/&gt;
 *                                       &lt;element name="MontoNF" type="{http://www.sii.cl/SiiDte}ValorType" minOccurs="0"/&gt;
 *                                       &lt;element name="TotalPeriodo" type="{http://www.sii.cl/SiiDte}ValorType" minOccurs="0"/&gt;
 *                                       &lt;element name="SaldoAnterior" type="{http://www.sii.cl/SiiDte}ValorType" minOccurs="0"/&gt;
 *                                       &lt;element name="VlrPagar" type="{http://www.sii.cl/SiiDte}ValorType" minOccurs="0"/&gt;
 *                                     &lt;/sequence&gt;
 *                                   &lt;/restriction&gt;
 *                                 &lt;/complexContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Detalle" maxOccurs="1000" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="NroLinDet"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
 *                                   &lt;minInclusive value="1"/&gt;
 *                                   &lt;maxInclusive value="1000"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="CdgItem" maxOccurs="5" minOccurs="0"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                     &lt;sequence&gt;
 *                                       &lt;element name="TpoCodigo"&gt;
 *                                         &lt;simpleType&gt;
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                             &lt;maxLength value="10"/&gt;
 *                                           &lt;/restriction&gt;
 *                                         &lt;/simpleType&gt;
 *                                       &lt;/element&gt;
 *                                       &lt;element name="VlrCodigo"&gt;
 *                                         &lt;simpleType&gt;
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                             &lt;maxLength value="35"/&gt;
 *                                           &lt;/restriction&gt;
 *                                         &lt;/simpleType&gt;
 *                                       &lt;/element&gt;
 *                                     &lt;/sequence&gt;
 *                                   &lt;/restriction&gt;
 *                                 &lt;/complexContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="IndExe" minOccurs="0"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
 *                                   &lt;enumeration value="1"/&gt;
 *                                   &lt;enumeration value="2"/&gt;
 *                                   &lt;enumeration value="6"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="ItemEspectaculo" minOccurs="0"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
 *                                   &lt;maxInclusive value="2"/&gt;
 *                                   &lt;totalDigits value="2"/&gt;
 *                                   &lt;enumeration value="01"/&gt;
 *                                   &lt;enumeration value="02"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="RUTMandante" type="{http://www.sii.cl/SiiDte}RUTType" minOccurs="0"/&gt;
 *                             &lt;element name="NmbItem"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                   &lt;maxLength value="80"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="InfoTicket" minOccurs="0"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                     &lt;sequence&gt;
 *                                       &lt;element name="FolioTicket"&gt;
 *                                         &lt;simpleType&gt;
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
 *                                             &lt;totalDigits value="6"/&gt;
 *                                           &lt;/restriction&gt;
 *                                         &lt;/simpleType&gt;
 *                                       &lt;/element&gt;
 *                                       &lt;element name="FchGenera"&gt;
 *                                         &lt;simpleType&gt;
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}dateTime"&gt;
 *                                             &lt;minInclusive value="2010-03-10T00:00:00"/&gt;
 *                                             &lt;maxInclusive value="2050-12-31T23:59:59"/&gt;
 *                                           &lt;/restriction&gt;
 *                                         &lt;/simpleType&gt;
 *                                       &lt;/element&gt;
 *                                       &lt;element name="NmbEvento"&gt;
 *                                         &lt;simpleType&gt;
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                             &lt;maxLength value="80"/&gt;
 *                                           &lt;/restriction&gt;
 *                                         &lt;/simpleType&gt;
 *                                       &lt;/element&gt;
 *                                       &lt;element name="TpoTicket"&gt;
 *                                         &lt;simpleType&gt;
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                             &lt;maxLength value="10"/&gt;
 *                                           &lt;/restriction&gt;
 *                                         &lt;/simpleType&gt;
 *                                       &lt;/element&gt;
 *                                       &lt;element name="CdgEvento"&gt;
 *                                         &lt;simpleType&gt;
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                             &lt;maxLength value="5"/&gt;
 *                                           &lt;/restriction&gt;
 *                                         &lt;/simpleType&gt;
 *                                       &lt;/element&gt;
 *                                       &lt;element name="FchEvento"&gt;
 *                                         &lt;simpleType&gt;
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}dateTime"&gt;
 *                                             &lt;minInclusive value="2010-03-10T00:00:00"/&gt;
 *                                             &lt;maxInclusive value="2050-12-31T23:59:59"/&gt;
 *                                           &lt;/restriction&gt;
 *                                         &lt;/simpleType&gt;
 *                                       &lt;/element&gt;
 *                                       &lt;element name="LugarEvento"&gt;
 *                                         &lt;simpleType&gt;
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                             &lt;maxLength value="80"/&gt;
 *                                           &lt;/restriction&gt;
 *                                         &lt;/simpleType&gt;
 *                                       &lt;/element&gt;
 *                                       &lt;element name="UbicEvento"&gt;
 *                                         &lt;simpleType&gt;
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                             &lt;maxLength value="20"/&gt;
 *                                           &lt;/restriction&gt;
 *                                         &lt;/simpleType&gt;
 *                                       &lt;/element&gt;
 *                                       &lt;element name="FilaUbicEvento" minOccurs="0"&gt;
 *                                         &lt;simpleType&gt;
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                             &lt;maxLength value="3"/&gt;
 *                                           &lt;/restriction&gt;
 *                                         &lt;/simpleType&gt;
 *                                       &lt;/element&gt;
 *                                       &lt;element name="AsntoUbicEvento" minOccurs="0"&gt;
 *                                         &lt;simpleType&gt;
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                             &lt;maxLength value="3"/&gt;
 *                                           &lt;/restriction&gt;
 *                                         &lt;/simpleType&gt;
 *                                       &lt;/element&gt;
 *                                     &lt;/sequence&gt;
 *                                   &lt;/restriction&gt;
 *                                 &lt;/complexContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="DscItem" minOccurs="0"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                   &lt;maxLength value="1000"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="QtyItem" minOccurs="0"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.sii.cl/SiiDte}Dec5Type"&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="UnmdItem" minOccurs="0"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                   &lt;maxLength value="4"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="PrcItem" minOccurs="0"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.sii.cl/SiiDte}Dec5Type"&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="DescuentoPct" minOccurs="0"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.sii.cl/SiiDte}PctType"&gt;
 *                                   &lt;minInclusive value="0.00"/&gt;
 *                                   &lt;maxInclusive value="100.00"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="DescuentoMonto" minOccurs="0"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"&gt;
 *                                   &lt;totalDigits value="18"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="RecargoPct" minOccurs="0"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.sii.cl/SiiDte}PctType"&gt;
 *                                   &lt;maxInclusive value="100.00"/&gt;
 *                                   &lt;minInclusive value="0.00"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="RecargoMonto" minOccurs="0"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"&gt;
 *                                   &lt;totalDigits value="18"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="MontoItem" type="{http://www.sii.cl/SiiDte}MontoType"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="SubTotInfo" maxOccurs="20" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="NroSTI"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
 *                                   &lt;maxInclusive value="20"/&gt;
 *                                   &lt;minInclusive value="1"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="GlosaSTI" minOccurs="0"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                   &lt;maxLength value="80"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="OrdenSTI" minOccurs="0"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
 *                                   &lt;maxInclusive value="99"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="SubTotNetoSTI" minOccurs="0"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.sii.cl/SiiDte}Dec1Type"&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="SubTotIVASTI" minOccurs="0"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.sii.cl/SiiDte}Dec1Type"&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="SubTotAdicSTI" type="{http://www.sii.cl/SiiDte}Dec1Type" minOccurs="0"/&gt;
 *                             &lt;element name="SubTotExeSTI" type="{http://www.sii.cl/SiiDte}Dec1Type" minOccurs="0"/&gt;
 *                             &lt;element name="ValSubtotSTI" type="{http://www.sii.cl/SiiDte}Dec1Type" minOccurs="0"/&gt;
 *                             &lt;element name="LineasDeta" maxOccurs="1000" minOccurs="0"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
 *                                   &lt;maxInclusive value="1000"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="DscRcgGlobal" maxOccurs="20" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="NroLinDR"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
 *                                   &lt;maxInclusive value="20"/&gt;
 *                                   &lt;minInclusive value="1"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="TpoMov"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                   &lt;enumeration value="D"/&gt;
 *                                   &lt;enumeration value="R"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="GlosaDR" minOccurs="0"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                   &lt;maxLength value="45"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="TpoValor"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                   &lt;enumeration value="%"/&gt;
 *                                   &lt;enumeration value="$"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="ValorDR" type="{http://www.sii.cl/SiiDte}Dec1Type"/&gt;
 *                             &lt;element name="IndExeDR" minOccurs="0"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
 *                                   &lt;enumeration value="1"/&gt;
 *                                   &lt;enumeration value="2"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Referencia" maxOccurs="40" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="NroLinRef"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
 *                                   &lt;maxInclusive value="40"/&gt;
 *                                   &lt;minInclusive value="1"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="TpoDocRef" minOccurs="0"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                   &lt;maxLength value="3"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="FolioRef" minOccurs="0"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                   &lt;maxLength value="18"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="CodRef" minOccurs="0"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                   &lt;maxLength value="18"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="RazonRef" minOccurs="0"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                   &lt;maxLength value="90"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="CodVndor" minOccurs="0"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                   &lt;maxLength value="8"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="CodCaja" minOccurs="0"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                   &lt;maxLength value="8"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="GeoRefEmision" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="LatitudEmision"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                   &lt;maxLength value="30"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="LongitudEmision"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                   &lt;maxLength value="30"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="SistemaReferencia"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
 *                                   &lt;maxInclusive value="9"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="TED"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="DD"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                     &lt;sequence&gt;
 *                                       &lt;element name="RE" type="{http://www.sii.cl/SiiDte}RUTType"/&gt;
 *                                       &lt;element name="TD" type="{http://www.sii.cl/SiiDte}DTEType"/&gt;
 *                                       &lt;element name="F" type="{http://www.sii.cl/SiiDte}FolioType"/&gt;
 *                                       &lt;element name="FE" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *                                       &lt;element name="RR" type="{http://www.sii.cl/SiiDte}RUTType"/&gt;
 *                                       &lt;element name="RSR"&gt;
 *                                         &lt;simpleType&gt;
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                             &lt;maxLength value="40"/&gt;
 *                                           &lt;/restriction&gt;
 *                                         &lt;/simpleType&gt;
 *                                       &lt;/element&gt;
 *                                       &lt;element name="MNT" type="{http://www.w3.org/2001/XMLSchema}unsignedLong"/&gt;
 *                                       &lt;element name="IT1"&gt;
 *                                         &lt;simpleType&gt;
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                             &lt;maxLength value="40"/&gt;
 *                                           &lt;/restriction&gt;
 *                                         &lt;/simpleType&gt;
 *                                       &lt;/element&gt;
 *                                       &lt;element name="CAF"&gt;
 *                                         &lt;complexType&gt;
 *                                           &lt;complexContent&gt;
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                               &lt;sequence&gt;
 *                                                 &lt;element name="DA"&gt;
 *                                                   &lt;complexType&gt;
 *                                                     &lt;complexContent&gt;
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                                         &lt;sequence&gt;
 *                                                           &lt;element name="RE" type="{http://www.sii.cl/SiiDte}RUTType"/&gt;
 *                                                           &lt;element name="RS"&gt;
 *                                                             &lt;simpleType&gt;
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                                                 &lt;maxLength value="40"/&gt;
 *                                                               &lt;/restriction&gt;
 *                                                             &lt;/simpleType&gt;
 *                                                           &lt;/element&gt;
 *                                                           &lt;element name="TD" type="{http://www.sii.cl/SiiDte}DTEType"/&gt;
 *                                                           &lt;element name="RNG"&gt;
 *                                                             &lt;complexType&gt;
 *                                                               &lt;complexContent&gt;
 *                                                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                                                   &lt;sequence&gt;
 *                                                                     &lt;element name="D" type="{http://www.sii.cl/SiiDte}FolioType"/&gt;
 *                                                                     &lt;element name="H" type="{http://www.sii.cl/SiiDte}FolioType"/&gt;
 *                                                                   &lt;/sequence&gt;
 *                                                                 &lt;/restriction&gt;
 *                                                               &lt;/complexContent&gt;
 *                                                             &lt;/complexType&gt;
 *                                                           &lt;/element&gt;
 *                                                           &lt;element name="FA" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *                                                           &lt;choice&gt;
 *                                                             &lt;element name="RSAPK"&gt;
 *                                                               &lt;complexType&gt;
 *                                                                 &lt;complexContent&gt;
 *                                                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                                                     &lt;sequence&gt;
 *                                                                       &lt;element name="M" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
 *                                                                       &lt;element name="E" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
 *                                                                     &lt;/sequence&gt;
 *                                                                   &lt;/restriction&gt;
 *                                                                 &lt;/complexContent&gt;
 *                                                               &lt;/complexType&gt;
 *                                                             &lt;/element&gt;
 *                                                             &lt;element name="DSAPK"&gt;
 *                                                               &lt;complexType&gt;
 *                                                                 &lt;complexContent&gt;
 *                                                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                                                     &lt;sequence&gt;
 *                                                                       &lt;element name="P" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
 *                                                                       &lt;element name="Q" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
 *                                                                       &lt;element name="G" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
 *                                                                       &lt;element name="Y" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
 *                                                                     &lt;/sequence&gt;
 *                                                                   &lt;/restriction&gt;
 *                                                                 &lt;/complexContent&gt;
 *                                                               &lt;/complexType&gt;
 *                                                             &lt;/element&gt;
 *                                                           &lt;/choice&gt;
 *                                                           &lt;element name="IDK" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *                                                         &lt;/sequence&gt;
 *                                                       &lt;/restriction&gt;
 *                                                     &lt;/complexContent&gt;
 *                                                   &lt;/complexType&gt;
 *                                                 &lt;/element&gt;
 *                                                 &lt;element name="FRMA"&gt;
 *                                                   &lt;complexType&gt;
 *                                                     &lt;simpleContent&gt;
 *                                                       &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;base64Binary"&gt;
 *                                                         &lt;attribute name="algoritmo" use="required" type="{http://www.w3.org/2001/XMLSchema}string" fixed="SHA1withRSA" /&gt;
 *                                                       &lt;/extension&gt;
 *                                                     &lt;/simpleContent&gt;
 *                                                   &lt;/complexType&gt;
 *                                                 &lt;/element&gt;
 *                                               &lt;/sequence&gt;
 *                                               &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" fixed="1.0" /&gt;
 *                                             &lt;/restriction&gt;
 *                                           &lt;/complexContent&gt;
 *                                         &lt;/complexType&gt;
 *                                       &lt;/element&gt;
 *                                       &lt;element name="TSTED" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *                                     &lt;/sequence&gt;
 *                                   &lt;/restriction&gt;
 *                                 &lt;/complexContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="FRMT"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;simpleContent&gt;
 *                                   &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;base64Binary"&gt;
 *                                     &lt;attribute name="algoritmo" use="required"&gt;
 *                                       &lt;simpleType&gt;
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                           &lt;enumeration value="SHA1withRSA"/&gt;
 *                                           &lt;enumeration value="SHA1withDSA"/&gt;
 *                                         &lt;/restriction&gt;
 *                                       &lt;/simpleType&gt;
 *                                     &lt;/attribute&gt;
 *                                   &lt;/extension&gt;
 *                                 &lt;/simpleContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
 *                           &lt;/sequence&gt;
 *                           &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" fixed="1.0" /&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="TmstFirma" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *                 &lt;/sequence&gt;
 *                 &lt;attribute name="ID" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" /&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element ref="{http://www.w3.org/2000/09/xmldsig#}Signature"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" fixed="1.0" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BOLETADefType", namespace = "http://www.sii.cl/SiiDte", propOrder = {
    "documento",
    "signature"
})
public class BOLETADefType {

    @XmlElement(name = "Documento", namespace = "http://www.sii.cl/SiiDte", required = true)
    protected BOLETADefType.Documento documento;
    @XmlElement(name = "Signature", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
    protected SignatureType signature;
    @XmlAttribute(name = "version", required = true)
    protected BigDecimal version;

    /**
     * Gets the value of the documento property.
     * 
     * @return
     *     possible object is
     *     {@link BOLETADefType.Documento }
     *     
     */
    public BOLETADefType.Documento getDocumento() {
        return documento;
    }

    /**
     * Sets the value of the documento property.
     * 
     * @param value
     *     allowed object is
     *     {@link BOLETADefType.Documento }
     *     
     */
    public void setDocumento(BOLETADefType.Documento value) {
        this.documento = value;
    }

    /**
     * Gets the value of the signature property.
     * 
     * @return
     *     possible object is
     *     {@link SignatureType }
     *     
     */
    public SignatureType getSignature() {
        return signature;
    }

    /**
     * Sets the value of the signature property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignatureType }
     *     
     */
    public void setSignature(SignatureType value) {
        this.signature = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVersion() {
        if (version == null) {
            return new BigDecimal("1.0");
        } else {
            return version;
        }
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVersion(BigDecimal value) {
        this.version = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="Encabezado"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="IdDoc"&gt;
     *                     &lt;complexType&gt;
     *                       &lt;complexContent&gt;
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                           &lt;sequence&gt;
     *                             &lt;element name="TipoDTE" type="{http://www.sii.cl/SiiDte}DTEType"/&gt;
     *                             &lt;element name="Folio" type="{http://www.sii.cl/SiiDte}FolioType"/&gt;
     *                             &lt;element name="FchEmis" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
     *                             &lt;element name="IndServicio"&gt;
     *                               &lt;simpleType&gt;
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
     *                                   &lt;enumeration value="1"/&gt;
     *                                   &lt;enumeration value="2"/&gt;
     *                                   &lt;enumeration value="3"/&gt;
     *                                   &lt;enumeration value="4"/&gt;
     *                                 &lt;/restriction&gt;
     *                               &lt;/simpleType&gt;
     *                             &lt;/element&gt;
     *                             &lt;element name="IndMntNeto" minOccurs="0"&gt;
     *                               &lt;simpleType&gt;
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
     *                                   &lt;enumeration value="2"/&gt;
     *                                 &lt;/restriction&gt;
     *                               &lt;/simpleType&gt;
     *                             &lt;/element&gt;
     *                             &lt;element name="PeriodoDesde" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
     *                             &lt;element name="PeriodoHasta" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
     *                             &lt;element name="FchVenc" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
     *                             &lt;element name="MedioPago" minOccurs="0"&gt;
     *                               &lt;simpleType&gt;
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
     *                                   &lt;enumeration value="1"/&gt;
     *                                   &lt;enumeration value="2"/&gt;
     *                                   &lt;enumeration value="3"/&gt;
     *                                   &lt;enumeration value="4"/&gt;
     *                                   &lt;enumeration value="5"/&gt;
     *                                 &lt;/restriction&gt;
     *                               &lt;/simpleType&gt;
     *                             &lt;/element&gt;
     *                           &lt;/sequence&gt;
     *                         &lt;/restriction&gt;
     *                       &lt;/complexContent&gt;
     *                     &lt;/complexType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="Emisor"&gt;
     *                     &lt;complexType&gt;
     *                       &lt;complexContent&gt;
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                           &lt;sequence&gt;
     *                             &lt;element name="RUTEmisor" type="{http://www.sii.cl/SiiDte}RUTType"/&gt;
     *                             &lt;element name="RznSocEmisor" minOccurs="0"&gt;
     *                               &lt;simpleType&gt;
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                   &lt;maxLength value="100"/&gt;
     *                                 &lt;/restriction&gt;
     *                               &lt;/simpleType&gt;
     *                             &lt;/element&gt;
     *                             &lt;element name="GiroEmisor" minOccurs="0"&gt;
     *                               &lt;simpleType&gt;
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                   &lt;maxLength value="80"/&gt;
     *                                 &lt;/restriction&gt;
     *                               &lt;/simpleType&gt;
     *                             &lt;/element&gt;
     *                             &lt;element name="CdgSIISucur" minOccurs="0"&gt;
     *                               &lt;simpleType&gt;
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
     *                                   &lt;totalDigits value="9"/&gt;
     *                                 &lt;/restriction&gt;
     *                               &lt;/simpleType&gt;
     *                             &lt;/element&gt;
     *                             &lt;element name="DirOrigen" minOccurs="0"&gt;
     *                               &lt;simpleType&gt;
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                   &lt;maxLength value="70"/&gt;
     *                                 &lt;/restriction&gt;
     *                               &lt;/simpleType&gt;
     *                             &lt;/element&gt;
     *                             &lt;element name="CmnaOrigen" minOccurs="0"&gt;
     *                               &lt;simpleType&gt;
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                   &lt;maxLength value="20"/&gt;
     *                                 &lt;/restriction&gt;
     *                               &lt;/simpleType&gt;
     *                             &lt;/element&gt;
     *                             &lt;element name="CiudadOrigen" minOccurs="0"&gt;
     *                               &lt;simpleType&gt;
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                   &lt;maxLength value="20"/&gt;
     *                                 &lt;/restriction&gt;
     *                               &lt;/simpleType&gt;
     *                             &lt;/element&gt;
     *                           &lt;/sequence&gt;
     *                         &lt;/restriction&gt;
     *                       &lt;/complexContent&gt;
     *                     &lt;/complexType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="Receptor"&gt;
     *                     &lt;complexType&gt;
     *                       &lt;complexContent&gt;
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                           &lt;sequence&gt;
     *                             &lt;element name="RUTRecep" type="{http://www.sii.cl/SiiDte}RUTType"/&gt;
     *                             &lt;element name="CdgIntRecep" minOccurs="0"&gt;
     *                               &lt;simpleType&gt;
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                   &lt;maxLength value="20"/&gt;
     *                                 &lt;/restriction&gt;
     *                               &lt;/simpleType&gt;
     *                             &lt;/element&gt;
     *                             &lt;element name="RznSocRecep" minOccurs="0"&gt;
     *                               &lt;simpleType&gt;
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                   &lt;maxLength value="100"/&gt;
     *                                 &lt;/restriction&gt;
     *                               &lt;/simpleType&gt;
     *                             &lt;/element&gt;
     *                             &lt;element name="Contacto" minOccurs="0"&gt;
     *                               &lt;simpleType&gt;
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                   &lt;maxLength value="80"/&gt;
     *                                 &lt;/restriction&gt;
     *                               &lt;/simpleType&gt;
     *                             &lt;/element&gt;
     *                             &lt;element name="CorreoRecep" type="{http://www.sii.cl/SiiDte}MailType" minOccurs="0"/&gt;
     *                             &lt;element name="TelefonoRecep" type="{http://www.sii.cl/SiiDte}FonoType" minOccurs="0"/&gt;
     *                             &lt;element name="DirRecep" minOccurs="0"&gt;
     *                               &lt;simpleType&gt;
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                   &lt;maxLength value="70"/&gt;
     *                                 &lt;/restriction&gt;
     *                               &lt;/simpleType&gt;
     *                             &lt;/element&gt;
     *                             &lt;element name="CmnaRecep" minOccurs="0"&gt;
     *                               &lt;simpleType&gt;
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                   &lt;maxLength value="20"/&gt;
     *                                 &lt;/restriction&gt;
     *                               &lt;/simpleType&gt;
     *                             &lt;/element&gt;
     *                             &lt;element name="CiudadRecep" minOccurs="0"&gt;
     *                               &lt;simpleType&gt;
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                   &lt;maxLength value="20"/&gt;
     *                                 &lt;/restriction&gt;
     *                               &lt;/simpleType&gt;
     *                             &lt;/element&gt;
     *                             &lt;element name="DirPostal" minOccurs="0"&gt;
     *                               &lt;simpleType&gt;
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                   &lt;maxLength value="70"/&gt;
     *                                 &lt;/restriction&gt;
     *                               &lt;/simpleType&gt;
     *                             &lt;/element&gt;
     *                             &lt;element name="CmnaPostal" minOccurs="0"&gt;
     *                               &lt;simpleType&gt;
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                   &lt;maxLength value="20"/&gt;
     *                                 &lt;/restriction&gt;
     *                               &lt;/simpleType&gt;
     *                             &lt;/element&gt;
     *                             &lt;element name="CiudadPostal" minOccurs="0"&gt;
     *                               &lt;simpleType&gt;
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                   &lt;maxLength value="20"/&gt;
     *                                 &lt;/restriction&gt;
     *                               &lt;/simpleType&gt;
     *                             &lt;/element&gt;
     *                           &lt;/sequence&gt;
     *                         &lt;/restriction&gt;
     *                       &lt;/complexContent&gt;
     *                     &lt;/complexType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="RUTProvSW" type="{http://www.sii.cl/SiiDte}RUTType" minOccurs="0"/&gt;
     *                   &lt;element name="Totales"&gt;
     *                     &lt;complexType&gt;
     *                       &lt;complexContent&gt;
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                           &lt;sequence&gt;
     *                             &lt;element name="MntNeto" type="{http://www.sii.cl/SiiDte}MontoType" minOccurs="0"/&gt;
     *                             &lt;element name="MntExe" type="{http://www.sii.cl/SiiDte}MontoType" minOccurs="0"/&gt;
     *                             &lt;element name="IVA" type="{http://www.sii.cl/SiiDte}MontoType" minOccurs="0"/&gt;
     *                             &lt;element name="MntTotal" type="{http://www.sii.cl/SiiDte}MontoType"/&gt;
     *                             &lt;element name="MontoNF" type="{http://www.sii.cl/SiiDte}ValorType" minOccurs="0"/&gt;
     *                             &lt;element name="TotalPeriodo" type="{http://www.sii.cl/SiiDte}ValorType" minOccurs="0"/&gt;
     *                             &lt;element name="SaldoAnterior" type="{http://www.sii.cl/SiiDte}ValorType" minOccurs="0"/&gt;
     *                             &lt;element name="VlrPagar" type="{http://www.sii.cl/SiiDte}ValorType" minOccurs="0"/&gt;
     *                           &lt;/sequence&gt;
     *                         &lt;/restriction&gt;
     *                       &lt;/complexContent&gt;
     *                     &lt;/complexType&gt;
     *                   &lt;/element&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Detalle" maxOccurs="1000" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="NroLinDet"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
     *                         &lt;minInclusive value="1"/&gt;
     *                         &lt;maxInclusive value="1000"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="CdgItem" maxOccurs="5" minOccurs="0"&gt;
     *                     &lt;complexType&gt;
     *                       &lt;complexContent&gt;
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                           &lt;sequence&gt;
     *                             &lt;element name="TpoCodigo"&gt;
     *                               &lt;simpleType&gt;
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                   &lt;maxLength value="10"/&gt;
     *                                 &lt;/restriction&gt;
     *                               &lt;/simpleType&gt;
     *                             &lt;/element&gt;
     *                             &lt;element name="VlrCodigo"&gt;
     *                               &lt;simpleType&gt;
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                   &lt;maxLength value="35"/&gt;
     *                                 &lt;/restriction&gt;
     *                               &lt;/simpleType&gt;
     *                             &lt;/element&gt;
     *                           &lt;/sequence&gt;
     *                         &lt;/restriction&gt;
     *                       &lt;/complexContent&gt;
     *                     &lt;/complexType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="IndExe" minOccurs="0"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
     *                         &lt;enumeration value="1"/&gt;
     *                         &lt;enumeration value="2"/&gt;
     *                         &lt;enumeration value="6"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="ItemEspectaculo" minOccurs="0"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
     *                         &lt;maxInclusive value="2"/&gt;
     *                         &lt;totalDigits value="2"/&gt;
     *                         &lt;enumeration value="01"/&gt;
     *                         &lt;enumeration value="02"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="RUTMandante" type="{http://www.sii.cl/SiiDte}RUTType" minOccurs="0"/&gt;
     *                   &lt;element name="NmbItem"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                         &lt;maxLength value="80"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="InfoTicket" minOccurs="0"&gt;
     *                     &lt;complexType&gt;
     *                       &lt;complexContent&gt;
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                           &lt;sequence&gt;
     *                             &lt;element name="FolioTicket"&gt;
     *                               &lt;simpleType&gt;
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
     *                                   &lt;totalDigits value="6"/&gt;
     *                                 &lt;/restriction&gt;
     *                               &lt;/simpleType&gt;
     *                             &lt;/element&gt;
     *                             &lt;element name="FchGenera"&gt;
     *                               &lt;simpleType&gt;
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}dateTime"&gt;
     *                                   &lt;minInclusive value="2010-03-10T00:00:00"/&gt;
     *                                   &lt;maxInclusive value="2050-12-31T23:59:59"/&gt;
     *                                 &lt;/restriction&gt;
     *                               &lt;/simpleType&gt;
     *                             &lt;/element&gt;
     *                             &lt;element name="NmbEvento"&gt;
     *                               &lt;simpleType&gt;
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                   &lt;maxLength value="80"/&gt;
     *                                 &lt;/restriction&gt;
     *                               &lt;/simpleType&gt;
     *                             &lt;/element&gt;
     *                             &lt;element name="TpoTicket"&gt;
     *                               &lt;simpleType&gt;
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                   &lt;maxLength value="10"/&gt;
     *                                 &lt;/restriction&gt;
     *                               &lt;/simpleType&gt;
     *                             &lt;/element&gt;
     *                             &lt;element name="CdgEvento"&gt;
     *                               &lt;simpleType&gt;
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                   &lt;maxLength value="5"/&gt;
     *                                 &lt;/restriction&gt;
     *                               &lt;/simpleType&gt;
     *                             &lt;/element&gt;
     *                             &lt;element name="FchEvento"&gt;
     *                               &lt;simpleType&gt;
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}dateTime"&gt;
     *                                   &lt;minInclusive value="2010-03-10T00:00:00"/&gt;
     *                                   &lt;maxInclusive value="2050-12-31T23:59:59"/&gt;
     *                                 &lt;/restriction&gt;
     *                               &lt;/simpleType&gt;
     *                             &lt;/element&gt;
     *                             &lt;element name="LugarEvento"&gt;
     *                               &lt;simpleType&gt;
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                   &lt;maxLength value="80"/&gt;
     *                                 &lt;/restriction&gt;
     *                               &lt;/simpleType&gt;
     *                             &lt;/element&gt;
     *                             &lt;element name="UbicEvento"&gt;
     *                               &lt;simpleType&gt;
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                   &lt;maxLength value="20"/&gt;
     *                                 &lt;/restriction&gt;
     *                               &lt;/simpleType&gt;
     *                             &lt;/element&gt;
     *                             &lt;element name="FilaUbicEvento" minOccurs="0"&gt;
     *                               &lt;simpleType&gt;
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                   &lt;maxLength value="3"/&gt;
     *                                 &lt;/restriction&gt;
     *                               &lt;/simpleType&gt;
     *                             &lt;/element&gt;
     *                             &lt;element name="AsntoUbicEvento" minOccurs="0"&gt;
     *                               &lt;simpleType&gt;
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                   &lt;maxLength value="3"/&gt;
     *                                 &lt;/restriction&gt;
     *                               &lt;/simpleType&gt;
     *                             &lt;/element&gt;
     *                           &lt;/sequence&gt;
     *                         &lt;/restriction&gt;
     *                       &lt;/complexContent&gt;
     *                     &lt;/complexType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="DscItem" minOccurs="0"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                         &lt;maxLength value="1000"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="QtyItem" minOccurs="0"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.sii.cl/SiiDte}Dec5Type"&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="UnmdItem" minOccurs="0"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                         &lt;maxLength value="4"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="PrcItem" minOccurs="0"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.sii.cl/SiiDte}Dec5Type"&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="DescuentoPct" minOccurs="0"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.sii.cl/SiiDte}PctType"&gt;
     *                         &lt;minInclusive value="0.00"/&gt;
     *                         &lt;maxInclusive value="100.00"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="DescuentoMonto" minOccurs="0"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"&gt;
     *                         &lt;totalDigits value="18"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="RecargoPct" minOccurs="0"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.sii.cl/SiiDte}PctType"&gt;
     *                         &lt;maxInclusive value="100.00"/&gt;
     *                         &lt;minInclusive value="0.00"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="RecargoMonto" minOccurs="0"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"&gt;
     *                         &lt;totalDigits value="18"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="MontoItem" type="{http://www.sii.cl/SiiDte}MontoType"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="SubTotInfo" maxOccurs="20" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="NroSTI"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
     *                         &lt;maxInclusive value="20"/&gt;
     *                         &lt;minInclusive value="1"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="GlosaSTI" minOccurs="0"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                         &lt;maxLength value="80"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="OrdenSTI" minOccurs="0"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
     *                         &lt;maxInclusive value="99"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="SubTotNetoSTI" minOccurs="0"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.sii.cl/SiiDte}Dec1Type"&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="SubTotIVASTI" minOccurs="0"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.sii.cl/SiiDte}Dec1Type"&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="SubTotAdicSTI" type="{http://www.sii.cl/SiiDte}Dec1Type" minOccurs="0"/&gt;
     *                   &lt;element name="SubTotExeSTI" type="{http://www.sii.cl/SiiDte}Dec1Type" minOccurs="0"/&gt;
     *                   &lt;element name="ValSubtotSTI" type="{http://www.sii.cl/SiiDte}Dec1Type" minOccurs="0"/&gt;
     *                   &lt;element name="LineasDeta" maxOccurs="1000" minOccurs="0"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
     *                         &lt;maxInclusive value="1000"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="DscRcgGlobal" maxOccurs="20" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="NroLinDR"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
     *                         &lt;maxInclusive value="20"/&gt;
     *                         &lt;minInclusive value="1"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="TpoMov"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                         &lt;enumeration value="D"/&gt;
     *                         &lt;enumeration value="R"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="GlosaDR" minOccurs="0"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                         &lt;maxLength value="45"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="TpoValor"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                         &lt;enumeration value="%"/&gt;
     *                         &lt;enumeration value="$"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="ValorDR" type="{http://www.sii.cl/SiiDte}Dec1Type"/&gt;
     *                   &lt;element name="IndExeDR" minOccurs="0"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
     *                         &lt;enumeration value="1"/&gt;
     *                         &lt;enumeration value="2"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Referencia" maxOccurs="40" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="NroLinRef"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
     *                         &lt;maxInclusive value="40"/&gt;
     *                         &lt;minInclusive value="1"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="TpoDocRef" minOccurs="0"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                         &lt;maxLength value="3"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="FolioRef" minOccurs="0"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                         &lt;maxLength value="18"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="CodRef" minOccurs="0"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                         &lt;maxLength value="18"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="RazonRef" minOccurs="0"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                         &lt;maxLength value="90"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="CodVndor" minOccurs="0"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                         &lt;maxLength value="8"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="CodCaja" minOccurs="0"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                         &lt;maxLength value="8"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="GeoRefEmision" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="LatitudEmision"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                         &lt;maxLength value="30"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="LongitudEmision"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                         &lt;maxLength value="30"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="SistemaReferencia"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
     *                         &lt;maxInclusive value="9"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="TED"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="DD"&gt;
     *                     &lt;complexType&gt;
     *                       &lt;complexContent&gt;
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                           &lt;sequence&gt;
     *                             &lt;element name="RE" type="{http://www.sii.cl/SiiDte}RUTType"/&gt;
     *                             &lt;element name="TD" type="{http://www.sii.cl/SiiDte}DTEType"/&gt;
     *                             &lt;element name="F" type="{http://www.sii.cl/SiiDte}FolioType"/&gt;
     *                             &lt;element name="FE" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
     *                             &lt;element name="RR" type="{http://www.sii.cl/SiiDte}RUTType"/&gt;
     *                             &lt;element name="RSR"&gt;
     *                               &lt;simpleType&gt;
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                   &lt;maxLength value="40"/&gt;
     *                                 &lt;/restriction&gt;
     *                               &lt;/simpleType&gt;
     *                             &lt;/element&gt;
     *                             &lt;element name="MNT" type="{http://www.w3.org/2001/XMLSchema}unsignedLong"/&gt;
     *                             &lt;element name="IT1"&gt;
     *                               &lt;simpleType&gt;
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                   &lt;maxLength value="40"/&gt;
     *                                 &lt;/restriction&gt;
     *                               &lt;/simpleType&gt;
     *                             &lt;/element&gt;
     *                             &lt;element name="CAF"&gt;
     *                               &lt;complexType&gt;
     *                                 &lt;complexContent&gt;
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                                     &lt;sequence&gt;
     *                                       &lt;element name="DA"&gt;
     *                                         &lt;complexType&gt;
     *                                           &lt;complexContent&gt;
     *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                                               &lt;sequence&gt;
     *                                                 &lt;element name="RE" type="{http://www.sii.cl/SiiDte}RUTType"/&gt;
     *                                                 &lt;element name="RS"&gt;
     *                                                   &lt;simpleType&gt;
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                                       &lt;maxLength value="40"/&gt;
     *                                                     &lt;/restriction&gt;
     *                                                   &lt;/simpleType&gt;
     *                                                 &lt;/element&gt;
     *                                                 &lt;element name="TD" type="{http://www.sii.cl/SiiDte}DTEType"/&gt;
     *                                                 &lt;element name="RNG"&gt;
     *                                                   &lt;complexType&gt;
     *                                                     &lt;complexContent&gt;
     *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                                                         &lt;sequence&gt;
     *                                                           &lt;element name="D" type="{http://www.sii.cl/SiiDte}FolioType"/&gt;
     *                                                           &lt;element name="H" type="{http://www.sii.cl/SiiDte}FolioType"/&gt;
     *                                                         &lt;/sequence&gt;
     *                                                       &lt;/restriction&gt;
     *                                                     &lt;/complexContent&gt;
     *                                                   &lt;/complexType&gt;
     *                                                 &lt;/element&gt;
     *                                                 &lt;element name="FA" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
     *                                                 &lt;choice&gt;
     *                                                   &lt;element name="RSAPK"&gt;
     *                                                     &lt;complexType&gt;
     *                                                       &lt;complexContent&gt;
     *                                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                                                           &lt;sequence&gt;
     *                                                             &lt;element name="M" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
     *                                                             &lt;element name="E" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
     *                                                           &lt;/sequence&gt;
     *                                                         &lt;/restriction&gt;
     *                                                       &lt;/complexContent&gt;
     *                                                     &lt;/complexType&gt;
     *                                                   &lt;/element&gt;
     *                                                   &lt;element name="DSAPK"&gt;
     *                                                     &lt;complexType&gt;
     *                                                       &lt;complexContent&gt;
     *                                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                                                           &lt;sequence&gt;
     *                                                             &lt;element name="P" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
     *                                                             &lt;element name="Q" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
     *                                                             &lt;element name="G" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
     *                                                             &lt;element name="Y" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
     *                                                           &lt;/sequence&gt;
     *                                                         &lt;/restriction&gt;
     *                                                       &lt;/complexContent&gt;
     *                                                     &lt;/complexType&gt;
     *                                                   &lt;/element&gt;
     *                                                 &lt;/choice&gt;
     *                                                 &lt;element name="IDK" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
     *                                               &lt;/sequence&gt;
     *                                             &lt;/restriction&gt;
     *                                           &lt;/complexContent&gt;
     *                                         &lt;/complexType&gt;
     *                                       &lt;/element&gt;
     *                                       &lt;element name="FRMA"&gt;
     *                                         &lt;complexType&gt;
     *                                           &lt;simpleContent&gt;
     *                                             &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;base64Binary"&gt;
     *                                               &lt;attribute name="algoritmo" use="required" type="{http://www.w3.org/2001/XMLSchema}string" fixed="SHA1withRSA" /&gt;
     *                                             &lt;/extension&gt;
     *                                           &lt;/simpleContent&gt;
     *                                         &lt;/complexType&gt;
     *                                       &lt;/element&gt;
     *                                     &lt;/sequence&gt;
     *                                     &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" fixed="1.0" /&gt;
     *                                   &lt;/restriction&gt;
     *                                 &lt;/complexContent&gt;
     *                               &lt;/complexType&gt;
     *                             &lt;/element&gt;
     *                             &lt;element name="TSTED" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
     *                           &lt;/sequence&gt;
     *                         &lt;/restriction&gt;
     *                       &lt;/complexContent&gt;
     *                     &lt;/complexType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="FRMT"&gt;
     *                     &lt;complexType&gt;
     *                       &lt;simpleContent&gt;
     *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;base64Binary"&gt;
     *                           &lt;attribute name="algoritmo" use="required"&gt;
     *                             &lt;simpleType&gt;
     *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                 &lt;enumeration value="SHA1withRSA"/&gt;
     *                                 &lt;enumeration value="SHA1withDSA"/&gt;
     *                               &lt;/restriction&gt;
     *                             &lt;/simpleType&gt;
     *                           &lt;/attribute&gt;
     *                         &lt;/extension&gt;
     *                       &lt;/simpleContent&gt;
     *                     &lt;/complexType&gt;
     *                   &lt;/element&gt;
     *                 &lt;/sequence&gt;
     *                 &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" fixed="1.0" /&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="TmstFirma" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
     *       &lt;/sequence&gt;
     *       &lt;attribute name="ID" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "encabezado",
        "detalle",
        "subTotInfo",
        "dscRcgGlobal",
        "referencia",
        "geoRefEmision",
        "ted",
        "tmstFirma"
    })
    public static class Documento {

        @XmlElement(name = "Encabezado", namespace = "http://www.sii.cl/SiiDte", required = true)
        protected BOLETADefType.Documento.Encabezado encabezado;
        @XmlElement(name = "Detalle", namespace = "http://www.sii.cl/SiiDte")
        protected List<BOLETADefType.Documento.Detalle> detalle;
        @XmlElement(name = "SubTotInfo", namespace = "http://www.sii.cl/SiiDte")
        protected List<BOLETADefType.Documento.SubTotInfo> subTotInfo;
        @XmlElement(name = "DscRcgGlobal", namespace = "http://www.sii.cl/SiiDte")
        protected List<BOLETADefType.Documento.DscRcgGlobal> dscRcgGlobal;
        @XmlElement(name = "Referencia", namespace = "http://www.sii.cl/SiiDte")
        protected List<BOLETADefType.Documento.Referencia> referencia;
        @XmlElement(name = "GeoRefEmision", namespace = "http://www.sii.cl/SiiDte")
        protected BOLETADefType.Documento.GeoRefEmision geoRefEmision;
        @XmlElement(name = "TED", namespace = "http://www.sii.cl/SiiDte", required = true)
        protected BOLETADefType.Documento.TED ted;
        @XmlElement(name = "TmstFirma", namespace = "http://www.sii.cl/SiiDte", required = true)
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar tmstFirma;
        @XmlAttribute(name = "ID", required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        @XmlID
        @XmlSchemaType(name = "ID")
        protected String id;

        /**
         * Gets the value of the encabezado property.
         * 
         * @return
         *     possible object is
         *     {@link BOLETADefType.Documento.Encabezado }
         *     
         */
        public BOLETADefType.Documento.Encabezado getEncabezado() {
            return encabezado;
        }

        /**
         * Sets the value of the encabezado property.
         * 
         * @param value
         *     allowed object is
         *     {@link BOLETADefType.Documento.Encabezado }
         *     
         */
        public void setEncabezado(BOLETADefType.Documento.Encabezado value) {
            this.encabezado = value;
        }

        /**
         * Gets the value of the detalle property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the detalle property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDetalle().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link BOLETADefType.Documento.Detalle }
         * 
         * 
         */
        public List<BOLETADefType.Documento.Detalle> getDetalle() {
            if (detalle == null) {
                detalle = new ArrayList<BOLETADefType.Documento.Detalle>();
            }
            return this.detalle;
        }

        /**
         * Gets the value of the subTotInfo property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the subTotInfo property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSubTotInfo().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link BOLETADefType.Documento.SubTotInfo }
         * 
         * 
         */
        public List<BOLETADefType.Documento.SubTotInfo> getSubTotInfo() {
            if (subTotInfo == null) {
                subTotInfo = new ArrayList<BOLETADefType.Documento.SubTotInfo>();
            }
            return this.subTotInfo;
        }

        /**
         * Gets the value of the dscRcgGlobal property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the dscRcgGlobal property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDscRcgGlobal().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link BOLETADefType.Documento.DscRcgGlobal }
         * 
         * 
         */
        public List<BOLETADefType.Documento.DscRcgGlobal> getDscRcgGlobal() {
            if (dscRcgGlobal == null) {
                dscRcgGlobal = new ArrayList<BOLETADefType.Documento.DscRcgGlobal>();
            }
            return this.dscRcgGlobal;
        }

        /**
         * Gets the value of the referencia property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the referencia property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getReferencia().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link BOLETADefType.Documento.Referencia }
         * 
         * 
         */
        public List<BOLETADefType.Documento.Referencia> getReferencia() {
            if (referencia == null) {
                referencia = new ArrayList<BOLETADefType.Documento.Referencia>();
            }
            return this.referencia;
        }

        /**
         * Gets the value of the geoRefEmision property.
         * 
         * @return
         *     possible object is
         *     {@link BOLETADefType.Documento.GeoRefEmision }
         *     
         */
        public BOLETADefType.Documento.GeoRefEmision getGeoRefEmision() {
            return geoRefEmision;
        }

        /**
         * Sets the value of the geoRefEmision property.
         * 
         * @param value
         *     allowed object is
         *     {@link BOLETADefType.Documento.GeoRefEmision }
         *     
         */
        public void setGeoRefEmision(BOLETADefType.Documento.GeoRefEmision value) {
            this.geoRefEmision = value;
        }

        /**
         * Gets the value of the ted property.
         * 
         * @return
         *     possible object is
         *     {@link BOLETADefType.Documento.TED }
         *     
         */
        public BOLETADefType.Documento.TED getTED() {
            return ted;
        }

        /**
         * Sets the value of the ted property.
         * 
         * @param value
         *     allowed object is
         *     {@link BOLETADefType.Documento.TED }
         *     
         */
        public void setTED(BOLETADefType.Documento.TED value) {
            this.ted = value;
        }

        /**
         * Gets the value of the tmstFirma property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getTmstFirma() {
            return tmstFirma;
        }

        /**
         * Sets the value of the tmstFirma property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setTmstFirma(XMLGregorianCalendar value) {
            this.tmstFirma = value;
        }

        /**
         * Gets the value of the id property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getID() {
            return id;
        }

        /**
         * Sets the value of the id property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setID(String value) {
            this.id = value;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;sequence&gt;
         *         &lt;element name="NroLinDet"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
         *               &lt;minInclusive value="1"/&gt;
         *               &lt;maxInclusive value="1000"/&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="CdgItem" maxOccurs="5" minOccurs="0"&gt;
         *           &lt;complexType&gt;
         *             &lt;complexContent&gt;
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                 &lt;sequence&gt;
         *                   &lt;element name="TpoCodigo"&gt;
         *                     &lt;simpleType&gt;
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                         &lt;maxLength value="10"/&gt;
         *                       &lt;/restriction&gt;
         *                     &lt;/simpleType&gt;
         *                   &lt;/element&gt;
         *                   &lt;element name="VlrCodigo"&gt;
         *                     &lt;simpleType&gt;
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                         &lt;maxLength value="35"/&gt;
         *                       &lt;/restriction&gt;
         *                     &lt;/simpleType&gt;
         *                   &lt;/element&gt;
         *                 &lt;/sequence&gt;
         *               &lt;/restriction&gt;
         *             &lt;/complexContent&gt;
         *           &lt;/complexType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="IndExe" minOccurs="0"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
         *               &lt;enumeration value="1"/&gt;
         *               &lt;enumeration value="2"/&gt;
         *               &lt;enumeration value="6"/&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="ItemEspectaculo" minOccurs="0"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
         *               &lt;maxInclusive value="2"/&gt;
         *               &lt;totalDigits value="2"/&gt;
         *               &lt;enumeration value="01"/&gt;
         *               &lt;enumeration value="02"/&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="RUTMandante" type="{http://www.sii.cl/SiiDte}RUTType" minOccurs="0"/&gt;
         *         &lt;element name="NmbItem"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *               &lt;maxLength value="80"/&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="InfoTicket" minOccurs="0"&gt;
         *           &lt;complexType&gt;
         *             &lt;complexContent&gt;
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                 &lt;sequence&gt;
         *                   &lt;element name="FolioTicket"&gt;
         *                     &lt;simpleType&gt;
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
         *                         &lt;totalDigits value="6"/&gt;
         *                       &lt;/restriction&gt;
         *                     &lt;/simpleType&gt;
         *                   &lt;/element&gt;
         *                   &lt;element name="FchGenera"&gt;
         *                     &lt;simpleType&gt;
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}dateTime"&gt;
         *                         &lt;minInclusive value="2010-03-10T00:00:00"/&gt;
         *                         &lt;maxInclusive value="2050-12-31T23:59:59"/&gt;
         *                       &lt;/restriction&gt;
         *                     &lt;/simpleType&gt;
         *                   &lt;/element&gt;
         *                   &lt;element name="NmbEvento"&gt;
         *                     &lt;simpleType&gt;
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                         &lt;maxLength value="80"/&gt;
         *                       &lt;/restriction&gt;
         *                     &lt;/simpleType&gt;
         *                   &lt;/element&gt;
         *                   &lt;element name="TpoTicket"&gt;
         *                     &lt;simpleType&gt;
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                         &lt;maxLength value="10"/&gt;
         *                       &lt;/restriction&gt;
         *                     &lt;/simpleType&gt;
         *                   &lt;/element&gt;
         *                   &lt;element name="CdgEvento"&gt;
         *                     &lt;simpleType&gt;
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                         &lt;maxLength value="5"/&gt;
         *                       &lt;/restriction&gt;
         *                     &lt;/simpleType&gt;
         *                   &lt;/element&gt;
         *                   &lt;element name="FchEvento"&gt;
         *                     &lt;simpleType&gt;
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}dateTime"&gt;
         *                         &lt;minInclusive value="2010-03-10T00:00:00"/&gt;
         *                         &lt;maxInclusive value="2050-12-31T23:59:59"/&gt;
         *                       &lt;/restriction&gt;
         *                     &lt;/simpleType&gt;
         *                   &lt;/element&gt;
         *                   &lt;element name="LugarEvento"&gt;
         *                     &lt;simpleType&gt;
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                         &lt;maxLength value="80"/&gt;
         *                       &lt;/restriction&gt;
         *                     &lt;/simpleType&gt;
         *                   &lt;/element&gt;
         *                   &lt;element name="UbicEvento"&gt;
         *                     &lt;simpleType&gt;
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                         &lt;maxLength value="20"/&gt;
         *                       &lt;/restriction&gt;
         *                     &lt;/simpleType&gt;
         *                   &lt;/element&gt;
         *                   &lt;element name="FilaUbicEvento" minOccurs="0"&gt;
         *                     &lt;simpleType&gt;
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                         &lt;maxLength value="3"/&gt;
         *                       &lt;/restriction&gt;
         *                     &lt;/simpleType&gt;
         *                   &lt;/element&gt;
         *                   &lt;element name="AsntoUbicEvento" minOccurs="0"&gt;
         *                     &lt;simpleType&gt;
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                         &lt;maxLength value="3"/&gt;
         *                       &lt;/restriction&gt;
         *                     &lt;/simpleType&gt;
         *                   &lt;/element&gt;
         *                 &lt;/sequence&gt;
         *               &lt;/restriction&gt;
         *             &lt;/complexContent&gt;
         *           &lt;/complexType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="DscItem" minOccurs="0"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *               &lt;maxLength value="1000"/&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="QtyItem" minOccurs="0"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.sii.cl/SiiDte}Dec5Type"&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="UnmdItem" minOccurs="0"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *               &lt;maxLength value="4"/&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="PrcItem" minOccurs="0"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.sii.cl/SiiDte}Dec5Type"&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="DescuentoPct" minOccurs="0"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.sii.cl/SiiDte}PctType"&gt;
         *               &lt;minInclusive value="0.00"/&gt;
         *               &lt;maxInclusive value="100.00"/&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="DescuentoMonto" minOccurs="0"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"&gt;
         *               &lt;totalDigits value="18"/&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="RecargoPct" minOccurs="0"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.sii.cl/SiiDte}PctType"&gt;
         *               &lt;maxInclusive value="100.00"/&gt;
         *               &lt;minInclusive value="0.00"/&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="RecargoMonto" minOccurs="0"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"&gt;
         *               &lt;totalDigits value="18"/&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="MontoItem" type="{http://www.sii.cl/SiiDte}MontoType"/&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "nroLinDet",
            "cdgItem",
            "indExe",
            "itemEspectaculo",
            "rutMandante",
            "nmbItem",
            "infoTicket",
            "dscItem",
            "qtyItem",
            "unmdItem",
            "prcItem",
            "descuentoPct",
            "descuentoMonto",
            "recargoPct",
            "recargoMonto",
            "montoItem"
        })
        public static class Detalle {

            @XmlElement(name = "NroLinDet", namespace = "http://www.sii.cl/SiiDte")
            protected int nroLinDet;
            @XmlElement(name = "CdgItem", namespace = "http://www.sii.cl/SiiDte")
            protected List<BOLETADefType.Documento.Detalle.CdgItem> cdgItem;
            @XmlElement(name = "IndExe", namespace = "http://www.sii.cl/SiiDte")
            protected BigInteger indExe;
            @XmlElement(name = "ItemEspectaculo", namespace = "http://www.sii.cl/SiiDte")
            protected Integer itemEspectaculo;
            @XmlElement(name = "RUTMandante", namespace = "http://www.sii.cl/SiiDte")
            protected String rutMandante;
            @XmlElement(name = "NmbItem", namespace = "http://www.sii.cl/SiiDte", required = true)
            protected String nmbItem;
            @XmlElement(name = "InfoTicket", namespace = "http://www.sii.cl/SiiDte")
            protected BOLETADefType.Documento.Detalle.InfoTicket infoTicket;
            @XmlElement(name = "DscItem", namespace = "http://www.sii.cl/SiiDte")
            protected String dscItem;
            @XmlElement(name = "QtyItem", namespace = "http://www.sii.cl/SiiDte")
            protected BigDecimal qtyItem;
            @XmlElement(name = "UnmdItem", namespace = "http://www.sii.cl/SiiDte")
            protected String unmdItem;
            @XmlElement(name = "PrcItem", namespace = "http://www.sii.cl/SiiDte")
            protected BigDecimal prcItem;
            @XmlElement(name = "DescuentoPct", namespace = "http://www.sii.cl/SiiDte")
            protected BigDecimal descuentoPct;
            @XmlElement(name = "DescuentoMonto", namespace = "http://www.sii.cl/SiiDte")
            protected BigInteger descuentoMonto;
            @XmlElement(name = "RecargoPct", namespace = "http://www.sii.cl/SiiDte")
            protected BigDecimal recargoPct;
            @XmlElement(name = "RecargoMonto", namespace = "http://www.sii.cl/SiiDte")
            protected BigInteger recargoMonto;
            @XmlElement(name = "MontoItem", namespace = "http://www.sii.cl/SiiDte", required = true)
            @XmlSchemaType(name = "nonNegativeInteger")
            protected BigInteger montoItem;

            /**
             * Gets the value of the nroLinDet property.
             * 
             */
            public int getNroLinDet() {
                return nroLinDet;
            }

            /**
             * Sets the value of the nroLinDet property.
             * 
             */
            public void setNroLinDet(int value) {
                this.nroLinDet = value;
            }

            /**
             * Gets the value of the cdgItem property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the Jakarta XML Binding object.
             * This is why there is not a <CODE>set</CODE> method for the cdgItem property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getCdgItem().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link BOLETADefType.Documento.Detalle.CdgItem }
             * 
             * 
             */
            public List<BOLETADefType.Documento.Detalle.CdgItem> getCdgItem() {
                if (cdgItem == null) {
                    cdgItem = new ArrayList<BOLETADefType.Documento.Detalle.CdgItem>();
                }
                return this.cdgItem;
            }

            /**
             * Gets the value of the indExe property.
             * 
             * @return
             *     possible object is
             *     {@link BigInteger }
             *     
             */
            public BigInteger getIndExe() {
                return indExe;
            }

            /**
             * Sets the value of the indExe property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *     
             */
            public void setIndExe(BigInteger value) {
                this.indExe = value;
            }

            /**
             * Gets the value of the itemEspectaculo property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getItemEspectaculo() {
                return itemEspectaculo;
            }

            /**
             * Sets the value of the itemEspectaculo property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setItemEspectaculo(Integer value) {
                this.itemEspectaculo = value;
            }

            /**
             * Gets the value of the rutMandante property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRUTMandante() {
                return rutMandante;
            }

            /**
             * Sets the value of the rutMandante property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRUTMandante(String value) {
                this.rutMandante = value;
            }

            /**
             * Gets the value of the nmbItem property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNmbItem() {
                return nmbItem;
            }

            /**
             * Sets the value of the nmbItem property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNmbItem(String value) {
                this.nmbItem = value;
            }

            /**
             * Gets the value of the infoTicket property.
             * 
             * @return
             *     possible object is
             *     {@link BOLETADefType.Documento.Detalle.InfoTicket }
             *     
             */
            public BOLETADefType.Documento.Detalle.InfoTicket getInfoTicket() {
                return infoTicket;
            }

            /**
             * Sets the value of the infoTicket property.
             * 
             * @param value
             *     allowed object is
             *     {@link BOLETADefType.Documento.Detalle.InfoTicket }
             *     
             */
            public void setInfoTicket(BOLETADefType.Documento.Detalle.InfoTicket value) {
                this.infoTicket = value;
            }

            /**
             * Gets the value of the dscItem property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDscItem() {
                return dscItem;
            }

            /**
             * Sets the value of the dscItem property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDscItem(String value) {
                this.dscItem = value;
            }

            /**
             * Gets the value of the qtyItem property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getQtyItem() {
                return qtyItem;
            }

            /**
             * Sets the value of the qtyItem property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setQtyItem(BigDecimal value) {
                this.qtyItem = value;
            }

            /**
             * Gets the value of the unmdItem property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getUnmdItem() {
                return unmdItem;
            }

            /**
             * Sets the value of the unmdItem property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setUnmdItem(String value) {
                this.unmdItem = value;
            }

            /**
             * Gets the value of the prcItem property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getPrcItem() {
                return prcItem;
            }

            /**
             * Sets the value of the prcItem property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setPrcItem(BigDecimal value) {
                this.prcItem = value;
            }

            /**
             * Gets the value of the descuentoPct property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getDescuentoPct() {
                return descuentoPct;
            }

            /**
             * Sets the value of the descuentoPct property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setDescuentoPct(BigDecimal value) {
                this.descuentoPct = value;
            }

            /**
             * Gets the value of the descuentoMonto property.
             * 
             * @return
             *     possible object is
             *     {@link BigInteger }
             *     
             */
            public BigInteger getDescuentoMonto() {
                return descuentoMonto;
            }

            /**
             * Sets the value of the descuentoMonto property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *     
             */
            public void setDescuentoMonto(BigInteger value) {
                this.descuentoMonto = value;
            }

            /**
             * Gets the value of the recargoPct property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getRecargoPct() {
                return recargoPct;
            }

            /**
             * Sets the value of the recargoPct property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setRecargoPct(BigDecimal value) {
                this.recargoPct = value;
            }

            /**
             * Gets the value of the recargoMonto property.
             * 
             * @return
             *     possible object is
             *     {@link BigInteger }
             *     
             */
            public BigInteger getRecargoMonto() {
                return recargoMonto;
            }

            /**
             * Sets the value of the recargoMonto property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *     
             */
            public void setRecargoMonto(BigInteger value) {
                this.recargoMonto = value;
            }

            /**
             * Gets the value of the montoItem property.
             * 
             * @return
             *     possible object is
             *     {@link BigInteger }
             *     
             */
            public BigInteger getMontoItem() {
                return montoItem;
            }

            /**
             * Sets the value of the montoItem property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *     
             */
            public void setMontoItem(BigInteger value) {
                this.montoItem = value;
            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType&gt;
             *   &lt;complexContent&gt;
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
             *       &lt;sequence&gt;
             *         &lt;element name="TpoCodigo"&gt;
             *           &lt;simpleType&gt;
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *               &lt;maxLength value="10"/&gt;
             *             &lt;/restriction&gt;
             *           &lt;/simpleType&gt;
             *         &lt;/element&gt;
             *         &lt;element name="VlrCodigo"&gt;
             *           &lt;simpleType&gt;
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *               &lt;maxLength value="35"/&gt;
             *             &lt;/restriction&gt;
             *           &lt;/simpleType&gt;
             *         &lt;/element&gt;
             *       &lt;/sequence&gt;
             *     &lt;/restriction&gt;
             *   &lt;/complexContent&gt;
             * &lt;/complexType&gt;
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "tpoCodigo",
                "vlrCodigo"
            })
            public static class CdgItem {

                @XmlElement(name = "TpoCodigo", namespace = "http://www.sii.cl/SiiDte", required = true)
                protected String tpoCodigo;
                @XmlElement(name = "VlrCodigo", namespace = "http://www.sii.cl/SiiDte", required = true)
                protected String vlrCodigo;

                /**
                 * Gets the value of the tpoCodigo property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getTpoCodigo() {
                    return tpoCodigo;
                }

                /**
                 * Sets the value of the tpoCodigo property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setTpoCodigo(String value) {
                    this.tpoCodigo = value;
                }

                /**
                 * Gets the value of the vlrCodigo property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getVlrCodigo() {
                    return vlrCodigo;
                }

                /**
                 * Sets the value of the vlrCodigo property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setVlrCodigo(String value) {
                    this.vlrCodigo = value;
                }

            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType&gt;
             *   &lt;complexContent&gt;
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
             *       &lt;sequence&gt;
             *         &lt;element name="FolioTicket"&gt;
             *           &lt;simpleType&gt;
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
             *               &lt;totalDigits value="6"/&gt;
             *             &lt;/restriction&gt;
             *           &lt;/simpleType&gt;
             *         &lt;/element&gt;
             *         &lt;element name="FchGenera"&gt;
             *           &lt;simpleType&gt;
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}dateTime"&gt;
             *               &lt;minInclusive value="2010-03-10T00:00:00"/&gt;
             *               &lt;maxInclusive value="2050-12-31T23:59:59"/&gt;
             *             &lt;/restriction&gt;
             *           &lt;/simpleType&gt;
             *         &lt;/element&gt;
             *         &lt;element name="NmbEvento"&gt;
             *           &lt;simpleType&gt;
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *               &lt;maxLength value="80"/&gt;
             *             &lt;/restriction&gt;
             *           &lt;/simpleType&gt;
             *         &lt;/element&gt;
             *         &lt;element name="TpoTicket"&gt;
             *           &lt;simpleType&gt;
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *               &lt;maxLength value="10"/&gt;
             *             &lt;/restriction&gt;
             *           &lt;/simpleType&gt;
             *         &lt;/element&gt;
             *         &lt;element name="CdgEvento"&gt;
             *           &lt;simpleType&gt;
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *               &lt;maxLength value="5"/&gt;
             *             &lt;/restriction&gt;
             *           &lt;/simpleType&gt;
             *         &lt;/element&gt;
             *         &lt;element name="FchEvento"&gt;
             *           &lt;simpleType&gt;
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}dateTime"&gt;
             *               &lt;minInclusive value="2010-03-10T00:00:00"/&gt;
             *               &lt;maxInclusive value="2050-12-31T23:59:59"/&gt;
             *             &lt;/restriction&gt;
             *           &lt;/simpleType&gt;
             *         &lt;/element&gt;
             *         &lt;element name="LugarEvento"&gt;
             *           &lt;simpleType&gt;
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *               &lt;maxLength value="80"/&gt;
             *             &lt;/restriction&gt;
             *           &lt;/simpleType&gt;
             *         &lt;/element&gt;
             *         &lt;element name="UbicEvento"&gt;
             *           &lt;simpleType&gt;
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *               &lt;maxLength value="20"/&gt;
             *             &lt;/restriction&gt;
             *           &lt;/simpleType&gt;
             *         &lt;/element&gt;
             *         &lt;element name="FilaUbicEvento" minOccurs="0"&gt;
             *           &lt;simpleType&gt;
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *               &lt;maxLength value="3"/&gt;
             *             &lt;/restriction&gt;
             *           &lt;/simpleType&gt;
             *         &lt;/element&gt;
             *         &lt;element name="AsntoUbicEvento" minOccurs="0"&gt;
             *           &lt;simpleType&gt;
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *               &lt;maxLength value="3"/&gt;
             *             &lt;/restriction&gt;
             *           &lt;/simpleType&gt;
             *         &lt;/element&gt;
             *       &lt;/sequence&gt;
             *     &lt;/restriction&gt;
             *   &lt;/complexContent&gt;
             * &lt;/complexType&gt;
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "folioTicket",
                "fchGenera",
                "nmbEvento",
                "tpoTicket",
                "cdgEvento",
                "fchEvento",
                "lugarEvento",
                "ubicEvento",
                "filaUbicEvento",
                "asntoUbicEvento"
            })
            public static class InfoTicket {

                @XmlElement(name = "FolioTicket", namespace = "http://www.sii.cl/SiiDte", required = true)
                protected BigInteger folioTicket;
                @XmlElement(name = "FchGenera", namespace = "http://www.sii.cl/SiiDte", required = true)
                protected XMLGregorianCalendar fchGenera;
                @XmlElement(name = "NmbEvento", namespace = "http://www.sii.cl/SiiDte", required = true)
                protected String nmbEvento;
                @XmlElement(name = "TpoTicket", namespace = "http://www.sii.cl/SiiDte", required = true)
                protected String tpoTicket;
                @XmlElement(name = "CdgEvento", namespace = "http://www.sii.cl/SiiDte", required = true)
                protected String cdgEvento;
                @XmlElement(name = "FchEvento", namespace = "http://www.sii.cl/SiiDte", required = true)
                protected XMLGregorianCalendar fchEvento;
                @XmlElement(name = "LugarEvento", namespace = "http://www.sii.cl/SiiDte", required = true)
                protected String lugarEvento;
                @XmlElement(name = "UbicEvento", namespace = "http://www.sii.cl/SiiDte", required = true)
                protected String ubicEvento;
                @XmlElement(name = "FilaUbicEvento", namespace = "http://www.sii.cl/SiiDte")
                protected String filaUbicEvento;
                @XmlElement(name = "AsntoUbicEvento", namespace = "http://www.sii.cl/SiiDte")
                protected String asntoUbicEvento;

                /**
                 * Gets the value of the folioTicket property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigInteger }
                 *     
                 */
                public BigInteger getFolioTicket() {
                    return folioTicket;
                }

                /**
                 * Sets the value of the folioTicket property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigInteger }
                 *     
                 */
                public void setFolioTicket(BigInteger value) {
                    this.folioTicket = value;
                }

                /**
                 * Gets the value of the fchGenera property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link XMLGregorianCalendar }
                 *     
                 */
                public XMLGregorianCalendar getFchGenera() {
                    return fchGenera;
                }

                /**
                 * Sets the value of the fchGenera property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link XMLGregorianCalendar }
                 *     
                 */
                public void setFchGenera(XMLGregorianCalendar value) {
                    this.fchGenera = value;
                }

                /**
                 * Gets the value of the nmbEvento property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getNmbEvento() {
                    return nmbEvento;
                }

                /**
                 * Sets the value of the nmbEvento property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setNmbEvento(String value) {
                    this.nmbEvento = value;
                }

                /**
                 * Gets the value of the tpoTicket property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getTpoTicket() {
                    return tpoTicket;
                }

                /**
                 * Sets the value of the tpoTicket property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setTpoTicket(String value) {
                    this.tpoTicket = value;
                }

                /**
                 * Gets the value of the cdgEvento property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCdgEvento() {
                    return cdgEvento;
                }

                /**
                 * Sets the value of the cdgEvento property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCdgEvento(String value) {
                    this.cdgEvento = value;
                }

                /**
                 * Gets the value of the fchEvento property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link XMLGregorianCalendar }
                 *     
                 */
                public XMLGregorianCalendar getFchEvento() {
                    return fchEvento;
                }

                /**
                 * Sets the value of the fchEvento property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link XMLGregorianCalendar }
                 *     
                 */
                public void setFchEvento(XMLGregorianCalendar value) {
                    this.fchEvento = value;
                }

                /**
                 * Gets the value of the lugarEvento property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getLugarEvento() {
                    return lugarEvento;
                }

                /**
                 * Sets the value of the lugarEvento property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setLugarEvento(String value) {
                    this.lugarEvento = value;
                }

                /**
                 * Gets the value of the ubicEvento property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getUbicEvento() {
                    return ubicEvento;
                }

                /**
                 * Sets the value of the ubicEvento property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setUbicEvento(String value) {
                    this.ubicEvento = value;
                }

                /**
                 * Gets the value of the filaUbicEvento property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getFilaUbicEvento() {
                    return filaUbicEvento;
                }

                /**
                 * Sets the value of the filaUbicEvento property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setFilaUbicEvento(String value) {
                    this.filaUbicEvento = value;
                }

                /**
                 * Gets the value of the asntoUbicEvento property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getAsntoUbicEvento() {
                    return asntoUbicEvento;
                }

                /**
                 * Sets the value of the asntoUbicEvento property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setAsntoUbicEvento(String value) {
                    this.asntoUbicEvento = value;
                }

            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;sequence&gt;
         *         &lt;element name="NroLinDR"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
         *               &lt;maxInclusive value="20"/&gt;
         *               &lt;minInclusive value="1"/&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="TpoMov"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *               &lt;enumeration value="D"/&gt;
         *               &lt;enumeration value="R"/&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="GlosaDR" minOccurs="0"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *               &lt;maxLength value="45"/&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="TpoValor"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *               &lt;enumeration value="%"/&gt;
         *               &lt;enumeration value="$"/&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="ValorDR" type="{http://www.sii.cl/SiiDte}Dec1Type"/&gt;
         *         &lt;element name="IndExeDR" minOccurs="0"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
         *               &lt;enumeration value="1"/&gt;
         *               &lt;enumeration value="2"/&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "nroLinDR",
            "tpoMov",
            "glosaDR",
            "tpoValor",
            "valorDR",
            "indExeDR"
        })
        public static class DscRcgGlobal {

            @XmlElement(name = "NroLinDR", namespace = "http://www.sii.cl/SiiDte")
            protected int nroLinDR;
            @XmlElement(name = "TpoMov", namespace = "http://www.sii.cl/SiiDte", required = true)
            protected String tpoMov;
            @XmlElement(name = "GlosaDR", namespace = "http://www.sii.cl/SiiDte")
            protected String glosaDR;
            @XmlElement(name = "TpoValor", namespace = "http://www.sii.cl/SiiDte", required = true)
            protected String tpoValor;
            @XmlElement(name = "ValorDR", namespace = "http://www.sii.cl/SiiDte", required = true)
            protected BigDecimal valorDR;
            @XmlElement(name = "IndExeDR", namespace = "http://www.sii.cl/SiiDte")
            protected BigInteger indExeDR;

            /**
             * Gets the value of the nroLinDR property.
             * 
             */
            public int getNroLinDR() {
                return nroLinDR;
            }

            /**
             * Sets the value of the nroLinDR property.
             * 
             */
            public void setNroLinDR(int value) {
                this.nroLinDR = value;
            }

            /**
             * Gets the value of the tpoMov property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTpoMov() {
                return tpoMov;
            }

            /**
             * Sets the value of the tpoMov property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTpoMov(String value) {
                this.tpoMov = value;
            }

            /**
             * Gets the value of the glosaDR property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getGlosaDR() {
                return glosaDR;
            }

            /**
             * Sets the value of the glosaDR property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setGlosaDR(String value) {
                this.glosaDR = value;
            }

            /**
             * Gets the value of the tpoValor property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTpoValor() {
                return tpoValor;
            }

            /**
             * Sets the value of the tpoValor property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTpoValor(String value) {
                this.tpoValor = value;
            }

            /**
             * Gets the value of the valorDR property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getValorDR() {
                return valorDR;
            }

            /**
             * Sets the value of the valorDR property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setValorDR(BigDecimal value) {
                this.valorDR = value;
            }

            /**
             * Gets the value of the indExeDR property.
             * 
             * @return
             *     possible object is
             *     {@link BigInteger }
             *     
             */
            public BigInteger getIndExeDR() {
                return indExeDR;
            }

            /**
             * Sets the value of the indExeDR property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *     
             */
            public void setIndExeDR(BigInteger value) {
                this.indExeDR = value;
            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;sequence&gt;
         *         &lt;element name="IdDoc"&gt;
         *           &lt;complexType&gt;
         *             &lt;complexContent&gt;
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                 &lt;sequence&gt;
         *                   &lt;element name="TipoDTE" type="{http://www.sii.cl/SiiDte}DTEType"/&gt;
         *                   &lt;element name="Folio" type="{http://www.sii.cl/SiiDte}FolioType"/&gt;
         *                   &lt;element name="FchEmis" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
         *                   &lt;element name="IndServicio"&gt;
         *                     &lt;simpleType&gt;
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
         *                         &lt;enumeration value="1"/&gt;
         *                         &lt;enumeration value="2"/&gt;
         *                         &lt;enumeration value="3"/&gt;
         *                         &lt;enumeration value="4"/&gt;
         *                       &lt;/restriction&gt;
         *                     &lt;/simpleType&gt;
         *                   &lt;/element&gt;
         *                   &lt;element name="IndMntNeto" minOccurs="0"&gt;
         *                     &lt;simpleType&gt;
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
         *                         &lt;enumeration value="2"/&gt;
         *                       &lt;/restriction&gt;
         *                     &lt;/simpleType&gt;
         *                   &lt;/element&gt;
         *                   &lt;element name="PeriodoDesde" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
         *                   &lt;element name="PeriodoHasta" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
         *                   &lt;element name="FchVenc" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
         *                   &lt;element name="MedioPago" minOccurs="0"&gt;
         *                     &lt;simpleType&gt;
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
         *                         &lt;enumeration value="1"/&gt;
         *                         &lt;enumeration value="2"/&gt;
         *                         &lt;enumeration value="3"/&gt;
         *                         &lt;enumeration value="4"/&gt;
         *                         &lt;enumeration value="5"/&gt;
         *                       &lt;/restriction&gt;
         *                     &lt;/simpleType&gt;
         *                   &lt;/element&gt;
         *                 &lt;/sequence&gt;
         *               &lt;/restriction&gt;
         *             &lt;/complexContent&gt;
         *           &lt;/complexType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="Emisor"&gt;
         *           &lt;complexType&gt;
         *             &lt;complexContent&gt;
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                 &lt;sequence&gt;
         *                   &lt;element name="RUTEmisor" type="{http://www.sii.cl/SiiDte}RUTType"/&gt;
         *                   &lt;element name="RznSocEmisor" minOccurs="0"&gt;
         *                     &lt;simpleType&gt;
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                         &lt;maxLength value="100"/&gt;
         *                       &lt;/restriction&gt;
         *                     &lt;/simpleType&gt;
         *                   &lt;/element&gt;
         *                   &lt;element name="GiroEmisor" minOccurs="0"&gt;
         *                     &lt;simpleType&gt;
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                         &lt;maxLength value="80"/&gt;
         *                       &lt;/restriction&gt;
         *                     &lt;/simpleType&gt;
         *                   &lt;/element&gt;
         *                   &lt;element name="CdgSIISucur" minOccurs="0"&gt;
         *                     &lt;simpleType&gt;
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
         *                         &lt;totalDigits value="9"/&gt;
         *                       &lt;/restriction&gt;
         *                     &lt;/simpleType&gt;
         *                   &lt;/element&gt;
         *                   &lt;element name="DirOrigen" minOccurs="0"&gt;
         *                     &lt;simpleType&gt;
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                         &lt;maxLength value="70"/&gt;
         *                       &lt;/restriction&gt;
         *                     &lt;/simpleType&gt;
         *                   &lt;/element&gt;
         *                   &lt;element name="CmnaOrigen" minOccurs="0"&gt;
         *                     &lt;simpleType&gt;
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                         &lt;maxLength value="20"/&gt;
         *                       &lt;/restriction&gt;
         *                     &lt;/simpleType&gt;
         *                   &lt;/element&gt;
         *                   &lt;element name="CiudadOrigen" minOccurs="0"&gt;
         *                     &lt;simpleType&gt;
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                         &lt;maxLength value="20"/&gt;
         *                       &lt;/restriction&gt;
         *                     &lt;/simpleType&gt;
         *                   &lt;/element&gt;
         *                 &lt;/sequence&gt;
         *               &lt;/restriction&gt;
         *             &lt;/complexContent&gt;
         *           &lt;/complexType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="Receptor"&gt;
         *           &lt;complexType&gt;
         *             &lt;complexContent&gt;
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                 &lt;sequence&gt;
         *                   &lt;element name="RUTRecep" type="{http://www.sii.cl/SiiDte}RUTType"/&gt;
         *                   &lt;element name="CdgIntRecep" minOccurs="0"&gt;
         *                     &lt;simpleType&gt;
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                         &lt;maxLength value="20"/&gt;
         *                       &lt;/restriction&gt;
         *                     &lt;/simpleType&gt;
         *                   &lt;/element&gt;
         *                   &lt;element name="RznSocRecep" minOccurs="0"&gt;
         *                     &lt;simpleType&gt;
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                         &lt;maxLength value="100"/&gt;
         *                       &lt;/restriction&gt;
         *                     &lt;/simpleType&gt;
         *                   &lt;/element&gt;
         *                   &lt;element name="Contacto" minOccurs="0"&gt;
         *                     &lt;simpleType&gt;
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                         &lt;maxLength value="80"/&gt;
         *                       &lt;/restriction&gt;
         *                     &lt;/simpleType&gt;
         *                   &lt;/element&gt;
         *                   &lt;element name="CorreoRecep" type="{http://www.sii.cl/SiiDte}MailType" minOccurs="0"/&gt;
         *                   &lt;element name="TelefonoRecep" type="{http://www.sii.cl/SiiDte}FonoType" minOccurs="0"/&gt;
         *                   &lt;element name="DirRecep" minOccurs="0"&gt;
         *                     &lt;simpleType&gt;
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                         &lt;maxLength value="70"/&gt;
         *                       &lt;/restriction&gt;
         *                     &lt;/simpleType&gt;
         *                   &lt;/element&gt;
         *                   &lt;element name="CmnaRecep" minOccurs="0"&gt;
         *                     &lt;simpleType&gt;
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                         &lt;maxLength value="20"/&gt;
         *                       &lt;/restriction&gt;
         *                     &lt;/simpleType&gt;
         *                   &lt;/element&gt;
         *                   &lt;element name="CiudadRecep" minOccurs="0"&gt;
         *                     &lt;simpleType&gt;
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                         &lt;maxLength value="20"/&gt;
         *                       &lt;/restriction&gt;
         *                     &lt;/simpleType&gt;
         *                   &lt;/element&gt;
         *                   &lt;element name="DirPostal" minOccurs="0"&gt;
         *                     &lt;simpleType&gt;
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                         &lt;maxLength value="70"/&gt;
         *                       &lt;/restriction&gt;
         *                     &lt;/simpleType&gt;
         *                   &lt;/element&gt;
         *                   &lt;element name="CmnaPostal" minOccurs="0"&gt;
         *                     &lt;simpleType&gt;
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                         &lt;maxLength value="20"/&gt;
         *                       &lt;/restriction&gt;
         *                     &lt;/simpleType&gt;
         *                   &lt;/element&gt;
         *                   &lt;element name="CiudadPostal" minOccurs="0"&gt;
         *                     &lt;simpleType&gt;
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                         &lt;maxLength value="20"/&gt;
         *                       &lt;/restriction&gt;
         *                     &lt;/simpleType&gt;
         *                   &lt;/element&gt;
         *                 &lt;/sequence&gt;
         *               &lt;/restriction&gt;
         *             &lt;/complexContent&gt;
         *           &lt;/complexType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="RUTProvSW" type="{http://www.sii.cl/SiiDte}RUTType" minOccurs="0"/&gt;
         *         &lt;element name="Totales"&gt;
         *           &lt;complexType&gt;
         *             &lt;complexContent&gt;
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                 &lt;sequence&gt;
         *                   &lt;element name="MntNeto" type="{http://www.sii.cl/SiiDte}MontoType" minOccurs="0"/&gt;
         *                   &lt;element name="MntExe" type="{http://www.sii.cl/SiiDte}MontoType" minOccurs="0"/&gt;
         *                   &lt;element name="IVA" type="{http://www.sii.cl/SiiDte}MontoType" minOccurs="0"/&gt;
         *                   &lt;element name="MntTotal" type="{http://www.sii.cl/SiiDte}MontoType"/&gt;
         *                   &lt;element name="MontoNF" type="{http://www.sii.cl/SiiDte}ValorType" minOccurs="0"/&gt;
         *                   &lt;element name="TotalPeriodo" type="{http://www.sii.cl/SiiDte}ValorType" minOccurs="0"/&gt;
         *                   &lt;element name="SaldoAnterior" type="{http://www.sii.cl/SiiDte}ValorType" minOccurs="0"/&gt;
         *                   &lt;element name="VlrPagar" type="{http://www.sii.cl/SiiDte}ValorType" minOccurs="0"/&gt;
         *                 &lt;/sequence&gt;
         *               &lt;/restriction&gt;
         *             &lt;/complexContent&gt;
         *           &lt;/complexType&gt;
         *         &lt;/element&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "idDoc",
            "emisor",
            "receptor",
            "rutProvSW",
            "totales"
        })
        public static class Encabezado {

            @XmlElement(name = "IdDoc", namespace = "http://www.sii.cl/SiiDte", required = true)
            protected BOLETADefType.Documento.Encabezado.IdDoc idDoc;
            @XmlElement(name = "Emisor", namespace = "http://www.sii.cl/SiiDte", required = true)
            protected BOLETADefType.Documento.Encabezado.Emisor emisor;
            @XmlElement(name = "Receptor", namespace = "http://www.sii.cl/SiiDte", required = true)
            protected BOLETADefType.Documento.Encabezado.Receptor receptor;
            @XmlElement(name = "RUTProvSW", namespace = "http://www.sii.cl/SiiDte")
            protected String rutProvSW;
            @XmlElement(name = "Totales", namespace = "http://www.sii.cl/SiiDte", required = true)
            protected BOLETADefType.Documento.Encabezado.Totales totales;

            /**
             * Gets the value of the idDoc property.
             * 
             * @return
             *     possible object is
             *     {@link BOLETADefType.Documento.Encabezado.IdDoc }
             *     
             */
            public BOLETADefType.Documento.Encabezado.IdDoc getIdDoc() {
                return idDoc;
            }

            /**
             * Sets the value of the idDoc property.
             * 
             * @param value
             *     allowed object is
             *     {@link BOLETADefType.Documento.Encabezado.IdDoc }
             *     
             */
            public void setIdDoc(BOLETADefType.Documento.Encabezado.IdDoc value) {
                this.idDoc = value;
            }

            /**
             * Gets the value of the emisor property.
             * 
             * @return
             *     possible object is
             *     {@link BOLETADefType.Documento.Encabezado.Emisor }
             *     
             */
            public BOLETADefType.Documento.Encabezado.Emisor getEmisor() {
                return emisor;
            }

            /**
             * Sets the value of the emisor property.
             * 
             * @param value
             *     allowed object is
             *     {@link BOLETADefType.Documento.Encabezado.Emisor }
             *     
             */
            public void setEmisor(BOLETADefType.Documento.Encabezado.Emisor value) {
                this.emisor = value;
            }

            /**
             * Gets the value of the receptor property.
             * 
             * @return
             *     possible object is
             *     {@link BOLETADefType.Documento.Encabezado.Receptor }
             *     
             */
            public BOLETADefType.Documento.Encabezado.Receptor getReceptor() {
                return receptor;
            }

            /**
             * Sets the value of the receptor property.
             * 
             * @param value
             *     allowed object is
             *     {@link BOLETADefType.Documento.Encabezado.Receptor }
             *     
             */
            public void setReceptor(BOLETADefType.Documento.Encabezado.Receptor value) {
                this.receptor = value;
            }

            /**
             * Gets the value of the rutProvSW property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRUTProvSW() {
                return rutProvSW;
            }

            /**
             * Sets the value of the rutProvSW property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRUTProvSW(String value) {
                this.rutProvSW = value;
            }

            /**
             * Gets the value of the totales property.
             * 
             * @return
             *     possible object is
             *     {@link BOLETADefType.Documento.Encabezado.Totales }
             *     
             */
            public BOLETADefType.Documento.Encabezado.Totales getTotales() {
                return totales;
            }

            /**
             * Sets the value of the totales property.
             * 
             * @param value
             *     allowed object is
             *     {@link BOLETADefType.Documento.Encabezado.Totales }
             *     
             */
            public void setTotales(BOLETADefType.Documento.Encabezado.Totales value) {
                this.totales = value;
            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType&gt;
             *   &lt;complexContent&gt;
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
             *       &lt;sequence&gt;
             *         &lt;element name="RUTEmisor" type="{http://www.sii.cl/SiiDte}RUTType"/&gt;
             *         &lt;element name="RznSocEmisor" minOccurs="0"&gt;
             *           &lt;simpleType&gt;
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *               &lt;maxLength value="100"/&gt;
             *             &lt;/restriction&gt;
             *           &lt;/simpleType&gt;
             *         &lt;/element&gt;
             *         &lt;element name="GiroEmisor" minOccurs="0"&gt;
             *           &lt;simpleType&gt;
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *               &lt;maxLength value="80"/&gt;
             *             &lt;/restriction&gt;
             *           &lt;/simpleType&gt;
             *         &lt;/element&gt;
             *         &lt;element name="CdgSIISucur" minOccurs="0"&gt;
             *           &lt;simpleType&gt;
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
             *               &lt;totalDigits value="9"/&gt;
             *             &lt;/restriction&gt;
             *           &lt;/simpleType&gt;
             *         &lt;/element&gt;
             *         &lt;element name="DirOrigen" minOccurs="0"&gt;
             *           &lt;simpleType&gt;
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *               &lt;maxLength value="70"/&gt;
             *             &lt;/restriction&gt;
             *           &lt;/simpleType&gt;
             *         &lt;/element&gt;
             *         &lt;element name="CmnaOrigen" minOccurs="0"&gt;
             *           &lt;simpleType&gt;
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *               &lt;maxLength value="20"/&gt;
             *             &lt;/restriction&gt;
             *           &lt;/simpleType&gt;
             *         &lt;/element&gt;
             *         &lt;element name="CiudadOrigen" minOccurs="0"&gt;
             *           &lt;simpleType&gt;
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *               &lt;maxLength value="20"/&gt;
             *             &lt;/restriction&gt;
             *           &lt;/simpleType&gt;
             *         &lt;/element&gt;
             *       &lt;/sequence&gt;
             *     &lt;/restriction&gt;
             *   &lt;/complexContent&gt;
             * &lt;/complexType&gt;
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "rutEmisor",
                "rznSocEmisor",
                "giroEmisor",
                "cdgSIISucur",
                "dirOrigen",
                "cmnaOrigen",
                "ciudadOrigen"
            })
            public static class Emisor {

                @XmlElement(name = "RUTEmisor", namespace = "http://www.sii.cl/SiiDte", required = true)
                protected String rutEmisor;
                @XmlElement(name = "RznSocEmisor", namespace = "http://www.sii.cl/SiiDte")
                protected String rznSocEmisor;
                @XmlElement(name = "GiroEmisor", namespace = "http://www.sii.cl/SiiDte")
                protected String giroEmisor;
                @XmlElement(name = "CdgSIISucur", namespace = "http://www.sii.cl/SiiDte")
                protected BigInteger cdgSIISucur;
                @XmlElement(name = "DirOrigen", namespace = "http://www.sii.cl/SiiDte")
                protected String dirOrigen;
                @XmlElement(name = "CmnaOrigen", namespace = "http://www.sii.cl/SiiDte")
                protected String cmnaOrigen;
                @XmlElement(name = "CiudadOrigen", namespace = "http://www.sii.cl/SiiDte")
                protected String ciudadOrigen;

                /**
                 * Gets the value of the rutEmisor property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getRUTEmisor() {
                    return rutEmisor;
                }

                /**
                 * Sets the value of the rutEmisor property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setRUTEmisor(String value) {
                    this.rutEmisor = value;
                }

                /**
                 * Gets the value of the rznSocEmisor property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getRznSocEmisor() {
                    return rznSocEmisor;
                }

                /**
                 * Sets the value of the rznSocEmisor property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setRznSocEmisor(String value) {
                    this.rznSocEmisor = value;
                }

                /**
                 * Gets the value of the giroEmisor property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getGiroEmisor() {
                    return giroEmisor;
                }

                /**
                 * Sets the value of the giroEmisor property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setGiroEmisor(String value) {
                    this.giroEmisor = value;
                }

                /**
                 * Gets the value of the cdgSIISucur property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigInteger }
                 *     
                 */
                public BigInteger getCdgSIISucur() {
                    return cdgSIISucur;
                }

                /**
                 * Sets the value of the cdgSIISucur property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigInteger }
                 *     
                 */
                public void setCdgSIISucur(BigInteger value) {
                    this.cdgSIISucur = value;
                }

                /**
                 * Gets the value of the dirOrigen property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getDirOrigen() {
                    return dirOrigen;
                }

                /**
                 * Sets the value of the dirOrigen property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setDirOrigen(String value) {
                    this.dirOrigen = value;
                }

                /**
                 * Gets the value of the cmnaOrigen property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCmnaOrigen() {
                    return cmnaOrigen;
                }

                /**
                 * Sets the value of the cmnaOrigen property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCmnaOrigen(String value) {
                    this.cmnaOrigen = value;
                }

                /**
                 * Gets the value of the ciudadOrigen property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCiudadOrigen() {
                    return ciudadOrigen;
                }

                /**
                 * Sets the value of the ciudadOrigen property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCiudadOrigen(String value) {
                    this.ciudadOrigen = value;
                }

            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType&gt;
             *   &lt;complexContent&gt;
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
             *       &lt;sequence&gt;
             *         &lt;element name="TipoDTE" type="{http://www.sii.cl/SiiDte}DTEType"/&gt;
             *         &lt;element name="Folio" type="{http://www.sii.cl/SiiDte}FolioType"/&gt;
             *         &lt;element name="FchEmis" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
             *         &lt;element name="IndServicio"&gt;
             *           &lt;simpleType&gt;
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
             *               &lt;enumeration value="1"/&gt;
             *               &lt;enumeration value="2"/&gt;
             *               &lt;enumeration value="3"/&gt;
             *               &lt;enumeration value="4"/&gt;
             *             &lt;/restriction&gt;
             *           &lt;/simpleType&gt;
             *         &lt;/element&gt;
             *         &lt;element name="IndMntNeto" minOccurs="0"&gt;
             *           &lt;simpleType&gt;
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
             *               &lt;enumeration value="2"/&gt;
             *             &lt;/restriction&gt;
             *           &lt;/simpleType&gt;
             *         &lt;/element&gt;
             *         &lt;element name="PeriodoDesde" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
             *         &lt;element name="PeriodoHasta" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
             *         &lt;element name="FchVenc" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
             *         &lt;element name="MedioPago" minOccurs="0"&gt;
             *           &lt;simpleType&gt;
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
             *               &lt;enumeration value="1"/&gt;
             *               &lt;enumeration value="2"/&gt;
             *               &lt;enumeration value="3"/&gt;
             *               &lt;enumeration value="4"/&gt;
             *               &lt;enumeration value="5"/&gt;
             *             &lt;/restriction&gt;
             *           &lt;/simpleType&gt;
             *         &lt;/element&gt;
             *       &lt;/sequence&gt;
             *     &lt;/restriction&gt;
             *   &lt;/complexContent&gt;
             * &lt;/complexType&gt;
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "tipoDTE",
                "folio",
                "fchEmis",
                "indServicio",
                "indMntNeto",
                "periodoDesde",
                "periodoHasta",
                "fchVenc",
                "medioPago"
            })
            public static class IdDoc {

                @XmlElement(name = "TipoDTE", namespace = "http://www.sii.cl/SiiDte", required = true)
                @XmlSchemaType(name = "positiveInteger")
                protected BigInteger tipoDTE;
                @XmlElement(name = "Folio", namespace = "http://www.sii.cl/SiiDte", required = true)
                @XmlSchemaType(name = "positiveInteger")
                protected BigInteger folio;
                @XmlElement(name = "FchEmis", namespace = "http://www.sii.cl/SiiDte", required = true)
                @XmlSchemaType(name = "date")
                protected XMLGregorianCalendar fchEmis;
                @XmlElement(name = "IndServicio", namespace = "http://www.sii.cl/SiiDte", required = true)
                protected BigInteger indServicio;
                @XmlElement(name = "IndMntNeto", namespace = "http://www.sii.cl/SiiDte")
                protected BigInteger indMntNeto;
                @XmlElement(name = "PeriodoDesde", namespace = "http://www.sii.cl/SiiDte")
                @XmlSchemaType(name = "date")
                protected XMLGregorianCalendar periodoDesde;
                @XmlElement(name = "PeriodoHasta", namespace = "http://www.sii.cl/SiiDte")
                @XmlSchemaType(name = "date")
                protected XMLGregorianCalendar periodoHasta;
                @XmlElement(name = "FchVenc", namespace = "http://www.sii.cl/SiiDte")
                @XmlSchemaType(name = "date")
                protected XMLGregorianCalendar fchVenc;
                @XmlElement(name = "MedioPago", namespace = "http://www.sii.cl/SiiDte")
                protected BigInteger medioPago;

                /**
                 * Gets the value of the tipoDTE property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigInteger }
                 *     
                 */
                public BigInteger getTipoDTE() {
                    return tipoDTE;
                }

                /**
                 * Sets the value of the tipoDTE property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigInteger }
                 *     
                 */
                public void setTipoDTE(BigInteger value) {
                    this.tipoDTE = value;
                }

                /**
                 * Gets the value of the folio property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigInteger }
                 *     
                 */
                public BigInteger getFolio() {
                    return folio;
                }

                /**
                 * Sets the value of the folio property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigInteger }
                 *     
                 */
                public void setFolio(BigInteger value) {
                    this.folio = value;
                }

                /**
                 * Gets the value of the fchEmis property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link XMLGregorianCalendar }
                 *     
                 */
                public XMLGregorianCalendar getFchEmis() {
                    return fchEmis;
                }

                /**
                 * Sets the value of the fchEmis property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link XMLGregorianCalendar }
                 *     
                 */
                public void setFchEmis(XMLGregorianCalendar value) {
                    this.fchEmis = value;
                }

                /**
                 * Gets the value of the indServicio property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigInteger }
                 *     
                 */
                public BigInteger getIndServicio() {
                    return indServicio;
                }

                /**
                 * Sets the value of the indServicio property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigInteger }
                 *     
                 */
                public void setIndServicio(BigInteger value) {
                    this.indServicio = value;
                }

                /**
                 * Gets the value of the indMntNeto property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigInteger }
                 *     
                 */
                public BigInteger getIndMntNeto() {
                    return indMntNeto;
                }

                /**
                 * Sets the value of the indMntNeto property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigInteger }
                 *     
                 */
                public void setIndMntNeto(BigInteger value) {
                    this.indMntNeto = value;
                }

                /**
                 * Gets the value of the periodoDesde property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link XMLGregorianCalendar }
                 *     
                 */
                public XMLGregorianCalendar getPeriodoDesde() {
                    return periodoDesde;
                }

                /**
                 * Sets the value of the periodoDesde property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link XMLGregorianCalendar }
                 *     
                 */
                public void setPeriodoDesde(XMLGregorianCalendar value) {
                    this.periodoDesde = value;
                }

                /**
                 * Gets the value of the periodoHasta property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link XMLGregorianCalendar }
                 *     
                 */
                public XMLGregorianCalendar getPeriodoHasta() {
                    return periodoHasta;
                }

                /**
                 * Sets the value of the periodoHasta property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link XMLGregorianCalendar }
                 *     
                 */
                public void setPeriodoHasta(XMLGregorianCalendar value) {
                    this.periodoHasta = value;
                }

                /**
                 * Gets the value of the fchVenc property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link XMLGregorianCalendar }
                 *     
                 */
                public XMLGregorianCalendar getFchVenc() {
                    return fchVenc;
                }

                /**
                 * Sets the value of the fchVenc property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link XMLGregorianCalendar }
                 *     
                 */
                public void setFchVenc(XMLGregorianCalendar value) {
                    this.fchVenc = value;
                }

                /**
                 * Gets the value of the medioPago property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigInteger }
                 *     
                 */
                public BigInteger getMedioPago() {
                    return medioPago;
                }

                /**
                 * Sets the value of the medioPago property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigInteger }
                 *     
                 */
                public void setMedioPago(BigInteger value) {
                    this.medioPago = value;
                }

            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType&gt;
             *   &lt;complexContent&gt;
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
             *       &lt;sequence&gt;
             *         &lt;element name="RUTRecep" type="{http://www.sii.cl/SiiDte}RUTType"/&gt;
             *         &lt;element name="CdgIntRecep" minOccurs="0"&gt;
             *           &lt;simpleType&gt;
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *               &lt;maxLength value="20"/&gt;
             *             &lt;/restriction&gt;
             *           &lt;/simpleType&gt;
             *         &lt;/element&gt;
             *         &lt;element name="RznSocRecep" minOccurs="0"&gt;
             *           &lt;simpleType&gt;
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *               &lt;maxLength value="100"/&gt;
             *             &lt;/restriction&gt;
             *           &lt;/simpleType&gt;
             *         &lt;/element&gt;
             *         &lt;element name="Contacto" minOccurs="0"&gt;
             *           &lt;simpleType&gt;
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *               &lt;maxLength value="80"/&gt;
             *             &lt;/restriction&gt;
             *           &lt;/simpleType&gt;
             *         &lt;/element&gt;
             *         &lt;element name="CorreoRecep" type="{http://www.sii.cl/SiiDte}MailType" minOccurs="0"/&gt;
             *         &lt;element name="TelefonoRecep" type="{http://www.sii.cl/SiiDte}FonoType" minOccurs="0"/&gt;
             *         &lt;element name="DirRecep" minOccurs="0"&gt;
             *           &lt;simpleType&gt;
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *               &lt;maxLength value="70"/&gt;
             *             &lt;/restriction&gt;
             *           &lt;/simpleType&gt;
             *         &lt;/element&gt;
             *         &lt;element name="CmnaRecep" minOccurs="0"&gt;
             *           &lt;simpleType&gt;
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *               &lt;maxLength value="20"/&gt;
             *             &lt;/restriction&gt;
             *           &lt;/simpleType&gt;
             *         &lt;/element&gt;
             *         &lt;element name="CiudadRecep" minOccurs="0"&gt;
             *           &lt;simpleType&gt;
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *               &lt;maxLength value="20"/&gt;
             *             &lt;/restriction&gt;
             *           &lt;/simpleType&gt;
             *         &lt;/element&gt;
             *         &lt;element name="DirPostal" minOccurs="0"&gt;
             *           &lt;simpleType&gt;
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *               &lt;maxLength value="70"/&gt;
             *             &lt;/restriction&gt;
             *           &lt;/simpleType&gt;
             *         &lt;/element&gt;
             *         &lt;element name="CmnaPostal" minOccurs="0"&gt;
             *           &lt;simpleType&gt;
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *               &lt;maxLength value="20"/&gt;
             *             &lt;/restriction&gt;
             *           &lt;/simpleType&gt;
             *         &lt;/element&gt;
             *         &lt;element name="CiudadPostal" minOccurs="0"&gt;
             *           &lt;simpleType&gt;
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *               &lt;maxLength value="20"/&gt;
             *             &lt;/restriction&gt;
             *           &lt;/simpleType&gt;
             *         &lt;/element&gt;
             *       &lt;/sequence&gt;
             *     &lt;/restriction&gt;
             *   &lt;/complexContent&gt;
             * &lt;/complexType&gt;
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "rutRecep",
                "cdgIntRecep",
                "rznSocRecep",
                "contacto",
                "correoRecep",
                "telefonoRecep",
                "dirRecep",
                "cmnaRecep",
                "ciudadRecep",
                "dirPostal",
                "cmnaPostal",
                "ciudadPostal"
            })
            public static class Receptor {

                @XmlElement(name = "RUTRecep", namespace = "http://www.sii.cl/SiiDte", required = true)
                protected String rutRecep;
                @XmlElement(name = "CdgIntRecep", namespace = "http://www.sii.cl/SiiDte")
                protected String cdgIntRecep;
                @XmlElement(name = "RznSocRecep", namespace = "http://www.sii.cl/SiiDte")
                protected String rznSocRecep;
                @XmlElement(name = "Contacto", namespace = "http://www.sii.cl/SiiDte")
                protected String contacto;
                @XmlElement(name = "CorreoRecep", namespace = "http://www.sii.cl/SiiDte")
                protected String correoRecep;
                @XmlElement(name = "TelefonoRecep", namespace = "http://www.sii.cl/SiiDte")
                protected String telefonoRecep;
                @XmlElement(name = "DirRecep", namespace = "http://www.sii.cl/SiiDte")
                protected String dirRecep;
                @XmlElement(name = "CmnaRecep", namespace = "http://www.sii.cl/SiiDte")
                protected String cmnaRecep;
                @XmlElement(name = "CiudadRecep", namespace = "http://www.sii.cl/SiiDte")
                protected String ciudadRecep;
                @XmlElement(name = "DirPostal", namespace = "http://www.sii.cl/SiiDte")
                protected String dirPostal;
                @XmlElement(name = "CmnaPostal", namespace = "http://www.sii.cl/SiiDte")
                protected String cmnaPostal;
                @XmlElement(name = "CiudadPostal", namespace = "http://www.sii.cl/SiiDte")
                protected String ciudadPostal;

                /**
                 * Gets the value of the rutRecep property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getRUTRecep() {
                    return rutRecep;
                }

                /**
                 * Sets the value of the rutRecep property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setRUTRecep(String value) {
                    this.rutRecep = value;
                }

                /**
                 * Gets the value of the cdgIntRecep property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCdgIntRecep() {
                    return cdgIntRecep;
                }

                /**
                 * Sets the value of the cdgIntRecep property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCdgIntRecep(String value) {
                    this.cdgIntRecep = value;
                }

                /**
                 * Gets the value of the rznSocRecep property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getRznSocRecep() {
                    return rznSocRecep;
                }

                /**
                 * Sets the value of the rznSocRecep property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setRznSocRecep(String value) {
                    this.rznSocRecep = value;
                }

                /**
                 * Gets the value of the contacto property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getContacto() {
                    return contacto;
                }

                /**
                 * Sets the value of the contacto property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setContacto(String value) {
                    this.contacto = value;
                }

                /**
                 * Gets the value of the correoRecep property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCorreoRecep() {
                    return correoRecep;
                }

                /**
                 * Sets the value of the correoRecep property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCorreoRecep(String value) {
                    this.correoRecep = value;
                }

                /**
                 * Gets the value of the telefonoRecep property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getTelefonoRecep() {
                    return telefonoRecep;
                }

                /**
                 * Sets the value of the telefonoRecep property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setTelefonoRecep(String value) {
                    this.telefonoRecep = value;
                }

                /**
                 * Gets the value of the dirRecep property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getDirRecep() {
                    return dirRecep;
                }

                /**
                 * Sets the value of the dirRecep property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setDirRecep(String value) {
                    this.dirRecep = value;
                }

                /**
                 * Gets the value of the cmnaRecep property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCmnaRecep() {
                    return cmnaRecep;
                }

                /**
                 * Sets the value of the cmnaRecep property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCmnaRecep(String value) {
                    this.cmnaRecep = value;
                }

                /**
                 * Gets the value of the ciudadRecep property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCiudadRecep() {
                    return ciudadRecep;
                }

                /**
                 * Sets the value of the ciudadRecep property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCiudadRecep(String value) {
                    this.ciudadRecep = value;
                }

                /**
                 * Gets the value of the dirPostal property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getDirPostal() {
                    return dirPostal;
                }

                /**
                 * Sets the value of the dirPostal property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setDirPostal(String value) {
                    this.dirPostal = value;
                }

                /**
                 * Gets the value of the cmnaPostal property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCmnaPostal() {
                    return cmnaPostal;
                }

                /**
                 * Sets the value of the cmnaPostal property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCmnaPostal(String value) {
                    this.cmnaPostal = value;
                }

                /**
                 * Gets the value of the ciudadPostal property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCiudadPostal() {
                    return ciudadPostal;
                }

                /**
                 * Sets the value of the ciudadPostal property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCiudadPostal(String value) {
                    this.ciudadPostal = value;
                }

            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType&gt;
             *   &lt;complexContent&gt;
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
             *       &lt;sequence&gt;
             *         &lt;element name="MntNeto" type="{http://www.sii.cl/SiiDte}MontoType" minOccurs="0"/&gt;
             *         &lt;element name="MntExe" type="{http://www.sii.cl/SiiDte}MontoType" minOccurs="0"/&gt;
             *         &lt;element name="IVA" type="{http://www.sii.cl/SiiDte}MontoType" minOccurs="0"/&gt;
             *         &lt;element name="MntTotal" type="{http://www.sii.cl/SiiDte}MontoType"/&gt;
             *         &lt;element name="MontoNF" type="{http://www.sii.cl/SiiDte}ValorType" minOccurs="0"/&gt;
             *         &lt;element name="TotalPeriodo" type="{http://www.sii.cl/SiiDte}ValorType" minOccurs="0"/&gt;
             *         &lt;element name="SaldoAnterior" type="{http://www.sii.cl/SiiDte}ValorType" minOccurs="0"/&gt;
             *         &lt;element name="VlrPagar" type="{http://www.sii.cl/SiiDte}ValorType" minOccurs="0"/&gt;
             *       &lt;/sequence&gt;
             *     &lt;/restriction&gt;
             *   &lt;/complexContent&gt;
             * &lt;/complexType&gt;
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "mntNeto",
                "mntExe",
                "iva",
                "mntTotal",
                "montoNF",
                "totalPeriodo",
                "saldoAnterior",
                "vlrPagar"
            })
            public static class Totales {

                @XmlElement(name = "MntNeto", namespace = "http://www.sii.cl/SiiDte")
                @XmlSchemaType(name = "nonNegativeInteger")
                protected BigInteger mntNeto;
                @XmlElement(name = "MntExe", namespace = "http://www.sii.cl/SiiDte")
                @XmlSchemaType(name = "nonNegativeInteger")
                protected BigInteger mntExe;
                @XmlElement(name = "IVA", namespace = "http://www.sii.cl/SiiDte")
                @XmlSchemaType(name = "nonNegativeInteger")
                protected BigInteger iva;
                @XmlElement(name = "MntTotal", namespace = "http://www.sii.cl/SiiDte", required = true)
                @XmlSchemaType(name = "nonNegativeInteger")
                protected BigInteger mntTotal;
                @XmlElement(name = "MontoNF", namespace = "http://www.sii.cl/SiiDte")
                protected BigInteger montoNF;
                @XmlElement(name = "TotalPeriodo", namespace = "http://www.sii.cl/SiiDte")
                protected BigInteger totalPeriodo;
                @XmlElement(name = "SaldoAnterior", namespace = "http://www.sii.cl/SiiDte")
                protected BigInteger saldoAnterior;
                @XmlElement(name = "VlrPagar", namespace = "http://www.sii.cl/SiiDte")
                protected BigInteger vlrPagar;

                /**
                 * Gets the value of the mntNeto property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigInteger }
                 *     
                 */
                public BigInteger getMntNeto() {
                    return mntNeto;
                }

                /**
                 * Sets the value of the mntNeto property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigInteger }
                 *     
                 */
                public void setMntNeto(BigInteger value) {
                    this.mntNeto = value;
                }

                /**
                 * Gets the value of the mntExe property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigInteger }
                 *     
                 */
                public BigInteger getMntExe() {
                    return mntExe;
                }

                /**
                 * Sets the value of the mntExe property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigInteger }
                 *     
                 */
                public void setMntExe(BigInteger value) {
                    this.mntExe = value;
                }

                /**
                 * Gets the value of the iva property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigInteger }
                 *     
                 */
                public BigInteger getIVA() {
                    return iva;
                }

                /**
                 * Sets the value of the iva property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigInteger }
                 *     
                 */
                public void setIVA(BigInteger value) {
                    this.iva = value;
                }

                /**
                 * Gets the value of the mntTotal property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigInteger }
                 *     
                 */
                public BigInteger getMntTotal() {
                    return mntTotal;
                }

                /**
                 * Sets the value of the mntTotal property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigInteger }
                 *     
                 */
                public void setMntTotal(BigInteger value) {
                    this.mntTotal = value;
                }

                /**
                 * Gets the value of the montoNF property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigInteger }
                 *     
                 */
                public BigInteger getMontoNF() {
                    return montoNF;
                }

                /**
                 * Sets the value of the montoNF property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigInteger }
                 *     
                 */
                public void setMontoNF(BigInteger value) {
                    this.montoNF = value;
                }

                /**
                 * Gets the value of the totalPeriodo property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigInteger }
                 *     
                 */
                public BigInteger getTotalPeriodo() {
                    return totalPeriodo;
                }

                /**
                 * Sets the value of the totalPeriodo property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigInteger }
                 *     
                 */
                public void setTotalPeriodo(BigInteger value) {
                    this.totalPeriodo = value;
                }

                /**
                 * Gets the value of the saldoAnterior property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigInteger }
                 *     
                 */
                public BigInteger getSaldoAnterior() {
                    return saldoAnterior;
                }

                /**
                 * Sets the value of the saldoAnterior property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigInteger }
                 *     
                 */
                public void setSaldoAnterior(BigInteger value) {
                    this.saldoAnterior = value;
                }

                /**
                 * Gets the value of the vlrPagar property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigInteger }
                 *     
                 */
                public BigInteger getVlrPagar() {
                    return vlrPagar;
                }

                /**
                 * Sets the value of the vlrPagar property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigInteger }
                 *     
                 */
                public void setVlrPagar(BigInteger value) {
                    this.vlrPagar = value;
                }

            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;sequence&gt;
         *         &lt;element name="LatitudEmision"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *               &lt;maxLength value="30"/&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="LongitudEmision"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *               &lt;maxLength value="30"/&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="SistemaReferencia"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
         *               &lt;maxInclusive value="9"/&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "latitudEmision",
            "longitudEmision",
            "sistemaReferencia"
        })
        public static class GeoRefEmision {

            @XmlElement(name = "LatitudEmision", namespace = "http://www.sii.cl/SiiDte", required = true)
            protected String latitudEmision;
            @XmlElement(name = "LongitudEmision", namespace = "http://www.sii.cl/SiiDte", required = true)
            protected String longitudEmision;
            @XmlElement(name = "SistemaReferencia", namespace = "http://www.sii.cl/SiiDte")
            protected int sistemaReferencia;

            /**
             * Gets the value of the latitudEmision property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLatitudEmision() {
                return latitudEmision;
            }

            /**
             * Sets the value of the latitudEmision property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLatitudEmision(String value) {
                this.latitudEmision = value;
            }

            /**
             * Gets the value of the longitudEmision property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLongitudEmision() {
                return longitudEmision;
            }

            /**
             * Sets the value of the longitudEmision property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLongitudEmision(String value) {
                this.longitudEmision = value;
            }

            /**
             * Gets the value of the sistemaReferencia property.
             * 
             */
            public int getSistemaReferencia() {
                return sistemaReferencia;
            }

            /**
             * Sets the value of the sistemaReferencia property.
             * 
             */
            public void setSistemaReferencia(int value) {
                this.sistemaReferencia = value;
            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;sequence&gt;
         *         &lt;element name="NroLinRef"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
         *               &lt;maxInclusive value="40"/&gt;
         *               &lt;minInclusive value="1"/&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="TpoDocRef" minOccurs="0"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *               &lt;maxLength value="3"/&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="FolioRef" minOccurs="0"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *               &lt;maxLength value="18"/&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="CodRef" minOccurs="0"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *               &lt;maxLength value="18"/&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="RazonRef" minOccurs="0"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *               &lt;maxLength value="90"/&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="CodVndor" minOccurs="0"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *               &lt;maxLength value="8"/&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="CodCaja" minOccurs="0"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *               &lt;maxLength value="8"/&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "nroLinRef",
            "tpoDocRef",
            "folioRef",
            "codRef",
            "razonRef",
            "codVndor",
            "codCaja"
        })
        public static class Referencia {

            @XmlElement(name = "NroLinRef", namespace = "http://www.sii.cl/SiiDte")
            protected int nroLinRef;
            @XmlElement(name = "TpoDocRef", namespace = "http://www.sii.cl/SiiDte")
            protected String tpoDocRef;
            @XmlElement(name = "FolioRef", namespace = "http://www.sii.cl/SiiDte")
            protected String folioRef;
            @XmlElement(name = "CodRef", namespace = "http://www.sii.cl/SiiDte")
            protected String codRef;
            @XmlElement(name = "RazonRef", namespace = "http://www.sii.cl/SiiDte")
            protected String razonRef;
            @XmlElement(name = "CodVndor", namespace = "http://www.sii.cl/SiiDte")
            protected String codVndor;
            @XmlElement(name = "CodCaja", namespace = "http://www.sii.cl/SiiDte")
            protected String codCaja;

            /**
             * Gets the value of the nroLinRef property.
             * 
             */
            public int getNroLinRef() {
                return nroLinRef;
            }

            /**
             * Sets the value of the nroLinRef property.
             * 
             */
            public void setNroLinRef(int value) {
                this.nroLinRef = value;
            }

            /**
             * Gets the value of the tpoDocRef property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTpoDocRef() {
                return tpoDocRef;
            }

            /**
             * Sets the value of the tpoDocRef property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTpoDocRef(String value) {
                this.tpoDocRef = value;
            }

            /**
             * Gets the value of the folioRef property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFolioRef() {
                return folioRef;
            }

            /**
             * Sets the value of the folioRef property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFolioRef(String value) {
                this.folioRef = value;
            }

            /**
             * Gets the value of the codRef property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCodRef() {
                return codRef;
            }

            /**
             * Sets the value of the codRef property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCodRef(String value) {
                this.codRef = value;
            }

            /**
             * Gets the value of the razonRef property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRazonRef() {
                return razonRef;
            }

            /**
             * Sets the value of the razonRef property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRazonRef(String value) {
                this.razonRef = value;
            }

            /**
             * Gets the value of the codVndor property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCodVndor() {
                return codVndor;
            }

            /**
             * Sets the value of the codVndor property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCodVndor(String value) {
                this.codVndor = value;
            }

            /**
             * Gets the value of the codCaja property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCodCaja() {
                return codCaja;
            }

            /**
             * Sets the value of the codCaja property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCodCaja(String value) {
                this.codCaja = value;
            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;sequence&gt;
         *         &lt;element name="NroSTI"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
         *               &lt;maxInclusive value="20"/&gt;
         *               &lt;minInclusive value="1"/&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="GlosaSTI" minOccurs="0"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *               &lt;maxLength value="80"/&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="OrdenSTI" minOccurs="0"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
         *               &lt;maxInclusive value="99"/&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="SubTotNetoSTI" minOccurs="0"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.sii.cl/SiiDte}Dec1Type"&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="SubTotIVASTI" minOccurs="0"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.sii.cl/SiiDte}Dec1Type"&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="SubTotAdicSTI" type="{http://www.sii.cl/SiiDte}Dec1Type" minOccurs="0"/&gt;
         *         &lt;element name="SubTotExeSTI" type="{http://www.sii.cl/SiiDte}Dec1Type" minOccurs="0"/&gt;
         *         &lt;element name="ValSubtotSTI" type="{http://www.sii.cl/SiiDte}Dec1Type" minOccurs="0"/&gt;
         *         &lt;element name="LineasDeta" maxOccurs="1000" minOccurs="0"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
         *               &lt;maxInclusive value="1000"/&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "nroSTI",
            "glosaSTI",
            "ordenSTI",
            "subTotNetoSTI",
            "subTotIVASTI",
            "subTotAdicSTI",
            "subTotExeSTI",
            "valSubtotSTI",
            "lineasDeta"
        })
        public static class SubTotInfo {

            @XmlElement(name = "NroSTI", namespace = "http://www.sii.cl/SiiDte")
            protected int nroSTI;
            @XmlElement(name = "GlosaSTI", namespace = "http://www.sii.cl/SiiDte")
            protected String glosaSTI;
            @XmlElement(name = "OrdenSTI", namespace = "http://www.sii.cl/SiiDte")
            protected Integer ordenSTI;
            @XmlElement(name = "SubTotNetoSTI", namespace = "http://www.sii.cl/SiiDte")
            protected BigDecimal subTotNetoSTI;
            @XmlElement(name = "SubTotIVASTI", namespace = "http://www.sii.cl/SiiDte")
            protected BigDecimal subTotIVASTI;
            @XmlElement(name = "SubTotAdicSTI", namespace = "http://www.sii.cl/SiiDte")
            protected BigDecimal subTotAdicSTI;
            @XmlElement(name = "SubTotExeSTI", namespace = "http://www.sii.cl/SiiDte")
            protected BigDecimal subTotExeSTI;
            @XmlElement(name = "ValSubtotSTI", namespace = "http://www.sii.cl/SiiDte")
            protected BigDecimal valSubtotSTI;
            @XmlElement(name = "LineasDeta", namespace = "http://www.sii.cl/SiiDte", type = Integer.class)
            protected List<Integer> lineasDeta;

            /**
             * Gets the value of the nroSTI property.
             * 
             */
            public int getNroSTI() {
                return nroSTI;
            }

            /**
             * Sets the value of the nroSTI property.
             * 
             */
            public void setNroSTI(int value) {
                this.nroSTI = value;
            }

            /**
             * Gets the value of the glosaSTI property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getGlosaSTI() {
                return glosaSTI;
            }

            /**
             * Sets the value of the glosaSTI property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setGlosaSTI(String value) {
                this.glosaSTI = value;
            }

            /**
             * Gets the value of the ordenSTI property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getOrdenSTI() {
                return ordenSTI;
            }

            /**
             * Sets the value of the ordenSTI property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setOrdenSTI(Integer value) {
                this.ordenSTI = value;
            }

            /**
             * Gets the value of the subTotNetoSTI property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getSubTotNetoSTI() {
                return subTotNetoSTI;
            }

            /**
             * Sets the value of the subTotNetoSTI property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setSubTotNetoSTI(BigDecimal value) {
                this.subTotNetoSTI = value;
            }

            /**
             * Gets the value of the subTotIVASTI property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getSubTotIVASTI() {
                return subTotIVASTI;
            }

            /**
             * Sets the value of the subTotIVASTI property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setSubTotIVASTI(BigDecimal value) {
                this.subTotIVASTI = value;
            }

            /**
             * Gets the value of the subTotAdicSTI property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getSubTotAdicSTI() {
                return subTotAdicSTI;
            }

            /**
             * Sets the value of the subTotAdicSTI property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setSubTotAdicSTI(BigDecimal value) {
                this.subTotAdicSTI = value;
            }

            /**
             * Gets the value of the subTotExeSTI property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getSubTotExeSTI() {
                return subTotExeSTI;
            }

            /**
             * Sets the value of the subTotExeSTI property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setSubTotExeSTI(BigDecimal value) {
                this.subTotExeSTI = value;
            }

            /**
             * Gets the value of the valSubtotSTI property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getValSubtotSTI() {
                return valSubtotSTI;
            }

            /**
             * Sets the value of the valSubtotSTI property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setValSubtotSTI(BigDecimal value) {
                this.valSubtotSTI = value;
            }

            /**
             * Gets the value of the lineasDeta property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the Jakarta XML Binding object.
             * This is why there is not a <CODE>set</CODE> method for the lineasDeta property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getLineasDeta().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Integer }
             * 
             * 
             */
            public List<Integer> getLineasDeta() {
                if (lineasDeta == null) {
                    lineasDeta = new ArrayList<Integer>();
                }
                return this.lineasDeta;
            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;sequence&gt;
         *         &lt;element name="DD"&gt;
         *           &lt;complexType&gt;
         *             &lt;complexContent&gt;
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                 &lt;sequence&gt;
         *                   &lt;element name="RE" type="{http://www.sii.cl/SiiDte}RUTType"/&gt;
         *                   &lt;element name="TD" type="{http://www.sii.cl/SiiDte}DTEType"/&gt;
         *                   &lt;element name="F" type="{http://www.sii.cl/SiiDte}FolioType"/&gt;
         *                   &lt;element name="FE" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
         *                   &lt;element name="RR" type="{http://www.sii.cl/SiiDte}RUTType"/&gt;
         *                   &lt;element name="RSR"&gt;
         *                     &lt;simpleType&gt;
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                         &lt;maxLength value="40"/&gt;
         *                       &lt;/restriction&gt;
         *                     &lt;/simpleType&gt;
         *                   &lt;/element&gt;
         *                   &lt;element name="MNT" type="{http://www.w3.org/2001/XMLSchema}unsignedLong"/&gt;
         *                   &lt;element name="IT1"&gt;
         *                     &lt;simpleType&gt;
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                         &lt;maxLength value="40"/&gt;
         *                       &lt;/restriction&gt;
         *                     &lt;/simpleType&gt;
         *                   &lt;/element&gt;
         *                   &lt;element name="CAF"&gt;
         *                     &lt;complexType&gt;
         *                       &lt;complexContent&gt;
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                           &lt;sequence&gt;
         *                             &lt;element name="DA"&gt;
         *                               &lt;complexType&gt;
         *                                 &lt;complexContent&gt;
         *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                                     &lt;sequence&gt;
         *                                       &lt;element name="RE" type="{http://www.sii.cl/SiiDte}RUTType"/&gt;
         *                                       &lt;element name="RS"&gt;
         *                                         &lt;simpleType&gt;
         *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                                             &lt;maxLength value="40"/&gt;
         *                                           &lt;/restriction&gt;
         *                                         &lt;/simpleType&gt;
         *                                       &lt;/element&gt;
         *                                       &lt;element name="TD" type="{http://www.sii.cl/SiiDte}DTEType"/&gt;
         *                                       &lt;element name="RNG"&gt;
         *                                         &lt;complexType&gt;
         *                                           &lt;complexContent&gt;
         *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                                               &lt;sequence&gt;
         *                                                 &lt;element name="D" type="{http://www.sii.cl/SiiDte}FolioType"/&gt;
         *                                                 &lt;element name="H" type="{http://www.sii.cl/SiiDte}FolioType"/&gt;
         *                                               &lt;/sequence&gt;
         *                                             &lt;/restriction&gt;
         *                                           &lt;/complexContent&gt;
         *                                         &lt;/complexType&gt;
         *                                       &lt;/element&gt;
         *                                       &lt;element name="FA" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
         *                                       &lt;choice&gt;
         *                                         &lt;element name="RSAPK"&gt;
         *                                           &lt;complexType&gt;
         *                                             &lt;complexContent&gt;
         *                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                                                 &lt;sequence&gt;
         *                                                   &lt;element name="M" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
         *                                                   &lt;element name="E" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
         *                                                 &lt;/sequence&gt;
         *                                               &lt;/restriction&gt;
         *                                             &lt;/complexContent&gt;
         *                                           &lt;/complexType&gt;
         *                                         &lt;/element&gt;
         *                                         &lt;element name="DSAPK"&gt;
         *                                           &lt;complexType&gt;
         *                                             &lt;complexContent&gt;
         *                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                                                 &lt;sequence&gt;
         *                                                   &lt;element name="P" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
         *                                                   &lt;element name="Q" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
         *                                                   &lt;element name="G" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
         *                                                   &lt;element name="Y" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
         *                                                 &lt;/sequence&gt;
         *                                               &lt;/restriction&gt;
         *                                             &lt;/complexContent&gt;
         *                                           &lt;/complexType&gt;
         *                                         &lt;/element&gt;
         *                                       &lt;/choice&gt;
         *                                       &lt;element name="IDK" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
         *                                     &lt;/sequence&gt;
         *                                   &lt;/restriction&gt;
         *                                 &lt;/complexContent&gt;
         *                               &lt;/complexType&gt;
         *                             &lt;/element&gt;
         *                             &lt;element name="FRMA"&gt;
         *                               &lt;complexType&gt;
         *                                 &lt;simpleContent&gt;
         *                                   &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;base64Binary"&gt;
         *                                     &lt;attribute name="algoritmo" use="required" type="{http://www.w3.org/2001/XMLSchema}string" fixed="SHA1withRSA" /&gt;
         *                                   &lt;/extension&gt;
         *                                 &lt;/simpleContent&gt;
         *                               &lt;/complexType&gt;
         *                             &lt;/element&gt;
         *                           &lt;/sequence&gt;
         *                           &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" fixed="1.0" /&gt;
         *                         &lt;/restriction&gt;
         *                       &lt;/complexContent&gt;
         *                     &lt;/complexType&gt;
         *                   &lt;/element&gt;
         *                   &lt;element name="TSTED" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
         *                 &lt;/sequence&gt;
         *               &lt;/restriction&gt;
         *             &lt;/complexContent&gt;
         *           &lt;/complexType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="FRMT"&gt;
         *           &lt;complexType&gt;
         *             &lt;simpleContent&gt;
         *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;base64Binary"&gt;
         *                 &lt;attribute name="algoritmo" use="required"&gt;
         *                   &lt;simpleType&gt;
         *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                       &lt;enumeration value="SHA1withRSA"/&gt;
         *                       &lt;enumeration value="SHA1withDSA"/&gt;
         *                     &lt;/restriction&gt;
         *                   &lt;/simpleType&gt;
         *                 &lt;/attribute&gt;
         *               &lt;/extension&gt;
         *             &lt;/simpleContent&gt;
         *           &lt;/complexType&gt;
         *         &lt;/element&gt;
         *       &lt;/sequence&gt;
         *       &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" fixed="1.0" /&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "dd",
            "frmt"
        })
        public static class TED {

            @XmlElement(name = "DD", namespace = "http://www.sii.cl/SiiDte", required = true)
            protected BOLETADefType.Documento.TED.DD dd;
            @XmlElement(name = "FRMT", namespace = "http://www.sii.cl/SiiDte", required = true)
            protected BOLETADefType.Documento.TED.FRMT frmt;
            @XmlAttribute(name = "version", required = true)
            @XmlSchemaType(name = "anySimpleType")
            protected String version;

            /**
             * Gets the value of the dd property.
             * 
             * @return
             *     possible object is
             *     {@link BOLETADefType.Documento.TED.DD }
             *     
             */
            public BOLETADefType.Documento.TED.DD getDD() {
                return dd;
            }

            /**
             * Sets the value of the dd property.
             * 
             * @param value
             *     allowed object is
             *     {@link BOLETADefType.Documento.TED.DD }
             *     
             */
            public void setDD(BOLETADefType.Documento.TED.DD value) {
                this.dd = value;
            }

            /**
             * Gets the value of the frmt property.
             * 
             * @return
             *     possible object is
             *     {@link BOLETADefType.Documento.TED.FRMT }
             *     
             */
            public BOLETADefType.Documento.TED.FRMT getFRMT() {
                return frmt;
            }

            /**
             * Sets the value of the frmt property.
             * 
             * @param value
             *     allowed object is
             *     {@link BOLETADefType.Documento.TED.FRMT }
             *     
             */
            public void setFRMT(BOLETADefType.Documento.TED.FRMT value) {
                this.frmt = value;
            }

            /**
             * Gets the value of the version property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVersion() {
                if (version == null) {
                    return "1.0";
                } else {
                    return version;
                }
            }

            /**
             * Sets the value of the version property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVersion(String value) {
                this.version = value;
            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType&gt;
             *   &lt;complexContent&gt;
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
             *       &lt;sequence&gt;
             *         &lt;element name="RE" type="{http://www.sii.cl/SiiDte}RUTType"/&gt;
             *         &lt;element name="TD" type="{http://www.sii.cl/SiiDte}DTEType"/&gt;
             *         &lt;element name="F" type="{http://www.sii.cl/SiiDte}FolioType"/&gt;
             *         &lt;element name="FE" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
             *         &lt;element name="RR" type="{http://www.sii.cl/SiiDte}RUTType"/&gt;
             *         &lt;element name="RSR"&gt;
             *           &lt;simpleType&gt;
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *               &lt;maxLength value="40"/&gt;
             *             &lt;/restriction&gt;
             *           &lt;/simpleType&gt;
             *         &lt;/element&gt;
             *         &lt;element name="MNT" type="{http://www.w3.org/2001/XMLSchema}unsignedLong"/&gt;
             *         &lt;element name="IT1"&gt;
             *           &lt;simpleType&gt;
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *               &lt;maxLength value="40"/&gt;
             *             &lt;/restriction&gt;
             *           &lt;/simpleType&gt;
             *         &lt;/element&gt;
             *         &lt;element name="CAF"&gt;
             *           &lt;complexType&gt;
             *             &lt;complexContent&gt;
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
             *                 &lt;sequence&gt;
             *                   &lt;element name="DA"&gt;
             *                     &lt;complexType&gt;
             *                       &lt;complexContent&gt;
             *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
             *                           &lt;sequence&gt;
             *                             &lt;element name="RE" type="{http://www.sii.cl/SiiDte}RUTType"/&gt;
             *                             &lt;element name="RS"&gt;
             *                               &lt;simpleType&gt;
             *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *                                   &lt;maxLength value="40"/&gt;
             *                                 &lt;/restriction&gt;
             *                               &lt;/simpleType&gt;
             *                             &lt;/element&gt;
             *                             &lt;element name="TD" type="{http://www.sii.cl/SiiDte}DTEType"/&gt;
             *                             &lt;element name="RNG"&gt;
             *                               &lt;complexType&gt;
             *                                 &lt;complexContent&gt;
             *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
             *                                     &lt;sequence&gt;
             *                                       &lt;element name="D" type="{http://www.sii.cl/SiiDte}FolioType"/&gt;
             *                                       &lt;element name="H" type="{http://www.sii.cl/SiiDte}FolioType"/&gt;
             *                                     &lt;/sequence&gt;
             *                                   &lt;/restriction&gt;
             *                                 &lt;/complexContent&gt;
             *                               &lt;/complexType&gt;
             *                             &lt;/element&gt;
             *                             &lt;element name="FA" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
             *                             &lt;choice&gt;
             *                               &lt;element name="RSAPK"&gt;
             *                                 &lt;complexType&gt;
             *                                   &lt;complexContent&gt;
             *                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
             *                                       &lt;sequence&gt;
             *                                         &lt;element name="M" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
             *                                         &lt;element name="E" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
             *                                       &lt;/sequence&gt;
             *                                     &lt;/restriction&gt;
             *                                   &lt;/complexContent&gt;
             *                                 &lt;/complexType&gt;
             *                               &lt;/element&gt;
             *                               &lt;element name="DSAPK"&gt;
             *                                 &lt;complexType&gt;
             *                                   &lt;complexContent&gt;
             *                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
             *                                       &lt;sequence&gt;
             *                                         &lt;element name="P" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
             *                                         &lt;element name="Q" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
             *                                         &lt;element name="G" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
             *                                         &lt;element name="Y" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
             *                                       &lt;/sequence&gt;
             *                                     &lt;/restriction&gt;
             *                                   &lt;/complexContent&gt;
             *                                 &lt;/complexType&gt;
             *                               &lt;/element&gt;
             *                             &lt;/choice&gt;
             *                             &lt;element name="IDK" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
             *                           &lt;/sequence&gt;
             *                         &lt;/restriction&gt;
             *                       &lt;/complexContent&gt;
             *                     &lt;/complexType&gt;
             *                   &lt;/element&gt;
             *                   &lt;element name="FRMA"&gt;
             *                     &lt;complexType&gt;
             *                       &lt;simpleContent&gt;
             *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;base64Binary"&gt;
             *                           &lt;attribute name="algoritmo" use="required" type="{http://www.w3.org/2001/XMLSchema}string" fixed="SHA1withRSA" /&gt;
             *                         &lt;/extension&gt;
             *                       &lt;/simpleContent&gt;
             *                     &lt;/complexType&gt;
             *                   &lt;/element&gt;
             *                 &lt;/sequence&gt;
             *                 &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" fixed="1.0" /&gt;
             *               &lt;/restriction&gt;
             *             &lt;/complexContent&gt;
             *           &lt;/complexType&gt;
             *         &lt;/element&gt;
             *         &lt;element name="TSTED" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
             *       &lt;/sequence&gt;
             *     &lt;/restriction&gt;
             *   &lt;/complexContent&gt;
             * &lt;/complexType&gt;
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "re",
                "td",
                "f",
                "fe",
                "rr",
                "rsr",
                "mnt",
                "it1",
                "caf",
                "tsted"
            })
            public static class DD {

                @XmlElement(name = "RE", namespace = "http://www.sii.cl/SiiDte", required = true)
                protected String re;
                @XmlElement(name = "TD", namespace = "http://www.sii.cl/SiiDte", required = true)
                @XmlSchemaType(name = "positiveInteger")
                protected BigInteger td;
                @XmlElement(name = "F", namespace = "http://www.sii.cl/SiiDte", required = true)
                @XmlSchemaType(name = "positiveInteger")
                protected BigInteger f;
                @XmlElement(name = "FE", namespace = "http://www.sii.cl/SiiDte", required = true)
                @XmlSchemaType(name = "date")
                protected XMLGregorianCalendar fe;
                @XmlElement(name = "RR", namespace = "http://www.sii.cl/SiiDte", required = true)
                protected String rr;
                @XmlElement(name = "RSR", namespace = "http://www.sii.cl/SiiDte", required = true)
                protected String rsr;
                @XmlElement(name = "MNT", namespace = "http://www.sii.cl/SiiDte", required = true)
                @XmlSchemaType(name = "unsignedLong")
                protected BigInteger mnt;
                @XmlElement(name = "IT1", namespace = "http://www.sii.cl/SiiDte", required = true)
                protected String it1;
                @XmlElement(name = "CAF", namespace = "http://www.sii.cl/SiiDte", required = true)
                protected BOLETADefType.Documento.TED.DD.CAF caf;
                @XmlElement(name = "TSTED", namespace = "http://www.sii.cl/SiiDte", required = true)
                @XmlSchemaType(name = "dateTime")
                protected XMLGregorianCalendar tsted;

                /**
                 * Gets the value of the re property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getRE() {
                    return re;
                }

                /**
                 * Sets the value of the re property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setRE(String value) {
                    this.re = value;
                }

                /**
                 * Gets the value of the td property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigInteger }
                 *     
                 */
                public BigInteger getTD() {
                    return td;
                }

                /**
                 * Sets the value of the td property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigInteger }
                 *     
                 */
                public void setTD(BigInteger value) {
                    this.td = value;
                }

                /**
                 * Gets the value of the f property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigInteger }
                 *     
                 */
                public BigInteger getF() {
                    return f;
                }

                /**
                 * Sets the value of the f property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigInteger }
                 *     
                 */
                public void setF(BigInteger value) {
                    this.f = value;
                }

                /**
                 * Gets the value of the fe property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link XMLGregorianCalendar }
                 *     
                 */
                public XMLGregorianCalendar getFE() {
                    return fe;
                }

                /**
                 * Sets the value of the fe property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link XMLGregorianCalendar }
                 *     
                 */
                public void setFE(XMLGregorianCalendar value) {
                    this.fe = value;
                }

                /**
                 * Gets the value of the rr property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getRR() {
                    return rr;
                }

                /**
                 * Sets the value of the rr property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setRR(String value) {
                    this.rr = value;
                }

                /**
                 * Gets the value of the rsr property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getRSR() {
                    return rsr;
                }

                /**
                 * Sets the value of the rsr property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setRSR(String value) {
                    this.rsr = value;
                }

                /**
                 * Gets the value of the mnt property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigInteger }
                 *     
                 */
                public BigInteger getMNT() {
                    return mnt;
                }

                /**
                 * Sets the value of the mnt property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigInteger }
                 *     
                 */
                public void setMNT(BigInteger value) {
                    this.mnt = value;
                }

                /**
                 * Gets the value of the it1 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getIT1() {
                    return it1;
                }

                /**
                 * Sets the value of the it1 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setIT1(String value) {
                    this.it1 = value;
                }

                /**
                 * Gets the value of the caf property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BOLETADefType.Documento.TED.DD.CAF }
                 *     
                 */
                public BOLETADefType.Documento.TED.DD.CAF getCAF() {
                    return caf;
                }

                /**
                 * Sets the value of the caf property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BOLETADefType.Documento.TED.DD.CAF }
                 *     
                 */
                public void setCAF(BOLETADefType.Documento.TED.DD.CAF value) {
                    this.caf = value;
                }

                /**
                 * Gets the value of the tsted property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link XMLGregorianCalendar }
                 *     
                 */
                public XMLGregorianCalendar getTSTED() {
                    return tsted;
                }

                /**
                 * Sets the value of the tsted property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link XMLGregorianCalendar }
                 *     
                 */
                public void setTSTED(XMLGregorianCalendar value) {
                    this.tsted = value;
                }


                /**
                 * <p>Java class for anonymous complex type.
                 * 
                 * <p>The following schema fragment specifies the expected content contained within this class.
                 * 
                 * <pre>
                 * &lt;complexType&gt;
                 *   &lt;complexContent&gt;
                 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
                 *       &lt;sequence&gt;
                 *         &lt;element name="DA"&gt;
                 *           &lt;complexType&gt;
                 *             &lt;complexContent&gt;
                 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
                 *                 &lt;sequence&gt;
                 *                   &lt;element name="RE" type="{http://www.sii.cl/SiiDte}RUTType"/&gt;
                 *                   &lt;element name="RS"&gt;
                 *                     &lt;simpleType&gt;
                 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
                 *                         &lt;maxLength value="40"/&gt;
                 *                       &lt;/restriction&gt;
                 *                     &lt;/simpleType&gt;
                 *                   &lt;/element&gt;
                 *                   &lt;element name="TD" type="{http://www.sii.cl/SiiDte}DTEType"/&gt;
                 *                   &lt;element name="RNG"&gt;
                 *                     &lt;complexType&gt;
                 *                       &lt;complexContent&gt;
                 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
                 *                           &lt;sequence&gt;
                 *                             &lt;element name="D" type="{http://www.sii.cl/SiiDte}FolioType"/&gt;
                 *                             &lt;element name="H" type="{http://www.sii.cl/SiiDte}FolioType"/&gt;
                 *                           &lt;/sequence&gt;
                 *                         &lt;/restriction&gt;
                 *                       &lt;/complexContent&gt;
                 *                     &lt;/complexType&gt;
                 *                   &lt;/element&gt;
                 *                   &lt;element name="FA" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
                 *                   &lt;choice&gt;
                 *                     &lt;element name="RSAPK"&gt;
                 *                       &lt;complexType&gt;
                 *                         &lt;complexContent&gt;
                 *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
                 *                             &lt;sequence&gt;
                 *                               &lt;element name="M" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
                 *                               &lt;element name="E" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
                 *                             &lt;/sequence&gt;
                 *                           &lt;/restriction&gt;
                 *                         &lt;/complexContent&gt;
                 *                       &lt;/complexType&gt;
                 *                     &lt;/element&gt;
                 *                     &lt;element name="DSAPK"&gt;
                 *                       &lt;complexType&gt;
                 *                         &lt;complexContent&gt;
                 *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
                 *                             &lt;sequence&gt;
                 *                               &lt;element name="P" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
                 *                               &lt;element name="Q" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
                 *                               &lt;element name="G" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
                 *                               &lt;element name="Y" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
                 *                             &lt;/sequence&gt;
                 *                           &lt;/restriction&gt;
                 *                         &lt;/complexContent&gt;
                 *                       &lt;/complexType&gt;
                 *                     &lt;/element&gt;
                 *                   &lt;/choice&gt;
                 *                   &lt;element name="IDK" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
                 *                 &lt;/sequence&gt;
                 *               &lt;/restriction&gt;
                 *             &lt;/complexContent&gt;
                 *           &lt;/complexType&gt;
                 *         &lt;/element&gt;
                 *         &lt;element name="FRMA"&gt;
                 *           &lt;complexType&gt;
                 *             &lt;simpleContent&gt;
                 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;base64Binary"&gt;
                 *                 &lt;attribute name="algoritmo" use="required" type="{http://www.w3.org/2001/XMLSchema}string" fixed="SHA1withRSA" /&gt;
                 *               &lt;/extension&gt;
                 *             &lt;/simpleContent&gt;
                 *           &lt;/complexType&gt;
                 *         &lt;/element&gt;
                 *       &lt;/sequence&gt;
                 *       &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" fixed="1.0" /&gt;
                 *     &lt;/restriction&gt;
                 *   &lt;/complexContent&gt;
                 * &lt;/complexType&gt;
                 * </pre>
                 * 
                 * 
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                    "da",
                    "frma"
                })
                public static class CAF {

                    @XmlElement(name = "DA", namespace = "http://www.sii.cl/SiiDte", required = true)
                    protected BOLETADefType.Documento.TED.DD.CAF.DA da;
                    @XmlElement(name = "FRMA", namespace = "http://www.sii.cl/SiiDte", required = true)
                    protected BOLETADefType.Documento.TED.DD.CAF.FRMA frma;
                    @XmlAttribute(name = "version", required = true)
                    @XmlSchemaType(name = "anySimpleType")
                    protected String version;

                    /**
                     * Gets the value of the da property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link BOLETADefType.Documento.TED.DD.CAF.DA }
                     *     
                     */
                    public BOLETADefType.Documento.TED.DD.CAF.DA getDA() {
                        return da;
                    }

                    /**
                     * Sets the value of the da property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link BOLETADefType.Documento.TED.DD.CAF.DA }
                     *     
                     */
                    public void setDA(BOLETADefType.Documento.TED.DD.CAF.DA value) {
                        this.da = value;
                    }

                    /**
                     * Gets the value of the frma property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link BOLETADefType.Documento.TED.DD.CAF.FRMA }
                     *     
                     */
                    public BOLETADefType.Documento.TED.DD.CAF.FRMA getFRMA() {
                        return frma;
                    }

                    /**
                     * Sets the value of the frma property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link BOLETADefType.Documento.TED.DD.CAF.FRMA }
                     *     
                     */
                    public void setFRMA(BOLETADefType.Documento.TED.DD.CAF.FRMA value) {
                        this.frma = value;
                    }

                    /**
                     * Gets the value of the version property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getVersion() {
                        if (version == null) {
                            return "1.0";
                        } else {
                            return version;
                        }
                    }

                    /**
                     * Sets the value of the version property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setVersion(String value) {
                        this.version = value;
                    }


                    /**
                     * <p>Java class for anonymous complex type.
                     * 
                     * <p>The following schema fragment specifies the expected content contained within this class.
                     * 
                     * <pre>
                     * &lt;complexType&gt;
                     *   &lt;complexContent&gt;
                     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
                     *       &lt;sequence&gt;
                     *         &lt;element name="RE" type="{http://www.sii.cl/SiiDte}RUTType"/&gt;
                     *         &lt;element name="RS"&gt;
                     *           &lt;simpleType&gt;
                     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
                     *               &lt;maxLength value="40"/&gt;
                     *             &lt;/restriction&gt;
                     *           &lt;/simpleType&gt;
                     *         &lt;/element&gt;
                     *         &lt;element name="TD" type="{http://www.sii.cl/SiiDte}DTEType"/&gt;
                     *         &lt;element name="RNG"&gt;
                     *           &lt;complexType&gt;
                     *             &lt;complexContent&gt;
                     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
                     *                 &lt;sequence&gt;
                     *                   &lt;element name="D" type="{http://www.sii.cl/SiiDte}FolioType"/&gt;
                     *                   &lt;element name="H" type="{http://www.sii.cl/SiiDte}FolioType"/&gt;
                     *                 &lt;/sequence&gt;
                     *               &lt;/restriction&gt;
                     *             &lt;/complexContent&gt;
                     *           &lt;/complexType&gt;
                     *         &lt;/element&gt;
                     *         &lt;element name="FA" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
                     *         &lt;choice&gt;
                     *           &lt;element name="RSAPK"&gt;
                     *             &lt;complexType&gt;
                     *               &lt;complexContent&gt;
                     *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
                     *                   &lt;sequence&gt;
                     *                     &lt;element name="M" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
                     *                     &lt;element name="E" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
                     *                   &lt;/sequence&gt;
                     *                 &lt;/restriction&gt;
                     *               &lt;/complexContent&gt;
                     *             &lt;/complexType&gt;
                     *           &lt;/element&gt;
                     *           &lt;element name="DSAPK"&gt;
                     *             &lt;complexType&gt;
                     *               &lt;complexContent&gt;
                     *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
                     *                   &lt;sequence&gt;
                     *                     &lt;element name="P" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
                     *                     &lt;element name="Q" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
                     *                     &lt;element name="G" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
                     *                     &lt;element name="Y" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
                     *                   &lt;/sequence&gt;
                     *                 &lt;/restriction&gt;
                     *               &lt;/complexContent&gt;
                     *             &lt;/complexType&gt;
                     *           &lt;/element&gt;
                     *         &lt;/choice&gt;
                     *         &lt;element name="IDK" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
                     *       &lt;/sequence&gt;
                     *     &lt;/restriction&gt;
                     *   &lt;/complexContent&gt;
                     * &lt;/complexType&gt;
                     * </pre>
                     * 
                     * 
                     */
                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "", propOrder = {
                        "re",
                        "rs",
                        "td",
                        "rng",
                        "fa",
                        "rsapk",
                        "dsapk",
                        "idk"
                    })
                    public static class DA {

                        @XmlElement(name = "RE", namespace = "http://www.sii.cl/SiiDte", required = true)
                        protected String re;
                        @XmlElement(name = "RS", namespace = "http://www.sii.cl/SiiDte", required = true)
                        protected String rs;
                        @XmlElement(name = "TD", namespace = "http://www.sii.cl/SiiDte", required = true)
                        @XmlSchemaType(name = "positiveInteger")
                        protected BigInteger td;
                        @XmlElement(name = "RNG", namespace = "http://www.sii.cl/SiiDte", required = true)
                        protected BOLETADefType.Documento.TED.DD.CAF.DA.RNG rng;
                        @XmlElement(name = "FA", namespace = "http://www.sii.cl/SiiDte", required = true)
                        @XmlSchemaType(name = "date")
                        protected XMLGregorianCalendar fa;
                        @XmlElement(name = "RSAPK", namespace = "http://www.sii.cl/SiiDte")
                        protected BOLETADefType.Documento.TED.DD.CAF.DA.RSAPK rsapk;
                        @XmlElement(name = "DSAPK", namespace = "http://www.sii.cl/SiiDte")
                        protected BOLETADefType.Documento.TED.DD.CAF.DA.DSAPK dsapk;
                        @XmlElement(name = "IDK", namespace = "http://www.sii.cl/SiiDte")
                        protected long idk;

                        /**
                         * Gets the value of the re property.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getRE() {
                            return re;
                        }

                        /**
                         * Sets the value of the re property.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setRE(String value) {
                            this.re = value;
                        }

                        /**
                         * Gets the value of the rs property.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getRS() {
                            return rs;
                        }

                        /**
                         * Sets the value of the rs property.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setRS(String value) {
                            this.rs = value;
                        }

                        /**
                         * Gets the value of the td property.
                         * 
                         * @return
                         *     possible object is
                         *     {@link BigInteger }
                         *     
                         */
                        public BigInteger getTD() {
                            return td;
                        }

                        /**
                         * Sets the value of the td property.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link BigInteger }
                         *     
                         */
                        public void setTD(BigInteger value) {
                            this.td = value;
                        }

                        /**
                         * Gets the value of the rng property.
                         * 
                         * @return
                         *     possible object is
                         *     {@link BOLETADefType.Documento.TED.DD.CAF.DA.RNG }
                         *     
                         */
                        public BOLETADefType.Documento.TED.DD.CAF.DA.RNG getRNG() {
                            return rng;
                        }

                        /**
                         * Sets the value of the rng property.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link BOLETADefType.Documento.TED.DD.CAF.DA.RNG }
                         *     
                         */
                        public void setRNG(BOLETADefType.Documento.TED.DD.CAF.DA.RNG value) {
                            this.rng = value;
                        }

                        /**
                         * Gets the value of the fa property.
                         * 
                         * @return
                         *     possible object is
                         *     {@link XMLGregorianCalendar }
                         *     
                         */
                        public XMLGregorianCalendar getFA() {
                            return fa;
                        }

                        /**
                         * Sets the value of the fa property.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link XMLGregorianCalendar }
                         *     
                         */
                        public void setFA(XMLGregorianCalendar value) {
                            this.fa = value;
                        }

                        /**
                         * Gets the value of the rsapk property.
                         * 
                         * @return
                         *     possible object is
                         *     {@link BOLETADefType.Documento.TED.DD.CAF.DA.RSAPK }
                         *     
                         */
                        public BOLETADefType.Documento.TED.DD.CAF.DA.RSAPK getRSAPK() {
                            return rsapk;
                        }

                        /**
                         * Sets the value of the rsapk property.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link BOLETADefType.Documento.TED.DD.CAF.DA.RSAPK }
                         *     
                         */
                        public void setRSAPK(BOLETADefType.Documento.TED.DD.CAF.DA.RSAPK value) {
                            this.rsapk = value;
                        }

                        /**
                         * Gets the value of the dsapk property.
                         * 
                         * @return
                         *     possible object is
                         *     {@link BOLETADefType.Documento.TED.DD.CAF.DA.DSAPK }
                         *     
                         */
                        public BOLETADefType.Documento.TED.DD.CAF.DA.DSAPK getDSAPK() {
                            return dsapk;
                        }

                        /**
                         * Sets the value of the dsapk property.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link BOLETADefType.Documento.TED.DD.CAF.DA.DSAPK }
                         *     
                         */
                        public void setDSAPK(BOLETADefType.Documento.TED.DD.CAF.DA.DSAPK value) {
                            this.dsapk = value;
                        }

                        /**
                         * Gets the value of the idk property.
                         * 
                         */
                        public long getIDK() {
                            return idk;
                        }

                        /**
                         * Sets the value of the idk property.
                         * 
                         */
                        public void setIDK(long value) {
                            this.idk = value;
                        }


                        /**
                         * <p>Java class for anonymous complex type.
                         * 
                         * <p>The following schema fragment specifies the expected content contained within this class.
                         * 
                         * <pre>
                         * &lt;complexType&gt;
                         *   &lt;complexContent&gt;
                         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
                         *       &lt;sequence&gt;
                         *         &lt;element name="P" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
                         *         &lt;element name="Q" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
                         *         &lt;element name="G" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
                         *         &lt;element name="Y" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
                         *       &lt;/sequence&gt;
                         *     &lt;/restriction&gt;
                         *   &lt;/complexContent&gt;
                         * &lt;/complexType&gt;
                         * </pre>
                         * 
                         * 
                         */
                        @XmlAccessorType(XmlAccessType.FIELD)
                        @XmlType(name = "", propOrder = {
                            "p",
                            "q",
                            "g",
                            "y"
                        })
                        public static class DSAPK {

                            @XmlElement(name = "P", namespace = "http://www.sii.cl/SiiDte", required = true)
                            protected byte[] p;
                            @XmlElement(name = "Q", namespace = "http://www.sii.cl/SiiDte", required = true)
                            protected byte[] q;
                            @XmlElement(name = "G", namespace = "http://www.sii.cl/SiiDte", required = true)
                            protected byte[] g;
                            @XmlElement(name = "Y", namespace = "http://www.sii.cl/SiiDte", required = true)
                            protected byte[] y;

                            /**
                             * Gets the value of the p property.
                             * 
                             * @return
                             *     possible object is
                             *     byte[]
                             */
                            public byte[] getP() {
                                return p;
                            }

                            /**
                             * Sets the value of the p property.
                             * 
                             * @param value
                             *     allowed object is
                             *     byte[]
                             */
                            public void setP(byte[] value) {
                                this.p = value;
                            }

                            /**
                             * Gets the value of the q property.
                             * 
                             * @return
                             *     possible object is
                             *     byte[]
                             */
                            public byte[] getQ() {
                                return q;
                            }

                            /**
                             * Sets the value of the q property.
                             * 
                             * @param value
                             *     allowed object is
                             *     byte[]
                             */
                            public void setQ(byte[] value) {
                                this.q = value;
                            }

                            /**
                             * Gets the value of the g property.
                             * 
                             * @return
                             *     possible object is
                             *     byte[]
                             */
                            public byte[] getG() {
                                return g;
                            }

                            /**
                             * Sets the value of the g property.
                             * 
                             * @param value
                             *     allowed object is
                             *     byte[]
                             */
                            public void setG(byte[] value) {
                                this.g = value;
                            }

                            /**
                             * Gets the value of the y property.
                             * 
                             * @return
                             *     possible object is
                             *     byte[]
                             */
                            public byte[] getY() {
                                return y;
                            }

                            /**
                             * Sets the value of the y property.
                             * 
                             * @param value
                             *     allowed object is
                             *     byte[]
                             */
                            public void setY(byte[] value) {
                                this.y = value;
                            }

                        }


                        /**
                         * <p>Java class for anonymous complex type.
                         * 
                         * <p>The following schema fragment specifies the expected content contained within this class.
                         * 
                         * <pre>
                         * &lt;complexType&gt;
                         *   &lt;complexContent&gt;
                         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
                         *       &lt;sequence&gt;
                         *         &lt;element name="D" type="{http://www.sii.cl/SiiDte}FolioType"/&gt;
                         *         &lt;element name="H" type="{http://www.sii.cl/SiiDte}FolioType"/&gt;
                         *       &lt;/sequence&gt;
                         *     &lt;/restriction&gt;
                         *   &lt;/complexContent&gt;
                         * &lt;/complexType&gt;
                         * </pre>
                         * 
                         * 
                         */
                        @XmlAccessorType(XmlAccessType.FIELD)
                        @XmlType(name = "", propOrder = {
                            "d",
                            "h"
                        })
                        public static class RNG {

                            @XmlElement(name = "D", namespace = "http://www.sii.cl/SiiDte", required = true)
                            @XmlSchemaType(name = "positiveInteger")
                            protected BigInteger d;
                            @XmlElement(name = "H", namespace = "http://www.sii.cl/SiiDte", required = true)
                            @XmlSchemaType(name = "positiveInteger")
                            protected BigInteger h;

                            /**
                             * Gets the value of the d property.
                             * 
                             * @return
                             *     possible object is
                             *     {@link BigInteger }
                             *     
                             */
                            public BigInteger getD() {
                                return d;
                            }

                            /**
                             * Sets the value of the d property.
                             * 
                             * @param value
                             *     allowed object is
                             *     {@link BigInteger }
                             *     
                             */
                            public void setD(BigInteger value) {
                                this.d = value;
                            }

                            /**
                             * Gets the value of the h property.
                             * 
                             * @return
                             *     possible object is
                             *     {@link BigInteger }
                             *     
                             */
                            public BigInteger getH() {
                                return h;
                            }

                            /**
                             * Sets the value of the h property.
                             * 
                             * @param value
                             *     allowed object is
                             *     {@link BigInteger }
                             *     
                             */
                            public void setH(BigInteger value) {
                                this.h = value;
                            }

                        }


                        /**
                         * <p>Java class for anonymous complex type.
                         * 
                         * <p>The following schema fragment specifies the expected content contained within this class.
                         * 
                         * <pre>
                         * &lt;complexType&gt;
                         *   &lt;complexContent&gt;
                         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
                         *       &lt;sequence&gt;
                         *         &lt;element name="M" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
                         *         &lt;element name="E" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
                         *       &lt;/sequence&gt;
                         *     &lt;/restriction&gt;
                         *   &lt;/complexContent&gt;
                         * &lt;/complexType&gt;
                         * </pre>
                         * 
                         * 
                         */
                        @XmlAccessorType(XmlAccessType.FIELD)
                        @XmlType(name = "", propOrder = {
                            "m",
                            "e"
                        })
                        public static class RSAPK {

                            @XmlElement(name = "M", namespace = "http://www.sii.cl/SiiDte", required = true)
                            protected byte[] m;
                            @XmlElement(name = "E", namespace = "http://www.sii.cl/SiiDte", required = true)
                            protected byte[] e;

                            /**
                             * Gets the value of the m property.
                             * 
                             * @return
                             *     possible object is
                             *     byte[]
                             */
                            public byte[] getM() {
                                return m;
                            }

                            /**
                             * Sets the value of the m property.
                             * 
                             * @param value
                             *     allowed object is
                             *     byte[]
                             */
                            public void setM(byte[] value) {
                                this.m = value;
                            }

                            /**
                             * Gets the value of the e property.
                             * 
                             * @return
                             *     possible object is
                             *     byte[]
                             */
                            public byte[] getE() {
                                return e;
                            }

                            /**
                             * Sets the value of the e property.
                             * 
                             * @param value
                             *     allowed object is
                             *     byte[]
                             */
                            public void setE(byte[] value) {
                                this.e = value;
                            }

                        }

                    }


                    /**
                     * <p>Java class for anonymous complex type.
                     * 
                     * <p>The following schema fragment specifies the expected content contained within this class.
                     * 
                     * <pre>
                     * &lt;complexType&gt;
                     *   &lt;simpleContent&gt;
                     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;base64Binary"&gt;
                     *       &lt;attribute name="algoritmo" use="required" type="{http://www.w3.org/2001/XMLSchema}string" fixed="SHA1withRSA" /&gt;
                     *     &lt;/extension&gt;
                     *   &lt;/simpleContent&gt;
                     * &lt;/complexType&gt;
                     * </pre>
                     * 
                     * 
                     */
                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "", propOrder = {
                        "value"
                    })
                    public static class FRMA {

                        @XmlValue
                        protected byte[] value;
                        @XmlAttribute(name = "algoritmo", required = true)
                        protected String algoritmo;

                        /**
                         * Gets the value of the value property.
                         * 
                         * @return
                         *     possible object is
                         *     byte[]
                         */
                        public byte[] getValue() {
                            return value;
                        }

                        /**
                         * Sets the value of the value property.
                         * 
                         * @param value
                         *     allowed object is
                         *     byte[]
                         */
                        public void setValue(byte[] value) {
                            this.value = value;
                        }

                        /**
                         * Gets the value of the algoritmo property.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getAlgoritmo() {
                            if (algoritmo == null) {
                                return "SHA1withRSA";
                            } else {
                                return algoritmo;
                            }
                        }

                        /**
                         * Sets the value of the algoritmo property.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setAlgoritmo(String value) {
                            this.algoritmo = value;
                        }

                    }

                }

            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType&gt;
             *   &lt;simpleContent&gt;
             *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;base64Binary"&gt;
             *       &lt;attribute name="algoritmo" use="required"&gt;
             *         &lt;simpleType&gt;
             *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *             &lt;enumeration value="SHA1withRSA"/&gt;
             *             &lt;enumeration value="SHA1withDSA"/&gt;
             *           &lt;/restriction&gt;
             *         &lt;/simpleType&gt;
             *       &lt;/attribute&gt;
             *     &lt;/extension&gt;
             *   &lt;/simpleContent&gt;
             * &lt;/complexType&gt;
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "value"
            })
            public static class FRMT {

                @XmlValue
                protected byte[] value;
                @XmlAttribute(name = "algoritmo", required = true)
                protected String algoritmo;

                /**
                 * Gets the value of the value property.
                 * 
                 * @return
                 *     possible object is
                 *     byte[]
                 */
                public byte[] getValue() {
                    return value;
                }

                /**
                 * Sets the value of the value property.
                 * 
                 * @param value
                 *     allowed object is
                 *     byte[]
                 */
                public void setValue(byte[] value) {
                    this.value = value;
                }

                /**
                 * Gets the value of the algoritmo property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getAlgoritmo() {
                    return algoritmo;
                }

                /**
                 * Sets the value of the algoritmo property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setAlgoritmo(String value) {
                    this.algoritmo = value;
                }

            }

        }

    }

}
