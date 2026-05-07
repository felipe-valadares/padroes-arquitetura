package com.padroes.chainofresponsibility;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Chain of Responsibility — Suporte Técnico")
class ChainOfResponsibilityTest {

    private ManipuladorSuporte cadeia;

    @BeforeEach
    void montarCadeia() {
        ManipuladorSuporte n1 = new SuporteNivel1();
        ManipuladorSuporte n2 = new SuporteNivel2();
        ManipuladorSuporte n3 = new SuporteNivel3();
        n1.setProximo(n2).setProximo(n3);
        cadeia = n1;
    }

    @Nested
    @DisplayName("SuporteNivel1")
    class Nivel1Test {

        @Test
        @DisplayName("Deve resolver tickets de prioridade BAIXA")
        void resolveTicketBaixaPrioridade() {
            var ticket = new TicketSuporte(1, "Redefinir senha", Prioridade.BAIXA);
            String resultado = cadeia.manipular(ticket);
            assertTrue(resultado.contains("[N1]"));
            assertTrue(resultado.contains("1"));
        }

        @Test
        @DisplayName("Não deve resolver tickets de prioridade MEDIA")
        void naoResolveTicketMediaPrioridade() {
            var ticket = new TicketSuporte(2, "Lentidão", Prioridade.MEDIA);
            String resultado = cadeia.manipular(ticket);
            assertFalse(resultado.contains("[N1]"));
        }

        @Test
        @DisplayName("Não deve resolver tickets de prioridade ALTA")
        void naoResolveTicketAltaPrioridade() {
            var ticket = new TicketSuporte(3, "Falha crítica", Prioridade.ALTA);
            String resultado = cadeia.manipular(ticket);
            assertFalse(resultado.contains("[N1]"));
        }
    }

    @Nested
    @DisplayName("SuporteNivel2")
    class Nivel2Test {

        @Test
        @DisplayName("Deve resolver tickets de prioridade MEDIA")
        void resolveTicketMediaPrioridade() {
            var ticket = new TicketSuporte(4, "Lentidão no sistema", Prioridade.MEDIA);
            String resultado = cadeia.manipular(ticket);
            assertTrue(resultado.contains("[N2]"));
            assertTrue(resultado.contains("4"));
        }

        @Test
        @DisplayName("Não deve resolver tickets de prioridade BAIXA")
        void naoResolveTicketBaixaPrioridade() {
            var n2 = new SuporteNivel2();
            var ticket = new TicketSuporte(5, "Senha", Prioridade.BAIXA);
            String resultado = n2.manipular(ticket);
            assertFalse(resultado.contains("[N2]"));
        }

        @Test
        @DisplayName("Não deve resolver tickets de prioridade ALTA")
        void naoResolveTicketAltaPrioridade() {
            var ticket = new TicketSuporte(6, "Falha crítica", Prioridade.ALTA);
            String resultado = cadeia.manipular(ticket);
            assertFalse(resultado.contains("[N2]"));
        }
    }

    @Nested
    @DisplayName("SuporteNivel3")
    class Nivel3Test {

        @Test
        @DisplayName("Deve resolver tickets de prioridade ALTA")
        void resolveTicketAltaPrioridade() {
            var ticket = new TicketSuporte(7, "Falha crítica em produção", Prioridade.ALTA);
            String resultado = cadeia.manipular(ticket);
            assertTrue(resultado.contains("[N3]"));
            assertTrue(resultado.contains("7"));
        }

        @Test
        @DisplayName("Não deve resolver tickets de prioridade BAIXA")
        void naoResolveTicketBaixaPrioridade() {
            var n3 = new SuporteNivel3();
            var ticket = new TicketSuporte(8, "Senha", Prioridade.BAIXA);
            String resultado = n3.manipular(ticket);
            assertFalse(resultado.contains("[N3]"));
        }
    }

    @Nested
    @DisplayName("Cadeia sem próximo handler")
    class SemProximoTest {

        @Test
        @DisplayName("Deve retornar mensagem de fallback quando nenhum handler resolve")
        void retornaMensagemDeFallback() {
            var n1Isolado = new SuporteNivel1();
            var ticket = new TicketSuporte(9, "Problema desconhecido", Prioridade.ALTA);
            String resultado = n1Isolado.manipular(ticket);
            assertFalse(resultado.isBlank());
            assertTrue(resultado.contains("9"));
        }
    }

    @Nested
    @DisplayName("Integração")
    class IntegracaoTest {

        @Test
        @DisplayName("Cada prioridade deve ser atendida pelo nível correto")
        void cadaPrioridadeAtendidaPeloNivelCorreto() {
            assertAll(
                () -> assertTrue(cadeia.manipular(new TicketSuporte(10, "A", Prioridade.BAIXA)).contains("[N1]")),
                () -> assertTrue(cadeia.manipular(new TicketSuporte(11, "B", Prioridade.MEDIA)).contains("[N2]")),
                () -> assertTrue(cadeia.manipular(new TicketSuporte(12, "C", Prioridade.ALTA)).contains("[N3]"))
            );
        }

        @Test
        @DisplayName("Cadeia parcial: N1→N2 deve escalar ALTA para fallback")
        void cadeiaParcialEscalaAltaParaFallback() {
            var n1 = new SuporteNivel1();
            var n2 = new SuporteNivel2();
            n1.setProximo(n2);

            var ticket = new TicketSuporte(13, "Falha crítica", Prioridade.ALTA);
            String resultado = n1.manipular(ticket);

            assertFalse(resultado.contains("[N1]"));
            assertFalse(resultado.contains("[N2]"));
            assertFalse(resultado.contains("[N3]"));
        }

        @Test
        @DisplayName("setProximo deve permitir encadeamento fluente")
        void encadeamentoFluente() {
            ManipuladorSuporte n1 = new SuporteNivel1();
            ManipuladorSuporte n2 = new SuporteNivel2();
            ManipuladorSuporte retorno = n1.setProximo(n2);
            assertSame(n2, retorno);
        }
    }
}
