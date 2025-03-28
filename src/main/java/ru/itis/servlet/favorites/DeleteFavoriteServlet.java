package ru.itis.servlet.favorites;

import ru.itis.model.User;
import ru.itis.service.FavoriteService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/favorites/delete")
public class DeleteFavoriteServlet extends HttpServlet {
    private FavoriteService favoriteService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        favoriteService = (FavoriteService) config.getServletContext().getAttribute("favoriteService");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        Long announcementId = Long.parseLong(req.getParameter("announcementId"));
        User user = (User) req.getSession().getAttribute("user");

        if (user != null) {
            favoriteService.removeFromFavorites(user.getId(), announcementId);
            req.getSession().removeAttribute("isFavorite");
            resp.sendRedirect(req.getContextPath() + "/announcements/" + announcementId);
        } else {
            resp.sendRedirect(req.getContextPath() + "/signIn");
        }
    }
}
