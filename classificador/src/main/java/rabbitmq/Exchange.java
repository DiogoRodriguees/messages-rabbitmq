package rabbitmq;

import java.io.IOException;

import com.rabbitmq.client.Channel;

public class Exchange {
    public static String[] names = { "esportes", "financas", "noticias" };

    /**
     * Format topic name to lower case sepatared by spaces
     * 
     * @param topicName
     * @return
     */
    public static String formatName(String topicName) {
        return topicName.replace(" ", "_").toLowerCase();
    }

    /**
     * Declare exchanges based on list
     * 
     * @param channel   rabbitmq connection
     * @param exchanges exchanges list that will be create
     * @throws IOException
     */
    public static void declareMany(Channel channel, String[] exchanges) throws IOException {
        for (String exchange : exchanges) {
            channel.exchangeDeclare(exchange, "direct");
        }
    }
}
