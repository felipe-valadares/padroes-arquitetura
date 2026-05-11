package com.padroes.templatemethod;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Template Method — Geração de Relatórios")
class TemplateMethodTest {

    @Nested
    @DisplayName("RelatorioVendas")
    class RelatorioVendasTest {

        private String resultado;

        @BeforeEach
        void gerar() {
            resultado = new RelatorioVendas().gerar();
        }

        @Test
        @DisplayName("Deve exportar como PDF por padrão")
        void exportaComoPdf() {
            assertTrue(resultado.startsWith("[PDF]"));
        }

        @Test
        @DisplayName("Deve conter título do relatório de vendas")
        void contemTituloVendas() {
            assertTrue(resultado.contains("Vendas"));
        }

        @Test
        @DisplayName("Deve conter dados processados")
        void contemDadosProcessados() {
            assertTrue(resultado.contains("vendas="));
        }
    }

    @Nested
    @DisplayName("RelatorioEstoque")
    class RelatorioEstoqueTest {

        private String resultado;

        @BeforeEach
        void gerar() {
            resultado = new RelatorioEstoque().gerar();
        }

        @Test
        @DisplayName("Deve exportar como CSV quando sobrescrito")
        void exportaComoCsv() {
            assertTrue(resultado.startsWith("[CSV]"));
        }

        @Test
        @DisplayName("Deve conter título do relatório de estoque")
        void contemTituloEstoque() {
            assertTrue(resultado.contains("Estoque"));
        }

        @Test
        @DisplayName("Deve conter dados de produtos")
        void contemDadosProdutos() {
            assertTrue(resultado.contains("produtos="));
        }
    }

    @Nested
    @DisplayName("Template Method")
    class TemplateTest {

        @Test
        @DisplayName("gerar() deve produzir resultado não nulo para qualquer subclasse")
        void gerarProduziuResultadoNaoNulo() {
            assertNotNull(new RelatorioVendas().gerar());
            assertNotNull(new RelatorioEstoque().gerar());
        }

        @Test
        @DisplayName("Subclasses diferentes devem produzir relatórios diferentes")
        void relatoriosDiferentes() {
            String vendas = new RelatorioVendas().gerar();
            String estoque = new RelatorioEstoque().gerar();
            assertNotEquals(vendas, estoque);
        }

        @Test
        @DisplayName("exportar() pode ser sobrescrito por subclasses")
        void exportarPodeSerSobrescrito() {
            String vendas = new RelatorioVendas().gerar();
            String estoque = new RelatorioEstoque().gerar();
            assertTrue(vendas.contains("[PDF]"));
            assertTrue(estoque.contains("[CSV]"));
        }
    }
}
