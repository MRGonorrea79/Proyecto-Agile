package ec.epn.edu.spanishBrailleApp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InverseBrailleServiceTest {

    private InverseBrailleService inverseService;

    @BeforeEach
    void setUp() {
        inverseService = new InverseBrailleService(new BrailleCharacterMap());
    }

    @Test
    @DisplayName("8. Transcribir braille básico a español")
    void brailleTextoBasico() {
        assertThat(inverseService.brailleAEspañol("⠓⠕⠇⠁⠀⠍⠥⠝⠙⠕"))
                .isEqualTo("hola mundo");
    }

    @Test
    @DisplayName("9. Transcribir mayúsculas con signo de mayúscula")
    void brailleMayusculas() {
        assertThat(inverseService.brailleAEspañol("⠨⠨⠓⠕⠇⠁"))
                .isEqualTo("HOLA");
    }

    @Test
    @DisplayName("10. Transcribir vocales acentuadas")
    void brailleAcentos() {
        assertThat(inverseService.brailleAEspañol("⠷⠮⠌⠬⠾"))
                .isEqualTo("áéíóú");
    }

    @Test
    @DisplayName("11. Transcribir letra ñ")
    void brailleEnie() {
        assertThat(inverseService.brailleAEspañol("⠁⠻⠕"))
                .isEqualTo("año");
    }

    @Test
    @DisplayName("12. Transcribir números con signo de número")
    void brailleNumeros() {
        assertThat(inverseService.brailleAEspañol("⠼⠁⠃⠉"))
                .isEqualTo("123");
    }

    @Test
    @DisplayName("13. Transcribir signos de puntuación")
    void braillePuntuacion() {
        assertThat(inverseService.brailleAEspañol("⠓⠕⠇⠁⠂⠀⠍⠥⠝⠙⠕⠄"))
                .isEqualTo("hola, mundo.");
    }
}