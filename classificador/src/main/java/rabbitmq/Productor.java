package rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import dtos.SpecificTopic;
import dtos.Topic;

public class Productor {
    // send broadcast to all consumers connects on exchange
    public static void broadcast(Topic topic, SpecificTopic specificTopic, Channel channel) {
        try {
            // convert specific topic to bytes (type used to transmit message on rabbitmq)
            ObjectMapper objectMapper = new ObjectMapper();
            byte[] msg = objectMapper.writeValueAsBytes(specificTopic);

            // format exchange and queue name
            String exchange = Queue.formatExchangeName(specificTopic.Name);
            String routingKey = Queue.formatRoutingKeyName(specificTopic.Name);

            // publishing message to routing key "queue"
            channel.basicPublish(exchange, routingKey, null, msg);
            System.out.println(" * Sent '" + routingKey + "'");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[Send Topic] Exception: " + e.getMessage());
        }
    }
}
