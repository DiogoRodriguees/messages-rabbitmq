package messages;

public class SpecificTopic {
    public String Name;

    public SpecificTopic() {}
    public SpecificTopic(String name) {
        this.Name = name;
    }

    @Override
    public String toString() {
        return "messages.SpecificTopic{" +
                "name='" + Name + '\'' +
                '}';
    }
}
