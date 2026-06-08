package com.padroes.proxy;

public class Main {
    public static void main(String[] args) {
        ServicoClima proxy = new ProxyServicoClima(new ServicoClimaReal());

        System.out.println("=== Primeira consulta (chamada real) ===");
        System.out.println("São Paulo: " + proxy.obterTemperatura("São Paulo") + "°C");
        System.out.println("Curitiba: " + proxy.obterPrevisao("Curitiba"));

        System.out.println("\n=== Segunda consulta (do cache) ===");
        System.out.println("São Paulo: " + proxy.obterTemperatura("São Paulo") + "°C");
        System.out.println("Curitiba: " + proxy.obterPrevisao("Curitiba"));

        System.out.println("\n=== Log de acessos ===");
        ((ProxyServicoClima) proxy).getLog().forEach(System.out::println);
    }
}
