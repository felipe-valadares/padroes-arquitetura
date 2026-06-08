package com.padroes.command;

public class Televisao {
    private boolean ligada = false;
    private int volume = 10;

    public void ligar() { ligada = true; }
    public void desligar() { ligada = false; }

    public void setVolume(int volume) {
        this.volume = Math.max(0, Math.min(100, volume));
    }

    public int getVolume() { return volume; }
    public boolean isLigada() { return ligada; }
}
