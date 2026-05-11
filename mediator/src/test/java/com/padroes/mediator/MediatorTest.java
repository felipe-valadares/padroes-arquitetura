package com.padroes.mediator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Mediator — Sala de Chat")
class MediatorTest {

    private SalaDeChat sala;
    private Usuario alice;
    private Usuario bob;
    private Usuario carlos;

    @BeforeEach
    void configurar() {
        sala = new SalaDeChat();
        alice = new Usuario(sala, "Alice");
        bob = new Usuario(sala, "Bob");
        carlos = new Usuario(sala, "Carlos");
        sala.registrar(alice);
        sala.registrar(bob);
        sala.registrar(carlos);
    }

    @Nested
    @DisplayName("Envio de mensagens")
    class EnvioTest {

        @Test
        @DisplayName("Remetente não deve receber a própria mensagem")
        void remetenteNaoRecebePropriasMensagem() {
            alice.enviar("Olá!");
            assertTrue(alice.getMensagensRecebidas().isEmpty());
        }

        @Test
        @DisplayName("Todos os outros participantes devem receber a mensagem")
        void todosOutrosReceberMensagem() {
            alice.enviar("Olá!");
            assertFalse(bob.getMensagensRecebidas().isEmpty());
            assertFalse(carlos.getMensagensRecebidas().isEmpty());
        }

        @Test
        @DisplayName("Mensagem deve conter o nome do remetente")
        void mensagemContemNomeDoRemetente() {
            alice.enviar("Olá!");
            assertTrue(bob.getMensagensRecebidas().get(0).contains("Alice"));
        }

        @Test
        @DisplayName("Mensagem deve conter o conteúdo enviado")
        void mensagemContemConteudo() {
            alice.enviar("Teste de mensagem");
            assertTrue(bob.getMensagensRecebidas().get(0).contains("Teste de mensagem"));
        }
    }

    @Nested
    @DisplayName("Múltiplas mensagens")
    class MultiplasMensagensTest {

        @Test
        @DisplayName("Participante deve acumular mensagens de múltiplos remetentes")
        void acumulaMensagensDeMultiplosRemetentes() {
            alice.enviar("Oi!");
            bob.enviar("Oi também!");
            assertEquals(2, carlos.getMensagensRecebidas().size());
        }

        @Test
        @DisplayName("Ordem das mensagens deve ser preservada")
        void ordemDasMensagensPreservada() {
            alice.enviar("Primeira");
            bob.enviar("Segunda");

            var mensagens = carlos.getMensagensRecebidas();
            assertTrue(mensagens.get(0).contains("Primeira"));
            assertTrue(mensagens.get(1).contains("Segunda"));
        }
    }

    @Nested
    @DisplayName("Registro de participantes")
    class RegistroTest {

        @Test
        @DisplayName("Participante não registrado não deve receber mensagens")
        void participanteNaoRegistradoNaoReceberMensagem() {
            Usuario visitante = new Usuario(sala, "Visitante");

            alice.enviar("Mensagem");
            assertTrue(visitante.getMensagensRecebidas().isEmpty());
        }

        @Test
        @DisplayName("getNome deve retornar o nome do participante")
        void getNomeRetornaCorreto() {
            assertEquals("Alice", alice.getNome());
        }
    }
}
