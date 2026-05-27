---
name: flyweight-iterator-prototype
description: Design para implementação dos padrões Flyweight, Iterator e Prototype seguindo convenções do projeto
metadata:
  type: project
---

# Design: Flyweight, Iterator e Prototype

## Convenções do Projeto

- Módulo Maven por padrão em `/padroes-arquitetura/<padrão>/`
- Pacote `com.padroes.<padrão>`
- `Main.java` com demo executável
- Testes JUnit 5 com `@Nested` / `@DisplayName`
- Diagrama `diagram.puml` (PlantUML)
- Nomes de classes e variáveis em português
- Raiz `pom.xml` deve registrar cada módulo novo

---

## 1. Flyweight — Floresta Simulada

### Problema
Renderizar milhares de árvores num simulador sem criar um objeto por árvore, já que espécie/cor/textura se repetem.

### Classes

| Classe | Papel |
|---|---|
| `TipoArvore` | Flyweight concreto — campos `especie`, `cor`, `textura`; método `renderizar(x, y, altura)` |
| `FabricaArvores` | Factory + cache — `Map<String, TipoArvore>`; `obter(especie, cor, textura)` |
| `Arvore` | Contexto — referência a `TipoArvore` (intrínseco) + `x`, `y`, `altura` (extrínseco); `renderizar()` |
| `Floresta` | Cliente — `plantarArvore(x, y, altura, especie, cor, textura)`, `renderizar()` |
| `Main` | Demo: planta 1000 árvores de 3 tipos → imprime contagem de TipoArvore criados |

### Fluxo
`Floresta.plantarArvore(...)` → `FabricaArvores.obter(especie, cor, textura)` → hit/miss no cache → nova `Arvore` com flyweight referenciado.

### Testes
- 100 árvores da mesma espécie/cor/textura → 1 único `TipoArvore`
- 3 combinações distintas → 3 objetos `TipoArvore`
- `Floresta.renderizar()` executa sem exceção
- Estado extrínseco (x, y, altura) não é armazenado no flyweight

---

## 2. Iterator — Playlist de Músicas

### Problema
Percorrer uma coleção de músicas sem expor a estrutura interna, suportando múltiplas estratégias de iteração.

### Classes

| Classe | Papel |
|---|---|
| `Iterador<T>` | Interface — `temProximo(): boolean`, `proximo(): T`, `reiniciar(): void` |
| `Agregado<T>` | Interface — `criarIterador(): Iterador<T>` |
| `Musica` | Elemento — `titulo`, `artista`, `duracaoSegundos` |
| `Playlist` | Implementa `Agregado<Musica>` — lista interna; `adicionar(Musica)`, `tamanho()`, `criarIterador()`, `criarIteradorEmbaralhado()` |
| `IteradorSequencial` | Iteração em ordem de inserção |
| `IteradorEmbaralhado` | Iteração em ordem aleatória; aceita `Random` no construtor (testável) |
| `Main` | Demo: playlist com 5 músicas, itera sequencial e embaralhado, imprime títulos |

### Testes
- `IteradorSequencial` percorre todas as músicas na ordem de inserção
- `temProximo()` retorna `false` após última música
- `reiniciar()` permite reiterar do início
- `IteradorEmbaralhado` com seed fixo produz ordem determinística
- Playlist vazia: `temProximo()` retorna `false` imediatamente

---

## 3. Prototype — Personagens de RPG

### Problema
Criar variantes de personagens a partir de templates sem depender de construtores concretos, permitindo modificar o clone sem afetar o original.

### Classes

| Classe | Papel |
|---|---|
| `Personagem` | Interface Prototype — `clonar(): Personagem`, getters de `nome`, `vida`, `ataque`, `defesa` |
| `Guerreiro` | Concreto — vida 150, ataque 80, defesa 100; implementa `clonar()` |
| `Mago` | Concreto — vida 80, ataque 150, defesa 40; implementa `clonar()` |
| `Arqueiro` | Concreto — vida 100, ataque 100, defesa 70; campo extra `alcance`; implementa `clonar()` |
| `RegistroPersonagens` | Registry — `Map<String, Personagem>` com templates; `registrar(chave, template)`, `obter(chave): Personagem` retorna clone |
| `Main` | Demo: registra templates, clona vários guerreiros/magos, modifica um clone e mostra que original não muda |

### Testes
- Clone é objeto diferente (`assertNotSame`)
- Clone tem mesmos valores que o original (`assertEquals`)
- Modificar o clone não altera o original
- `RegistroPersonagens.obter()` retorna clone diferente a cada chamada
- `Arqueiro` clona campo `alcance` corretamente
