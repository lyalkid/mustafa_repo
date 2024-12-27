package ru.itis.repository;

import ru.itis.model.Announcement;

import java.util.List;

public interface AnnouncementRepository {
    void save(Announcement announcement);
    void update(Announcement announcement);
    void updateTitle(Announcement announcement, String new_title) ;
    void updateDescription(Announcement announcement, String new_description);
    boolean delete(Announcement announcement);
    boolean deleteById(Long id);

    Announcement findById(Long id);
    List<Announcement> findAll();
    List<Announcement> findByTitle(String title);


}
