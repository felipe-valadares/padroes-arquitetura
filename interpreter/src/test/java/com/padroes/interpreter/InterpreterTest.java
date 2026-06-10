package com.padroes.interpreter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Interpreter — Calculadora de Expressões")
class InterpreterTest {

    private Contexto ctx;

    @BeforeEach
    void setUp() {
        ctx = new Contexto();
    }

    @Nested
    @DisplayName("ExpressaoNumero")
    class ExpressaoNumeroTest {

        @Test
        @DisplayName("Deve retornar o literal inteiro")
        void deveRetornarLiteral() {
            assertEquals(42, new ExpressaoNumero(42).interpretar(ctx));
        }

        @Test
        @DisplayName("Deve retornar zero")
        void deveRetornarZero() {
            assertEquals(0, new ExpressaoNumero(0).interpretar(ctx));
        }

        @Test
        @DisplayName("Deve retornar valor negativo")
        void deveRetornarNegativo() {
            assertEquals(-5, new ExpressaoNumero(-5).interpretar(ctx));
        }
    }

    @Nested
    @DisplayName("ExpressaoVariavel")
    class ExpressaoVariavelTest {

        @Test
        @DisplayName("Deve retornar valor definido no contexto")
        void deveRetornarValorDoContexto() {
            ctx.definir("x", 7);
            assertEquals(7, new ExpressaoVariavel("x").interpretar(ctx));
        }

        @Test
        @DisplayName("Deve lançar exceção para variável não definida")
        void deveLancarExcecaoParaVariavelNaoDefinida() {
            assertThrows(IllegalArgumentException.class,
                () -> new ExpressaoVariavel("z").interpretar(ctx));
        }
    }

    @Nested
    @DisplayName("Operações básicas")
    class OperacoesBasicasTest {

        @Test
        @DisplayName("Soma: 3 + 4 = 7")
        void soma() {
            Expressao expr = new ExpressaoSoma(new ExpressaoNumero(3), new ExpressaoNumero(4));
            assertEquals(7, expr.interpretar(ctx));
        }

        @Test
        @DisplayName("Subtração: 10 - 3 = 7")
        void subtracao() {
            Expressao expr = new ExpressaoSubtracao(new ExpressaoNumero(10), new ExpressaoNumero(3));
            assertEquals(7, expr.interpretar(ctx));
        }

        @Test
        @DisplayName("Multiplicação: 6 * 7 = 42")
        void multiplicacao() {
            Expressao expr = new ExpressaoMultiplicacao(new ExpressaoNumero(6), new ExpressaoNumero(7));
            assertEquals(42, expr.interpretar(ctx));
        }

        @Test
        @DisplayName("Divisão: 20 / 4 = 5")
        void divisao() {
            Expressao expr = new ExpressaoDivisao(new ExpressaoNumero(20), new ExpressaoNumero(4));
            assertEquals(5, expr.interpretar(ctx));
        }
    }

    @Nested
    @DisplayName("Expressões compostas")
    class ExpressoesCompostasTest {

        @Test
        @DisplayName("(x + 3) * 2 - y com x=10, y=3 deve ser 23")
        void expressaoComVariaveis() {
            ctx.definir("x", 10);
            ctx.definir("y", 3);
            // (10 + 3) * 2 - 3 = 13 * 2 - 3 = 26 - 3 = 23
            Expressao expr = new ExpressaoSubtracao(
                new ExpressaoMultiplicacao(
                    new ExpressaoSoma(
                        new ExpressaoVariavel("x"),
                        new ExpressaoNumero(3)
                    ),
                    new ExpressaoNumero(2)
                ),
                new ExpressaoVariavel("y")
            );
            assertEquals(23, expr.interpretar(ctx));
        }

        @Test
        @DisplayName("100 / (5 * 4) deve ser 5")
        void divisaoComMultiplicacaoAninhada() {
            Expressao expr = new ExpressaoDivisao(
                new ExpressaoNumero(100),
                new ExpressaoMultiplicacao(
                    new ExpressaoNumero(5),
                    new ExpressaoNumero(4)
                )
            );
            assertEquals(5, expr.interpretar(ctx));
        }

        @Test
        @DisplayName("Mesma expressão com contextos diferentes produz resultados diferentes")
        void mesmaExpressaoContextosDiferentes() {
            Expressao expr = new ExpressaoSoma(
                new ExpressaoVariavel("a"),
                new ExpressaoVariavel("b")
            );

            ctx.definir("a", 1);
            ctx.definir("b", 2);
            assertEquals(3, expr.interpretar(ctx));

            Contexto ctx2 = new Contexto();
            ctx2.definir("a", 100);
            ctx2.definir("b", 200);
            assertEquals(300, expr.interpretar(ctx2));
        }

        @Test
        @DisplayName("((2 + 3) * (4 - 1)) / 5 deve ser 3")
        void expressaoProfundamenteAninhada() {
            // ((2 + 3) * (4 - 1)) / 5 = (5 * 3) / 5 = 15 / 5 = 3
            Expressao expr = new ExpressaoDivisao(
                new ExpressaoMultiplicacao(
                    new ExpressaoSoma(new ExpressaoNumero(2), new ExpressaoNumero(3)),
                    new ExpressaoSubtracao(new ExpressaoNumero(4), new ExpressaoNumero(1))
                ),
                new ExpressaoNumero(5)
            );
            assertEquals(3, expr.interpretar(ctx));
        }
    }
}
