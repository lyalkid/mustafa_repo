package ru.itis.servlets;

import ru.itis.model.User;
import ru.itis.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
                req.getRequestDispatcher("/jsp/home.jsp").forward(req, resp);
            }
        }
    }
}
