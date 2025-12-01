package SiiBoleta;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import SIIEnvio.EnvioDTE;
import SiiSignature.SignatureType;

import org.w3c.dom.*;

import org.json.JSONArray;
import org.json.JSONObject;


public class DTEMakers {

    /** Maker for < IdDoc >, which belongs to < Encabezado >
     *
     * @param folio Number of Document authorized by SII
     * @param indServicio Type of Service provided:
     *                      -1 for "Domestic Services" (Electricity Bill, Water Bill, Gas Bill, Phone Bill, Internet Bill, etc.)
     *                      -2 for "Other Periodical Services" (Subscriptions, Rent, etc.)
     *                      -3 for "Services Invoice"
     *                      Only for Export Invoice:
     *                      -4 for "Hospitality Services"
     *                      -5 for "International Land Transport"
     * @param fmaPago Payment Method:
     *                -1 for "Efectivo" / "Cash"
     *                -2 for "Tarjeta de Crédito" / "Credit Card"
     *                -3 for "Gratuito" / "For Free"
     *                According to SII
     * @param medioPago Description for Payment Method:
     *                  -"Efectivo" / "Cash"
     *                  -"Tarjeta de Crédito" / "Credit Card"
     *                  -"Transferencia bancaria" / "Bank Transfer"
     *                  -"Cheque" / "Check"
     * @return Object containing all < IdDoc > information.
     * @throws DatatypeConfigurationException
     */
    public static DTEDefType.Documento.Encabezado.IdDoc makeIdDoc
            (int tipoDoc,
             int folio,
             int indServicio,
             int fmaPago,
             MedioPagoType medioPago
            ) throws DatatypeConfigurationException {
        DTEDefType.Documento.Encabezado.IdDoc  idDoc = new DTEDefType.Documento.Encabezado.IdDoc();

        /**
         * All the documents refered here correspond to different types of Electronic Invoice:
         * - 33 for "Electronic Invoice"
         * - 34 for "Tax-Exempt Invoice"
         * - 43 for "Settlement Invoice"
         * - 46 for "Purchase Invoice"
         * - 52 for "Dispatch Guide"
         * - 56 for "Debit Note"
         * - 61 for "Credit Note"
         * - 110 for "Export Invoice"
         * - 111 for "Export Debit Note"
         * - 112 for "Export Credit Note"
         */
        // Sii DTE Document Type set to 34, representing a 'Factura Exenta' or an 'Electronic Tax-Exempt Invoice'
        idDoc.setTipoDTE(BigInteger.valueOf(tipoDoc));

        // @params folio
        idDoc.setFolio(BigInteger.valueOf(folio));

        // Initializing a GregorianCalender
        GregorianCalendar calendar = new GregorianCalendar();
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        xmlDate.setTimezone( DatatypeConstants.FIELD_UNDEFINED );

            // Issuing Date
        idDoc.setFchEmis(xmlDate);

        // @params indServicio
        idDoc.setIndServicio(BigInteger.valueOf(indServicio));

        // @params fmaPago
        idDoc.setFmaPago(BigInteger.valueOf(fmaPago));

            // Payment Date
        idDoc.setFchCancel(xmlDate);

        // @params medioPago
        idDoc.setMedioPago(medioPago);

            // Expiring Date
        idDoc.setFchVenc(xmlDate);

        return idDoc;
    }

    /** Default maker for < Emisor > or 'Issuing' object, containing default values for NIC Chile.
     * < Emisor > belongs to < Encabezado >
     *
     * All setters arguments can be edited to create a new 'Emisor'.
     *
     * @return Instance of 'Emisor' class
     */
    public static DTEDefType.Documento.Encabezado.Emisor makeEmisor
            (){
        DTEDefType.Documento.Encabezado.Emisor emisor = new DTEDefType.Documento.Encabezado.Emisor();
        emisor.setRUTEmisor("60910000-1");
        emisor.setRznSoc("Universidad de Chile");
        emisor.setGiroEmis("Corporación Educacional y Servicios Profesionales");
        emisor.setActeco(new BigInteger("803010"));
        emisor.setSucursal("NIC Chile");
        emisor.setCdgSIISucur(new BigInteger("67051191"));
        emisor.setDirOrigen("Miraflores 222, Piso 14");
        emisor.setCmnaOrigen("Santiago");
        emisor.setCiudadOrigen("Santiago");
        return emisor;
    }

    /** Maker for a < Receptor > or 'Receiver' object, which belongs to < Encabezado >
     *
     * @param RUTRecep Receptor/Receiver's RUT
     * @param rznSoc Company Name
     * @param giroRecep Business Line
     * @param Contacto Receiver Company Name and Phone Number
     * @param dirRecap Receiver Address
     * @param cmnaRecap Receiver City Municipality
     * @param ciudadRecep Receiver City
     * @return Receptor Object containing all information required.
     */
    public static DTEDefType.Documento.Encabezado.Receptor makeReceptor(
            String RUTRecep,
            String rznSoc,
            String giroRecep,
            String Contacto,
            String dirRecap,
            String cmnaRecap,
            String ciudadRecep
    ){
        DTEDefType.Documento.Encabezado.Receptor receptor = new DTEDefType.Documento.Encabezado.Receptor();
        receptor.setRUTRecep(RUTRecep);
        receptor.setRznSocRecep(rznSoc);
        receptor.setGiroRecep(giroRecep);
        receptor.setContacto(Contacto);
        receptor.setDirRecep(dirRecap);
        receptor.setCmnaRecep(cmnaRecap);
        receptor.setCiudadRecep(ciudadRecep);
        return receptor;
    }

