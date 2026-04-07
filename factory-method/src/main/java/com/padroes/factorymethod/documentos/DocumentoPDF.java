package com.padroes.factorymethod.documentos;

public class DocumentoPDF implements Documento {

    @Override
    public String abrir() {
        return "Abrindo documento PDF com Adobe Reader Engine";
    }

    @Override
    public String salvar() {
        return "Salvando documento no formato PDF/A (ISO 32000)";
    }

    @Override
    public String fechar() {
        return "Fechando documento PDF e liberando memória do renderizador";
    }

    @Override
    public String getFormato() {
        return "PDF";
    }
}
