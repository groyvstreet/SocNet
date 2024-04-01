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

@Component
public class AuthorizationUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public AuthorizationUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException(STR."No such user with email = \{email}");
        }

        return new User(
                email,
                user.get().getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
