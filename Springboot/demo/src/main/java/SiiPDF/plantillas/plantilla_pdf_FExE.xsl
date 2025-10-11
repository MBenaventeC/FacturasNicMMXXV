<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	xmlns:tedbarcode="cl.nic.dte.fop.TedBarcodeExtension"
	extension-element-prefixes="tedbarcode"
	>

	<xsl:output method="xml" version="1.0" omit-xml-declaration="no"
		indent="yes" />

	<xsl:param name="versionParam" select="'1.0'" />
	<xsl:template match="/">
		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
			<fo:layout-master-set>
				<fo:simple-page-master master-name="simple"
					page-height="27.9cm" page-width="21.6cm" margin-top="1cm"
					margin-bottom="2cm" margin-left="1cm" margin-right="1cm">
					<fo:region-body margin-top="0cm" />
					<fo:region-before extent="3cm" />
					<fo:region-after extent="1.5cm" />
				</fo:simple-page-master>
			</fo:layout-master-set>


			<fo:page-sequence master-reference="simple">
				<fo:flow flow-name="xsl-region-body">
					<xsl:apply-templates select="DTE/Documento" />
				</fo:flow>
			</fo:page-sequence>

		</fo:root>
	</xsl:template>


	<xsl:template match="DTE/Documento">
		<fo:block>
			<xsl:apply-templates select="Encabezado/Emisor">
				<xsl:with-param name="folio">
					<xsl:value-of select="Encabezado/IdDoc/Folio" />
				</xsl:with-param>
				<xsl:with-param name="tipo">
					<xsl:value-of select="Encabezado/IdDoc/TipoDTE" />
				</xsl:with-param>
			</xsl:apply-templates>
			<xsl:apply-templates select="Encabezado/Receptor">
				<xsl:with-param name="fecha">
					<xsl:value-of select="Encabezado/IdDoc/FchEmis" />
				</xsl:with-param>
				<xsl:with-param name="medioPago">
					<xsl:value-of select="Encabezado/IdDoc/MedioPago" />
				</xsl:with-param>
				<xsl:with-param name="formaPago">
					<xsl:value-of select="Encabezado/IdDoc/FmaPago" />
				</xsl:with-param>
			</xsl:apply-templates>

			<!--  La lista de detalle -->
			<fo:block-container absolute-position="absolute" left="0cm"
				top="9cm">
				<fo:block font-size="8pt" font-family="monospace"
					color="black" text-align="left" space-before="8pt">
					<fo:table table-layout="fixed" width="100%"
						border-collapse="collapse">
						<fo:table-column column-width="2cm" />
						<fo:table-column column-width="12.5cm" />
						<fo:table-column column-width="2.5cm" />
						<fo:table-column column-width="2.5cm" />

						<fo:table-body>
							<fo:table-row>
								<fo:table-cell text-align="center"
									border-width="0.5pt" border-style="solid">
									<fo:block>
										<fo:inline font-weight="bold">
											Cantidad
										</fo:inline>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="center"
									border-width="0.5pt" border-style="solid">
									<fo:block>
										<fo:inline font-weight="bold">
											Detalle
										</fo:inline>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="center"
									border-width="0.5pt" border-style="solid">
									<fo:block>
										<fo:inline font-weight="bold">
											P. Unitario
										</fo:inline>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="center"
									border-width="0.5pt" border-style="solid">
									<fo:block>
										<fo:inline font-weight="bold">
											Total
										</fo:inline>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<xsl:choose>
								<xsl:when test="Detalle[NroLinDet=1]">
									<xsl:apply-templates
										select="Detalle[NroLinDet=1]" />
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template
										name="DetalleVacio" />
								</xsl:otherwise>
							</xsl:choose>
							<xsl:choose>
								<xsl:when test="Detalle[NroLinDet=2]">
									<xsl:apply-templates
										select="Detalle[NroLinDet=2]" />
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template
										name="DetalleVacio" />
								</xsl:otherwise>
							</xsl:choose>
							<xsl:choose>
								<xsl:when test="Detalle[NroLinDet=3]">
									<xsl:apply-templates
										select="Detalle[NroLinDet=3]" />
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template
										name="DetalleVacio" />
								</xsl:otherwise>
							</xsl:choose>
							<xsl:choose>
								<xsl:when test="Detalle[NroLinDet=4]">
									<xsl:apply-templates
										select="Detalle[NroLinDet=4]" />
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template
										name="DetalleVacio" />
								</xsl:otherwise>
							</xsl:choose>
							<xsl:choose>
								<xsl:when test="Detalle[NroLinDet=5]">
									<xsl:apply-templates
										select="Detalle[NroLinDet=5]" />
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template
										name="DetalleVacio" />
								</xsl:otherwise>
							</xsl:choose>
							<xsl:choose>
								<xsl:when test="Detalle[NroLinDet=6]">
									<xsl:apply-templates
										select="Detalle[NroLinDet=6]" />
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template
										name="DetalleVacio" />
								</xsl:otherwise>
							</xsl:choose>
							<xsl:choose>
								<xsl:when test="Detalle[NroLinDet=7]">
									<xsl:apply-templates
										select="Detalle[NroLinDet=7]" />
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template
										name="DetalleVacio" />
								</xsl:otherwise>
							</xsl:choose>
							<xsl:choose>
								<xsl:when test="Detalle[NroLinDet=8]">
									<xsl:apply-templates
										select="Detalle[NroLinDet=8]" />
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template
										name="DetalleVacio" />
								</xsl:otherwise>
							</xsl:choose>
							<xsl:choose>
								<xsl:when test="Detalle[NroLinDet=9]">
									<xsl:apply-templates
									select="Detalle[NroLinDet=9]" />
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template
										name="DetalleVacio" />
								</xsl:otherwise>
							</xsl:choose>
							<xsl:choose>
								<xsl:when
									test="Detalle[NroLinDet=10]">
									<xsl:apply-templates
										select="Detalle[NroLinDet=10]" />
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template
										name="DetalleVacio" />
								</xsl:otherwise>
							</xsl:choose>
							<xsl:choose>
								<xsl:when
									test="Detalle[NroLinDet=11]">
									<xsl:apply-templates
										select="Detalle[NroLinDet=11]" />
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template
										name="DetalleVacio" />
								</xsl:otherwise>
							</xsl:choose>
							<fo:table-row >
								<fo:table-cell text-align="right" border-left-width="0.5pt"
									border-left-style="solid" border-right-width="0.5pt"
									border-right-style="solid" margin-right="2mm"  height="0.8cm">
									<fo:block>
											<fo:block>&#160;</fo:block>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="left" border-left-width="0.5pt"
									border-left-style="solid" border-right-width="0.5pt"
									border-right-style="solid"  margin-right="2mm" margin-left="2mm"  height="0.8cm">
									<fo:block >
											<xsl:text>Atención de: </xsl:text>
											<xsl:choose>
												<xsl:when test="Encabezado/Receptor/Contacto">
													<xsl:value-of select="Encabezado/Receptor/Contacto" />
												</xsl:when>
												<xsl:otherwise>Sin contacto</xsl:otherwise>
											</xsl:choose>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="right" border-left-width="0.5pt"
									border-left-style="solid" border-right-width="0.5pt"
									border-right-style="solid" margin-right="2mm"  height="0.8cm">
									<fo:block>
											<fo:block>&#160;</fo:block>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="right" border-left-width="0.5pt"
									border-left-style="solid" border-right-width="0.5pt"
									border-right-style="solid" margin-right="2mm" height="0.8cm" >
									<fo:block>
											<fo:block>&#160;</fo:block>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>

							<fo:table-row>
								<fo:table-cell text-align="center"
									border-left-width="0.5pt" border-left-style="solid"
									border-right-width="0.5pt" border-right-style="solid"
									border-bottom-width="0.5pt" border-bottom-style="solid"
									number-columns-spanned="4">
									<fo:block />
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell text-align="center"
									border-width="0.5pt" border-style="solid" display-align="center" column-number="3" height="1cm">
									<fo:block>
										<fo:inline font-weight="bold">
											Exento
										</fo:inline>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="center"
									border-width="0.5pt" border-style="solid" column-number="4" display-align="center" height="1cm">
									<fo:block>
										<fo:inline font-weight="bold">
											<xsl:value-of select="Encabezado/Totales/MntExe"/>
										</fo:inline>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell text-align="center"
									border-width="0.5pt" border-style="solid" column-number="3" display-align="center" height="1cm">
									<fo:block>
										<fo:inline font-weight="bold">
											Total
										</fo:inline>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="center"
									border-width="0.5pt" border-style="solid" column-number="4" display-align="center" height="1cm">
									<fo:block>
										<fo:inline font-weight="bold">
											<xsl:value-of select="Encabezado/Totales/MntTotal"/>
										</fo:inline>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
				</fo:block>
			</fo:block-container>
			<fo:block-container absolute-position="absolute" bottom="0.5cm"
				margin-top="0.5cm" left="14.5cm" height="3cm" width="5cm"
				border-color="gray" border-style="solid" border-width="1mm">
				<fo:block font-size="16pt" font-family="monospace"
					font-weight="bold" color="gray" text-align="center"
					hyphenate="false" margin-top="0.6cm">
					P A G A D O
				</fo:block>

				<fo:block font-size="12pt" font-family="monospace"
					font-weight="bold" color="gray" text-align="center"
					hyphenate="false">
					<xsl:call-template name="FechaFormat">
						<xsl:with-param name="fecha">
							<xsl:value-of select="Encabezado/IdDoc/FchEmis" />
						</xsl:with-param>
					</xsl:call-template>
				</fo:block>

				<fo:block font-size="16pt" font-family="monospace"
					font-weight="bold" color="gray" text-align="center"
					hyphenate="false">
					NIC CHILE
				</fo:block>
			</fo:block-container>
			<fo:block-container absolute-position="absolute" left="7.2cm" bottom="1.5cm" width="7cm" height="4cm" border-color="black" border-style="solid" border-width="0.5mm">
				<fo:block font-size="12pt" font-family="monospace" color="black" text-align="center" margin-top="0.2cm">
					ACUSE DE RECIBO
				</fo:block>
				<fo:block font-size="10pt" font-family="monospace" color="black" text-align="left" margin-left="0.5cm" margin-top="0.2cm">
					Nombre <fo:inline>____________________</fo:inline>
				</fo:block>
				<fo:block font-size="10pt" font-family="monospace" color="black" text-align="left" margin-left="0.5cm">
					RUT <fo:inline>_______________________</fo:inline>
				</fo:block>
				<fo:block font-size="10pt" font-family="monospace" color="black" text-align="left" margin-left="0.5cm">
					Fecha <fo:inline>_____________________</fo:inline>
				</fo:block>
				<fo:block font-size="10pt" font-family="monospace" color="black" text-align="left" margin-left="0.5cm" margin-bottom="0.5cm">
					Recinto <fo:inline>___________________</fo:inline>
				</fo:block>
				<fo:block font-size="10pt" font-family="monospace" color="black" text-align="left" margin-left="0.5cm" margin-bottom="0.9cm">
					Firma <fo:inline>_____________________</fo:inline>
				</fo:block>
				<fo:block font-size="7pt" font-family="monospace" color="black" text-align="left">
					EL ACUSE RECIBO QUE SE DECLARA EN ESTE ACTO, DE ACUERDO A LO DISPUESTO EN LA LETRA B) DEL ART. 9° Y LA LETRA A) DEL ART. 12 DE LA LEY 19.983, AUTORIZA LA ENTREGA EN PROPIEDAD DE LOS BIENES Y/O SERVICIOS PRESTADOS, HABIENDO SIDO RECIBIDOS EN TOTAL CONFORMIDAD.
				</fo:block>
			</fo:block-container>
			<xsl:apply-templates select="TED" />
		</fo:block>
	</xsl:template>


	<!-- Datos del emisor -->
	<xsl:template match="Emisor">
		<xsl:param name="folio" />
		<xsl:param name="tipo" />

		<!--  El logo -->
		<fo:block-container absolute-position="absolute" left="0cm"
			top="-0.5cm">
			<fo:block>
				<fo:external-graphic
					src="url('src/main/java/SiiPDF/logoUchile3.png')"
					content-width="2.5cm"
    				content-height="4cm"/>
			</fo:block>
		</fo:block-container>

		<fo:block-container absolute-position="absolute" left="2.5cm"
			top="0cm" width="9cm">

			<fo:block font-size="18pt" font-family="Helvetica"
				font-weight="bold" text-align="left" color="blue">
				<xsl:value-of select="RznSoc" />
			</fo:block>

			<xsl:if test="Sucursal">
				<fo:block font-weight="bold" font-size="12pt" font-family="monospace"
					language="es" hyphenate="true" color="black" text-align="left">
					Sucursal: <xsl:value-of select="Sucursal" /> (Codigo SII: <xsl:value-of select="CdgSIISucur" />)
				</fo:block>
			</xsl:if>

			<fo:block font-weight="bold" font-size="12pt" font-family="monospace"
				language="es" hyphenate="true" color="black" text-align="left">
				<xsl:value-of select="GiroEmis" />
			</fo:block>

			<fo:block font-weight="bold" font-size="12pt" font-family="monospace"
				language="es" hyphenate="true" color="black" text-align="left">
				<xsl:value-of select="DirOrigen" />
			</fo:block>

			<fo:block font-weight="bold" font-size="12pt" font-family="monospace"
				language="es" hyphenate="true" color="black" text-align="left">
				<xsl:value-of select="CmnaOrigen" />
				,
				<xsl:value-of select="CiudadOrigen" />
			</fo:block>

		</fo:block-container>

		<!-- Recuadro con folio -->
		<fo:block-container absolute-position="absolute" top="0cm"
			margin-top="0.5cm" left="12cm" height="3cm" width="7.5cm"
			border-color="red" border-style="solid" border-width="0.5mm">
			<fo:block font-size="14pt" font-family="monospace"
				font-weight="bold" color="red" text-align="center"
				hyphenate="false">
				R.U.T.:
				<xsl:call-template name="RutFormat">
					<xsl:with-param name="rut">
						<xsl:value-of select="RUTEmisor" />
					</xsl:with-param>
				</xsl:call-template>
			</fo:block>
			<fo:block font-size="14pt" font-family="monospace"
				font-weight="bold" color="red" text-align="center">
				<xsl:choose>
					<xsl:when test="$tipo=34">
						FACTURA NO AFECTA O EXENTA ELECTRONICA
					</xsl:when>
					<xsl:when test="$tipo=52">
						GUIA DE DESPACHO ELECTRONICA
					</xsl:when>
					<xsl:when test="$tipo=56">
						NOTA DE DEBITO ELECTRONICA
					</xsl:when>
					<xsl:when test="$tipo=61">
						NOTA DE CREDITO ELECTRONICA
					</xsl:when>
					<xsl:when test="$tipo=41">
						BOLETA EXENTA ELECTRÓNICA
					</xsl:when>
					<xsl:otherwise>
						CORREGIR EN TEMPLATE XSL
					</xsl:otherwise>
				</xsl:choose>
			</fo:block>
			<fo:block font-size="14pt" font-family="monospace"
				font-weight="bold" color="red" text-align="center">
				N&#176;
				<xsl:value-of select="$folio" />
			</fo:block>
		</fo:block-container>
		<fo:block-container absolute-position ="absolute" left = "12cm" top ="3cm" width ="7.5cm">
			<fo:block font-size="14pt" font-family="monospace"
					font-weight="bold" color="red" text-align="center"
					hyphenate="false">
					SII- SANTIAGO CENTRO
			</fo:block>
		</fo:block-container>
	</xsl:template>

	<!-- Datos del receptor -->
	<xsl:template match="Receptor">
		<xsl:param name="fecha" />
		<xsl:param name="medioPago"/>
		<xsl:param name="formaPago"/>

     	<fo:block-container absolute-position="absolute" left="0cm"
			top="4cm">
			<fo:block font-size="10pt" font-family="monospace" space-after="8pt"
				language="es" hyphenate="true" color="black" text-align="left">
				Santiago,
				<xsl:call-template name="FechaFormat">
					<xsl:with-param name="fecha">
						<xsl:value-of select="$fecha" />
					</xsl:with-param>
				</xsl:call-template>

			</fo:block>
		
			<fo:block font-size="10pt" font-family="monospace"
				language="es" hyphenate="true" color="black" text-align="left">
				<fo:table table-layout="fixed" width="100%">
					<fo:table-column column-width="3cm" />
					<fo:table-column column-width="5cm" />
					<fo:table-column column-width="4cm" />
					<fo:table-column column-width="5cm" />


					<fo:table-body>
						<fo:table-row>
							<fo:table-cell text-align="left">
								<fo:block>
									<fo:inline font-weight="bold">
										SE&#209;OR(ES):
									</fo:inline>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell text-align="left"
								number-columns-spanned="3">
								<fo:block>
									<xsl:value-of select="RznSocRecep" />
								</fo:block>
							</fo:table-cell>
						</fo:table-row>
						<fo:table-row>
							<fo:table-cell text-align="left">
								<fo:block>
									<fo:inline font-weight="bold">
										R.U.T.:
									</fo:inline>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell text-align="left"
								number-columns-spanned="3">
								<fo:block>
									<xsl:value-of select="RUTRecep" />
								</fo:block>
							</fo:table-cell>
						</fo:table-row>
						<fo:table-row>
							<fo:table-cell text-align="left">
								<fo:block>
									<fo:inline font-weight="bold">
										DIRECCION:
									</fo:inline>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell text-align="left"
								number-columns-spanned="3">
								<fo:block>
									<xsl:value-of select="DirRecep" />
								</fo:block>
							</fo:table-cell>
						</fo:table-row>
						<fo:table-row>
							<fo:table-cell text-align="left">
								<fo:block>
									<fo:inline font-weight="bold">
										COMUNA:
									</fo:inline>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell text-align="left">
								<fo:block>
									<xsl:value-of select="CmnaRecep" />
								</fo:block>
							</fo:table-cell>
							<fo:table-cell text-align="left">
								<fo:block>
									<fo:inline font-weight="bold">
										CIUDAD:
									</fo:inline>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell text-align="left">
								<fo:block>
									<xsl:value-of select="CiudadRecep" />
								</fo:block>
							</fo:table-cell>
						</fo:table-row>
						<fo:table-row>
							<fo:table-cell text-align="left">
								<fo:block>
									<fo:inline font-weight="bold">
										GIRO:
									</fo:inline>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell text-align="left">
								<fo:block>
									<xsl:value-of select="GiroRecep" />
								</fo:block>
							</fo:table-cell>
							<fo:table-cell text-align="left">
								<fo:block>
									<fo:inline font-weight="bold">
										CONDICION VENTA:
									</fo:inline>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell text-align="left">
								<fo:block>
									<xsl:call-template name="PagoFormat">
										<xsl:with-param name="medioPago" select="$medioPago"/>
										<xsl:with-param name="formaPago" select="$formaPago"/>
									</xsl:call-template>
								</fo:block>
							</fo:table-cell>
						</fo:table-row>
						<fo:table-row>
							<fo:table-cell text-align="left" number-columns-spanned="2">
								<fo:block>
									<fo:inline font-weight="bold">CENTRO DE COSTO:</fo:inline>
									<xsl:text> 1966</xsl:text>				
								</fo:block>
							</fo:table-cell>
						
							<fo:table-cell text-align="left" number-columns-spanned="2">
								<fo:block>
									<fo:inline font-weight="bold">ITEM:</fo:inline>
									<xsl:text> 6.1.01.03.01 (2152)</xsl:text>	
								</fo:block>
							</fo:table-cell>
						</fo:table-row>
						<fo:table-row>
							<fo:table-cell text-align="left" number-columns-spanned="2">
								<fo:block>
									<fo:inline font-weight="bold">Vencimiento: </fo:inline>
									<xsl:text> </xsl:text>
            						<xsl:value-of select="../IdDoc/FchVenc" />
								</fo:block>
							</fo:table-cell>
						</fo:table-row>
					</fo:table-body>
				</fo:table>
				

			</fo:block>

