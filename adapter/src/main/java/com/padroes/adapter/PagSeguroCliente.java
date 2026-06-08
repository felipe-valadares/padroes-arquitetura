package com.padroes.adapter;

public class PagSeguroCliente {

    public String efetuarCobranca(double quantia, String produto, String recebedor) {
        return "PS-" + System.nanoTime();
    }

    public String consultarStatus(String codigoTransacao) {
        return "APROVADO";
    }
}
