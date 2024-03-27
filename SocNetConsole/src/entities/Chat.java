package entities;

import java.util.UUID;

public class Chat {
    public UUID id = UUID.randomUUID();
    public String name;

    public Chat() {}

    public Chat(String name) {
        this.name = name;
    }
}
