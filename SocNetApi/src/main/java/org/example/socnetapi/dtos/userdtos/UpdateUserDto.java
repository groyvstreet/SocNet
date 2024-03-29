package org.example.socnetapi.dtos.userdtos;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class UpdateUserDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String image;
}
