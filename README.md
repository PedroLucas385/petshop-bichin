# Sistema de Gestão para Petshop (Petshop Bichin)

Projeto desenvolvido em Java para a disciplina de Programação Orientada a Objetos do curso de Ciência da Computação (IFMG - Campus Formiga). O sistema gerencia as operações de um petshop via linha de comando (CLI), abrangendo o cadastro de clientes, animais, serviços e o registro de atendimentos realizados.

## Arquitetura do Sistema

O projeto foi estruturado seguindo o padrão arquitetural em quatro camadas desacopladas, com injeção de dependências via construtores:

* `model`: Entidades de domínio (`Cliente`, `Pet`, `Servico`, `ServicoPrestado`) e suas associações de composição e agregação.
* `repository`: Camada de persistência em memória (`ArrayList`) responsável pelo CRUD e pela carga inicial de dados a partir de arquivos de texto estruturados (`.txt`).
* `controller`: Camada intermediária que recebe as requisições da interface, resolve dependências entre objetos e delega as operações aos repositórios.
* `visao.cmd`: Interface de interação com o usuário em linha de comando (`UI_Cmd`, `UiCmdCliente`, `UiCmdPet`, `UiCmdServico`, `UiCmdServicoPrestado`).

## Status do Projeto

Versão Inicial (Parte 1) concluída:

* [x] Modelagem das classes de domínio e seus relacionamentos (composição entre `Pet` e `Cliente`; agregação em `ServicoPrestado`).
* [x] Implementação da camada de persistência em memória (`ArrayList`) e leitura inicial de arquivos `.txt` (`clientes.txt`, `pets.txt`, `servicos.txt`, `servicos_prestados.txt`).
* [x] Implementação da camada de controle (`Controller`) com injeção de dependências.
* [x] Implementação da interface de linha de comando (`visao.cmd`) e integração na classe `Main`.
* [ ] Camada de serviço (`Service`), enumeradores e herança de serviços (Versão 2 - em planejamento).
* [ ] Persistência em banco de dados relacional via JDBC e interfaces genéricas (Versão Final - em planejamento).

## Tecnologias e Conceitos Aplicados

* Java 17+
* Programação Orientada a Objetos (encapsulamento, composição, agregação, sobrescrita de `equals` e `toString`)
* Java Collections Framework (`ArrayList`)
* Manipulação de arquivos e entrada/saída (`BufferedReader`, `FileReader`, `Scanner`)
* API de Datas (`java.time.LocalDate`, `DateTimeFormatter`)
* Arquitetura em camadas e injeção de dependências via construtor

## Funcionalidades Implementadas

* **Clientes:** Cadastro, listagem, busca por ID, alteração e remoção.
* **Pets:** Cadastro vinculado obrigatoriamente a um cliente existente, listagem, busca por ID, alteração e remoção.
* **Serviços:** Cadastro de catálogo de procedimentos, listagem, busca por ID, alteração e remoção.
* **Serviços Prestados:** Registro de atendimentos associando pet e serviço, listagem geral, busca por ID e exclusão.
* **Consultas e Relatórios:** Filtro de atendimentos por intervalo de datas, listagem de animais por nome do tutor, histórico de atendimentos por cliente e cálculo do maior valor gasto por cliente.

## Como Executar o Projeto

### Pré-requisitos

* Java Development Kit (JDK) 17 ou superior instalado.
* Git instalado.

### Passo a passo

1. Clone o repositório:

```bash
git clone [https://github.com/SEU-USUARIO/petshop-bichin.git](https://github.com/SEU-USUARIO/petshop-bichin.git)
cd petshop-bichin

    Certifique-se de que a pasta dados/ exista na raiz do projeto contendo os arquivos clientes.txt, pets.txt, servicos.txt e servicos_prestados.txt formatados com separador ponto e vírgula (;).

    
```
2. Compile os arquivos-fonte:
```bash


javac -d bin src/model/*.java src/repository/*.java src/controller/*.java src/visao/cmd/*.java src/Main.java
```
3. Execute a aplicação:

```bash

java -cp bin Main