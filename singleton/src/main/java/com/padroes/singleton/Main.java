package com.padroes.singleton;

public class Main {

    public static void main(String[] args) {
        ConfiguracaoApp config1 = ConfiguracaoApp.getInstancia();
        ConfiguracaoApp config2 = ConfiguracaoApp.getInstancia();

        System.out.println("config1 == config2? " + (config1 == config2));

        config1.getTodasPropriedades().forEach((k, v) ->
            System.out.println(k + " = " + v)
        );

        config1.setPropriedade("ambiente", "producao");
        System.out.println("Ambiente via config2: " + config2.getPropriedade("ambiente"));

        Runnable tarefa = () -> {
            ConfiguracaoApp cfg = ConfiguracaoApp.getInstancia();
            System.out.println(Thread.currentThread().getName() + " -> " + System.identityHashCode(cfg));
        };

        Thread t1 = new Thread(tarefa, "Thread-1");
        Thread t2 = new Thread(tarefa, "Thread-2");
        Thread t3 = new Thread(tarefa, "Thread-3");
        t1.start(); t2.start(); t3.start();

        try { t1.join(); t2.join(); t3.join(); } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
