# Tarjetas CRC

## Spanish Braille Application

**Fase XP:** II. Diseño — Tarjetas CRC  
**Rol:** Programador — Diseño (Elian Caizapanta)  
**Basado en:** Historias de Usuario V2 (01H1–07H7) y código existente (ITERACION-2)

> **Nota:** Las 12 tarjetas CRC se derivan de las 7 Historias de Usuario V2 y se alinean con el código existente en la rama ITERACION-2. Cada tarjeta incluye el mapeo a la implementación real.

---

### Tarjeta 1 — ConversionController «Controller»

| | |
|---|---|
| **Clase** | **ConversionController** «Controller» |

| Responsabilidades | Colaboradores |
|---|---|
| Recibir las solicitudes HTTP del usuario (texto a convertir, conversión inversa, modo espejo) | BrailleConverterService |
| Validar que el campo de texto de entrada no esté vacío | InverseBrailleService |
| Invocar al servicio correspondiente según la acción solicitada | SignaleticsService |
| Retornar la respuesta (resultado Braille o texto español) a la vista | ConversionView (Thymeleaf / HTML) |
| Manejar y registrar errores de conversión para informar al usuario | |

| **Superclase** | Ninguna (clase raíz de control) |
|---|---|
| **HU V2** | 01H1, 06H6, 07H7 |
| **Impl. Real** | `TranscriptionController.java` |

---

### Tarjeta 2 — BrailleConverterService «Service»

| | |
|---|---|
| **Clase** | **BrailleConverterService** «Service» |

| Responsabilidades | Colaboradores |
|---|---|
| Orquestar la conversión completa de un texto en español a Braille | AlphabetMapService |
| Iterar carácter a carácter y delegar a los sub-servicios especializados | AccentMapService |
| Ensamblar la cadena de cuadratines Braille resultante | NumberMapService |
| Manejar espacios en blanco entre palabras y saltos de línea | UpperCaseHandlerService |
| Retornar la cadena Braille final al controlador | PunctuationMapService |

| **Superclase** | Ninguna |
|---|---|
| **HU V2** | 01H1, 02H2, 03H3, 04H4, 05H5 |
| **Impl. Real** | `BrailleMapper.java` (método `españolABraille`) |

---

### Tarjeta 3 — AlphabetMapService «Service»

| | |
|---|---|
| **Clase** | **AlphabetMapService** «Service» |

| Responsabilidades | Colaboradores |
|---|---|
| Mantener el mapa de caracteres: letras a–z → patrones Braille (series 1, 2 y 3) | BrailleCharacterMap |
| Proveer el cuadratín Braille para una letra minúscula dada | |
| Retornar advertencia si la letra no existe en el mapa | |

| **Superclase** | Ninguna |
|---|---|
| **HU V2** | 01H1 |
| **Impl. Real** | `BrailleDictionary.java` (método `initLetters`) |

---

### Tarjeta 4 — AccentMapService «Service»

| | |
|---|---|
| **Clase** | **AccentMapService** «Service» |

| Responsabilidades | Colaboradores |
|---|---|
| Mantener el mapa de caracteres especiales: á,é,í,ó,ú,ñ,ü → cuadratines Braille | BrailleCharacterMap |
| Proveer el cuadratín Braille para un carácter acentuado dado | |
| Retornar advertencia si el carácter no tiene mapeo definido | |

| **Superclase** | Ninguna |
|---|---|
| **HU V2** | 02H2 |
| **Impl. Real** | `BrailleDictionary.java` (método `initAccents`) |

---

### Tarjeta 5 — NumberMapService «Service»

| | |
|---|---|
| **Clase** | **NumberMapService** «Service» |

| Responsabilidades | Colaboradores |
|---|---|
| Detectar secuencias de dígitos en el texto de entrada | BrailleCharacterMap |
| Anteponer el signo de número Braille (puntos 3456) una sola vez por secuencia | |
| Convertir cada dígito (0–9) usando la primera serie Braille | |
| Manejar puntos y comas decimales dentro de secuencias numéricas | |
| Cerrar la secuencia numérica al encontrar un carácter no numérico | |

| **Superclase** | Ninguna |
|---|---|
| **HU V2** | 03H3 |
| **Impl. Real** | `BrailleDictionary.java` (método `initNumbers`) + `BrailleMapper.java` (lógica numérica) |

---

### Tarjeta 6 — UpperCaseHandlerService «Service»

| | |
|---|---|
| **Clase** | **UpperCaseHandlerService** «Service» |

| Responsabilidades | Colaboradores |
|---|---|
| Detectar si un carácter del texto de entrada es mayúscula | AlphabetMapService |
| Insertar el cuadratín indicador de mayúscula Braille (puntos 46) antes de la letra | BrailleCharacterMap |
| Delegar la conversión de la letra (en minúscula) a AlphabetMapService | |

| **Superclase** | Ninguna |
|---|---|
| **HU V2** | 04H4 |
| **Impl. Real** | `BrailleMapper.java` (constante `SIGNO_MAYUSCULA = mask(4,6)`) |

---

### Tarjeta 7 — PunctuationMapService «Service»

| | |
|---|---|
| **Clase** | **PunctuationMapService** «Service» |

| Responsabilidades | Colaboradores |
|---|---|
| Mantener el mapa de signos de puntuación básicos → cuadratines Braille | BrailleCharacterMap |
| Proveer el cuadratín Braille para un signo de puntuación dado (.,;¿?¡!) | |
| Omitir y advertir sobre signos no soportados | |

| **Superclase** | Ninguna |
|---|---|
| **HU V2** | 05H5 |
| **Impl. Real** | `BrailleDictionary.java` (método `initPunctuation`) |

