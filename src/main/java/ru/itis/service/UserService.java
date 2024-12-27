package ru.itis.service;

import ru.itis.model.User;
import ru.itis.repository.UserRepository;
import ru.itis.repository.UserRepositoryJdbcImpl;

import javax.sql.DataSource;
import java.util.List;

public class UserService {

    private UserRepository userRepository;

    public UserService(DataSource dataSource) {
        this.userRepository = new UserRepositoryJdbcImpl(dataSource);
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public void save(User user) {
        try {
            userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean deleteById(Long id) {
        boolean result = false;
        try {
            result = userRepository.deleteUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id);
    }

}
