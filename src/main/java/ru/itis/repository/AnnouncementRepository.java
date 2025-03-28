package ru.itis.repository;

import ru.itis.model.Announcement;

import java.util.List;

public interface AnnouncementRepository {
    Long save(Announcement announcement);

    void save(String title, String description, Long user_id);

    void update(Announcement announcement);

    void updateTitle(Long announcementId, String new_title);

    void updateDescription(Long announcementId, String new_description);


    boolean deleteByAnnouncementId(Long id);

    Announcement findById(Long id);

    List<Announcement> findAll();

    List<Announcement> findByUserId(Long id);


}
