# Proyecto Agile — Spanish Braille Application

## Descripción del Proyecto

Aplicación web desarrollada con **Spring Boot** que permite la traducción bidireccional entre **español** y **Braille Unicode**, incluyendo soporte para acentos, mayúsculas, ñ, números y signos de puntuación. También cuenta con la función de **Braille espejo** para facilitar la impresión en relieve y escritura manual.

- **Repositorio del código fuente:** [Spanish-Braille-Application (ITERACION-2)](https://github.com/MRGonorrea79/Spanish-Braille-Application/tree/ITERACION-2)
- **Metodología:** XP (Extreme Programming)
- **Herramienta de gestión:** GitHub
- **Materia:** Construcción y Evolución de Software — Primer Bimestre

---

## Fases XP del Proyecto

| Fase | Artefactos | Responsable |
|------|-----------|-------------|
| **I. Planificación** | Historias de Usuario V2, Release Plan, Sprint Plan, Velocidad del Proyecto | Planning |
| **II. Diseño** | Tarjetas CRC, Diagrama de Casos de Uso, Diagrama de Clases, Roadmap Braille | Diseño (Elian) |
| **III. Desarrollo** | Código fuente (ITERACION-2), Programación por parejas, Integración | Codificación |
| **IV. Pruebas** | Pruebas unitarias, Pruebas de aceptación | Testing |

---

## Equipo y Roles XP

| Rol XP | Integrante | Responsabilidad |
|--------|-----------|-----------------|
| **Cliente / Planning** | *(Pendiente de completar)* | Definir historias de usuario, priorizar el backlog y participar en las pruebas de aceptación |
| **Programador — Diseño** | **Elian Caizapanta** | Crear los artefactos de diseño: tarjetas CRC, diagramas de casos de uso, diagramas de clases y roadmap |
| **Programador — Codificación** | *(Pendiente de completar)* | Implementar y modificar el código fuente según las historias de usuario |
| **Programador — Codificación** | *(Pendiente de completar)* | Implementar y modificar el código fuente según las historias de usuario |
| **Tester** | *(Pendiente de completar)* | Diseñar y ejecutar pruebas unitarias y de aceptación |
| **Manager / Tracker** | *(Pendiente de completar)* | Supervisar el progreso del proyecto, coordinar al equipo y presentar resultados |

---

## Aportes por Integrante

### Elian Caizapanta — Programador (Diseño)

**Rol XP:** Programador encargado del diseño de la arquitectura y modelado del sistema (Fase II — Diseño XP).

**Artefactos entregados:**

#### 1. Tarjetas CRC
- **Archivo:** [`docs/design/tarjetas-crc.md`](docs/design/tarjetas-crc.md)
- 12 Tarjetas CRC (Clase · Responsabilidades · Colaboradores) derivadas de los Casos de Uso CU-01 a CU-10
- Clases identificadas: ConversionController, BrailleConverterService, AlphabetMapService, AccentMapService, NumberMapService, UpperCaseHandlerService, PunctuationMapService, BrailleRendererService, SignaleticsService, InverseBrailleService, BrailleCharacterMap, ConversionView
- Mapeo de cada tarjeta CRC a su implementación real en el código existente (ITERACION-2)

#### 2. Diagrama de Casos de Uso
- **Archivo:** [`docs/design/diagrama-casos-de-uso.md`](docs/design/diagrama-casos-de-uso.md)
- 8 Casos de Uso (CU-01 a CU-08) derivados de las 7 Historias de Usuario V2
- Diagrama general en Mermaid con relaciones `<<incluye>>` y `<<extiende>>`
- Descripción detallada de cada CU: actor, precondiciones, flujo principal, flujos alternativos, postcondiciones y excepciones
- Matriz de trazabilidad HU V2 ↔ Casos de Uso por sprint

#### 3. Diagrama de Clases
- **Archivo:** [`docs/design/diagrama-de-clases.md`](docs/design/diagrama-de-clases.md)
- 12 clases derivadas de las Tarjetas CRC con atributos y métodos
- Diagrama de clases en Mermaid con relaciones de dependencia y delegación
- 2 Diagramas de Secuencia: flujo Español→Braille y flujo Braille→Español (inverso)
- Tabla de mapeo Diseño CRC → Implementación real en el código
- Patrones de diseño identificados: MVC, Strategy, Facade, Singleton/Data, Template Method

#### 4. Roadmap del Alfabeto Braille Español
- **Archivo:** [`docs/design/roadmap-alfabeto-braille.md`](docs/design/roadmap-alfabeto-braille.md)
- Estructura de la celda Braille (6 puntos) y fórmula Unicode
- Tablas completas: letras (1ra, 2da, 3ra serie), caracteres especiales (ñ, ü), vocales acentuadas
- Números (0–9) con signo numérico, signos de puntuación y signos de control
- Explicación del Braille espejo (reflexión horizontal de puntos)
- Diagramas Gantt por Sprint 1 y Sprint 2
- Velocidad del proyecto: 27 pts totales, 3.4 pts/sem promedio
- Ejemplo de transcripción paso a paso

---

### *(Nombre del integrante)* — Cliente / Planning

**Rol XP:** Cliente que define las historias de usuario y prioriza el backlog.

**Artefactos entregados:**

> *Pendiente de completar. Agregar aquí:*
> - Historias de Usuario V2 (7 HU: 01H1–07H7)
> - Release Planning y Sprint Planning
> - Velocidad del Proyecto
> - Evidencia de reuniones de planificación

---

### *(Nombre del integrante)* — Programador (Codificación)

**Rol XP:** Programador encargado de la codificación del sistema.

**Artefactos entregados:**

> *Pendiente de completar. Agregar aquí:*
> - Código implementado (clases, métodos, funcionalidades)
> - Commits realizados con descripción de cambios
> - Evidencia de pair programming (si aplica)

---

### *(Nombre del integrante)* — Programador (Codificación)

**Rol XP:** Programador encargado de la codificación del sistema.

**Artefactos entregados:**

> *Pendiente de completar. Agregar aquí:*
> - Código implementado (clases, métodos, funcionalidades)
> - Commits realizados con descripción de cambios
> - Evidencia de pair programming (si aplica)

---

### *(Nombre del integrante)* — Tester

**Rol XP:** Encargado de pruebas unitarias y de aceptación.

**Artefactos entregados:**

> *Pendiente de completar. Agregar aquí:*
> - Plan de pruebas
> - Pruebas unitarias implementadas
> - Resultados de ejecución de pruebas
> - Pruebas de aceptación con el cliente

---

### *(Nombre del integrante)* — Manager / Tracker

**Rol XP:** Gestor y tracker del proyecto.

**Artefactos entregados:**

> *Pendiente de completar. Agregar aquí:*
> - Seguimiento de velocidad por sprint
> - Burndown chart o métricas de avance
> - Actas de reuniones (stand-ups, retrospectivas)
> - Presentación final del proyecto

---

## Estructura del Repositorio

```
Proyecto-Agile/
├── README.md                              ← Este archivo
├── docs/
│   └── design/                            ← Artefactos de Diseño (Elian)
│       ├── tarjetas-crc.md                ← 12 Tarjetas CRC
│       ├── diagrama-casos-de-uso.md       ← 8 CU con flujos detallados
│       ├── diagrama-de-clases.md          ← 12 clases + secuencia
│       └── roadmap-alfabeto-braille.md    ← Alfabeto Braille + Gantt
```

---

## Historias de Usuario (V2)

| ID | Historia de Usuario | Estimación | Importancia | Sprint |
|----|---------------------|-----------|-------------|--------|
| 01H1 | Transcribir abecedario (a–z) a Braille (series 1, 2 y 3) | 5 pts | Alta | 1 |
| 02H2 | Convertir vocales acentuadas (á,é,í,ó,ú) y ñ a Braille | 3 pts | Media | 1 |
| 03H3 | Transcribir números (0–9) con signo de número Braille (3456) | 2 pts | Alta | 1 |
| 04H4 | Manejar letras mayúsculas con indicador Braille (puntos 46) | 2 pts | Media | 1 |
| 05H5 | Convertir signos de puntuación básicos (.,;¿?¡!) a Braille | 3 pts | Media | 2 |
| 06H6 | Generar señalética Braille imprimible (exportable PNG/PDF) | 7 pts | Alta | 2 |
| 07H7 | Transcribir patrón Braille a español (bidireccional) | 5 pts | Alta | 2 |

---

## Planificación de Sprints

### Sprint 1 — Transcripción Base (12 pts · 4 semanas)
- **HUs:** 01H1 (5 pts), 02H2 (3 pts), 03H3 (2 pts), 04H4 (2 pts)
- **Objetivo:** Implementar la conversión completa de texto español a Braille incluyendo abecedario, vocales acentuadas, números y mayúsculas
- **Velocidad:** 3.0 pts/sem · 24 h estimadas · 96 h disponibles

### Sprint 2 — Funcionalidades Avanzadas (15 pts · 4 semanas)
- **HUs:** 05H5 (3 pts), 06H6 (7 pts), 07H7 (5 pts)
- **Objetivo:** Agregar puntuación, generación de señalética imprimible y transcripción inversa Braille→español
- **Velocidad:** 3.8 pts/sem · 28 h estimadas · 96 h disponibles

### Velocidad Total del Release
- **Story Points totales:** 27 pts
- **Velocidad promedio:** 3.4 pts/sem
- **Calibración:** 1.9 h/pt

---

## Tecnologías Utilizadas

| Tecnología | Uso |
|-----------|-----|
| Java 17/21 | Lenguaje de programación |
| Spring Boot | Framework web |
| Thymeleaf | Motor de plantillas HTML |
| Maven | Gestión de dependencias y build |
| GitHub | Control de versiones y gestión del proyecto |
| Mermaid | Diagramas renderizados en Markdown |
