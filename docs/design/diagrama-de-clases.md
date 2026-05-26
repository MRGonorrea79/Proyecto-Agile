# Diagrama de Clases

## Spanish Braille Application

**Fase XP:** II. Diseño  
**Rol:** Programador — Diseño (Elian Caizapanta)  
**Basado en:** Tarjetas CRC, Historias de Usuario V2 (01H1–07H7) y código existente (ITERACION-2)

---

### Diagrama de Clases General

```mermaid
classDiagram
    direction TB

    class ConversionController {
        <<Controller>>
        -brailleConverterService : BrailleConverterService
        -inverseBrailleService : InverseBrailleService
        +index() String
        +convertirEspañolABraille(texto : String, model : Model) String
        +convertirBrailleAEspañol(texto : String, model : Model) String
        +convertirEspejo(texto : String, model : Model) String
    }

    class BrailleConverterService {
        <<Service>>
        -alphabetMapService : AlphabetMapService
        -accentMapService : AccentMapService
        -numberMapService : NumberMapService
        -upperCaseHandlerService : UpperCaseHandlerService
        -punctuationMapService : PunctuationMapService
        +convertirTextoABraille(texto : String) String
        -procesarPalabra(palabra : String) String
        -manejarEspacios(palabras : String[]) String
    }

    class AlphabetMapService {
        <<Service>>
        -mapaAlfabeto : Map~String, Integer~
        +obtenerPatronBraille(letra : String) Integer
        +existeLetra(letra : String) boolean
        -inicializarSerie1() void
        -inicializarSerie2() void
        -inicializarSerie3() void
    }

    class AccentMapService {
        <<Service>>
        -mapaAcentos : Map~String, Integer~
        +obtenerPatronAcento(caracter : String) Integer
        +esCaracterAcentuado(caracter : String) boolean
    }

    class NumberMapService {
        <<Service>>
        -mapaNumeros : Map~String, Integer~
        -SIGNO_NUMERO : int
        +obtenerPatronNumero(digito : String) Integer
        +esDigito(caracter : char) boolean
        +obtenerSignoNumero() Integer
    }

    class UpperCaseHandlerService {
        <<Service>>
        -SIGNO_MAYUSCULA : int
        +esMayuscula(caracter : char) boolean
        +obtenerIndicadorMayuscula() Integer
        +convertirAMinuscula(caracter : String) String
    }

    class PunctuationMapService {
        <<Service>>
        -mapaPuntuacion : Map~String, Integer~
        +obtenerPatronPuntuacion(signo : String) Integer
        +esSignoPuntuacion(caracter : String) boolean
    }

    class BrailleRendererService {
        <<Service>>
        +convertirAUnicode(mascara : int) String
        +generarSVGCuadratin(mascara : int) String
        +renderizarCadena(cadena : String) String
    }

    class SignaleticsService {
        <<Service>>
        -brailleConverterService : BrailleConverterService
        +generarSenaletika(texto : String) Senaletika
        +exportarPNG(senaletika : Senaletika) byte[]
        +exportarPDF(senaletika : Senaletika) byte[]
    }

    class InverseBrailleService {
        <<Service>>
        -mapaInverso : Map~Integer, String~
        -SIGNO_NUMERO : int
        -SIGNO_MAYUSCULA : int
        +brailleAEspañol(textoBraille : String) String
        -procesarCaracterBraille(mascara : int) String
        -brailleCharToMask(c : char) int
    }

    class BrailleCharacterMap {
        <<Data>>
        -mapaNormal : Map~String, Integer~
        -mapaEspejo : Map~String, Integer~
        -mapaInverso : Map~Integer, String~
        +getMapaNormal() Map
        +getMapaEspejo() Map
        +getMapaInverso() Map
        -mask(dots : int[]) int
        -addDot(base : int, dot : int) int
    }

    class ConversionView {
        <<View - Thymeleaf/HTML>>
        +campoEntradaTexto : TextArea
        +areaSalidaBraille : Div
        +botonConvertir : Button
        +botonLimpiar : Button
        +vistaSeñaletica : Section
    }

    %% Relaciones
    ConversionController --> BrailleConverterService : usa
    ConversionController --> InverseBrailleService : usa
    ConversionController --> SignaleticsService : usa
    ConversionController --> ConversionView : retorna vista

    BrailleConverterService --> AlphabetMapService : delega letras
    BrailleConverterService --> AccentMapService : delega acentos
    BrailleConverterService --> NumberMapService : delega números
    BrailleConverterService --> UpperCaseHandlerService : delega mayúsculas
    BrailleConverterService --> PunctuationMapService : delega puntuación

    AlphabetMapService --> BrailleCharacterMap : consulta
    AccentMapService --> BrailleCharacterMap : consulta
    NumberMapService --> BrailleCharacterMap : consulta
    PunctuationMapService --> BrailleCharacterMap : consulta
    UpperCaseHandlerService --> AlphabetMapService : delega letra

    BrailleRendererService --> ConversionController : provee renderizado
    BrailleRendererService --> ConversionView : inyecta HTML/SVG

    SignaleticsService --> BrailleConverterService : usa conversión

    InverseBrailleService --> BrailleCharacterMap : consulta mapa inverso
```

---

### Mapeo Diseño (CRC) → Implementación Real → HU V2

