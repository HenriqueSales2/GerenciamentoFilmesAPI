
# 🎬 API de Gerenciamento de Filmes

Esta é uma API RESTful desenvolvida em Java utilizando o framework **Spring Boot**, com persistência em banco de dados **MySQL**. O sistema permite o cadastro, atualização, listagem e exclusão de **filmes e seus gêneros**.

---

## 🚀 Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- Swagger (Documentação)
- Lombok

---

## 📂 Estrutura de Diretórios

```
src
├── main
│   ├── java
│   │   └── com.henrique.GerenciamentoFilmes
│   │       ├── Controller
│   │       ├── Model
│   │       ├── Repository
│   │       ├── Service
│   ├── resources
│       └── application.properties
```

---

## 🔧 Configuração do Banco de Dados

No arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/filmes
spring.datasource.username=root
spring.datasource.password=SUASENHA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 📌 Funcionalidades da API

### 🎥 Filmes

- `GET /filmes` — Listar todos os filmes
- `GET /filmes/{id}` — Buscar um filme por ID
- `POST /filmes` — Cadastrar um novo filme
- `PUT /filmes/{id}` — Atualizar um filme existente
- `DELETE /filmes/{id}` — Remover um filme

### 📁 Gêneros

- `GET /generos` — Listar todos os gêneros
- `POST /generos` — Criar um novo gênero
- `PUT /generos/{id}` — Atualizar um gênero
- `DELETE /generos/{id}` — Remover um gênero

---

## 📑 Documentação da API (Swagger)

A API possui documentação interativa gerada automaticamente pelo **Swagger UI**. Para acessá-la localmente após iniciar a aplicação:

```
http://localhost:8080/henriqueFilmes/swagger-ui/index.html
```

---

## 🧪 Como Executar Localmente

1. Clone o repositório:
```bash
git clone https://github.com/seuusuario/GerenciamentoFilmes.git
```

2. Crie o banco de dados no MySQL:
```sql
CREATE DATABASE filmes;
```

3. Configure o `application.properties` com seu usuário e senha do MySQL.

4. Execute o projeto pela sua IDE (IntelliJ, Eclipse, etc) ou via terminal:
```bash
./mvnw spring-boot:run
```

---

## 📘 Observações

- A API ainda não possui autenticação (JWT), mas é possível adicionar com Spring Security.
- O projeto segue a arquitetura em camadas: **Controller → Service → Repository → Model**
- Você pode testar a API com ferramentas como Postman ou Insomnia.

---

## 📚 Autor

Desenvolvido por **Henrique Oliveira Sales**  
📧 https://www.linkedin.com/in/henriquessales/

---

## 📝 Licença

Este projeto está licenciado sob a licença MIT.
