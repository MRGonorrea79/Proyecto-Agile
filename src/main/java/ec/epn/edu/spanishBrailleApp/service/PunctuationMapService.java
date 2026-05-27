package ec.epn.edu.spanishBrailleApp.service;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Set;

@Service
public class PunctuationMapService {

    private static final Set<String> SIGNOS = Set.of(
            ",",";",":",".","?","!","-","(",")","+","*","="
    );

    private final Map<String, Integer> mapaPuntuacion;

    public PunctuationMapService(BrailleCharacterMap characterMap) {
        this.mapaPuntuacion = characterMap.getMapaNormal();
    }

    public Integer obtenerPatronPuntuacion(String signo) {
        return mapaPuntuacion.get(signo);
    }

    public boolean esSignoPuntuacion(String caracter) {
        return SIGNOS.contains(caracter);
    }
}