package com.padroes.builder;

public abstract class ComputadorBuilder {

    protected String cpu;
    protected int memoriaGb;
    protected int armazenamentoGb;
    protected String gpu;
    protected String sistemaOperacional;

    public abstract ComputadorBuilder configurarCpu();

    public abstract ComputadorBuilder configurarMemoria();

    public abstract ComputadorBuilder configurarArmazenamento();

    public abstract ComputadorBuilder configurarGpu();

    public abstract ComputadorBuilder configurarSistemaOperacional();

    public Computador construir() {
        return new Computador(cpu, memoriaGb, armazenamentoGb, gpu, sistemaOperacional);
    }
}
