package com.padroes.flyweight;

import java.util.HashMap;
import java.util.Map;

public class FabricaArvores {
    private final Map<String, TipoArvore> cache = new HashMap<>();

    public TipoArvore obter(String especie, String cor, String textura) {
        String chave = especie + "#" + cor + "#" + textura;
        return cache.computeIfAbsent(chave, k -> new TipoArvore(especie, cor, textura));
    }

    public int totalTipos() {
        return cache.size();
    }
}
