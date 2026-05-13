package com.padroes.composite;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Composite — Sistema de Arquivos")
class CompositeTest {

    @Nested
    @DisplayName("Arquivo")
    class ArquivoTest {

        @Test
        @DisplayName("Deve retornar o nome correto")
        void deveRetornarNome() {
            assertEquals("dados.txt", new Arquivo("dados.txt", 500).getNome());
        }

        @Test
        @DisplayName("Deve retornar o tamanho correto")
        void deveRetornarTamanho() {
            assertEquals(500L, new Arquivo("dados.txt", 500).getTamanho());
        }

        @Test
        @DisplayName("listar deve conter o nome do arquivo")
        void listarDeveConterNome() {
            assertTrue(new Arquivo("dados.txt", 500).listar("").contains("dados.txt"));
        }

        @Test
        @DisplayName("listar deve conter o tamanho")
        void listarDeveConterTamanho() {
            assertTrue(new Arquivo("dados.txt", 500).listar("").contains("500"));
        }
    }

    @Nested
    @DisplayName("Diretorio")
    class DiretorioTest {

        @Test
        @DisplayName("Diretório vazio deve ter tamanho zero")
        void diretorioVazioTemTamanhoZero() {
            assertEquals(0L, new Diretorio("vazio").getTamanho());
        }

        @Test
        @DisplayName("Tamanho deve ser a soma dos filhos")
        void tamanhoDeveSerSomaFilhos() {
            Diretorio dir = new Diretorio("dir");
            dir.adicionar(new Arquivo("a.txt", 100));
            dir.adicionar(new Arquivo("b.txt", 200));
            assertEquals(300L, dir.getTamanho());
        }

        @Test
        @DisplayName("listar deve conter o nome do diretório")
        void listarDeveConterNomeDiretorio() {
            assertTrue(new Diretorio("src").listar("").contains("src"));
        }

        @Test
        @DisplayName("listar deve incluir nomes dos filhos")
        void listarDeveIncluirFilhos() {
            Diretorio dir = new Diretorio("src");
            dir.adicionar(new Arquivo("Main.java", 100));
            assertTrue(dir.listar("").contains("Main.java"));
        }

        @Test
        @DisplayName("remover deve excluir o componente")
        void removerDeveExcluirComponente() {
            Diretorio dir = new Diretorio("src");
            Arquivo arquivo = new Arquivo("temp.txt", 100);
            dir.adicionar(arquivo);
            dir.remover(arquivo);
            assertEquals(0L, dir.getTamanho());
        }
    }

    @Nested
    @DisplayName("Comportamento Composite")
    class ComportamentoCompositeTest {

        @Test
        @DisplayName("Deve tratar Arquivo e Diretorio pela mesma interface")
        void deveTratarUniformemente() {
            ComponenteSistema arquivo = new Arquivo("a.txt", 100);
            Diretorio dir = new Diretorio("dir");
            dir.adicionar(arquivo);
            Diretorio raiz = new Diretorio("raiz");
            raiz.adicionar(dir);
            String listagem = raiz.listar("");
            assertTrue(listagem.contains("raiz"));
            assertTrue(listagem.contains("dir"));
            assertTrue(listagem.contains("a.txt"));
        }

        @Test
        @DisplayName("Tamanho deve ser calculado recursivamente em profundidade arbitrária")
        void tamanhoRecursivoEmProfundidadeArbitraria() {
            Diretorio nivel3 = new Diretorio("nivel3");
            nivel3.adicionar(new Arquivo("deep.txt", 50));

            Diretorio nivel2 = new Diretorio("nivel2");
            nivel2.adicionar(nivel3);

            Diretorio nivel1 = new Diretorio("nivel1");
            nivel1.adicionar(nivel2);
            nivel1.adicionar(new Arquivo("raso.txt", 150));

            assertEquals(200L, nivel1.getTamanho());
        }

        @Test
        @DisplayName("listar deve indentar filhos por nível")
        void listarDeveIndentarPorNivel() {
            Diretorio pai = new Diretorio("pai");
            pai.adicionar(new Arquivo("filho.txt", 10));
            String listagem = pai.listar("");
            assertTrue(listagem.contains("pai"));
            String linhaFilho = listagem.lines()
                .filter(l -> l.contains("filho.txt"))
                .findFirst().orElse("");
            assertTrue(linhaFilho.startsWith("  "), "Filho deve ser indentado com espaços");
        }
    }
}
