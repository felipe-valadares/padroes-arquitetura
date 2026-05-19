package com.padroes.visitor;

public interface Visitante {
    void visitar(Receita receita);
    void visitar(Despesa despesa);
    void visitar(Investimento investimento);
}
