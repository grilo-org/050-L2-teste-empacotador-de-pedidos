# Desafio Técnico – Empacotador de Pedidos

Este projeto expõe uma API REST que empacota produtos em caixas com base em suas dimensões e volume. A aplicação foi desenvolvida com **Java + Spring Boot**, utilizando **Docker** para facilitar a execução.

## Como executar

### Pré-requisitos

- Docker instalado

### Passo único

Para subir a aplicação:

```bash
docker-compose up --build
```

A aplicação será exposta localmente na porta **8080**.

## Endpoints disponíveis

Acesse a documentação interativa via Swagger:

📄 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Autenticação:

User: admin

Passord: 1234

| Método | Endpoint        | Descrição                                         |
|--------|------------------|--------------------------------------------------|
| POST   | /api/pedidos     | Recebe uma lista de pedidos e retorna as caixas necessárias para empacotamento |

## Exemplo de uso

```json
{
  "pedidos": [
    {
      "pedido_id": 1,
      "produtos": [
        {
          "produto_id": "Mouse",
          "dimensoes": {
            "altura": 5,
            "largura": 5,
            "comprimento": 5
          }
        }
      ]
    }
  ]
}
```
