package com.padroes.templatemethod;

public class Main {

    public static void main(String[] args) {
        GeradorRelatorio vendas = new RelatorioVendas();
        System.out.println(vendas.gerar());
        System.out.println();

        GeradorRelatorio estoque = new RelatorioEstoque();
        System.out.println(estoque.gerar());
    }
}
