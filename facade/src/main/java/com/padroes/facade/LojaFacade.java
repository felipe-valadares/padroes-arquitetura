package com.padroes.facade;

public class LojaFacade {
    private final GerenciadorEstoque estoque;
    private final ProcessadorPagamento pagamento;
    private final ServicoNotificacao notificacao;

    public LojaFacade(GerenciadorEstoque estoque, ProcessadorPagamento pagamento, ServicoNotificacao notificacao) {
        this.estoque = estoque;
        this.pagamento = pagamento;
        this.notificacao = notificacao;
    }

    public ResultadoCompra realizarCompra(String produto, int quantidade, String cartao, String email) {
        if (!estoque.verificarDisponibilidade(produto, quantidade)) {
            return new ResultadoCompra(false, "Estoque insuficiente para: " + produto);
        }
        if (!pagamento.processar(cartao, quantidade)) {
            return new ResultadoCompra(false, "Pagamento recusado para o cartão: " + cartao);
        }
        estoque.reservar(produto, quantidade);
        notificacao.enviarConfirmacao(email, produto);
        return new ResultadoCompra(true, "Compra realizada com sucesso: " + produto);
    }
}
