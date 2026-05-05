package com.padroes.state.estados;

import com.padroes.state.EstadoPedido;
import com.padroes.state.Pedido;

public class EstadoPago implements EstadoPedido {
    @Override
    public void pagar(Pedido pedido) {
        throw new IllegalStateException("Pedido já foi pago.");
    }

    @Override
    public void enviar(Pedido pedido) {
        pedido.setEstado(new EstadoEnviado());
    }

    @Override
    public void entregar(Pedido pedido) {
        throw new IllegalStateException("Pedido pago precisa ser enviado antes de ser entregue.");
    }

    @Override
    public void cancelar(Pedido pedido) {
        pedido.setEstado(new EstadoCancelado());
    }

    @Override
    public String getNome() { return "Pago"; }
}
