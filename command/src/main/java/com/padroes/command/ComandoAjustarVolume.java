package com.padroes.command;

public class ComandoAjustarVolume implements Comando {
    private final Televisao tv;
    private final int novoVolume;
    private int volumeAnterior;

    public ComandoAjustarVolume(Televisao tv, int novoVolume) {
        this.tv = tv;
        this.novoVolume = novoVolume;
    }

    @Override
    public void executar() {
        volumeAnterior = tv.getVolume();
        tv.setVolume(novoVolume);
    }

    @Override
    public void desfazer() { tv.setVolume(volumeAnterior); }
}
