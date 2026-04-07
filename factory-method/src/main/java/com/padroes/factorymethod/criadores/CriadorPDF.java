package com.padroes.factorymethod.criadores;

import com.padroes.factorymethod.documentos.Documento;
import com.padroes.factorymethod.documentos.DocumentoPDF;

public class CriadorPDF extends CriadorDocumento {

    @Override
    public Documento criarDocumento() {
        return new DocumentoPDF();
    }
}
