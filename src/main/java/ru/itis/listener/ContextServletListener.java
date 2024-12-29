package ru.itis.listener;


import org.springframework.jdbc.datasource.DriverManagerDataSource;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ru.itis.repository.AnnouncementRepository;
import ru.itis.repository.AnnouncementRepositoryJdbcImpl;
import ru.itis.repository.UserRepository;
import ru.itis.repository.UserRepositoryJdbcImpl;

@WebListener
public class ContextServletListener implements ServletContextListener {

    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "postgres";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_DRIVER = "org.postgresql.Driver";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setUrl(DB_URL);
        dataSource.setUsername(DB_USERNAME);

        UserRepository userRepository = new UserRepositoryJdbcImpl(dataSource);
        AnnouncementRepository announcementRepository = new AnnouncementRepositoryJdbcImpl(dataSource);
        servletContext.setAttribute("userRepository", userRepository);
        servletContext.setAttribute("announcementRepository", announcementRepository);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}