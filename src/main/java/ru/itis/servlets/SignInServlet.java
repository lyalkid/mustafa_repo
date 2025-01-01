package ru.itis.servlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import ru.itis.model.User;
import ru.itis.service.UserService;

import java.io.IOException;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {
    private static final String SIGN_IN_JSP = "/jsp/signIn.jsp";
    private UserService userService = new UserService();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(SIGN_IN_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // Проверяем, существует ли пользователь и соответствует ли пароль


        User user = userService.getByUsernameAndPassword(username, password);

        if (user != null) {
            HttpSession session = req.getSession(true);
            userService.auth(user, req, resp);
            var cookie = new Cookie("username", username);
            resp.addCookie(cookie);
            resp.sendRedirect(req.getContextPath() + "/home");

        } else {
            // Возвращаем сообщение об ошибке
            req.setAttribute("message", "Invalid username or password");
            getServletContext().getRequestDispatcher(SIGN_IN_JSP).forward(req, resp);
        }
    }
}