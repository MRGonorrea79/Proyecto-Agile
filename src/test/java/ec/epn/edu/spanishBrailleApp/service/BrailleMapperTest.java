package ec.epn.edu.spanishBrailleApp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BrailleMapperTest {

    private BrailleMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new BrailleMapper();
    }
    @Test
    @DisplayName("1. Transcribir texto básico en español")
    void transcribirTextoBasico() {
        String result = mapper.españolABraille("hola mundo");
        assertThat(result).isEqualTo("⠓⠕⠇⠁⠀⠍⠥⠝⠙⠕");
    }

    @Test
    @DisplayName("2. Transcribir mayúsculas con signo de mayúscula")
    void transcribirMayusculas() {
        String result = mapper.españolABraille("HOLA");
        assertThat(result).isEqualTo("⠨⠨⠓⠕⠇⠁");
    }

    @Test
    @DisplayName("3. Transcribir vocales acentuadas")
    void transcribirAcentos() {
        String result = mapper.españolABraille("áéíóú");
        assertThat(result).isEqualTo("⠷⠮⠌⠬⠾");
    }

    @Test
    @DisplayName("4. Transcribir letra ñ")
    void transcribirEnie() {
        String result = mapper.españolABraille("año");
        assertThat(result).isEqualTo("⠁⠻⠕");
    }

    @Test
    @DisplayName("6. Transcribir números con signo de número")
    void transcribirNumeros() {
        String result = mapper.españolABraille("123");
        assertThat(result).isEqualTo("⠼⠁⠃⠉");
    }
    // CU-06 — Signos de puntuación
    @Test
    @DisplayName("7. Transcribir signos de puntuación válidos")
    void transcribirPuntuacion() {
        String result = mapper.españolABraille("hola, mundo.");
        assertThat(result).isEqualTo("⠓⠕⠇⠁⠂⠀⠍⠥⠝⠙⠕⠄");
    }

    // CU-09 — Transcripción inversa Braille → español
    @Test
    @DisplayName("8. Transcribir braille básico a español")
    void brailleTextoBasico() {
        String result = mapper.brailleAEspañol("⠓⠕⠇⠁⠀⠍⠥⠝⠙⠕");
        assertThat(result).isEqualTo("hola mundo");
    }

    @Test
    @DisplayName("9. Transcribir mayúsculas con signo de mayúscula")
    void brailleMayusculas() {
        String result = mapper.brailleAEspañol("⠨⠨⠓⠕⠇⠁");
        assertThat(result).isEqualTo("HOLA");
    }

    @Test
    @DisplayName("10. Transcribir vocales acentuadas")
    void brailleAcentos() {
        String result = mapper.brailleAEspañol("⠷⠮⠌⠬⠾");
        assertThat(result).isEqualTo("áéíóú");
    }

    @Test
    @DisplayName("11. Transcribir letra ñ")
    void brailleEnie() {
        String result = mapper.brailleAEspañol("⠁⠻⠕");
        assertThat(result).isEqualTo("año");
    }

    @Test
    @DisplayName("12. Transcribir números con signo de número")
    void brailleNumeros() {
        String result = mapper.brailleAEspañol("⠼⠁⠃⠉");
        assertThat(result).isEqualTo("123");
    }

    @Test
    @DisplayName("13. Transcribir signos de puntuación")
    void braillePuntuacion() {
        String result = mapper.brailleAEspañol("⠓⠕⠇⠁⠂⠀⠍⠥⠝⠙⠕⠄");
        assertThat(result).isEqualTo("hola, mundo.");
    }
}
