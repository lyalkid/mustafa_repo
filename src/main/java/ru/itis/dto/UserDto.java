package ru.itis.dto;

import lombok.*;
import ru.itis.model.Role;

import java.sql.Date;


@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String secondName;
    private Date birthDate;
    private String role;
}