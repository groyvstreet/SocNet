package org.example.socnetapi.dtos.userdtos;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class GetUserDto {
    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String image;
}
