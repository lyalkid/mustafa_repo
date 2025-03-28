package ru.itis.dto;

import lombok.Builder;
import lombok.Data;
import ru.itis.model.User;

@Data
@Builder
public class CreateReviewDto {
    Long id;
    String rate;
    String comment;
    User user;
    Long announcementId;
}
