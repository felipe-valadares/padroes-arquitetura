package com.padroes.bridge.implementacao;

public class RenderizadorVetorial implements RenderizadorAPI {

    @Override
    public String renderizarCirculo(double x, double y, double raio) {
        return String.format(
            "[Vetorial] Círculo: centro=(%.1f, %.1f), raio=%.1f — gerado como curva Bézier",
            x, y, raio
        );
    }

    @Override
    public String renderizarRetangulo(double x, double y, double largura, double altura) {
        return String.format(
            "[Vetorial] Retângulo: origem=(%.1f, %.1f), %.1fx%.1f — gerado como polígono vetorial",
            x, y, largura, altura
        );
    }
}
