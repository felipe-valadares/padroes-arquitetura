package com.padroes.proxy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Proxy — Serviço de Clima com Cache")
class ProxyTest {

    private ServicoClimaReal real;
    private ProxyServicoClima proxy;

    @BeforeEach
    void setUp() {
        real = new ServicoClimaReal();
        proxy = new ProxyServicoClima(real);
    }

    @Nested
    @DisplayName("Comportamento de cache")
    class CacheTest {

        @Test
        @DisplayName("Segunda chamada para mesma cidade não acessa o serviço real")
        void segundaChamadaUsaCache() {
            proxy.obterTemperatura("São Paulo");
            proxy.obterTemperatura("São Paulo");
            assertEquals(1, real.getChamadas());
        }

        @Test
        @DisplayName("Cidades diferentes fazem chamadas independentes ao serviço real")
        void cidadesDiferentesFazemChamadasIndependentes() {
            proxy.obterTemperatura("São Paulo");
            proxy.obterTemperatura("Rio de Janeiro");
            assertEquals(2, real.getChamadas());
        }

        @Test
        @DisplayName("Cache de previsão é independente do cache de temperatura")
        void cachePrevisoIndependenteDeTemperatura() {
            proxy.obterTemperatura("Curitiba");
            proxy.obterPrevisao("Curitiba");
            assertEquals(2, real.getChamadas());

            proxy.obterTemperatura("Curitiba");
            proxy.obterPrevisao("Curitiba");
            assertEquals(2, real.getChamadas());
        }

        @Test
        @DisplayName("Após limparCache(), próxima chamada acessa o serviço real novamente")
        void limparCacheForceNovaConsulta() {
            proxy.obterTemperatura("São Paulo");
            proxy.limparCache();
            proxy.obterTemperatura("São Paulo");
            assertEquals(2, real.getChamadas());
        }
    }

    @Nested
    @DisplayName("Log de acessos")
    class LogTest {

        @Test
        @DisplayName("Primeira chamada registra CHAMADA REAL no log")
        void primeiraChamadaRegistraLog() {
            proxy.obterTemperatura("São Paulo");
            assertTrue(proxy.getLog().get(0).contains("CHAMADA REAL"));
        }

        @Test
        @DisplayName("Segunda chamada registra CACHE HIT no log")
        void segundaChamadaRegistraCacheHit() {
            proxy.obterTemperatura("São Paulo");
            proxy.obterTemperatura("São Paulo");
            assertTrue(proxy.getLog().get(1).contains("CACHE HIT"));
        }
    }

    @Nested
    @DisplayName("Transparência da interface")
    class TransparenciaTest {

        @Test
        @DisplayName("Proxy retorna a mesma temperatura que o serviço real")
        void proxyRetornaMesmaTemperatura() {
            assertEquals(22.5, proxy.obterTemperatura("São Paulo"));
        }

        @Test
        @DisplayName("Previsão do proxy tem os mesmos dados que a do serviço real")
        void previsaoProxyMesmosValores() {
            Previsao previsao = proxy.obterPrevisao("Curitiba");
            assertEquals("Curitiba", previsao.getCidade());
            assertEquals(15.0, previsao.getTemperatura());
            assertEquals("Frio", previsao.getCondicao());
        }
    }
}
