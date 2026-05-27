package com.padroes.flyweight;

public class Arvore {
    private final int x;
    private final int y;
    private final int altura;
    private final TipoArvore tipo;

    public Arvore(int x, int y, int altura, TipoArvore tipo) {
        this.x = x;
        this.y = y;
        this.altura = altura;
        this.tipo = tipo;
    }

    public void renderizar() {
        tipo.renderizar(x, y, altura);
    }

    public TipoArvore getTipo() { return tipo; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getAltura() { return altura; }
}
