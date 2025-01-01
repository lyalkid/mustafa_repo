package ru.itis.repository;

import ru.itis.model.Review;

import java.sql.SQLException;

public interface ReviewRepository {
    public void save(Review review) throws SQLException;
    public void updateComment(String newComment);
    public void updateRate(int newRate);
    public boolean deleteById(Long id) throws SQLException;
}
