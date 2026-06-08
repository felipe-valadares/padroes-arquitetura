package com.padroes.adapter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Adapter — Sistema de Pagamento")
class AdapterTest {

    private final Pagamento pagamento = new Pagamento(99.90, "Assinatura mensal", "loja@exemplo.com");

    @Nested
    @DisplayName("AdaptadorPagSeguro")
    class AdaptadorPagSeguroTest {

        @Test
        @DisplayName("Deve processar pagamento e retornar recibo aprovado")
        void deveProcessarERetornarRecibo() {
            ProcessadorPagamento processador = new AdaptadorPagSeguro(new PagSeguroCliente());
            Recibo recibo = processador.processar(pagamento);
            assertNotNull(recibo.getId());
            assertEquals("APROVADO", recibo.getStatus());
            assertEquals(99.90, recibo.getValor());
        }

        @Test
        @DisplayName("Id do recibo deve ter prefixo PS-")
        void idDeveConterPrefixoPS() {
            ProcessadorPagamento processador = new AdaptadorPagSeguro(new PagSeguroCliente());
            Recibo recibo = processador.processar(pagamento);
            assertTrue(recibo.getId().startsWith("PS-"));
        }
    }

    @Nested
    @DisplayName("AdaptadorStripe")
    class AdaptadorStripeTest {

        @Test
        @DisplayName("Deve processar pagamento e retornar recibo aprovado")
        void deveProcessarERetornarRecibo() {
            ProcessadorPagamento processador = new AdaptadorStripe(new StripeCliente());
            Recibo recibo = processador.processar(pagamento);
            assertNotNull(recibo.getId());
            assertEquals("APROVADO", recibo.getStatus());
            assertEquals(99.90, recibo.getValor());
        }

        @Test
        @DisplayName("Id do recibo deve ter prefixo ch_")
        void idDeveConterPrefixoCh() {
            ProcessadorPagamento processador = new AdaptadorStripe(new StripeCliente());
            Recibo recibo = processador.processar(pagamento);
            assertTrue(recibo.getId().startsWith("ch_"));
        }
    }

    @Test
    @DisplayName("Ambos os adaptadores implementam a mesma interface e retornam recibo aprovado")
    void ambosAdaptadoresImplementamMesmaInterface() {
        ProcessadorPagamento ps = new AdaptadorPagSeguro(new PagSeguroCliente());
        ProcessadorPagamento stripe = new AdaptadorStripe(new StripeCliente());

        Recibo reciboPs = ps.processar(pagamento);
        Recibo reciboStripe = stripe.processar(pagamento);

        assertEquals("APROVADO", reciboPs.getStatus());
        assertEquals("APROVADO", reciboStripe.getStatus());
        assertEquals(reciboPs.getValor(), reciboStripe.getValor());
    }
}
