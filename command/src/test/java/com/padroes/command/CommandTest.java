package com.padroes.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Command — Controle Remoto")
class CommandTest {

    @Nested
    @DisplayName("Televisao")
    class TelevisaoTest {

        @Test
        @DisplayName("Ligar e desligar deve alterar estado")
        void ligarDesligarAlteraEstado() {
            Televisao tv = new Televisao();
            assertFalse(tv.isLigada());
            tv.ligar();
            assertTrue(tv.isLigada());
            tv.desligar();
            assertFalse(tv.isLigada());
        }

        @Test
        @DisplayName("Volume deve ser limitado entre 0 e 100")
        void volumeLimitadoEntreZeroECem() {
            Televisao tv = new Televisao();
            tv.setVolume(150);
            assertEquals(100, tv.getVolume());
            tv.setVolume(-5);
            assertEquals(0, tv.getVolume());
        }
    }

    @Nested
    @DisplayName("Comandos de Televisao")
    class ComandosTelevisaoTest {

        private Televisao tv;
        private ControleRemoto controle;

        @BeforeEach
        void setUp() {
            tv = new Televisao();
            controle = new ControleRemoto();
        }

        @Test
        @DisplayName("Deve ligar a TV e desfazer deve desligar")
        void ligarTvComUndo() {
            controle.executar(new ComandoLigarTelevisao(tv));
            assertTrue(tv.isLigada());
            controle.desfazer();
            assertFalse(tv.isLigada());
        }

        @Test
        @DisplayName("Deve desligar a TV e desfazer deve religar")
        void desligarTvComUndo() {
            tv.ligar();
            controle.executar(new ComandoDesligarTelevisao(tv));
            assertFalse(tv.isLigada());
            controle.desfazer();
            assertTrue(tv.isLigada());
        }

        @Test
        @DisplayName("Deve ajustar volume e desfazer deve restaurar volume anterior")
        void ajustarVolumeComUndo() {
            tv.setVolume(10);
            controle.executar(new ComandoAjustarVolume(tv, 50));
            assertEquals(50, tv.getVolume());
            controle.desfazer();
            assertEquals(10, tv.getVolume());
        }
    }

    @Nested
    @DisplayName("Comandos de LuzInteligente")
    class ComandosLuzTest {

        private LuzInteligente luz;
        private ControleRemoto controle;

        @BeforeEach
        void setUp() {
            luz = new LuzInteligente();
            controle = new ControleRemoto();
        }

        @Test
        @DisplayName("Deve ligar a luz e desfazer deve apagar")
        void ligarLuzComUndo() {
            controle.executar(new ComandoLigarLuz(luz));
            assertTrue(luz.isLigada());
            controle.desfazer();
            assertFalse(luz.isLigada());
        }

        @Test
        @DisplayName("Deve apagar a luz e desfazer deve religar")
        void desligarLuzComUndo() {
            luz.ligar();
            controle.executar(new ComandoDesligarLuz(luz));
            assertFalse(luz.isLigada());
            controle.desfazer();
            assertTrue(luz.isLigada());
        }

        @Test
        @DisplayName("Deve ajustar intensidade e desfazer deve restaurar valor anterior")
        void ajustarIntensidadeComUndo() {
            luz.setIntensidade(100);
            controle.executar(new ComandoAjustarIntensidade(luz, 30));
            assertEquals(30, luz.getIntensidade());
            controle.desfazer();
            assertEquals(100, luz.getIntensidade());
        }
    }

    @Nested
    @DisplayName("ControleRemoto — histórico")
    class ControleRemotoHistoricoTest {

        @Test
        @DisplayName("Múltiplos desfazeres ocorrem na ordem inversa de execução")
        void multiplosUndosEmOrdemInversa() {
            Televisao tv = new Televisao();
            ControleRemoto controle = new ControleRemoto();

            controle.executar(new ComandoLigarTelevisao(tv));
            controle.executar(new ComandoAjustarVolume(tv, 80));

            assertEquals(80, tv.getVolume());
            assertTrue(tv.isLigada());

            controle.desfazer();
            assertEquals(10, tv.getVolume());

            controle.desfazer();
            assertFalse(tv.isLigada());
        }

        @Test
        @DisplayName("desfazer() sem histórico não lança exceção")
        void desfazerSemHistoricoNaoLancaExcecao() {
            ControleRemoto controle = new ControleRemoto();
            assertDoesNotThrow(controle::desfazer);
        }

        @Test
        @DisplayName("temHistorico() reflete estado correto do histórico")
        void temHistoricoRefleteEstado() {
            Televisao tv = new Televisao();
            ControleRemoto controle = new ControleRemoto();
            assertFalse(controle.temHistorico());
            controle.executar(new ComandoLigarTelevisao(tv));
            assertTrue(controle.temHistorico());
            controle.desfazer();
            assertFalse(controle.temHistorico());
        }
    }
}
