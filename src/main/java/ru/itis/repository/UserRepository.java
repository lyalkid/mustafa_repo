package ru.itis.repository;

import ru.itis.model.User;

import java.sql.Date;
import java.util.List;

public interface UserRepository {
    void save(User user) ;

    boolean updateUsername(Long userId, String username) ;

    void updatePassword(Long userId, String new_password) ;

    boolean updateEmail(Long userId, String email) ;

    void updateFirstName(Long userId, String firstName) ;

    void updateSecondName(Long userId, String secondName) ;

    void updateBirthDate(Long userId, Date b_date) ;

    boolean deleteUserById(Long userId) ;

    List<User> findAll();

    User findById(Long userId);

    User findByEmail(String email);

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password) ;
}