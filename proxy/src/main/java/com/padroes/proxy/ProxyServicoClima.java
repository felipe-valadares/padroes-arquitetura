package com.padroes.proxy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProxyServicoClima implements ServicoClima {
    private final ServicoClima real;
    private final Map<String, Double> cacheTemperatura = new HashMap<>();
    private final Map<String, Previsao> cachePrevisao = new HashMap<>();
    private final List<String> log = new ArrayList<>();

    public ProxyServicoClima(ServicoClima real) {
        this.real = real;
    }

    @Override
    public double obterTemperatura(String cidade) {
        if (cacheTemperatura.containsKey(cidade)) {
            log.add("CACHE HIT: temperatura de " + cidade);
            return cacheTemperatura.get(cidade);
        }
        log.add("CHAMADA REAL: temperatura de " + cidade);
        double temp = real.obterTemperatura(cidade);
        cacheTemperatura.put(cidade, temp);
        return temp;
    }

    @Override
    public Previsao obterPrevisao(String cidade) {
        if (cachePrevisao.containsKey(cidade)) {
            log.add("CACHE HIT: previsao de " + cidade);
            return cachePrevisao.get(cidade);
        }
        log.add("CHAMADA REAL: previsao de " + cidade);
        Previsao previsao = real.obterPrevisao(cidade);
        cachePrevisao.put(cidade, previsao);
        return previsao;
    }

    public List<String> getLog() { return Collections.unmodifiableList(log); }

    public void limparCache() {
        cacheTemperatura.clear();
        cachePrevisao.clear();
    }
}
