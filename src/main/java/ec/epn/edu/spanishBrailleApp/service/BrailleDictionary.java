package ec.epn.edu.spanishBrailleApp.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BrailleDictionary {

    private static final int SIGNO_NUMERO = mask(3, 4, 5, 6);

    private final Map<String, Integer> mapaNormal   = new HashMap<>();
    private final Map<String, Integer> mapaEspejo   = new HashMap<>();
    private final Map<Integer, String> mapaInverso  = new HashMap<>();

    public BrailleDictionary() {

        initLetters();
        initAccents();
        initPunctuation();
        initNumbers();
        initReverseMap();
    }

    // ===========================
    //  GETTERS (SOLO LECTURA)
    // ===========================

    public Map<String, Integer> getMapaNormal() {
        return Collections.unmodifiableMap(mapaNormal);
    }

    public Map<String, Integer> getMapaEspejo() {
        return Collections.unmodifiableMap(mapaEspejo);
    }

    public Map<Integer, String> getMapaInverso() {
        return Collections.unmodifiableMap(mapaInverso);
    }

    // ===========================
    //  INICIALIZACIÓN
    // ===========================

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

        mapaNormal.put("k", addDot(mapaNormal.get("a"), 3));
        mapaNormal.put("l", addDot(mapaNormal.get("b"), 3));
        mapaNormal.put("m", addDot(mapaNormal.get("c"), 3));
        mapaNormal.put("n", addDot(mapaNormal.get("d"), 3));
        mapaNormal.put("o", addDot(mapaNormal.get("e"), 3));
        mapaNormal.put("p", addDot(mapaNormal.get("f"), 3));
        mapaNormal.put("q", addDot(mapaNormal.get("g"), 3));
        mapaNormal.put("r", addDot(mapaNormal.get("h"), 3));
        mapaNormal.put("s", addDot(mapaNormal.get("i"), 3));
        mapaNormal.put("t", addDot(mapaNormal.get("j"), 3));
        mapaNormal.put("u", addDot(mapaNormal.get("k"), 6));
        mapaNormal.put("v", addDot(mapaNormal.get("l"), 6));
        mapaNormal.put("x", addDot(mapaNormal.get("m"), 6));
        mapaNormal.put("y", addDot(mapaNormal.get("n"), 6));
        mapaNormal.put("z", addDot(mapaNormal.get("o"), 6));
        mapaNormal.put("ñ", mask(1, 2, 4, 5, 6));
        mapaNormal.put("ü", mask(1, 2, 5, 6));

        mapaEspejo.put("k", addDot(mapaEspejo.get("a"), 6));
        mapaEspejo.put("l", addDot(mapaEspejo.get("b"), 6));
        mapaEspejo.put("m", addDot(mapaEspejo.get("c"), 6));
        mapaEspejo.put("n", addDot(mapaEspejo.get("d"), 6));
        mapaEspejo.put("o", addDot(mapaEspejo.get("e"), 6));
        mapaEspejo.put("p", addDot(mapaEspejo.get("f"), 6));
        mapaEspejo.put("q", addDot(mapaEspejo.get("g"), 6));
        mapaEspejo.put("r", addDot(mapaEspejo.get("h"), 6));
        mapaEspejo.put("s", addDot(mapaEspejo.get("i"), 6));
        mapaEspejo.put("t", addDot(mapaEspejo.get("j"), 6));
        mapaEspejo.put("u", addDot(mapaEspejo.get("k"), 3));
        mapaEspejo.put("v", addDot(mapaEspejo.get("l"), 3));
        mapaEspejo.put("x", addDot(mapaEspejo.get("m"), 3));
        mapaEspejo.put("y", addDot(mapaEspejo.get("n"), 3));
        mapaEspejo.put("z", addDot(mapaEspejo.get("o"), 3));
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
        mapaNormal.put("#", SIGNO_NUMERO);
        mapaNormal.put("1", mapaNormal.get("a"));
        mapaNormal.put("2", mapaNormal.get("b"));
        mapaNormal.put("3", mapaNormal.get("c"));
        mapaNormal.put("4", mapaNormal.get("d"));
        mapaNormal.put("5", mapaNormal.get("e"));
        mapaNormal.put("6", mapaNormal.get("f"));
        mapaNormal.put("7", mapaNormal.get("g"));
        mapaNormal.put("8", mapaNormal.get("h"));
        mapaNormal.put("9", mapaNormal.get("i"));
        mapaNormal.put("0", mapaNormal.get("j"));

        mapaEspejo.put("#", mask(1, 2, 3, 6));
        mapaEspejo.put("1", mapaEspejo.get("a"));
        mapaEspejo.put("2", mapaEspejo.get("b"));
        mapaEspejo.put("3", mapaEspejo.get("c"));
        mapaEspejo.put("4", mapaEspejo.get("d"));
        mapaEspejo.put("5", mapaEspejo.get("e"));
        mapaEspejo.put("6", mapaEspejo.get("f"));
        mapaEspejo.put("7", mapaEspejo.get("g"));
        mapaEspejo.put("8", mapaEspejo.get("h"));
        mapaEspejo.put("9", mapaEspejo.get("i"));
        mapaEspejo.put("0", mapaEspejo.get("j"));
    }

    private void initReverseMap() {
        for (Map.Entry<String, Integer> e : mapaNormal.entrySet()) {
            mapaInverso.put(e.getValue(), e.getKey());
        }
    }

    // ===========================
    //  UTILIDADES
    // ===========================

    private static int mask(int... dots) {
        int m = 0;
        for (int d : dots) m |= (1 << (d - 1));
        return m;
    }

    private static int addDot(int base, int dot) {
        return base | (1 << (dot - 1));
    }
}