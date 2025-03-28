package ru.itis.servlet.admin;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.itis.model.User;
import ru.itis.service.UserService;
import ru.itis.utils.JspPaths;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/users")
public class AdminUsersServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = (UserService) config.getServletContext().getAttribute("userService");
        super.init(config);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userService.getAllUsers();  // Получаем список всех пользователей
        req.setAttribute("users", users);
        req.getRequestDispatcher(JspPaths.ADMIN_USERS).forward(req, resp);  // Переход на JSP
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String userIdParam = req.getParameter("userId");  // Получаем ID пользователя для удаления

        if (userIdParam != null) {
            Long userId = Long.parseLong(userIdParam);
            userService.deleteUserById(userId);  // Удаляем пользователя
        }

        resp.sendRedirect(req.getContextPath() + "/admin/users");  // Обновляем страницу
    }
}
