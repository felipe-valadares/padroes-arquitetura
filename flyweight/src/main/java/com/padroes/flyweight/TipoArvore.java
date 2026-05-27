package com.padroes.flyweight;

public class TipoArvore {
    private final String especie;
    private final String cor;
    private final String textura;

    public TipoArvore(String especie, String cor, String textura) {
        this.especie = especie;
        this.cor = cor;
        this.textura = textura;
    }

    public void renderizar(int x, int y, int altura) {
        System.out.printf("Árvore[%s] cor=%s textura=%s em (%d,%d) altura=%d%n",
                especie, cor, textura, x, y, altura);
    }

    public String getEspecie() { return especie; }
    public String getCor() { return cor; }
    public String getTextura() { return textura; }
}
