package ru.itis.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Announcement {
    private int id;
    private String title;
    private String description;
}
