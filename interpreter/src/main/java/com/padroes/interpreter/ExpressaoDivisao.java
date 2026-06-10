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
        int divisor = direita.interpretar(contexto);
        if (divisor == 0) throw new ArithmeticException("Divisão por zero");
        return esquerda.interpretar(contexto) / divisor;
    }
}
