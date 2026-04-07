package com.padroes.abstractfactory.produtos.mac;

import com.padroes.abstractfactory.produtos.Botao;

public class BotaoMac implements Botao {

    @Override
    public String renderizar() {
        return "[macOS] Renderizando botão com cantos arredondados e tema Aqua";
    }

    @Override
    public String clicar() {
        return "[macOS] Botão clicado: animação de bounce macOS ativada";
    }
}
