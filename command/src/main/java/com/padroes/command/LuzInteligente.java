package com.padroes.command;

public class LuzInteligente {
    private boolean ligada = false;
    private int intensidade = 100;

    public void ligar() { ligada = true; }
    public void desligar() { ligada = false; }

    public void setIntensidade(int intensidade) {
        this.intensidade = Math.max(0, Math.min(100, intensidade));
    }

    public int getIntensidade() { return intensidade; }
    public boolean isLigada() { return ligada; }
}
