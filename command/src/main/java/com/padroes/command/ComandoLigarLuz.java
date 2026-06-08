package com.padroes.command;

public class ComandoLigarLuz implements Comando {
    private final LuzInteligente luz;

    public ComandoLigarLuz(LuzInteligente luz) { this.luz = luz; }

    @Override public void executar() { luz.ligar(); }
    @Override public void desfazer() { luz.desligar(); }
}
