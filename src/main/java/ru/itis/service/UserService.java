package ru.itis.service;

import org.mindrot.jbcrypt.BCrypt;
import ru.itis.dto.CreateUserDto;
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

    public UserService() {
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

    public boolean deleteUserById(Long id) {
        boolean result = false;
        try {
            result = userRepository.deleteUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void create(CreateUserDto userDto) {
        User user = new User(userDto);
        registerUser(user, userDto.getPassword());
    }

    public User getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User getUserByName(String username) {
        return userRepository.findByUsername(username);

    }

    public User getByUsernameAndPassword(String username, String password) {

        User user = null;
        try {
            user = userRepository.findByUsernameAndPassword(username, password);
            ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public void authorization(User user, HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("username", user.getUsername());
        request.getSession().setAttribute("authenticated", true);
        if (user.isAdmin()) request.getSession().setAttribute("role", "admin");
        else request.getSession().setAttribute("role", "user");
    }

    public boolean isNonGuest(HttpServletRequest req, HttpServletResponse resp) {
        return req.getSession().getAttribute("user") != null;
    }

    public User getUser(HttpServletRequest req, HttpServletResponse resp) {
        return (User) req.getSession().getAttribute("user");
    }

    public void updateFirstName(User user, String firstName) {
        userRepository.updateFirstName(user.getId(), firstName);
    }

    public void updateSecondName(User user, String secondName) {
        userRepository.updateSecondName(user.getId(), secondName);
    }

    public void updateBirthDate(User user, String birthDate) {
        userRepository.updateBirthDate(user.getId(), Date.valueOf(birthDate));
    }

    public void updatePassword(User user, String password) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        userRepository.updatePassword(user.getId(), hashedPassword);
    }

    public boolean updateUsername(User user, String username) {
        return userRepository.updateUsername(user.getId(), username);
    }

    public boolean updateEmail(User user, String email) {
        return userRepository.updateEmail(user.getId(), email);
    }

    public void registerUser(User user, String password) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        user.setPassword(hashedPassword);
        saveUser(user);
    }

    public boolean authenticated(String username, String plainPassword, HttpServletRequest request) {
        User user = getUserByName(username);

        if (user == null) {
            return false;  // Пользователь не найден
        }

        request.getSession().setAttribute("user", user);
        request.getSession().setAttribute("userId", user.getId());

        // Проверка пароля
        return BCrypt.checkpw(plainPassword, user.getPassword());
    }

    public User getByEmail(String email){
        return userRepository.findByEmail(email);
    }
}