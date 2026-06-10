package com.padroes.interpreter;

public class Main {
    public static void main(String[] args) {
        Contexto ctx = new Contexto();
        ctx.definir("x", 10);
        ctx.definir("y", 3);

        // (x + 3) * 2 - y = (10 + 3) * 2 - 3 = 23
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

        System.out.println("x = " + ctx.obter("x") + ", y = " + ctx.obter("y"));
        System.out.println("(x + 3) * 2 - y = " + expr.interpretar(ctx));

        // 100 / (5 * 4) = 5
        Expressao divisao = new ExpressaoDivisao(
            new ExpressaoNumero(100),
            new ExpressaoMultiplicacao(
                new ExpressaoNumero(5),
                new ExpressaoNumero(4)
            )
        );
        System.out.println("100 / (5 * 4) = " + divisao.interpretar(ctx));
    }
}
