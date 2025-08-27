from dataclasses import dataclass, field
from decimal import Decimal
from typing import Optional

from xsdata.models.datatype import XmlDate


@dataclass
class Factura:
    emisor: Optional[str] = field(
        default=None,
        metadata={
            "name": "Emisor",
            "type": "Element",
            "required": True,
        },
    )
    receptor: Optional[str] = field(
        default=None,
        metadata={
            "name": "Receptor",
            "type": "Element",
            "required": True,
        },
    )
    fecha: Optional[XmlDate] = field(
        default=None,
        metadata={
            "name": "Fecha",
            "type": "Element",
            "required": True,
        },
    )
    total: Optional[Decimal] = field(
        default=None,
        metadata={
            "name": "Total",
            "type": "Element",
            "required": True,
        },
    )
