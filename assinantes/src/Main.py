from RabbitMQ import RabbitMQ
import sys


amount_params = sys.argv.__len__()
print("amount_params: ", amount_params)

sys.argv.pop(0)
print("params: ", sys.argv)
params = list(sys.argv)
if amount_params > 1:
    rabbitmq = RabbitMQ()
    rabbitmq.consume_messages(params)
else:
    print("Amount params invalid")
