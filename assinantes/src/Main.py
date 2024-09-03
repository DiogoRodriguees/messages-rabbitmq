from RabbitMQ import RabbitMQ
import sys


amount_params = sys.argv.__len__()
sys.argv.pop(0)  # remove o primeiro parametro (nome do programa)
params = list(sys.argv)  # tranforma os demais parametros em uma lista

# verifica existencia de ao menos um parametro
if amount_params > 1:
    # para cada parametro, cria os binds nas exchanges
    for param in params:
        param_splited = param.split(":")  # separa o topico geral do especifico
        exchange = param_splited[0]  # topico principal é o nome da exchange
        # topcios especifcos sao os nomes das routing keys
        routing_keys = param_splited[1].split(",")

        rabbitmq = RabbitMQ()
        rabbitmq.consume_messages(exchange, routing_keys)
else:
    print("Amount params invalid")
