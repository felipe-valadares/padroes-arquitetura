package com.padroes.bridge;

import com.padroes.bridge.formas.Circulo;
import com.padroes.bridge.formas.Forma;
import com.padroes.bridge.formas.Retangulo;
import com.padroes.bridge.implementacao.RenderizadorAPI;
import com.padroes.bridge.implementacao.RenderizadorRaster;
import com.padroes.bridge.implementacao.RenderizadorVetorial;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Bridge - Formas e Renderizadores")
class BridgeTest {

    private RenderizadorAPI vetorial;
    private RenderizadorAPI raster;

    @BeforeEach
    void setUp() {
        vetorial = new RenderizadorVetorial();
        raster = new RenderizadorRaster();
    }

    @Nested
    @DisplayName("Circulo")
    class CirculoTest {

        @Test
        @DisplayName("Deve delegar ao RenderizadorVetorial")
        void deveDelegarAoVetorial() {
            assertTrue(new Circulo(0, 0, 5, vetorial).desenhar().contains("Vetorial"));
        }

        @Test
        @DisplayName("Deve delegar ao RenderizadorRaster")
        void deveDelegarAoRaster() {
            assertTrue(new Circulo(0, 0, 5, raster).desenhar().contains("Raster"));
        }

        @Test
        @DisplayName("Deve incluir o raio na saída")
        void deveIncluirRaioNaSaida() {
            String resultado = new Circulo(0, 0, 7.0, vetorial).desenhar();
            assertTrue(resultado.contains("7,0") || resultado.contains("7.0"));
        }

        @Test
        @DisplayName("Redimensionar deve escalar o raio")
        void redimensionarDeveEscalarRaio() {
            Circulo original = new Circulo(0, 0, 10, vetorial);
            Circulo escalado = (Circulo) original.redimensionar(2.0);
            assertEquals(10.0, original.getRaio());
            assertEquals(20.0, escalado.getRaio());
        }

        @Test
        @DisplayName("Redimensionar deve preservar o renderizador")
        void redimensionarDevePreservarRenderizador() {
            assertTrue(new Circulo(0, 0, 5, vetorial).redimensionar(3.0).desenhar().contains("Vetorial"));
        }

        @Test
        @DisplayName("Mesmo círculo com renderizadores diferentes produz saídas distintas")
        void mesmaFormaRenderizadoresDiferentesProduzemSaidasDistintas() {
            assertNotEquals(
                new Circulo(1, 2, 5, vetorial).desenhar(),
                new Circulo(1, 2, 5, raster).desenhar()
            );
        }
    }

    @Nested
    @DisplayName("Retangulo")
    class RetanguloTest {

        @Test
        @DisplayName("Deve delegar ao RenderizadorVetorial")
        void deveDelegarAoVetorial() {
            assertTrue(new Retangulo(0, 0, 10, 5, vetorial).desenhar().contains("Vetorial"));
        }

        @Test
        @DisplayName("Deve delegar ao RenderizadorRaster")
        void deveDelegarAoRaster() {
            assertTrue(new Retangulo(0, 0, 10, 5, raster).desenhar().contains("Raster"));
        }

        @Test
        @DisplayName("Redimensionar deve escalar largura e altura")
        void redimensionarDeveEscalarDimensoes() {
            Retangulo original = new Retangulo(0, 0, 10, 4, vetorial);
            Retangulo escalado = (Retangulo) original.redimensionar(2.0);
            assertAll(
                () -> assertEquals(10.0, original.getLargura()),
                () -> assertEquals(4.0, original.getAltura()),
                () -> assertEquals(20.0, escalado.getLargura()),
                () -> assertEquals(8.0, escalado.getAltura())
            );
        }

        @Test
        @DisplayName("Raster deve calcular pixels da área")
        void rasterDeveCalcularPixels() {
            assertTrue(new Retangulo(0, 0, 10, 5, raster).desenhar().contains("50"));
        }
    }

    @Nested
    @DisplayName("Independência entre hierarquias")
    class IndependenciaTest {

        @Test
        @DisplayName("Vetorial funciona com qualquer forma")
        void vetorialFuncionaComTodasAsFormas() {
            assertAll(
                () -> assertFalse(new Circulo(0, 0, 5, vetorial).desenhar().isBlank()),
                () -> assertFalse(new Retangulo(0, 0, 4, 3, vetorial).desenhar().isBlank())
            );
        }

        @Test
        @DisplayName("Circulo vetorial não deve conter 'Raster'")
        void circuloVetorialNaoDeveConterRaster() {
            assertFalse(new Circulo(0, 0, 5, vetorial).desenhar().contains("Raster"));
        }
    }
}
