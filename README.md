# MaisPrati República API

API backend para o projeto MaisPrati, uma plataforma para gerenciamento de repúblicas estudantis. Este sistema permite o cadastro de usuários, autenticação via Token JWT, e gerenciamento de perfis.

---

## 🚀 Funcionalidades Implementadas

-   **CRUD de Usuário:** Sistema para Cadastro (`C`), Leitura (`R`) e Atualização (`U`) de perfis de usuário.
-   **Autenticação Segura:** Fluxo completo de login com Tokens JWT para proteger os endpoints da API.
-   **Documentação Interativa:** API documentada e testável em tempo real através do Swagger UI.
-   **Ambiente Containerizado:** Configuração completa com Docker e Docker Compose para rodar a aplicação e o banco de dados PostgreSQL de forma isolada e consistente.
-   **Inicialização de Dados (Seeding):** Criação automática de um usuário `admin` no ambiente de desenvolvimento para facilitar os testes.

---

## 🛠️ Tecnologias Utilizadas

-   **Backend:** Java 21, Spring Boot 3
-   **Segurança:** Spring Security, JSON Web Tokens (JWT)
-   **Banco de Dados:** PostgreSQL (Produção/Docker) e H2 (Desenvolvimento)
-   **Persistência:** Spring Data JPA / Hibernate
-   **Build:** Apache Maven
-   **Containerização:** Docker / Docker Compose
-   **Documentação:** Springdoc OpenAPI (Swagger UI)

---

## 📋 Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina:
-   [JDK 21](https'www.oracle.com/java/technologies/downloads/#java21) ou superior
-   [Apache Maven 3.9+](https'maven.apache.org/download.cgi)
-   [Docker](https'www.docker.com/products/docker-desktop/) e Docker Compose

---

## ▶️ Como Executar o Projeto

Existem duas formas de rodar a aplicação: localmente para desenvolvimento ou via Docker para um ambiente de produção.

### Ambiente de Desenvolvimento (com H2)

Ideal para desenvolver e testar rapidamente, pois usa um banco de dados em memória (H2) e cria um usuário `admin` automaticamente.

1.  **Clone o repositório:**
    ```bash
    git clone git@github.com:lucassenacode/MaisPratiRepublica.git
    cd MaisPratiRepublica
    ```

2.  **Execute o projeto com Maven:**
    O perfil `dev` será ativado por padrão no arquivo `application.properties`.
    ```bash
    mvn spring-boot:run
    ```

3.  A API estará disponível em `http://localhost:8080`.
    -   O usuário `admin@vortex.com` com senha `admin123` será criado automaticamente.

### Ambiente de Produção (com Docker e PostgreSQL)

Simula um ambiente de produção, executando a aplicação e o banco de dados PostgreSQL em containers Docker.

1.  **Certifique-se de que o Docker Desktop está em execução.**

2.  **Construa as imagens Docker:**
    Na raiz do projeto, execute:
    ```bash
    docker-compose build
    ```

3.  **Inicie os containers:**
    ```bash
    docker-compose up
    ```

4.  A API estará disponível em `http://localhost:8080` e conectada ao banco de dados PostgreSQL.
    -   Neste modo, **nenhum usuário é criado automaticamente**. Você precisará cadastrar o primeiro usuário através da API.

---

## 🏦 Como Conectar ao Banco de Dados PostgreSQL

Quando o ambiente Docker estiver rodando (`docker-compose up`), você pode se conectar ao banco de dados PostgreSQL usando uma ferramenta como o DBeaver com as seguintes credenciais:

-   **Host:** `localhost`
-   **Porta:** `5432`
-   **Base de Dados:** `vortex_db`
-   **Usuário:** `postgres`
-   **Senha:** `admin123`

---

## 📖 Como Testar a API

A forma mais fácil de testar é usando a documentação interativa do Swagger.

1.  **Acesse o Swagger UI:**
    Com a aplicação rodando, acesse no seu navegador:
    [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

2.  **Fluxo de Teste Completo:**

    a. **Cadastre um novo usuário:**
       - Expanda o endpoint `POST /api/usuarios/cadastrar`.
       - Clique em "Try it out".
       - Preencha o JSON com os dados do novo usuário e clique em "Execute".

    b. **Faça o login para obter um token:**
       - Expanda o endpoint `POST /api/auth/login`.
       - Clique em "Try it out".
       - Preencha o JSON com o email e a senha do usuário que você acabou de cadastrar.
       - Clique em "Execute" e **copie o token** da resposta.

    c. **Acesse uma rota protegida:**
       - Expanda o endpoint `GET /api/usuarios/perfil`.
       - Clique no botão **"Authorize"** no topo da página.
       - Na janela que abrir, digite `Bearer ` (com um espaço no final) e cole o seu token. Clique em "Authorize" e depois "Close".
       - Agora, no endpoint de perfil, clique em "Try it out" e depois em "Execute".

    Se tudo estiver correto, você receberá os dados do perfil do usuário logado como resposta.

---

## 👥 Autores

-   [Lucas Sena](https://github.com/lucassenacode)
-   *Adicione aqui os outros membros do grupo*