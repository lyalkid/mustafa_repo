package ru.itis.model;

import lombok.*;
import ru.itis.dto.CreateReviewDto;

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
    Long userId;
    Long announcementId;
    String author;
    public Review(CreateReviewDto createReviewDto) {

        this.rate = Integer.parseInt(createReviewDto.getRate());
        this.comment = createReviewDto.getComment();
        this.userId = createReviewDto.getUser().getId();
        this.announcementId = createReviewDto.getAnnouncementId();
    }
}
