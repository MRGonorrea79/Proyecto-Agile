package ec.epn.edu.spanishBrailleApp.service;

import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class BrailleConverterService {

    private static final int SIGNO_MAYUSCULA      = BrailleCharacterMap.mask(4, 6);
    private static final int SIGNO_NUMERO         = BrailleCharacterMap.mask(3, 4, 5, 6);
    private static final int SIGNO_MAYUS_ESPEJO   = BrailleCharacterMap.mask(1, 3);
    private static final int SIGNO_NUMERO_ESPEJO  = BrailleCharacterMap.mask(1, 2, 3, 6);

    private final AlphabetMapService     alphabetMapService;
    private final AccentMapService       accentMapService;
    private final NumberMapService       numberMapService;
    private final UpperCaseHandlerService upperCaseHandlerService;
    private final PunctuationMapService  punctuationMapService;
    private final BrailleRendererService rendererService;
    private final BrailleCharacterMap    characterMap;

    public BrailleConverterService(
            AlphabetMapService alphabetMapService,
            AccentMapService accentMapService,
            NumberMapService numberMapService,
            UpperCaseHandlerService upperCaseHandlerService,
            PunctuationMapService punctuationMapService,
            BrailleRendererService rendererService,
            BrailleCharacterMap characterMap) {

        this.alphabetMapService   = alphabetMapService;
        this.accentMapService     = accentMapService;
        this.numberMapService     = numberMapService;
        this.upperCaseHandlerService = upperCaseHandlerService;
        this.punctuationMapService = punctuationMapService;
        this.rendererService      = rendererService;
        this.characterMap         = characterMap;
    }

    // ── Español → Braille normal ──────────────────────────────────────────────

    public String convertirTextoABraille(String texto) {
        return procesarTexto(texto, characterMap.getMapaNormal(),
                SIGNO_MAYUSCULA, SIGNO_NUMERO, false);
    }

    // ── Español → Braille espejo ──────────────────────────────────────────────

    public String convertirTextoABrailleEspejo(String texto) {
        String resultado = procesarTexto(texto, characterMap.getMapaEspejo(),
                SIGNO_MAYUS_ESPEJO, SIGNO_NUMERO_ESPEJO, false);
        return new StringBuilder(resultado).reverse().toString();
    }

    // ── Núcleo compartido ─────────────────────────────────────────────────────

    private String procesarTexto(String texto, Map<String, Integer> mapa,
                                 int signoMayus, int signoNum, boolean espejo) {
        if (texto.trim().isEmpty()) return rendererService.convertirAUnicode(0);

        texto = texto.replaceAll("\\s+", " ").trim();
        StringBuilder sb = new StringBuilder();
        String[] palabras = texto.split(" ");

        for (int i = 0; i < palabras.length; i++) {
            sb.append(procesarPalabra(palabras[i], mapa, signoMayus, signoNum));
            if (i < palabras.length - 1) sb.append(rendererService.convertirAUnicode(0));
        }
        return sb.toString();
    }

    private String procesarPalabra(String palabra, Map<String, Integer> mapa,
                                   int signoMayus, int signoNum) {
        StringBuilder sb = new StringBuilder();
        boolean esNumero       = palabra.matches("(?=.*\\d)[^A-Za-z]+");
        boolean esMayusCompleta = !esNumero && esPalabraCompleta(palabra);

        if (esMayusCompleta) {
            sb.append(rendererService.convertirAUnicode(signoMayus));
            sb.append(rendererService.convertirAUnicode(signoMayus));
        }

        boolean inNumber = false;
        for (char c : palabra.toCharArray()) {
            String ch = String.valueOf(c);

            if (esNumero) {
                if (numberMapService.esDigito(c)) {
                    if (!inNumber) {
                        sb.append(rendererService.convertirAUnicode(signoNum));
                        inNumber = true;
                    }
                    sb.append(rendererService.convertirAUnicode(mapa.get(ch)));
                } else if (mapa.containsKey(ch)) {
                    sb.append(rendererService.convertirAUnicode(mapa.get(ch)));
                    inNumber = false;
                } else {
                    sb.append(" ");
                    inNumber = false;
                }
                continue;
            }

            if (!esMayusCompleta && upperCaseHandlerService.esMayuscula(c)) {
                sb.append(rendererService.convertirAUnicode(signoMayus));
                ch = upperCaseHandlerService.convertirAMinuscula(ch);
            }

            String cLower = ch.toLowerCase();
            if (accentMapService.esCaracterAcentuado(cLower)) {
                sb.append(rendererService.convertirAUnicode(mapa.get(cLower)));
            } else if (punctuationMapService.esSignoPuntuacion(cLower)) {
                sb.append(rendererService.convertirAUnicode(mapa.get(cLower)));
            } else if (alphabetMapService.existeLetra(cLower)) {
                sb.append(rendererService.convertirAUnicode(mapa.get(cLower)));
            } else {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    private boolean esPalabraCompleta(String word) {
        return word.length() > 1 && word.equals(word.toUpperCase());
    }
}