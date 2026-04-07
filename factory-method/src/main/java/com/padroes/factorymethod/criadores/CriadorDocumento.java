package com.padroes.factorymethod.criadores;

import com.padroes.factorymethod.documentos.Documento;

public abstract class CriadorDocumento {

    public abstract Documento criarDocumento();

    public String processarDocumento() {
        Documento documento = criarDocumento();

        return "Iniciando processamento de documento " + documento.getFormato() + ":\n"
            + "  " + documento.abrir() + "\n"
            + "  " + documento.salvar() + "\n"
            + "  " + documento.fechar() + "\n"
            + "Processamento concluído.";
    }
}
