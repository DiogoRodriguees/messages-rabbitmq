/* 
 * 
 */

import com.rabbitmq.client.Channel;

import rabbitmq.Consumer;
import rabbitmq.Exchange;
import rabbitmq.Queue;
import rabbitmq.RabbitMQManager;

public class Main {
    public static void main(String[] argv) throws Exception {
        // create connection and get channel
        Channel channel = RabbitMQManager.createChannel("rabbitmq");

        // declare queue names at rabbitmq server
        Exchange.declareMany(channel, Exchange.names);

        // run consumer
        Consumer.execute(channel, Queue.tweets);
    }
}
