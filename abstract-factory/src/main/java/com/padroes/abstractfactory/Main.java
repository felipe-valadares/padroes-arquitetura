package com.padroes.abstractfactory;

import com.padroes.abstractfactory.fabricas.FabricaMac;
import com.padroes.abstractfactory.fabricas.FabricaUI;
import com.padroes.abstractfactory.fabricas.FabricaWindows;

public class Main {

    public static void main(String[] args) {
        executarComFabrica(new FabricaWindows());
        System.out.println();
        executarComFabrica(new FabricaMac());
    }

    private static void executarComFabrica(FabricaUI fabrica) {
        Aplicacao app = new Aplicacao(fabrica);
        System.out.println(app.construirUI());
        System.out.println(app.interagir());
    }
}
