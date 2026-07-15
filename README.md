# Proyecto Agile — Spanish Braille Application

## Descripción del Proyecto

Aplicación web desarrollada con **Spring Boot** que permite la traducción bidireccional entre **español** y **Braille Unicode**, incluyendo soporte para acentos, mayúsculas, ñ, números y signos de puntuación. También cuenta con la función de **Braille espejo** para facilitar la impresión en relieve y escritura manual.

- **Repositorio del código fuente:** [Spanish-Braille-Application (ITERACION-2)](https://github.com/MRGonorrea79/Spanish-Braille-Application/tree/ITERACION-2)
- **Metodología:** XP (Extreme Programming)
- **Herramienta de gestión:** GitHub
- **Materia:** Construcción y Evolución de Software — Primer Bimestre

---

## Roles Scrum (equipo)

| Rol Scrum | Integrante | Evidencia principal |
|-----------|-----------|----------------------|
| **Product Owner** | José Castro | Product Backlog, Release Plan, Sprint Review (aceptación del incremento) |
| **Scrum Master** | Victor Aveiga | Daily Scrum, Sprint Retrospective, tablero Kanban, Burndown y velocidad |
| **Equipo de Desarrollo** | Javier Angulo, Elian Caizapanta, Erick Costa, Emily Aumala | Historias de Usuario técnicas, Sprint Backlog, Definition of Done / Incremento |

> El equipo se autoorganiza: nadie externo asigna las tareas del Sprint Backlog, cada integrante las toma según disponibilidad y habilidad (ver `docs/artefactos-scrum/02-sprint-backlog.pdf`).

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

### Erick Costa — Programador (Codificación)

**Rol XP:** Programador encargado de la codificación del sistema. **Rol Scrum:** Equipo de Desarrollo.

**Artefactos entregados:**
- Sprint 1 (5h): mapa de caracteres del abecedario español (`AlphabetMapService`, HU 01H1)
- Sprint 1 (4h): detección de dígitos y signo numérico Braille (`NumberMapService`, HU 03H3)
- Sprint 2 (3h): mapa de signos de puntuación básicos (`PunctuationMapService`, HU 05H5)
- Sprint 2 (5h): función inversa Braille → español (`InverseBrailleService`, HU 07H7)
- Documentación de artefactos Scrum del equipo (Product Backlog, Sprint Backlog, Burndown Charts, Incremento) en `docs/artefactos-scrum/`, y contenedor Docker (`Dockerfile`, `docker-compose.yml`) para despliegue reproducible.
- Concepto Scrum expuesto: **el Sprint como time-box** de duración fija (Sprint 1: 22 jun–3 jul, 2 semanas; Sprint 2: 6–10 jul, 1 semana), cada uno cerrado en su fecha planificada con objetivo propio.

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
├── Dockerfile                             ← Build multi-stage (JDK 25 → JRE 25 Alpine)
├── docker-compose.yml                     ← docker compose up --build
├── docs/
│   ├── design/                            ← Artefactos de Diseño (Elian)
│   │   ├── tarjetas-crc.md                ← 12 Tarjetas CRC
│   │   ├── diagrama-casos-de-uso.md       ← 8 CU con flujos detallados
│   │   ├── diagrama-de-clases.md          ← 12 clases + secuencia
│   │   └── roadmap-alfabeto-braille.md    ← Alfabeto Braille + Gantt
│   ├── artefactos-scrum/                  ← Artefactos Scrum (LaTeX → PDF)
│   │   ├── 01-product-backlog.pdf
│   │   ├── 02-sprint-backlog.pdf
│   │   ├── 03-burndown-charts.pdf
│   │   └── 04-incremento.pdf
│   └── presentacion/                      ← Diapositivas de la exposición (Beamer → PDF)
│       └── presentacion.pdf
```

## Artefactos Scrum

| Artefacto | Descripción | Archivo |
|-----------|-------------|---------|
| Product Backlog | 7 Historias de Usuario priorizadas, estimadas y con estado real | [`01-product-backlog.pdf`](docs/artefactos-scrum/01-product-backlog.pdf) |
| Sprint Backlog | Tareas técnicas por sprint, responsable y horas reales | [`02-sprint-backlog.pdf`](docs/artefactos-scrum/02-sprint-backlog.pdf) |
| Burndown Charts | Avance ideal vs. real por sprint, con plantilla para el Scrum Master | [`03-burndown-charts.pdf`](docs/artefactos-scrum/03-burndown-charts.pdf) |
| Incremento | Funcionalidad entregable, Definition of Done y cómo ejecutarla | [`04-incremento.pdf`](docs/artefactos-scrum/04-incremento.pdf) |

Fuentes LaTeX en `docs/artefactos-scrum/*.tex` (compilables con `pdflatex`, preámbulo compartido en `preambulo.tex`).

## Diapositivas de la exposición

[`docs/presentacion/presentacion.pdf`](docs/presentacion/presentacion.pdf) --- 31 diapositivas (Beamer, tema Metropolis) centradas en la metodología ágil: framework Scrum, roles, eventos y artefactos, recorrido de Sprint 1 y Sprint 2 con datos reales (fechas, responsables, horas, burndown), Definition of Done, Incremento, Sprint Review/Retrospective y aprendizajes. Incluye las 4 exposiciones de concepto Scrum asignadas al equipo (time-box, Sprint Backlog, Definition of Done, autoorganización). Fuente en `docs/presentacion/presentacion.tex`.

## Cómo ejecutar (Docker)

```bash
docker compose up --build
# abrir http://localhost:8080
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

> Duración planificada originalmente: 4 semanas/sprint. Duración **real** ejecutada: ver fechas abajo (ambos sprints cerraron antes de lo planificado).

### Sprint 1 — Transcripción Base (12 pts · 22 jun – 3 jul 2026, 2 semanas)
- **HUs:** 01H1 (5 pts), 02H2 (3 pts), 03H3 (2 pts), 04H4 (2 pts)
- **Objetivo:** Implementar la conversión completa de texto español a Braille incluyendo abecedario, vocales acentuadas, números y mayúsculas
- **Velocidad real:** 6.0 pts/sem · 32 h técnicas registradas

### Sprint 2 — Funcionalidades Avanzadas (15 pts · 6 – 10 jul 2026, 1 semana)
- **HUs:** 05H5 (3 pts), 06H6 (7 pts), 07H7 (5 pts)
- **Objetivo:** Agregar puntuación, generación de señalética imprimible y transcripción inversa Braille→español
- **Velocidad real:** 15.0 pts/sem · 23 h técnicas registradas

### Velocidad Total del Release
- **Story Points totales:** 27 pts
- **Duración real total:** 3 semanas (22 jun – 10 jul 2026) · 15 Daily Scrums registrados
- **Horas técnicas totales:** 55 h (Javier Angulo 14h, Erick Costa 14h, Elian Caizapanta 12h, Emily Aumala 11h)

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
