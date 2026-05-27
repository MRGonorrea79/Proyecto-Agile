package ec.epn.edu.spanishBrailleApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada de la aplicación Spring Boot para el sistema de
 * transcripción español ↔ Braille Unicode.
 *
 * <p>{@link SpringBootApplication} habilita el escaneo de componentes,
 * la auto-configuración y el arranque del servidor embebido.</p>
 */
@SpringBootApplication
public class SpanishBrailleApp {

    /**
     * Inicia la aplicación Spring Boot.
     *
     * @param args argumentos de línea de comandos (pueden omitirse)
     */
    public static void main(String[] args) {
        SpringApplication.run(SpanishBrailleApp.class, args);
    }
}