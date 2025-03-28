package ru.itis.service;

import ru.itis.model.Review;
import ru.itis.repository.FavoritesRepository;
import ru.itis.repository.ReviewRepository;
import ru.itis.repository.impl.FavoritesRepositoryJdbcImpl;
import ru.itis.repository.impl.ReviewRepositoryJdbcImpl;

import java.sql.SQLException;
import java.util.List;

public class ReviewService {
    private ReviewRepository reviewRepository;

    public ReviewService() {
        this.reviewRepository = new ReviewRepositoryJdbcImpl();
    }

    public Review getReviewByUser(Long userId, Long announcementId) {
        return reviewRepository.findByUserAnnouncement(userId, announcementId);

    }

    public void addReview(Review review) {
            reviewRepository.save(review);
    }

    public boolean isReviewed(Long userId, Long announcementId) {
        var list = reviewRepository.findAllByUser(userId);
        boolean favorite = false;
        for (var item : list) {
            if (item.getAnnouncementId().equals(announcementId)) {
                favorite = true;
                break;
            }
        }
        return favorite;
    }
    public List<Review> getReviewByAnnouncement(Long announcementId) {
        return reviewRepository.findByAnnouncement(announcementId);

    }
}
