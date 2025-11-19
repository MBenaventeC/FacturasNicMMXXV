/**
 * Copyright [2009] [NIC Labs]
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the 	License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or 
 * agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * 
 **/

package SiiEnvio.net;

import java.io.File;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;
import java.math.BigInteger;
import java.nio.file.Files;

import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.parsers.ParserConfigurationException;
//Cambios a jakarta en vez de javax para soap
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.Name;
import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPBodyElement;
import jakarta.xml.soap.SOAPConnection;
import jakarta.xml.soap.SOAPConnectionFactory;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPEnvelope;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPHeader;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.soap.SOAPPart;

import org.apache.http.client.ClientProtocolException;
// Consultar por qué hacen uso de httpClient de Apache y no nativo de Java (desde 11+ es nativo en Java)
// HTTP
// import org.apache.http.HttpEntity;
// import org.apache.http.HttpResponse;
// import org.apache.http.ParseException;
// import org.apache.http.client.ClientProtocolException;
// import org.apache.http.client.CookieStore;
// import org.apache.http.client.HttpClient;
// import org.apache.http.client.methods.HttpPost;
// import org.apache.http.client.params.ClientPNames;
// import org.apache.http.client.params.CookiePolicy;
// import org.apache.http.client.protocol.ClientContext;
// import org.apache.http.entity.mime.HttpMultipartMode;
// import org.apache.http.entity.mime.MultipartEntity;
// import org.apache.http.entity.mime.content.FileBody;
// import org.apache.http.entity.mime.content.StringBody;
// import org.apache.http.impl.client.BasicCookieStore;
// import org.apache.http.impl.client.DefaultHttpClient;
// import org.apache.http.impl.cookie.BasicClientCookie;
// import org.apache.http.message.BasicHeader;
// import org.apache.http.protocol.BasicHttpContext;
// import org.apache.http.protocol.HttpContext;
// import org.apache.http.util.EntityUtils;
// XMLBeans
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
// Extensión?
import org.xml.sax.SAXException;

import SiiEnvio.generatedClasses.cl.sii.siiDte.RESPUESTA;
import SiiEnvio.util.GetTokenDocument;
import SiiEnvio.util.RESPUESTADocument;
import SiiEnvio.util.Utilities;
import SiiEnvio.util.RECEPCIONDTEDocument;

import SiiBoleta.DTEDefType.Documento;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.InputSource;
import java.io.StringReader;

public class ConexionSii {

	public boolean autentifica(PrivateKey pKey) {

		return false;
	}

