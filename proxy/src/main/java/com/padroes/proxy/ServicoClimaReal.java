package com.padroes.proxy;

public class ServicoClimaReal implements ServicoClima {
    private int chamadas = 0;

    @Override
    public double obterTemperatura(String cidade) {
        chamadas++;
        return switch (cidade) {
            case "São Paulo" -> 22.5;
            case "Rio de Janeiro" -> 28.0;
            case "Curitiba" -> 15.0;
            default -> 20.0;
        };
    }

    @Override
    public Previsao obterPrevisao(String cidade) {
        chamadas++;
        double temp = switch (cidade) {
            case "São Paulo" -> 22.5;
            case "Rio de Janeiro" -> 28.0;
            case "Curitiba" -> 15.0;
            default -> 20.0;
        };
        String condicao = temp > 25 ? "Ensolarado" : temp > 18 ? "Nublado" : "Frio";
        return new Previsao(cidade, temp, condicao);
    }

    public int getChamadas() { return chamadas; }
}
