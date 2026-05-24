# Proyecto Agile — Spanish Braille Application

## Descripción del Proyecto

Aplicación web desarrollada con **Spring Boot** que permite la traducción bidireccional entre **español** y **Braille Unicode**, incluyendo soporte para acentos, mayúsculas, ñ, números y signos de puntuación. También cuenta con la función de **Braille espejo** para facilitar la impresión en relieve y escritura manual.

- **Repositorio del código fuente:** [Spanish-Braille-Application (ITERACION-2)](https://github.com/MRGonorrea79/Spanish-Braille-Application/tree/ITERACION-2)
- **Metodología:** XP (Extreme Programming)
- **Herramienta de gestión:** GitHub
- **Materia:** Metodologías Ágiles

---

## Equipo y Roles XP

| Rol XP | Integrante | Responsabilidad |
|--------|-----------|-----------------|
| **Cliente / Planning** | *(Pendiente de completar)* | Definir historias de usuario, priorizar el backlog y participar en las pruebas de aceptación |
| **Programador — Diseño** | **Elian Caizapanta** | Crear los artefactos de diseño: diagramas de casos de uso, diagramas de clases y roadmap del sistema |
| **Programador — Codificación** | *(Pendiente de completar)* | Implementar y modificar el código fuente según las historias de usuario |
| **Programador — Codificación** | *(Pendiente de completar)* | Implementar y modificar el código fuente según las historias de usuario |
| **Tester** | *(Pendiente de completar)* | Diseñar y ejecutar pruebas unitarias y de aceptación |
| **Manager / Tracker** | *(Pendiente de completar)* | Supervisar el progreso del proyecto, coordinar al equipo y presentar resultados |

---

## Aportes por Integrante

### Elian Caizapanta — Programador (Diseño)

**Rol XP:** Programador encargado del diseño de la arquitectura y modelado del sistema.

**Artefactos entregados:**

#### 1. Diagrama de Casos de Uso
- **Archivo:** [`docs/design/diagrama-casos-de-uso.md`](docs/design/diagrama-casos-de-uso.md)
- 10 Casos de Uso (CU-01 a CU-10) derivados de las 10 Historias de Usuario (01H1–10H10)
- Diagrama general en Mermaid con relaciones `<<incluye>>` entre casos de uso
- Descripción detallada de cada CU: actor, precondición, flujo principal, postcondición y prioridad
- Matriz de trazabilidad que relaciona cada Historia de Usuario con su Caso de Uso correspondiente y sprint asignado

#### 2. Diagrama de Clases
- **Archivo:** [`docs/design/diagrama-de-clases.md`](docs/design/diagrama-de-clases.md)
- 5 clases con **nombres prototipo** (diseño conceptual previo a la implementación):
  - `AplicacionPrincipal` → Punto de entrada Spring Boot
  - `ControladorTraduccion` → Controlador web (endpoints HTTP)
  - `TraductorTexto` → Servicio central de traducción bidireccional
  - `DiccionarioBraille` → Almacén de mapas de correspondencia carácter↔Braille
  - `ConvertidorInverso` → Servicio especializado Braille→Español
- 2 Diagramas de Secuencia: flujo Español→Braille y flujo Braille→Español
- Tabla de mapeo Diseño Prototipo → Implementación real
- Patrones de diseño identificados: MVC, Diccionario/Lookup Table, Strategy, Inmutabilidad

#### 3. Roadmap del Alfabeto Braille Español
- **Archivo:** [`docs/design/roadmap-alfabeto-braille.md`](docs/design/roadmap-alfabeto-braille.md)
- Estructura de la celda Braille (6 puntos) y fórmula Unicode
- Tablas completas: letras (1ra, 2da, 3ra serie), caracteres especiales (ñ, ü), vocales acentuadas
- Números (0–9) con signo numérico, signos de puntuación y signos de control
- Explicación del Braille espejo (reflexión horizontal de puntos)
- Diagrama Gantt por Sprint 1 y Sprint 2
- Ejemplos de transcripción: "Hola Mundo 123", "café", "ECUADOR"

---

### *(Nombre del integrante)* — Cliente / Planning

**Rol XP:** Cliente que define las historias de usuario y prioriza el backlog.

**Artefactos entregados:**

> *Pendiente de completar. Agregar aquí:*
> - Historias de Usuario con prioridad y criterios de aceptación
> - Release Planning (planificación de sprints)
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
│       ├── diagrama-casos-de-uso.md       ← 10 CU con flujos detallados
│       ├── diagrama-de-clases.md          ← Clases prototipo + secuencia
│       └── roadmap-alfabeto-braille.md    ← Alfabeto Braille + Gantt
├── src/                                   ← Código fuente (Spring Boot)
│   ├── main/
│   └── test/
└── pom.xml                                ← Configuración Maven
```

---

## Historias de Usuario

| ID | Historia de Usuario | Prioridad | Sprint |
|----|---------------------|-----------|--------|
| 01H1 | Como usuario necesito ingresar texto en español para convertirlo a Braille | Alta | 1 |
| 02H2 | Como usuario necesito que el sistema transcriba correctamente las letras (a–z) al Braille | Alta | 1 |
| 03H3 | Como usuario necesito que convierta vocales acentuadas (á,é,í,ó,ú) y ñ al Braille | Alta | 1 |
| 04H4 | Como usuario necesito que transcriba números (0–9) con signo de número Braille | Media | 1 |
| 05H5 | Como usuario necesito que identifique mayúsculas y anteponga el indicador Braille | Media | 1 |
| 06H6 | Como usuario necesito que convierta signos de puntuación básicos al Braille | Media | 2 |
| 07H7 | Como usuario necesito ver la representación gráfica de los cuadratines Braille | Media | 2 |
| 08H8 | Como usuario necesito generar señalética Braille espejo para impresión | Alta | 2 |
| 09H9 | Como usuario necesito transcribir Braille de vuelta a español (bidireccional) | Baja | 2 |
| 10H10 | Como usuario necesito limpiar los campos de entrada/salida con un botón de reset | Baja | 2 |

---

## Planificación de Sprints

### Sprint 1 — Transcripción Base
- **Duración:** 1 semana
- **Historias:** 01H1, 02H2, 03H3, 04H4, 05H5
- **Objetivo:** Implementar la conversión Español→Braille con soporte para letras, acentos, números y mayúsculas

### Sprint 2 — Funcionalidades Avanzadas
- **Duración:** 1 semana
- **Historias:** 06H6, 07H7, 08H8, 09H9, 10H10
- **Objetivo:** Agregar puntuación, visualización gráfica, modo espejo, conversión inversa y botón de limpieza

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
