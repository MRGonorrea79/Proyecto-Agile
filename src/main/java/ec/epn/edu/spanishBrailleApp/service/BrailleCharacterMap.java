package ec.epn.edu.spanishBrailleApp.service;

import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class BrailleCharacterMap {

    private final Map<String, Integer> mapaNormal  = new HashMap<>();
    private final Map<String, Integer> mapaEspejo  = new HashMap<>();
    private final Map<Integer, String> mapaInverso = new HashMap<>();

    public BrailleCharacterMap() {
        initLetters();
        initAccents();
        initPunctuation();
        initNumbers();
        initReverseMap();
    }

    public Map<String, Integer> getMapaNormal()  { return Collections.unmodifiableMap(mapaNormal); }
    public Map<String, Integer> getMapaEspejo()  { return Collections.unmodifiableMap(mapaEspejo); }
    public Map<Integer, String> getMapaInverso() { return Collections.unmodifiableMap(mapaInverso); }

    private void initLetters() {
        mapaNormal.put("a", mask(1));
        mapaNormal.put("b", mask(1, 2));
        mapaNormal.put("c", mask(1, 4));
        mapaNormal.put("d", mask(1, 4, 5));
        mapaNormal.put("e", mask(1, 5));
        mapaNormal.put("f", mask(1, 2, 4));
        mapaNormal.put("g", mask(1, 2, 4, 5));
        mapaNormal.put("h", mask(1, 2, 5));
        mapaNormal.put("i", mask(2, 4));
        mapaNormal.put("j", mask(2, 4, 5));

        mapaEspejo.put("a", mask(4));
        mapaEspejo.put("b", mask(4, 5));
        mapaEspejo.put("c", mask(1, 4));
        mapaEspejo.put("d", mask(1, 2, 4));
        mapaEspejo.put("e", mask(2, 4));
        mapaEspejo.put("f", mask(1, 4, 5));
        mapaEspejo.put("g", mask(1, 2, 4, 5));
        mapaEspejo.put("h", mask(2, 4, 5));
        mapaEspejo.put("i", mask(1, 5));
        mapaEspejo.put("j", mask(2, 4, 5));

        // Serie 2 (k-t): serie 1 + punto 3
        String[] serie1 = {"a","b","c","d","e","f","g","h","i","j"};
        String[] serie2 = {"k","l","m","n","o","p","q","r","s","t"};
        for (int i = 0; i < serie1.length; i++) {
            mapaNormal.put(serie2[i], addDot(mapaNormal.get(serie1[i]), 3));
            mapaEspejo.put(serie2[i], addDot(mapaEspejo.get(serie1[i]), 6));
        }

        // Serie 3 (u-z): serie 2 + punto 6
        String[] serie2n = {"k","l","m","n","o"};
        String[] serie3  = {"u","v","x","y","z"};
        for (int i = 0; i < serie2n.length; i++) {
            mapaNormal.put(serie3[i], addDot(mapaNormal.get(serie2n[i]), 6));
            mapaEspejo.put(serie3[i], addDot(mapaEspejo.get(serie2n[i]), 3));
        }

        mapaNormal.put("ñ", mask(1, 2, 4, 5, 6));
        mapaNormal.put("ü", mask(1, 2, 5, 6));
        mapaEspejo.put("ñ", mask(1, 2, 4, 5, 3));
        mapaEspejo.put("ü", mask(4, 2, 5, 3));
    }

    private void initAccents() {
        mapaNormal.put("á", mask(1, 2, 3, 5, 6));
        mapaNormal.put("é", mask(2, 3, 4, 6));
        mapaNormal.put("í", mask(3, 4));
        mapaNormal.put("ó", mask(3, 4, 6));
        mapaNormal.put("ú", mask(2, 3, 4, 5, 6));

        mapaEspejo.put("á", mask(2, 3, 4, 5, 6));
        mapaEspejo.put("é", mask(1, 3, 5, 6));
        mapaEspejo.put("í", mask(1, 6));
        mapaEspejo.put("ó", mask(3, 1, 6));
        mapaEspejo.put("ú", mask(2, 3, 1, 5, 6));
    }

    private void initPunctuation() {
        mapaNormal.put(",", mask(2));
        mapaNormal.put(";", mask(2, 3));
        mapaNormal.put(":", mask(2, 5));
        mapaNormal.put(".", mask(3));
        mapaNormal.put("?", mask(2, 6));
        mapaNormal.put("!", mask(2, 3, 5));
        mapaNormal.put("-", mask(3, 6));
        mapaNormal.put("(", mask(1, 2, 6));
        mapaNormal.put(")", mask(3, 4, 5));
        mapaNormal.put("+", mask(2, 3, 5));
        mapaNormal.put("*", mask(3, 5));
        mapaNormal.put("=", mask(2, 3, 5, 6));
        mapaNormal.put(" ", 0);

        mapaEspejo.put(",", mask(5));
        mapaEspejo.put(";", mask(5, 6));
        mapaEspejo.put(":", mask(2, 5));
        mapaEspejo.put(".", mask(6));
        mapaEspejo.put("?", mask(5, 3));
        mapaEspejo.put("!", mask(2, 5, 6));
        mapaEspejo.put("-", mask(3, 6));
        mapaEspejo.put("(", mask(3, 4, 5));
        mapaEspejo.put(")", mask(1, 2, 6));
        mapaEspejo.put("+", mask(2, 5, 6));
        mapaEspejo.put("*", mask(2, 3, 6));
        mapaEspejo.put("=", mask(2, 3, 5, 6));
        mapaEspejo.put(" ", 0);
    }

    private void initNumbers() {
        int signoNumero = mask(3, 4, 5, 6);
        mapaNormal.put("#", signoNumero);
        mapaEspejo.put("#", mask(1, 2, 3, 6));

        String[] letras  = {"a","b","c","d","e","f","g","h","i","j"};
        String[] digitos = {"1","2","3","4","5","6","7","8","9","0"};
        for (int i = 0; i < letras.length; i++) {
            mapaNormal.put(digitos[i], mapaNormal.get(letras[i]));
            mapaEspejo.put(digitos[i], mapaEspejo.get(letras[i]));
        }
    }

    private void initReverseMap() {
        for (Map.Entry<String, Integer> e : mapaNormal.entrySet()) {
            mapaInverso.put(e.getValue(), e.getKey());
        }
    }

    public static int mask(int... dots) {
        int m = 0;
        for (int d : dots) m |= (1 << (d - 1));
        return m;
    }

    public static int addDot(int base, int dot) {
        return base | (1 << (dot - 1));
    }
}