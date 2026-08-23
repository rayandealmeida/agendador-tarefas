# Agendador de Tarefas

Serviço desenvolvido com **Java e Spring Boot** para gerenciamento de tarefas dos usuários.

O projeto faz parte de uma aplicação Back-end dividida em diferentes serviços e possui comunicação com o serviço de usuários para buscar e validar informações necessárias durante o gerenciamento das tarefas.

## Funcionalidades

* Cadastro de tarefas
* Consulta de tarefas
* Atualização de tarefas
* Exclusão de tarefas
* Associação das tarefas aos usuários
* Comunicação com o serviço de usuários
* Validação e tratamento de exceções
* Autenticação através de JWT

## Tecnologias utilizadas

* Java
* Spring Boot
* Spring Data MongoDB
* MongoDB
* Spring Security
* JWT
* Gradle
* API REST

## Estrutura

O projeto segue uma organização em camadas, separando as responsabilidades da aplicação entre:

* Controller
* Service
* Repository
* DTO
* Mapper
* Entity
* Security

Essa separação ajuda a manter o código organizado e facilita a manutenção da aplicação.

## Comunicação entre serviços

O Agendador de Tarefas trabalha em conjunto com o serviço de usuários.

A comunicação entre as aplicações permite utilizar informações do usuário dentro do gerenciamento das tarefas, mantendo cada serviço responsável pela sua própria parte do sistema.

## Banco de dados

O serviço utiliza **MongoDB** para armazenamento das tarefas, com integração através do **Spring Data MongoDB**.

## Sobre o projeto

O Agendador de Tarefas faz parte de um projeto Back-end maior desenvolvido com **Java e Spring Boot**, composto pelos seguintes módulos:

* Serviço de Usuários
* Agendador de Tarefas
* Serviço de Notificação
* BFF

Cada serviço possui sua própria responsabilidade e trabalha em conjunto com os demais para formar a aplicação completa.

## Projeto completo

A documentação geral e a arquitetura do sistema estão disponíveis no repositório:

[Sistema Agendador de Tarefas](https://github.com/rayandealmeida/sistema-agendador-tarefas)
