package com.padroes.prototype;

public interface Personagem {
    Personagem clonar();
    String getNome();
    int getVida();
    int getAtaque();
    int getDefesa();
}
