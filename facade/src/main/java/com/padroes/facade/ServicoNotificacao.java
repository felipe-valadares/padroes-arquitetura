package com.padroes.facade;

public class ServicoNotificacao {
    private String ultimaNotificacao;

    public void enviarConfirmacao(String email, String produto) {
        ultimaNotificacao = "Confirmação enviada para " + email + ": " + produto;
    }

    public String getUltimaNotificacao() {
        return ultimaNotificacao;
    }
}
