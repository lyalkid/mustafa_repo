package ru.itis.repository.impl;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.model.Role;
import ru.itis.model.User;
import ru.itis.repository.UserRepository;
import ru.itis.utils.Property;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryJdbcImpl implements UserRepository {


    private static final String SQL_INSERT_INTO_USERS = "insert into users( user_name, email, password, first_name, second_name, birth_date, role_id) values (?, ?,?,?, ?,? , ?)";
    private static final String SQL_DELETE_USER_BY_ID = "delete from users where id = ?";

    private static final String SQL_SELECT_ALL_USERS = "select * from users";
    private static final String SQL_SELECT_USER_BY_ID = "select * from users where id = ?";
    private static final String SQL_SELECT_USER_BY_EMAIL = "select * from users where email = ?";

    private static final String SQL_SELECT_USER_BY_USERNAME = "select * from users where user_name = ?";
    private static final String SQL_UPDATE_PASSWORD_USERS = "update users set password = ? where id = ?";
    private static final String SQL_UPDATE_FIRST_NAME = "update users set first_name = ? where id = ?";
    private static final String SQL_UPDATE_SECOND_NAME = "update users set second_name = ? where id = ?";
    private static final String SQL_UPDATE_BIRTH_DATE = "update users set birth_date = ? where id = ?";
    private static final String SQL_UPDATE_EMAIL = "update users set email = ? where id = ?";
    private static final String SQL_UPDATE_USERNAME = "update users set user_name = ? where id = ?";

    private static final String GET_SQL_BY_USERNAME_AND_PASSWORD = "select * from users where user_name = ? and password = ?";


    private DataSource dataSource;


    public UserRepositoryJdbcImpl() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Property.DB_DRIVER);
        dataSource.setPassword(Property.DB_PASSWORD);
        dataSource.setUrl(Property.DB_URL);
        dataSource.setUsername(Property.DB_USERNAME);
        this.dataSource = dataSource;
    }

    public UserRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(User user) {
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_INTO_USERS)) {
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getEmail());
                preparedStatement.setString(3, user.getPassword());
                preparedStatement.setString(4, user.getFirstName());
                preparedStatement.setString(5, user.getSecondName());
                preparedStatement.setDate(6, user.getBirthDate());
                preparedStatement.setInt(7, (user.getRole().equals(Role.USER)) ? 1 : 2);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean updateUsername(Long userId, String username) {
        boolean res = false;
        String sql = SQL_UPDATE_USERNAME;
        User user1 = findByUsername(username);
        if (user1 == null) {
            res = true;

            try {
                Connection connection = dataSource.getConnection();
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
                    preparedStatement.setString(1, username);
                    preparedStatement.setLong(2, userId);

                    preparedStatement.executeUpdate();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        return res;
    }

    @Override
    public void updatePassword(Long userId, String new_password) {
        String sql = SQL_UPDATE_PASSWORD_USERS;
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {

                preparedStatement.setString(1, new_password);
                preparedStatement.setLong(2, userId);
                preparedStatement.executeUpdate();

            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void updateBirthDate(Long userId, Date b_date) {
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_BIRTH_DATE)) {
                preparedStatement.setDate(1, b_date);
                preparedStatement.setLong(2, userId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public boolean updateEmail(Long userId, String email) {
        boolean res = false;
        User find = findByEmail(email);
        if (find == null) {
            res = true;
            try {
                Connection connection = dataSource.getConnection();
                try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_EMAIL)) {
                    preparedStatement.setString(1, email);
                    preparedStatement.setLong(2, userId);
                    preparedStatement.executeUpdate();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        return res;
    }

    @Override
    public void updateFirstName(Long userId, String firstName) {
        boolean res = false;
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_FIRST_NAME)) {
                preparedStatement.setString(1, firstName);
                preparedStatement.setLong(2, userId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void updateSecondName(Long userId, String secondName) {
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_SECOND_NAME)) {
                preparedStatement.setString(1, secondName);
                preparedStatement.setLong(2, userId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean deleteUserById(Long userId) {
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
                    user.setFirstName(resultSet.getString("first_name"));
                    user.setSecondName(resultSet.getString("second_name"));
                    user.setBirthDate(resultSet.getDate("birth_date"));
                    user.setRole(user.role_helper(resultSet.getInt("role_id")));
                    if (!user.isAdmin()) {
                        users.add(user);
                    }
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }


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
                    user.setFirstName(resultSet.getString("first_name"));
                    user.setSecondName(resultSet.getString("second_name"));
                    user.setBirthDate(resultSet.getDate("birth_date"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public User findByEmail(String email) {
        User user = null;
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_EMAIL);) {
                preparedStatement.setString(1, email);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    user = new User();

                    user.setId(resultSet.getLong("id"));
                    user.setUsername(resultSet.getString("user_name"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("password"));
                    user.setFirstName(resultSet.getString("first_name"));
                    user.setSecondName(resultSet.getString("second_name"));
                    user.setBirthDate(resultSet.getDate("birth_date"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

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
                    user.setFirstName(resultSet.getString("first_name"));
                    user.setSecondName(resultSet.getString("second_name"));
                    user.setBirthDate(resultSet.getDate("birth_date"));
                    user.setRole(user.role_helper(resultSet.getInt("role_id")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }


    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user = null;
        try {
            Connection connection = dataSource.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(GET_SQL_BY_USERNAME_AND_PASSWORD);) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setUsername(resultSet.getString("user_name"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("password"));
                    user.setFirstName(resultSet.getString("first_name"));
                    user.setSecondName(resultSet.getString("second_name"));
                    user.setBirthDate(resultSet.getDate("birth_date"));
                    user.setRole(user.role_helper(resultSet.getInt("role_id")));
                }
                return user;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }
}