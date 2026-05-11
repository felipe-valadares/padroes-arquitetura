package com.padroes.mediator;

import java.util.ArrayList;
import java.util.List;

public class Usuario extends Participante {

    private final List<String> mensagensRecebidas = new ArrayList<>();

    public Usuario(Mediador mediador, String nome) {
        super(mediador, nome);
    }

    @Override
    public void enviar(String mensagem) {
        mediador.enviarMensagem(mensagem, this);
    }

    @Override
    public void receberMensagem(String mensagem) {
        mensagensRecebidas.add(mensagem);
    }

    public List<String> getMensagensRecebidas() {
        return List.copyOf(mensagensRecebidas);
    }
}
