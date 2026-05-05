package com.padroes.strategy;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoCompras {
    private final List<Item> itens = new ArrayList<>();
    private EstrategiaPagamento estrategia;

    public record Item(String nome, double preco) {}

    public void adicionarItem(String nome, double preco) {
        itens.add(new Item(nome, preco));
    }

    public double calcularTotal() {
        return itens.stream().mapToDouble(Item::preco).sum();
    }

    public void setEstrategia(EstrategiaPagamento estrategia) {
        this.estrategia = estrategia;
    }

    public String finalizarCompra() {
        if (estrategia == null) {
            throw new IllegalStateException("Estratégia de pagamento não definida.");
        }
        return estrategia.processar(calcularTotal());
    }

    public List<Item> getItens() { return List.copyOf(itens); }
}
