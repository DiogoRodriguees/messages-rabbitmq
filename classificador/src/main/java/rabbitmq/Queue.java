package rabbitmq;

import java.io.IOException;

import com.rabbitmq.client.Channel;

public class Queue {
    public final static String tweets = "tweets";
    public static String[] names = {"futebol", "volei", "tenis", "basquete", "mercado_imobiliario", "bolsa_de_valores", "politica", "educacao"};

    public static String formatExchangeName(String topicName) {
        return topicName.replace(" ", "_").toLowerCase();
    }

    public static String formatRoutingKeyName(String topicName) {
        return topicName.replace(" ", "_").toLowerCase();
    }

    public static void declareAllExchanges(Channel channel, String[] exchanges) throws IOException {
        for (String exchange : exchanges) {
            channel.exchangeDeclare(exchange, "direct");
        }
    }
}
