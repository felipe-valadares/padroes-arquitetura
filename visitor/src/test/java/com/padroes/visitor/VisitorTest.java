package com.padroes.visitor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Visitor — Relatório Financeiro com múltiplos exportadores")
class VisitorTest {

    private Receita receita;
    private Despesa despesa;
    private Investimento investimento;

    @BeforeEach
    void setUp() {
        receita      = new Receita("Venda online", 3000.00);
        despesa      = new Despesa("Internet", 150.00);
        investimento = new Investimento("Tesouro Direto", 5000.00, 0.10);
    }

    @Nested
    @DisplayName("ExportadorCSV")
    class ExportadorCSVTest {

        private ExportadorCSV csv;

        @BeforeEach
        void setUp() { csv = new ExportadorCSV(); }

        @Test
        @DisplayName("Deve conter cabeçalho CSV")
        void deveConterCabecalho() {
            assertTrue(csv.getSaida().startsWith("tipo,descricao,valor"));
        }

        @Test
        @DisplayName("Deve exportar Receita com tipo RECEITA")
        void deveExportarReceita() {
            receita.aceitar(csv);
            String saida = csv.getSaida();
            assertTrue(saida.contains("RECEITA"));
            assertTrue(saida.contains("Venda online"));
            assertTrue(saida.contains("3000,00") || saida.contains("3000.00"));
        }

        @Test
        @DisplayName("Deve exportar Despesa com tipo DESPESA")
        void deveExportarDespesa() {
            despesa.aceitar(csv);
            assertTrue(csv.getSaida().contains("DESPESA"));
            assertTrue(csv.getSaida().contains("Internet"));
        }

        @Test
        @DisplayName("Deve exportar Investimento com tipo INVESTIMENTO")
        void deveExportarInvestimento() {
            investimento.aceitar(csv);
            assertTrue(csv.getSaida().contains("INVESTIMENTO"));
            assertTrue(csv.getSaida().contains("Tesouro Direto"));
        }

        @Test
        @DisplayName("Deve exportar múltiplos itens em linhas separadas")
        void deveExportarMultiplosItens() {
            receita.aceitar(csv);
            despesa.aceitar(csv);
            investimento.aceitar(csv);
            String[] linhas = csv.getSaida().strip().split("\n");
            assertEquals(4, linhas.length);
        }
    }

    @Nested
    @DisplayName("ExportadorJSON")
    class ExportadorJSONTest {

        private ExportadorJSON json;

        @BeforeEach
        void setUp() { json = new ExportadorJSON(); }

        @Test
        @DisplayName("Lista vazia deve retornar JSON de array vazio")
        void listaVaziaRetornaArrayVazio() {
            assertEquals("[]", json.getSaida());
        }

        @Test
        @DisplayName("Deve exportar Receita com campo tipo RECEITA")
        void deveExportarReceita() {
            receita.aceitar(json);
            String saida = json.getSaida();
            assertTrue(saida.contains("\"tipo\":\"RECEITA\""));
            assertTrue(saida.contains("\"descricao\":\"Venda online\""));
        }

        @Test
        @DisplayName("Deve exportar Investimento com campo taxaRetorno")
        void deveExportarInvestimentoComTaxa() {
            investimento.aceitar(json);
            String saida = json.getSaida();
            assertTrue(saida.contains("\"taxaRetorno\""));
            assertTrue(saida.contains("INVESTIMENTO"));
        }

        @Test
        @DisplayName("Deve envolver múltiplos itens em array JSON")
        void deveEnvolverEmArray() {
            receita.aceitar(json);
            despesa.aceitar(json);
            String saida = json.getSaida();
            assertTrue(saida.startsWith("["));
            assertTrue(saida.endsWith("]"));
            assertTrue(saida.contains("},{"));
        }
    }

    @Nested
    @DisplayName("Double Dispatch")
    class DoubleDispatchTest {

        @Test
        @DisplayName("aceitar() deve chamar o método correto do visitante para Receita")
        void deveDespacharReceitaCorretamente() {
            ExportadorCSV csv = new ExportadorCSV();
            receita.aceitar(csv);
            assertTrue(csv.getSaida().contains("RECEITA"));
            assertFalse(csv.getSaida().contains("DESPESA"));
            assertFalse(csv.getSaida().contains("INVESTIMENTO"));
        }

        @Test
        @DisplayName("Mesmo visitante pode processar todos os tipos sem alteração nos elementos")
        void mesmoVisitanteProcessaTodosOsTipos() {
            ExportadorJSON json = new ExportadorJSON();
            receita.aceitar(json);
            despesa.aceitar(json);
            investimento.aceitar(json);
            String saida = json.getSaida();
            assertTrue(saida.contains("RECEITA"));
            assertTrue(saida.contains("DESPESA"));
            assertTrue(saida.contains("INVESTIMENTO"));
        }

        @Test
        @DisplayName("Novo visitante pode ser adicionado sem alterar os elementos")
        void novoVisitanteNaoAlteraElementos() {
            ExportadorCSV csv = new ExportadorCSV();
            ExportadorJSON json = new ExportadorJSON();
            receita.aceitar(csv);
            receita.aceitar(json);
            assertEquals("Venda online", receita.getDescricao());
            assertEquals(3000.00, receita.getValor());
        }
    }
}
