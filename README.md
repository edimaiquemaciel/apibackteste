# MaisPrati República API

API backend para o projeto MaisPrati, uma plataforma para gerenciamento de repúblicas estudantis. Este sistema permite o cadastro de usuários, autenticação via Token JWT, e gerenciamento de perfis.

---

## 🌐 Links do Projeto

- **API no Ar (Railway):** [https://maispratirepublica-production.up.railway.app](https://maispratirepublica-production.up.railway.app)
- **Documentação da API (Swagger UI):** [https://maispratirepublica-production.up.railway.app/swagger-ui.html](https://maispratirepublica-production.up.railway.app/swagger-ui.html)

---

## 🚀 Funcionalidades Implementadas

- **CRUD de Usuário:** Sistema para Cadastro (C), Leitura (R) e Atualização (U) de perfis de usuário.
- **Autenticação Segura:** Fluxo completo de login com Tokens JWT para proteger os endpoints da API.
- **Documentação Interativa:** API documentada e testável em tempo real através do Swagger UI.
- **Ambiente Containerizado:** Configuração completa com Docker e Docker Compose para rodar a aplicação e o banco de dados PostgreSQL.
- **Inicialização de Dados:** Criação automática de um usuário de teste no ambiente de desenvolvimento para facilitar os testes.

---

## 🛠️ Tecnologias Utilizadas

- **Backend:** Java 21, Spring Boot 3
- **Segurança:** Spring Security, JSON Web Tokens (JWT)
- **Banco de Dados:** PostgreSQL (Produção/Docker) e H2 (Desenvolvimento)
- **Persistência:** Spring Data JPA / Hibernate
- **Build:** Apache Maven
- **Containerização:** Docker / Docker Compose
- **Documentação:** Springdoc OpenAPI (Swagger UI)

---

## 📋 Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina:

- [JDK 21](https://www.oracle.com/java/technologies/downloads/#java21) ou superior
- [Apache Maven 3.9+](https://maven.apache.org/download.cgi)
- [Docker](https://www.docker.com/products/docker-desktop/) e Docker Compose

---

## ▶️ Como Executar o Projeto

### Ambiente de Desenvolvimento (com H2)

Ideal para desenvolver e testar rapidamente.

1.  **Clone o repositório:**

    ```bash
    git clone [https://github.com/lucassenacode/maisPraTI-Republicas-backend.git](https://github.com/lucassenacode/maisPraTI-Republicas-backend.git)
    cd maisPraTI-Republicas-backend
    ```

2.  **Execute o projeto com Maven:**
    O perfil `dev` é ativado por padrão.
    ```bash
    mvn spring-boot:run
    ```

- A API estará disponível em `http://localhost:8080`.
- Um usuário de teste é criado na inicialização. Para ver as credenciais padrão, consulte a classe `DataSeeder.java`.

### Ambiente de Produção Simulado (com Docker e PostgreSQL)

Executa a aplicação e o banco de dados em containers Docker.

1.  **Certifique-se de que o Docker Desktop está em execução.**

2.  **Construa e inicie os containers:**
    Na raiz do projeto, execute:
    ```bash
    docker-compose up --build
    ```

- A API estará disponível em `http://localhost:8080`.
- Neste modo, **nenhum usuário é criado automaticamente**.

---

## 🏦 Como Conectar ao Banco de Dados Local (Docker)

Você pode se conectar ao banco de dados PostgreSQL do Docker usando uma ferramenta como o DBeaver. As credenciais (`host`, `porta`, `usuário`, `senha`, `database`) estão definidas na seção `environment` do serviço `postgres-db` no arquivo `docker-compose.yml`.

---

## 📖 Como Testar a API

A forma mais fácil de testar é usando a documentação interativa do Swagger, tanto localmente quanto no ambiente de produção.

- **Swagger Local:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **Swagger Produção:** [https://maispratirepublica-production.up.railway.app/swagger-ui.html](https://maispratirepublica-production.up.railway.app/swagger-ui.html)

### Fluxo de Teste

1.  **Cadastre um novo usuário** usando o endpoint `POST /api/usuarios/cadastrar`.
2.  **Faça o login** com as credenciais cadastradas em `POST /api/auth/login` para obter um token.
3.  **Autorize suas requisições** clicando no botão "Authorize" no Swagger e colando o token no formato `Bearer seu_token`.
4.  **Acesse as rotas protegidas**, como `GET /api/usuarios/perfil`.

---

## 👥 Autores

- Lucas Sena
