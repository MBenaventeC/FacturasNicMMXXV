# importamos la Clase Factura
from sii_dte.mini_recibo import Factura

# Modulo para la fecha actual
from datetime import date

# Módulos para convertir de clase a XML
from xsdata.formats.dataclass.serializers import XmlSerializer
from xsdata.formats.dataclass.serializers.config import SerializerConfig


def crearXML(emisor, receptor, fecha, total):
    """
    Función que recibe los datos de la factura a crear y entrega un XML con aquellos datos
    """

    # Objeto de clase Factura que representa una factura
    factura = Factura(emisor, receptor, fecha, total)

    # Creamos el objeto que permite convertir de clases a XML
    serializer = XmlSerializer(
        config=SerializerConfig(
            xml_declaration=True,
            pretty_print=True,
            encoding="iso-8859-1"  # SII usa ISO-8859-1
        )
    )

    # Convertir factura a XML
    xml_text = serializer.render(factura)    # str en ISO-8859-1

    # Guardar XML
    with open("dte.xml", "w", encoding="iso-8859-1", newline="\n") as f:
        f.write(xml_text)


# LLamar la funcion para probar
fecha_ahora = date.today().strftime("%Y-%m-%d")
crearXML("Juan", "Pedro", fecha_ahora, 5000)