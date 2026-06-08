package com.padroes.command;

public class ComandoLigarTelevisao implements Comando {
    private final Televisao tv;

    public ComandoLigarTelevisao(Televisao tv) { this.tv = tv; }

    @Override public void executar() { tv.ligar(); }
    @Override public void desfazer() { tv.desligar(); }
}
