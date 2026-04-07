package com.padroes.abstractfactory.produtos.windows;

import com.padroes.abstractfactory.produtos.Botao;

public class BotaoWindows implements Botao {

    @Override
    public String renderizar() {
        return "[Windows] Renderizando botão com bordas quadradas e tema Fluent Design";
    }

    @Override
    public String clicar() {
        return "[Windows] Botão clicado: efeito de pressionamento Windows ativado";
    }
}
