package com.padroes.composite;

import java.util.ArrayList;
import java.util.List;

public class Diretorio implements ComponenteSistema {
    private final String nome;
    private final List<ComponenteSistema> filhos = new ArrayList<>();

    public Diretorio(String nome) {
        this.nome = nome;
    }

    public void adicionar(ComponenteSistema componente) {
        filhos.add(componente);
    }

    public void remover(ComponenteSistema componente) {
        filhos.remove(componente);
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public long getTamanho() {
        return filhos.stream().mapToLong(ComponenteSistema::getTamanho).sum();
    }

    @Override
    public String listar(String prefixo) {
        StringBuilder sb = new StringBuilder();
        sb.append(prefixo).append(nome).append("/");
        for (ComponenteSistema filho : filhos) {
            sb.append("\n").append(filho.listar(prefixo + "  "));
        }
        return sb.toString();
    }
}
