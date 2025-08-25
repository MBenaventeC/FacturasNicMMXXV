# FacturasNicMMXXV
<!-- README del proyecto, escribir instrucciones conforme se avance con el código -->

Este proyecto busca actualizar un software que se usaba para la generación de archivos XML y traerlo tanto a Python como a versiones modernas de Java (versión [pendiente]).

Primero antes que nada es necesario clonar el repositorio:

```
git clone https://github.com/MBenaventeC/FacturasNicMMXXV
```

 ## Python

 1. Crear un entorno virtual al interior del repositorio, a la misma altura de la carpeta _Python_, y activarlo:

    ```
    python -m venv venv

    # En Linux/Mac:
    source venv/bin/activate

    # En Windows:
    venv\Scripts\activate
    ```
    Debería quedar así:
    ```
    ├── Java
        └── ...
    ├── Python
        └── ...
    ├── venv [debe quedar a esta altura]
        └── ...
    └── README.md
    ...
    ```

2. Abrir un terminal, posicionarse en el directorio del repositorio clonado y  descargar las librerías presentes en `requirements.txt`:
    ```
    pip install -r Python/requirements.txt

    # Verificar que se instalaron bien
    pip list
    ```
3. ?