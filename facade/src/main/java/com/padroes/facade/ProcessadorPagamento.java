package com.padroes.facade;

public class ProcessadorPagamento {
    public boolean processar(String cartao, int quantidade) {
        return cartao != null && !cartao.isBlank() && quantidade > 0;
    }
}
