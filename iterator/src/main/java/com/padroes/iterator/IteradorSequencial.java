package com.padroes.iterator;

import java.util.List;

class IteradorSequencial implements Iterador<Musica> {
    private final List<Musica> musicas;
    private int posicao = 0;

    IteradorSequencial(List<Musica> musicas) {
        this.musicas = musicas;
    }

    @Override
    public boolean temProximo() {
        return posicao < musicas.size();
    }

    @Override
    public Musica proximo() {
        return musicas.get(posicao++);
    }

    @Override
    public void reiniciar() {
        posicao = 0;
    }
}
