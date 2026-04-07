package com.padroes.factorymethod.documentos;

public interface Documento {
    String abrir();
    String salvar();
    String fechar();
    String getFormato();
}
