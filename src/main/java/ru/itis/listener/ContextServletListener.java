package ru.itis.listener;

import ru.itis.service.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        UserService userService = new UserService();
        AnnouncementService announcementService = new AnnouncementService();
        ImageService imageService = new ImageService();
        FavoriteService favoriteService = new FavoriteService();
        ReviewService reviewService = new ReviewService();
        sce.getServletContext().setAttribute("userService", userService);
        sce.getServletContext().setAttribute("announcementService", announcementService);
        sce.getServletContext().setAttribute("imageService", imageService);
        sce.getServletContext().setAttribute("favoriteService", favoriteService);
        sce.getServletContext().setAttribute("reviewService", reviewService);
        System.out.println("Application Started");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Application Stopped");
    }
}
