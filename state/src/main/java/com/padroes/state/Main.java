package com.padroes.state;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Fluxo de pedido completo ===");
        var pedido = new Pedido();
        System.out.println("Estado: " + pedido.getEstado());
        pedido.pagar();
        System.out.println("Após pagar: " + pedido.getEstado());
        pedido.enviar();
        System.out.println("Após enviar: " + pedido.getEstado());
        pedido.entregar();
        System.out.println("Após entregar: " + pedido.getEstado());

        System.out.println("\n=== Histórico ===");
        pedido.getHistorico().forEach(System.out::println);

        System.out.println("\n=== Cancelamento ===");
        var pedido2 = new Pedido();
        pedido2.pagar();
        pedido2.cancelar();
        System.out.println("Estado: " + pedido2.getEstado());

        System.out.println("\n=== Transição inválida ===");
        var pedido3 = new Pedido();
        try {
            pedido3.enviar();
        } catch (IllegalStateException e) {
            System.out.println("Erro esperado: " + e.getMessage());
        }
    }
}
