package rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import messages.Message;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import messages.SpecificTopic;

public class Consumer {
    private final static String QUEUE_NAME = "hello";

    public void execute() {
        try {
             ObjectMapper objectMapper = new ObjectMapper(); // Inicialize o ObjectMapper
            System.out.println("Starting connection");
//             Configurar a conexão com o RabbitMQ
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            // Declarar a fila de onde vamos consumir as mensagens
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

            // Definir o callback que será executado quando uma mensagem for recebida
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String messageBody = new String(delivery.getBody(), "UTF-8");

                try {
                    // Desserializar o JSON para um objeto messages.Message
                    Message message = objectMapper.readValue(messageBody, Message.class);
//                    System.out.println(" [x] Received '" + message + "'");
                    System.out.println("Topic:" + message.GenericTopic.Name);
                    for (int i = 0; i< message.SpecificTopics.size(); i++) {
                        this.sendTopic(message.SpecificTopics.get(i));
                    }

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

    public void sendTopic(SpecificTopic specificTopic) {
        try{
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(specificTopic.Name, false, false, false, null);

            ObjectMapper objectMapper = new ObjectMapper();
            byte[] msg = objectMapper.writeValueAsBytes(specificTopic);

            channel.basicPublish("", specificTopic.Name, null, msg);
            System.out.println(" [x] Sent '" + specificTopic.Name + "'");
        }catch (Exception e){
            System.out.println("Deu ruim");
        }
   }
}
