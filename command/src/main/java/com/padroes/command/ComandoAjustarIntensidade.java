package com.padroes.command;

public class ComandoAjustarIntensidade implements Comando {
    private final LuzInteligente luz;
    private final int novaIntensidade;
    private int intensidadeAnterior;

    public ComandoAjustarIntensidade(LuzInteligente luz, int novaIntensidade) {
        this.luz = luz;
        this.novaIntensidade = novaIntensidade;
    }

    @Override
    public void executar() {
        intensidadeAnterior = luz.getIntensidade();
        luz.setIntensidade(novaIntensidade);
    }

    @Override
    public void desfazer() { luz.setIntensidade(intensidadeAnterior); }
}
