package com.padroes.builder;

public class Montador {

    private final ComputadorBuilder builder;

    public Montador(ComputadorBuilder builder) {
        this.builder = builder;
    }

    public Computador montarComputadorCompleto() {
        return builder
                .configurarCpu()
                .configurarMemoria()
                .configurarArmazenamento()
                .configurarGpu()
                .configurarSistemaOperacional()
                .construir();
    }
}
