package com.padroes.abstractfactory;

import com.padroes.abstractfactory.fabricas.FabricaMac;
import com.padroes.abstractfactory.fabricas.FabricaUI;
import com.padroes.abstractfactory.fabricas.FabricaWindows;
import com.padroes.abstractfactory.produtos.Botao;
import com.padroes.abstractfactory.produtos.CaixaSelecao;
import com.padroes.abstractfactory.produtos.mac.BotaoMac;
import com.padroes.abstractfactory.produtos.mac.CaixaSelecaoMac;
import com.padroes.abstractfactory.produtos.windows.BotaoWindows;
import com.padroes.abstractfactory.produtos.windows.CaixaSelecaoWindows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Abstract Factory - Fábricas de UI")
class AbstractFactoryTest {

    @Nested
    @DisplayName("FabricaWindows")
    class FabricaWindowsTest {

        private final FabricaUI fabrica = new FabricaWindows();

        @Test
        @DisplayName("Deve criar BotaoWindows")
        void deveCriarBotaoWindows() {
            assertInstanceOf(BotaoWindows.class, fabrica.criarBotao());
        }

        @Test
        @DisplayName("Deve criar CaixaSelecaoWindows")
        void deveCriarCaixaSelecaoWindows() {
            assertInstanceOf(CaixaSelecaoWindows.class, fabrica.criarCaixaSelecao());
        }

        @Test
        @DisplayName("Botão Windows deve conter 'Windows' na renderização")
        void botaoWindowsDeveConterWindows() {
            assertTrue(fabrica.criarBotao().renderizar().contains("Windows"));
        }

        @Test
        @DisplayName("Checkbox Windows deve conter 'Windows' na renderização")
        void caixaWindowsDeveConterWindows() {
            assertTrue(fabrica.criarCaixaSelecao().renderizar().contains("Windows"));
        }
    }

    @Nested
    @DisplayName("FabricaMac")
    class FabricaMacTest {

        private final FabricaUI fabrica = new FabricaMac();

        @Test
        @DisplayName("Deve criar BotaoMac")
        void deveCriarBotaoMac() {
            assertInstanceOf(BotaoMac.class, fabrica.criarBotao());
        }

        @Test
        @DisplayName("Deve criar CaixaSelecaoMac")
        void deveCriarCaixaSelecaoMac() {
            assertInstanceOf(CaixaSelecaoMac.class, fabrica.criarCaixaSelecao());
        }

        @Test
        @DisplayName("Botão Mac deve conter 'macOS' na renderização")
        void botaoMacDeveConterMacOS() {
            assertTrue(fabrica.criarBotao().renderizar().contains("macOS"));
        }
    }

    @Nested
    @DisplayName("CaixaSelecao - comportamento")
    class CaixaSelecaoComportamentoTest {

        @Test
        @DisplayName("Deve iniciar desmarcada")
        void deveIniciarDesmarcada() {
            assertFalse(new FabricaWindows().criarCaixaSelecao().estaMarcada());
        }

        @Test
        @DisplayName("Deve marcar ao alternar estado pela primeira vez")
        void deveMarcarAoAlternarPrimeiraVez() {
            CaixaSelecao caixa = new FabricaMac().criarCaixaSelecao();
            assertTrue(caixa.alternarEstado());
            assertTrue(caixa.estaMarcada());
        }

        @Test
        @DisplayName("Deve desmarcar ao alternar estado duas vezes")
        void deveDesmarcarAoAlternarDuasVezes() {
            CaixaSelecao caixa = new FabricaWindows().criarCaixaSelecao();
            caixa.alternarEstado();
            caixa.alternarEstado();
            assertFalse(caixa.estaMarcada());
        }
    }

    @Nested
    @DisplayName("Aplicacao - cliente da fábrica")
    class AplicacaoTest {

        @Test
        @DisplayName("Aplicação com Windows deve construir UI Windows")
        void aplicacaoWindowsDeveConstruirUIWindows() {
            assertTrue(new Aplicacao(new FabricaWindows()).construirUI().contains("Windows"));
        }

        @Test
        @DisplayName("Aplicação com Mac deve construir UI Mac")
        void aplicacaoMacDeveConstruirUIMac() {
            assertTrue(new Aplicacao(new FabricaMac()).construirUI().contains("macOS"));
        }

        @Test
        @DisplayName("Não deve mesclar componentes de plataformas diferentes")
        void naoDeveMisturarPlataformas() {
            String uiWindows = new Aplicacao(new FabricaWindows()).construirUI();
            String uiMac = new Aplicacao(new FabricaMac()).construirUI();
            assertFalse(uiWindows.contains("macOS"));
            assertFalse(uiMac.contains("Windows"));
        }
    }
}
