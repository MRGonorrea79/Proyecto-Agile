package ec.epn.edu.spanishBrailleApp.service;

import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class AlphabetMapService {

    private final Map<String, Integer> mapaAlfabeto;

    public AlphabetMapService(BrailleCharacterMap characterMap) {
        this.mapaAlfabeto = characterMap.getMapaNormal();
    }

    public Integer obtenerPatronBraille(String letra) {
        return mapaAlfabeto.get(letra.toLowerCase());
    }

    public boolean existeLetra(String letra) {
        return mapaAlfabeto.containsKey(letra.toLowerCase());
    }
}