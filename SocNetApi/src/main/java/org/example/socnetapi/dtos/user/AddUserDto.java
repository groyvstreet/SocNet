package org.example.socnetapi.dtos.user;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AddUserDto {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
}