    /** Maker for a < Totales > section, which belongs to < Encabezado >.
     *
     * @param total Net Total Amount
     * @return
     */
    public static DTEDefType.Documento.Encabezado.Totales makeTotales
            (int total){
        DTEDefType.Documento.Encabezado.Totales totales = new DTEDefType.Documento.Encabezado.Totales();

        // Total Sum for Tax-Exempt Items
        totales.setMntExe(BigInteger.valueOf(total));//totales.setMntExe(mntExe);

        // Net Total Amount
        totales.setMntTotal(BigInteger.valueOf(total));//totales.setMntTotal(mntTotal);
        return totales;
    }

    /** Maker for < Encabezado > section, which belongs to < Documento > section.
     *
     * @param idDoc Document ID
     * @param emisor Emisor Object containing all < Emisor > information.
     * @param receptor Receptor Object containing all < Receptor > information.
     * @param totales Totales Object containing all < Totales > information.
     * @return Encabezado object that contains all its subsections information.
     */
    public static DTEDefType.Documento.Encabezado makeEncabezado(
            DTEDefType.Documento.Encabezado.IdDoc idDoc,
            DTEDefType.Documento.Encabezado.Emisor emisor,
            DTEDefType.Documento.Encabezado.Receptor receptor,
            DTEDefType.Documento.Encabezado.Totales totales){
        DTEDefType.Documento.Encabezado encabezado = new DTEDefType.Documento.Encabezado();
        encabezado.setIdDoc(idDoc);
        encabezado.setEmisor(emisor);
        encabezado.setReceptor(receptor);
        encabezado.setTotales(totales);
        return encabezado;
    }

    /** Maker for < Detalle > section, which belongs to < Documento > section.
     *
     * @param nroLinDet Number of detail lines
     * @param nmbItem Name of the item.
     * @param qtyItem Quantity of the item.
     * @param prcItem Price of the item.
     * @return Detalle object that contains all its subsections information.
     */
    public static DTEDefType.Documento.Detalle makeDetalle(
            int nroLinDet,
            String nmbItem,
            int qtyItem,
            double prcItem){
        DTEDefType.Documento.Detalle detalle = new DTEDefType.Documento.Detalle();
        detalle.setNroLinDet(nroLinDet); //Cantidad de lineas de detalle x es el numero de esta linea en especifico
        detalle.setNmbItem("dominio "+nmbItem); //Nombre del dominio, debe empezar por "dominio "
        detalle.setQtyItem(BigDecimal.valueOf(qtyItem));// cantidad del item
        detalle.setPrcItem(BigDecimal.valueOf(prcItem));// precio del item
        detalle.setMontoItem(BigDecimal.valueOf(qtyItem).multiply(BigDecimal.valueOf(prcItem)).toBigInteger());//qty*prc
        return detalle;
    }

    /** Idea: Add n detalles to < Detalle > section, from a JSON Array
     * @param detallesJson JSON Array containing n detalles values
     * @return List of Detalle objects
     */
    public static List<DTEDefType.Documento.Detalle> makeDetalles(JSONArray detallesJson) {
        List<DTEDefType.Documento.Detalle> detalles = new ArrayList<>();
        for (int i = 0; i < detallesJson.length(); i++) {
            JSONObject detalleJson = detallesJson.getJSONObject(i);
            int nroLinDet = i+1;
            String nmbItem = detalleJson.getString("nmbItem");
            int qtyItem = detalleJson.getInt("qtyItem");
            double prcItem = detalleJson.getDouble("prcItem");

            DTEDefType.Documento.Detalle detalle = makeDetalle(nroLinDet, nmbItem, qtyItem, prcItem);
            detalles.add(detalle);
        }
        return detalles;
    }

    /**
     * Reads JSON file
     * @param ruta
     * @return
     * @throws Exception
     */
    public static JSONArray leerJsonArray(String ruta) throws Exception {
        String contenido = new String(Files.readAllBytes(Paths.get(ruta)));
        return new JSONArray(contenido);
    }

    /**
     * Creates Documento with given values and returns it
     * @param encabezado
     * @param detalles
     * @param ted
     * @param id
     * @return
     * @throws DatatypeConfigurationException
     */
    public static DTEDefType.Documento makeDocumento(DTEDefType.Documento.Encabezado encabezado, List<DTEDefType.Documento.Detalle> detalles,DTEDefType.Documento.TED ted,String id) throws DatatypeConfigurationException {
        DTEDefType.Documento documento = new DTEDefType.Documento();
        documento.setEncabezado(encabezado);
        documento.initializeDetalle();
        for (int i = 0; i < detalles.size(); i++) {
            documento.addDetalle(detalles.get(i));
        }
        GregorianCalendar calendar = new GregorianCalendar();
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        xmlDate.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
        xmlDate.setTimezone( DatatypeConstants.FIELD_UNDEFINED );
        documento.setTmstFirma(xmlDate);
        documento.setID(id);
        documento.setTED(ted);
        return documento;
    }

