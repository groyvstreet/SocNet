package org.example.socnetapi.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class AuthenticationResponse {
    private UUID id;
    private String token;
}
