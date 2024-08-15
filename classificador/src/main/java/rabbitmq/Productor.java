package rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import messages.SpecificTopic;

public class Productor {
    public static void sendTopic(SpecificTopic specificTopic, Channel channel) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            byte[] msg = objectMapper.writeValueAsBytes(specificTopic);

            channel.basicPublish("topic_" + specificTopic.Name.toLowerCase().replace(" ", "_"), specificTopic.Name.split("_")[0], null, msg);
            System.out.println(" * Sent '" + "topic_" + specificTopic.Name.toLowerCase().replace(" ", "_") + "'");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[Send Topic] Exception: " + e.getMessage());
        }
    }
}
