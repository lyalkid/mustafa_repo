package ru.itis.repository;

import ru.itis.model.Announcement;

import java.sql.SQLException;
import java.util.List;

public interface AnnouncementRepository {
    void save(Announcement announcement) ;
    void save(String title, String description, Long user_id) ;

    void update(Announcement announcement) ;
    void updateTitle(Announcement announcement, String new_title) ;
    void updateDescription(Announcement announcement, String new_description) ;

    void updateUserId(Announcement announcement, Long userId);
    void updateImageId(Announcement announcement, Long userId);


boolean deleteByAnnouncementId(Long id) ;
    Announcement findById(Long id);
    List<Announcement> findAll();
    List<Announcement> findByTitle(String title);

    List<Announcement> findByUserId(Long id);
     List<Announcement> findByImageId(Long imageId);


}
