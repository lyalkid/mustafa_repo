package ru.itis.repository;

import ru.itis.model.Review;

public interface ReviewRepository {
    public void save(Review review);

    public boolean updateComment(String newComment);
    public boolean updateRate(int newRate);

    public boolean deleteById(Long id);
}
