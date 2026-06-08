package com.padroes.adapter;

public class Main {
    public static void main(String[] args) {
        Pagamento pagamento = new Pagamento(150.00, "Curso de Java", "vendedor@exemplo.com");

        System.out.println("=== PagSeguro ===");
        ProcessadorPagamento pagSeguro = new AdaptadorPagSeguro(new PagSeguroCliente());
        System.out.println(pagSeguro.processar(pagamento));

        System.out.println("\n=== Stripe ===");
        ProcessadorPagamento stripe = new AdaptadorStripe(new StripeCliente());
        System.out.println(stripe.processar(pagamento));
    }
}
