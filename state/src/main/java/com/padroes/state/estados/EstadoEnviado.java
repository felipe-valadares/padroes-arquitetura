package com.padroes.state.estados;

import com.padroes.state.EstadoPedido;
import com.padroes.state.Pedido;

public class EstadoEnviado implements EstadoPedido {
    @Override
    public void pagar(Pedido pedido) {
        throw new IllegalStateException("Pedido enviado já foi pago.");
    }

    @Override
    public void enviar(Pedido pedido) {
        throw new IllegalStateException("Pedido já foi enviado.");
    }

    @Override
    public void entregar(Pedido pedido) {
        pedido.setEstado(new EstadoEntregue());
    }

    @Override
    public void cancelar(Pedido pedido) {
        pedido.setEstado(new EstadoCancelado());
    }

    @Override
    public String getNome() { return "Enviado"; }
}
