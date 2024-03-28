package entities;

import lombok.Data;

import java.util.UUID;

@Data
public class Photo {
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
