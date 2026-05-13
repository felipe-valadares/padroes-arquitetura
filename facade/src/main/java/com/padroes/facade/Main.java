package com.padroes.facade;

public class Main {
    public static void main(String[] args) {
        LojaFacade loja = new LojaFacade(
            new GerenciadorEstoque(),
            new ProcessadorPagamento(),
            new ServicoNotificacao()
        );

        ResultadoCompra compra = loja.realizarCompra("Notebook", 1, "4111-1111-1111-1111", "cliente@email.com");
        System.out.println(compra.getMensagem());

        ResultadoCompra semEstoque = loja.realizarCompra("Monitor", 1, "4111-1111-1111-1111", "cliente@email.com");
        System.out.println(semEstoque.getMensagem());

        ResultadoCompra pagamentoRecusado = loja.realizarCompra("Mouse", 1, "", "cliente@email.com");
        System.out.println(pagamentoRecusado.getMensagem());
    }
}
