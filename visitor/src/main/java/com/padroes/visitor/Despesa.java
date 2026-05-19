package com.padroes.visitor;

public class Despesa implements ItemRelatorio {
    private final String descricao;
    private final double valor;

    public Despesa(String descricao, double valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    @Override public String getDescricao() { return descricao; }
    @Override public double getValor() { return valor; }
    @Override public void aceitar(Visitante visitante) { visitante.visitar(this); }
}
