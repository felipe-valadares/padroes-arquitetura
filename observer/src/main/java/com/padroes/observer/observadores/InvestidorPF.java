package com.padroes.observer.observadores;

import com.padroes.observer.Observador;

public class InvestidorPF implements Observador {
    private final String nome;
    private final double limiteAlerta;
    private String ultimoAlerta;

    public InvestidorPF(String nome, double limiteAlerta) {
        this.nome = nome;
        this.limiteAlerta = limiteAlerta;
    }

    @Override
    public void atualizar(String acao, double preco) {
        if (preco > limiteAlerta) {
            ultimoAlerta = String.format("%s: %s acima do limite! Preço: R$%.2f", nome, acao, preco);
        }
    }

    public String getUltimoAlerta() { return ultimoAlerta; }
    public String getNome()         { return nome; }
}
