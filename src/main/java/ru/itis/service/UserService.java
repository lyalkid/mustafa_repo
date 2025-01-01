package ru.itis.service;

import ru.itis.dto.UserDto;
import ru.itis.model.User;
import ru.itis.repository.UserRepository;
import ru.itis.repository.impl.UserRepositoryJdbcImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.sql.Date;
import java.util.List;

public class UserService {

    private UserRepository userRepository;

    public UserService(){
        this.userRepository = new UserRepositoryJdbcImpl();
    }

    public UserService(DataSource dataSource) {
        this.userRepository = new UserRepositoryJdbcImpl(dataSource);
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
    public boolean deleteById(Long id) {
        boolean result = false;
        try {
            result = userRepository.deleteUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void create(UserDto userDto){
        saveUser(new User(userDto));
    }

    public User getUserById(Long id){
        return userRepository.findById(id);
    }

    public User getUserByName(String username){
        return userRepository.findByUsername(username);

    }
    public User getByUsernameAndPassword(String username, String password) {
        User user = null;
        try {
            user  = userRepository.findByUsernameAndPassword(username, password);
            ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public void auth(User user, HttpServletRequest request, HttpServletResponse response){
        request.getSession().setAttribute("user", user);
        request.getSession().setAttribute("username", user.getUsername());
        request.getSession().setAttribute("authenticated", true);
    }

    public boolean isNonGuest(HttpServletRequest req, HttpServletResponse resp){
        return req.getSession().getAttribute("user") != null;
    }

    public User getUser(HttpServletRequest req, HttpServletResponse resp){
        return (User) req.getSession().getAttribute("user");
    }

    public boolean updateFirstName(User user, String firstName){
        return userRepository.updateFirstName(user, firstName);
    }

    public boolean updateSecondName(User user, String secondName){
        return userRepository.updateSecondName(user, secondName);
    }

    public boolean updateBirthDate(User user, String birthDate){
        return userRepository.updateBirthDate(user, Date.valueOf(birthDate));
    }
    public boolean updatePassword(User user, String password){
        return userRepository.updatePassword(user, password);
    }

    public boolean updateUsername(User user, String username){
        return userRepository.updateUsername(user, username);
    }
    public boolean updateEmail(User user, String email){
        return userRepository.updateEmail(user, email);
    }
}