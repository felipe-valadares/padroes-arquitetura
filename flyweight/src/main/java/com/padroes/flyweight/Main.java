package com.padroes.flyweight;

public class Main {
    public static void main(String[] args) {
        Floresta floresta = new Floresta();

        for (int i = 0; i < 500; i++)
            floresta.plantarArvore(i * 2, i * 3, 10 + (i % 5), "Carvalho", "Verde", "Rugosa");
        for (int i = 0; i < 300; i++)
            floresta.plantarArvore(i, i * 2, 8 + (i % 3), "Pinheiro", "Verde-escuro", "Lisa");
        for (int i = 0; i < 200; i++)
            floresta.plantarArvore(i * 4, i, 15 + (i % 7), "Bambu", "Amarelo", "Estriada");

        System.out.printf("Árvores plantadas : %d%n", floresta.totalArvores());
        System.out.printf("Tipos de árvore   : %d (economia de %d objetos)%n",
                floresta.totalTipos(), floresta.totalArvores() - floresta.totalTipos());
    }
}
