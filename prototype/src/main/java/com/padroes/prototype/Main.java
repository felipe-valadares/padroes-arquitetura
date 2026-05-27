package com.padroes.prototype;

public class Main {
    public static void main(String[] args) {
        RegistroPersonagens registro = new RegistroPersonagens();
        registro.registrar("guerreiro", new Guerreiro("Guerreiro", 150, 80, 100));
        registro.registrar("mago",      new Mago("Mago", 80, 150, 40));
        registro.registrar("arqueiro",  new Arqueiro("Arqueiro", 100, 100, 70, 30));

        Guerreiro g1 = (Guerreiro) registro.obter("guerreiro");
        Guerreiro g2 = (Guerreiro) registro.obter("guerreiro");
        g1.setNome("Thorin");
        g2.setNome("Aragorn");

        System.out.println("Guerreiro 1 : " + g1.getNome() + " | Vida: " + g1.getVida());
        System.out.println("Guerreiro 2 : " + g2.getNome() + " | Vida: " + g2.getVida());

        Mago mago = (Mago) registro.obter("mago");
        System.out.println("Mago        : " + mago.getNome() + " | Ataque: " + mago.getAtaque());

        Arqueiro arqueiro = (Arqueiro) registro.obter("arqueiro");
        System.out.println("Arqueiro    : " + arqueiro.getNome() + " | Alcance: " + arqueiro.getAlcance());
    }
}
