package ru.itis.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Favorites {
    Long id;
    Long userId;
    Long announcementId;
}
