package com.padroes.factorymethod.criadores;

import com.padroes.factorymethod.documentos.Documento;
import com.padroes.factorymethod.documentos.DocumentoWord;

public class CriadorWord extends CriadorDocumento {

    @Override
    public Documento criarDocumento() {
        return new DocumentoWord();
    }
}
