import rabbitmq.Consumer;

public class Main {
    public static void main(String[] argv) throws Exception {
        Consumer consumer = new Consumer();
        consumer.execute();
    }
}
