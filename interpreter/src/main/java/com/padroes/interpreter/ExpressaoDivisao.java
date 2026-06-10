package com.padroes.interpreter;

public class ExpressaoDivisao implements Expressao {
    private final Expressao esquerda;
    private final Expressao direita;

    public ExpressaoDivisao(Expressao esquerda, Expressao direita) {
        this.esquerda = esquerda;
        this.direita = direita;
    }

    @Override
    public int interpretar(Contexto contexto) {
        return esquerda.interpretar(contexto) / direita.interpretar(contexto);
    }
}
