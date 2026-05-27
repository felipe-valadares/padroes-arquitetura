package com.padroes.iterator;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Playlist playlist = new Playlist();
        playlist.adicionar(new Musica("Bohemian Rhapsody", "Queen", 354));
        playlist.adicionar(new Musica("Hotel California", "Eagles", 391));
        playlist.adicionar(new Musica("Stairway to Heaven", "Led Zeppelin", 482));
        playlist.adicionar(new Musica("Smells Like Teen Spirit", "Nirvana", 301));
        playlist.adicionar(new Musica("Imagine", "John Lennon", 187));

        System.out.println("=== Sequencial ===");
        Iterador<Musica> sequencial = playlist.criarIterador();
        while (sequencial.temProximo()) System.out.println(sequencial.proximo());

        System.out.println("\n=== Embaralhado ===");
        Iterador<Musica> embaralhado = playlist.criarIteradorEmbaralhado(new Random(42));
        while (embaralhado.temProximo()) System.out.println(embaralhado.proximo());
    }
}
