package ec.epn.edu.spanishBrailleApp.service;

import java.util.Map;

/**
 * Servicio de traducción Español → Braille Unicode.
 * Basado en el estándar oficial del Braille español (Unicode U+2800).
 *
 */
public class BrailleMapper {

    // =====================================================
    //  CONSTANTES BRAILLE
    // =====================================================

    private static final int SIGNO_NUMERO   = mask(3, 4, 5, 6); // ⠼
    private static final int SIGNO_MAYUSCULA = mask(4, 6);       // ⠠

    // =====================================================
    //  MAPA
    // =====================================================

    private final Map<String, Integer> map = new BrailleDictionary().getMap();

    // =====================================================
    //  ESPAÑOL → BRAILLE
    // =====================================================

    public String españolABraille(String texto) {
        if (texto.trim().isEmpty()) {
            return maskToUnicode(0);
        }

        texto = texto.replaceAll("\\s+", " ");

        StringBuilder sb = new StringBuilder();
        String[] palabras = texto.split(" ");

        for (int i = 0; i < palabras.length; i++) {
            String palabra = palabras[i];

            boolean esNumero        = palabra.matches("(?=.*\\d)[^A-Za-z]+");
            boolean esMayusCompleta = !esNumero && isFullUppercaseWord(palabra);

            if (esMayusCompleta) {
                sb.append(maskToUnicode(SIGNO_MAYUSCULA));
                sb.append(maskToUnicode(SIGNO_MAYUSCULA));
            }

            boolean inNumber = false;

            for (char c : palabra.toCharArray()) {
                String ch = String.valueOf(c);

                if (esNumero) {
                    if (Character.isDigit(c)) {
                        if (!inNumber) {
                            sb.append(maskToUnicode(SIGNO_NUMERO));
                            inNumber = true;
                        }
                        sb.append(maskToUnicode(map.get(ch)));
                    } else if (map.containsKey(ch)) {
                        sb.append(maskToUnicode(map.get(ch)));
                        inNumber = false;
                    } else {
                        sb.append(" ");
                        inNumber = false;
                    }
                    continue;
                }

                if (!esMayusCompleta && Character.isUpperCase(c)) {
                    sb.append(maskToUnicode(SIGNO_MAYUSCULA));
                    ch = ch.toLowerCase();
                }

                if (map.containsKey(ch.toLowerCase())) {
                    sb.append(maskToUnicode(map.get(ch.toLowerCase())));
                } else {
                    sb.append(" ");
                }
            }

            if (i < palabras.length - 1) {
                sb.append(maskToUnicode(0));
            }
        }

        return sb.toString();
    }

    // =====================================================
    //  UTILIDADES PRIVADAS
    // =====================================================

    private static int mask(int... dots) {
        int m = 0;
        for (int d : dots) m |= (1 << (d - 1));
        return m;
    }

    private static String maskToUnicode(int mask) {
        return String.valueOf((char) (0x2800 + mask));
    }

    private static boolean isFullUppercaseWord(String word) {
        return word.length() > 1 && word.equals(word.toUpperCase());
    }
}