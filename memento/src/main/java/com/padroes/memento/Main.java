package com.padroes.memento;

public class Main {
    public static void main(String[] args) {
        EditorTexto editor = new EditorTexto();
        HistoricoEdicao historico = new HistoricoEdicao(editor);

        historico.salvar();
        editor.escrever("Olá, ");
        historico.salvar();
        editor.escrever("Mundo!");
        System.out.println("Atual: " + editor.getTexto());

        historico.desfazer();
        System.out.println("Após desfazer: " + editor.getTexto());

        historico.refazer();
        System.out.println("Após refazer: " + editor.getTexto());
    }
}
