package com.padroes.command;

import java.util.ArrayDeque;
import java.util.Deque;

public class ControleRemoto {
    private final Deque<Comando> historico = new ArrayDeque<>();

    public void executar(Comando comando) {
        comando.executar();
        historico.push(comando);
    }

    public void desfazer() {
        if (!historico.isEmpty()) {
            historico.pop().desfazer();
        }
    }

    public boolean temHistorico() { return !historico.isEmpty(); }
}
