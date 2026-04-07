package com.padroes.factorymethod.documentos;

public class DocumentoMarkdown implements Documento {

    @Override
    public String abrir() {
        return "Abrindo documento Markdown com parser CommonMark";
    }

    @Override
    public String salvar() {
        return "Salvando documento no formato Markdown (.md)";
    }

    @Override
    public String fechar() {
        return "Fechando documento Markdown";
    }

    @Override
    public String getFormato() {
        return "Markdown";
    }
}
