package com.padroes.command;

public class ComandoDesligarLuz implements Comando {
    private final LuzInteligente luz;

    public ComandoDesligarLuz(LuzInteligente luz) { this.luz = luz; }

    @Override public void executar() { luz.desligar(); }
    @Override public void desfazer() { luz.ligar(); }
}
