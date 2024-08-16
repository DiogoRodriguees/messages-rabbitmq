package rabbitmq;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import dtos.SpecificTopic;
import dtos.Tweet;

public class Consumer {

    // callback function used by rabbitmq
    public static DeliverCallback deliveryCallBack(Channel channel) {
        // mapper to convert objet message to tweet class
        ObjectMapper objectMapper = new ObjectMapper();

        return (consumerTag, delivery) -> {
            // read message
            String messageBody = new String(delivery.getBody(), "UTF-8");

            // convert message to tweet class
            Tweet tweet = objectMapper.readValue(messageBody, Tweet.class);
            System.out.println("Topic:" + tweet.Topic.Name);

            // generate broadcast by specific topic
            for (SpecificTopic specificTopic : tweet.SpecificTopics) {
                Productor.broadcast(specificTopic, channel);
            }
        };
    }

    // run consumer
    public static void execute(Channel channel, String queue) throws IOException {
        // callback function called when receive message
        DeliverCallback deliverCallback = Consumer.deliveryCallBack(channel);

        // configure queue to consume
        channel.basicConsume(queue, true, deliverCallback, consumerTag -> {
        });
    }
}
