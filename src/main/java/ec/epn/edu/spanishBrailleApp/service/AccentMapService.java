package ec.epn.edu.spanishBrailleApp.service;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Set;

@Service
public class AccentMapService {

    private static final Set<String> ACENTUADAS = Set.of("á","é","í","ó","ú");

    private final Map<String, Integer> mapaAcentos;

    public AccentMapService(BrailleCharacterMap characterMap) {
        this.mapaAcentos = characterMap.getMapaNormal();
    }

    public Integer obtenerPatronAcento(String caracter) {
        return mapaAcentos.get(caracter);
    }

    public boolean esCaracterAcentuado(String caracter) {
        return ACENTUADAS.contains(caracter);
    }
}