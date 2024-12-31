package ru.itis.repository.impl;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.model.Review;
import ru.itis.repository.ReviewRepository;
import ru.itis.utils.DBProperty;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReviewRepositoryJdbcImpl implements ReviewRepository {

    private static final String SQL_INSERT_INTO_REVIEW = "insert into review(rate, comment, user_id, announcement_id) values(?,?,?,?)";
    private static final String SQL_UPDATE_COMMENT = "update review set comment = ? where id = ?";
    private static final String SQL_UPDATE_RATE = "update review set rate = ? where id ?";
    private static final String SQL_DELETE_REVIEW_BY_ID = "delete from review where id = ?";

    public DataSource dataSource;
    public ReviewRepositoryJdbcImpl(DataSource dataSource){
        this.dataSource = dataSource;
    }
    public ReviewRepositoryJdbcImpl(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DBProperty.DB_DRIVER);
        dataSource.setPassword(DBProperty.DB_PASSWORD);
        dataSource.setUrl(DBProperty.DB_URL);
        dataSource.setUsername(DBProperty.DB_USERNAME);
        this.dataSource = dataSource;
    }
    @Override
    public void save(Review review) {
        String sql = SQL_INSERT_INTO_REVIEW;
        try{
            Connection connection = dataSource.getConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setInt(1, review.getRate());
                preparedStatement.setString(2, review.getComment());
                preparedStatement.setLong(3, review.getUser().getId());
                preparedStatement.setLong(4, review.getAnnouncement().getId());
                preparedStatement.executeUpdate();
            }

        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    @Override
    public void updateComment(String newComment) {
        String sql = SQL_UPDATE_COMMENT;
        try{
            Connection connection = dataSource.getConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
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
        try{
            Connection connection = dataSource.getConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setInt(1, newRate);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = SQL_DELETE_REVIEW_BY_ID;
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, id);
                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    }
