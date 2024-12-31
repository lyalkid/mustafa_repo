package ru.itis.repository;

import ru.itis.model.Review;

public interface ReviewRepository {
    public void save(Review review);

    public void updateComment(String newComment);
    public void updateRate(int newRate);

    public boolean deleteById(Long id);
}
