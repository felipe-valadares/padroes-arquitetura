package com.padroes.interpreter;

public class ExpressaoMultiplicacao implements Expressao {
    private final Expressao esquerda;
    private final Expressao direita;

    public ExpressaoMultiplicacao(Expressao esquerda, Expressao direita) {
        this.esquerda = esquerda;
        this.direita = direita;
    }

    @Override
    public int interpretar(Contexto contexto) {
        return esquerda.interpretar(contexto) * direita.interpretar(contexto);
    }
}