    /**
     * Prints node for debugging
     * @param node
     * @throws Exception
     */
    public static void printNode(Node node) throws Exception {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        // Indent and encoding properties, used for debugging purposes
        //transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        //transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");

        transformer.transform(new DOMSource(node), new StreamResult(System.out));
    }

    /**
     * Creates DTE with given values and returns it
     * @param documento
     * @param signature
     * @return
     */
    public static DTEDefType makeDTE(DTEDefType.Documento documento, SignatureType signature) {
        DTEDefType dte = new DTEDefType();
        dte.setDocumento(documento);
        dte.setSignature(signature);
        dte.setVersion(BigDecimal.valueOf(1.0));
        return dte;
    }

    /**
     * Creates Caratula with given values and returns it
     * @param rutEnvia
     * @param rutEmisor
     * @param rutReceptor
     * @param nroResol
     * @param subTotDTE
     * @return
     * @throws DatatypeConfigurationException
     */
    public static EnvioDTE.SetDTE.Caratula makeCaratula(String rutEnvia, String rutEmisor, String rutReceptor,
                                                        /*XMLGregorianCalendar fchResol,*/ int nroResol,
                                                        /*XMLGregorianCalendar tmstFirmaEnv,*/
                                                        List<EnvioDTE.SetDTE.Caratula.SubTotDTE> subTotDTE) throws DatatypeConfigurationException {
        EnvioDTE.SetDTE.Caratula caratula = new EnvioDTE.SetDTE.Caratula();
        // Rut Contribuyente Emisor de los DTE
        caratula.setRutEmisor(rutEmisor);
        // Rut Persona que envía los DTE
        caratula.setRutEnvia(rutEnvia);
        // Rut Contribuyente Receptor de los DTE
        caratula.setRutReceptor(rutReceptor);
        GregorianCalendar calendar = new GregorianCalendar(2002, GregorianCalendar.OCTOBER,20);
        GregorianCalendar calendarActual = new GregorianCalendar();
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        xmlDate.setTimezone( DatatypeConstants.FIELD_UNDEFINED );
        xmlDate.setMillisecond( DatatypeConstants.FIELD_UNDEFINED );
        // Fecha Resolución SII que autoriza al emisor
        caratula.setFchResol(xmlDate);
        // Nº de resolución SII que autoriza al emisor
        caratula.setNroResol(BigInteger.valueOf(nroResol));
        XMLGregorianCalendar xmlDate2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendarActual);
        xmlDate2.setTimezone( DatatypeConstants.FIELD_UNDEFINED );
        xmlDate2.setMillisecond( DatatypeConstants.FIELD_UNDEFINED );
        // Fecha y hora de firma del envío AAAAMMDDHHMMSS
        caratula.setTmstFirmaEnv(xmlDate2);
        // Uno o más Subtotales por tipo de DTE
        caratula.setSubTotDTE(subTotDTE);
        caratula.setVersion(BigDecimal.valueOf(1.0));
        return caratula;
    }

    /**
     * Creates SubTotDTE with given values and returns it
     * @param tpoDTE
     * @param nroDTE
     * @return
     */
    public static EnvioDTE.SetDTE.Caratula.SubTotDTE makeSubTotDTE(int tpoDTE,int nroDTE) {
        EnvioDTE.SetDTE.Caratula.SubTotDTE subTot =  new EnvioDTE.SetDTE.Caratula.SubTotDTE();
        subTot.setTpoDTE(BigInteger.valueOf(tpoDTE));
        subTot.setNroDTE(BigInteger.valueOf(nroDTE));
        return subTot;
    }

    /**
     * Creates SetDTE with given values and returns it
     * @param caratula
     * @param DteList
     * @param id
     * @return
     */
    public static EnvioDTE.SetDTE makeSetDTE(EnvioDTE.SetDTE.Caratula caratula,List<SiiBoleta.DTEDefType> DteList,String id){
        EnvioDTE.SetDTE  setDTE = new EnvioDTE.SetDTE();
        setDTE.setCaratula(caratula);
        setDTE.setDteList(DteList);
        setDTE.setID(id);
        return setDTE;
    }

    /**
     * Creates EnvioDTE with given values and returns it
     * @param setDTE
     * @param signature
     * @return
     */
    public static EnvioDTE makeEnvioDTE(EnvioDTE.SetDTE setDTE, SignatureType signature){
        EnvioDTE envioDTE = new EnvioDTE();
        envioDTE.setSetDTE(setDTE);
        envioDTE.setSignature(signature);
        envioDTE.setVersion(BigDecimal.valueOf(1.0));
        return envioDTE;
    }

}