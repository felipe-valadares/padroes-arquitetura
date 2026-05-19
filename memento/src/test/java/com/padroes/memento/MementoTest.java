package com.padroes.memento;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Memento — Editor de Texto com Undo/Redo")
class MementoTest {

    private EditorTexto editor;
    private HistoricoEdicao historico;

    @BeforeEach
    void setUp() {
        editor = new EditorTexto();
        historico = new HistoricoEdicao(editor);
    }

    @Nested
    @DisplayName("EditorTexto — operações básicas")
    class EditorTextoTest {

        @Test
        @DisplayName("Deve iniciar com texto vazio")
        void deveIniciarComTextoVazio() {
            assertEquals("", editor.getTexto());
        }

        @Test
        @DisplayName("Deve escrever e acumular conteúdo")
        void deveEscreverConteudo() {
            editor.escrever("Olá");
            editor.escrever(", Mundo");
            assertEquals("Olá, Mundo", editor.getTexto());
        }

        @Test
        @DisplayName("Deve apagar caracteres do fim")
        void deveApagarCaracteres() {
            editor.escrever("Olá, Mundo");
            editor.apagar(5);
            assertEquals("Olá, ", editor.getTexto());
        }

        @Test
        @DisplayName("Apagar mais caracteres do que existem não deve lançar exceção")
        void apagarExcessoNaoLancaExcecao() {
            editor.escrever("Hi");
            assertDoesNotThrow(() -> editor.apagar(100));
            assertEquals("", editor.getTexto());
        }
    }

    @Nested
    @DisplayName("Salvar e restaurar snapshot")
    class SalvarRestaurarTest {

        @Test
        @DisplayName("Deve restaurar o texto do snapshot")
        void deveRestaurarTexto() {
            editor.escrever("Estado A");
            ConteudoTexto snapshot = editor.salvar();
            editor.escrever(" + B");
            editor.restaurar(snapshot);
            assertEquals("Estado A", editor.getTexto());
        }

        @Test
        @DisplayName("Deve restaurar a posição do cursor")
        void deveRestaurarCursor() {
            editor.escrever("Hello");
            ConteudoTexto snapshot = editor.salvar();
            editor.apagar(5);
            editor.restaurar(snapshot);
            assertEquals(5, editor.getPosicaoCursor());
        }

        @Test
        @DisplayName("Snapshots são imutáveis — restaurar não altera o snapshot")
        void snapshotEImutavel() {
            editor.escrever("Imutável");
            ConteudoTexto snapshot = editor.salvar();
            editor.escrever(" alterado");
            assertEquals("Imutável", snapshot.getTexto());
        }
    }

    @Nested
    @DisplayName("Desfazer (Undo)")
    class DesfazerTest {

        @Test
        @DisplayName("Deve desfazer a última edição")
        void deveDesfazerUltimaEdicao() {
            historico.salvar();
            editor.escrever("Versão 1");
            historico.salvar();
            editor.escrever(" + extra");
            historico.desfazer();
            assertEquals("Versão 1", editor.getTexto());
        }

        @Test
        @DisplayName("Deve desfazer múltiplas edições em sequência")
        void deveDesfazerMultiplasEdicoes() {
            historico.salvar();
            editor.escrever("A");
            historico.salvar();
            editor.escrever("B");
            historico.salvar();
            editor.escrever("C");
            historico.desfazer();
            historico.desfazer();
            assertEquals("A", editor.getTexto());
        }

        @Test
        @DisplayName("Desfazer sem histórico não deve lançar exceção")
        void desfazerSemHistoricoNaoLancaExcecao() {
            assertDoesNotThrow(() -> historico.desfazer());
        }

        @Test
        @DisplayName("podeDesfazer deve retornar false quando histórico está vazio")
        void podeDesfazerRetornaFalseQuandoVazio() {
            assertFalse(historico.podeDesfazer());
        }
    }

    @Nested
    @DisplayName("Refazer (Redo)")
    class RefazerTest {

        @Test
        @DisplayName("Deve refazer após desfazer")
        void deveRefazerAposDesfazer() {
            historico.salvar();
            editor.escrever("Versão 1");
            historico.salvar();
            editor.escrever(" + extra");
            historico.desfazer();
            historico.refazer();
            assertEquals("Versão 1 + extra", editor.getTexto());
        }

        @Test
        @DisplayName("Nova edição após undo deve limpar o histórico de redo")
        void novaEdicaoLimpaRedo() {
            historico.salvar();
            editor.escrever("A");
            historico.salvar();
            editor.escrever("B");
            historico.desfazer();
            historico.salvar();
            editor.escrever("C");
            assertFalse(historico.podeRefazer());
        }

        @Test
        @DisplayName("Refazer sem futuros não deve lançar exceção")
        void refazerSemFuturosNaoLancaExcecao() {
            assertDoesNotThrow(() -> historico.refazer());
        }
    }
}
