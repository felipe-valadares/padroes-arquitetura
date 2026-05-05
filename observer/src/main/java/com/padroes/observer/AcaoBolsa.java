package com.padroes.observer;

import java.util.ArrayList;
import java.util.List;

public class AcaoBolsa implements Observavel {
    private final String ticker;
    private double preco;
    private final List<Observador> observadores = new ArrayList<>();

    public AcaoBolsa(String ticker, double precoInicial) {
        this.ticker = ticker;
        this.preco = precoInicial;
    }

    @Override
    public void adicionar(Observador observador) {
        observadores.add(observador);
    }

    @Override
    public void remover(Observador observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificar() {
        for (Observador o : observadores) {
            o.atualizar(ticker, preco);
        }
    }

    public void setPreco(double novoPreco) {
        if (novoPreco != this.preco) {
            this.preco = novoPreco;
            notificar();
        }
    }

    public double getPreco()  { return preco; }
    public String getTicker() { return ticker; }
}
