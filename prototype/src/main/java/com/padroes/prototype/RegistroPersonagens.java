package com.padroes.prototype;

import java.util.HashMap;
import java.util.Map;

public class RegistroPersonagens {
    private final Map<String, Personagem> templates = new HashMap<>();

    public void registrar(String chave, Personagem template) {
        templates.put(chave, template);
    }

    public Personagem obter(String chave) {
        Personagem template = templates.get(chave);
        if (template == null) throw new IllegalArgumentException("Template não encontrado: " + chave);
        return template.clonar();
    }
}
