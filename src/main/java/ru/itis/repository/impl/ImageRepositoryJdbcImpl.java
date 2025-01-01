package ru.itis.repository.impl;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.model.Image;
import ru.itis.repository.ImageRepository;
import ru.itis.utils.DBProperty;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ImageRepositoryJdbcImpl implements ImageRepository {

    private static final String FIND_ALL_BY_ANNOUNCEMENT_ID_SQL =
            "SELECT * FROM images WHERE announcement_id = ?";
    private static final String INSERT_IMAGE_SQL =
            "INSERT INTO images (announcement_id, file_name, file_path, file_type) VALUES (?, ?, ?, ?)";
    private static final String DELETE_BY_ANNOUNCEMENT_ID_SQL =
            "DELETE FROM images WHERE announcement_id = ?";

    private DataSource dataSource;

    public ImageRepositoryJdbcImpl(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public ImageRepositoryJdbcImpl(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DBProperty.DB_DRIVER);
        dataSource.setPassword(DBProperty.DB_PASSWORD);
        dataSource.setUrl(DBProperty.DB_URL);
        dataSource.setUsername(DBProperty.DB_USERNAME);
        this.dataSource = dataSource;
    }
    
    
    @Override
    public Image findAllByAnnouncementId(Long announcementId) {
        Image images = new Image();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_ANNOUNCEMENT_ID_SQL)) {

            preparedStatement.setLong(1, announcementId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Image image = new Image();
                    image.setFileName(resultSet.getString("file_name"));
                    image.setFilePath(resultSet.getString("file_path"));
                    image.setType(resultSet.getString("file_type"));
                    images = image;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return images;
    }

    @Override
    public void save(Image image) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_IMAGE_SQL)) {
            preparedStatement.setString(1, image.getFileName());
            preparedStatement.setString(2, image.getFilePath());
            preparedStatement.setString(3, image.getType());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteByAnnouncementId(Long announcementId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ANNOUNCEMENT_ID_SQL)) {

            preparedStatement.setLong(1, announcementId);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
