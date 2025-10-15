## 🏗️ Task Manager Backend (Gestão de Projetos de Construção)
API de backend RESTful desenvolvida em Kotlin com Spring Boot, responsável por gerenciar dados de projetos de construção civil, tarefas, colaboradores e documentos, incluindo um robusto fluxo de versionamento e aprovação.

<p align="center">
<img src="https://img.shields.io/badge/Kotlin-A97BFF?style=for-the-badge&logo=kotlin&logoColor=white" />
<img src="https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot" />
<img src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white" />
<img src="https://img.shields.io/badge/OpenAPI-6AA35F?style=for-the-badge&logo=openapi&logoColor=white" />
</p>

## 📋 Funcionalidades Chave da API
- O back-end expõe endpoints para as seguintes funcionalidades:
- Gestão de Projetos (/api/projects): CRUD completo com DTOs para controle de cronograma, status e orçamento.
- Gestão de Tarefas (/api/tasks): CRUD com atribuição a colaboradores e validação de integridade (a tarefa só é salva se o Projeto/Colaborador existir).
- Gestão de Pessoas (/api/employees e /api/teams): Cadastro e filtros de colaboradores por status (ativo/inativo) e organização em times.
- Gestão de Documentos (/api/documents):
- Criação de documentos mestres com código único.
- Versionamento: Rastreia diferentes revisões de arquivos.
- Fluxo de Aprovação: Endpoint para gerenciar a aprovação em 3 estágios por colaboradores.

## ⚙️ Tecnologias Utilizadas
- Linguagem: Kotlin (JVM 21)
- Framework: Spring Boot 3.5.x
- Persistência: Spring Data JPA (Hibernate)
- Banco de Dados: PostgreSQL
- Build Tool: Gradle (Kotlin DSL)
- Documentação: Springdoc OpenAPI / Swagger UI

## 🚀 Como Executar o Projeto (Guia Rápido)
Siga os passos abaixo para configurar e rodar a aplicação localmente.\

Pré-requisitos:
- Java Development Kit (JDK) 21 ou superior.
- PostgreSQL (Servidor deve estar em execução).
- Git.

## 1. Configuração do Banco de Dados
Certifique-se de que o serviço PostgreSQL está rodando.
Crie um novo banco de dados (ex: via pgAdmin ou linha de comando):

### SQL
```SQL
CREATE DATABASE task_manager;
```
### YAML
Edite o arquivo src/main/resources/application.yml com suas credenciais:

#### src/main/resources/application.yml
```YML
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/task_manager # <-- CONFIRA A PORTA
    username: [SEU_USUARIO_POSTGRES] # <-- SUBSTITUA
    password: [SUA_SENHA_POSTGRES]   # <-- SUBSTITUA
  jpa:
    hibernate:
      ddl-auto: update # Cria e atualiza as tabelas automaticamente
    # ...
```
## 2. Rodar a Aplicação
Navegue até o diretório raiz do projeto no terminal e use o Wrapper do Gradle:

```Bash
# Executa a aplicação Spring Boot
./gradlew bootRun
```

A aplicação será iniciada na porta 8080.

## 3. Acessar a Documentação da API (Swagger)
Uma vez que a aplicação esteja rodando, a documentação interativa da API estará acessível no seu navegador. Use esta URL para visualizar todos os endpoints, DTOs e testar as requisições:

🔗 http://localhost:8080/swagger-ui.html

## 🤝 Integração com o Front-end KMP
Esta API foi desenhada para ser consumida pelo projeto Kotlin Multiplatform. O colega do front-end deve:

Utilizar a interface Swagger UI para obter a estrutura exata de todos os DTOs (ProjectRequestDTO, TaskResponseDTO, etc.).

Usar bibliotecas como Ktor Client ou Kotlinx Serialization para lidar com as requisições HTTP e o parseamento de JSON.

## 👥 Contribuidores
Projeto Interdisciplinar - 6º Semestre

Integrantes:

- 1º: Breno Ribeiro Souza
- 2º: Daniele Capistrano
- 3º: Diego Bicelli 
- 4º: Lucas Trindade
- 5º: Gustavo dos Anjos
- 6º: Reryson Santos de Andrade
- 7º: Ulisses da Silva Antonelli
