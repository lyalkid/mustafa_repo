package ru.itis;

import ru.itis.model.User;
import ru.itis.service.UserService;

public class Main {
    public static void main(String[] args) {
        User user = new User(1l, "Mustafa", "root", "mustafa@mail.ru");
        System.out.println(user);

        UserService userService = new UserService();
        System.out.println(        userService.getAllUsers());

//        userService.saveUser(user);
//        System.out.println(        userService.getAllUsers());


    }
}
