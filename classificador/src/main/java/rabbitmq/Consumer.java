package rabbitmq;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import messages.Tweet;

public class Consumer {

    public static void execute(Channel channel) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String messageBody = new String(delivery.getBody(), "UTF-8");

            try {
                Tweet tweet = objectMapper.readValue(messageBody, Tweet.class);
                System.out.println("Topic:" + tweet.Topic.Name);

                for (int i = 0; i < tweet.SpecificTopics.size(); i++) {
                    Productor.sendTopic(tweet.SpecificTopics.get(i), channel);
                }

            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Failed to deserialize message: " + e.getMessage());
            }
        };

        channel.basicConsume(Queue.tweets, true, deliverCallback, consumerTag -> {
        });
    }
}
