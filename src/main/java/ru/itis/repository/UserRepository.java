package ru.itis.repository;

import ru.itis.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserRepository {
    void save(User user) throws SQLException;
    void update(User user) throws SQLException;
    void updatePassword(User user, String new_password) throws SQLException;
    boolean deleteUserById(Long userId)throws SQLException;

    List<User> findAll();
    User findById(Long userId);
    User findByEmail(String email)throws SQLException;
    User findByUsername(String username)throws SQLException;

}
