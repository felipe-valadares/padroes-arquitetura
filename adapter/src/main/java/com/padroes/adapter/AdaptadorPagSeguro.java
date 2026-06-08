package com.padroes.adapter;

public class AdaptadorPagSeguro implements ProcessadorPagamento {
    private final PagSeguroCliente cliente;

    public AdaptadorPagSeguro(PagSeguroCliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public Recibo processar(Pagamento pagamento) {
        String codigo = cliente.efetuarCobranca(
            pagamento.getValor(),
            pagamento.getDescricao(),
            pagamento.getDestinatario()
        );
        String status = cliente.consultarStatus(codigo);
        return new Recibo(codigo, status, pagamento.getValor());
    }
}
