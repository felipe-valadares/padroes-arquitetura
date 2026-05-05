package com.padroes.state.estados;

import com.padroes.state.EstadoPedido;
import com.padroes.state.Pedido;

public class EstadoEntregue implements EstadoPedido {
    @Override
    public void pagar(Pedido pedido) {
        throw new IllegalStateException("Pedido já foi entregue.");
    }

    @Override
    public void enviar(Pedido pedido) {
        throw new IllegalStateException("Pedido já foi entregue.");
    }

    @Override
    public void entregar(Pedido pedido) {
        throw new IllegalStateException("Pedido já foi entregue.");
    }

    @Override
    public void cancelar(Pedido pedido) {
        throw new IllegalStateException("Pedido entregue não pode ser cancelado.");
    }

    @Override
    public String getNome() { return "Entregue"; }
}
