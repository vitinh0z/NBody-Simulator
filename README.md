# NBody Simulator 🌌

Um simulador de N-corpos que calcula e visualiza as interações gravitacionais entre múltiplos objetos celestes em tempo real.

## 📋 Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Fundamentos Físicos](#fundamentos-físicos)
- [Como Funciona](#como-funciona)
- [Instalação](#instalação)
- [Uso](#uso)
- [Exemplos](#exemplos)
- [Tecnologias](#tecnologias)

## 🌟 Sobre o Projeto

O NBody Simulator é uma simulação física que demonstra como múltiplos corpos celestes interagem entre si através da força gravitacional. Cada corpo exerce uma força sobre todos os outros corpos, criando um sistema dinâmico complexo.

Este projeto é ideal para:
- Estudantes de física e astronomia
- Entusiastas de simulações
- Aprendizado de física computacional
- Visualização de sistemas dinâmicos

## 🔬 Fundamentos Físicos

### Lei da Gravitação Universal de Newton

A força gravitacional entre dois corpos é dada por:

```
F = G × (m₁ × m₂) / r²
```

Onde:
- `F` = Força gravitacional (N)
- `G` = Constante gravitacional (6. 674 × 10⁻¹¹ N⋅m²/kg²)
- `m₁, m₂` = Massas dos corpos (kg)
- `r` = Distância entre os corpos (m)

### Vetores de Força

Para cada par de corpos, calculamos um vetor de força:

```
       Corpo 2 (m₂)
            ●
           /|
          / |
     F⃗  /  | F_y
        /   |
       /    |
      /_____|
     ●      
Corpo 1    F_x
 (m₁)
```

O vetor força F⃗ possui componentes:
- `F_x = F × cos(θ) = F × (Δx / r)`
- `F_y = F × sin(θ) = F × (Δy / r)`

### Aceleração e Velocidade

A partir da Segunda Lei de Newton:

```
a⃗ = F⃗ / m
```

**Diagrama de vetores em um sistema de 3 corpos:**

```
                    ●₃ (Corpo 3)
                   /|\
                  / | \
             F⃗₃₁/  |  \ F⃗₃₂
                /   |   \
               /    |    \
              ↙     ↓     ↘
         
    ●₁ ←────────────────────→ ●₂
   Corpo 1      F⃗₁₂          Corpo 2
        ↖                    ↗
         \                  /
      F⃗₁₃\                / F⃗₂₃
           \              /
            \            /
             \          /
              \        /
               \      /
                \    /
                 \  /
                  ↓
```

Cada corpo sente a força resultante de todos os outros corpos:

```
Corpo 1: F⃗_total₁ = F⃗₁₂ + F⃗₁₃
Corpo 2: F⃗_total₂ = F⃗₂₁ + F⃗₂₃
Corpo 3: F⃗_total₃ = F⃗₃₁ + F⃗₃₂
```

## ⚙️ Como Funciona

### Algoritmo de Simulação

O simulador utiliza o **Método de Euler** para integração numérica:

```
1. Para cada corpo i:
   a.  Calcular força total: F⃗ᵢ = Σ F⃗ᵢⱼ (j ≠ i)
   
2. Para cada corpo i:
   b. Calcular aceleração: a⃗ᵢ = F⃗ᵢ / mᵢ
   c. Atualizar velocidade: v⃗ᵢ(t+Δt) = v⃗ᵢ(t) + a⃗ᵢ × Δt
   d. Atualizar posição: r⃗ᵢ(t+Δt) = r⃗ᵢ(t) + v⃗ᵢ × Δt
```

### Visualização dos Vetores

**Estado inicial de um corpo:**

```
        ↑ v⃗ (velocidade)
        |
        |
    ●───┘
   (r⃗_x, r⃗_y)
   
   Posição: r⃗ = (x, y)
   Velocidade: v⃗ = (vₓ, vᵧ)
```

**Após calcular forças:**

```
        F⃗₁ ↗
           \
        F⃗₂ →  ●  ← F⃗₃
           /
        F⃗₄ ↘
        
   F⃗_total = F⃗₁ + F⃗₂ + F⃗₃ + F⃗₄
```

**Atualização do movimento:**

```
Timestep t:                Timestep t+Δt:
                          
    v⃗(t)                      v⃗(t+Δt) = v⃗(t) + a⃗×Δt
     ↑                           ↗
     |                          /
     ●─────→ a⃗                ●
   r⃗(t)                     r⃗(t+Δt) = r⃗(t) + v⃗×Δt
```

## 🚀 Instalação

### Pré-requisitos

- Java JDK 8 ou superior
- Git (opcional)

### Passos

1. Clone o repositório:
```bash
git clone https://github.com/vitinh0z/NBody-Simulator.git
cd NBody-Simulator
```

2. Compile o projeto:
```bash
javac -d bin src/**/*.java
```

3. Execute o simulador:
```bash
java -cp bin Main
```

## 💻 Uso

### Configuração Básica

Edite os parâmetros no código-fonte para personalizar a simulação:

```java
// Número de corpos
int numBodies = 5;

// Timestep (menor = mais preciso, mas mais lento)
double dt = 0.01;

// Constante gravitacional
double G = 6.674e-11;
```

### Criando Corpos Celestes

```java
Body sun = new Body(
    0, 0,           // posição (x, y)
    0, 0,           // velocidade (vx, vy)
    1.989e30        // massa (kg)
);
```

## 🎯 Exemplos

### Sistema Sol-Terra

```
        v⃗_terra
          ↓
    ☉ ←────── 🌍
   Sol   F⃗   Terra
   
Órbita circular aproximada
```

### Sistema de 3 Corpos (Problema Clássico)

```
     ●
    / \
   /   \
  /     \
 ●───────●

Configuração triangular
Movimento caótico resultante
```

### Colisão de Galáxias

```
Antes:              Durante:           Depois:
                   
●●●●    ●●●●      ●● ●●●  ●        ●●●●●●●●●
●●●●    ●●●●   →  ● ●●●● ●●   →      ●●●●●
●●●●    ●●●●      ●●●●● ●●●          ●●●
                   
Galáxia A  B      Interação        Fusão
```

## 🛠️ Tecnologias

- **Java**: Linguagem principal
- **Java Swing/AWT**: Interface gráfica e visualização
- **Programação Orientada a Objetos**: Estrutura do código

## 📊 Estrutura do Projeto

```
NBody-Simulator/
│
├── src/
│   ├── Body.java          # Classe que representa um corpo celeste
│   ├── Simulator.java     # Engine de simulação
│   ├── Visualizer.java    # Renderização gráfica
│   └── Main.java          # Ponto de entrada
│
├── README.md
└── .gitignore
```

## 🎓 Conceitos Aprendidos

- **Física Newtoniana**: Gravitação e dinâmica
- **Álgebra Vetorial**: Operações com vetores 2D/3D
- **Integração Numérica**: Métodos de Euler e Runge-Kutta
- **Otimização**: Algoritmos O(n²) e estruturas espaciais
- **Visualização**: Renderização em tempo real

## 🔧 Melhorias Futuras

- [ ] Implementar método de integração Runge-Kutta (mais preciso)
- [ ] Adicionar detecção de colisões
- [ ] Sistema de trails (rastros) para visualizar órbitas
- [ ] Suporte para 3D
- [ ] Barnes-Hut algorithm para otimização O(n log n)
- [ ] Salvar/carregar configurações
- [ ] Zoom e pan interativos

## 📐 Fórmulas Importantes

### Distância entre corpos
```
r = √[(x₂ - x₁)² + (y₂ - y₁)²]
```

### Vetor unitário (direção da força)
```
r⃗̂ = (x₂ - x₁, y₂ - y₁) / r
```

### Força vetorial
```
F⃗ = (G × m₁ × m₂ / r²) × r⃗̂
```

### Energia Total (Conservação)
```
E_total = E_cinética + E_potencial
E_cinética = Σ(½ × m × v²)
E_potencial = -Σ(G × mᵢ × mⱼ / rᵢⱼ)
```

## 📝 Licença

Este projeto está sob a licença MIT.  Veja o arquivo LICENSE para mais detalhes.

## 👤 Autor

**vitinh0z**
- GitHub: [@vitinh0z](https://github.com/vitinh0z)

## 🤝 Contribuindo

Contribuições são bem-vindas! Sinta-se à vontade para:

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/NovaFeature`)
3. Commit suas mudanças (`git commit -m 'Adiciona nova feature'`)
4. Push para a branch (`git push origin feature/NovaFeature`)
5. Abra um Pull Request

## 📚 Referências

- Newton, I. (1687). *Philosophiæ Naturalis Principia Mathematica*
- Feynman, R.  *The Feynman Lectures on Physics*
- [Three-body problem - Wikipedia](https://en.wikipedia. org/wiki/Three-body_problem)

---

⭐ Se este projeto te ajudou, considere dar uma estrela! 
