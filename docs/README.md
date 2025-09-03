# 📑 Republica API - Postman Collection

Este diretório contém a collection do Postman para facilitar os testes da API **Republica**.

## 📂 Arquivos

- `Republica-API.postman_collection.json` → Collection com todos os endpoints da API.

## 🚀 Como usar

1. Abra o **Postman**.
2. Clique em **Import** (canto superior esquerdo).
3. Selecione o arquivo `Republica-API.postman_collection.json`.
4. Após a importação, você verá a collection **Republica API** disponível.

## 🔑 Fluxo recomendado de testes

1. **Cadastrar Usuário**  
   - Endpoint: `POST /usuarios/cadastrar`  
   - Cria um novo usuário no sistema.  

2. **Login**  
   - Endpoint: `POST /auth/login`  
   - Retorna o **JWT token** que deve ser usado nas próximas requisições.  

3. **Perfil do Usuário**  
   - Endpoint: `GET /usuarios/perfil`  
   - Requer token JWT no header `Authorization: Bearer <token>`.  

4. **Atualizar Perfil**  
   - Endpoint: `PUT /usuarios/perfil`  
   - Permite alterar `nome` e `telefone`.  

## ⚙️ Variáveis de ambiente

Para facilitar a troca entre **local** e **Railway**, use variáveis no Postman:

- `baseUrl`  
  - Local: `http://localhost:8080`  
  - Railway: `https://maispratirepublica-production.up.railway.app/`

- `token`  
  - Preenchido automaticamente após o login.  

## ✅ Dicas

- Depois de fazer o login, configure o **Test Script** no Postman para salvar o token em uma variável `token` e reutilizá-lo nas próximas requests.  
- Verifique se o header `Authorization` está configurado como:  
