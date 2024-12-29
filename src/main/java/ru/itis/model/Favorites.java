package ru.itis.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
public class Favorites {
    Long id;
    User user;
    Announcement announcement;

}
