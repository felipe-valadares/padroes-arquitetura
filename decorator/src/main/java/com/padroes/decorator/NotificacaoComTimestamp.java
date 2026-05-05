package com.padroes.decorator;

public class NotificacaoComTimestamp extends DecoradorNotificacao {
    public NotificacaoComTimestamp(Notificacao notificacao) {
        super(notificacao);
    }

    @Override
    public String enviar() {
        return notificacao.enviar() + " [ts:" + System.currentTimeMillis() + "]";
    }
}
