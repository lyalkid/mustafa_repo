package ru.itis.repository;

import ru.itis.model.Announcement;

import java.sql.SQLException;
import java.util.List;

public interface AnnouncementRepository {
    void save(Announcement announcement) throws SQLException;
    void update(Announcement announcement) throws SQLException;
    void updateTitle(Announcement announcement, String new_title) throws SQLException;
    void updateDescription(Announcement announcement, String new_description) throws SQLException;
    boolean deleteByAnnouncementId(Long id) throws SQLException;
    Announcement findById(Long id);
    List<Announcement> findAll();
    List<Announcement> findByTitle(String title);

}