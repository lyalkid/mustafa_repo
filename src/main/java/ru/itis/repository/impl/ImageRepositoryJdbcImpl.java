package ru.itis.repository.impl;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.model.Announcement;
import ru.itis.model.Image;
import ru.itis.repository.ImageRepository;
import ru.itis.utils.Property;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.UUID;

public class ImageRepositoryJdbcImpl implements ImageRepository {


    private static final String INSERT_IMAGE = "insert into images(storage_file_name, original_file_name, file_path, file_type, size, announcement_id) values(?,?,?,?,?,?)";
    private static final String SQL_SELECT_IMAGE = "select * from images where storage_file_name=? and original_file_name=? and file_path=? and file_type=? and size = ? and announcement_id = ?";

    private static final String SQL_SELECT_BY_ANNOUNCEMENT = "select * from images where id = ?";
    private DataSource dataSource;

    public ImageRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public ImageRepositoryJdbcImpl() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Property.DB_DRIVER);
        dataSource.setPassword(Property.DB_PASSWORD);
        dataSource.setUrl(Property.DB_URL);
        dataSource.setUsername(Property.DB_USERNAME);
        this.dataSource = dataSource;
    }

    @Override
    public Image findByImage(Image image2) {
        Image image = null;
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_IMAGE)) {
                preparedStatement.setString(1, image2.getStorageFileName());
                preparedStatement.setString(2, image2.getOriginalFileName());
                preparedStatement.setString(3, image2.getFilePath());
                preparedStatement.setString(4, image2.getFileType());
                preparedStatement.setLong(5, image2.getSize());
                preparedStatement.setLong(6, image2.getAnnouncementId());
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    image = Image.builder().id(resultSet.getLong("id")).storageFileName(resultSet.getString("storage_file_name")).originalFileName(resultSet.getString("original_file_name")).filePath(resultSet.getString("file_path")).fileType(resultSet.getString("file_type")).size(resultSet.getLong("size")).announcementId(resultSet.getLong("announcement_id")).build();

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return image;
    }

    @Override
    public Image findById(Long id) {
        Image image = null;
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ANNOUNCEMENT)) {
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    image = Image.builder().id(resultSet.getLong("id")).storageFileName(resultSet.getString("storage_file_name")).originalFileName(resultSet.getString("original_file_name")).filePath(resultSet.getString("file_path")).fileType(resultSet.getString("file_type")).size(resultSet.getLong("size")).announcementId(resultSet.getLong("announcement_id")).build();

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return image;
    }

    @Override
    public Image findByImagePath(String imagePath) {
        Image image = null;
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement("select * from images where file_path = ?")) {
                preparedStatement.setString(1, imagePath);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    image = Image.builder().id(resultSet.getLong("id")).
                            storageFileName(resultSet.getString("storage_file_name")).
                            originalFileName(resultSet.getString("original_file_name")).
                            filePath(resultSet.getString("file_path")).
                            fileType(resultSet.getString("file_type")).
                            size(resultSet.getLong("size")).
                            announcementId(resultSet.getLong("announcement_id"))
                            .build();

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return image;
    }

    @Override
    public List<Image> findAllByAnnouncementId(Long announcementId) {
        List<Image> images = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement("select * from images where announcement_id = ?")) {
                preparedStatement.setLong(1, announcementId);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Image image = Image.builder().id(resultSet.getLong("id")).storageFileName(resultSet.getString("storage_file_name")).originalFileName(resultSet.getString("original_file_name")).filePath(resultSet.getString("file_path")).fileType(resultSet.getString("file_type")).size(resultSet.getLong("size")).announcementId(resultSet.getLong("announcement_id")).build();
                    images.add(image);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return images;
    }

    @Override
    public void save(Image image) throws SQLException {
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_IMAGE)) {
                preparedStatement.setString(1, image.getStorageFileName());
                preparedStatement.setString(2, image.getOriginalFileName());
                preparedStatement.setString(3, image.getFilePath());
                preparedStatement.setString(4, image.getFileType());
                preparedStatement.setLong(5, image.getSize());
                preparedStatement.setLong(6, image.getAnnouncementId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public void deleteByAnnouncementId(Long announcementId) {

    }

    @Override
    public Image saveImageToStorage(InputStream file, String originalName, String contentType, Long size, Long announcementId) {

        Image image = Image.builder().storageFileName(UUID.randomUUID().toString()).originalFileName(originalName).fileType(contentType).size(size).announcementId(announcementId).build();

        image.setFilePath( image.getStorageFileName() + "." + image.getFileType().split("/")[1]);
        String pth = Property.IMAGES_FOLDER + image.getFilePath();
        try {
            Files.copy(file, Path.of(pth));
            save(image);

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            return image;
        }
    }
}
