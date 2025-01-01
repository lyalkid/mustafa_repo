package ru.itis.servlets;

import ru.itis.model.Announcement;
import ru.itis.model.User;
import ru.itis.service.AnnouncementService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/announcements/my/new")
public class CreateAnnouncementServlet extends HttpServlet {
    private AnnouncementService announcementService = new AnnouncementService();

    private static final String CREATE_ANNOUNCEMENT_JSP = "/jsp/create_announcement.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(CREATE_ANNOUNCEMENT_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        User user = (User) req.getSession().getAttribute("user");
        announcementService.save(title, description, user.getId());
        resp.sendRedirect("/announcements/my");
    }
}
