package com.padroes.abstractfactory.fabricas;

import com.padroes.abstractfactory.produtos.Botao;
import com.padroes.abstractfactory.produtos.CaixaSelecao;
import com.padroes.abstractfactory.produtos.mac.BotaoMac;
import com.padroes.abstractfactory.produtos.mac.CaixaSelecaoMac;

public class FabricaMac implements FabricaUI {

    @Override
    public Botao criarBotao() {
        return new BotaoMac();
    }

    @Override
    public CaixaSelecao criarCaixaSelecao() {
        return new CaixaSelecaoMac();
    }
}
