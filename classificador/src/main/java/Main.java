import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import rabbitmq.Consumer;
import rabbitmq.RabbitMQManager;

public class Main {
    public static void main(String[] argv) throws Exception {
        Channel channel = RabbitMQManager.createChannel("localhost");
        channel.queueDeclare("topics", false, false, false, null);

        Consumer.execute(channel);
    }
}
