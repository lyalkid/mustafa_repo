package ru.itis.servlets;

import ru.itis.model.User;
import ru.itis.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/profile/edit")
public class ProfileEditServlet extends HttpServlet {
    private static final String EDIT_PROFILE_JSP = "/jsp/profile_edit.jsp";
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(EDIT_PROFILE_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean allOk = true;
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
                allOk = false;
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
                allOk = false;
                req.setAttribute("usernameMessage", "username exists");
            } else {
                req.setAttribute("username", username);
                user.setUsername(username);
                req.removeAttribute("usernameMessage");
            }
        }
        req.removeAttribute("user");
        req.setAttribute("user", user);



        if (allOk) {
//            getServletContext().getRequestDispatcher("/jsp/profile.jsp").forward(req, resp);
        resp.sendRedirect("/profile");
//    getServletContext().getRequestDispatcher("/profile").forward(req, resp);
        }
    }
}
