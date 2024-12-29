package ru.itis.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.dto.UserDto;
import ru.itis.service.UserService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/html/signUp.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto userDto = UserDto.builder()
                .username(req.getParameter("username"))
                .password(req.getParameter("password"))
                .email(req.getParameter("email"))
                .build();
        UserService userService = new UserService();

        // todo: добавить обработку исключений

            if (userService.getUserByName(userDto.getUsername()) == null) {
                userService.create(userDto);
                resp.sendRedirect("/signIn");
            }
    }
}
