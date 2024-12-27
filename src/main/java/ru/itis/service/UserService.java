package ru.itis.service;

import ru.itis.model.User;
import ru.itis.repository.UserRepository;
import ru.itis.repository.UserRepositoryJdbcImpl;

import java.util.List;

public class UserService {

    private UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepositoryJdbcImpl();
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public void saveUser(User user) {
        try {
            userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
public void deleteUser(Long id) {
    try {
        userRepository.deleteUser(id);
    } catch (Exception e) {
        e.printStackTrace();
    }
}

}
