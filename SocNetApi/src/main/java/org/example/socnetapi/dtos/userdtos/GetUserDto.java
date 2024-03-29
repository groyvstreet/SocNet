package org.example.socnetapi.dtos.userdtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetUserDto {
    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String image;
}
