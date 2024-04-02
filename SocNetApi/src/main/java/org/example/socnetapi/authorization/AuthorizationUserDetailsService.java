package org.example.socnetapi.authorization;

import org.example.socnetapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.UUID;

@Component
public class AuthorizationUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public AuthorizationUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        var user = userRepository.findById(UUID.fromString(id));

        if (user.isEmpty()) {
            throw new UsernameNotFoundException(STR."No such user with id = \{id}");
        }

        return new User(
                id,
                user.get().getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("USER")));
    }
}