---

### Tarjeta 8 — BrailleRendererService «Service»

| | |
|---|---|
| **Clase** | **BrailleRendererService** «Service» |

| Responsabilidades | Colaboradores |
|---|---|
| Convertir la cadena de cuadratines Braille a caracteres Unicode (U+2800–U+28FF) | ConversionController |
| Generar SVG de respaldo si el navegador no soporta la fuente Braille | ConversionView |
| Calcular la máscara de bits de cada cuadratín para seleccionar el carácter Unicode correcto | |
| Proveer el HTML/SVG final listo para inyectar en la vista | |

| **Superclase** | Ninguna |
|---|---|
| **HU V2** | 01H1 (renderizado del resultado) |
| **Impl. Real** | `BrailleMapper.java` (método `maskToUnicode`) |

---

### Tarjeta 9 — SignaleticsService «Service»

| | |
|---|---|
| **Clase** | **SignaleticsService** «Service» |

| Responsabilidades | Colaboradores |
|---|---|
| Recibir el texto de la señal y coordinar su conversión a Braille | BrailleConverterService |
| Aplicar la plantilla de señalética (texto en tinta + Braille) | BrailleRendererService |
| Generar la vista previa de la señalética en la interfaz | ConversionController |
| Exportar la señalética como imagen PNG (300 dpi) o PDF | |
| Validar el largo máximo del texto de la señal | |

| **Superclase** | Ninguna |
|---|---|
| **HU V2** | 06H6 |
| **Impl. Real** | *Pendiente de implementación — Sprint 2* |

---

### Tarjeta 10 — InverseBrailleService «Service»

| | |
|---|---|
| **Clase** | **InverseBrailleService** «Service» |

| Responsabilidades | Colaboradores |
|---|---|
| Recibir un patrón de puntos Braille e identificar el carácter español correspondiente | BrailleCharacterMap (mapa inverso) |
| Construir la máscara de bits a partir del texto Braille Unicode | ConversionController |
| Buscar la máscara en el mapa inverso Braille → español | |
| Manejar los indicadores de número y mayúscula en el flujo inverso | |
| Retornar el carácter español o el mensaje "Patrón no reconocido" | |

| **Superclase** | Ninguna |
|---|---|
| **HU V2** | 07H7 |
| **Impl. Real** | `BrailleMapper.java` (método `brailleAEspañol`) + `EspañolMapper.java` |

---

### Tarjeta 11 — BrailleCharacterMap «Data»

| | |
|---|---|
| **Clase** | **BrailleCharacterMap** «Data» |

| Responsabilidades | Colaboradores |
|---|---|
| Almacenar y proveer el mapa completo de caracteres español → Braille (normal y espejo) | AlphabetMapService |
| Almacenar y proveer el mapa inverso Braille → español | AccentMapService |
| Calcular la máscara de bits: mask(dots) = OR(1 << (d-1)) | NumberMapService |
| Inicializar los mapas al inicio de la aplicación | PunctuationMapService |
| Garantizar inmutabilidad de los mapas (Collections.unmodifiableMap) | InverseBrailleService |

| **Superclase** | Ninguna |
|---|---|
| **HU V2** | 01H1, 02H2, 03H3, 04H4, 05H5, 07H7 |
| **Impl. Real** | `BrailleDictionary.java` (mapas `map`, `pam`, `reverseMap`) |

---

### Tarjeta 12 — ConversionView «View (UI)»

| | |
|---|---|
| **Clase** | **ConversionView** «View (UI)» |

| Responsabilidades | Colaboradores |
|---|---|
| Mostrar el campo de entrada de texto en español | ConversionController |
| Mostrar el área de salida con la representación Braille (Unicode/SVG) | BrailleRendererService |
| Proveer interfaz para la conversión inversa Braille → español | |
| Mostrar la vista previa de señalética y el botón de exportación | |
| Ejecutar el reset de campos al hacer clic en "Limpiar" (sin recargar página) | |
| Mostrar mensajes de advertencia y error al usuario | |

| **Superclase** | Ninguna (plantilla Thymeleaf / HTML+JS) |
|---|---|
| **HU V2** | 01H1, 06H6, 07H7 |
| **Impl. Real** | `index.html`, `result-español.html`, `result-braille.html`, `result-espejo.html` |

---

### Matriz de Trazabilidad HU V2 ↔ Tarjetas CRC ↔ Código

| HU V2 | Descripción | Sprint | Tarjetas CRC | Código (ITERACION-2) |
|--------|-------------|--------|-------------|---------------------|
| 01H1 | Transcribir abecedario (a–z) | 1 | #01, #02, #03, #08, #11, #12 | `BrailleDictionary.initLetters()`, `BrailleMapper.españolABraille()` |
| 02H2 | Vocales acentuadas y ñ | 1 | #02, #04, #11 | `BrailleDictionary.initAccents()` |
| 03H3 | Números con signo numérico | 1 | #02, #05, #11 | `BrailleDictionary.initNumbers()`, `BrailleMapper` (lógica numérica) |
| 04H4 | Mayúsculas con indicador | 1 | #02, #06, #11 | `BrailleMapper.SIGNO_MAYUSCULA = mask(4,6)` |
| 05H5 | Signos de puntuación | 2 | #02, #07, #11 | `BrailleDictionary.initPunctuation()` |
| 06H6 | Señalética imprimible | 2 | #01, #09, #12 | *Pendiente — Sprint 2* |
| 07H7 | Braille → español (inverso) | 2 | #01, #10, #11, #12 | `BrailleMapper.brailleAEspañol()`, `EspañolMapper.java` |
