package ru.itis.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Review {
    Long id;
    int rate;
    String comment;
    User user;
    Announcement announcement;
}
