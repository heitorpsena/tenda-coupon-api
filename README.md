# 🚀 Tenda Coupon API

API REST para gerenciamento de cupons desenvolvida como **desafio técnico**.

O sistema permite criar e deletar cupons com validações de regras de negócio encapsuladas no domínio, seguindo boas práticas de arquitetura, testes e documentação.

---

# 📌 Tecnologias utilizadas

* Java 17
* Spring Boot 3
* Spring Web
* Spring Data JPA
* H2 Database (in-memory)
* Swagger / OpenAPI
* JUnit + Mockito
* JaCoCo (test coverage)
* Docker / Docker Compose
* Maven

---

# 🧠 Arquitetura do projeto

O projeto segue princípios de **DDD (Domain-Driven Design) simplificado**, com separação de responsabilidades:

```
domain → regras de negócio
application → orquestração
infrastructure → controllers / DTOs
```

## Estrutura:

```
src/main/java/br/com/tenda/tenda_coupon_api

├── application
│  
├── config
│   
├── domain
│   ├── model
│   ├── repository
│   ├── exception
│   └── validators
│
└── infra
    ├── controller
    └── dto

```

### ✔ Regras de negócio ficam no domínio (Coupon).

---

# ⚙️ Regras de negócio implementadas

## Create Coupon

* Code obrigatório
* Description obrigatória
* DiscountValue obrigatório
* ExpirationDate obrigatório
* Code deve ser alfanumérico com 6 caracteres
* Caracteres especiais são removidos automaticamente
* Desconto mínimo de 0.5
* Data de expiração não pode ser no passado
* Cupom pode ser criado como publicado

## Delete Coupon

* Soft delete no banco
* Não permite deletar duas vezes
* Mantém histórico de dados

---

# ▶️ Como rodar o projeto

## ✅ Pré-requisitos

* Java 17+
* Maven
* Docker (opcional)

---

## Rodar localmente

```
mvn clean install
mvn spring-boot:run
```

Aplicação:

```
http://localhost:8080
```

---

# 🗄 Banco H2

Banco em memória utilizado para desenvolvimento.

Console:

```
http://localhost:8080/h2-console
```

Configuração padrão:

```
JDBC URL: jdbc:h2:mem:testdb
User: sa
Password: (vazio)
```

---

# 📚 Documentação da API (Swagger)

A documentação interativa está disponível em:

```
http://localhost:8080/swagger-ui.html
```

Permite testar todos endpoints diretamente pelo navegador.

---

# 🧪 Testes

Os testes cobrem regras de negócio e fluxo da aplicação.

Tipos de testes:

* Testes de domínio (regras de negócio)
* Testes de serviço
* Testes de controller

Rodar testes:

```
mvn test
```

---

# 📊 Test Coverage

Relatório gerado com JaCoCo.

Gerar:

```
mvn clean verify
```

Abrir relatório:

```
target/site/jacoco/index.html
```

Cobertura mínima exigida pelo desafio: **80%**.

---

# 🐳 Rodando com Docker

## Build do projeto

```
mvn clean package
```

## Subir container

```
docker compose up --build
```

Aplicação disponível em:

```
http://localhost:8080
```

---

# 🔌 Endpoints principais

## Criar cupom

```
POST /coupons
```

### Exemplo request:

```json
{
  "code": "ABC123",
  "description": "10% discount",
  "discountValue": 1.5,
  "expirationDate": "31/12/2026",
  "published": true
}
```

---

## Deletar cupom

```
DELETE /coupons/{id}
```

Realiza soft delete.

---

# 🎯 Decisões técnicas

* Regras de negócio encapsuladas no domínio
* Validações realizadas na entidade
* Soft delete para preservar histórico
* Arquitetura em camadas com separação clara de responsabilidades
* Banco em memória para facilitar execução
* Testes focados em regras de negócio
* Documentação automática com OpenAPI

---

# 👨‍💻 Autor

Desenvolvido por Heitor Sena como desafio técnico para a Tenda.
