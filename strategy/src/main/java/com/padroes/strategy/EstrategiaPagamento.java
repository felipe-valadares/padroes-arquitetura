package com.padroes.strategy;

public interface EstrategiaPagamento {
    String processar(double valor);
    String getNome();
}
