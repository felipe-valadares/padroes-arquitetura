package com.padroes.bridge.formas;

import com.padroes.bridge.implementacao.RenderizadorAPI;

public class Retangulo extends Forma {

    private final double x;
    private final double y;
    private final double largura;
    private final double altura;

    public Retangulo(double x, double y, double largura, double altura, RenderizadorAPI renderizador) {
        super(renderizador);
        this.x = x;
        this.y = y;
        this.largura = largura;
        this.altura = altura;
    }

    @Override
    public String desenhar() {
        return renderizador.renderizarRetangulo(x, y, largura, altura);
    }

    @Override
    public Forma redimensionar(double fator) {
        return new Retangulo(x, y, largura * fator, altura * fator, renderizador);
    }

    public double getLargura() {
        return largura;
    }

    public double getAltura() {
        return altura;
    }
}
