package com.padroes.iterator;

public interface Iterador<T> {
    boolean temProximo();
    T proximo();
    void reiniciar();
}
