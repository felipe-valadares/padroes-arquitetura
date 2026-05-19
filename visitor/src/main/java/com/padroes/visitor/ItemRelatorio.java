package com.padroes.visitor;

public interface ItemRelatorio {
    String getDescricao();
    double getValor();
    void aceitar(Visitante visitante);
}
