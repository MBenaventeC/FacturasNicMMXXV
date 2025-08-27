from cryptography.hazmat.primitives import hashes, serialization
from cryptography.hazmat.primitives.asymmetric import padding
import base64

def build_dd(datos, caf):
    """
    El objetivo de esta función es armar el bloque <DD> del timbre electrónico de un DTE, siguiendo las 
    indicaciones presentes en el documento "Instructivo de Emisión" del SII, anexo 2 (página 16).

    Params:
        datos (dict): Los datos (etiquetas) que requiere este bloque. Asumimos por el momento que
        esta función los recibirá en formato de diccionario.

        caf: Código de Autorización de folios. Se indica que se debe incluir en este bloque de datos
        (DD) tal cual se recibe.

    return:
        Un string con los datos y el formato del <DD> que se indica en el anexo 2. 
    """

    # Datos que debe contener el <DD> y deberían venir incluidos en el diccionario que se recibe.
    requeridos = ["RE", "TD", "F", "FE", "RR", "RSR", "MNT", "IT1", "TSTED"]

    # Esta lista contiene los datos rqueridos faltantes en el diccionario 
    faltan = [k for k in requeridos if k not in datos]

    if faltan:
        raise ValueError(f"Faltan los datos {faltan} requeridos")
    
    # Bloque <DD>. Aparentemente habría que quitar los \t y \n.
    dd = (
        "<DD>\n"
        f"\t<RE>{datos['RE']}</RE>\n"
        f"\t<TD>{datos['TD']}</TD>\n"
        f"\t<F>{datos['F']}</F>\n"
        f"\t<FE>{datos['FE']}</FE>\n"
        f"\t<RR>{datos['RR']}</RR>\n"
        f"\t<RSR>{datos['RSR']}</RSR>\n"
        f"\t<MNT>{datos['MNT']}</MNT>\n"
        f"\t<IT1>{datos['IT1']}</IT1>\n"
        f"\t<CAF>{caf}</CAF>\n"  # CAF tal cual, sin modificar
        f"\t<TSTED>{datos['TSTED']}</TSTED>\n"
        "</DD>"
    )
    return dd


def sign_dd(dd_text, private_key):
    """El objetivo de esta función es generar la firma digital sobre Datos <DD>, siguiendo las indicaciones
    del "Instructivo de Emisión", anexo (página 18). Se indica que el algoritmo a utilizar debe ser 
    SHA1+RSA.

    Params:
        dd_text (str): El bloque de datos <DD>.
        private_key (PEM): La llave privada generada por el SII para un determinado rango de folios que nos asignan.

    return:
        Devuelve el valor de la firma aplicada a Datos <DD> en base64 (ocupado para crear el bloque FRMT)

    """
    # Cargamos la llave (si tiene contraseña se le colocaría en el parametro password)
    key = serialization.load_pem_private_key(private_key, password=None)

    firma = key.sign(
        dd_text.encode("latin-1"), # pasamos el dd a ISO-8859-1 (latin-1), según como se indica en la pag 19.
        padding.PKCS1v15(),
        hashes.SHA1(),
    )

    return base64.b64encode(firma).decode("ascii")


def build_ted(dd_text: str, frmt_sign: str) -> str:
    """
    Esta función conforma el <TED> final tomando el bloque <DD> creado y la firma creada como parametros.

    Params:
        dd_text (str): El bloque de datos <DD>.
        frmt_sign: La firma obtenida con la función "sign_dd"

    return:
        La estructura final del timbre electronico del DTE presentada en la página 15 del
        documento "Instructivo de Emisión" del SII, anexo 2.
    """
    # Aquí hay que corroborar que sea así, o si falta agregar tabs para que quede con la estructura 
    # presentada en el ejemplo del documento.
    return f'<TED version="1.0">{dd_text}<FRMT algoritmo="SHA1withRSA">{frmt_sign}</FRMT></TED>'
