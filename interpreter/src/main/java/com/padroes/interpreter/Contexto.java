package com.padroes.interpreter;

import java.util.HashMap;
import java.util.Map;

public class Contexto {
    private final Map<String, Integer> variaveis = new HashMap<>();

    public void definir(String nome, int valor) {
        variaveis.put(nome, valor);
    }

    public int obter(String nome) {
        if (!variaveis.containsKey(nome)) {
            throw new IllegalArgumentException("Variável não definida: " + nome);
        }
        return variaveis.get(nome);
    }
}
