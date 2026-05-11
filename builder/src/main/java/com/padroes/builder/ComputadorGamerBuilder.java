package com.padroes.builder;

public class ComputadorGamerBuilder extends ComputadorBuilder {

    @Override
    public ComputadorBuilder configurarCpu() {
        cpu = "Intel Core i9-14900K";
        return this;
    }

    @Override
    public ComputadorBuilder configurarMemoria() {
        memoriaGb = 32;
        return this;
    }

    @Override
    public ComputadorBuilder configurarArmazenamento() {
        armazenamentoGb = 2000;
        return this;
    }

    @Override
    public ComputadorBuilder configurarGpu() {
        gpu = "NVIDIA RTX 4090";
        return this;
    }

    @Override
    public ComputadorBuilder configurarSistemaOperacional() {
        sistemaOperacional = "Windows 11";
        return this;
    }
}
