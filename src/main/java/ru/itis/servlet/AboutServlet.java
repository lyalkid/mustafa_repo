package ru.itis.servlet;

import ru.itis.utils.JspPaths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/about")
public class AboutServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");


        req.setAttribute("pageTitle", "About Our Application");
        req.setAttribute("description", "This web application is created to help users buy and sell real estate easily.");


        req.getRequestDispatcher(JspPaths.ABOUT).forward(req, resp);
    }
}
