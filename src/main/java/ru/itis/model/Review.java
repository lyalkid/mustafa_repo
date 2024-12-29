package ru.itis.model;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
public class Review {
    Long id;
    int rate;
    String comment;
    User user;
    Announcement announcement;
}
