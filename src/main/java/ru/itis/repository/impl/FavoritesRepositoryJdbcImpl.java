package ru.itis.repository.impl;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.model.Announcement;
import ru.itis.model.Favorites;
import ru.itis.repository.FavoritesRepository;
import ru.itis.service.AnnouncementService;
import ru.itis.service.UserService;
import ru.itis.utils.DBProperty;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FavoritesRepositoryJdbcImpl implements FavoritesRepository {
    private UserService userService = new UserService();
    private AnnouncementService announcementService = new AnnouncementService();
    private static final String SQL_INSERT_INTO_FAVORITES = "insert into favorites (user_id, announcement_id) values (?,?)";
    private static final String SQL_DELETE_FAVORITES_FROM_ID = "delete from favorites where id = ?";
    private static final String SQL_SELECT_ALL_BY_USER = "select * from favorites where = ?";

    public DataSource dataSource;
    public FavoritesRepositoryJdbcImpl(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public FavoritesRepositoryJdbcImpl(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DBProperty.DB_DRIVER);
        dataSource.setPassword(DBProperty.DB_PASSWORD);
        dataSource.setUrl(DBProperty.DB_URL);
        dataSource.setUsername(DBProperty.DB_USERNAME);
        this.dataSource = dataSource;
    }
    @Override
    public void save(Favorites favorites) {
        String sql = SQL_INSERT_INTO_FAVORITES;
        try {
        Connection connection = dataSource.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, favorites.getUser().getId());
            preparedStatement.setLong(2, favorites.getAnnouncement().getId());
            preparedStatement.executeUpdate();
        }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = SQL_DELETE_FAVORITES_FROM_ID;
        try{
            Connection connection = dataSource.getConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setLong(1, id);
                int affectedRows = preparedStatement.executeUpdate();
                return affectedRows > 0;
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Favorites> findAllByUser(Long userId) {
        List<Favorites> favoritesList = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_BY_USER)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Favorites favorites = new Favorites();
                    favorites.setId(resultSet.getLong("id"));
                    favorites.setUser(userService.getUserById(resultSet.getLong("user_id")));
                    favorites.setAnnouncement(announcementService.getAnnouncementById(resultSet.getLong("announcement_id")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return favoritesList;
    }
}
