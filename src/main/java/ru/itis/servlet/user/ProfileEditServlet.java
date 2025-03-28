package ru.itis.servlet.user;

import ru.itis.model.User;
import ru.itis.service.UserService;
import ru.itis.utils.JspPaths;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/profile/edit")
public class ProfileEditServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = (UserService) config.getServletContext().getAttribute("userService");
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspPaths.PROFILE_EDIT).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");


        boolean noErrors = true;
        User user = userService.getUser(req, resp);
        String username = (req.getParameter("username"));
        String password = (req.getParameter("password"));
        String email = (req.getParameter("email"));
        String firstName = (req.getParameter("firstName"));
        String secondName = (req.getParameter("secondName"));
        String birthDate = ((req.getParameter("birthDate")));
        boolean email_b = false;
        boolean username_b = false;
        if (!firstName.equals("")) {
            req.setAttribute("firstName", firstName);

            userService.updateFirstName(user, firstName);
            user.setFirstName(firstName);
        }
        if (!secondName.equals("")) {
            req.setAttribute("secondName", secondName);

            userService.updateSecondName(user, secondName);
            user.setSecondName(secondName);
        }
        if (!birthDate.equals("")) {
            req.setAttribute("birthDate", birthDate);
            userService.updateBirthDate(user, birthDate);
            user.setBirthDate(Date.valueOf(birthDate));
        }
        if (!password.equals("")) {
            userService.updatePassword(user, password);
            user.setPassword(password);
        }
        if (!email.equals("")) {
            email_b = userService.updateEmail(user, email);
            if (!email_b) {
                noErrors = false;
                req.setAttribute("emailMessage", "email exists");
            } else {
                req.setAttribute("email", email);
                user.setEmail(email);
                req.removeAttribute("emailMessage");
            }

        }
        if (!username.equals("")) {
            username_b = userService.updateUsername(user, username);
            if (!username_b) {
                noErrors = false;
                req.setAttribute("usernameMessage", "username exists");
            } else {
                req.setAttribute("username", username);
                user.setUsername(username);
                req.removeAttribute("usernameMessage");
            }
        }
        req.removeAttribute("user");
        req.setAttribute("user", user);
        if (noErrors) {

            resp.sendRedirect("/profile");
        } else {
            getServletContext().getRequestDispatcher(JspPaths.PROFILE_EDIT).forward(req, resp);
        }
    }
}
