package ru.itis.servlet.announcement;

import ru.itis.model.Announcement;
import ru.itis.model.Image;
import ru.itis.model.Review;
import ru.itis.service.*;
import ru.itis.utils.JspPaths;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/announcements/*")
public class AnnouncementDetailsServlet extends HttpServlet {
    private UserService userService;
    private AnnouncementService announcementService;
    private ImageService imageService;
    private FavoriteService favoriteService;
    private ReviewService reviewService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        announcementService = (AnnouncementService) config.getServletContext().getAttribute("announcementService");
        imageService = (ImageService) config.getServletContext().getAttribute("imageService");
        favoriteService = (FavoriteService) config.getServletContext().getAttribute("favoriteService");
        reviewService = (ReviewService) config.getServletContext().getAttribute("reviewService");
        userService = (UserService) config.getServletContext().getAttribute("userService");
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

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


        Announcement announcement = announcementService.getAnnouncementById(announcementId);
        List<Image> images = imageService.getAllByAnnouncement(announcementId);
        boolean isFavorite = favoriteService.isFavorite((Long) req.getSession().getAttribute("userId"), announcementId);
        boolean isReviewed = reviewService.isReviewed((Long) req.getSession().getAttribute("userId"), announcementId);

        List<Review> reviews = reviewService.getReviewByAnnouncement(announcementId);

        for (Review review : reviews) {
            review.setAuthor(userService.getUserById(review.getUserId()).getUsername());
        }
        if (announcement != null) {
            req.setAttribute("title", announcement.getTitle());
            req.setAttribute("description", announcement.getDescription());
            req.setAttribute("id", announcement.getId());
            req.setAttribute("images", images);
            req.setAttribute("isFavorite", isFavorite);
            req.setAttribute("isReviewed", isReviewed);
            req.setAttribute("reviews", reviews);
            req.getRequestDispatcher(JspPaths.ANNOUNCEMENT_DETAILS).forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
