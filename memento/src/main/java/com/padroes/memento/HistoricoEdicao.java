package com.padroes.memento;

import java.util.ArrayDeque;
import java.util.Deque;

public class HistoricoEdicao {
    private final EditorTexto editor;
    private final Deque<ConteudoTexto> historico = new ArrayDeque<>();
    private final Deque<ConteudoTexto> futuros = new ArrayDeque<>();

    public HistoricoEdicao(EditorTexto editor) {
        this.editor = editor;
    }

    public void salvar() {
        historico.push(editor.salvar());
        futuros.clear();
    }

    public boolean podeDesfazer() { return !historico.isEmpty(); }
    public boolean podeRefazer() { return !futuros.isEmpty(); }

    public void desfazer() {
        if (!podeDesfazer()) return;
        futuros.push(editor.salvar());
        editor.restaurar(historico.pop());
    }

    public void refazer() {
        if (!podeRefazer()) return;
        historico.push(editor.salvar());
        editor.restaurar(futuros.pop());
    }
}
