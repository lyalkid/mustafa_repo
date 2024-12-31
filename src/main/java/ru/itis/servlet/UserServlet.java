package ru.itis.servlet;

import ru.itis.model.User;
import ru.itis.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        String id = req.getParameter("id");

        if (id != null && !id.isEmpty()) {
            User user = userService.getUserById(Long.parseLong(id));
            if (user != null) {
                req.setAttribute("username", user.getUsername());
                req.setAttribute("email", user.getEmail());
                req.getRequestDispatcher("/jsp/user.jsp").forward(req, resp);
            }
        }
    }
}