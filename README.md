# ProjetoSpring

API REST de um sistema de pedidos desenvolvida com Spring Boot, Java, JPA e PostgreSQL. A aplicação modela usuários, produtos, categorias, pedidos, itens de pedido e pagamentos, disponibilizando operações de consulta para o catálogo e pedidos, além de operações CRUD para usuários.

## Tecnologias

- Java 25
- Spring Boot 4.1.1
- Spring Web MVC
- Spring Data JPA
- Hibernate
- PostgreSQL
- H2 Database
- Maven
- JUnit

## Arquitetura

O projeto segue uma organização em camadas:

```text
src/
├── main/
│   ├── java/com/cursoJava/ProjetoSpring/
│   │   ├── config/          # Configurações e carga de dados de teste
│   │   ├── entities/        # Entidades JPA e enumerações
│   │   ├── repository/      # Interfaces de acesso ao banco
│   │   ├── resource/        # Controllers e tratamento de exceções HTTP
│   │   └── services/        # Regras de negócio
│   └── resources/           # Configurações por perfil
└── test/                    # Testes automatizados
```

O fluxo de uma requisição é:

```text
Cliente HTTP → Resource (Controller) → Service → Repository → Banco de dados
```

Os repositories estendem `JpaRepository`, obtendo automaticamente operações de persistência e consulta fornecidas pelo Spring Data JPA.

## Modelo de domínio

| Entidade    | Descrição                                                        |
|-------------|------------------------------------------------------------------|
| `User`      | Cliente cadastrado, com nome, e-mail, telefone e senha.          |
| `Order`     | Pedido de um cliente, com data, status, itens e pagamento.       |
| `OrderItem` | Produto incluído em um pedido, com quantidade e preço praticado. |
| `Product`   | Produto do catálogo, com nome, descrição, preço e imagem.        |
| `Category`  | Categoria de produtos.                                           |
| `Payment`   | Pagamento associado a um pedido e à sua data de realização.      |

Relacionamentos principais:

- Um usuário pode possuir vários pedidos.
- Um pedido pertence a um usuário.
- Um pedido possui vários itens.
- Um produto pode aparecer em vários pedidos.
- Um produto pode pertencer a várias categorias.
- Uma categoria pode conter vários produtos.
- Um pedido pode possuir um pagamento.
- `OrderItem` utiliza uma chave composta formada por `order_id` e `product_id`.

## Pré-requisitos

- JDK 25 ou compatível com a configuração do projeto.
- PostgreSQL instalado e em execução para o perfil `dev`.
- Maven instalado, ou uso do Maven Wrapper incluído (`mvnw.cmd`).
- Banco de dados PostgreSQL chamado `springboot_course`.

## Configuração do PostgreSQL

Crie o banco de dados:

```sql
CREATE DATABASE springboot_course;
```

O perfil `dev` está configurado para:

```text
URL:      jdbc:postgresql://localhost:5432/springboot_course
Usuário:  postgres
Senha:    definida em src/main/resources/application-dev.properties
```

Antes de executar a aplicação, confira e ajuste o usuário, a senha e a porta conforme o ambiente local. Por segurança, não versione credenciais reais.


O perfil ativo padrão é `dev`, definido em `application.properties`. Também é possível informar o perfil explicitamente:


## Perfil de testes com H2

O arquivo `application-test.properties` utiliza um banco H2 em memória. O `TestConfig` é ativado somente com o perfil `test` e insere dados de exemplo automaticamente:

- 2 usuários;
- 3 pedidos;
- 3 categorias;
- 5 produtos;
- 4 itens de pedido;
- 1 pagamento.


O console do H2 fica disponível em:

```text
http://localhost:8080/h2-console
```

## Endpoints

A API não possui prefixo global. Todas as respostas são JSON.

### Usuários

