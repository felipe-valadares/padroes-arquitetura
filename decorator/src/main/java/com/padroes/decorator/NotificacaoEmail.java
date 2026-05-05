package com.padroes.decorator;

public class NotificacaoEmail implements Notificacao {
    private final String destinatario;
    private final String mensagem;

    public NotificacaoEmail(String destinatario, String mensagem) {
        this.destinatario = destinatario;
        this.mensagem = mensagem;
    }

    @Override
    public String enviar() {
        return "Email para " + destinatario + ": " + mensagem;
    }
}
