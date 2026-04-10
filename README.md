# 🏢 ReservaCode - Sistema de Agendamento de Salas

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Status](https://img.shields.io/badge/Status-Em_Desenvolvimento-yellow?style=for-the-badge)

## Sobre o Projeto

O **ReservaCode** é uma API RESTful desenvolvida em Java com Spring Boot para o gerenciamento e agendamento de salas. O sistema permite a criação de usuários, cadastro de salas disponíveis e a realização de reservas, garantindo que não haja conflitos de horários e sobreposição de agendamentos.

##  Tecnologias Utilizadas

* **Linguagem:** Java
* **Framework:** Spring Boot
* **Acesso a Dados:** Spring Data JPA / Hibernate
* **Gerenciamento de Dependências:** Maven
* **Padrões de Projeto:** DTO (Data Transfer Object), Controller-Service-Repository

##  Funcionalidades Atuais

O projeto está estruturado em uma arquitetura em camadas e atualmente suporta as seguintes operações:

* **👤 Gestão de Usuários (`Usuario`):**
    * Criação de novos usuários no sistema.
    * Validação de e-mails duplicados (`EmailJaExiste`).
* **🚪 Gestão de Salas (`Sala`):**
    * Cadastro de novas salas para agendamento.
* **📅 Gestão de Reservas (`Reserva`):**
    * Criação de reservas associando um Usuário a uma Sala.
    * Controle de status da reserva através do Enum `StatusReserva`.
    * Validação de disponibilidade de sala para evitar conflitos de agenda (`SalaOcupada`).

##  Estrutura do Projeto

A arquitetura do projeto foi desenhada para ser escalável e de fácil manutenção:

* `controller`: Endpoints da API REST (ex: `ReservaController`, `SalaController`, `UserController`).
* `dto`: Objetos de transferência de dados (`request` e `response`) para isolar as entidades do banco de dados das requisições HTTP.
* `entity`: Classes de modelo que representam as tabelas no banco de dados.
* `exceptions`: Tratamento global de erros e exceções de negócio customizadas (ex: `NaoEncontrado`, `ErrorException`).
* `repository`: Interfaces do Spring Data JPA para acesso e persistência de dados.
* `service`: Camada de regras de negócio e orquestração.
* `security`: *Em implementação...*

##  Roadmap (Próximos Passos)

- [ ] **Spring Security:** Implementação de autenticação e autorização.
- [ ] **Controle de Acesso (RBAC):** Criação de perfis `ADMIN` (pode criar salas e gerenciar tudo) e `USER` (pode apenas realizar e visualizar suas reservas).
- [ ] **Autenticação via JWT:** Proteção dos endpoints da API com tokens.

##  Como Executar o Projeto Localmente

1. Clone o repositório:
   ```bash
   git clone https://github.com/mathesilva/system-reserva.git
   Acesse a pasta do projeto:
    Bash
    cd systemreserva
    Atualize as dependências e rode a aplicação via Maven Wrapper:
    ./mvnw spring-boot:run

````