| Método   | Endpoint      | Descrição                        | Status esperado  |
|----------|---------------|----------------------------------|------------------|
| `GET`    | `/users`      | Lista todos os usuários          | `200 OK`         |
| `GET`    | `/users/{id}` | Busca um usuário pelo ID         | `200 OK`         |
| `POST`   | `/users`      | Cadastra um usuário              | `201 Created`    |
| `PUT`    | `/users/{id}` | Atualiza nome, e-mail e telefone | `200 OK`         |
| `DELETE` | `/users/{id}` | Remove um usuário                | `204 No Content` |

Exemplo de cadastro:

```http
POST http://localhost:8080/users
Content-Type: application/json

{
  "name": "João da Silva",
  "email": "joao@example.com",
  "phone": "11999999999",
  "password": "senha-segura"
}
```


### Categorias

| Método | Endpoint           | Descrição                   |
|--------|--------------------|-----------------------------|
| `GET`  | `/categories`      | Lista todas as categorias   |
| `GET`  | `/categories/{id}` | Busca uma categoria pelo ID |

### Produtos

| Método | Endpoint         | Descrição                |
|--------|------------------|--------------------------|
| `GET`  | `/products`      | Lista todos os produtos  |
| `GET`  | `/products/{id}` | Busca um produto pelo ID |

Exemplo de resposta:

```json
{
  "id": 1,
  "name": "The Lord of the Rings",
  "description": "Lorem ipsum dolor sit amet, consectetur.",
  "price": 90.5,
  "imgUrl": "",
  "categories": [
    {
      "id": 2,
      "name": "Books"
    }
  ]
}
```

### Pedidos

| Método | Endpoint       | Descrição               |
|--------|----------------|-------------------------|
| `GET`  | `/orders`      | Lista todos os pedidos  |
| `GET`  | `/orders/{id}` | Busca um pedido pelo ID |

Exemplo de resposta:

```json
{
  "id": 1,
  "moment": "2019-06-20T19:53:07Z",
  "client": {
    "id": 1,
    "name": "Maria Brown",
    "email": "maria@gmail.com",
    "phone": "988888888",
    "password": "123456"
  },
  "orderStatus": "PAID",
  "items": [
    {
      "product": {
        "id": 1,
        "name": "The Lord of the Rings",
        "description": "Lorem ipsum dolor sit amet, consectetur.",
        "price": 90.5,
        "imgUrl": "",
        "categories": [
          {
            "id": 2,
            "name": "Books"
          }
        ]
      },
      "quantity": 2,
      "price": 90.5,
      "subTotal": 181.0
    }
  ],
  "total": 181.0,
  "payment": {
    "id": 1,
    "moment": "2019-06-20T22:53:07Z"
  }
}
```

## Tratamento de erros

Erros de recurso inexistente retornam `404 Not Found`. Erros de integridade ou operação no banco retornam `400 Bad Request`.

Formato padrão:

```json
{
  "timestamp": "2026-08-27T12:00:00Z",
  "status": 404,
  "error": "Resource not found",
  "message": "Resource not found with id 99",
  "path": "/users/99"
}
```

## O que eu aprendi

Este projeto foi fundamental para consolidar conceitos práticos de desenvolvimento backend, destacando:

* **Arquitetura em Camadas:** Separação prática de responsabilidades entre Resource, Service e Repository.
* **ORM com JPA/Hibernate:** Mapeamento de entidades e relacionamentos complexos para o banco de dados relacional.
* **Design de APIs RESTful:** Padronização de rotas, uso correto de verbos HTTP e seus respectivos Status Codes.
* **Tratamento de Exceções:** Implementação de handlers globais para retornar mensagens de erro em formato JSON amigável.
* **Gerenciamento de Ambientes:** Uso de *profiles* do Spring Boot para alternar entre banco H2 (testes) e PostgreSQL (desenvolvimento).


## Créditos e Agradecimentos

Este projeto foi desenvolvido com fins de estudo e portfólio, baseado nos ensinamentos do curso **[Java COMPLETO Programação Orientada a Objetos + Projetos]** ministrado pelo professor **Nélio Alves**.

