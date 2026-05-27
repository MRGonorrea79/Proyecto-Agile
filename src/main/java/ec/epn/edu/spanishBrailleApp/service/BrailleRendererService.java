package ec.epn.edu.spanishBrailleApp.service;

import org.springframework.stereotype.Service;

@Service
public class BrailleRendererService {

    public String convertirAUnicode(int mascara) {
        return String.valueOf((char) (0x2800 + mascara));
    }

    public String renderizarCadena(int[] mascaras) {
        StringBuilder sb = new StringBuilder();
        for (int m : mascaras) sb.append(convertirAUnicode(m));
        return sb.toString();
    }
}