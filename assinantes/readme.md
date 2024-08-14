## Dependências

-   Python 3.8.10
-   Docker version 27.1.1
-   Servidor RabbitMQ em execução

## Como executar

### Usando command line

```bash
    # entry on source folder
    $ cd ./src

    # install dependencies
    $ pip install --no-cache-dir -r requirements.txt

    # running
    $ python3 Main.py
```

### Usando Docker

```bash
    # building images
    $ docker build -t assinantes .

    # creating container
    $ docker run --name assinantes-app assinantes
```
