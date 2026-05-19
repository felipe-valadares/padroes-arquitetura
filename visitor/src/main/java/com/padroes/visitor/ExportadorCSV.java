package com.padroes.visitor;

public class ExportadorCSV implements Visitante {
    private final StringBuilder saida = new StringBuilder("tipo,descricao,valor\n");

    @Override
    public void visitar(Receita receita) {
        saida.append(String.format("RECEITA,%s,%.2f%n", receita.getDescricao(), receita.getValor()));
    }

    @Override
    public void visitar(Despesa despesa) {
        saida.append(String.format("DESPESA,%s,%.2f%n", despesa.getDescricao(), despesa.getValor()));
    }

    @Override
    public void visitar(Investimento investimento) {
        saida.append(String.format("INVESTIMENTO,%s,%.2f%n", investimento.getDescricao(), investimento.getValor()));
    }

    public String getSaida() { return saida.toString(); }
}
