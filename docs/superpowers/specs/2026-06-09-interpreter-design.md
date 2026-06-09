# Interpreter — Calculadora de Expressões

**Padrão:** GoF Interpreter  
**Módulo Maven:** `interpreter`  
**Package:** `com.padroes.interpreter`

## Objetivo

Representar expressões aritméticas como árvores de objetos e avaliá-las via polimorfismo. Cada nó da árvore é uma `Expressao` que sabe se interpretar dado um `Contexto`.

## Participantes

### Contexto
`Contexto` — encapsula `Map<String, Integer>` com valores de variáveis nomeadas. Métodos: `definir(nome, valor)`, `obter(nome)`.

### Interface
`Expressao` — `int interpretar(Contexto ctx)`

### Expressões terminais
- `ExpressaoNumero(int valor)` — retorna o literal diretamente
- `ExpressaoVariavel(String nome)` — busca o valor no `Contexto`

### Expressões não-terminais
Todas recebem `Expressao esquerda, Expressao direita` no construtor:
- `ExpressaoSoma` — `esquerda + direita`
- `ExpressaoSubtracao` — `esquerda - direita`
- `ExpressaoMultiplicacao` — `esquerda * direita`
- `ExpressaoDivisao` — `esquerda / direita`

### Demo
`Main.java` — constrói e avalia expressões como `(x + 3) * 2 - y` via árvore de objetos.

## Testes

`InterpreterTest.java` com `@Nested` por categoria:
- `ExpressaoNumeroTest` — literal retorna valor
- `ExpressaoVariavelTest` — lê do contexto
- `OperacoesBasicasTest` — soma, subtração, multiplicação, divisão
- `ExpresoesCompostasTest` — árvores aninhadas, expressão com variáveis

## Estrutura de arquivos

```
interpreter/
  pom.xml
  diagram.puml
  src/main/java/com/padroes/interpreter/
    Expressao.java
    Contexto.java
    ExpressaoNumero.java
    ExpressaoVariavel.java
    ExpressaoSoma.java
    ExpressaoSubtracao.java
    ExpressaoMultiplicacao.java
    ExpressaoDivisao.java
    Main.java
  src/test/java/com/padroes/interpreter/
    InterpreterTest.java
```
