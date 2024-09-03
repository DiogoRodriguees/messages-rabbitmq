package rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import dtos.SpecificTopic;

public class Productor {
    // send broadcast to all consumers connected on exchange
    public static void broadcast(String exchange, String routingKey, SpecificTopic specificTopic, Channel channel) {
        try {
            // convert specific topic to bytes (type used to transmit message on rabbitmq)
            ObjectMapper objectMapper = new ObjectMapper();
            byte[] msg = objectMapper.writeValueAsBytes(specificTopic);

            // publishing message to routing key
            channel.basicPublish(exchange, routingKey, null, msg);
            System.out.println(" * Sent " + exchange + ": " + routingKey);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[Send Topic] Exception: " + e.getMessage());
        }
    }
}
