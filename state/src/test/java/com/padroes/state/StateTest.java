package com.padroes.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("State — Pedido de E-commerce")
class StateTest {

    @Nested
    @DisplayName("Transições válidas")
    class TransicoesValidas {

        @Test
        @DisplayName("Pedido começa no estado Pendente")
        void pedidoComecaPendente() {
            assertEquals("Pendente", new Pedido().getEstado());
        }

        @Test
        @DisplayName("Fluxo completo: Pendente → Pago → Enviado → Entregue")
        void fluxoCompleto() {
            var pedido = new Pedido();
            pedido.pagar();
            assertEquals("Pago", pedido.getEstado());
            pedido.enviar();
            assertEquals("Enviado", pedido.getEstado());
            pedido.entregar();
            assertEquals("Entregue", pedido.getEstado());
        }
    }

    @Nested
    @DisplayName("Cancelamento")
    class Cancelamento {

        @Test
        @DisplayName("Deve cancelar pedido Pendente")
        void cancelarPendente() {
            var pedido = new Pedido();
            pedido.cancelar();
            assertEquals("Cancelado", pedido.getEstado());
        }

        @Test
        @DisplayName("Deve cancelar pedido Pago")
        void cancelarPago() {
            var pedido = new Pedido();
            pedido.pagar();
            pedido.cancelar();
            assertEquals("Cancelado", pedido.getEstado());
        }

        @Test
        @DisplayName("Deve cancelar pedido Enviado")
        void cancelarEnviado() {
            var pedido = new Pedido();
            pedido.pagar();
            pedido.enviar();
            pedido.cancelar();
            assertEquals("Cancelado", pedido.getEstado());
        }
    }

    @Nested
    @DisplayName("Transições inválidas")
    class TransicoesInvalidas {

        @Test
        @DisplayName("Não pode enviar pedido Pendente")
        void naoEnviaPendente() {
            assertThrows(IllegalStateException.class, () -> new Pedido().enviar());
        }

        @Test
        @DisplayName("Não pode entregar pedido Pendente")
        void naoEntregaPendente() {
            assertThrows(IllegalStateException.class, () -> new Pedido().entregar());
        }

        @Test
        @DisplayName("Não pode entregar pedido Pago (precisa ser enviado antes)")
        void naoEntregaPago() {
            var pedido = new Pedido();
            pedido.pagar();
            assertThrows(IllegalStateException.class, pedido::entregar);
        }

        @Test
        @DisplayName("Não pode cancelar pedido Entregue")
        void naoCancelaEntregue() {
            var pedido = new Pedido();
            pedido.pagar();
            pedido.enviar();
            pedido.entregar();
            assertThrows(IllegalStateException.class, pedido::cancelar);
        }

        @Test
        @DisplayName("Nenhuma operação válida em pedido Cancelado")
        void nenhumaOperacaoEmCancelado() {
            var pedido = new Pedido();
            pedido.cancelar();
            assertAll(
                () -> assertThrows(IllegalStateException.class, pedido::pagar),
                () -> assertThrows(IllegalStateException.class, pedido::enviar),
                () -> assertThrows(IllegalStateException.class, pedido::entregar),
                () -> assertThrows(IllegalStateException.class, pedido::cancelar)
            );
        }

        @Test
        @DisplayName("Nenhuma operação válida em pedido Entregue")
        void nenhumaOperacaoEmEntregue() {
            var pedido = new Pedido();
            pedido.pagar();
            pedido.enviar();
            pedido.entregar();
            assertAll(
                () -> assertThrows(IllegalStateException.class, pedido::pagar),
                () -> assertThrows(IllegalStateException.class, pedido::enviar),
                () -> assertThrows(IllegalStateException.class, pedido::entregar)
            );
        }
    }

    @Nested
    @DisplayName("Histórico de transições")
    class Historico {

        @Test
        @DisplayName("Deve registrar estado inicial no histórico")
        void registraEstadoInicial() {
            var pedido = new Pedido();
            assertFalse(pedido.getHistorico().isEmpty());
            assertTrue(pedido.getHistorico().get(0).contains("Pendente"));
        }

        @Test
        @DisplayName("Deve registrar todas as transições no histórico")
        void registraTodasAsTransicoes() {
            var pedido = new Pedido();
            pedido.pagar();
            pedido.enviar();
            var historico = pedido.getHistorico();
            assertEquals(3, historico.size());
            assertTrue(historico.get(1).contains("Pago"));
            assertTrue(historico.get(2).contains("Enviado"));
        }
    }
}
