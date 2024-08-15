package rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import messages.SpecificTopic;

public class Productor {
    public static void sendTopic(SpecificTopic specificTopic, Channel channel) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            byte[] msg = objectMapper.writeValueAsBytes(specificTopic);

            channel.basicPublish("", Queue.topics, null, msg);
            System.out.println(" * Sent '" + specificTopic.Name + "'");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[Send Topic] Exception: " + e.getMessage());
        }
    }
}
