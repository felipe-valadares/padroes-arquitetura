package com.padroes.adapter;

public class AdaptadorStripe implements ProcessadorPagamento {
    private final StripeCliente cliente;

    public AdaptadorStripe(StripeCliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public Recibo processar(Pagamento pagamento) {
        long centavos = Math.round(pagamento.getValor() * 100);
        StripeCliente.StripeCharge charge = cliente.charge(
            centavos,
            pagamento.getDescricao(),
            pagamento.getDestinatario()
        );
        String status = "succeeded".equals(charge.status()) ? "APROVADO" : "RECUSADO";
        return new Recibo(charge.id(), status, pagamento.getValor());
    }
}
