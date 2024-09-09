/* 
 * Descrição: responsavel pela implementação de metodos 
 * de consumidores do rabbitmq
 */
package rabbitmq;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import dtos.SpecificTopic;
import dtos.Tweet;

public class Consumer {

    /**
     * @param channel rabbitmq connection
     * @return callback function to execute when to listen message
     */
    public static DeliverCallback deliveryCallBack(Channel channel) {
        // mapper to convert objet message to tweet class
        ObjectMapper objectMapper = new ObjectMapper();

        return (consumerTag, delivery) -> {
            // read message
            String messageBody = new String(delivery.getBody(), "UTF-8");

            // convert message to Twwet class
            Tweet tweet = objectMapper.readValue(messageBody, Tweet.class);
            System.out.println("Topic:" + tweet.Topic.Name);

            // generate broadcast by specific topic
            for (SpecificTopic specificTopic : tweet.SpecificTopics) {
                String exchange = Exchange.formatName(tweet.Topic.Name);
                String routingKey = Exchange.formatName(specificTopic.Name);
                Productor.broadcast(exchange, routingKey, specificTopic, channel);
            }
        };
    }

    /**
     * Set consumer on rabbitmq channel
     * 
     * @param channel channel connection with rabbitmq
     * @param queue   name queue to connect to listenning
     * @throws IOException
     */
    public static void execute(Channel channel, String queue) throws IOException {
        // callback function called when receive message
        DeliverCallback deliverCallback = Consumer.deliveryCallBack(channel);

        // configure queue to consume
        channel.basicConsume(queue, true, deliverCallback, consumerTag -> {
        });
    }
}
