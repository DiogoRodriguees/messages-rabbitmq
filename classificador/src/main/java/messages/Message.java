package messages;

import java.util.List;

public class Message {
    public GenericTopic GenericTopic;
    public List<SpecificTopic> SpecificTopics;

    public Message() {}
    public Message(GenericTopic genericTopic, List<SpecificTopic> specificTopics) {
        this.GenericTopic = genericTopic;
        this.SpecificTopics = specificTopics;
    }

    @Override
    public String toString() {
        return "messages.Message{" +
                "messages.GenericTopic=" + GenericTopic +
                ", SpecificTopics=" + SpecificTopics +
                '}';
    }
}
