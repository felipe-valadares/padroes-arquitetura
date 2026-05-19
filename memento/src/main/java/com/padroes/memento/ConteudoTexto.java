package com.padroes.memento;

public class ConteudoTexto {
    private final String texto;
    private final int posicaoCursor;

    ConteudoTexto(String texto, int posicaoCursor) {
        this.texto = texto;
        this.posicaoCursor = posicaoCursor;
    }

    String getTexto() { return texto; }
    int getPosicaoCursor() { return posicaoCursor; }
}
