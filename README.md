# JavaCoin - Exchange Cripto

Este projeto é uma aplicação para gestão de uma Exchange de Criptomoedas, permitindo operações como compra e venda de ativos digitais (Bitcoin, Ethereum e Ripple) e controle de carteiras de investimento. A aplicação foi desenvolvida com foco na simplicidade de uso e persistência de dados, utilizando NetBeans para o desenvolvimento, Docker para o banco de dados, e DBeaver para a administração e visualização do banco.

## Funcionalidades

### Área do Usuário

Os investidores têm acesso às seguintes funcionalidades através da tela de login:

- Consultar saldo e extrato de operações.
- Depositar e sacar reais.
- Comprar e vender criptomoedas com taxas aplicadas por moeda.
- Atualizar cotações de criptomoedas.

### Área do Administrador

Os administradores, acessando com credenciais específicas, podem:

- Cadastrar e excluir investidores.
- Cadastrar e excluir criptomoedas.
- Consultar saldos e extratos de qualquer investidor.
- Atualizar cotações das criptomoedas.

## Tecnologias Utilizadas

- NetBeans: IDE para o desenvolvimento do projeto.
- Docker: Utilizado para subir um container com o banco de dados - PostgreSQL.
- DBeaver: Ferramenta para gerenciar e visualizar o banco de dados.
- PostgreSQL: Banco de dados utilizado para persistir as informações do sistema.

## Iniciar Banco de dados com Docker

Para iniciar o banco de dados pelo docker é necessário instanciar o banco de dados, pode utilizar o docker para isso:
```bash
docker build -t postgres . 
``` 
```bash
docker volume create java_data
``` 
```bash
docker run --name java_db -d -p 5432:5432 -v java_data:/var/lib/postgresql/data postgres    
``` 

## Iniciar Banco de dados com pg Admin 4

Definir variáveis do banco:
```bash
    "jdbc:postgresql://localhost:5432/postgres", // url de conexão
    "postgres",                                  // user
    "postgres"                                   // password
```

## Configurar Banco de dados

Ao iniciar o banco seja no PgAdmin4 ou pelo docker é necessário rodar os seguintes scripts, na respectiva ordem:

- create_table.sql
- insert_basic_data.sql

Com esses scripts as tabelas são criadas e as moedas principais e um usuário de administrador são adicionados ao banco.

Este projeto foi desenvolvido com base nos conceitos de Programação Orientada a Objetos e boas práticas de desenvolvimento de software.

## Membros

- Arthur Leal Mussio  - RA: 22.223.017-9
- Felipe Brum Pereira - RA: 22.123.112-9
