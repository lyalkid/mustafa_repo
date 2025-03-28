package ru.itis.repository.impl;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.model.Favorites;
import ru.itis.repository.FavoritesRepository;
import ru.itis.utils.Property;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FavoritesRepositoryJdbcImpl implements FavoritesRepository {


    private static final String SQL_INSERT_INTO_FAVORITES = "insert into favorites (user_id, announcement_id) values (?,?)";
    private static final String SQL_DELETE_FAVORITES_FROM_ID = "delete from favorites where id = ?";
    private static final String SQL_SELECT_ALL_BY_USER = "select * from favorites where user_id = ?";

    public DataSource dataSource;

    public FavoritesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public FavoritesRepositoryJdbcImpl() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Property.DB_DRIVER);
        dataSource.setPassword(Property.DB_PASSWORD);
        dataSource.setUrl(Property.DB_URL);
        dataSource.setUsername(Property.DB_USERNAME);
        this.dataSource = dataSource;
    }

    @Override
    public void save(Long userId, Long announcementId) {
        String sql = SQL_INSERT_INTO_FAVORITES;
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, userId);
                preparedStatement.setLong(2, announcementId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = SQL_DELETE_FAVORITES_FROM_ID;
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, id);
                int affectedRows = preparedStatement.executeUpdate();
                return affectedRows > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Favorites> findAllByUser(Long userId) {
        List<Favorites> favoritesList = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_BY_USER)) {
                preparedStatement.setLong(1, userId);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Favorites favorites = new Favorites();
                    favorites.setId(resultSet.getLong("id"));
                    favorites.setUserId(resultSet.getLong("user_id"));
                    favorites.setAnnouncementId(resultSet.getLong("announcement_id"));
                    favoritesList.add(favorites);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return favoritesList;
    }

    public void delete(Long userId, Long announcementId) {
        String sql = "delete from favorites where user_id = ? and announcement_id = ?";
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, userId);
                preparedStatement.setLong(2, announcementId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}