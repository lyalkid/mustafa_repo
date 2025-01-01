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
import java.util.List;

@WebServlet("/announcements/my")
public class MyAnnouncementsServlet extends HttpServlet {
    private AnnouncementService announcementService = new AnnouncementService();
    private static final String MY_ANNOUNCEMENT_JSP = "/jsp/my_announcement.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        List<Announcement> announcements = announcementService.getAnnouncementByUserId(user.getId());
        req.setAttribute("announcements", announcements);
        req.getRequestDispatcher(MY_ANNOUNCEMENT_JSP).forward(req, resp);
    }


}
