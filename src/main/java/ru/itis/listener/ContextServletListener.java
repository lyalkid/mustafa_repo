package ru.itis.listener;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextServletListener implements ServletContextListener {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "postgres";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/mustafa";

    private static final String DB_DRIVER = "org.postgresql.Driver";


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setUrl(DB_URL);



    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }


}