	@SuppressWarnings("unchecked")
	public String getToken(PrivateKey pKey, X509Certificate cert)
			throws NoSuchAlgorithmException,
			InvalidAlgorithmParameterException, KeyException, MarshalException,
			XMLSignatureException, SAXException, IOException,
			ParserConfigurationException, XmlException,
			UnsupportedOperationException, SOAPException, ConexionSiiException, Exception {

		String urlSolicitud = Utilities.netLabels
				.getString("URL_SOLICITUD_TOKEN");

		String semilla = getSemilla();

		GetTokenDocument req = GetTokenDocument.newInstance();

		req.addNewGetToken().addNewItem().setSemilla(semilla);

		HashMap<String, String> namespaces = new HashMap<String, String>();
		namespaces.put("", "http://www.sii.cl/SiiDte");
		
		//Funcionalidades llevadas a GetTokenDocument
		// XmlOptions opts = new XmlOptions();
		// opts = new XmlOptions();
		// opts.setSaveImplicitNamespaces(namespaces);
		// opts.setLoadSubstituteNamespaces(namespaces);
		// opts.setSavePrettyPrint();
		// opts.setSavePrettyPrintIndent(0);

		// firmo
		req.sign(pKey, cert);



		SOAPConnectionFactory scFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection con = scFactory.createConnection();
		MessageFactory factory = MessageFactory.newInstance();
		SOAPMessage message = factory.createMessage();
		SOAPPart soapPart = message.getSOAPPart();
		SOAPEnvelope envelope = soapPart.getEnvelope();
		SOAPHeader header = envelope.getHeader();
		SOAPBody body = envelope.getBody();
		header.detachNode();

		Name bodyName = envelope.createName("getToken", "m", urlSolicitud);
		SOAPBodyElement gltp = body.addBodyElement(bodyName);

		Name toKname = envelope.createName("pszXml");

		// opts = new XmlOptions();
		// opts.setCharacterEncoding("ISO-8859-1");
		// opts.setSaveImplicitNamespaces(namespaces);

		// toKsymbol.addTextNode(req.xmlText(opts));

		SOAPElement toKsymbol = gltp.addChildElement(toKname);
		String signedXml = req.xmlText();
		toKsymbol.addTextNode(signedXml);

		message.getMimeHeaders().addHeader("SOAPAction", "");

		//URL endpoint = new URL(urlSolicitud);
		URI uri = URI.create(urlSolicitud);
		URL endpoint = uri.toURL();

		message.writeTo(System.out);

		SOAPMessage responseSII = con.call(message, endpoint);

		//Mismo que getSemilla pero ahora con getToken para parsear la respuesta
		SOAPBody responseBody = responseSII.getSOAPBody();
		Iterator<?> it = responseBody.getChildElements();
		String xmlResponse = null;
		while (it.hasNext()) {
			Object obj = it.next();
			if (obj instanceof SOAPBodyElement) {
				SOAPBodyElement element = (SOAPBodyElement) obj;
				
				// Buscar getSeedReturn
				Iterator<?> children = element.getChildElements();
				while (children.hasNext()) {
					Object child = children.next();
					if (child instanceof SOAPElement) {
						SOAPElement returnElement = (SOAPElement) child;
						
						// Obtener el valor de texto (el XML escapado)
						xmlResponse = returnElement.getValue();
						System.out.println("\n===== XML Extraído (getToken)=====");
						System.out.println(xmlResponse);
						System.out.println("========================\n");
						break;
					}
				}
			}
			if (xmlResponse != null) break;
		}

		if (xmlResponse == null || xmlResponse.isEmpty()) {
			throw new ConexionSiiException("No se encontró contenido en getSeedReturn");
		}

		String cleanXml = xmlResponse
        	.replaceAll("<SII:", "<")
        	.replaceAll("</SII:", "</")
        	.replaceAll("xmlns:SII=\"http://www.sii.cl/XMLSchema\"", "xmlns=\"http://www.sii.cl/XMLSchema\"");

		//Intento parseo XML a DOM
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		Document doc;
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.parse(new InputSource(new StringReader(cleanXml)));
		} catch (ParserConfigurationException | SAXException e) {
			throw new XmlException("Error parseando XML de respuesta: " + e.getMessage(), e);
		}	
		NodeList nl = doc.getElementsByTagNameNS("http://www.sii.cl/XMLSchema", "RESPUESTA");

		if (nl != null && nl.getLength() > 0) {
			try{
				RESPUESTA respuesta = unmarshalNode(nl.item(0), RESPUESTA.class);
				if (respuesta.getRESPHDR().getESTADO() == 0) {
					return respuesta.getRESPBODY().getTOKEN();
				} else {
					throw new ConexionSiiException("No obtuvo Semilla: Codigo: "
							+ respuesta.getRESPHDR().getESTADO()
							+ "; Glosa: " + respuesta.getRESPHDR().getGLOSA());
				}
			} catch (JAXBException je) {
				throw new XmlException("Error unmarshalling RESPUESTA ((getToken)): " + je.getMessage(), je);
			}
		}
		System.out.println("Checkpoint 16 (getToken)");
		throw new ConexionSiiException("Respuesta inválida del SII (getToken)");

