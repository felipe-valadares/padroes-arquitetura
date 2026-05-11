package com.padroes.mediator;

public interface Mediador {
    void registrar(Participante participante);
    void enviarMensagem(String mensagem, Participante remetente);
}
