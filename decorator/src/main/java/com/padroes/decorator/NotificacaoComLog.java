package com.padroes.decorator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NotificacaoComLog extends DecoradorNotificacao {
    private static final DateTimeFormatter FORMATTER =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public NotificacaoComLog(Notificacao notificacao) {
        super(notificacao);
    }

    @Override
    public String enviar() {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        return "[LOG " + timestamp + "] " + notificacao.enviar();
    }
}
