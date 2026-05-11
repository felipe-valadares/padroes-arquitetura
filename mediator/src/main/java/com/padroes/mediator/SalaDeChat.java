package com.padroes.mediator;

import java.util.ArrayList;
import java.util.List;

public class SalaDeChat implements Mediador {

    private final List<Participante> participantes = new ArrayList<>();

    @Override
    public void registrar(Participante participante) {
        participantes.add(participante);
    }

    @Override
    public void enviarMensagem(String mensagem, Participante remetente) {
        for (Participante p : participantes) {
            if (p != remetente) {
                p.receberMensagem(remetente.getNome() + ": " + mensagem);
            }
        }
    }
}
