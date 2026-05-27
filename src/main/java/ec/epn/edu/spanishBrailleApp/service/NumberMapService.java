package ec.epn.edu.spanishBrailleApp.service;

import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class NumberMapService {

    private static final int SIGNO_NUMERO = BrailleCharacterMap.mask(3, 4, 5, 6);

    private final Map<String, Integer> mapaNumeros;

    public NumberMapService(BrailleCharacterMap characterMap) {
        this.mapaNumeros = characterMap.getMapaNormal();
    }

    public Integer obtenerPatronNumero(String digito) {
        return mapaNumeros.get(digito);
    }

    public boolean esDigito(char caracter) {
        return Character.isDigit(caracter);
    }

    public Integer obtenerSignoNumero() {
        return SIGNO_NUMERO;
    }
}