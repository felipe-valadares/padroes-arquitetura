package com.padroes.visitor;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<ItemRelatorio> itens = List.of(
            new Receita("Venda de produto", 5000.00),
            new Despesa("Aluguel do escritório", 1500.00),
            new Investimento("Fundo de ações", 10000.00, 0.12)
        );

        ExportadorCSV csv = new ExportadorCSV();
        ExportadorJSON json = new ExportadorJSON();

        for (ItemRelatorio item : itens) {
            item.aceitar(csv);
            item.aceitar(json);
        }

        System.out.println("=== CSV ===");
        System.out.println(csv.getSaida());
        System.out.println("=== JSON ===");
        System.out.println(json.getSaida());
    }
}
