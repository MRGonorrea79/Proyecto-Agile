# Lógica del Negocio — Aplicación de Alfabeto Braille Español

## 1. Estructura de la Celda Braille

El sistema debe trabajar con una celda Braille compuesta por 6 puntos organizados en dos columnas y tres filas.  
Cada carácter Braille se define mediante una combinación específica de puntos activos e inactivos.

Las posiciones válidas de los puntos son:

```
 ┌───┬───┐
 │ 1 │ 4 │
 ├───┼───┤
 │ 2 │ 5 │
 ├───┼───┤
 │ 3 │ 6 │
 └───┴───┘
```

Cada combinación representa una letra, número, signo o carácter especial del idioma español.

---

## 2. Conversión de Letras del Alfabeto
<img width="621" height="386" alt="imagen" src="https://github.com/user-attachments/assets/3e3a7492-5d6d-44ba-bd37-821429e8f0b5" />


El sistema debe permitir convertir letras del alfabeto español a su equivalente Braille.

### Reglas generales

- Las letras `a–j` pertenecen a la primera serie base.
- Las letras `k–t` reutilizan los patrones anteriores agregando el punto 3.
- Las letras `u–z` reutilizan patrones agregando el punto 6.
- Las letras especiales del español (`ñ`, `ü`) deben tener representación propia.

El sistema debe reconocer tanto letras minúsculas como mayúsculas.

---

## 3. Manejo de Vocales Acentuadas

El sistema debe reconocer y convertir correctamente:

- á
- é
- í
- ó
- ú

Cada vocal acentuada debe poseer una representación Braille específica e independiente de su versión sin tilde.

---

## 4. Conversión de Números

Los números del 0 al 9 deben representarse mediante:

1. Un indicador de número.
2. El patrón equivalente de las letras base `a–j`.

### Regla de secuencia numérica

Cuando existan varios dígitos consecutivos:

- El indicador numérico debe colocarse una sola vez al inicio de la secuencia.
- El modo numérico permanece activo hasta que aparezca un carácter no numérico.

Ejemplo conceptual:

- `123` → indicador numérico + representación de 1, 2 y 3.

---

## 5. Manejo de Mayúsculas

El sistema debe diferenciar letras mayúsculas de minúsculas.

### Reglas

- Una letra mayúscula debe antecederse por un indicador de mayúscula.
- Si una palabra completa está en mayúsculas, debe utilizarse un indicador de doble mayúscula.

---

## 6. Conversión de Signos de Puntuación

El sistema debe soportar símbolos de puntuación comunes del español, incluyendo:

- coma
- punto
- punto y coma
- dos puntos
- signos de interrogación
- signos de exclamación
- guiones
- paréntesis

Cada signo debe poseer una representación Braille específica.

---

## 7. Espacios y Separación de Palabras

El sistema debe reconocer espacios entre palabras y representarlos como separadores vacíos dentro de la transcripción Braille.

Los espacios no deben activar reglas especiales ni modificar estados de numeración o mayúsculas más allá de finalizar secuencias activas.

---

## 8. Modo Braille Espejo

El sistema debe incluir una función de “Braille espejo”.

### Objetivo

Generar una representación invertida horizontalmente para procesos de impresión en relieve.

### Regla de inversión

- Los puntos izquierdos se intercambian con los derechos:
  - 1 ↔ 4
  - 2 ↔ 5
  - 3 ↔ 6

La representación espejo debe conservar el significado original del carácter.

---

## 9. Transcripción Español → Braille

El sistema debe permitir convertir texto escrito en español a Braille.

### Flujo lógico

Para cada carácter del texto:

1. Identificar el tipo de carácter:
   - letra
   - número
   - espacio
   - puntuación
   - carácter especial

2. Aplicar reglas contextuales:
   - indicador numérico
   - indicador de mayúscula
   - doble mayúscula

3. Generar la representación Braille correspondiente.

4. Mantener estados activos cuando corresponda:
   - modo numérico
   - palabra en mayúsculas

---

## 10. Transcripción Braille → Español

El sistema debe permitir la conversión inversa desde Braille hacia texto en español.

### Requisitos

- Interpretar indicadores numéricos.
- Interpretar indicadores de mayúsculas.
- Reconocer puntuación y caracteres especiales.
- Reconstruir correctamente palabras y números.

---

## 11. Reglas de Validación

El sistema debe validar que:

- Solo se procesen caracteres soportados.
- Las secuencias Braille tengan estructuras válidas.
- Los indicadores contextuales sean interpretados correctamente.
- Las conversiones mantengan coherencia bidireccional.

---

## 12. Objetivos Funcionales del Sistema

La aplicación debe permitir:

- Convertir texto español a Braille.
- Convertir Braille a texto español.
- Representar caracteres especiales del español.
- Manejar números y mayúsculas.
- Generar representación Braille espejo.
- Facilitar la creación de material accesible y señalética Braille.
