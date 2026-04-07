package com.padroes.singleton;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ConfiguracaoApp {

    private static volatile ConfiguracaoApp instancia;
    private final Map<String, String> propriedades;

    private ConfiguracaoApp() {
        propriedades = new HashMap<>();
        propriedades.put("versao", "1.0.0");
        propriedades.put("ambiente", "desenvolvimento");
        propriedades.put("timeout", "30");
        propriedades.put("max-conexoes", "10");
    }

    public static ConfiguracaoApp getInstancia() {
        if (instancia == null) {
            synchronized (ConfiguracaoApp.class) {
                if (instancia == null) {
                    instancia = new ConfiguracaoApp();
                }
            }
        }
        return instancia;
    }

    public String getPropriedade(String chave) {
        return propriedades.getOrDefault(chave, "");
    }

    public void setPropriedade(String chave, String valor) {
        propriedades.put(chave, valor);
    }

    public Map<String, String> getTodasPropriedades() {
        return Collections.unmodifiableMap(propriedades);
    }

    public boolean contemPropriedade(String chave) {
        return propriedades.containsKey(chave);
    }
}
