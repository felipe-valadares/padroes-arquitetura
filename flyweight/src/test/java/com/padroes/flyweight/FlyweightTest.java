package com.padroes.flyweight;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Flyweight — Floresta Simulada")
class FlyweightTest {

    private Floresta floresta;

    @BeforeEach
    void setUp() {
        floresta = new Floresta();
    }

    @Nested
    @DisplayName("FabricaArvores — compartilhamento de flyweights")
    class FabricaArvoresTest {

        private FabricaArvores fabrica;

        @BeforeEach
        void setUp() { fabrica = new FabricaArvores(); }

        @Test
        @DisplayName("Mesma combinação retorna o mesmo objeto TipoArvore")
        void mesmaCombinacaoRetornaMesmoObjeto() {
            TipoArvore a = fabrica.obter("Carvalho", "Verde", "Rugosa");
            TipoArvore b = fabrica.obter("Carvalho", "Verde", "Rugosa");
            assertSame(a, b);
        }

        @Test
        @DisplayName("Combinações distintas criam objetos diferentes")
        void combinacoesDistintasCriaObjetos() {
            TipoArvore a = fabrica.obter("Carvalho", "Verde", "Rugosa");
            TipoArvore b = fabrica.obter("Pinheiro", "Verde-escuro", "Lisa");
            assertNotSame(a, b);
            assertEquals(2, fabrica.totalTipos());
        }

        @Test
        @DisplayName("100 chamadas com mesmos parâmetros criam apenas 1 TipoArvore")
        void cemChamadasCriam1Tipo() {
            for (int i = 0; i < 100; i++) {
                fabrica.obter("Bambu", "Amarelo", "Estriada");
            }
            assertEquals(1, fabrica.totalTipos());
        }
    }

    @Nested
    @DisplayName("Floresta — estado extrínseco por instância")
    class FlorestaTest {

        @Test
        @DisplayName("Plantar 1000 árvores do mesmo tipo usa apenas 1 flyweight")
        void milArvoresMesmoTipoUsa1Flyweight() {
            for (int i = 0; i < 1000; i++) {
                floresta.plantarArvore(i, i * 2, 10, "Carvalho", "Verde", "Rugosa");
            }
            assertEquals(1000, floresta.totalArvores());
            assertEquals(1, floresta.totalTipos());
        }

        @Test
        @DisplayName("3 tipos distintos criam 3 flyweights")
        void tresTiposDistintoCria3Flyweights() {
            floresta.plantarArvore(0, 0, 10, "Carvalho", "Verde", "Rugosa");
            floresta.plantarArvore(1, 1, 8, "Pinheiro", "Verde-escuro", "Lisa");
            floresta.plantarArvore(2, 2, 15, "Bambu", "Amarelo", "Estriada");
            assertEquals(3, floresta.totalTipos());
        }

        @Test
        @DisplayName("renderizar() não lança exceção")
        void renderizarNaoLancaExcecao() {
            floresta.plantarArvore(0, 0, 10, "Carvalho", "Verde", "Rugosa");
            floresta.plantarArvore(5, 5, 12, "Pinheiro", "Verde-escuro", "Lisa");
            assertDoesNotThrow(() -> floresta.renderizar());
        }

        @Test
        @DisplayName("Estado extrínseco difere entre árvores do mesmo tipo")
        void estadoExtrinsecoDifereEntreArvores() {
            floresta.plantarArvore(10, 20, 5, "Carvalho", "Verde", "Rugosa");
            floresta.plantarArvore(30, 40, 15, "Carvalho", "Verde", "Rugosa");
            assertEquals(2, floresta.totalArvores());
            assertEquals(1, floresta.totalTipos());
        }
    }
}
