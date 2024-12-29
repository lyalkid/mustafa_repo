package ru.itis.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDto {
    Long id;
    String username;
    String password;
    String email;

}
