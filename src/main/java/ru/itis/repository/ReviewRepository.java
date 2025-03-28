package ru.itis.repository;

import ru.itis.model.Review;

import java.sql.SQLException;
import java.util.List;

public interface ReviewRepository {
    void save(Review review);

    void updateComment(String newComment);

    void updateRate(int newRate);

    boolean deleteById(Long id) throws SQLException;

    Review findByUserAnnouncement(Long userId, Long announcementId);

    List<Review> findByAnnouncement(Long announcementId);

    List<Review> findAllByUser(Long userId);

}
