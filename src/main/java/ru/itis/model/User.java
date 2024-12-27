package ru.itis.model;


import lombok.*;


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
}
