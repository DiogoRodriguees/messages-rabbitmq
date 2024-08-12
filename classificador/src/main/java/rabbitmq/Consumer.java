package rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import messages.Message;

import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Consumer {
    private final static String QUEUE_NAME = "hello";
    private final static ObjectMapper objectMapper = new ObjectMapper(); // Inicialize o ObjectMapper

    public void execute() {
        try {
            System.out.println("Starting connection");
            // Configurar a conexão com o RabbitMQ
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            // Declarar a fila de onde vamos consumir as mensagens
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

            // Definir o callback que será executado quando uma mensagem for recebida
//            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
//                String message = new String(delivery.getBody(), "UTF-8");
//                System.out.println(" [x] Received '" + message + "'");
//            };
            // Definir o callback que será executado quando uma mensagem for recebida
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String messageBody = new String(delivery.getBody(), "UTF-8");

                try {
                    // Desserializar o JSON para um objeto messages.Message
                    Message message = objectMapper.readValue(messageBody, Message.class);
//                    System.out.println(" [x] Received '" + message + "'");
                    System.out.println("Topic:" + message.GenericTopic.Name);
                } catch (IOException e) {
                    System.err.println("Failed to deserialize message: " + e.getMessage());
                }
            };
            // Consumir mensagens da fila
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
        } catch(Exception e) {
            System.out.println("Deu ruim");

        }
    }
}
