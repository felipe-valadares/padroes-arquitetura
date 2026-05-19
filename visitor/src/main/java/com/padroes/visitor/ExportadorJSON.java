package com.padroes.visitor;

import java.util.ArrayList;
import java.util.List;

public class ExportadorJSON implements Visitante {
    private final List<String> itens = new ArrayList<>();

    @Override
    public void visitar(Receita receita) {
        itens.add(String.format("{\"tipo\":\"RECEITA\",\"descricao\":\"%s\",\"valor\":%.2f}",
                receita.getDescricao(), receita.getValor()));
    }

    @Override
    public void visitar(Despesa despesa) {
        itens.add(String.format("{\"tipo\":\"DESPESA\",\"descricao\":\"%s\",\"valor\":%.2f}",
                despesa.getDescricao(), despesa.getValor()));
    }

    @Override
    public void visitar(Investimento investimento) {
        itens.add(String.format("{\"tipo\":\"INVESTIMENTO\",\"descricao\":\"%s\",\"valor\":%.2f,\"taxaRetorno\":%.2f}",
                investimento.getDescricao(), investimento.getValor(), investimento.getTaxaRetorno()));
    }

    public String getSaida() {
        return "[" + String.join(",", itens) + "]";
    }
}
