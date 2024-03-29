package org.example.socnetapi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "chats")
@Getter
@Setter
public class Chat {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false, length = Integer.MAX_VALUE)
    private String name;

    //@OneToMany(mappedBy = "chat")
    //private Set<ChatUser> chatUsers = new LinkedHashSet<>();

    //@OneToMany(mappedBy = "chat")
    //private Set<Message> messages = new LinkedHashSet<>();
}
