package com.padroes.abstractfactory.produtos.mac;

import com.padroes.abstractfactory.produtos.CaixaSelecao;

public class CaixaSelecaoMac implements CaixaSelecao {

    private boolean marcada = false;

    @Override
    public String renderizar() {
        return "[macOS] Checkbox " + (marcada ? "✓" : "○") + " com estilo Aqua";
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
