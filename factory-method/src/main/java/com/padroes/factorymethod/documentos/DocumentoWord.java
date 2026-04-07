package com.padroes.factorymethod.documentos;

public class DocumentoWord implements Documento {

    @Override
    public String abrir() {
        return "Abrindo documento Word com processador OOXML";
    }

    @Override
    public String salvar() {
        return "Salvando documento no formato DOCX (Office Open XML)";
    }

    @Override
    public String fechar() {
        return "Fechando documento Word e liberando parser XML";
    }

    @Override
    public String getFormato() {
        return "DOCX";
    }
}
