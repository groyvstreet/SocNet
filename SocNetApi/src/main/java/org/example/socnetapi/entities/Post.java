package org.example.socnetapi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "posts")
@Getter
@Setter
public class Post {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "date_time", nullable = false)
    private Instant dateTime;

    @Column(name = "text", nullable = false, length = Integer.MAX_VALUE)
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    private User user;

    //@OneToMany(mappedBy = "post")
    //private Set<Comment> comments = new LinkedHashSet<>();
}
