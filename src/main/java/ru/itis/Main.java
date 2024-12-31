package ru.itis;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.model.User;
import ru.itis.service.UserService;
import ru.itis.utils.DBProperty;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {
        System.out.println(DBProperty.DB_USERNAME);
    }
}
