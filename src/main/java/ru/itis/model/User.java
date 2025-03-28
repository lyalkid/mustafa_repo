package ru.itis.model;


import lombok.*;
import ru.itis.dto.CreateUserDto;
import ru.itis.dto.UserDto;

import java.sql.Date;


@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String secondName;
    private Date birthDate;

    private String role;


    public User(UserDto userDto) {
        this.id = userDto.getId();
        this.username = userDto.getUsername();
        this.email = userDto.getEmail();
        this.password = userDto.getPassword();
        this.firstName = userDto.getFirstName();
        this.secondName = userDto.getSecondName();
        this.birthDate = userDto.getBirthDate();
        this.role = userDto.getRole();
    }

    public User(CreateUserDto userDto) {
        this.username = userDto.getUsername();
        this.email = userDto.getEmail();
        this.password = userDto.getPassword();
        this.firstName = userDto.getFirstName();
        this.secondName = userDto.getSecondName();
        this.birthDate = Date.valueOf(userDto.getBirthDate());
        this.role = userDto.getRole();
    }

    public String role_helper(int role) {
        if (role == 1) {
            return Role.USER;
        }
        return Role.ADMIN;
    }

    public boolean isAdmin() {
        return this.role.equals( Role.ADMIN);
    }

}