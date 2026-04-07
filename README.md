# Padrões de Projeto — Java

Implementações dos padrões GoF para estudo.

| Padrão | Módulo |
|---|---|
| Singleton | [singleton/](singleton/) |
| Abstract Factory | [abstract-factory/](abstract-factory/) |
| Factory Method | [factory-method/](factory-method/) |
| Bridge | [bridge/](bridge/) |

## Rodar

```bash
# todos os testes
mvn test

# módulo específico
mvn test -pl singleton

# demonstração
mvn compile exec:java -pl bridge -Dexec.mainClass="com.padroes.bridge.Main"
```
