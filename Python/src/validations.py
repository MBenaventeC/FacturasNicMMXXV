from itertools import cycle

"""
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

def validar_rut(rut: str) -> bool:
    """
    -RUT Empresa (< RE >): 
        formato XXXXXXXX-X
    """
    rut = rut.upper().replace("-", "").replace(".", "")
    rut_aux = rut[:-1]
    dv = rut[-1:]

    if not rut_aux.isdigit() or not (1_000_000 <= int(rut_aux) <= 25_000_000):
        return False

    revertido = map(int, reversed(rut_aux))
    factors = cycle(range(2, 8))
    suma = sum(d * f for d, f in zip(revertido, factors))
    residuo = suma % 11

    if dv == 'K':
        return residuo == 1
    if dv == '0':
        return residuo == 11
    return residuo == 11 - int(dv)

def validar_rut2(rut: str) -> bool:
    """
    -RUT Empresa (< RE >): 
        formato XXXXXXXX-X
    """
    rut = rut.split("-")

    if len(rut) != 2 or "." in rut[0]:
        return False

    if len(rut[0]) != 8 or len(rut[1]) != 2:
        return False
    
    return True

def validate_DTE(TD: int) -> bool:
    """
    -Tipo DTE (< TD >): 
        caracteres ASCII, Este valor está conforme a la definición de tipos de DTE impuesta por SII. 
        (Ej: Factura corresponde a ASCII "33").
    -Tipos:
            Factura Electrónica (código de DTE 33)
            Factura No Afecta o Exenta Electrónica (código de DTE 34)
            Liquidación-Factura Electrónica (código de DTE 43)
            Factura de Compra Electrónica (código de DTE 46)
            Guía de Despacho Electrónica (código de DTE 52)
            Nota de Débito Electrónica (código de DTE 56)
            Nota de Crédito Electrónica (código de DTE 61)
            Factura de Exportación (código de DTE 110)
            Nota de Débito de Exportación (código de DTE 111)
            Nota de Crédito de Exportación (código de DTE 112)
    """
    if (
        isinstance(TD, int) and
        TD in [33, 34, 43, 46, 52, 56, 61, 110, 111, 112]
        ):
        return True
    return False

def validate_RNG(RNG) -> bool:
    """
    -Rango de Folios (< RNG >): 
        par de valores indicando el rango de folios 
        autorizados en estructura “Desde-Hasta”.
    """
    if (
        isinstance(RNG, tuple) and
        len(RNG) == 2 and
        all(isinstance(x, int) for x in RNG) and
        RNG[0] < RNG[1]
    ):
        return True
    return False

def validate_RSAPK(rsapk):
    return 