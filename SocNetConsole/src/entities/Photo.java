package entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class Photo extends Entity {
    private UUID id = UUID.randomUUID();
    private String source;
    private UUID userId;

    public Photo() {}

    public Photo(String source,
                 UUID userId) {
        this.source = source;
        this.userId = userId;
    }
}
