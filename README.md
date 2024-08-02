# Recall de Compras

Uma aplicação para controle de compras / gastos, trazendo a memória informações da última compra (quantidade, valor, data) para fazer uma ponderação no ato da nova compra.


## Índice

  

- [Sobre](#sobre)

- [Tecnologias Utilizadas](#tecnologias-utilizadas)

- [Configuração do Ambiente](#configuração-do-ambiente)

- [Instalação](#instalação)

- [Uso](#uso)

- [Testes](#testes)

- [Contribuição](#contribuição)

- [Licença](#licença)

  

## Sobre

  

O `recall-de-compras` é uma aplicação que permite o controle eficiente das suas compras, armazenando dados da última compra para ajudar na tomada de decisões na nova compra. O objetivo é otimizar os gastos e gerenciar melhor as compras realizadas ao longo do tempo.

  

## Tecnologias Utilizadas

  

- Java 11

- Spring Boot 2.7.4

- PostgreSQL

- Flyway para migrações de banco de dados

- Spring Data JPA

- Spring Security

- Spring Cloud OpenFeign

- Gson

- Dotenv para gerenciamento de variáveis de ambiente

- Maven para gerenciamento de dependências

  

## Configuração do Ambiente

  

1. Instale o [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).

2. Instale o [Maven](https://maven.apache.org/install.html).

3. Instale o [PostgreSQL](https://www.postgresql.org/download/).

4. Configure as variáveis de ambiente necessárias no arquivo `.env`.

  

## Instalação

  

1. Clone o repositório:

  

```sh

git clone https://github.com/gustavo91010/shopping-recorder

cd shopping-recorder

```

  

2. Instale as dependências:

  

```sh

mvn clean install

```

  

3. Configure o banco de dados PostgreSQL e execute as migrações do Flyway:

  

```sh

mvn flyway:migrate

```

  

## Uso

  

Para rodar a aplicação:

  

```sh

mvn spring-boot:run
```
  
  

## Contribuições são bem-vindas!

Sinta-se à vontade para abrir issues e pull requests.

  

Fork o projeto.

Crie uma nova branch (git checkout -b feature/nome-da-sua-feature).

Commit suas mudanças (git commit -m 'Adiciona nova feature').

Push para a branch (git push origin feature/nome-da-sua-feature).

Abra um Pull Request.

  

Sinta-se à vontade para ajustar conforme necessário e adicionar quaisquer informações adicionais relevantes para o seu projeto.