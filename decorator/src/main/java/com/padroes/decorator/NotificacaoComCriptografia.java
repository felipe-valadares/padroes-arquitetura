package com.padroes.decorator;

public class NotificacaoComCriptografia extends DecoradorNotificacao {
    public NotificacaoComCriptografia(Notificacao notificacao) {
        super(notificacao);
    }

    @Override
    public String enviar() {
        return notificacao.enviar().replaceAll("[aeiouAEIOU]", "*");
    }
}
