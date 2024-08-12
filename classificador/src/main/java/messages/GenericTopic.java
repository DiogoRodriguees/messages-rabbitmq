package messages;

public class GenericTopic {
    public String Name;

    public GenericTopic() {}

    public GenericTopic(String name) {
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
        return "messages.GenericTopic{" +
                "name='" + Name + '\'' +
                '}';
    }
}
