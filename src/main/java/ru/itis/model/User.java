package ru.itis.model;


import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
}
