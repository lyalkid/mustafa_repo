package ru.itis.repository.impl;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.model.Announcement;
import ru.itis.repository.AnnouncementRepository;
import ru.itis.utils.DBProperty;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnnouncementRepositoryJdbcImpl implements AnnouncementRepository {

    private static final String SQL_INSERT_INTO_ANNOUNCEMENT = "insert into announcement( title, description, user_id) values (?,?,?)";
    private static final String SQL_INSERT_INTO_FULL = "insert into announcement( title, description, user_id, image_id) values (?,?,?, ?)";

    private static final String SQL_UPDATE_ANNOUNCEMENT = "update announcement set title = ?, dedescription = ?, user_id = ?, image_id = ? ";
    private static final String SQL_UPDATE_TITLE_ANNOUNCEMENT = "update announcement set title = ? where id = ?";
    private static final String SQL_UPDATE_DESCRIPTION_ANNOUNCEMENT = "update announcement set description = ? where id = ?";

    private static final String SQL_UPDATE_USER_ID = "update announcement set user_id = ? where id = ?";
    private static final String SQL_UPDATE_IMAGE_ID = "update announcement set image_id = ? where id = ?";

    private static final String SQL_DELETE_ANNOUNCEMENT_BY_ID = "delete from announcement where id = ?";
    private static final String SQL_SELECT_ANNOUNCEMENT_BY_ID = "select * from announcement where id = ?";
    private static final String SQL_SELECT_ALL_ANNOUNCEMENTS = "select * from announcement";
    private static final String SQL_SELECT_ANNOUNCEMENT_BY_TITLE = "select * from announcement where title = ?";

    private static final String SQL_SELECT_ANNOUNCEMENTS_BY_USER_ID = "select * from announcement where user_id = ?";
    private static final String SQL_SELECT_ANNOUNCEMENTS_BY_IMAGE_ID = "select * from announcement where image_id = ?";

    private DataSource dataSource;

    public AnnouncementRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public AnnouncementRepositoryJdbcImpl() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DBProperty.DB_DRIVER);
        dataSource.setPassword(DBProperty.DB_PASSWORD);
        dataSource.setUrl(DBProperty.DB_URL);
        dataSource.setUsername(DBProperty.DB_USERNAME);
        this.dataSource = dataSource;
    }

    @Override
    public void save(Announcement announcement)  {
        String sql = SQL_INSERT_INTO_FULL;
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setString(1, announcement.getTitle());
                preparedStatement.setString(2, announcement.getDescription());
                preparedStatement.setLong(3, announcement.getUserId());
                preparedStatement.setLong(4, announcement.getImageId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
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
    public void updateTitle(Announcement announcement, String new_title)  {
        String sql = SQL_UPDATE_TITLE_ANNOUNCEMENT;
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, new_title);
                preparedStatement.setLong(2, announcement.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void updateUserId(Announcement announcement, Long userId) {
        String sql = SQL_UPDATE_USER_ID;
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, userId);
                preparedStatement.setLong(2, announcement.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        
    }

    @Override
    public void updateImageId(Announcement announcement, Long imageId) {
        String sql = SQL_UPDATE_IMAGE_ID;
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, imageId);
                preparedStatement.setLong(2, announcement.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void updateDescription(Announcement announcement, String new_description)  {
        String sql = SQL_UPDATE_DESCRIPTION_ANNOUNCEMENT;
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, new_description);
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
                    announcement.setUserId(resultSet.getLong("user_id"));
                    announcement.setImageId(resultSet.getLong("image_id"));

                    announcements.add(announcement);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return announcements;
    }

    @Override
    public List<Announcement> findByTitle(String title) {
        List<Announcement> announcements = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ANNOUNCEMENT_BY_TITLE)) {
                preparedStatement.setString(1, title);

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Announcement announcement = new Announcement();
                    announcement.setId(resultSet.getLong("id"));
                    announcement.setTitle(resultSet.getString("title"));
                    announcement.setDescription(resultSet.getString("description"));
                    announcement.setUserId(resultSet.getLong("user_id"));
                    announcement.setImageId(resultSet.getLong("image_id"));
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
                    announcement.setUserId(resultSet.getLong("user_id"));
                    announcement.setImageId(resultSet.getLong("image_id"));
                    announcements.add(announcement);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return announcements;
    }

    @Override
    public List<Announcement> findByImageId(Long imageId) {
        List<Announcement> announcements = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ANNOUNCEMENTS_BY_IMAGE_ID)) {
                preparedStatement.setLong(1, imageId);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Announcement announcement = new Announcement();
                    announcement.setId(resultSet.getLong("id"));
                    announcement.setTitle(resultSet.getString("title"));
                    announcement.setDescription(resultSet.getString("description"));
                    announcement.setUserId(resultSet.getLong("user_id"));
                    announcement.setImageId(resultSet.getLong("image_id"));
                    announcements.add(announcement);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return announcements;
    }
}