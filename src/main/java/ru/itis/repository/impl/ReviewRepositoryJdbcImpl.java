package ru.itis.repository.impl;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.model.Favorites;
import ru.itis.model.Review;
import ru.itis.model.User;
import ru.itis.repository.ReviewRepository;
import ru.itis.utils.Property;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepositoryJdbcImpl implements ReviewRepository {

    private static final String SQL_INSERT_INTO_REVIEW = "insert into reviews(rate, comment, user_id, announcement_id) values(?,?,?,?)";
    private static final String SQL_UPDATE_COMMENT = "update reviews set comment = ? where id = ?";
    private static final String SQL_UPDATE_RATE = "update reviews set rate = ? where id ?";
    private static final String SQL_DELETE_REVIEW_BY_ID = "delete from reviews where id = ?";

    public DataSource dataSource;

    public ReviewRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public ReviewRepositoryJdbcImpl() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Property.DB_DRIVER);
        dataSource.setPassword(Property.DB_PASSWORD);
        dataSource.setUrl(Property.DB_URL);
        dataSource.setUsername(Property.DB_USERNAME);
        this.dataSource = dataSource;
    }

    @Override
    public void save(Review review) {
        String sql = SQL_INSERT_INTO_REVIEW;
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, review.getRate());
                preparedStatement.setString(2, review.getComment());
                preparedStatement.setLong(3, review.getUserId());
                preparedStatement.setLong(4, review.getAnnouncementId());
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void updateComment(String newComment) {
        String sql = SQL_UPDATE_COMMENT;
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, newComment);
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateRate(int newRate) {
        String sql = SQL_UPDATE_RATE;
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, newRate);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(Long id) throws SQLException {
        String sql = SQL_DELETE_REVIEW_BY_ID;
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
    public Review findByUserAnnouncement(Long userId, Long announcementId) {
        Review review = null;
        String sql = "select * from reviews where user_id = ? and announcement_id = ?";
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, userId);
                preparedStatement.setLong(2, announcementId);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    review = new Review();
                    review.setId(resultSet.getLong(1));
                    review.setRate(resultSet.getInt(2));
                    review.setComment(resultSet.getString(3));
                    review.setUserId(resultSet.getLong(4));
                    review.setAnnouncementId(resultSet.getLong(5));

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return review;
    }


    @Override
    public List<Review> findAllByUser(Long userId){
        List<Review> reviewList = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement("select * from reviews where user_id = ?")) {
                preparedStatement.setLong(1, userId);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Review review = new Review();
                    review.setId(resultSet.getLong("id"));
                    review.setRate(resultSet.getInt("rate"));
                    review.setComment(resultSet.getString("comment"));
                    review.setUserId(resultSet.getLong("user_id"));
                    review.setAnnouncementId(resultSet.getLong("announcement_id"));
                    reviewList.add(review);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reviewList;
    }


    @Override
    public List<Review> findByAnnouncement(Long announcementId){
        List<Review> reviewList = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement("select * from reviews where announcement_id = ?")) {
                preparedStatement.setLong(1, announcementId);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Review review = new Review();
                    review.setId(resultSet.getLong("id"));
                    review.setRate(resultSet.getInt("rate"));
                    review.setComment(resultSet.getString("comment"));
                    review.setUserId(resultSet.getLong("user_id"));
                    review.setAnnouncementId(resultSet.getLong("announcement_id"));

                    reviewList.add(review);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reviewList;
    }

}
