package com.padroes.proxy;

public interface ServicoClima {
    double obterTemperatura(String cidade);
    Previsao obterPrevisao(String cidade);
}
