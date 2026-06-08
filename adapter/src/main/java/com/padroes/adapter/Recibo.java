package com.padroes.adapter;

public class Recibo {
    private final String id;
    private final String status;
    private final double valor;

    public Recibo(String id, String status, double valor) {
        this.id = id;
        this.status = status;
        this.valor = valor;
    }

    public String getId() { return id; }
    public String getStatus() { return status; }
    public double getValor() { return valor; }

    @Override
    public String toString() {
        return "Recibo{id='" + id + "', status='" + status + "', valor=" + valor + "}";
    }
}
