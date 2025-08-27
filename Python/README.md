El código en el archivo sii_dte/mini_recibo.py se generó utilizando la librería *xsdata*, la cual genera las clases que se indican en un archivo .xsd en código python.

En este caso, se ejecutó el siguiente comando:

```
python -m xsdata generate schemas/MiniRecibo.xsd --output dataclasses --package sii_dte
```

generándose así la clase Factura en sii_dte/mini_recibo.py.
