package com.padroes.observer;

import com.padroes.observer.observadores.AlertaEmail;
import com.padroes.observer.observadores.InvestidorPF;
import com.padroes.observer.observadores.InvestidorPJ;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Observer — Alertas de Bolsa")
class ObserverTest {

    private AcaoBolsa petr4;
    private AlertaEmail alerta;

    @BeforeEach
    void setUp() {
        petr4 = new AcaoBolsa("PETR4", 30.0);
        alerta = new AlertaEmail("inv@email.com");
    }

    @Nested
    @DisplayName("AcaoBolsa")
    class AcaoBolsaTest {

        @Test
        @DisplayName("Deve notificar observadores quando preço muda")
        void notificaAoMudarPreco() {
            petr4.adicionar(alerta);
            petr4.setPreco(35.0);
            assertEquals(1, alerta.getNotificacoesRecebidas().size());
        }

        @Test
        @DisplayName("Não deve notificar se preço não mudou")
        void naoNotificaSemMudanca() {
            petr4.adicionar(alerta);
            petr4.setPreco(30.0);
            assertTrue(alerta.getNotificacoesRecebidas().isEmpty());
        }

        @Test
        @DisplayName("Deve notificar múltiplas vezes em múltiplas mudanças")
        void notificaMultiplasVezes() {
            petr4.adicionar(alerta);
            petr4.setPreco(31.0);
            petr4.setPreco(32.0);
            petr4.setPreco(33.0);
            assertEquals(3, alerta.getNotificacoesRecebidas().size());
        }

        @Test
        @DisplayName("Notificação deve conter o ticker")
        void notificacaoContemTicker() {
            petr4.adicionar(alerta);
            petr4.setPreco(35.0);
            assertTrue(alerta.getNotificacoesRecebidas().get(0).contains("PETR4"));
        }
    }

    @Nested
    @DisplayName("Gerenciamento de observadores")
    class GerenciamentoTest {

        @Test
        @DisplayName("Deve parar de notificar após remover observador")
        void paraDeNotificarAposRemover() {
            petr4.adicionar(alerta);
            petr4.remover(alerta);
            petr4.setPreco(40.0);
            assertTrue(alerta.getNotificacoesRecebidas().isEmpty());
        }

        @Test
        @DisplayName("Deve notificar apenas observadores registrados")
        void notificaSomenteRegistrados() {
            var outro = new AlertaEmail("outro@email.com");
            petr4.adicionar(alerta);
            petr4.setPreco(35.0);
            assertTrue(outro.getNotificacoesRecebidas().isEmpty());
            assertEquals(1, alerta.getNotificacoesRecebidas().size());
        }
    }

    @Nested
    @DisplayName("InvestidorPF")
    class InvestidorPFTest {

        @Test
        @DisplayName("Deve alertar quando preço supera limite")
        void alertaAcimaDoLimite() {
            var inv = new InvestidorPF("João", 50.0);
            var acao = new AcaoBolsa("VALE3", 40.0);
            acao.adicionar(inv);
            acao.setPreco(55.0);
            assertNotNull(inv.getUltimoAlerta());
            assertTrue(inv.getUltimoAlerta().contains("VALE3"));
        }

        @Test
        @DisplayName("Não deve alertar quando preço está abaixo do limite")
        void naoAlertaAbaixoDoLimite() {
            var inv = new InvestidorPF("João", 50.0);
            var acao = new AcaoBolsa("VALE3", 40.0);
            acao.adicionar(inv);
            acao.setPreco(45.0);
            assertNull(inv.getUltimoAlerta());
        }

        @Test
        @DisplayName("Deve incluir nome do investidor no alerta")
        void incluiNomeNoAlerta() {
            var inv = new InvestidorPF("Maria", 10.0);
            var acao = new AcaoBolsa("ITUB4", 5.0);
            acao.adicionar(inv);
            acao.setPreco(15.0);
            assertTrue(inv.getUltimoAlerta().contains("Maria"));
        }
    }

    @Nested
    @DisplayName("InvestidorPJ")
    class InvestidorPJTest {

        @Test
        @DisplayName("Deve calcular valor do lote ao ser notificado")
        void calculaValorDoLote() {
            var inv = new InvestidorPJ("Empresa X", 100);
            var acao = new AcaoBolsa("VALE3", 80.0);
            acao.adicionar(inv);
            acao.setPreco(85.0);
            assertNotNull(inv.getUltimoCalculo());
            assertTrue(inv.getUltimoCalculo().contains("8500"));
        }

        @Test
        @DisplayName("Deve incluir nome do investidor no cálculo")
        void incluiNomeNoCalculo() {
            var inv = new InvestidorPJ("Fundo ABC", 200);
            var acao = new AcaoBolsa("PETR4", 30.0);
            acao.adicionar(inv);
            acao.setPreco(32.0);
            assertTrue(inv.getUltimoCalculo().contains("Fundo ABC"));
        }
    }

    @Nested
    @DisplayName("AlertaEmail")
    class AlertaEmailTest {

        @Test
        @DisplayName("Deve acumular múltiplas notificações")
        void acumulaMultiplasNotificacoes() {
            petr4.adicionar(alerta);
            petr4.setPreco(31.0);
            petr4.setPreco(32.0);
            assertEquals(2, alerta.getNotificacoesRecebidas().size());
        }

        @Test
        @DisplayName("Notificações devem incluir preço formatado")
        void notificacaoContemPreco() {
            petr4.adicionar(alerta);
            petr4.setPreco(35.50);
            assertTrue(alerta.getNotificacoesRecebidas().get(0).contains("35"));
        }
    }

    @Nested
    @DisplayName("Observador em múltiplos sujeitos")
    class MultiplosSujeitosTest {

        @Test
        @DisplayName("Observador pode monitorar múltiplas ações")
        void monitoraMultiplasAcoes() {
            var vale3 = new AcaoBolsa("VALE3", 80.0);
            petr4.adicionar(alerta);
            vale3.adicionar(alerta);
            petr4.setPreco(31.0);
            vale3.setPreco(82.0);
            assertEquals(2, alerta.getNotificacoesRecebidas().size());
            assertTrue(alerta.getNotificacoesRecebidas().get(0).contains("PETR4"));
            assertTrue(alerta.getNotificacoesRecebidas().get(1).contains("VALE3"));
        }
    }
}
