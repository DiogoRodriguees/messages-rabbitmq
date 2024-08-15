package messages;

public class Topic {
    public String Name;

    public Topic() {}
    public Topic(String name) {
        this.Name = name;
    }

    @Override
    public String toString() {
        return "messages.GenericTopic{" +
                "name='" + Name + '\'' +
                '}';
    }
}
