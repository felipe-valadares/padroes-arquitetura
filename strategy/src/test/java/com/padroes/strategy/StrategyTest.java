package com.padroes.strategy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Strategy — Formas de Pagamento")
class StrategyTest {

    @Nested
    @DisplayName("PagamentoCredito")
    class CreditoTest {

        @Test
        @DisplayName("Deve aplicar taxa de 3% sobre o valor")
        void aplicaTaxaDeTresPorcento() {
            var credito = new PagamentoCredito(1);
            String resultado = credito.processar(100.0);
            assertTrue(resultado.contains("103"));
        }

        @Test
        @DisplayName("Deve dividir em N parcelas")
        void divideEmParcelas() {
            var credito = new PagamentoCredito(3);
            String resultado = credito.processar(300.0);
            assertTrue(resultado.contains("3x"));
        }

        @Test
        @DisplayName("Valor da parcela deve ser total/parcelas")
        void calculaValorDaParcela() {
            var credito = new PagamentoCredito(2);
            String resultado = credito.processar(200.0);
            // Total com 3%: 206.00 → parcela: 103.00
            assertTrue(resultado.contains("103"));
        }

        @Test
        @DisplayName("getNome deve retornar nome do meio de pagamento")
        void getNomeRetornaNome() {
            assertFalse(new PagamentoCredito(1).getNome().isBlank());
        }
    }

    @Nested
    @DisplayName("PagamentoPIX")
    class PIXTest {

        @Test
        @DisplayName("Deve processar valor sem taxa adicional")
        void processaSemTaxa() {
            var pix = new PagamentoPIX();
            String resultado = pix.processar(100.0);
            assertTrue(resultado.contains("100"));
            assertFalse(resultado.contains("103"));
        }

        @Test
        @DisplayName("Deve incluir código PIX no resultado")
        void incluiCodigoPIX() {
            var pix = new PagamentoPIX();
            String resultado = pix.processar(50.0);
            assertFalse(resultado.isBlank());
            assertTrue(resultado.toLowerCase().contains("pix"));
        }

        @Test
        @DisplayName("getNome deve retornar nome do meio de pagamento")
        void getNomeRetornaNome() {
            assertFalse(new PagamentoPIX().getNome().isBlank());
        }
    }

    @Nested
    @DisplayName("PagamentoBoleto")
    class BoletoTest {

        @Test
        @DisplayName("Deve incluir valor no resultado")
        void incluiValor() {
            var boleto = new PagamentoBoleto();
            assertTrue(boleto.processar(150.0).contains("150"));
        }

        @Test
        @DisplayName("Deve incluir data de vencimento no formato dd/MM/yyyy")
        void incluiDataDeVencimento() {
            var boleto = new PagamentoBoleto();
            String resultado = boleto.processar(100.0);
            assertTrue(resultado.matches(".*\\d{2}/\\d{2}/\\d{4}.*"));
        }

        @Test
        @DisplayName("getNome deve retornar nome do meio de pagamento")
        void getNomeRetornaNome() {
            assertFalse(new PagamentoBoleto().getNome().isBlank());
        }
    }

    @Nested
    @DisplayName("CarrinhoCompras")
    class CarrinhoTest {

        @Test
        @DisplayName("Deve calcular total correto dos itens")
        void calculaTotalCorreto() {
            var carrinho = new CarrinhoCompras();
            carrinho.adicionarItem("Notebook", 3000.0);
            carrinho.adicionarItem("Mouse", 150.0);
            assertEquals(3150.0, carrinho.calcularTotal(), 0.001);
        }

        @Test
        @DisplayName("Deve finalizar compra com estratégia definida")
        void finalizaComEstrategiaPIX() {
            var carrinho = new CarrinhoCompras();
            carrinho.adicionarItem("Teclado", 200.0);
            carrinho.setEstrategia(new PagamentoPIX());
            String resultado = carrinho.finalizarCompra();
            assertFalse(resultado.isBlank());
            assertTrue(resultado.contains("200"));
        }

        @Test
        @DisplayName("Deve permitir trocar estratégia em runtime")
        void trocaEstrategiaEmRuntime() {
            var carrinho = new CarrinhoCompras();
            carrinho.adicionarItem("Cadeira", 800.0);

            carrinho.setEstrategia(new PagamentoPIX());
            String resultadoPix = carrinho.finalizarCompra();

            carrinho.setEstrategia(new PagamentoCredito(2));
            String resultadoCredito = carrinho.finalizarCompra();

            assertNotEquals(resultadoPix, resultadoCredito);
        }

        @Test
        @DisplayName("Deve lançar IllegalStateException sem estratégia definida")
        void lancaExcecaoSemEstrategia() {
            var carrinho = new CarrinhoCompras();
            carrinho.adicionarItem("Produto", 100.0);
            assertThrows(IllegalStateException.class, carrinho::finalizarCompra);
        }
    }

    @Nested
    @DisplayName("Integração")
    class IntegracaoTest {

        @Test
        @DisplayName("Fluxo completo: itens + crédito parcelado")
        void fluxoCompletoCredito() {
            var carrinho = new CarrinhoCompras();
            carrinho.adicionarItem("Monitor", 1200.0);
            carrinho.adicionarItem("Webcam", 300.0);
            carrinho.setEstrategia(new PagamentoCredito(3));
            String resultado = carrinho.finalizarCompra();
            // Total: 1500 + 3% = 1545 → 3x de 515
            assertTrue(resultado.contains("3x"));
            assertTrue(resultado.contains("515"));
        }

        @Test
        @DisplayName("Estratégias diferentes produzem resultados diferentes")
        void estrategiasDiferentesProduzemResultadosDiferentes() {
            var carrinho = new CarrinhoCompras();
            carrinho.adicionarItem("Item", 100.0);

            carrinho.setEstrategia(new PagamentoPIX());
            String pix = carrinho.finalizarCompra();

            carrinho.setEstrategia(new PagamentoBoleto());
            String boleto = carrinho.finalizarCompra();

            carrinho.setEstrategia(new PagamentoCredito(1));
            String credito = carrinho.finalizarCompra();

            assertAll(
                () -> assertNotEquals(pix, boleto),
                () -> assertNotEquals(pix, credito),
                () -> assertNotEquals(boleto, credito)
            );
        }
    }
}
