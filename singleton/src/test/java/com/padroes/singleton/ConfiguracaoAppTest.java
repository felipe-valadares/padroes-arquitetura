package com.padroes.singleton;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ConfiguracaoApp - Singleton")
class ConfiguracaoAppTest {

    @BeforeEach
    void restaurarPropriedadePadrao() {
        ConfiguracaoApp.getInstancia().setPropriedade("ambiente", "desenvolvimento");
    }

    @Test
    @DisplayName("Deve retornar sempre a mesma instância")
    void deveRetornarMesmaInstancia() {
        assertSame(ConfiguracaoApp.getInstancia(), ConfiguracaoApp.getInstancia());
    }

    @Test
    @DisplayName("Deve compartilhar estado entre referências")
    void deveCompartilharEstadoEntreReferencias() {
        ConfiguracaoApp.getInstancia().setPropriedade("chave-teste", "valor-compartilhado");
        assertEquals("valor-compartilhado", ConfiguracaoApp.getInstancia().getPropriedade("chave-teste"));
    }

    @Test
    @DisplayName("Deve conter propriedades padrão após inicialização")
    void deveConterPropriedadesPadrao() {
        ConfiguracaoApp config = ConfiguracaoApp.getInstancia();
        assertAll(
            () -> assertEquals("1.0.0", config.getPropriedade("versao")),
            () -> assertTrue(config.contemPropriedade("timeout")),
            () -> assertTrue(config.contemPropriedade("max-conexoes"))
        );
    }

    @Test
    @DisplayName("Deve retornar string vazia para propriedade inexistente")
    void deveRetornarVazioParaPropriedadeInexistente() {
        assertEquals("", ConfiguracaoApp.getInstancia().getPropriedade("nao-existe"));
    }

    @Test
    @DisplayName("Deve permitir sobrescrever propriedade existente")
    void deveSobrescreverPropriedade() {
        ConfiguracaoApp.getInstancia().setPropriedade("ambiente", "producao");
        assertEquals("producao", ConfiguracaoApp.getInstancia().getPropriedade("ambiente"));
    }

    @Test
    @DisplayName("getTodasPropriedades deve retornar mapa imutável")
    void getTodasPropriedadesDeveSerImutavel() {
        assertThrows(UnsupportedOperationException.class,
            () -> ConfiguracaoApp.getInstancia().getTodasPropriedades().put("nova", "valor"));
    }

    @Test
    @DisplayName("Construtor deve ser privado")
    void construtorDeveSerPrivado() throws NoSuchMethodException {
        Constructor<ConfiguracaoApp> construtor = ConfiguracaoApp.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(construtor.getModifiers()));
    }

    @Test
    @DisplayName("Deve ser thread-safe")
    void deveSerThreadSafe() throws InterruptedException {
        int numeroThreads = 20;
        Set<ConfiguracaoApp> instancias = Collections.newSetFromMap(new ConcurrentHashMap<>());
        CountDownLatch largada = new CountDownLatch(1);
        CountDownLatch conclusao = new CountDownLatch(numeroThreads);
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < numeroThreads; i++) {
            threads.add(new Thread(() -> {
                try {
                    largada.await();
                    instancias.add(ConfiguracaoApp.getInstancia());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    conclusao.countDown();
                }
            }));
        }

        threads.forEach(Thread::start);
        largada.countDown();
        conclusao.await();

        assertEquals(1, instancias.size());
    }
}
