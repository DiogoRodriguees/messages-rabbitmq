from SpecificTopic import SpecificTopic
import pika
import json


def callback(ch, method, properties, body):
    try:
        # Desserializar a mensagem JSON para um dicionário Python
        data = json.loads(body)

        # Criar um objeto SpecificTopic a partir do JSON
        specific_topic = SpecificTopic(name=data["Name"])

        # Exibir o objeto
        print(f" [x] Received {specific_topic}")

    except Exception as e:
        print(f"Failed to process message: {e}")


class RabbitMQ:

    def consume_messages(self, exchange, routing_keys):
        print(" [*] Waiting for messages. To exit press CTRL+C")

        # Conectar ao RabbitMQ
        conn = pika.BlockingConnection(pika.ConnectionParameters(host="localhost"))
        channel = conn.channel()

        # Declarar a fila de onde vamos consumir as mensagens
        for routing_key in routing_keys:
            result = channel.queue_declare(queue="", exclusive=True)
            queue = result.method.queue

            print(f"Connecting(exchange:routing_key) {exchange}:{routing_key}")
            channel.queue_bind(queue=queue, exchange=exchange, routing_key=routing_key)
            channel.basic_consume(queue=queue, on_message_callback=callback)

        # Iniciar o consumo de mensagens
        channel.start_consuming()
