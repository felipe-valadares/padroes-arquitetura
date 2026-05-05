package com.padroes.decorator;

public abstract class DecoradorNotificacao implements Notificacao {
    protected final Notificacao notificacao;

    protected DecoradorNotificacao(Notificacao notificacao) {
        this.notificacao = notificacao;
    }

    @Override
    public String enviar() {
        return notificacao.enviar();
    }
}
