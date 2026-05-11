package com.padroes.builder;

public class Main {

    public static void main(String[] args) {
        Montador montadorGamer = new Montador(new ComputadorGamerBuilder());
        Computador gamer = montadorGamer.montarComputadorCompleto();
        System.out.println("Computador Gamer: " + gamer);

        Montador montadorEscritorio = new Montador(new ComputadorEscritorioBuilder());
        Computador escritorio = montadorEscritorio.montarComputadorCompleto();
        System.out.println("Computador Escritório: " + escritorio);
    }
}
