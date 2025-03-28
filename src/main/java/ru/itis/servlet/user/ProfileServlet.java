package ru.itis.servlet.user;

import ru.itis.model.User;
import ru.itis.utils.JspPaths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        User user = (User) req.getSession().getAttribute("user");
        req.setAttribute("username", user.getUsername());
        req.setAttribute("email", user.getEmail());
        req.setAttribute("firstName", user.getFirstName());
        req.setAttribute("secondName", user.getSecondName());
        req.setAttribute("birthDate", user.getBirthDate());

        req.getRequestDispatcher(JspPaths.PROFILE).forward(req, resp);
    }
}
