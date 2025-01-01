package ru.itis.servlets;

import ru.itis.model.Announcement;
import ru.itis.service.AnnouncementService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/announcements")
public class AnnouncementsServlet extends HttpServlet {
    private AnnouncementService announcementService = new AnnouncementService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Announcement> announcements = announcementService.getAllAnnouncements();
        req.setAttribute("announcements", announcements);
        req.getRequestDispatcher("/jsp/announcement.jsp").forward(req, resp);
        System.out.println(announcements);

    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<Announcement> announcements = announcementService.getAllAnnouncements();
//        req.setAttribute("announcements", announcements);
//        req.getRequestDispatcher("/jsp/announcement.jsp").forward(req, resp);
//    }
}
