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

@WebServlet("/announcement/*")
public class AnnouncementDetailsServlet extends HttpServlet {
    private AnnouncementService announcementService = new AnnouncementService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        // Извлекаем id фильма из URL
        String[] pathParts = pathInfo.split("/");
        if (pathParts.length != 2) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        Long announcementId;
        try {
            announcementId = Long.parseLong(pathParts[1]);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        List<Announcement> announcementList = announcementService.getAllAnnouncements();
        Announcement announcement = announcementService.getAnnouncementById(announcementId);
        if(announcement!= null) {
            req.setAttribute("title", announcement.getTitle());
            req.setAttribute("desc", announcement.getDescription());
            req.setAttribute("id", announcement.getId());
            req.getRequestDispatcher("/jsp/announcementDetails.jsp").forward(req, resp);
        }
        else {
            req.getRequestDispatcher("/WEB-INF/view/page_not_found.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
