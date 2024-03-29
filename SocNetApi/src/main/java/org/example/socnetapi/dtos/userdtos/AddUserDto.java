package org.example.socnetapi.dtos.userdtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddUserDto {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
}
