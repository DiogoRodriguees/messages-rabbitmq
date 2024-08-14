## Dependências

-   GO version 1.22.3 linux/amd64
-   Java version 17
-   Python 3.8.10
-   Docker version 27.1.1
-   Servidor RabbitMQ em execução

## Como executar

### Subindo servidor RabbitMQ

```bash
    # creating container rabbitmq n background (-d)
    $ docker run -d --hostname rabbitmq --name rabbitmq rabbitmq:3
```

### Executando cada parte separada

Lembre de trocar os hosts na conexão com o servidor rabbitmq para localhost (Atualmente ta sendo utilizado rabbitmq por causa do container)

-   [Assinantes](./assinantes/readme.md#como-executar)
-   [Classificador](./classificador/readme.md#como-executar)
-   [Coletor](./coletor/readme.md#como-executar)

### Executando aplicação inteira com Docker Compose

```bash
    # up containers with docker compose
    $ docker compose up --build
```
