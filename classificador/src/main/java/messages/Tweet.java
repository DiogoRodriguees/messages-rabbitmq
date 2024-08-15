package messages;

import java.util.List;

public class Tweet {
    public Topic Topic;
    public List<SpecificTopic> SpecificTopics;

    public Tweet() {}
    public Tweet(Topic genericTopic, List<SpecificTopic> specificTopics) {
        this.Topic = genericTopic;
        this.SpecificTopics = specificTopics;
    }

    @Override
    public String toString() {
        return "messages.Message{" +
                "messages.GenericTopic=" + Topic +
                ", SpecificTopics=" + SpecificTopics +
                '}';
    }
}
