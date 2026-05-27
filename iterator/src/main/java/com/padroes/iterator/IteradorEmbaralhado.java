package com.padroes.iterator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class IteradorEmbaralhado implements Iterador<Musica> {
    private final List<Musica> embaralhadas;
    private final Random random;
    private int posicao = 0;

    IteradorEmbaralhado(List<Musica> musicas, Random random) {
        this.random = random;
        this.embaralhadas = new ArrayList<>(musicas);
        Collections.shuffle(this.embaralhadas, random);
    }

    @Override
    public boolean temProximo() {
        return posicao < embaralhadas.size();
    }

    @Override
    public Musica proximo() {
        return embaralhadas.get(posicao++);
    }

    @Override
    public void reiniciar() {
        posicao = 0;
        Collections.shuffle(embaralhadas, random);
    }
}
