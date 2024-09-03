package rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQManager {
    /**
     * Open connection with rabbitmq server
     * 
     * @param host URL of rabbitmq server
     * @return Channel
     * @throws IOException
     * @throws TimeoutException
     */
    public static Channel createChannel(String host) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        Connection connection = factory.newConnection();
        return connection.createChannel();
    }
}
