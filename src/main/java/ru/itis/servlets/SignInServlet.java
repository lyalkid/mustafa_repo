package ru.itis.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.model.User;
import ru.itis.service.UserService;

import java.io.IOException;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/html/signIn.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // Проверяем, существует ли пользователь и соответствует ли пароль
        User user = userService.getUserByName(username);

        if (user != null && user.getPassword().equals(password)) {
            // Сохраняем пользователя в сессии
            req.getSession().setAttribute("currentUser", user);
            // Редирект на страницу пользователя
            resp.sendRedirect(req.getContextPath() + "/user?id=" + user.getId());
        } else {
            // Возвращаем сообщение об ошибке
            req.setAttribute("errorMessage", "Invalid username or password");
            req.getRequestDispatcher("/html/signIn.html").forward(req, resp);
        }


    }
}
