package com.padroes.mediator;

public abstract class Participante {

    protected final Mediador mediador;
    protected final String nome;

    protected Participante(Mediador mediador, String nome) {
        this.mediador = mediador;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public abstract void enviar(String mensagem);

    public abstract void receberMensagem(String mensagem);
}
