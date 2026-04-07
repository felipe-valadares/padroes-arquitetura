package com.padroes.bridge;

import com.padroes.bridge.formas.Circulo;
import com.padroes.bridge.formas.Forma;
import com.padroes.bridge.formas.Retangulo;
import com.padroes.bridge.implementacao.RenderizadorRaster;
import com.padroes.bridge.implementacao.RenderizadorVetorial;

public class Main {

    public static void main(String[] args) {
        var vetorial = new RenderizadorVetorial();
        var raster = new RenderizadorRaster();

        Forma circulo = new Circulo(5, 10, 7, vetorial);
        System.out.println(circulo.desenhar());
        System.out.println(new Circulo(5, 10, 7, raster).desenhar());

        Forma retangulo = new Retangulo(0, 0, 20, 10, vetorial);
        System.out.println(retangulo.desenhar());
        System.out.println(new Retangulo(0, 0, 20, 10, raster).desenhar());

        Forma circuloGrande = circulo.redimensionar(2.0);
        System.out.println("Original: " + circulo.desenhar());
        System.out.println("2x:       " + circuloGrande.desenhar());
    }
}
