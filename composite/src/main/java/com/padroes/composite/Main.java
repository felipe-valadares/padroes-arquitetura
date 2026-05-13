package com.padroes.composite;

public class Main {
    public static void main(String[] args) {
        Arquivo readme = new Arquivo("README.md", 1024);
        Arquivo app = new Arquivo("App.java", 2048);
        Arquivo appTest = new Arquivo("AppTest.java", 512);

        Diretorio src = new Diretorio("src");
        src.adicionar(app);
        src.adicionar(appTest);

        Diretorio projeto = new Diretorio("projeto");
        projeto.adicionar(readme);
        projeto.adicionar(src);

        System.out.println(projeto.listar(""));
        System.out.println("Tamanho total: " + projeto.getTamanho() + " bytes");
    }
}
