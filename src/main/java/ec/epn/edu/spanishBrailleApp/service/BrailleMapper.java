package ec.epn.edu.spanishBrailleApp.service;

import java.util.HashMap;
import java.util.Map;

public class BrailleMapper {

    // =====================================================
    //  CONSTANTES BRAILLE
    // =====================================================

    private static final int SIGNO_NUMERO    = mask(3, 4, 5, 6);
    private static final int SIGNO_MAYUSCULA = mask(4, 6);

    // =====================================================
    //  MAPA
    // =====================================================

    private final Map<String, Integer> map = new HashMap<>();

    // =====================================================
    //  CONSTRUCTOR
    // =====================================================

    public BrailleMapper() {
        initLetters();
        initAccents();
        initPunctuation();
        initNumbers();
    }

    // =====================================================
    //  INICIALIZACIÓN DEL MAPA
    // =====================================================

    private void initLetters() {
        map.put("a", mask(1));
        map.put("b", mask(1, 2));
        map.put("c", mask(1, 4));
        map.put("d", mask(1, 4, 5));
        map.put("e", mask(1, 5));
        map.put("f", mask(1, 2, 4));
        map.put("g", mask(1, 2, 4, 5));
        map.put("h", mask(1, 2, 5));
        map.put("i", mask(2, 4));
        map.put("j", mask(2, 4, 5));

        map.put("k", addDot(map.get("a"), 3));
        map.put("l", addDot(map.get("b"), 3));
        map.put("m", addDot(map.get("c"), 3));
        map.put("n", addDot(map.get("d"), 3));
        map.put("o", addDot(map.get("e"), 3));
        map.put("p", addDot(map.get("f"), 3));
        map.put("q", addDot(map.get("g"), 3));
        map.put("r", addDot(map.get("h"), 3));
        map.put("s", addDot(map.get("i"), 3));
        map.put("t", addDot(map.get("j"), 3));
        map.put("u", addDot(map.get("k"), 6));
        map.put("v", addDot(map.get("l"), 6));
        map.put("x", addDot(map.get("m"), 6));
        map.put("y", addDot(map.get("n"), 6));
        map.put("z", addDot(map.get("o"), 6));

        map.put("ñ", mask(1, 2, 4, 5, 6));
        map.put("ü", mask(1, 2, 5, 6));
    }

    private void initAccents() {
        map.put("á", mask(1, 2, 3, 5, 6));
        map.put("é", mask(2, 3, 4, 6));
        map.put("í", mask(3, 4));
        map.put("ó", mask(3, 4, 6));
        map.put("ú", mask(2, 3, 4, 5, 6));
    }

    private void initPunctuation() {
        map.put(",", mask(2));
        map.put(";", mask(2, 3));
        map.put(":", mask(2, 5));
        map.put(".", mask(3));
        map.put("?", mask(2, 6));
        map.put("!", mask(2, 3, 5));
        map.put("-", mask(3, 6));
        map.put("(", mask(1, 2, 6));
        map.put(")", mask(3, 4, 5));
        map.put("+", mask(2, 3, 5));
        map.put("*", mask(3, 5));
        map.put("=", mask(2, 3, 5, 6));
        map.put(" ", 0);
    }

    private void initNumbers() {
        map.put("#", SIGNO_NUMERO);
        map.put("1", map.get("a"));
        map.put("2", map.get("b"));
        map.put("3", map.get("c"));
        map.put("4", map.get("d"));
        map.put("5", map.get("e"));
        map.put("6", map.get("f"));
        map.put("7", map.get("g"));
        map.put("8", map.get("h"));
        map.put("9", map.get("i"));
        map.put("0", map.get("j"));
    }

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

    private static int addDot(int base, int dot) {
        return base | (1 << (dot - 1));
    }

    private static String maskToUnicode(int mask) {
        return String.valueOf((char) (0x2800 + mask));
    }

    private static boolean isFullUppercaseWord(String word) {
        return word.length() > 1 && word.equals(word.toUpperCase());
    }
}