package ru.itis.repository;

import ru.itis.model.Favorites;

import java.sql.SQLException;
import java.util.List;

public interface FavoritesRepository {
    public void save(Favorites favorites) throws SQLException;
    public boolean deleteById(Long id) throws SQLException;
    List<Favorites> findAllByUser(Long userId);

}
