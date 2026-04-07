package com.padroes.bridge.implementacao;

public class RenderizadorRaster implements RenderizadorAPI {

    @Override
    public String renderizarCirculo(double x, double y, double raio) {
        long pixels = Math.round(Math.PI * raio * raio);
        return String.format(
            "[Raster] Círculo: centro=(%.1f, %.1f), raio=%.1f — %d pixels via algoritmo de Bresenham",
            x, y, raio, pixels
        );
    }

    @Override
    public String renderizarRetangulo(double x, double y, double largura, double altura) {
        long pixels = Math.round(largura * altura);
        return String.format(
            "[Raster] Retângulo: origem=(%.1f, %.1f), %.1fx%.1f — %d pixels preenchidos",
            x, y, largura, altura, pixels
        );
    }
}
