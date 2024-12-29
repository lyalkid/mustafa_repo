package ru.itis.model;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
public class PersonalData {
    Long id;
    User user;
    String firstName;
    String secondName;
    Date birthDate;
    String phoneNumber;

    }
