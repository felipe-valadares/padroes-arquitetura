package com.padroes.command;

public class ComandoDesligarTelevisao implements Comando {
    private final Televisao tv;

    public ComandoDesligarTelevisao(Televisao tv) { this.tv = tv; }

    @Override public void executar() { tv.desligar(); }
    @Override public void desfazer() { tv.ligar(); }
}
