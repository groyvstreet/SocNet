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
@Table(name = "comments")
@Getter
@Setter
public class Comment {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "date_time", nullable = false)
    private Instant dateTime;

    @Column(name = "text", nullable = false, length = Integer.MAX_VALUE)
    private String text;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@OnDelete(action = OnDeleteAction.CASCADE)
    //@JoinColumn(name = "post_id")
    //private Post post;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@OnDelete(action = OnDeleteAction.CASCADE)
    //@JoinColumn(name = "user_id")
    //private User user;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@OnDelete(action = OnDeleteAction.CASCADE)
    //@JoinColumn(name = "comment_id")
    //private Comment parentComment;

    //@OneToMany(mappedBy = "parentComment")
    //private Set<Comment> comments = new LinkedHashSet<>();
}
