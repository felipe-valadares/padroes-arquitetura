package com.padroes.memento;

public class EditorTexto {
    private StringBuilder texto = new StringBuilder();
    private int posicaoCursor = 0;

    public void escrever(String conteudo) {
        texto.insert(posicaoCursor, conteudo);
        posicaoCursor += conteudo.length();
    }

    public void apagar(int quantidade) {
        int inicio = Math.max(0, posicaoCursor - quantidade);
        texto.delete(inicio, posicaoCursor);
        posicaoCursor = inicio;
    }

    public String getTexto() { return texto.toString(); }
    public int getPosicaoCursor() { return posicaoCursor; }

    public ConteudoTexto salvar() {
        return new ConteudoTexto(texto.toString(), posicaoCursor);
    }

    public void restaurar(ConteudoTexto memento) {
        this.texto = new StringBuilder(memento.getTexto());
        this.posicaoCursor = memento.getPosicaoCursor();
    }
}
