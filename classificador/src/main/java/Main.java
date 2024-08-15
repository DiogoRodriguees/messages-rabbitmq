import com.rabbitmq.client.Channel;

import rabbitmq.Consumer;
import rabbitmq.RabbitMQManager;

public class Main {
    public static void main(String[] argv) throws Exception {
        String[] queues = { "futebol",
                "volei",
                "tenis",
                "basquete",
                "mercado_imobiliario",
                "bolsa_de_valores",
                "politica",
                "educacao",
        };
        Channel channel = RabbitMQManager.createChannel("localhost");

        for (int i = 0; i < queues.length; i++) {
            channel.exchangeDeclare("topic_" + queues[i], "fanout");
        }

        Consumer.execute(channel);
    }
}
