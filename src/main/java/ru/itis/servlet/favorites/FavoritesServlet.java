package ru.itis.servlet.favorites;

import ru.itis.model.Announcement;
import ru.itis.model.Favorites;
import ru.itis.model.User;
import ru.itis.service.AnnouncementService;
import ru.itis.service.FavoriteService;
import ru.itis.utils.JspPaths;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/favorites")
public class FavoritesServlet extends HttpServlet {
    private FavoriteService favoriteService;
    private AnnouncementService announcementService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        favoriteService = (FavoriteService) config.getServletContext().getAttribute("favoriteService");
        announcementService = (AnnouncementService) config.getServletContext().getAttribute("announcementService");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<Favorites> favorites = favoriteService.getFavorites(user.getId());

        List<Announcement> announcements = new ArrayList<>();
        for (var favorite : favorites) {
            announcements.add(announcementService.getAnnouncementById(favorite.getAnnouncementId()));
        }
        req.setAttribute("announcements", announcements);
        req.getRequestDispatcher(JspPaths.FAVORITES_LIST).forward(req, resp);
    }

}
