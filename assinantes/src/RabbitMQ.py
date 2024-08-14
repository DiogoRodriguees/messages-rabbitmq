from SpecificTopic import SpecificTopic
import pika
import json


class RabbitMQ:
    def callback(self, ch, method, properties, body):
        try:
            # Desserializar a mensagem JSON para um dicionário Python
            data = json.loads(body)

            # Criar um objeto SpecificTopic a partir do JSON
            specific_topic = SpecificTopic(name=data["Name"])

            # Exibir o objeto
            print(f" [x] Received {specific_topic}")

        except Exception as e:
            print(f"Failed to process message: {e}")

    def consume_messages(self):
        # Conectar ao RabbitMQ
        connection = pika.BlockingConnection(pika.ConnectionParameters("rabbitmq"))
        channel = connection.channel()

        # Declarar a fila de onde vamos consumir as mensagens
        queue_name = "Futebol"
        channel.queue_declare(queue=queue_name)

        print(" [*] Waiting for messages. To exit press CTRL+C")

        # Consumir mensagens da fila
        channel.basic_consume(
            queue=queue_name, on_message_callback=self.callback, auto_ack=True
        )

        # Iniciar o consumo de mensagens
        channel.start_consuming()
