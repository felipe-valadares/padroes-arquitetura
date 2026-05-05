package com.padroes.state.estados;

import com.padroes.state.EstadoPedido;
import com.padroes.state.Pedido;

public class EstadoCancelado implements EstadoPedido {
    @Override
    public void pagar(Pedido pedido) {
        throw new IllegalStateException("Pedido cancelado não pode ser pago.");
    }

    @Override
    public void enviar(Pedido pedido) {
        throw new IllegalStateException("Pedido cancelado não pode ser enviado.");
    }

    @Override
    public void entregar(Pedido pedido) {
        throw new IllegalStateException("Pedido cancelado não pode ser entregue.");
    }

    @Override
    public void cancelar(Pedido pedido) {
        throw new IllegalStateException("Pedido já está cancelado.");
    }

    @Override
    public String getNome() { return "Cancelado"; }
}
