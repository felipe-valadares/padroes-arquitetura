package com.padroes.abstractfactory.produtos.windows;

import com.padroes.abstractfactory.produtos.CaixaSelecao;

public class CaixaSelecaoWindows implements CaixaSelecao {

    private boolean marcada = false;

    @Override
    public String renderizar() {
        return "[Windows] Checkbox " + (marcada ? "[X]" : "[ ]") + " com estilo Fluent Design";
    }

    @Override
    public boolean alternarEstado() {
        marcada = !marcada;
        return marcada;
    }

    @Override
    public boolean estaMarcada() {
        return marcada;
    }
}
