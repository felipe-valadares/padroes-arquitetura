package com.padroes.iterator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Playlist implements Agregado<Musica> {
    private final List<Musica> musicas = new ArrayList<>();

    public void adicionar(Musica musica) {
        musicas.add(musica);
    }

    public int tamanho() { return musicas.size(); }

    @Override
    public Iterador<Musica> criarIterador() {
        return new IteradorSequencial(musicas);
    }

    public Iterador<Musica> criarIteradorEmbaralhado(Random random) {
        return new IteradorEmbaralhado(musicas, random);
    }
}
