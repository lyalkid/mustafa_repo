package ru.itis.repository;

import ru.itis.model.User;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void save(User user) ;

    boolean updateUsername(User user, String username) ;

    boolean updatePassword(User user, String new_password) ;

    boolean updateEmail(User user, String email) ;

    boolean updateFirstName(User user, String firstName) ;

    boolean updateSecondName(User user, String secondName) ;

    boolean updateBirthDate(User user, Date b_date) ;

    boolean deleteUserById(Long userId) ;

    List<User> findAll();

    User findById(Long userId);

    User findByEmail(String email);

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password) ;
}