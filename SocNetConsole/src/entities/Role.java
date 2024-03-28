package entities;

import lombok.Data;

import java.util.UUID;

@Data
public class Role {
    private UUID id = UUID.randomUUID();
    private String name;

    public Role() {}

    public Role(String name) {
        this.name = name;
    }
}
