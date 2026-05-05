package com.padroes.observer.observadores;

import com.padroes.observer.Observador;

public class InvestidorPJ implements Observador {
    private final String nome;
    private final int loteMinimo;
    private String ultimoCalculo;

    public InvestidorPJ(String nome, int loteMinimo) {
        this.nome = nome;
        this.loteMinimo = loteMinimo;
    }

    @Override
    public void atualizar(String acao, double preco) {
        double valorLote = preco * loteMinimo;
        ultimoCalculo = String.format("%s: lote de %d %s = R$%.2f", nome, loteMinimo, acao, valorLote);
    }

    public String getUltimoCalculo() { return ultimoCalculo; }
    public String getNome()          { return nome; }
}
