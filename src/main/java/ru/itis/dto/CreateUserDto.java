package ru.itis.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Value
@Builder
@Data
public class CreateUserDto {
    String id;
    String username;
    String email;
    String password;
    String firstName;
    String secondName;
    String birthDate;
    String role;
}
