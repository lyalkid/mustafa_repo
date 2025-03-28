package ru.itis.dto;

import lombok.*;
import ru.itis.model.Currency;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAnnouncementDto {
        private Long id;
        private String title;
        private String description;
        private Long price;
        private String currency;
        private Long userId;
}
