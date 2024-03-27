package entities;

import java.util.UUID;

public class Role {
    public UUID id = UUID.randomUUID();
    public String name;

    public Role() {}

    public Role(String name) {
        this.name = name;
    }
}