		// // --- Reemplazo para la parte de parsing en getToken() ---
		// Document doc = responseSII.getSOAPBody().extractContentAsDocument();
		// NodeList nl = doc.getElementsByTagNameNS("http://www.sii.cl/XMLSchema", "RESPUESTA");
		// if (nl != null && nl.getLength() > 0) {
		//     RESPUESTA respuesta = unmarshalNode(nl.item(0), RESPUESTA.class);
		//     if (respuesta.getRESPHDR().getESTADO() == 0) {
		//         return respuesta.getRESPBODY().getTOKEN();
		//     } else {
		//         throw new ConexionSiiException("No obtuvo Token: Codigo: "
		//             + respuesta.getRESPHDR().getESTADO()
		//             + "; Glosa: " + respuesta.getRESPHDR().getGLOSA());
		//     }
		// }
		// throw new ConexionSiiException("Respuesta invalida del SII (getToken)");
	}

	@SuppressWarnings("unchecked")
	private String getSemilla() throws UnsupportedOperationException,
			SOAPException, IOException, XmlException, ConexionSiiException {
		
		SOAPConnectionFactory scFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection con = scFactory.createConnection();
		MessageFactory factory = MessageFactory.newInstance();
		SOAPMessage message = factory.createMessage();
		SOAPPart soapPart = message.getSOAPPart(); //
		SOAPEnvelope envelope = soapPart.getEnvelope();
		SOAPHeader header = envelope.getHeader();
		SOAPBody body = envelope.getBody();
		header.detachNode();

		String urlSolicitud = Utilities.netLabels
				.getString("URL_SOLICITUD_SEMILLA");

		Name bodyName = envelope.createName("getSeed", "m", urlSolicitud);
		message.getMimeHeaders().addHeader("SOAPAction", "");
		body.addBodyElement(bodyName);
		
		//URL endpoint = new URL(urlSolicitud);
		URI uri = URI.create(urlSolicitud);
		URL endpoint = uri.toURL();

		SOAPMessage responseSII = con.call(message, endpoint);

		// Antiguo: Extraer DOM y unmarshall con JAXB (RESPUESTAType)
		//Document doc = responseSII.getSOAPBody().extractContentAsDocument();
		//NodeList nl = doc.getElementsByTagNameNS("http://www.sii.cl/XMLSchema", "RESPUESTA");
		
		//Buscamos seed
		SOAPBody responseBody = responseSII.getSOAPBody();
		Iterator<?> it = responseBody.getChildElements();
		String xmlResponse = null;
		while (it.hasNext()) {
			Object obj = it.next();
			if (obj instanceof SOAPBodyElement) {
				SOAPBodyElement element = (SOAPBodyElement) obj;
				
				// Buscar getSeedReturn
				Iterator<?> children = element.getChildElements();
				while (children.hasNext()) {
					Object child = children.next();
					if (child instanceof SOAPElement) {
						SOAPElement returnElement = (SOAPElement) child;
						
						// Obtener el valor de texto (el XML escapado)
						xmlResponse = returnElement.getValue();
						System.out.println("\n===== XML Extraído =====");
						System.out.println(xmlResponse);
						System.out.println("========================\n");
						break;
					}
				}
			}
			if (xmlResponse != null) break;
		}

		if (xmlResponse == null || xmlResponse.isEmpty()) {
			throw new ConexionSiiException("No se encontró contenido en getSeedReturn");
		}

		String cleanXml = xmlResponse
        	.replaceAll("<SII:", "<")
        	.replaceAll("</SII:", "</")
        	.replaceAll("xmlns:SII=\"http://www.sii.cl/XMLSchema\"", "xmlns=\"http://www.sii.cl/XMLSchema\"");

		//Intento parseo XML a DOM
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		Document doc;
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.parse(new InputSource(new StringReader(cleanXml)));
		} catch (ParserConfigurationException | SAXException e) {
			throw new XmlException("Error parseando XML de respuesta: " + e.getMessage(), e);
		}	
		NodeList nl = doc.getElementsByTagNameNS("http://www.sii.cl/XMLSchema", "RESPUESTA");

		if (nl != null && nl.getLength() > 0) {
			try{
				RESPUESTA respuesta = unmarshalNode(nl.item(0), RESPUESTA.class);
				if (respuesta.getRESPHDR().getESTADO() == 0) {
					System.out.println(respuesta.getRESPBODY().getSEMILLA());
					return respuesta.getRESPBODY().getSEMILLA();
				} else {
					throw new ConexionSiiException("No obtuvo Semilla: Codigo: "
							+ respuesta.getRESPHDR().getESTADO()
							+ "; Glosa: " + respuesta.getRESPHDR().getGLOSA());
				}
			} catch (JAXBException je) {
				throw new XmlException("Error unmarshalling RESPUESTA (semilla): " + je.getMessage(), je);
			}
		}
		throw new ConexionSiiException("Respuesta inválida del SII (getSemilla)");
	}

	
	/**
	 * Consulta el estado de un DTE en el ambiente de produccion del SII
	 * 
	 * @param rutConsultante
	 * @param dte
	 * @param token
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws SOAPException
	 * @throws MalformedURLException
	 * @throws XmlException
	 */
	public RESPUESTADocument getEstadoDTEProduccion(String rutConsultante,
			Documento dte, String token) throws UnsupportedOperationException,
			SOAPException, MalformedURLException, XmlException {
		String urlSolicitud = Utilities.netLabels
				.getString("URL_CONSULTA_ESTADO_DTE_PRODUCCION");
		return getEstadoDTE(rutConsultante, dte, token, urlSolicitud);
	}

	/**
	 * Consulta el estado de un DTE en el ambiente de certificacion del SII
	 * 
	 * @param rutConsultante
	 * @param dte
	 * @param token
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws SOAPException
	 * @throws MalformedURLException
	 * @throws XmlException
	 */
	public RESPUESTADocument getEstadoDTECertificacion(String rutConsultante,
			Documento dte, String token) throws UnsupportedOperationException,
			SOAPException, MalformedURLException, XmlException {
		String urlSolicitud = Utilities.netLabels
				.getString("URL_CONSULTA_ESTADO_DTE_CERTIFICACION");
		return getEstadoDTE(rutConsultante, dte, token, urlSolicitud);
	}

	@SuppressWarnings("unchecked")
	private RESPUESTADocument getEstadoDTE(String rutConsultante,
			Documento dte, String token, String urlSolicitud)
			throws UnsupportedOperationException, SOAPException,
			MalformedURLException, XmlException {

		String rutEmisor = dte.getEncabezado().getEmisor().getRUTEmisor();
		String rutReceptor = dte.getEncabezado().getReceptor().getRUTRecep();
		Integer tipoDTE = dte.getEncabezado().getIdDoc().getTipoDTE().intValue();
		// folio puede venir como BigInteger desde JAXB: manejar null y conversión segura
		BigInteger folioBI = dte.getEncabezado().getIdDoc().getFolio();
		long folioDTE = (folioBI == null) ? 0L : folioBI.longValue();
		String fechaEmision = Utilities.fechaEstadoDte.format(dte.getEncabezado().getIdDoc().getFchEmis().toGregorianCalendar().getTime());
		// monto total puede ser BigInteger también
		BigInteger montoBI = dte.getEncabezado().getTotales().getMntTotal();
		long montoTotal = (montoBI == null) ? 0L : montoBI.longValue();

		SOAPConnectionFactory scFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection con = scFactory.createConnection();
		MessageFactory factory = MessageFactory.newInstance();
		SOAPMessage message = factory.createMessage();
		SOAPPart soapPart = message.getSOAPPart();
		SOAPEnvelope envelope = soapPart.getEnvelope();
		SOAPHeader header = envelope.getHeader();
		SOAPBody body = envelope.getBody();
		header.detachNode();

		Name bodyName = envelope.createName("getEstDte", "m", urlSolicitud);
		SOAPBodyElement gltp = body.addBodyElement(bodyName);

		Name toKname = envelope.createName("RutConsultante");
		SOAPElement toKsymbol = gltp.addChildElement(toKname);
		toKsymbol.addTextNode(rutConsultante.substring(0, rutConsultante
				.length() - 2));

		toKname = envelope.createName("DvConsultante");
		toKsymbol = gltp.addChildElement(toKname);
		toKsymbol.addTextNode(rutConsultante.substring(
				rutConsultante.length() - 1, rutConsultante.length()));

		toKname = envelope.createName("RutCompania");
		toKsymbol = gltp.addChildElement(toKname);
		toKsymbol.addTextNode(rutEmisor.substring(0, rutEmisor.length() - 2));

		toKname = envelope.createName("DvCompania");
		toKsymbol = gltp.addChildElement(toKname);
		toKsymbol.addTextNode(rutEmisor.substring(rutEmisor.length() - 1,
				rutEmisor.length()));

		toKname = envelope.createName("RutReceptor");
		toKsymbol = gltp.addChildElement(toKname);
		toKsymbol.addTextNode(rutReceptor
				.substring(0, rutReceptor.length() - 2));

		toKname = envelope.createName("DvReceptor");
		toKsymbol = gltp.addChildElement(toKname);
		toKsymbol.addTextNode(rutReceptor.substring(rutReceptor.length() - 1,
				rutReceptor.length()));

		toKname = envelope.createName("TipoDte");
		toKsymbol = gltp.addChildElement(toKname);
		toKsymbol.addTextNode(Integer.toString(tipoDTE));

		toKname = envelope.createName("FolioDte");
		toKsymbol = gltp.addChildElement(toKname);
		toKsymbol.addTextNode(Long.toString(folioDTE));

		toKname = envelope.createName("FechaEmisionDte");
		toKsymbol = gltp.addChildElement(toKname);
		toKsymbol.addTextNode(fechaEmision);

		toKname = envelope.createName("MontoDte");
		toKsymbol = gltp.addChildElement(toKname);
		toKsymbol.addTextNode(Long.toString(montoTotal));

		toKname = envelope.createName("Token");
		toKsymbol = gltp.addChildElement(toKname);
		toKsymbol.addTextNode(token);

		message.getMimeHeaders().addHeader("SOAPAction", "");

		//URL endpoint = new URL(urlSolicitud);
		URI uri = URI.create(urlSolicitud);
		URL endpoint = uri.toURL();

		SOAPMessage responseSII = con.call(message, endpoint);

		// Extraer DOM y buscar RESPUESTA
		Document doc = responseSII.getSOAPBody().extractContentAsDocument();
		
		// Buscar en ambos namespaces posibles
		NodeList nl = doc.getElementsByTagNameNS("http://www.sii.cl/XMLSchema", "RESPUESTA");
		if (nl == null || nl.getLength() == 0) {
			// Fallback: buscar sin namespace
			nl = doc.getElementsByTagName("RESPUESTA");
		}
		
		if (nl != null && nl.getLength() > 0) {
			try {
				// Serializar nodo a String y parsear con namespace handling
				TransformerFactory tf = TransformerFactory.newInstance();
				Transformer transformer = tf.newTransformer();
				StringWriter writer = new StringWriter();
				transformer.transform(new DOMSource(nl.item(0)), new StreamResult(writer));
				String respuestaXml = writer.toString();
				
				// Parsear con sustitución de namespace
				HashMap<String, String> namespaces = new HashMap<>();
				namespaces.put("", "http://www.sii.cl/XMLSchema");
				XmlOptions opts = new XmlOptions();
				opts.setLoadSubstituteNamespaces(namespaces);
				
				return RESPUESTADocument.Factory.parse(respuestaXml, opts);
				
			} catch (TransformerException e) {
				throw new XmlException("Error procesando RESPUESTA (getEstadoDTE): " + e.getMessage(), e);
			}
		}

		throw new XmlException("No se encontró elemento RESPUESTA en la respuesta del SII");
	}

	/**
	 * Envia el archivo XML indicado al ambiente de produccion del SII.
	 * 
	 * @param rutEnvia
	 * @param rutCompania
	 * @param archivoEnviarSII
	 * @param token
	 * @return
	 * @throws ClientProtocolException
	 * @throws ParseException
	 * @throws IOException
	 * @throws XmlException
	 */
	public RECEPCIONDTEDocument uploadEnvioProduccion(String rutEnvia,
			String rutCompania, File archivoEnviarSII, String token)
			throws IOException, XmlException, InterruptedException {
		String urlEnvio = Utilities.netLabels
				.getString("URL_UPLOAD_PRODUCCION");
		String hostEnvio = Utilities.netLabels
				.getString("HOST_UPLOAD_PRODUCCION");
		return uploadEnvio(rutEnvia, rutCompania, archivoEnviarSII, token,
				urlEnvio, hostEnvio);
	}

	/**
	 * Envia el archivo XML indicado al ambiente de certificacion del SII.
	 * 
	 * @param rutEnvia
	 * @param rutCompania
	 * @param archivoEnviarSII
	 * @param token
	 * @return
	 * @throws IOException
	 * @throws XmlException
	 * @throws InterruptedException
	 */
	public RECEPCIONDTEDocument uploadEnvioCertificacion(String rutEnvia,
			String rutCompania, File archivoEnviarSII, String token)
			throws IOException, XmlException, InterruptedException {          // <- cambiado
        String urlEnvio = Utilities.netLabels
                .getString("URL_UPLOAD_CERTIFICACION");
        String hostEnvio = Utilities.netLabels
                .getString("HOST_UPLOAD_CERTIFICACION");
        return uploadEnvio(rutEnvia, rutCompania, archivoEnviarSII, token,
                urlEnvio, hostEnvio);
    }

	private RECEPCIONDTEDocument uploadEnvio(String rutEnvia,
			String rutCompania, File archivoEnviarSII, String token,
			String urlEnvio, String hostEnvio) throws IOException, XmlException, InterruptedException { // <- agregado InterruptedException
		
		// Crear boundary para multipart
		String boundary = "----WebKitFormBoundary" + System.currentTimeMillis();
		
		//Reemplazo Multipartbody con ByteArray
		java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
    	java.nio.charset.Charset charset = java.nio.charset.StandardCharsets.ISO_8859_1;
		
		//RutSender
		String field1 = "--" + boundary + "\r\n" +
                   "Content-Disposition: form-data; name=\"rutSender\"\r\n\r\n" +
                   rutEnvia.substring(0, rutEnvia.length() - 2) + "\r\n";
    	baos.write(field1.getBytes(charset));
		//dvSender
		String field2 = "--" + boundary + "\r\n" +
                   "Content-Disposition: form-data; name=\"dvSender\"\r\n\r\n" +
                   rutEnvia.substring(rutEnvia.length() - 1) + "\r\n";
    				baos.write(field2.getBytes(charset));
		//rutCompany
		String field3 = "--" + boundary + "\r\n" +
                   "Content-Disposition: form-data; name=\"rutCompany\"\r\n\r\n" +
                   rutCompania.substring(0, rutCompania.length() - 2) + "\r\n";
    				baos.write(field3.getBytes(charset));
		
		//dvCompany
		String field4 = "--" + boundary + "\r\n" +
                   "Content-Disposition: form-data; name=\"dvCompany\"\r\n\r\n" +
                   rutCompania.substring(rutCompania.length() - 1) + "\r\n";
    				baos.write(field4.getBytes(charset));
		
		//Archivo
		String fileHeader = "--" + boundary + "\r\n" +
                       "Content-Disposition: form-data; name=\"archivo\"; filename=\"" +
                       archivoEnviarSII.getName() + "\"\r\n" +
                       "Content-Type: application/xml\r\n\r\n";
    					baos.write(fileHeader.getBytes(charset));

		byte[] fileBytes = Files.readAllBytes(archivoEnviarSII.toPath());
    	baos.write(fileBytes);
    	baos.write("\r\n".getBytes(charset));
		String footer = "--" + boundary + "--\r\n";
    	baos.write(footer.getBytes(charset));
		byte[] bodyBytes = baos.toByteArray();
		
		// Crear HTTP Client
		HttpClient client = HttpClient.newBuilder()
			.cookieHandler(new java.net.CookieManager())
			.build();

		// Crear request
		HttpRequest request = HttpRequest.newBuilder()
		 	.uri(URI.create(urlEnvio))
		 	.header("Content-Type", "multipart/form-data; boundary=" + boundary)
			.header("Cookie", "TOKEN=" + token)
		 	.header("User-Agent", Utilities.netLabels.getString("UPLOAD_SII_HEADER_VALUE"))
		 	.header("Accept", "*/*")
			.POST(HttpRequest.BodyPublishers.ofByteArray(bodyBytes))
			.timeout(java.time.Duration.ofMinutes(5))
		 	.build();
		
		// HttpRequest request = HttpRequest.newBuilder()
		//  	.uri(URI.create(urlEnvio))
		//  	.header("Content-Type", "multipart/form-data; boundary=" + boundary)
		//  	.header("Cookie", "TOKEN=" + token)
		//  	.header("User-Agent", Utilities.netLabels.getString("UPLOAD_SII_HEADER_VALUE"))
		//  	.POST(HttpRequest.BodyPublishers.ofString(multipartBody.toString()))
		//  	.build();

		try {
			// Enviar request
			HttpResponse<String> response = client.send(request, 
				HttpResponse.BodyHandlers.ofString());

			String cleanedResponse = response.body()
			.trim()
			.replaceAll("&#13;", "")
			.replaceAll("&#10;", "")
			.replaceAll("<RECEPCIONDTE>", "<RECEPCIONDTE xmlns=\"http://www.sii.cl/SiiDte\">");
					

			// Parsear respuesta
			HashMap<String, String> namespaces = new HashMap<>();
			namespaces.put("", "http://www.sii.cl/SiiDte");
			XmlOptions opts = new XmlOptions();
			opts.setLoadSubstituteNamespaces(namespaces);

			// usar el parse que envuelve excepciones de parsing (XmlException/IOException)
			//Aqui falla ahora
			try {
				return RECEPCIONDTEDocument.Factory.parse(cleanedResponse, opts);
			} catch (XmlException xe) {
				System.err.println("Error parseando RECEPCIONDTE:");
				System.err.println("XML recibido:\n" + cleanedResponse);
				xe.printStackTrace();
				throw new XmlException("Error parseando respuesta del SII: " + xe.getMessage(), xe);
			}
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw e;
        }
	}

    // JAXB helper integrado: reutiliza JAXBContext para el paquete generado
    private static JAXBContext siiJaxbContext;

    private static synchronized JAXBContext getSiiJaxbContext() throws JAXBException {
        if (siiJaxbContext == null) {
            // ajustar el package si tu plugin JAXB generó otro
            siiJaxbContext = JAXBContext.newInstance("SiiEnvio.generatedClasses.cl.sii.siiDte");
        }
        return siiJaxbContext;
    }

    @SuppressWarnings("unchecked")
    private static <T> T unmarshalNode(Node node, Class<T> clazz) throws JAXBException {
        Unmarshaller un = getSiiJaxbContext().createUnmarshaller();
        Object obj = un.unmarshal(node); // usar la variante genérica
        if (obj instanceof JAXBElement) {
            Object value = ((JAXBElement<?>) obj).getValue();
            return clazz.cast(value);
        } else {
            return clazz.cast(obj);
        }
    }

}
