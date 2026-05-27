package ec.epn.edu.spanishBrailleApp.service;

import org.springframework.stereotype.Service;

@Service
public class UpperCaseHandlerService {

    private static final int SIGNO_MAYUSCULA = BrailleCharacterMap.mask(4, 6);

    public boolean esMayuscula(char caracter) {
        return Character.isUpperCase(caracter);
    }

    public Integer obtenerIndicadorMayuscula() {
        return SIGNO_MAYUSCULA;
    }

    public String convertirAMinuscula(String caracter) {
        return caracter.toLowerCase();
    }
}