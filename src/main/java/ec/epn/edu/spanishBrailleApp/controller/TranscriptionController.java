package ec.epn.edu.spanishBrailleApp.controller;

import ec.epn.edu.spanishBrailleApp.service.BrailleConverterService;
import ec.epn.edu.spanishBrailleApp.service.InverseBrailleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * <p><b>TranscriptionController</b></p>
 *
 * <p>Controlador MVC encargado de gestionar las solicitudes HTTP relacionadas
 * con la transcripción bidireccional entre texto plano en español y Braille
 * Unicode, así como la generación de Braille en formato espejo.</p>
 *
 * <p>Funciones principales:</p>
 * <ul>
 *     <li>Mostrar la página principal del transcriptor.</li>
 *     <li>Convertir texto en español a su representación en Braille Unicode.</li>
 *     <li>Convertir texto en Braille Unicode a su representación en español.</li>
 *     <li>Convertir texto en español a Braille en formato espejo (para impresión
 *         en relieve desde el reverso).</li>
 * </ul>
 *
 * <p>Delega la lógica de conversión en los siguientes servicios:</p>
 * <ul>
 *     <li>{@link BrailleConverterService} — conversión español → Braille normal y espejo.</li>
 *     <li>{@link InverseBrailleService} — conversión Braille → español.</li>
 * </ul>
 */
@Controller
public class TranscriptionController {

    private final BrailleConverterService brailleConverter;
    private final InverseBrailleService   inverseBrailleService;

    /**
     * Construye el controlador inyectando los servicios de conversión.
     *
     * @param brailleConverter      servicio que convierte español a Braille normal y espejo
     * @param inverseBrailleService servicio que convierte Braille a español
     */
    public TranscriptionController(BrailleConverterService brailleConverter,
                                   InverseBrailleService inverseBrailleService) {
        this.brailleConverter       = brailleConverter;
        this.inverseBrailleService  = inverseBrailleService;
    }

    /**
     * Muestra la página principal del transcriptor.
     *
     * <p>Responde a solicitudes GET en la raíz de la aplicación y retorna la
     * vista base donde el usuario puede ingresar texto para transcribir.</p>
     *
     * @return nombre de la plantilla Thymeleaf correspondiente a la página inicial
     *         ({@code "index"})
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * Convierte texto en español a Braille Unicode.
     *
     * <p>Recibe texto plano desde el formulario, lo procesa mediante
     * {@link BrailleConverterService#convertirTextoABraille(String)} y envía
     * el resultado al modelo para su presentación en la vista.</p>
     *
     * @param texto texto plano en español ingresado por el usuario
     * @param model objeto utilizado para enviar atributos a la vista Thymeleaf
     * @return nombre de la plantilla Thymeleaf donde se muestra el resultado
     *         ({@code "result-braille"})
     */
    @PostMapping("/transcribir-Español")
    public String convertirEspañolABraille(@RequestParam String texto, Model model) {
        model.addAttribute("textoOriginal", texto);
        model.addAttribute("resultado", brailleConverter.convertirTextoABraille(texto));
        return "result-braille";
    }

    /**
     * Convierte texto en Braille Unicode a español.
     *
     * <p>Recibe una cadena de caracteres Braille Unicode desde el formulario,
     * la procesa mediante {@link InverseBrailleService#brailleAEspañol(String)}
     * y envía el resultado al modelo para su presentación en la vista.</p>
     *
     * @param texto cadena de caracteres Braille Unicode ingresada por el usuario
     * @param model objeto utilizado para enviar atributos a la vista Thymeleaf
     * @return nombre de la plantilla Thymeleaf donde se muestra el resultado
     *         ({@code "result-español"})
     */
    @PostMapping("/transcribir-Braille")
    public String convertirBrailleAEspañol(@RequestParam String texto, Model model) {
        model.addAttribute("textoOriginal", texto);
        model.addAttribute("resultado", inverseBrailleService.brailleAEspañol(texto));
        return "result-español";
    }

    /**
     * Convierte texto en español a Braille en formato espejo.
     *
     * <p>Recibe texto plano desde el formulario, lo procesa mediante
     * {@link BrailleConverterService#convertirTextoABrailleEspejo(String)}, que
     * aplica el mapa de caracteres espejo e invierte la cadena resultante para
     * permitir la impresión en relieve desde el reverso del soporte, y envía
     * el resultado al modelo para su presentación en la vista.</p>
     *
     * @param texto texto plano en español ingresado por el usuario
     * @param model objeto utilizado para enviar atributos a la vista Thymeleaf
     * @return nombre de la plantilla Thymeleaf donde se muestra el resultado
     *         ({@code "result-espejo"})
     */
    @PostMapping("/espejo")
    public String convertirEspejo(@RequestParam String texto, Model model) {
        model.addAttribute("textoOriginal", texto);
        model.addAttribute("resultado", brailleConverter.convertirTextoABrailleEspejo(texto));
        return "result-espejo";
    }
}
