package com.padroes.bridge.formas;

import com.padroes.bridge.implementacao.RenderizadorAPI;

public class Circulo extends Forma {

    private final double x;
    private final double y;
    private final double raio;

    public Circulo(double x, double y, double raio, RenderizadorAPI renderizador) {
        super(renderizador);
        this.x = x;
        this.y = y;
        this.raio = raio;
    }

    @Override
    public String desenhar() {
        return renderizador.renderizarCirculo(x, y, raio);
    }

    @Override
    public Forma redimensionar(double fator) {
        return new Circulo(x, y, raio * fator, renderizador);
    }

    public double getRaio() {
        return raio;
    }
}
