package entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class Role extends Entity {
    private UUID id = UUID.randomUUID();
    private String name;

    public Role() {}

    public Role(String name) {
        this.name = name;
    }
}
