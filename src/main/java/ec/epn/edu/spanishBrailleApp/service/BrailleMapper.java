package ec.epn.edu.spanishBrailleApp.service;

import java.util.Map;

public class BrailleMapper {

    // =====================================================
    //  CONSTANTES BRAILLE
    // =====================================================

    private static final int SIGNO_NUMERO          = mask(3, 4, 5, 6);
    private static final int SIGNO_MAYUSCULA       = mask(4, 6);
    private static final int SIGNO_NUMERO_ESPEJO   = mask(1, 2, 3, 6);
    private static final int SIGNO_MAYUSCULA_ESPEJO = mask(1, 3);

    // =====================================================
    //  MAPAS
    // =====================================================

    private final BrailleDictionary characterMap = new BrailleDictionary();

    private final Map<String, Integer> map        = characterMap.getMapaNormal();
    private final Map<String, Integer> pam        = characterMap.getMapaEspejo();
    private final Map<Integer, String> reverseMap = characterMap.getMapaInverso();

    // =====================================================
    //  ESPAÑOL → BRAILLE
    // =====================================================

    public String españolABraille(String texto) {
        if (texto.trim().isEmpty()) return maskToUnicode(0);

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
                        if (!inNumber) { sb.append(maskToUnicode(SIGNO_NUMERO)); inNumber = true; }
                        sb.append(maskToUnicode(map.get(ch)));
                    } else if (map.containsKey(ch)) {
                        sb.append(maskToUnicode(map.get(ch))); inNumber = false;
                    } else {
                        sb.append(" "); inNumber = false;
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
            if (i < palabras.length - 1) sb.append(maskToUnicode(0));
        }
        return sb.toString();
    }

    // =====================================================
    //  BRAILLE → ESPAÑOL
    // =====================================================

    public String brailleAEspañol(String textoBraille) {
        StringBuilder resultado = new StringBuilder();
        boolean modoNumero        = false;
        boolean siguienteMayuscula = false;
        boolean mayusculaPalabra  = false;

        for (int i = 0; i < textoBraille.length(); i++) {
            char c = textoBraille.charAt(i);

            if (c == ' ' || c == '\u2800') {
                resultado.append(' ');
                modoNumero = false;
                siguienteMayuscula = false;
                continue;
            }

            int mascara = brailleCharToMask(c);

            if (mascara == SIGNO_NUMERO) { modoNumero = true; continue; }

            if (mascara == SIGNO_MAYUSCULA) {
                if (i + 1 < textoBraille.length()
                        && brailleCharToMask(textoBraille.charAt(i + 1)) == SIGNO_MAYUSCULA) {
                    mayusculaPalabra = true; i++;
                } else {
                    siguienteMayuscula = true;
                }
                continue;
            }

            String valor = reverseMap.get(mascara);
            if (valor == null) {
                resultado.append('?');
                modoNumero = false; siguienteMayuscula = false; mayusculaPalabra = false;
                continue;
            }

            if (modoNumero) {
                String numero = letraANumero(valor);
                if (numero != null) { resultado.append(numero); continue; }
                else modoNumero = false;
            }

            if (mayusculaPalabra) {
                resultado.append(valor.toUpperCase());
            } else if (siguienteMayuscula) {
                resultado.append(valor.toUpperCase());
                siguienteMayuscula = false;
            } else {
                resultado.append(valor);
            }
        }
        return resultado.toString();
    }

    // =====================================================
    //  ESPAÑOL → BRAILLE ESPEJO
    // =====================================================

    public String españolABrailleEspejo(String texto) {
        if (texto.trim().isEmpty()) return maskToUnicode(0);

        texto = texto.replaceAll("\\s+", " ");
        StringBuilder sb = new StringBuilder();
        String[] palabras = texto.split(" ");

        for (int i = 0; i < palabras.length; i++) {
            String palabra = palabras[i];
            boolean esNumero        = palabra.matches("(?=.*\\d)[^A-Za-z]+");
            boolean esMayusCompleta = !esNumero && isFullUppercaseWord(palabra);

            if (esMayusCompleta) {
                sb.append(maskToUnicode(SIGNO_MAYUSCULA_ESPEJO));
                sb.append(maskToUnicode(SIGNO_MAYUSCULA_ESPEJO));
            }

            boolean inNumber = false;
            for (char c : palabra.toCharArray()) {
                String ch = String.valueOf(c);
                if (esNumero) {
                    if (Character.isDigit(c)) {
                        if (!inNumber) { sb.append(maskToUnicode(SIGNO_NUMERO_ESPEJO)); inNumber = true; }
                        sb.append(maskToUnicode(pam.get(ch)));
                    } else if (pam.containsKey(ch)) {
                        sb.append(maskToUnicode(pam.get(ch))); inNumber = false;
                    } else {
                        sb.append(" "); inNumber = false;
                    }
                    continue;
                }
                if (!esMayusCompleta && Character.isUpperCase(c)) {
                    sb.append(maskToUnicode(SIGNO_MAYUSCULA_ESPEJO));
                    ch = ch.toLowerCase();
                }
                if (pam.containsKey(ch.toLowerCase())) {
                    sb.append(maskToUnicode(pam.get(ch.toLowerCase())));
                } else {
                    sb.append(" ");
                }
            }
            if (i < palabras.length - 1) sb.append(maskToUnicode(0));
        }
        sb.reverse();
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

    private static int brailleCharToMask(char braille) {
        return braille - 0x2800;
    }

    private static String letraANumero(String l) {
        switch (l) {
            case "a": return "1";
            case "b": return "2";
            case "c": return "3";
            case "d": return "4";
            case "e": return "5";
            case "f": return "6";
            case "g": return "7";
            case "h": return "8";
            case "i": return "9";
            case "j": return "0";
            default:  return null;
        }
    }
}