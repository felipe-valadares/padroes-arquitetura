package com.padroes.adapter;

public interface ProcessadorPagamento {
    Recibo processar(Pagamento pagamento);
}
