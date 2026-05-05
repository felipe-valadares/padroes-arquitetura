package com.padroes.strategy;

public class PagamentoCredito implements EstrategiaPagamento {
    private static final double TAXA = 0.03;
    private final int parcelas;

    public PagamentoCredito(int parcelas) {
        this.parcelas = parcelas;
    }

    @Override
    public String processar(double valor) {
        double total = valor * (1 + TAXA);
        double parcela = total / parcelas;
        return String.format("Crédito: %dx de R$%.2f (total: R$%.2f com taxa de 3%%)",
            parcelas, parcela, total);
    }

    @Override
    public String getNome() { return "Cartão de Crédito"; }
}
