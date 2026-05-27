package com.padroes.flyweight;

import java.util.ArrayList;
import java.util.List;

public class Floresta {
    private final List<Arvore> arvores = new ArrayList<>();
    private final FabricaArvores fabrica = new FabricaArvores();

    public void plantarArvore(int x, int y, int altura, String especie, String cor, String textura) {
        TipoArvore tipo = fabrica.obter(especie, cor, textura);
        arvores.add(new Arvore(x, y, altura, tipo));
    }

    public void renderizar() {
        arvores.forEach(Arvore::renderizar);
    }

    public int totalArvores() { return arvores.size(); }
    public int totalTipos() { return fabrica.totalTipos(); }
}
