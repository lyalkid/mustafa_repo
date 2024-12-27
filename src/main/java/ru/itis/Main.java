package ru.itis;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.model.User;
import ru.itis.service.UserService;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {
        User user = new User(1l, "Mustafa",  "mustafa@mail.ru", "root");

        System.out.println(user);
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/mustafa");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        dataSource.setDriverClassName("org.postgresql.Driver");

        UserService userService = new UserService(dataSource);
        System.out.println(userService.getAllUsers());

        userService.save(user);


        System.out.println(userService.getUserById(1l));


    }
}
