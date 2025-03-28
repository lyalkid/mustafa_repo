package ru.itis.servlet.announcement;

import ru.itis.model.Announcement;
import ru.itis.service.AnnouncementService;
import ru.itis.utils.JspPaths;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/announcements")
public class AnnouncementsServlet extends HttpServlet {
    private AnnouncementService announcementService ;

    @Override
    public void init(ServletConfig config) throws ServletException {
        announcementService = (AnnouncementService) config.getServletContext().getAttribute("announcementService");
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        List<Announcement> announcements = announcementService.getAllAnnouncements();
        req.setAttribute("announcements", announcements);
        req.getRequestDispatcher(JspPaths.ANNOUNCEMENT).forward(req, resp);
        System.out.println(announcements);

    }
}