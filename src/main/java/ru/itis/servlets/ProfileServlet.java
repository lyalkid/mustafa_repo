package ru.itis.servlets;

import ru.itis.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private static final String PROFILE_JSP = "/jsp/profile.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        req.setAttribute("username", user.getUsername());
        req.setAttribute("email", user.getEmail());
        req.setAttribute("firstName", user.getFirstName());
        req.setAttribute("secondName", user.getSecondName());
        req.setAttribute("birthDate", user.getBirthDate());

        req.getRequestDispatcher(PROFILE_JSP).forward(req, resp);
    }
}
