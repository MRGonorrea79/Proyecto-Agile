package ec.epn.edu.spanishBrailleApp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BrailleConverterServiceTest {

    private BrailleConverterService converterService;

    @BeforeEach
    void setUp() {
        BrailleCharacterMap     characterMap          = new BrailleCharacterMap();
        AlphabetMapService      alphabetMapService    = new AlphabetMapService(characterMap);
        AccentMapService        accentMapService      = new AccentMapService(characterMap);
        NumberMapService        numberMapService      = new NumberMapService(characterMap);
        UpperCaseHandlerService upperCaseHandler      = new UpperCaseHandlerService();
        PunctuationMapService   punctuationMapService = new PunctuationMapService(characterMap);
        BrailleRendererService  rendererService       = new BrailleRendererService();

        converterService = new BrailleConverterService(
                alphabetMapService,
                accentMapService,
                numberMapService,
                upperCaseHandler,
                punctuationMapService,
                rendererService,
                characterMap
        );
    }

    @Test
    @DisplayName("1. Transcribir texto básico en español")
    void transcribirTextoBasico() {
        assertThat(converterService.convertirTextoABraille("hola mundo"))
                .isEqualTo("⠓⠕⠇⠁⠀⠍⠥⠝⠙⠕");
    }

    @Test
    @DisplayName("2. Transcribir mayúsculas con signo de mayúscula")
    void transcribirMayusculas() {
        assertThat(converterService.convertirTextoABraille("HOLA"))
                .isEqualTo("⠨⠨⠓⠕⠇⠁");
    }

    @Test
    @DisplayName("3. Transcribir vocales acentuadas")
    void transcribirAcentos() {
        assertThat(converterService.convertirTextoABraille("áéíóú"))
                .isEqualTo("⠷⠮⠌⠬⠾");
    }

    @Test
    @DisplayName("4. Transcribir letra ñ")
    void transcribirEnie() {
        assertThat(converterService.convertirTextoABraille("año"))
                .isEqualTo("⠁⠻⠕");
    }

    @Test
    @DisplayName("5. Transcribir números con signo de número")
    void transcribirNumeros() {
        assertThat(converterService.convertirTextoABraille("123"))
                .isEqualTo("⠼⠁⠃⠉");
    }

    @Test
    @DisplayName("6. Transcribir signos de puntuación válidos")
    void transcribirPuntuacion() {
        assertThat(converterService.convertirTextoABraille("hola, mundo."))
                .isEqualTo("⠓⠕⠇⠁⠂⠀⠍⠥⠝⠙⠕⠄");
    }

    @Test
    @DisplayName("7. Transcribir texto en braille espejo")
    void transcribirEspejo() {
        assertThat(converterService.convertirTextoABrailleEspejo("hola"))
                .isEqualTo("⠈⠸⠪⠚");
    }
}