package ec.epn.edu.spanishBrailleApp.service;

import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class InverseBrailleService {

    private static final int SIGNO_NUMERO    = BrailleCharacterMap.mask(3, 4, 5, 6);
    private static final int SIGNO_MAYUSCULA = BrailleCharacterMap.mask(4, 6);

    private final Map<Integer, String> mapaInverso;

    public InverseBrailleService(BrailleCharacterMap characterMap) {
        this.mapaInverso = characterMap.getMapaInverso();
    }

    public String brailleAEspañol(String textoBraille) {
        StringBuilder resultado       = new StringBuilder();
        boolean modoNumero            = false;
        boolean siguienteMayuscula    = false;
        boolean mayusculaPalabra      = false;

        for (int i = 0; i < textoBraille.length(); i++) {
            char c = textoBraille.charAt(i);

            if (c == ' ' || c == '\u2800') {
                resultado.append(' ');
                modoNumero = false;
                siguienteMayuscula = false;
                continue;
            }

            int mascara = brailleCharToMask(c);

            if (mascara == SIGNO_NUMERO) {
                modoNumero = true;
                continue;
            }

            if (mascara == SIGNO_MAYUSCULA) {
                if (i + 1 < textoBraille.length()
                        && brailleCharToMask(textoBraille.charAt(i + 1)) == SIGNO_MAYUSCULA) {
                    mayusculaPalabra = true;
                    i++;
                } else {
                    siguienteMayuscula = true;
                }
                continue;
            }

            String valor = procesarCaracterBraille(mascara);
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

            if (mayusculaPalabra || siguienteMayuscula) {
                resultado.append(valor.toUpperCase());
                if (siguienteMayuscula) siguienteMayuscula = false;
            } else {
                resultado.append(valor);
            }
        }
        return resultado.toString();
    }

    private String procesarCaracterBraille(int mascara) {
        return mapaInverso.get(mascara);
    }

    private int brailleCharToMask(char braille) {
        return braille - 0x2800;
    }

    private String letraANumero(String letra) {
        return switch (letra) {
            case "a" -> "1"; case "b" -> "2"; case "c" -> "3";
            case "d" -> "4"; case "e" -> "5"; case "f" -> "6";
            case "g" -> "7"; case "h" -> "8"; case "i" -> "9";
            case "j" -> "0"; default -> null;
        };
    }
}