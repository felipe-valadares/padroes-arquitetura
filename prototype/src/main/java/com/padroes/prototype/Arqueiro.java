package com.padroes.prototype;

public class Arqueiro implements Personagem {
    private String nome;
    private int vida;
    private int ataque;
    private int defesa;
    private int alcance;

    public Arqueiro(String nome, int vida, int ataque, int defesa, int alcance) {
        this.nome = nome;
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.alcance = alcance;
    }

    @Override
    public Personagem clonar() {
        return new Arqueiro(nome, vida, ataque, defesa, alcance);
    }

    @Override public String getNome()  { return nome; }
    @Override public int getVida()     { return vida; }
    @Override public int getAtaque()   { return ataque; }
    @Override public int getDefesa()   { return defesa; }
    public int getAlcance()            { return alcance; }

    public void setNome(String nome) { this.nome = nome; }
    public void setVida(int vida)    { this.vida = vida; }
}
