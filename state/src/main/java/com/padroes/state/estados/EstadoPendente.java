package com.padroes.state.estados;

import com.padroes.state.EstadoPedido;
import com.padroes.state.Pedido;

public class EstadoPendente implements EstadoPedido {
    @Override
    public void pagar(Pedido pedido) {
        pedido.setEstado(new EstadoPago());
    }

    @Override
    public void enviar(Pedido pedido) {
        throw new IllegalStateException("Pedido pendente não pode ser enviado sem pagamento.");
    }

    @Override
    public void entregar(Pedido pedido) {
        throw new IllegalStateException("Pedido pendente não pode ser entregue.");
    }

    @Override
    public void cancelar(Pedido pedido) {
        pedido.setEstado(new EstadoCancelado());
    }

    @Override
    public String getNome() { return "Pendente"; }
}
