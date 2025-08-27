from cryptography.hazmat.primitives import hashes, serialization
from cryptography.hazmat.primitives.asymmetric import padding
import base64
from validations import *

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
    
    # Bloque <DD>
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

def build_caf(RE: str, TD: int, RNG: tuple[int], FA: str, RSAPK: tuple[str] , IDK: int):
    """
    -RUT Empresa (< RE >): 
        formato XXXXXXXX-X

    -Razón Social de la Empresa (< RS >): 
        máximo de 40 caracteres.

    -Tipo DTE (< TD >): 
        caracteres ASCII, Este valor está conforme a la definición de tipos de DTE impuesta por SII. 
        (Ej: Factura corresponde a ASCII "33").

    -Rango de Folios (< RNG >): par de valores indicando el rango de folios autorizados en estructura “Desde-Hasta”.

    -Fecha (< FA >): 
        cadena de caracteres ASCII indica fecha en que fue autorizado 
        el rango de folios en formato AAAA-MM-DD, ej: “2004-02-29”.

    -Llave Pública del contribuyente (< RSAPK >): 
        cadena de caracteres ASCII con el valor de la llave pública, 
        generada por el SII. (el SII entregará sólo llaves correspondientes al algoritmo 
        criptográfico de llave pública RSA.) 

        Una llave pública RSA tiene dos valores numéricos que la definen, un módulo y un exponente. 

        -Módulo < M >: 
            Indica el valor del módulo de la llave. Este valor es la codificación en Base64 
            del arreglo de bytes en orden Big-Endian (el byte más significativo es el elemento 0 del arreglo) 
            que contiene el valor entero sin signo (unsigned integer) del módulo.

        -Exponente < E >: 
            Indica el valor del exponente de la llave. Este valor es la codificación en Base64 del arreglo 
            de bytes en orden Big-Endian (el byte más significativo es el elemento 0 del arreglo) que contiene el valor 
            entero sin signo (unsigned integer) del exponente.
    
    -Identificación llave pública del SII (< IDK >): 
        Se trata de un identificador de la llave y no de la llave.
    """

    if not validar_rut(RE):
        return False
    
    
