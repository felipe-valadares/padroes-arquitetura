package com.padroes.factorymethod.criadores;

import com.padroes.factorymethod.documentos.Documento;
import com.padroes.factorymethod.documentos.DocumentoMarkdown;

public class CriadorMarkdown extends CriadorDocumento {

    @Override
    public Documento criarDocumento() {
        return new DocumentoMarkdown();
    }
}
