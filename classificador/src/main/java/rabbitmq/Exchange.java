package rabbitmq;

import com.rabbitmq.client.Channel;

import java.io.IOException;

public class Exchange {
    public static String[] names = {"esportes", "financas", "noticias"};

    public static String formatName(String topicName) {
        return topicName.replace(" ", "_").toLowerCase();
    }

    public static String formatRoutingKeyName(String topicName) {
        return topicName.replace(" ", "_").toLowerCase();
    }

    public static void declareMany(Channel channel, String[] exchanges) throws IOException {
        for (String exchange : exchanges) {
            channel.exchangeDeclare(exchange, "direct");
        }
    }
}
