package com.padroes.strategy;

import java.util.UUID;

public class PagamentoPIX implements EstrategiaPagamento {
    @Override
    public String processar(double valor) {
        String codigo = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        return String.format("PIX: R$%.2f — código %s — aprovação instantânea", valor, codigo);
    }

    @Override
    public String getNome() { return "PIX"; }
}
