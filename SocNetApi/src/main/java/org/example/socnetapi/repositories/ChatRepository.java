package org.example.socnetapi.repositories;

import org.example.socnetapi.entities.Chat;
import org.example.socnetapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ChatRepository extends JpaRepository<Chat, UUID> {
    List<Chat> findAllByUsersContains(Set<User> users);
}
