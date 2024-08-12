package messages;

public class SpecificTopic {
    public String Name;

    public SpecificTopic() {}

    public SpecificTopic(String name) {
        this.Name = name;
    }

    // Getter and Setter
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    @Override
    public String toString() {
        return "messages.SpecificTopic{" +
                "name='" + Name + '\'' +
                '}';
    }
}
