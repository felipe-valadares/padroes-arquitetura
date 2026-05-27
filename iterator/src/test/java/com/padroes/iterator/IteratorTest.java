package com.padroes.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Iterator — Playlist de Músicas")
class IteratorTest {

    private Playlist playlist;
    private Musica bohemian;
    private Musica hotel;
    private Musica stairway;

    @BeforeEach
    void setUp() {
        playlist = new Playlist();
        bohemian = new Musica("Bohemian Rhapsody", "Queen", 354);
        hotel    = new Musica("Hotel California", "Eagles", 391);
        stairway = new Musica("Stairway to Heaven", "Led Zeppelin", 482);
        playlist.adicionar(bohemian);
        playlist.adicionar(hotel);
        playlist.adicionar(stairway);
    }

    @Nested
    @DisplayName("IteradorSequencial")
    class IteradorSequencialTest {

        @Test
        @DisplayName("Deve percorrer músicas na ordem de inserção")
        void devePercorrerEmOrdem() {
            Iterador<Musica> it = playlist.criarIterador();
            assertEquals(bohemian, it.proximo());
            assertEquals(hotel,    it.proximo());
            assertEquals(stairway, it.proximo());
        }

        @Test
        @DisplayName("temProximo() retorna false após última música")
        void temProximoFalseAposUltima() {
            Iterador<Musica> it = playlist.criarIterador();
            it.proximo(); it.proximo(); it.proximo();
            assertFalse(it.temProximo());
        }

        @Test
        @DisplayName("reiniciar() permite reiterar do início")
        void reiniciarPermiteReiterarDoInicio() {
            Iterador<Musica> it = playlist.criarIterador();
            it.proximo(); it.proximo(); it.proximo();
            it.reiniciar();
            assertTrue(it.temProximo());
            assertEquals(bohemian, it.proximo());
        }

        @Test
        @DisplayName("Playlist vazia: temProximo() retorna false imediatamente")
        void playlistVaziaTemProximoFalse() {
            assertFalse(new Playlist().criarIterador().temProximo());
        }
    }

    @Nested
    @DisplayName("IteradorEmbaralhado")
    class IteradorEmbaralhadoTest {

        @Test
        @DisplayName("Seed fixo produz ordem determinística")
        void seedFixoProduzOrdemDeterministica() {
            Iterador<Musica> it1 = playlist.criarIteradorEmbaralhado(new Random(42));
            Iterador<Musica> it2 = playlist.criarIteradorEmbaralhado(new Random(42));

            List<Musica> r1 = new ArrayList<>();
            List<Musica> r2 = new ArrayList<>();
            while (it1.temProximo()) r1.add(it1.proximo());
            while (it2.temProximo()) r2.add(it2.proximo());

            assertEquals(r1, r2);
        }

        @Test
        @DisplayName("Percorre todas as músicas exatamente uma vez")
        void percorreTodasUmaVez() {
            Iterador<Musica> it = playlist.criarIteradorEmbaralhado(new Random(99));
            List<Musica> resultado = new ArrayList<>();
            while (it.temProximo()) resultado.add(it.proximo());
            assertEquals(3, resultado.size());
            assertTrue(resultado.containsAll(List.of(bohemian, hotel, stairway)));
        }

        @Test
        @DisplayName("temProximo() retorna false após percorrer todas")
        void temProximoFalseAposPercorrerTodas() {
            Iterador<Musica> it = playlist.criarIteradorEmbaralhado(new Random(7));
            while (it.temProximo()) it.proximo();
            assertFalse(it.temProximo());
        }

        @Test
        @DisplayName("reiniciar() permite percorrer todas novamente")
        void reiniciarPermitePercorrerNovamente() {
            Iterador<Musica> it = playlist.criarIteradorEmbaralhado(new Random(13));
            while (it.temProximo()) it.proximo();
            it.reiniciar();
            List<Musica> resultado = new ArrayList<>();
            while (it.temProximo()) resultado.add(it.proximo());
            assertEquals(3, resultado.size());
            assertTrue(resultado.containsAll(List.of(bohemian, hotel, stairway)));
        }
    }
}
