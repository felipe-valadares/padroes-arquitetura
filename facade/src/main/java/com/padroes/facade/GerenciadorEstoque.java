package com.padroes.facade;

import java.util.HashMap;
import java.util.Map;

public class GerenciadorEstoque {
    private final Map<String, Integer> estoque = new HashMap<>();

    public GerenciadorEstoque() {
        estoque.put("Notebook", 10);
        estoque.put("Mouse", 50);
        estoque.put("Teclado", 30);
    }

    public boolean verificarDisponibilidade(String produto, int quantidade) {
        return estoque.getOrDefault(produto, 0) >= quantidade;
    }

    public void reservar(String produto, int quantidade) {
        estoque.merge(produto, -quantidade, Integer::sum);
    }
}
