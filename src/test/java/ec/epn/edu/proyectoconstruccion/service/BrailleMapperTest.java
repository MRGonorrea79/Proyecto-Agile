package ec.epn.edu.proyectoconstruccion.service;

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
}