</fo:block-container>
	</xsl:template>

<!-- Detalle -->
	<xsl:template match="Detalle">
		<fo:table-row >
			<fo:table-cell text-align="right" border-left-width="0.5pt"
				border-left-style="solid" border-right-width="0.5pt"
				border-right-style="solid" margin-right="2mm"  height="0.8cm">
				<fo:block>
						<xsl:value-of select="QtyItem" />
				</fo:block>
			</fo:table-cell>
			<fo:table-cell text-align="left" border-left-width="0.5pt"
				border-left-style="solid" border-right-width="0.5pt"
				border-right-style="solid"  margin-right="2mm" margin-left="2mm"  height="0.8cm">
				<fo:block >
						<xsl:value-of select="NmbItem" />
				</fo:block>
			</fo:table-cell>
			<fo:table-cell text-align="right" border-left-width="0.5pt"
				border-left-style="solid" border-right-width="0.5pt"
				border-right-style="solid" margin-right="2mm"  height="0.8cm">
				<fo:block>
						<xsl:value-of select="PrcItem" />
				</fo:block>
			</fo:table-cell>
			<fo:table-cell text-align="right" border-left-width="0.5pt"
				border-left-style="solid" border-right-width="0.5pt"
				border-right-style="solid" margin-right="2mm" height="0.8cm" >
				<fo:block>
						<xsl:value-of select="MontoItem"/>
				</fo:block>
			</fo:table-cell>
		</fo:table-row>
	</xsl:template>

	<!-- Timbre electrónico -->
	<xsl:template match="TED">
		<fo:block-container absolute-position="absolute" left = "-1.5cm" top="20cm" width="10cm">
			<fo:block text-align="center">
				<fo:external-graphic src="url('test_files/Out/ted_temp/ted_barcode.png')" 
										content-width="6cm" 
										content-height="18cm"
										/>
			</fo:block>
			<fo:block font-size="8pt" font-family="sans-serif"
				text-align="center">
				Timbre Electrónico SII
			</fo:block>
			<fo:block font-size="8pt" font-family="sans-serif"
				text-align="center">
				Res. XX de 2007 - Verifique Documento: www.sii.cl
			</fo:block>
		</fo:block-container>
	</xsl:template>

	<xsl:template name="PagoFormat">
		<xsl:param name="medioPago" />
		<xsl:param name="formaPago" />

		<xsl:choose>
			<xsl:when test="$medioPago='CH'">Cheque</xsl:when>
			<xsl:when test="$medioPago='LT'">Letra</xsl:when>
			<xsl:when test="$medioPago='EF'">Efectivo</xsl:when>
			<xsl:when test="$medioPago='PE'">Pago a Cta. Corriente</xsl:when>
			<xsl:when test="$medioPago='TC'">Tarjeta de Crédito</xsl:when>
			<xsl:when test="$medioPago='CF'">Cheque a Fecha</xsl:when>
			<xsl:when test="$medioPago='OT'">Otro</xsl:when>
		</xsl:choose>

		<xsl:choose>
			<xsl:when test="$formaPago=1"> (Contado)</xsl:when>
			<xsl:when test="$formaPago=2"> (Crédito)</xsl:when>
			<xsl:when test="$formaPago=3"> (Sin Valor)</xsl:when>
		</xsl:choose>

	</xsl:template>

	<xsl:template name="FechaFormat">
		<xsl:param name="fecha" />

		<xsl:value-of
			select="substring($fecha,string-length($fecha)-1,2)" />
		de
		<xsl:choose>
			<xsl:when
				test="substring($fecha,string-length($fecha)-4,2)=01">
				Enero
			</xsl:when>
			<xsl:when
				test="substring($fecha,string-length($fecha)-4,2)=02">
				Febrero
			</xsl:when>
			<xsl:when
				test="substring($fecha,string-length($fecha)-4,2)=03">
				Marzo
			</xsl:when>
			<xsl:when
				test="substring($fecha,string-length($fecha)-4,2)=04">
				Abril
			</xsl:when>
			<xsl:when
				test="substring($fecha,string-length($fecha)-4,2)=05">
				Mayo
			</xsl:when>
			<xsl:when
				test="substring($fecha,string-length($fecha)-4,2)=06">
				Junio
			</xsl:when>
			<xsl:when
				test="substring($fecha,string-length($fecha)-4,2)=07">
				Julio
			</xsl:when>
			<xsl:when
				test="substring($fecha,string-length($fecha)-4,2)=08">
				Agosto
			</xsl:when>
			<xsl:when
				test="substring($fecha,string-length($fecha)-4,2)=09">
				Septiembre
			</xsl:when>
			<xsl:when
				test="substring($fecha,string-length($fecha)-4,2)=10">
				Octubre
			</xsl:when>
			<xsl:when
				test="substring($fecha,string-length($fecha)-4,2)=11">
				Noviembre
			</xsl:when>
			<xsl:when
				test="substring($fecha,string-length($fecha)-4,2)=12">
				Diciembre
			</xsl:when>
		</xsl:choose>
		de
		<xsl:value-of
			select="substring($fecha,string-length($fecha)-9,4)" />
	</xsl:template>

	<xsl:template name="RutFormat">
		<xsl:param name="rut" />
		<xsl:variable name="num" select="substring-before($rut,'-')" />
		<xsl:variable name="dv" select="substring-after($rut,'-')" />

		<xsl:value-of select="substring($num,string-length($num)-8,3)" />.<xsl:value-of
		 select="substring($num,string-length($num)-5,3)" />.<xsl:value-of 
		 select="substring($num,string-length($num)-2,3)" />-<xsl:value-of select="$dv" />

	</xsl:template>

	<xsl:template name="DetalleVacio">
		<fo:table-row>
			<fo:table-cell text-align="center" border-left-width="0.5pt"
				border-left-style="solid" border-right-width="0.5pt"
				border-right-style="solid" height="0.8cm">
				<fo:block white-space-treatment="preserve">&#xa0;</fo:block>
			</fo:table-cell>
			<fo:table-cell text-align="center" border-left-width="0.5pt"
				border-left-style="solid" border-right-width="0.5pt"
				border-right-style="solid" height="0.8cm">
				<fo:block white-space-treatment="preserve">&#xa0;</fo:block>
			</fo:table-cell>
			<fo:table-cell text-align="center" border-left-width="0.5pt"
				border-left-style="solid" border-right-width="0.5pt"
				border-right-style="solid" height="0.8cm">
				<fo:block white-space-treatment="preserve">&#xa0;</fo:block>
			</fo:table-cell>
			<fo:table-cell text-align="center" border-left-width="0.5pt"
				border-left-style="solid" border-right-width="0.5pt"
				border-right-style="solid" height="0.8cm">
				<fo:block white-space-treatment="preserve">&#xa0;</fo:block>
			</fo:table-cell>
		</fo:table-row>
	</xsl:template>

		<!-- Codigo País-->
	<xsl:template name = "CodPais">
		<xsl:param name = "codigoPais"/>
		<xsl:choose>
			<xsl:when test="$codigoPais='100'">Territorio Británico en Asia</xsl:when>
			<xsl:when test="$codigoPais='101'">Senegal</xsl:when>
			<xsl:when test="$codigoPais='102'">Gambia</xsl:when>
			<xsl:when test="$codigoPais='103'">Guinea - Bissau</xsl:when>
			<xsl:when test="$codigoPais='104'">Guinea</xsl:when>
			<xsl:when test="$codigoPais='105'">Sierra Leona</xsl:when>
			<xsl:when test="$codigoPais='106'">Liberia</xsl:when>
			<xsl:when test="$codigoPais='107'">Costa de Marfil</xsl:when>
			<xsl:when test="$codigoPais='108'">Ghana</xsl:when>
			<xsl:when test="$codigoPais='109'">Togo</xsl:when>
			<xsl:when test="$codigoPais='111'">Nigeria</xsl:when>
			<xsl:when test="$codigoPais='112'">Sudáfrica</xsl:when>
			<xsl:when test="$codigoPais='113'">Botswana</xsl:when>
			<xsl:when test="$codigoPais='114'">Lesotho</xsl:when>
			<xsl:when test="$codigoPais='115'">Malawi</xsl:when>
			<xsl:when test="$codigoPais='116'">Zimbabwe</xsl:when>
			<xsl:when test="$codigoPais='117'">Zambia</xsl:when>
			<xsl:when test="$codigoPais='118'">Comoras</xsl:when>
			<xsl:when test="$codigoPais='119'">Mauricio</xsl:when>
			<xsl:when test="$codigoPais='120'">Madagascar</xsl:when>
			<xsl:when test="$codigoPais='121'">Mozambique</xsl:when>
			<xsl:when test="$codigoPais='122'">Swazilandia</xsl:when>
			<xsl:when test="$codigoPais='123'">Sudán</xsl:when>
			<xsl:when test="$codigoPais='124'">Egipto</xsl:when>
			<xsl:when test="$codigoPais='125'">Libia</xsl:when>
			<xsl:when test="$codigoPais='126'">Tunez</xsl:when>
			<xsl:when test="$codigoPais='127'">Argelia</xsl:when>
			<xsl:when test="$codigoPais='128'">Marruecos</xsl:when>
			<xsl:when test="$codigoPais='129'">Cabo Verde</xsl:when>
			<xsl:when test="$codigoPais='130'">Chad</xsl:when>
			<xsl:when test="$codigoPais='131'">Niger</xsl:when>
			<xsl:when test="$codigoPais='132'">Alto Volta</xsl:when>
			<xsl:when test="$codigoPais='133'">Mali</xsl:when>
			<xsl:when test="$codigoPais='134'">Mauritania</xsl:when>
			<xsl:when test="$codigoPais='135'">Tanzania</xsl:when>
			<xsl:when test="$codigoPais='136'">Uganda</xsl:when>
			<xsl:when test="$codigoPais='137'">Kenia</xsl:when>
			<xsl:when test="$codigoPais='138'">Somalia</xsl:when>
			<xsl:when test="$codigoPais='139'">Etiopia</xsl:when>
			<xsl:when test="$codigoPais='140'">Angola</xsl:when>
			<xsl:when test="$codigoPais='141'">Burundi</xsl:when>
			<xsl:when test="$codigoPais='142'">Rwanda</xsl:when>
			<xsl:when test="$codigoPais='143'">República Democratica del Congo (ex Zaire)</xsl:when>
			<xsl:when test="$codigoPais='144'">República del Congo</xsl:when>
			<xsl:when test="$codigoPais='145'">Gabon</xsl:when>
			<xsl:when test="$codigoPais='146'">Sao Tome y Príncipe</xsl:when>
			<xsl:when test="$codigoPais='147'">Guinea Ecuatorial</xsl:when>
			<xsl:when test="$codigoPais='148'">República Centroafricana</xsl:when>
			<xsl:when test="$codigoPais='149'">Camerún</xsl:when>
			<xsl:when test="$codigoPais='150'">Benín</xsl:when>
			<xsl:when test="$codigoPais='151'">Territorio Britanico en Africa</xsl:when>
			<xsl:when test="$codigoPais='152'">Territorio Español en África</xsl:when>
			<xsl:when test="$codigoPais='153'">Territorio Francés en África</xsl:when>
			<xsl:when test="$codigoPais='154'">Bophuthatswana</xsl:when>
			<xsl:when test="$codigoPais='155'">Djibouti</xsl:when>
			<xsl:when test="$codigoPais='156'">Seychelles</xsl:when>
			<xsl:when test="$codigoPais='158'">Vienda</xsl:when>
			<xsl:when test="$codigoPais='159'">Namibia</xsl:when>
			<xsl:when test="$codigoPais='160'">Sudán del Sur</xsl:when>
			<xsl:when test="$codigoPais='161'">Burkina Faso</xsl:when>
			<xsl:when test="$codigoPais='162'">Ciskey</xsl:when>
			<xsl:when test="$codigoPais='163'">Eritrea</xsl:when>
			<xsl:when test="$codigoPais='164'">Islas Marshall</xsl:when>
			<xsl:when test="$codigoPais='165'">Saharavi</xsl:when>
			<xsl:when test="$codigoPais='166'">Transkei</xsl:when>
			<xsl:when test="$codigoPais='201'">Venezuela</xsl:when>
			<xsl:when test="$codigoPais='202'">Colombia</xsl:when>
			<xsl:when test="$codigoPais='203'">Trinidad y Tobago</xsl:when>
			<xsl:when test="$codigoPais='204'">Barbados</xsl:when>
			<xsl:when test="$codigoPais='205'">Jamaica</xsl:when>
			<xsl:when test="$codigoPais='206'">República Dominicana</xsl:when>
			<xsl:when test="$codigoPais='207'">Bahamas</xsl:when>
			<xsl:when test="$codigoPais='208'">Haití</xsl:when>
			<xsl:when test="$codigoPais='209'">Cuba</xsl:when>
			<xsl:when test="$codigoPais='210'">Panamá</xsl:when>
			<xsl:when test="$codigoPais='211'">Costa Rica</xsl:when>
			<xsl:when test="$codigoPais='212'">Nicaragua</xsl:when>
			<xsl:when test="$codigoPais='213'">El Salvador</xsl:when>
			<xsl:when test="$codigoPais='214'">Honduras</xsl:when>
			<xsl:when test="$codigoPais='215'">Guatemala</xsl:when>
			<xsl:when test="$codigoPais='216'">México</xsl:when>
			<xsl:when test="$codigoPais='217'">Guyana</xsl:when>
			<xsl:when test="$codigoPais='218'">Ecuador</xsl:when>
			<xsl:when test="$codigoPais='219'">Perú</xsl:when>
			<xsl:when test="$codigoPais='220'">Brasil</xsl:when>
			<xsl:when test="$codigoPais='221'">Bolivia</xsl:when>
			<xsl:when test="$codigoPais='222'">Paraguay</xsl:when>
			<xsl:when test="$codigoPais='223'">Uruguay</xsl:when>
			<xsl:when test="$codigoPais='224'">Argentina</xsl:when>
			<xsl:when test="$codigoPais='225'">Estados Unidos de América</xsl:when>
			<xsl:when test="$codigoPais='226'">Canadá</xsl:when>
			<xsl:when test="$codigoPais='227'">Territorio Británico en América</xsl:when>
			<xsl:when test="$codigoPais='228'">Territorio Francés en América</xsl:when>
			<xsl:when test="$codigoPais='229'">Territorio Holandes en América</xsl:when>
			<xsl:when test="$codigoPais='230'">Territorio de Dinamarca en América</xsl:when>
			<xsl:when test="$codigoPais='231'">Dominica</xsl:when>
			<xsl:when test="$codigoPais='232'">Granada</xsl:when>
			<xsl:when test="$codigoPais='233'">Santa Lucía (Islas  Occidentales)</xsl:when>
			<xsl:when test="$codigoPais='234'">San Vicente y las Granadinas</xsl:when>
			<xsl:when test="$codigoPais='235'">Surinam</xsl:when>
			<xsl:when test="$codigoPais='236'">Belice</xsl:when>
			<xsl:when test="$codigoPais='240'">Antigua y Barbuda</xsl:when>
			<xsl:when test="$codigoPais='241'">Saint Kitts &amp; Nevis</xsl:when>
			<xsl:when test="$codigoPais='242'">Anguila</xsl:when>
			<xsl:when test="$codigoPais='243'">Aruba</xsl:when>
			<xsl:when test="$codigoPais='244'">Bermudas</xsl:when>
			<xsl:when test="$codigoPais='245'">Islas Virgenes Britanicas</xsl:when>
			<xsl:when test="$codigoPais='246'">Islas Caymán</xsl:when>
			<xsl:when test="$codigoPais='247'">Antillas Neerlandesas</xsl:when>
			<xsl:when test="$codigoPais='248'">Turcas y Caicos</xsl:when>
			<xsl:when test="$codigoPais='249'">Islas Virgenes (Estados Unidos de América)</xsl:when>
			<xsl:when test="$codigoPais='250'">Martinica</xsl:when>
			<xsl:when test="$codigoPais='251'">Puerto Rico</xsl:when>
			<xsl:when test="$codigoPais='252'">Monserrat</xsl:when>
			<xsl:when test="$codigoPais='253'">Groenlandia</xsl:when>
			<xsl:when test="$codigoPais='301'">Jordania</xsl:when>
			<xsl:when test="$codigoPais='302'">Arabia Saudita</xsl:when>
			<xsl:when test="$codigoPais='303'">Kuwait</xsl:when>
			<xsl:when test="$codigoPais='304'">Omán</xsl:when>
			<xsl:when test="$codigoPais='305'">Chipre</xsl:when>
			<xsl:when test="$codigoPais='306'">Israel</xsl:when>
			<xsl:when test="$codigoPais='307'">Iraq</xsl:when>
			<xsl:when test="$codigoPais='308'">Afghanistán</xsl:when>
			<xsl:when test="$codigoPais='309'">Irán</xsl:when>
			<xsl:when test="$codigoPais='310'">Siria</xsl:when>
			<xsl:when test="$codigoPais='311'">Libano</xsl:when>
			<xsl:when test="$codigoPais='312'">Qatar</xsl:when>
			<xsl:when test="$codigoPais='313'">Bahrein</xsl:when>
			<xsl:when test="$codigoPais='314'">Sri Lanka</xsl:when>
			<xsl:when test="$codigoPais='315'">Cambodia</xsl:when>
			<xsl:when test="$codigoPais='316'">Laos</xsl:when>
			<xsl:when test="$codigoPais='317'">India</xsl:when>
			<xsl:when test="$codigoPais='318'">Bután</xsl:when>
			<xsl:when test="$codigoPais='319'">Thailandia</xsl:when>
			<xsl:when test="$codigoPais='320'">Nepal</xsl:when>
			<xsl:when test="$codigoPais='321'">Bangladesh</xsl:when>
			<xsl:when test="$codigoPais='322'">Palestina</xsl:when>
			<xsl:when test="$codigoPais='324'">Pakistán</xsl:when>
			<xsl:when test="$codigoPais='325'">Vietnam</xsl:when>
			<xsl:when test="$codigoPais='326'">Myanmar (ex Birmania)</xsl:when>
			<xsl:when test="$codigoPais='327'">Maldivas (Isla Maldivas)</xsl:when>
			<xsl:when test="$codigoPais='328'">Indonesia</xsl:when>
			<xsl:when test="$codigoPais='329'">Malasia</xsl:when>
			<xsl:when test="$codigoPais='330'">Taiwán (Formosa)</xsl:when>
			<xsl:when test="$codigoPais='331'">Japón</xsl:when>
			<xsl:when test="$codigoPais='332'">Singapur</xsl:when>
			<xsl:when test="$codigoPais='333'">Corea del Sur</xsl:when>
			<xsl:when test="$codigoPais='334'">Corea del Norte</xsl:when>
			<xsl:when test="$codigoPais='335'">Filipinas</xsl:when>
			<xsl:when test="$codigoPais='336'">China</xsl:when>
			<xsl:when test="$codigoPais='337'">Mongolia</xsl:when>
			<xsl:when test="$codigoPais='338'">Territorio Británico en Asia</xsl:when>
			<xsl:when test="$codigoPais='341'">Emiratos Arabes Unidos</xsl:when>
			<xsl:when test="$codigoPais='342'">Hong Kong (Región administrativa especial de China)</xsl:when>
			<xsl:when test="$codigoPais='343'">Territorio Portugués en Asia</xsl:when>
			<xsl:when test="$codigoPais='344'">Brunei</xsl:when>
			<xsl:when test="$codigoPais='345'">Macao</xsl:when>
			<xsl:when test="$codigoPais='346'">República de Yemen</xsl:when>
			<xsl:when test="$codigoPais='401'">Fiji</xsl:when>
			<xsl:when test="$codigoPais='402'">Nauru</xsl:when>
			<xsl:when test="$codigoPais='403'">Tonga (Isla Tonga)</xsl:when>
			<xsl:when test="$codigoPais='404'">Samoa Occidental</xsl:when>
			<xsl:when test="$codigoPais='405'">Nueva Zelandia</xsl:when>
			<xsl:when test="$codigoPais='406'">Australia</xsl:when>
			<xsl:when test="$codigoPais='407'">Territorio Británico en Oceanía y el Pacífico</xsl:when>
			<xsl:when test="$codigoPais='408'">Territorio Francés en Oceanía y el Pacífico</xsl:when>
			<xsl:when test="$codigoPais='409'">Territorio NorteAméricano en Oceanía y el Pacífico</xsl:when>
			<xsl:when test="$codigoPais='412'">Papua Nueva Guinea</xsl:when>
			<xsl:when test="$codigoPais='415'">Vanuatu</xsl:when>
			<xsl:when test="$codigoPais='416'">Kiribati</xsl:when>
			<xsl:when test="$codigoPais='417'">Micronesia</xsl:when>
			<xsl:when test="$codigoPais='418'">Islas Salomón</xsl:when>
			<xsl:when test="$codigoPais='419'">Tuvalu</xsl:when>
			<xsl:when test="$codigoPais='420'">Palau (ex Belau)</xsl:when>
			<xsl:when test="$codigoPais='421'">Niue</xsl:when>
			<xsl:when test="$codigoPais='422'">Polinesia Francesa</xsl:when>
			<xsl:when test="$codigoPais='423'">Nueva Caledonia</xsl:when>
			<xsl:when test="$codigoPais='424'">Islas Marianas del Norte</xsl:when>
			<xsl:when test="$codigoPais='425'">Guam</xsl:when>
			<xsl:when test="$codigoPais='426'">Timor Oriental</xsl:when>
			<xsl:when test="$codigoPais='427'">Islas Cook</xsl:when>
			<xsl:when test="$codigoPais='501'">Portugal</xsl:when>
			<xsl:when test="$codigoPais='502'">Alemania F.R.</xsl:when>
			<xsl:when test="$codigoPais='503'">Alemania R.D.</xsl:when>
			<xsl:when test="$codigoPais='504'">Italia</xsl:when>
			<xsl:when test="$codigoPais='505'">Francia</xsl:when>
			<xsl:when test="$codigoPais='506'">Irlanda</xsl:when>
			<xsl:when test="$codigoPais='507'">Dinamarca</xsl:when>
			<xsl:when test="$codigoPais='508'">Suiza</xsl:when>
			<xsl:when test="$codigoPais='509'">Austria</xsl:when>
			<xsl:when test="$codigoPais='510'">Reino Unido</xsl:when>
			<xsl:when test="$codigoPais='511'">Suecia</xsl:when>
			<xsl:when test="$codigoPais='512'">Finlandia</xsl:when>
			<xsl:when test="$codigoPais='513'">Noruega</xsl:when>
			<xsl:when test="$codigoPais='514'">Bélgica</xsl:when>
			<xsl:when test="$codigoPais='515'">Holanda</xsl:when>
			<xsl:when test="$codigoPais='516'">Islandia</xsl:when>
			<xsl:when test="$codigoPais='517'">España</xsl:when>
			<xsl:when test="$codigoPais='518'">Albania</xsl:when>
			<xsl:when test="$codigoPais='519'">Rumania</xsl:when>
			<xsl:when test="$codigoPais='520'">Grecia</xsl:when>
			<xsl:when test="$codigoPais='521'">U.R.S.S.</xsl:when>
			<xsl:when test="$codigoPais='522'">Turquia</xsl:when>
			<xsl:when test="$codigoPais='523'">Malta</xsl:when>
			<xsl:when test="$codigoPais='524'">Santa Sede (Ciudad del Vaticano)</xsl:when>
			<xsl:when test="$codigoPais='525'">Andorra</xsl:when>
			<xsl:when test="$codigoPais='526'">Yugoeslavia</xsl:when>
			<xsl:when test="$codigoPais='527'">Bulgaria</xsl:when>
			<xsl:when test="$codigoPais='528'">Polonia</xsl:when>
			<xsl:when test="$codigoPais='529'">Otros de Europa</xsl:when>
			<xsl:when test="$codigoPais='530'">Hungria</xsl:when>
			<xsl:when test="$codigoPais='532'">Luxemburgo</xsl:when>
			<xsl:when test="$codigoPais='534'">Liechtenstein</xsl:when>
			<xsl:when test="$codigoPais='535'">Monaco</xsl:when>
			<xsl:when test="$codigoPais='536'">San Marino</xsl:when>
			<xsl:when test="$codigoPais='540'">Armenia</xsl:when>
			<xsl:when test="$codigoPais='541'">Azerbaiyan</xsl:when>
			<xsl:when test="$codigoPais='542'">Belarus</xsl:when>
			<xsl:when test="$codigoPais='543'">Bosnia y Herzegovina</xsl:when>
			<xsl:when test="$codigoPais='544'">República Checa</xsl:when>
			<xsl:when test="$codigoPais='545'">República Eslovaca</xsl:when>
			<xsl:when test="$codigoPais='546'">República de Serbia</xsl:when>
			<xsl:when test="$codigoPais='547'">Croacia</xsl:when>
			<xsl:when test="$codigoPais='548'">Eslovenia</xsl:when>
			<xsl:when test="$codigoPais='549'">Estonia</xsl:when>
			<xsl:when test="$codigoPais='550'">Georgia</xsl:when>
			<xsl:when test="$codigoPais='551'">Kasajstán</xsl:when>
			<xsl:when test="$codigoPais='552'">Kirgistán</xsl:when>
			<xsl:when test="$codigoPais='553'">Letonia</xsl:when>
			<xsl:when test="$codigoPais='554'">Lituania</xsl:when>
			<xsl:when test="$codigoPais='555'">Macedonia</xsl:when>
			<xsl:when test="$codigoPais='556'">Moldova</xsl:when>
			<xsl:when test="$codigoPais='557'">Tadjikistán</xsl:when>
			<xsl:when test="$codigoPais='558'">Turkmenistán</xsl:when>
			<xsl:when test="$codigoPais='559'">Ucrania</xsl:when>
			<xsl:when test="$codigoPais='560'">Uzbekistan</xsl:when>
			<xsl:when test="$codigoPais='561'">Montenegro</xsl:when>
			<xsl:when test="$codigoPais='562'">Rusia</xsl:when>
			<xsl:when test="$codigoPais='563'">Alemania</xsl:when>
			<xsl:when test="$codigoPais='564'">RF Yugoeslavia</xsl:when>
			<xsl:when test="$codigoPais='565'">Gibraltar</xsl:when>
			<xsl:when test="$codigoPais='566'">Guernsey</xsl:when>
			<xsl:when test="$codigoPais='567'">Isla de Man</xsl:when>
			<xsl:when test="$codigoPais='568'">Jersey</xsl:when>
			<xsl:when test="$codigoPais='585'">Gibraltar</xsl:when>
			<xsl:when test="$codigoPais='888'">Resto del Mundo</xsl:when>
			<xsl:when test="$codigoPais='901'">Combustibles y lubricantes destinados al consumo de naves y aeronaves extranjeras y de revistas con el mismo objeto</xsl:when>
			<xsl:when test="$codigoPais='902'">Aprovisionamiento (Rancho) de naves y aeronaves extranjeras y de maderas para estibar mercancías cargadas en puertos chilenos</xsl:when>
			<xsl:when test="$codigoPais='903'">Pesca Extraterritorial</xsl:when>
			<xsl:when test="$codigoPais='904'">Origenes o Destinaciones no precisadas por razones comerciales o militares</xsl:when>
			<xsl:when test="$codigoPais='905'">Zona franca Iquique</xsl:when>
			<xsl:when test="$codigoPais='906'">Depósito franco</xsl:when>
			<xsl:when test="$codigoPais='907'">Zona franca Punta Arenas</xsl:when>
			<xsl:when test="$codigoPais='910'">Zona Franca Arica, Zona Industrial</xsl:when>
			<xsl:when test="$codigoPais='997'">Chile</xsl:when>
			<xsl:when test="$codigoPais='998'">Nacional reputada</xsl:when>
			<xsl:when test="$codigoPais='999'">Otros</xsl:when>
		</xsl:choose>
	</xsl:template>

</xsl:stylesheet>


