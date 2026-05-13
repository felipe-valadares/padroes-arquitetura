package com.padroes.facade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Facade — Checkout de Loja")
class FacadeTest {

    private GerenciadorEstoque estoque;
    private ProcessadorPagamento pagamento;
    private ServicoNotificacao notificacao;
    private LojaFacade loja;

    @BeforeEach
    void setUp() {
        estoque = new GerenciadorEstoque();
        pagamento = new ProcessadorPagamento();
        notificacao = new ServicoNotificacao();
        loja = new LojaFacade(estoque, pagamento, notificacao);
    }

    @Nested
    @DisplayName("Compra com sucesso")
    class CompraComSucessoTest {

        @Test
        @DisplayName("Deve retornar sucesso quando estoque e pagamento são válidos")
        void deveRetornarSucesso() {
            ResultadoCompra resultado = loja.realizarCompra("Notebook", 1, "4111-1111-1111-1111", "a@b.com");
            assertTrue(resultado.isSucesso());
        }

        @Test
        @DisplayName("Mensagem deve conter o nome do produto")
        void mensagemDeveConterProduto() {
            ResultadoCompra resultado = loja.realizarCompra("Notebook", 1, "4111-1111-1111-1111", "a@b.com");
            assertTrue(resultado.getMensagem().contains("Notebook"));
        }

        @Test
        @DisplayName("Deve enviar notificação após compra bem-sucedida")
        void deveEnviarNotificacao() {
            loja.realizarCompra("Mouse", 1, "4111-1111-1111-1111", "cliente@email.com");
            assertNotNull(notificacao.getUltimaNotificacao());
            assertTrue(notificacao.getUltimaNotificacao().contains("cliente@email.com"));
        }

        @Test
        @DisplayName("Deve reduzir o estoque após compra bem-sucedida")
        void deveReduzirEstoque() {
            loja.realizarCompra("Mouse", 1, "4111-1111-1111-1111", "a@b.com");
            assertFalse(estoque.verificarDisponibilidade("Mouse", 50));
            assertTrue(estoque.verificarDisponibilidade("Mouse", 49));
        }
    }

    @Nested
    @DisplayName("Estoque insuficiente")
    class EstoqueInsuficienteTest {

        @Test
        @DisplayName("Deve retornar falha quando produto não existe no estoque")
        void deveRetornarFalhaProdutoInexistente() {
            ResultadoCompra resultado = loja.realizarCompra("Monitor", 1, "4111-1111-1111-1111", "a@b.com");
            assertFalse(resultado.isSucesso());
        }

        @Test
        @DisplayName("Não deve enviar notificação quando estoque é insuficiente")
        void naoDeveEnviarNotificacaoSemEstoque() {
            loja.realizarCompra("Monitor", 1, "4111-1111-1111-1111", "a@b.com");
            assertNull(notificacao.getUltimaNotificacao());
        }

        @Test
        @DisplayName("Mensagem deve indicar problema com estoque")
        void mensagemDeveIndicarEstoque() {
            ResultadoCompra resultado = loja.realizarCompra("Monitor", 1, "4111-1111-1111-1111", "a@b.com");
            assertTrue(resultado.getMensagem().toLowerCase().contains("estoque")
                || resultado.getMensagem().contains("Monitor"));
        }
    }

    @Nested
    @DisplayName("Pagamento recusado")
    class PagamentoRecusadoTest {

        @Test
        @DisplayName("Deve retornar falha com cartão inválido")
        void deveRetornarFalhaCartaoInvalido() {
            ResultadoCompra resultado = loja.realizarCompra("Notebook", 1, "", "a@b.com");
            assertFalse(resultado.isSucesso());
        }

        @Test
        @DisplayName("Não deve reservar estoque quando pagamento é recusado")
        void naoDeveReservarEstoque() {
            loja.realizarCompra("Notebook", 1, "", "a@b.com");
            assertTrue(estoque.verificarDisponibilidade("Notebook", 10));
        }

        @Test
        @DisplayName("Não deve enviar notificação quando pagamento é recusado")
        void naoDeveEnviarNotificacao() {
            loja.realizarCompra("Notebook", 1, "", "a@b.com");
            assertNull(notificacao.getUltimaNotificacao());
        }
    }

    @Nested
    @DisplayName("Facade")
    class FacadeComportamentoTest {

        @Test
        @DisplayName("Deve simplificar a interface em um único método")
        void devePossuirInterfaceSimplificada() {
            ResultadoCompra resultado = loja.realizarCompra("Mouse", 1, "cartao-valido", "user@email.com");
            assertNotNull(resultado);
            assertTrue(resultado.isSucesso());
        }

        @Test
        @DisplayName("Subsistemas podem ser trocados sem alterar o cliente")
        void subsistemasPodemsSerTrocados() {
            LojaFacade outraLoja = new LojaFacade(
                new GerenciadorEstoque(),
                new ProcessadorPagamento(),
                new ServicoNotificacao()
            );
            ResultadoCompra resultado = outraLoja.realizarCompra("Teclado", 1, "4111-1111-1111-1111", "a@b.com");
            assertTrue(resultado.isSucesso());
        }
    }
}
