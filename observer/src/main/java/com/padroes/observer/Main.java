package com.padroes.observer;

import com.padroes.observer.observadores.AlertaEmail;
import com.padroes.observer.observadores.InvestidorPF;
import com.padroes.observer.observadores.InvestidorPJ;

public class Main {
    public static void main(String[] args) {
        var petr4 = new AcaoBolsa("PETR4", 30.0);

        var joao  = new InvestidorPF("João", 35.0);
        var fundo = new InvestidorPJ("Fundo Alpha", 100);
        var email = new AlertaEmail("alertas@corretora.com");

        petr4.adicionar(joao);
        petr4.adicionar(fundo);
        petr4.adicionar(email);

        System.out.println("=== Mudança de preço: R$30 → R$33 ===");
        petr4.setPreco(33.0);

        System.out.println("=== Mudança de preço: R$33 → R$38 ===");
        petr4.setPreco(38.0);
        System.out.println("Alerta João: " + joao.getUltimoAlerta());
        System.out.println("Cálculo Fundo: " + fundo.getUltimoCalculo());

        System.out.println("\n=== Notificações por email ===");
        email.getNotificacoesRecebidas().forEach(System.out::println);

        System.out.println("\n=== Removendo João ===");
        petr4.remover(joao);
        petr4.setPreco(40.0);
        System.out.println("Alerta João após remoção: " + joao.getUltimoAlerta());
        System.out.println("Cálculo Fundo após mudança: " + fundo.getUltimoCalculo());
    }
}
