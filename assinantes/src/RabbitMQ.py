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

    def consume_messages(self, queue_names):

        # Conectar ao RabbitMQ
        connection = pika.BlockingConnection(
            pika.ConnectionParameters(host="localhost")
        )
        channel = connection.channel()

        # Declarar a fila de onde vamos consumir as mensagens
        for routing_key in queue_names:
            result = channel.queue_declare(queue="", exclusive=True)
            queue_name = result.method.queue

            print(" [*] Waiting for messages. To exit press CTRL+C")
            channel.queue_bind(
                queue=queue_name, exchange=routing_key, routing_key=routing_key
            )
            channel.basic_consume(queue=queue_name, on_message_callback=callback)

        # Iniciar o consumo de mensagens
        channel.start_consuming()
