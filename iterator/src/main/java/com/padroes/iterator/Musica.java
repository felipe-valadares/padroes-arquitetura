package com.padroes.iterator;

public class Musica {
    private final String titulo;
    private final String artista;
    private final int duracaoSegundos;

    public Musica(String titulo, String artista, int duracaoSegundos) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracaoSegundos = duracaoSegundos;
    }

    public String getTitulo() { return titulo; }
    public String getArtista() { return artista; }
    public int getDuracaoSegundos() { return duracaoSegundos; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Musica m)) return false;
        return duracaoSegundos == m.duracaoSegundos
                && titulo.equals(m.titulo)
                && artista.equals(m.artista);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(titulo, artista, duracaoSegundos);
    }

    @Override
    public String toString() {
        return String.format("%s — %s (%ds)", titulo, artista, duracaoSegundos);
    }
}
