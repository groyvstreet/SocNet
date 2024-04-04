package org.example.socnetapi.repositories;

import org.example.socnetapi.entities.Chat;
import org.example.socnetapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);

    List<User> findAllByChatsContains(Set<Chat> chats);

    List<User> findAllByChatsNotContains(Set<Chat> chats);
}
