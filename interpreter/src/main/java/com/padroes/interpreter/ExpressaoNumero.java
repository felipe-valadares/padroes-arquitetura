package com.padroes.interpreter;

public class ExpressaoNumero implements Expressao {
    private final int valor;

    public ExpressaoNumero(int valor) {
        this.valor = valor;
    }

    @Override
    public int interpretar(Contexto contexto) {
        return valor;
    }
}
