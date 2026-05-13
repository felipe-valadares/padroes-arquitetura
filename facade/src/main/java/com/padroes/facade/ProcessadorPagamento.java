package com.padroes.facade;

public class ProcessadorPagamento {
    public boolean processar(String cartao, double valor) {
        return cartao != null && !cartao.isBlank() && valor > 0;
    }
}
