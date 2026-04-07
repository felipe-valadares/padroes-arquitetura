package com.padroes.abstractfactory;

import com.padroes.abstractfactory.fabricas.FabricaUI;
import com.padroes.abstractfactory.produtos.Botao;
import com.padroes.abstractfactory.produtos.CaixaSelecao;

public class Aplicacao {

    private final Botao botao;
    private final CaixaSelecao caixaSelecao;

    public Aplicacao(FabricaUI fabrica) {
        this.botao = fabrica.criarBotao();
        this.caixaSelecao = fabrica.criarCaixaSelecao();
    }

    public String construirUI() {
        return botao.renderizar() + "\n" + caixaSelecao.renderizar();
    }

    public String interagir() {
        String clique = botao.clicar();
        boolean novoEstado = caixaSelecao.alternarEstado();
        return clique + "\nCheckbox agora está: " + (novoEstado ? "marcada" : "desmarcada");
    }
}
