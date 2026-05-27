package com.padroes.prototype;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Prototype — Personagens de RPG")
class PrototypeTest {

    @Nested
    @DisplayName("Clonagem — Guerreiro")
    class GuerreiroTest {

        private Guerreiro template;

        @BeforeEach
        void setUp() {
            template = new Guerreiro("Guerreiro", 150, 80, 100);
        }

        @Test
        @DisplayName("Clone é objeto diferente do original")
        void cloneEObjetoDiferente() {
            assertNotSame(template, template.clonar());
        }

        @Test
        @DisplayName("Clone tem os mesmos atributos do original")
        void cloneTemMesmosAtributos() {
            Personagem clone = template.clonar();
            assertEquals(template.getNome(),   clone.getNome());
            assertEquals(template.getVida(),   clone.getVida());
            assertEquals(template.getAtaque(), clone.getAtaque());
            assertEquals(template.getDefesa(), clone.getDefesa());
        }

        @Test
        @DisplayName("Modificar o clone não altera o original")
        void modificarCloneNaoAlteraOriginal() {
            Guerreiro clone = (Guerreiro) template.clonar();
            clone.setNome("Thorin");
            clone.setVida(50);
            assertEquals("Guerreiro", template.getNome());
            assertEquals(150, template.getVida());
        }
    }

    @Nested
    @DisplayName("Clonagem — Arqueiro (campo extra)")
    class ArqueiroTest {

        @Test
        @DisplayName("Clone de Arqueiro preserva campo alcance")
        void clonePreservaAlcance() {
            Arqueiro template = new Arqueiro("Arqueiro", 100, 100, 70, 30);
            Arqueiro clone = (Arqueiro) template.clonar();
            assertEquals(30, clone.getAlcance());
            assertNotSame(template, clone);
        }
    }

    @Nested
    @DisplayName("RegistroPersonagens")
    class RegistroTest {

        private RegistroPersonagens registro;

        @BeforeEach
        void setUp() {
            registro = new RegistroPersonagens();
            registro.registrar("guerreiro", new Guerreiro("Guerreiro", 150, 80, 100));
            registro.registrar("mago",      new Mago("Mago", 80, 150, 40));
        }

        @Test
        @DisplayName("obter() retorna novo clone a cada chamada")
        void obterRetornaNovoCloneCadaChamada() {
            assertNotSame(registro.obter("guerreiro"), registro.obter("guerreiro"));
        }

        @Test
        @DisplayName("Clone mantém atributos do template")
        void cloneMantemAtributosDoTemplate() {
            Personagem guerreiro = registro.obter("guerreiro");
            assertEquals(150, guerreiro.getVida());
            assertEquals(80,  guerreiro.getAtaque());
        }

        @Test
        @DisplayName("Modificar clone não altera o próximo clone do registro")
        void modificarCloneNaoAlteraProximoClone() {
            Guerreiro g1 = (Guerreiro) registro.obter("guerreiro");
            g1.setVida(1);
            Guerreiro g2 = (Guerreiro) registro.obter("guerreiro");
            assertEquals(150, g2.getVida());
        }

        @Test
        @DisplayName("Chave inexistente lança IllegalArgumentException")
        void chaveInexistenteLancaExcecao() {
            assertThrows(IllegalArgumentException.class, () -> registro.obter("ladrao"));
        }
    }
}
