package com.padroes.observer;

public interface Observavel {
    void adicionar(Observador observador);
    void remover(Observador observador);
    void notificar();
}
