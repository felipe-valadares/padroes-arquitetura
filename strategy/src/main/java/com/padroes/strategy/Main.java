package com.padroes.strategy;

public class Main {
    public static void main(String[] args) {
        var carrinho = new CarrinhoCompras();
        carrinho.adicionarItem("Notebook", 3000.0);
        carrinho.adicionarItem("Mouse sem fio", 150.0);
        carrinho.adicionarItem("Mousepad XL", 80.0);

        System.out.println("Itens no carrinho:");
        carrinho.getItens().forEach(i ->
            System.out.printf("  %-20s R$%.2f%n", i.nome(), i.preco()));
        System.out.printf("Total: R$%.2f%n%n", carrinho.calcularTotal());

        System.out.println("=== Pagamento via PIX ===");
        carrinho.setEstrategia(new PagamentoPIX());
        System.out.println(carrinho.finalizarCompra());

        System.out.println("\n=== Pagamento via Boleto ===");
        carrinho.setEstrategia(new PagamentoBoleto());
        System.out.println(carrinho.finalizarCompra());

        System.out.println("\n=== Pagamento via Crédito (6x) ===");
        carrinho.setEstrategia(new PagamentoCredito(6));
        System.out.println(carrinho.finalizarCompra());
    }
}
