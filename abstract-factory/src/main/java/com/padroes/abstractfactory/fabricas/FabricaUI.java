package com.padroes.abstractfactory.fabricas;

import com.padroes.abstractfactory.produtos.Botao;
import com.padroes.abstractfactory.produtos.CaixaSelecao;

public interface FabricaUI {
    Botao criarBotao();
    CaixaSelecao criarCaixaSelecao();
}
