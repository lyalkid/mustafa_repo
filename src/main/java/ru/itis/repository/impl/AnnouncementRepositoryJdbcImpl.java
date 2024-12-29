package ru.itis.repository.impl;

import ru.itis.model.Announcement;
import ru.itis.repository.AnnouncementRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnnouncementRepositoryJdbcImpl implements AnnouncementRepository {

    private static final String SQL_INSERT_INTO_ANNOUNCEMENT = "insert into announcement(id, name, description) values (?,?,?)";
    private static final String SQL_UPDATE_ANNOUNCEMENT = "update announcement set name = ?, description = ? where id = ?";
    private static final String SQL_UPDATE_TITLE_ANNOUNCEMENT = "update announcement set title = ? where id = ?";
    private static final String SQL_UPDATE_DESCRIPTION_ANNOUNCEMENT = "update announcement set description = ? where id = ?";
    private static final String SQL_DELETE_ANNOUNCEMENT_BY_ID = "delete from announcement where id = ?";
    private static final String SQL_SELECT_ANNOUNCEMENT_BY_ID = "select * from announcement where id = ?";
    private static final String SQL_SELECT_ALL_ANNOUNCEMENTS = "select * from announcement";
    private static final String SQL_SELECT_ANNOUCEMENT_BY_TITLE ="select * from announcement where title = ?";
    private DataSource dataSource;

    public AnnouncementRepositoryJdbcImpl(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void save(Announcement announcement) throws SQLException {
        String sql = SQL_INSERT_INTO_ANNOUNCEMENT;
        try{
            Connection connection = dataSource.getConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setLong(1, announcement.getId());
                preparedStatement.setString(2,announcement.getTitle());
                preparedStatement.setString(3, announcement.getDescription());
                preparedStatement.executeUpdate();
            }
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    @Override
    public void update(Announcement announcement) throws SQLException {
        String sql = SQL_UPDATE_ANNOUNCEMENT;
        try{
            Connection connection = dataSource.getConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setLong(1, announcement.getId());
                preparedStatement.setString(2, announcement.getTitle());
                preparedStatement.setString(3, announcement.getDescription());
                preparedStatement.executeUpdate();
            }
        }catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void updateTitle(Announcement announcement, String new_title) throws SQLException {
        String sql = SQL_UPDATE_TITLE_ANNOUNCEMENT;
        try{
            Connection connection = dataSource.getConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setString(1, new_title);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e){
            throw new RuntimeException();
        }
    }

    @Override
    public void updateDescription(Announcement announcement, String new_description) throws SQLException {
        String sql = SQL_UPDATE_DESCRIPTION_ANNOUNCEMENT;
        try{
            Connection connection = dataSource.getConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setString(1, new_description);
                preparedStatement.executeUpdate();
            }
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }


    @Override
    public boolean deleteByAnnouncementId(Long id) throws SQLException {
        String sql = SQL_DELETE_ANNOUNCEMENT_BY_ID;
        try{
            Connection connection = dataSource.getConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
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
        try{
            Connection connection = dataSource.getConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ANNOUNCEMENT_BY_ID)){
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()){
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
        try{
            Connection connection = dataSource.getConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_ANNOUNCEMENTS)){
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    Announcement announcement = new Announcement();
                    announcement.setId(resultSet.getLong( "id"));
                    announcement.setTitle(resultSet.getString("title"));
                    announcement.setDescription(resultSet.getString("description"));
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
        try{
            Connection connection = dataSource.getConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ANNOUCEMENT_BY_TITLE)){
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    Announcement announcement = new Announcement();
                    announcement.setTitle(resultSet.getString("title"));
                    announcements.add(announcement);
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return announcements;
    }
}