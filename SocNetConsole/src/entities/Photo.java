package entities;

import java.util.UUID;

public class Photo {
    public UUID id = UUID.randomUUID();
    public String source;
    public UUID userId;

    public Photo() {}

    public Photo(String source,
                 UUID userId) {
        this.source = source;
        this.userId = userId;
    }
}
