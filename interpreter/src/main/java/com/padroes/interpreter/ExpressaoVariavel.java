package com.padroes.interpreter;

public class ExpressaoVariavel implements Expressao {
    private final String nome;

    public ExpressaoVariavel(String nome) {
        this.nome = nome;
    }

    @Override
    public int interpretar(Contexto contexto) {
        return contexto.obter(nome);
    }
}
