package com.padroes.command;

public class Main {
    public static void main(String[] args) {
        Televisao tv = new Televisao();
        LuzInteligente luz = new LuzInteligente();
        ControleRemoto controle = new ControleRemoto();

        System.out.println("=== Controlando TV ===");
        controle.executar(new ComandoLigarTelevisao(tv));
        System.out.println("TV ligada: " + tv.isLigada());
        controle.executar(new ComandoAjustarVolume(tv, 45));
        System.out.println("Volume: " + tv.getVolume());
        controle.desfazer();
        System.out.println("Após desfazer (volume): " + tv.getVolume());
        controle.desfazer();
        System.out.println("Após desfazer (ligada): " + tv.isLigada());

        System.out.println("\n=== Controlando Luz ===");
        controle.executar(new ComandoLigarLuz(luz));
        System.out.println("Luz ligada: " + luz.isLigada());
        controle.executar(new ComandoAjustarIntensidade(luz, 40));
        System.out.println("Intensidade: " + luz.getIntensidade());
        controle.desfazer();
        System.out.println("Após desfazer (intensidade): " + luz.getIntensidade());
    }
}
