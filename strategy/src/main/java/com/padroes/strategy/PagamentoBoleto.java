package com.padroes.strategy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PagamentoBoleto implements EstrategiaPagamento {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public String processar(double valor) {
        LocalDate vencimento = LocalDate.now().plusDays(3);
        return String.format("Boleto: R$%.2f — vencimento %s", valor, vencimento.format(FORMATTER));
    }

    @Override
    public String getNome() { return "Boleto Bancário"; }
}
