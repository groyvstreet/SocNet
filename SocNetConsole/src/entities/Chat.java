package entities;

import lombok.Data;

import java.util.UUID;

@Data
public class Chat {
    private UUID id = UUID.randomUUID();
    private String name;

    public Chat() {}

    public Chat(String name) {
        this.name = name;
    }
}
