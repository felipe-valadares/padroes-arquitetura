package com.padroes.observer.observadores;

import com.padroes.observer.Observador;

import java.util.ArrayList;
import java.util.List;

public class AlertaEmail implements Observador {
    private final String endereco;
    private final List<String> notificacoesRecebidas = new ArrayList<>();

    public AlertaEmail(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public void atualizar(String acao, double preco) {
        notificacoesRecebidas.add(
            String.format("[%s] %s: R$%.2f", endereco, acao, preco)
        );
    }

    public List<String> getNotificacoesRecebidas() { return List.copyOf(notificacoesRecebidas); }
    public String getEndereco()                    { return endereco; }
}
