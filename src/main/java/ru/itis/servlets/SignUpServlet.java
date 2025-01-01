package ru.itis.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.itis.dto.UserDto;
import ru.itis.service.UserService;

import java.io.IOException;
import java.sql.Date;

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
                .firstName(req.getParameter("firstName"))
                .secondName(req.getParameter("secondName"))
                .birthDate(Date.valueOf(req.getParameter("birthDate")))
                .build();
        UserService userService = new UserService();

        // todo: добавить обработку исключений

        if (userService.getUserByName(userDto.getUsername()) == null) {
            userService.create(userDto);
        }
        resp.sendRedirect("/signIn");

    }
}
