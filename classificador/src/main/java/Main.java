import com.rabbitmq.client.Channel;

import rabbitmq.Consumer;
import rabbitmq.Exchange;
import rabbitmq.Queue;
import rabbitmq.RabbitMQManager;

public class Main {
    public static void main(String[] argv) throws Exception {
        // create connection and return channel
        Channel channel = RabbitMQManager.createChannel("rabbitmq");

        // declare queue names on rabbitmq server
        Exchange.declareMany(channel, Exchange.names);

        // consuming queue tweets
        Consumer.execute(channel, Queue.tweets);
    }
}
