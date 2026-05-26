package ec.epn.edu.spanishBrailleApp.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BrailleDictionary {

    private static final int SIGNO_NUMERO = mask(3, 4, 5, 6);

    private final Map<String, Integer> map = new HashMap<>();

    public BrailleDictionary() {
        initLetters();
        initAccents();
        initPunctuation();
        initNumbers();
    }

    // ===========================
    //  GETTER (SOLO LECTURA)
    // ===========================

    public Map<String, Integer> getMap() {
        return Collections.unmodifiableMap(map);
    }

    // ===========================
    //  INICIALIZACIÓN
    // ===========================

    private void initLetters() {
        map.put("a", mask(1));
        map.put("b", mask(1, 2));
        map.put("c", mask(1, 4));
        map.put("d", mask(1, 4, 5));
        map.put("e", mask(1, 5));
        map.put("f", mask(1, 2, 4));
        map.put("g", mask(1, 2, 4, 5));
        map.put("h", mask(1, 2, 5));
        map.put("i", mask(2, 4));
        map.put("j", mask(2, 4, 5));

        map.put("k", addDot(map.get("a"), 3));
        map.put("l", addDot(map.get("b"), 3));
        map.put("m", addDot(map.get("c"), 3));
        map.put("n", addDot(map.get("d"), 3));
        map.put("o", addDot(map.get("e"), 3));
        map.put("p", addDot(map.get("f"), 3));
        map.put("q", addDot(map.get("g"), 3));
        map.put("r", addDot(map.get("h"), 3));
        map.put("s", addDot(map.get("i"), 3));
        map.put("t", addDot(map.get("j"), 3));
        map.put("u", addDot(map.get("k"), 6));
        map.put("v", addDot(map.get("l"), 6));
        map.put("x", addDot(map.get("m"), 6));
        map.put("y", addDot(map.get("n"), 6));
        map.put("z", addDot(map.get("o"), 6));

        map.put("ñ", mask(1, 2, 4, 5, 6));
        map.put("ü", mask(1, 2, 5, 6));
    }

    private void initAccents() {
        map.put("á", mask(1, 2, 3, 5, 6));
        map.put("é", mask(2, 3, 4, 6));
        map.put("í", mask(3, 4));
        map.put("ó", mask(3, 4, 6));
        map.put("ú", mask(2, 3, 4, 5, 6));
    }

    private void initPunctuation() {
        map.put(",", mask(2));
        map.put(";", mask(2, 3));
        map.put(":", mask(2, 5));
        map.put(".", mask(3));
        map.put("?", mask(2, 6));
        map.put("!", mask(2, 3, 5));
        map.put("-", mask(3, 6));
        map.put("(", mask(1, 2, 6));
        map.put(")", mask(3, 4, 5));
        map.put("+", mask(2, 3, 5));
        map.put("*", mask(3, 5));
        map.put("=", mask(2, 3, 5, 6));
        map.put(" ", 0);
    }

    private void initNumbers() {
        map.put("#", SIGNO_NUMERO);
        map.put("1", map.get("a"));
        map.put("2", map.get("b"));
        map.put("3", map.get("c"));
        map.put("4", map.get("d"));
        map.put("5", map.get("e"));
        map.put("6", map.get("f"));
        map.put("7", map.get("g"));
        map.put("8", map.get("h"));
        map.put("9", map.get("i"));
        map.put("0", map.get("j"));
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