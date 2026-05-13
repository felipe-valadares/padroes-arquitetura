---
title: Composite e Facade — Design Spec
date: 2026-05-12
status: approved
---

# Composite e Facade

## Contexto

Adicionar dois padrões estruturais ao repositório `padroes-arquitetura`, seguindo as convenções existentes: Maven multi-module, Java 17, JUnit 5, diagrama PlantUML, `Main.java` de demonstração e nomes em português.

---

## Padrão Composite — Sistema de Arquivos

### Objetivo

Demonstrar como tratar objetos individuais (arquivos) e compostos (diretórios) de forma uniforme através de uma interface comum.

### Estrutura

**Pacote:** `com.padroes.composite`

| Elemento | Tipo | Papel no padrão |
|----------|------|----------------|
| `ComponenteSistema` | interface | Component |
| `Arquivo` | class | Leaf |
| `Diretorio` | class | Composite |
| `Main` | class | Demo |

**Interface `ComponenteSistema`:**
- `String getNome()`
- `long getTamanho()`
- `String listar(String prefixo)`

**Decisão de design:** `adicionar` e `remover` ficam apenas em `Diretorio`, não na interface. Isso evita métodos inválidos em `Arquivo` e mantém a interface focada nas operações que fazem sentido para todos os componentes.

**`Arquivo` (Leaf):**
- Campos: `nome: String`, `tamanho: long`
- `getTamanho()` retorna o tamanho fixo
- `listar()` retorna uma linha com nome e tamanho

**`Diretorio` (Composite):**
- Campos: `nome: String`, `filhos: List<ComponenteSistema>`
- `getTamanho()` = soma recursiva dos filhos
- `listar()` = lista recursiva com indentação por prefixo
- `adicionar(ComponenteSistema)` e `remover(ComponenteSistema)`

### Testes

Arquivo único: `CompositeTest.java` com classes `@Nested` por contexto:
- `ArquivoTest`: tamanho correto, listar contém nome
- `DiretorioTest`: tamanho = soma filhos, listar recursivo, adicionar/remover
- `CompositeTest` (comportamento do padrão): tratamento uniforme, profundidade arbitrária

### Diagrama

`diagram.puml` com PlantUML, exibindo interface, Leaf e Composite com associação de composição.

---

## Padrão Facade — Checkout de Loja

### Objetivo

Demonstrar como uma Facade simplifica a interface para um conjunto de subsistemas complexos, expondo uma única operação de alto nível.

### Estrutura

**Pacote:** `com.padroes.facade`

| Elemento | Tipo | Papel no padrão |
|----------|------|----------------|
| `GerenciadorEstoque` | class | Subsistema |
| `ProcessadorPagamento` | class | Subsistema |
| `ServicoNotificacao` | class | Subsistema |
| `ResultadoCompra` | class | Value Object |
| `LojaFacade` | class | Facade |
| `Main` | class | Demo |

**Subsistemas:**
- `GerenciadorEstoque`: `verificarDisponibilidade(produto, qtd): boolean`, `reservar(produto, qtd): void`
- `ProcessadorPagamento`: `processar(cartao, valor): boolean`
- `ServicoNotificacao`: `enviarConfirmacao(email, produto): void`

**`ResultadoCompra` (VO):**
- `sucesso: boolean`, `mensagem: String`

**`LojaFacade`:**
- Recebe os três subsistemas no construtor (permite teste sem dependências reais)
- Método único: `realizarCompra(produto, qtd, cartao, email): ResultadoCompra`
- Orquestra em sequência: verifica estoque → processa pagamento → reserva → notifica

### Testes

Arquivo único: `FacadeTest.java` com classes `@Nested` por contexto:
- `CompraComSucessoTest`: fluxo feliz, mensagem contém produto
- `EstoqueInsuficienteTest`: retorna falha sem chamar pagamento
- `PagamentoRecusadoTest`: retorna falha sem reservar estoque
- `FacadeTest` (comportamento): subsistemas substituíveis, interface simplificada

**Estratégia de teste:** Os subsistemas são classes concretas simples (sem I/O real), instanciadas diretamente nos testes — sem mocks. Segue o padrão do repositório de não usar frameworks de mock.

### Diagrama

`diagram.puml` com PlantUML, exibindo Facade, três subsistemas e o cliente.

---

## Convenções a seguir

- `pom.xml` filho referenciando o parent `com.padroes:padroes-arquitetura:1.0.0`
- Adicionar módulo ao `pom.xml` raiz
- Testes com `@DisplayName` em português, `@Nested` por contexto
- Sem comentários no código (lógica autoexplicativa)
- `diagram.puml` + bloco PlantUML no `README.md`
