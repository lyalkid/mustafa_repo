package ru.itis.repository.impl;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.model.User;
import ru.itis.repository.UserRepository;
import ru.itis.util.ConnectionManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryJdbcImpl implements UserRepository {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "postgres";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_DRIVER = "org.postgresql.Driver";


    private static final String SQL_INSERT_INTO_USERS = "insert into users( user_name, email, password) values (?, ?,?)";
    private static final String SQL_UPDATE_USERS = "update users set user_name = ?, email = ?, password = ? where user_id = ?";
    private static final String SQL_DELETE_USER_BY_ID = "delete from users where id = ?";
    private static final String SQL_UPDATE_PASSWORD_USERS = "update users set password = ? where id = ?";
    private static final String SQL_SELECT_ALL_USERS = "select * from users";

    private static final String SQL_SELECT_USER_BY_ID = "select * from users where id = ?";
    private static final String SQL_SELECT_USER_BY_USERNAME = "select * from users where user_name = ?";
//    private static final ConnectionManager connectionManager = new ConnectionManager();

    private DataSource dataSource;

    public UserRepositoryJdbcImpl() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setUrl(DB_URL);
        dataSource.setUsername(DB_USERNAME);
        this.dataSource = dataSource;
    }

    public UserRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(User user) throws SQLException {
        String sql = SQL_INSERT_INTO_USERS;
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
               // preparedStatement.setLong(1, user.getId() != null ?  user.getId() : count()+1);
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getEmail());
                preparedStatement.setString(3, user.getPassword());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void update(User user) throws SQLException {
        String sql = SQL_UPDATE_USERS;
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getEmail());
                preparedStatement.setString(3, user.getPassword());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void updatePassword(User user, String new_password) throws SQLException {
        String sql = SQL_UPDATE_PASSWORD_USERS;
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {

                preparedStatement.setString(1, new_password);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean deleteUserById(Long userId) throws SQLException {
        String sql = SQL_DELETE_USER_BY_ID;
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
                preparedStatement.setLong(1, userId);
                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_USERS);) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setUsername(resultSet.getString("user_name"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("password"));
                    users.add(user);
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }


    // TODO: домашка
    @Override
    public User findById(Long userId) {
        User user = null;
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_ID);) {
                preparedStatement.setLong(1, userId);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setUsername(resultSet.getString("user_name"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("password"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    // TODO: домашка
    @Override
    public User findByEmail(String email) {
        return null;
    }

    // TODO: домашка
    @Override
    public User findByUsername(String username) {
        User user = null;
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_USERNAME)) {
                preparedStatement.setString(1, username);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setUsername(resultSet.getString("user_name"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("password"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }



}

// TODO: методы исправить , announcment repository, services,  servlets , jsp, filter, auth, roles, dto, dao
//