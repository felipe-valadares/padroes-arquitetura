package com.padroes.abstractfactory.fabricas;

import com.padroes.abstractfactory.produtos.Botao;
import com.padroes.abstractfactory.produtos.CaixaSelecao;
import com.padroes.abstractfactory.produtos.windows.BotaoWindows;
import com.padroes.abstractfactory.produtos.windows.CaixaSelecaoWindows;

public class FabricaWindows implements FabricaUI {

    @Override
    public Botao criarBotao() {
        return new BotaoWindows();
    }

    @Override
    public CaixaSelecao criarCaixaSelecao() {
        return new CaixaSelecaoWindows();
    }
}
