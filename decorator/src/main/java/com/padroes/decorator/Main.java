package com.padroes.decorator;

public class Main {
    public static void main(String[] args) {
        Notificacao base = new NotificacaoEmail("cliente@email.com", "Sua compra foi confirmada");

        System.out.println("=== Sem decoradores ===");
        System.out.println(base.enviar());

        System.out.println("\n=== Com Log ===");
        System.out.println(new NotificacaoComLog(base).enviar());

        System.out.println("\n=== Com Criptografia ===");
        System.out.println(new NotificacaoComCriptografia(base).enviar());

        System.out.println("\n=== Com Timestamp ===");
        System.out.println(new NotificacaoComTimestamp(base).enviar());

        System.out.println("\n=== Log + Criptografia + Timestamp (empilhados) ===");
        Notificacao completo = new NotificacaoComLog(
            new NotificacaoComCriptografia(
                new NotificacaoComTimestamp(base)
            )
        );
        System.out.println(completo.enviar());
    }
}
