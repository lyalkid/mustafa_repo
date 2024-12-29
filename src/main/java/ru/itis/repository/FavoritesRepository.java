package ru.itis.repository;

import ru.itis.model.Favorites;

import java.util.List;

public interface FavoritesRepository {
    public void save(Favorites favorites);
    public boolean deleteById(Long id);

    public List<Favorites> findAllByUser(Long userId);

}
