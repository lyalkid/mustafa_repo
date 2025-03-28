package ru.itis.repository.impl;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.model.Announcement;
import ru.itis.model.Currency;
import ru.itis.repository.AnnouncementRepository;
import ru.itis.utils.Property;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnnouncementRepositoryJdbcImpl implements AnnouncementRepository {



    // insert

    private static final String SQL_INSERT_INTO_ANNOUNCEMENT = "insert into announcement( title, description, user_id) values (?,?,?)";
    private static final String SQL_INSERT_INTO_FULL = "insert into announcement( title, description, price,  currency_id, user_id) values (?,?, ?, ?, ?)";

    // update
    private static final String SQL_UPDATE_ANNOUNCEMENT = "update announcement set title = ?, description = ?, user_id = ? ";
    private static final String SQL_UPDATE_TITLE_ANNOUNCEMENT = "update announcement set title = ? where id = ?";
    private static final String SQL_UPDATE_DESCRIPTION_ANNOUNCEMENT = "update announcement set description = ? where id = ?";


    // delete
    private static final String SQL_DELETE_ANNOUNCEMENT_BY_ID = "delete from announcement where id = ?";

    // select
    private static final String SQL_SELECT_ANNOUNCEMENT_BY_ID = "select * from announcement where id = ?";
    private static final String SQL_SELECT_ALL_ANNOUNCEMENTS = "select * from announcement";

    private static final String SQL_SELECT_ANNOUNCEMENTS_BY_USER_ID = "select * from announcement where user_id = ?";

    private DataSource dataSource;

    public AnnouncementRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public AnnouncementRepositoryJdbcImpl() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Property.DB_DRIVER);
        dataSource.setPassword(Property.DB_PASSWORD);
        dataSource.setUrl(Property.DB_URL);
        dataSource.setUsername(Property.DB_USERNAME);
        this.dataSource = dataSource;
    }

    @Override
    public Long save(Announcement announcement)  {
        String sql = SQL_INSERT_INTO_FULL;
        Long generatedId = -1l;
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, announcement.getTitle());
                preparedStatement.setString(2, announcement.getDescription());
                preparedStatement.setLong(3, announcement.getPrice());
                preparedStatement.setLong(4, announcement.currencyHelper(announcement.getCurrency()));
                preparedStatement.setLong(5, announcement.getUserId());
//                preparedStatement.setLong(6, announcement.getImageId());
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    // Получаем сгенерированный ключ
                    try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            generatedId = generatedKeys.getLong(1);  // Получаем ID объявления
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return generatedId;


    }
    @Override
    public void save(String title, String description, Long user_id)  {
        String sql = SQL_INSERT_INTO_ANNOUNCEMENT;
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setString(1, title);
                preparedStatement.setString(2, description);
                preparedStatement.setLong(3, user_id);
//                preparedStatement.setLong(4, announcement.getImageId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void update(Announcement announcement)  {
        String sql = SQL_UPDATE_ANNOUNCEMENT;
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, announcement.getId());
                preparedStatement.setString(2, announcement.getTitle());
                preparedStatement.setString(3, announcement.getDescription());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void updateTitle(Long announcementId, String new_title)  {
        String sql = SQL_UPDATE_TITLE_ANNOUNCEMENT;
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, new_title);
                preparedStatement.setLong(2, announcementId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }



    @Override
    public void updateDescription(Long announcementId, String new_description)  {
        String sql = SQL_UPDATE_DESCRIPTION_ANNOUNCEMENT;
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, new_description);
                preparedStatement.setLong(2, announcementId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }


    @Override
    public boolean deleteByAnnouncementId(Long id)  {
        String sql = SQL_DELETE_ANNOUNCEMENT_BY_ID;
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, id);
                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Announcement findById(Long id) {
        Announcement announcement = null;
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ANNOUNCEMENT_BY_ID)) {
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    announcement = new Announcement();
                    announcement.setId(resultSet.getLong("id"));
                    announcement.setTitle(resultSet.getString("title"));
                    announcement.setDescription(resultSet.getString("description"));
                    announcement.setPrice(resultSet.getLong("price"));
                    announcement.setCurrency(announcement.currencyHelper(resultSet.getInt("currency_id")));
                    announcement.setUserId(resultSet.getLong("user_id"));
//                    announcement.setImageId(resultSet.getLong("image_id"));
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return announcement;
    }

    @Override
    public List<Announcement> findAll() {
        List<Announcement> announcements = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_ANNOUNCEMENTS)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Announcement announcement = new Announcement();
                    announcement.setId(resultSet.getLong("id"));
                    announcement.setTitle(resultSet.getString("title"));
                    announcement.setDescription(resultSet.getString("description"));
                    announcement.setPrice(resultSet.getLong("price"));
                    announcement.setCurrency(announcement.currencyHelper(resultSet.getInt("currency_id")));
                    announcement.setUserId(resultSet.getLong("user_id"));
//                    announcement.setImageId(resultSet.getLong("image_id"));

                    announcements.add(announcement);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return announcements;
    }

    @Override
    public List<Announcement> findByUserId(Long userId) {
        List<Announcement> announcements = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ANNOUNCEMENTS_BY_USER_ID)) {
                preparedStatement.setLong(1, userId);

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Announcement announcement = new Announcement();
                    announcement.setId(resultSet.getLong("id"));
                    announcement.setTitle(resultSet.getString("title"));
                    announcement.setDescription(resultSet.getString("description"));
                    announcement.setPrice(resultSet.getLong("price"));
                    announcement.setCurrency(announcement.currencyHelper(resultSet.getInt("currency_id")));
                    announcement.setUserId(resultSet.getLong("user_id"));
                    announcements.add(announcement);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return announcements;
    }

}