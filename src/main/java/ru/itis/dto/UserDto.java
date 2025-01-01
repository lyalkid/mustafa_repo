package ru.itis.dto;

import lombok.*;

import java.sql.Date;

@Value
@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class UserDto {
    Long id;
    String username;
    String password;
    String email;
    private String firstName;
    private String secondName;
    private Date birthDate;


}