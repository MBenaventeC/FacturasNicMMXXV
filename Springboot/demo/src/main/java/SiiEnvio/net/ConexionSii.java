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
import java.nio.file.Files;

import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.parsers.ParserConfigurationException;
//Cambios a jakarta en vez de javax para soap
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

// Utilities viene del repo de Nic
import SiiEnvio.Utilities;
// Consultar de donde provienen los imports 'cl.sii.*'
import SiiEnvio.schemas.GetTokenDocument;
import SiiEnvio.schemas.RECEPCIONDTEDocument;
import SiiEnvio.schemas.RESPUESTADocument;
import SiiBoleta.DTEDefType.Documento;

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
			UnsupportedOperationException, SOAPException, ConexionSiiException {

		String urlSolicitud = Utilities.netLabels
				.getString("URL_SOLICITUD_TOKEN");

		String semilla = getSemilla();

		GetTokenDocument req = GetTokenDocument.Factory.newInstance();

		req.addNewGetToken().addNewItem().setSemilla(semilla);

		HashMap<String, String> namespaces = new HashMap<String, String>();
		namespaces.put("", "http://www.sii.cl/SiiDte");
		XmlOptions opts = new XmlOptions();

		opts = new XmlOptions();
		opts.setSaveImplicitNamespaces(namespaces);
		opts.setLoadSubstituteNamespaces(namespaces);
		opts.setSavePrettyPrint();
		opts.setSavePrettyPrintIndent(0);

		req = GetTokenDocument.Factory.parse(req.newInputStream(opts), opts);

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

		SOAPElement toKsymbol = gltp.addChildElement(toKname);

		opts = new XmlOptions();
		opts.setCharacterEncoding("ISO-8859-1");
		opts.setSaveImplicitNamespaces(namespaces);

		toKsymbol.addTextNode(req.xmlText(opts));

		message.getMimeHeaders().addHeader("SOAPAction", "");

		URL endpoint = new URL(urlSolicitud);

		message.writeTo(System.out);

		SOAPMessage responseSII = con.call(message, endpoint);

		SOAPPart sp = responseSII.getSOAPPart();
		SOAPBody b = sp.getEnvelope().getBody();

		cl.sii.xmlSchema.RESPUESTADocument resp = null;
		for (Iterator<SOAPBodyElement> res = b.getChildElements(sp
				.getEnvelope().createName("getTokenResponse", "ns1",
						urlSolicitud)); res.hasNext();) {
			for (Iterator<SOAPBodyElement> ret = res.next().getChildElements(
					sp.getEnvelope().createName("getTokenReturn", "ns1",
							urlSolicitud)); ret.hasNext();) {

				namespaces = new HashMap<String, String>();
				namespaces.put("", "http://www.sii.cl/XMLSchema");
				opts.setLoadSubstituteNamespaces(namespaces);

				resp = RESPUESTADocument.Factory.parse(ret.next().getValue(),
						opts);
			}
		}

		if (resp != null && resp.getRESPUESTA().getRESPHDR().getESTADO() == 0) {
			return resp.getRESPUESTA().getRESPBODY().getTOKEN();
		} else {
			throw new ConexionSiiException("No obtuvo Semilla: Codigo: "
					+ resp.getRESPUESTA().getRESPHDR().getESTADO()
					+ "; Glosa: " + resp.getRESPUESTA().getRESPHDR().getGLOSA());
		}

	}

	@SuppressWarnings("unchecked")
	private String getSemilla() throws UnsupportedOperationException,
			SOAPException, IOException, XmlException, ConexionSiiException {
		SOAPConnectionFactory scFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection con = scFactory.createConnection();
		MessageFactory factory = MessageFactory.newInstance();
		SOAPMessage message = factory.createMessage();
		SOAPPart soapPart = message.getSOAPPart();
		SOAPEnvelope envelope = soapPart.getEnvelope();
		SOAPHeader header = envelope.getHeader();
		SOAPBody body = envelope.getBody();
		header.detachNode();

		String urlSolicitud = Utilities.netLabels
				.getString("URL_SOLICITUD_SEMILLA");

		Name bodyName = envelope.createName("getSeed", "m", urlSolicitud);

		message.getMimeHeaders().addHeader("SOAPAction", "");

		body.addBodyElement(bodyName);

		URL endpoint = new URL(urlSolicitud);

		SOAPMessage responseSII = con.call(message, endpoint);

		SOAPPart sp = responseSII.getSOAPPart();
		SOAPBody b = sp.getEnvelope().getBody();

		cl.sii.xmlSchema.RESPUESTADocument resp = null;
		for (Iterator<SOAPBodyElement> res = b.getChildElements(sp
				.getEnvelope().createName("getSeedResponse", "ns1",
						urlSolicitud)); res.hasNext();) {
			for (Iterator<SOAPBodyElement> ret = res.next().getChildElements(
					sp.getEnvelope().createName("getSeedReturn", "ns1",
							urlSolicitud)); ret.hasNext();) {

				HashMap<String, String> namespaces = new HashMap<String, String>();
				namespaces.put("", "http://www.sii.cl/XMLSchema");
				XmlOptions opts = new XmlOptions();
				opts.setLoadSubstituteNamespaces(namespaces);

				resp = RESPUESTADocument.Factory.parse(ret.next().getValue(),
						opts);

			}
		}

		if (resp != null && resp.getRESPUESTA().getRESPHDR().getESTADO() == 0) {
			return resp.getRESPUESTA().getRESPBODY().getSEMILLA();
		} else {
			throw new ConexionSiiException("No obtuvo Semilla: Codigo: "
					+ resp.getRESPUESTA().getRESPHDR().getESTADO()
					+ "; Glosa: " + resp.getRESPUESTA().getRESPHDR().getGLOSA());
		}
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
		Integer tipoDTE = dte.getEncabezado().getIdDoc().getTipoDTE()
				.intValue();
		long folioDTE = dte.getEncabezado().getIdDoc().getFolio();
		String fechaEmision = Utilities.fechaEstadoDte.format(dte
				.getEncabezado().getIdDoc().getFchEmis().getTime());
		long montoTotal = dte.getEncabezado().getTotales().getMntTotal();

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

		URL endpoint = new URL(urlSolicitud);

		SOAPMessage responseSII = con.call(message, endpoint);

		SOAPPart sp = responseSII.getSOAPPart();
		SOAPBody b = sp.getEnvelope().getBody();

		for (Iterator<SOAPBodyElement> res = b.getChildElements(sp
				.getEnvelope().createName("getEstDteResponse", "ns1",
						urlSolicitud)); res.hasNext();) {
			for (Iterator<SOAPBodyElement> ret = res.next().getChildElements(
					sp.getEnvelope().createName("getEstDteReturn", "ns1",
							urlSolicitud)); ret.hasNext();) {

				HashMap<String, String> namespaces = new HashMap<String, String>();
				namespaces.put("", "http://www.sii.cl/XMLSchema");
				XmlOptions opts = new XmlOptions();
				opts.setLoadSubstituteNamespaces(namespaces);

				return RESPUESTADocument.Factory.parse(ret.next().getValue(),
						opts);
			}
		}

		return null;

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
	 * @throws ClientProtocolException
	 * @throws ParseException
	 * @throws IOException
	 * @throws XmlException
	 */
	public RECEPCIONDTEDocument uploadEnvioCertificacion(String rutEnvia,
			String rutCompania, File archivoEnviarSII, String token)
			throws ClientProtocolException, ParseException, IOException,
			XmlException {
		String urlEnvio = Utilities.netLabels
				.getString("URL_UPLOAD_CERTIFICACION");
		String hostEnvio = Utilities.netLabels
				.getString("HOST_UPLOAD_CERTIFICACION");
		return uploadEnvio(rutEnvia, rutCompania, archivoEnviarSII, token,
				urlEnvio, hostEnvio);
	}

	private RECEPCIONDTEDocument uploadEnvio(String rutEnvia,
			String rutCompania, File archivoEnviarSII, String token,
			String urlEnvio, String hostEnvio) throws IOException, XmlException {

		// Crear boundary para multipart
		String boundary = "----WebKitFormBoundary" + System.currentTimeMillis();
		
		// Construir cuerpo multipart manualmente
		StringBuilder multipartBody = new StringBuilder();
		
		// Agregar campos de texto
		multipartBody.append("--").append(boundary).append("\r\n");
		multipartBody.append("Content-Disposition: form-data; name=\"rutSender\"\r\n\r\n");
		multipartBody.append(rutEnvia.substring(0, rutEnvia.length() - 2)).append("\r\n");
		
		multipartBody.append("--").append(boundary).append("\r\n");
		multipartBody.append("Content-Disposition: form-data; name=\"dvSender\"\r\n\r\n");
		multipartBody.append(rutEnvia.substring(rutEnvia.length() - 1)).append("\r\n");
		
		multipartBody.append("--").append(boundary).append("\r\n");
		multipartBody.append("Content-Disposition: form-data; name=\"rutCompany\"\r\n\r\n");
		multipartBody.append(rutCompania.substring(0, rutCompania.length() - 2)).append("\r\n");
		
		multipartBody.append("--").append(boundary).append("\r\n");
		multipartBody.append("Content-Disposition: form-data; name=\"dvCompany\"\r\n\r\n");
		multipartBody.append(rutCompania.substring(rutCompania.length() - 1)).append("\r\n");
		
		// Agregar archivo
		multipartBody.append("--").append(boundary).append("\r\n");
		multipartBody.append("Content-Disposition: form-data; name=\"archivo\"; filename=\"")
					.append(archivoEnviarSII.getName()).append("\"\r\n");
		multipartBody.append("Content-Type: application/xml\r\n\r\n");
		
		// Leer contenido del archivo
		String fileContent = Files.readString(archivoEnviarSII.toPath());
		multipartBody.append(fileContent).append("\r\n");
		
		// Cerrar multipart
		multipartBody.append("--").append(boundary).append("--\r\n");

		// Crear HTTP Client
		HttpClient client = HttpClient.newBuilder()
			.cookieHandler(CookieHandler.getDefault())
			.build();

		// Crear request
		HttpRequest request = HttpRequest.newBuilder()
			.uri(URI.create(urlEnvio))
			.header("Content-Type", "multipart/form-data; boundary=" + boundary)
			.header("Cookie", "TOKEN=" + token)
			.header("User-Agent", Utilities.netLabels.getString("UPLOAD_SII_HEADER_VALUE"))
			.POST(HttpRequest.BodyPublishers.ofString(multipartBody.toString()))
			.build();

		try {
			// Enviar request
			HttpResponse<String> response = client.send(request, 
				HttpResponse.BodyHandlers.ofString());

			// Parsear respuesta
			HashMap<String, String> namespaces = new HashMap<>();
			namespaces.put("", "http://www.sii.cl/SiiDte");
			XmlOptions opts = new XmlOptions();
			opts.setLoadSubstituteNamespaces(namespaces);

			return RECEPCIONDTEDocument.Factory.parse(response.body(), opts);
			
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new IOException("Request interrupted", e);
		}
	}

}
