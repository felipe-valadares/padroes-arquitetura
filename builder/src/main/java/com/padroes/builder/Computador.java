package com.padroes.builder;

public record Computador(
        String cpu,
        int memoriaGb,
        int armazenamentoGb,
        String gpu,
        String sistemaOperacional
) {}
