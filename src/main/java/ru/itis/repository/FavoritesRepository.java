package ru.itis.repository;

import ru.itis.model.Favorites;

import java.sql.SQLException;
import java.util.List;

public interface FavoritesRepository {
    void save(Long userId, Long announcementId) ;
    boolean deleteById(Long id) ;
    List<Favorites> findAllByUser(Long userId);
    void delete(Long userId, Long announcementId);
}
