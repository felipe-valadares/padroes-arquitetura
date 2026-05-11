package com.padroes.builder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Builder — Montagem de Computadores")
class BuilderTest {

    @Nested
    @DisplayName("ComputadorGamerBuilder")
    class GamerBuilderTest {

        private Computador computador;

        @BeforeEach
        void montar() {
            computador = new Montador(new ComputadorGamerBuilder()).montarComputadorCompleto();
        }

        @Test
        @DisplayName("Deve configurar CPU de alto desempenho")
        void configuraCpuAltoDesempenho() {
            assertNotNull(computador.cpu());
            assertTrue(computador.cpu().contains("i9"));
        }

        @Test
        @DisplayName("Deve configurar 32GB de memória")
        void configura32GbMemoria() {
            assertEquals(32, computador.memoriaGb());
        }

        @Test
        @DisplayName("Deve configurar armazenamento de 2TB")
        void configura2TbArmazenamento() {
            assertEquals(2000, computador.armazenamentoGb());
        }

        @Test
        @DisplayName("Deve configurar GPU dedicada de alto desempenho")
        void configuraSGpuDedicada() {
            assertTrue(computador.gpu().contains("RTX"));
        }

        @Test
        @DisplayName("Deve configurar sistema operacional")
        void configuraSistemaOperacional() {
            assertNotNull(computador.sistemaOperacional());
        }
    }

    @Nested
    @DisplayName("ComputadorEscritorioBuilder")
    class EscritorioBuilderTest {

        private Computador computador;

        @BeforeEach
        void montar() {
            computador = new Montador(new ComputadorEscritorioBuilder()).montarComputadorCompleto();
        }

        @Test
        @DisplayName("Deve configurar CPU de uso geral")
        void configuraCpuUsoGeral() {
            assertNotNull(computador.cpu());
            assertTrue(computador.cpu().contains("i5"));
        }

        @Test
        @DisplayName("Deve configurar 16GB de memória")
        void configura16GbMemoria() {
            assertEquals(16, computador.memoriaGb());
        }

        @Test
        @DisplayName("Deve configurar armazenamento de 512GB")
        void configura512GbArmazenamento() {
            assertEquals(512, computador.armazenamentoGb());
        }

        @Test
        @DisplayName("Deve configurar GPU integrada")
        void configuraSGpuIntegrada() {
            assertTrue(computador.gpu().contains("UHD"));
        }

        @Test
        @DisplayName("Deve configurar sistema operacional")
        void configuraSistemaOperacional() {
            assertNotNull(computador.sistemaOperacional());
        }
    }

    @Nested
    @DisplayName("Montador")
    class MontadorTest {

        @Test
        @DisplayName("Deve construir computador com todos os componentes preenchidos")
        void constroiComputadorCompleto() {
            Computador c = new Montador(new ComputadorGamerBuilder()).montarComputadorCompleto();
            assertAll(
                    () -> assertNotNull(c.cpu()),
                    () -> assertTrue(c.memoriaGb() > 0),
                    () -> assertTrue(c.armazenamentoGb() > 0),
                    () -> assertNotNull(c.gpu()),
                    () -> assertNotNull(c.sistemaOperacional())
            );
        }

        @Test
        @DisplayName("Builders diferentes devem produzir computadores diferentes")
        void buildersDiferentesProduzemComputadoresDiferentes() {
            Computador gamer = new Montador(new ComputadorGamerBuilder()).montarComputadorCompleto();
            Computador escritorio = new Montador(new ComputadorEscritorioBuilder()).montarComputadorCompleto();

            assertNotEquals(gamer.cpu(), escritorio.cpu());
            assertNotEquals(gamer.memoriaGb(), escritorio.memoriaGb());
        }
    }
}
