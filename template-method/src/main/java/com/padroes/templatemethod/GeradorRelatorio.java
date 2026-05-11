package com.padroes.templatemethod;

public abstract class GeradorRelatorio {

    public final String gerar() {
        String dados = coletarDados();
        String processado = processarDados(dados);
        String formatado = formatarRelatorio(processado);
        return exportar(formatado);
    }

    protected abstract String coletarDados();

    protected abstract String processarDados(String dados);

    protected abstract String formatarRelatorio(String dados);

    protected String exportar(String relatorio) {
        return "[PDF] " + relatorio;
    }
}
