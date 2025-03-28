package ru.itis.service;

import ru.itis.model.Favorites;
import ru.itis.repository.FavoritesRepository;
import ru.itis.repository.impl.AnnouncementRepositoryJdbcImpl;
import ru.itis.repository.impl.FavoritesRepositoryJdbcImpl;

import java.sql.SQLException;
import java.util.List;

public class FavoriteService {
    private FavoritesRepository favoritesRepository;

    public FavoriteService() {
        this.favoritesRepository = new FavoritesRepositoryJdbcImpl();
    }


    public void addToFavorites(Long userId, Long announcementId) {
        favoritesRepository.save(userId, announcementId);
    }

    public void removeFromFavorites(Long userId, Long announcementId) {
        favoritesRepository.delete(userId, announcementId);
    }

    public boolean isFavorite(Long userId, Long announcementId) {
        var list = favoritesRepository.findAllByUser(userId);
        boolean favorite = false;
        for (var item : list) {
            if (item.getAnnouncementId().equals(announcementId)) {
                favorite = true;
                break;
            }
        }
        return favorite;
    }

    public List<Favorites> getFavorites(Long userId) {
        return favoritesRepository.findAllByUser(userId);
    }
}
