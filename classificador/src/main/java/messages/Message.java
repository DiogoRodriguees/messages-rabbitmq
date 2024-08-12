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

    // Getters and Setters
    public GenericTopic getGenericTopic() {
        return GenericTopic;
    }

    public void setGenericTopic(GenericTopic genericTopic) {
        this.GenericTopic = genericTopic;
    }

    public List<SpecificTopic> getSpecificTopics() {
        return SpecificTopics;
    }

    public void setSpecificTopics(List<SpecificTopic> specificTopics) {
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
