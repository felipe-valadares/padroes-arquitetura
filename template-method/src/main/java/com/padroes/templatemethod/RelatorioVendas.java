package com.padroes.templatemethod;

public class RelatorioVendas extends GeradorRelatorio {

    @Override
    protected String coletarDados() {
        return "vendas=1500,devolucoes=50,total=1450";
    }

    @Override
    protected String processarDados(String dados) {
        return "Vendas processadas: " + dados;
    }

    @Override
    protected String formatarRelatorio(String dados) {
        return "=== Relatório de Vendas ===\n" + dados;
    }
}
