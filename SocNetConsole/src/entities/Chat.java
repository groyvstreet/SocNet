package entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class Chat extends Entity {
    private UUID id = UUID.randomUUID();
    private String name;
    private ArrayList<UUID> messageIds = new ArrayList<>();
    private ArrayList<UUID> userIds = new ArrayList<>();

    public Chat() {}

    public Chat(String name) {
        this.name = name;
    }
}
