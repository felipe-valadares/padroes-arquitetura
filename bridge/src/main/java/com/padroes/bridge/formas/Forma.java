package com.padroes.bridge.formas;

import com.padroes.bridge.implementacao.RenderizadorAPI;

public abstract class Forma {

    protected final RenderizadorAPI renderizador;

    protected Forma(RenderizadorAPI renderizador) {
        this.renderizador = renderizador;
    }

    public abstract String desenhar();

    public abstract Forma redimensionar(double fator);
}
