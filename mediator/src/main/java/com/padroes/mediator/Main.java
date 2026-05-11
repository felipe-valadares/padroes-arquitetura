package com.padroes.mediator;

public class Main {

    public static void main(String[] args) {
        SalaDeChat sala = new SalaDeChat();

        Usuario alice = new Usuario(sala, "Alice");
        Usuario bob = new Usuario(sala, "Bob");
        Usuario carlos = new Usuario(sala, "Carlos");

        sala.registrar(alice);
        sala.registrar(bob);
        sala.registrar(carlos);

        alice.enviar("Olá a todos!");
        bob.enviar("Oi Alice!");

        System.out.println("Mensagens recebidas por Bob: " + bob.getMensagensRecebidas());
        System.out.println("Mensagens recebidas por Carlos: " + carlos.getMensagensRecebidas());
    }
}