| Clase CRC (Diseño) | Clase Real (Código ITERACION-2) | HU V2 |
|---------------------|--------------------------------|-------|
| ConversionController | `TranscriptionController.java` | 01H1, 06H6, 07H7 |
| BrailleConverterService | `BrailleMapper.java` → `españolABraille()` | 01H1, 02H2, 03H3, 04H4, 05H5 |
| AlphabetMapService | `BrailleDictionary.java` → `initLetters()` | 01H1 |
| AccentMapService | `BrailleDictionary.java` → `initAccents()` | 02H2 |
| NumberMapService | `BrailleDictionary.java` → `initNumbers()` + `BrailleMapper` (lógica numérica) | 03H3 |
| UpperCaseHandlerService | `BrailleMapper.java` → `SIGNO_MAYUSCULA = mask(4,6)` | 04H4 |
| PunctuationMapService | `BrailleDictionary.java` → `initPunctuation()` | 05H5 |
| BrailleRendererService | `BrailleMapper.java` → `maskToUnicode()` | 01H1 (renderizado) |
| SignaleticsService | *Pendiente — Sprint 2* | 06H6 |
| InverseBrailleService | `BrailleMapper.java` → `brailleAEspañol()` + `EspañolMapper.java` | 07H7 |
| BrailleCharacterMap | `BrailleDictionary.java` → mapas `map`, `pam`, `reverseMap` | 01H1–05H5, 07H7 |
| ConversionView | `index.html`, `result-español.html`, `result-braille.html`, `result-espejo.html` | 01H1, 06H6, 07H7 |

> **Nota:** El diseño CRC propone 12 clases con responsabilidades separadas (SRP). La implementación actual consolida en 3 clases principales (`TranscriptionController`, `BrailleMapper`, `BrailleDictionary`) + `EspañolMapper` + plantillas HTML.

---

### Diagrama de Secuencia — Español → Braille (HU 01H1–05H5)

```mermaid
sequenceDiagram
    actor U as Usuario
    participant V as ConversionView
    participant C as ConversionController
    participant S as BrailleConverterService
    participant A as AlphabetMapService
    participant Ac as AccentMapService
    participant N as NumberMapService
    participant Up as UpperCaseHandlerService
    participant P as PunctuationMapService
    participant R as BrailleRendererService
    participant M as BrailleCharacterMap

    U->>V: Ingresa texto y clic "Convertir"
    V->>C: POST /transcribir-Español (texto)
    C->>S: convertirTextoABraille(texto)
    
    loop Por cada carácter
        alt Es mayúscula (HU 04H4)
            S->>Up: esMayuscula(char)
            Up-->>S: true + indicador mayúscula (puntos 46)
            Up->>A: obtenerPatronBraille(minúscula)
            A->>M: consultar mapa
            M-->>A: patrón de puntos
            A-->>Up: cuadratín
        else Es dígito (HU 03H3)
            S->>N: esDigito(char)
            N->>M: consultar mapa + signo número (3456)
            M-->>N: patrón
            N-->>S: cuadratín con signo
        else Es acento/ñ (HU 02H2)
            S->>Ac: esCaracterAcentuado(char)
            Ac->>M: consultar tabla adicional
            M-->>Ac: patrón
            Ac-->>S: cuadratín
        else Es puntuación (HU 05H5)
            S->>P: esSignoPuntuacion(char)
            P->>M: consultar sección signos
            M-->>P: patrón
            P-->>S: cuadratín
        else Es letra normal (HU 01H1)
            S->>A: obtenerPatronBraille(char)
            A->>M: consultar mapa series 1,2,3
            M-->>A: patrón
            A-->>S: cuadratín
        end
    end
    
    S-->>C: cadena Braille completa
    C->>R: renderizar para vista (maskToUnicode)
    R-->>C: HTML con Unicode Braille
    C-->>V: resultado Braille
    V-->>U: muestra cuadratines Braille
```

---

### Diagrama de Secuencia — Braille → Español (HU 07H7)

```mermaid
sequenceDiagram
    actor U as Usuario
    participant V as ConversionView
    participant C as ConversionController
    participant I as InverseBrailleService
    participant M as BrailleCharacterMap

    U->>V: Ingresa texto Braille y clic "Convertir"
    V->>C: POST /transcribir-Braille (texto)
    C->>I: brailleAEspañol(textoBraille)
    
    loop Por cada carácter Braille
        I->>I: brailleCharToMask(char)
        alt Es signo de número (3456)
            I->>I: activar modoNumero
        else Es signo de mayúscula (46)
            I->>I: activar siguienteMayuscula
        else Es doble mayúscula (46+46)
            I->>I: activar mayusculaPalabra
        else Es espacio (U+2800)
            I->>I: desactivar modos
        else Es carácter normal
            I->>M: buscar en mapaInverso(mascara)
            M-->>I: carácter español
            alt modoNumero activo
                I->>I: interpretar como dígito
            else siguienteMayuscula activo
                I->>I: convertir a mayúscula
            end
        end
    end
    
    I-->>C: texto español completo
    C-->>V: resultado
    V-->>U: muestra texto español
```

---

### Patrones de Diseño Identificados

| Patrón | Aplicación en el Diseño |
|--------|------------------------|
| **MVC** (Model-View-Controller) | `ConversionController` (Controller) + `ConversionView` (View) + Services (Model) |
| **Strategy** | Cada `*MapService` implementa una estrategia de conversión para un tipo de carácter |
| **Facade** | `BrailleConverterService` actúa como fachada, ocultando la complejidad de los sub-servicios |
| **Singleton / Data** | `BrailleCharacterMap` como repositorio central de mapeos, consultado por todos los servicios |
| **Template Method** | El flujo de conversión sigue: iterar → clasificar → delegar → ensamblar |
