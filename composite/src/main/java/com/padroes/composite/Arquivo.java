package com.padroes.composite;

public class Arquivo implements ComponenteSistema {
    private final String nome;
    private final long tamanho;

    public Arquivo(String nome, long tamanho) {
        this.nome = nome;
        this.tamanho = tamanho;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public long getTamanho() {
        return tamanho;
    }

    @Override
    public String listar(String prefixo) {
        return prefixo + nome + " (" + tamanho + " bytes)";
    }
}
