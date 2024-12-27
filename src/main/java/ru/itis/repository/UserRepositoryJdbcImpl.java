package ru.itis.repository;

import ru.itis.model.User;
import ru.itis.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryJdbcImpl implements UserRepository {

    private static final String SQL_INSERT_INTO_USERS = "insert into users(id, user_name, email, password) values (?, ?,?,?)";
    private static final String SQL_UPDATE_USERS = "update users set user_name = ?, email = ?, password = ? where user_id = ?";
    private static final String SQL_DELETE_USERS = "delete from users where user_id = ?";
    private static final String SQL_UPDATEPASSWORD_USERS = "update users set password = ? where user_id = ?";
    private static final String SQL_SELECT_ALL_USERS = "select * from users";
    private static final ConnectionManager connectionManager = new ConnectionManager();




    @Override
    public void save(User user) throws SQLException {
        String sql = SQL_INSERT_INTO_USERS;
        Connection connection = connectionManager.get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.executeUpdate();
        }
        connectionManager.putConnection(connection);
    }

    @Override
    public void update(User user) throws SQLException {
        String sql = SQL_UPDATE_USERS;
        Connection connection = connectionManager.get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.executeUpdate();
        }
        connectionManager.putConnection(connection);
    }

    @Override
    public void updatePassword(User user, String new_password) throws SQLException {
        String sql = SQL_UPDATEPASSWORD_USERS;
        Connection connection = connectionManager.get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {

            preparedStatement.setString(1, new_password);
            preparedStatement.executeUpdate();
        }
        connectionManager.putConnection(connection);
    }

    @Override
    public void deleteUser(Long userId) throws SQLException {
        String sql = SQL_DELETE_USERS;
        Connection connection = connectionManager.get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setLong(1, userId);
            preparedStatement.executeUpdate();
        }
        connectionManager.putConnection(connection);
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        Connection connection = connectionManager.get();
        try {
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

        connectionManager.putConnection(connection);
        return users;
    }

    @Override
    public User findById(int userId) throws SQLException {
        return null;
    }

    @Override
    public User findByEmail(String email) throws SQLException {
        return null;
    }

    @Override
    public User findByUsername(String username) throws SQLException {
        return null;
    }
}
