package com.padroes.adapter;

public class Pagamento {
    private final double valor;
    private final String descricao;
    private final String destinatario;

    public Pagamento(double valor, String descricao, String destinatario) {
        this.valor = valor;
        this.descricao = descricao;
        this.destinatario = destinatario;
    }

    public double getValor() { return valor; }
    public String getDescricao() { return descricao; }
    public String getDestinatario() { return destinatario; }
}
