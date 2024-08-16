from RabbitMQ import RabbitMQ
import sys


amount_params = sys.argv.__len__()

sys.argv.pop(0)
params = list(sys.argv)

if amount_params > 1:
    for param in params:
        param_splited = param.split(":")
        exchange = param_splited[0]
        routing_keys = param_splited[1].split(",")

        rabbitmq = RabbitMQ()
        rabbitmq.consume_messages(exchange, routing_keys)
else:
    print("Amount params invalid")
