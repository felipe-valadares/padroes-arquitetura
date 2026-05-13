package com.padroes.facade;

public class ResultadoCompra {
    private final boolean sucesso;
    private final String mensagem;

    public ResultadoCompra(boolean sucesso, String mensagem) {
        this.sucesso = sucesso;
        this.mensagem = mensagem;
    }

    public boolean isSucesso() {
        return sucesso;
    }

    public String getMensagem() {
        return mensagem;
    }
}
