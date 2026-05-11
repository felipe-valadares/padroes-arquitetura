package com.padroes.builder;

public class ComputadorEscritorioBuilder extends ComputadorBuilder {

    @Override
    public ComputadorBuilder configurarCpu() {
        cpu = "Intel Core i5-13400";
        return this;
    }

    @Override
    public ComputadorBuilder configurarMemoria() {
        memoriaGb = 16;
        return this;
    }

    @Override
    public ComputadorBuilder configurarArmazenamento() {
        armazenamentoGb = 512;
        return this;
    }

    @Override
    public ComputadorBuilder configurarGpu() {
        gpu = "Intel UHD Graphics 730";
        return this;
    }

    @Override
    public ComputadorBuilder configurarSistemaOperacional() {
        sistemaOperacional = "Windows 11 Pro";
        return this;
    }
}
