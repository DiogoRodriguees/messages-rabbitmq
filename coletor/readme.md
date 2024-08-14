## Dependências

-   GO version 1.22.3 linux/amd64
-   Docker version 27.1.1
-   Servidor RabbitMQ em execução

## Como executar

### Usando go CLI

```bash
    # entry on source folder
    $ cd ./src

    # compile and install packages and dependencies
    $ go install

    # running
    $ go run main.go
```

### Usando Docker

```bash
    # building images
    $ docker build -t coletor .

    # creating container
    $ docker run --name coletor-app coletor
```
