package ru.itis.servlet.user;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import ru.itis.service.UserService;
import ru.itis.utils.JspPaths;

import java.io.IOException;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {

        userService = (UserService) config.getServletContext().getAttribute("userService");
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspPaths.LOGIN).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // Проверяем, существует ли пользователь и соответствует ли пароль


        boolean authenticated = userService.authenticated(username, password, req);

        if (authenticated) {

            userService.authorization(userService.getUserByName(username), req, resp);
            resp.sendRedirect(req.getContextPath() + "/home");

        } else {
            // Возвращаем сообщение об ошибке
            req.setAttribute("message", "Invalid username or password");
            getServletContext().getRequestDispatcher(JspPaths.LOGIN).forward(req, resp);
        }
    }
}