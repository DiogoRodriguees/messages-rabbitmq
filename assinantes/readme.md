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

    # running ex: python3 Main.py esportes:volei,futebol noticias:educacao
    $ python3 Main.py <exchange_name>:<topic_separated_by_comma>,<topic_separated_by_comma>
```

### Usando Docker

```bash
    # building images
    $ docker build -t assinantes .

    # creating container
    $ docker run --network <your-network> --name assinantes-app assinantes arg1 arg2 ...
```
