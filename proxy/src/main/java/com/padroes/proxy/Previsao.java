package com.padroes.proxy;

public class Previsao {
    private final String cidade;
    private final double temperatura;
    private final String condicao;

    public Previsao(String cidade, double temperatura, String condicao) {
        this.cidade = cidade;
        this.temperatura = temperatura;
        this.condicao = condicao;
    }

    public String getCidade() { return cidade; }
    public double getTemperatura() { return temperatura; }
    public String getCondicao() { return condicao; }

    @Override
    public String toString() {
        return cidade + ": " + temperatura + "°C, " + condicao;
    }
}
