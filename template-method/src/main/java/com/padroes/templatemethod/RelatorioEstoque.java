package com.padroes.templatemethod;

public class RelatorioEstoque extends GeradorRelatorio {

    @Override
    protected String coletarDados() {
        return "produtos=320,criticos=12,zerados=3";
    }

    @Override
    protected String processarDados(String dados) {
        return "Estoque processado: " + dados;
    }

    @Override
    protected String formatarRelatorio(String dados) {
        return "=== Relatório de Estoque ===\n" + dados;
    }

    @Override
    protected String exportar(String relatorio) {
        return "[CSV] " + relatorio;
    }
}
