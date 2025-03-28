package ru.itis.servlet.user;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.itis.dto.CreateUserDto;
import ru.itis.model.Role;
import ru.itis.service.UserService;
import ru.itis.utils.JspPaths;

import java.io.IOException;


@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = (UserService) config.getServletContext().getAttribute("userService");
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspPaths.REGISTER).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");


        CreateUserDto userDto = CreateUserDto.builder()
                .username(req.getParameter("username"))
                .password(req.getParameter("password"))
                .email(req.getParameter("email"))
                .firstName(req.getParameter("firstName"))
                .secondName(req.getParameter("secondName"))
                .birthDate(req.getParameter("birthDate"))
                .role(Role.USER)
                .build();
        req.removeAttribute("userMessage");
        req.removeAttribute("emailMessage");
        boolean noErrors = true;
        if (userService.getUserByName(userDto.getUsername()) != null) {
            noErrors = false;
            req.setAttribute("userMessage", "User with this username already exists");
        }
        if (userService.getByEmail(userDto.getEmail()) != null) {
            noErrors = false;
            req.setAttribute("emailMessage", "User with this email already exists");
        }
        if (!noErrors) {
            req.getRequestDispatcher(JspPaths.REGISTER).forward(req, resp);
        } else {
            userService.create(userDto);
            resp.sendRedirect("/signIn");
        }

    }
}
