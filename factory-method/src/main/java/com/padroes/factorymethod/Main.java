package com.padroes.factorymethod;

import com.padroes.factorymethod.criadores.CriadorDocumento;
import com.padroes.factorymethod.criadores.CriadorMarkdown;
import com.padroes.factorymethod.criadores.CriadorPDF;
import com.padroes.factorymethod.criadores.CriadorWord;

public class Main {

    public static void main(String[] args) {
        processar(new CriadorPDF());
        System.out.println();
        processar(new CriadorWord());
        System.out.println();
        processar(new CriadorMarkdown());
    }

    private static void processar(CriadorDocumento criador) {
        System.out.println(criador.processarDocumento());
    }
}
