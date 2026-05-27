package com.padroes.prototype;

public class Mago implements Personagem {
    private String nome;
    private int vida;
    private int ataque;
    private int defesa;
    private int mana;

    public Mago(String nome, int vida, int ataque, int defesa, int mana) {
        this.nome = nome;
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.mana = mana;
    }

    @Override
    public Personagem clonar() {
        return new Mago(nome, vida, ataque, defesa, mana);
    }

    @Override public String getNome()  { return nome; }
    @Override public int getVida()     { return vida; }
    @Override public int getAtaque()   { return ataque; }
    @Override public int getDefesa()   { return defesa; }
    public int getMana()               { return mana; }

    public void setNome(String nome) { this.nome = nome; }
    public void setVida(int vida)    { this.vida = vida; }
}
