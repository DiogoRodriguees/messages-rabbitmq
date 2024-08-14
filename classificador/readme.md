## Dependências

-   Java version 17
-   Docker version 27.1.1
-   Servidor RabbitMQ em execução

## Como executar

### Usando maven

```bash
    # compile and install packages and dependencies
    $ mvn clean install

    # running
    $ mvn exec:java -Dexec.mainClass="Main"
```

### Usando Docker

```bash
    # building images
    $ docker build -t classificador .

    # creating container
    $ docker run --name classificador-app classificador
```
