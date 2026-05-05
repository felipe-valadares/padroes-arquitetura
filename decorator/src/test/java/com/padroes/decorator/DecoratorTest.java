package com.padroes.decorator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Decorator — Sistema de Notificações")
class DecoratorTest {

    @Nested
    @DisplayName("NotificacaoEmail")
    class NotificacaoEmailTest {

        @Test
        @DisplayName("Deve incluir destinatário na saída")
        void deveIncluirDestinatario() {
            var n = new NotificacaoEmail("user@email.com", "Olá mundo");
            assertTrue(n.enviar().contains("user@email.com"));
        }

        @Test
        @DisplayName("Deve incluir mensagem na saída")
        void deveIncluirMensagem() {
            var n = new NotificacaoEmail("user@email.com", "Promoção especial");
            assertTrue(n.enviar().contains("Promoção especial"));
        }
    }

    @Nested
    @DisplayName("NotificacaoComLog")
    class DecoradorLogTest {

        @Test
        @DisplayName("Deve prefixar com [LOG")
        void deveConterPrefixoLog() {
            var n = new NotificacaoComLog(new NotificacaoEmail("a@b.com", "msg"));
            assertTrue(n.enviar().startsWith("[LOG "));
        }

        @Test
        @DisplayName("Deve incluir timestamp no formato yyyy-MM-dd HH:mm:ss")
        void deveIncluirTimestampFormatado() {
            var n = new NotificacaoComLog(new NotificacaoEmail("a@b.com", "msg"));
            assertTrue(n.enviar().matches("\\[LOG \\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\].*"));
        }

        @Test
        @DisplayName("Deve preservar conteúdo original após o prefixo")
        void devePreservarConteudo() {
            var base = new NotificacaoEmail("a@b.com", "teste");
            var log = new NotificacaoComLog(base);
            assertTrue(log.enviar().contains(base.enviar()));
        }
    }

    @Nested
    @DisplayName("NotificacaoComCriptografia")
    class DecoradorCriptografiaTest {

        @Test
        @DisplayName("Deve mascarar vogais com asterisco")
        void deveMascararVogais() {
            var n = new NotificacaoComCriptografia(new NotificacaoEmail("a@b.com", "cafe"));
            String resultado = n.enviar();
            assertFalse(resultado.contains("cafe"));
            assertTrue(resultado.contains("*"));
        }

        @Test
        @DisplayName("Deve alterar o conteúdo em relação ao original")
        void deveAlterarConteudo() {
            var base = new NotificacaoEmail("x@y.com", "abcde");
            var cripto = new NotificacaoComCriptografia(base);
            assertNotEquals(base.enviar(), cripto.enviar());
        }

        @Test
        @DisplayName("Deve manter consoantes intactas")
        void deveManterConsoantes() {
            var n = new NotificacaoComCriptografia(new NotificacaoEmail("a@b.com", "bcd"));
            assertTrue(n.enviar().contains("bcd"));
        }
    }

    @Nested
    @DisplayName("NotificacaoComTimestamp")
    class DecoradorTimestampTest {

        @Test
        @DisplayName("Deve incluir sufixo [ts: com número]")
        void deveIncluirTimestampNumerico() {
            var n = new NotificacaoComTimestamp(new NotificacaoEmail("a@b.com", "msg"));
            assertTrue(n.enviar().matches(".*\\[ts:\\d+\\]"));
        }

        @Test
        @DisplayName("Deve preservar conteúdo original antes do sufixo")
        void devePreservarConteudo() {
            var base = new NotificacaoEmail("a@b.com", "msg");
            var ts = new NotificacaoComTimestamp(base);
            assertTrue(ts.enviar().startsWith(base.enviar()));
        }
    }

    @Nested
    @DisplayName("Composição de decoradores")
    class ComposicaoTest {

        @Test
        @DisplayName("Log + Criptografia devem ser empilháveis")
        void deveEmpilharDecoradores() {
            Notificacao n = new NotificacaoComLog(
                new NotificacaoComCriptografia(
                    new NotificacaoEmail("a@b.com", "Promoção")
                )
            );
            String resultado = n.enviar();
            assertTrue(resultado.startsWith("[LOG "));
            assertTrue(resultado.contains("*"));
        }

        @Test
        @DisplayName("Ordem diferente dos decoradores produz resultado diferente")
        void ordemImportaNaComposicao() {
            var base = new NotificacaoEmail("a@b.com", "teste");
            Notificacao criptoLog = new NotificacaoComCriptografia(new NotificacaoComLog(base));
            Notificacao logCripto = new NotificacaoComLog(new NotificacaoComCriptografia(base));
            assertNotEquals(criptoLog.enviar(), logCripto.enviar());
        }

        @Test
        @DisplayName("Três decoradores empilhados devem funcionar")
        void tresDecoradoresEmpilhados() {
            Notificacao n = new NotificacaoComLog(
                new NotificacaoComTimestamp(
                    new NotificacaoComCriptografia(
                        new NotificacaoEmail("a@b.com", "Oferta")
                    )
                )
            );
            String resultado = n.enviar();
            assertTrue(resultado.startsWith("[LOG "));
            assertTrue(resultado.contains("[ts:"));
            assertTrue(resultado.contains("*"));
        }
    }
}
