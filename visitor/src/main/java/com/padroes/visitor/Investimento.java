package com.padroes.visitor;

public class Investimento implements ItemRelatorio {
    private final String descricao;
    private final double valor;
    private final double taxaRetorno;

    public Investimento(String descricao, double valor, double taxaRetorno) {
        this.descricao = descricao;
        this.valor = valor;
        this.taxaRetorno = taxaRetorno;
    }

    @Override public String getDescricao() { return descricao; }
    @Override public double getValor() { return valor; }
    public double getTaxaRetorno() { return taxaRetorno; }
    @Override public void aceitar(Visitante visitante) { visitante.visitar(this); }
}
