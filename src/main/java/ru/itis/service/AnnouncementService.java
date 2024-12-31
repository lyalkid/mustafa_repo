package ru.itis.service;

import ru.itis.model.Announcement;
import ru.itis.repository.AnnouncementRepository;
import ru.itis.repository.impl.AnnouncementRepositoryJdbcImpl;


import javax.sql.DataSource;
import java.util.List;

public class AnnouncementService {

    private AnnouncementRepository announcementRepository;

    public AnnouncementService() {
        this.announcementRepository = new AnnouncementRepositoryJdbcImpl();
    }

    public AnnouncementService(DataSource dataSource) {
        this.announcementRepository = new AnnouncementRepositoryJdbcImpl(dataSource);
    }

    public AnnouncementService(AnnouncementRepository AnnouncementRepository) {
        this.announcementRepository = AnnouncementRepository;
    }

    public List<Announcement> getAllAnnouncements() {
        return announcementRepository.findAll();
    }


    public void save(Announcement Announcement) {
        try {
            announcementRepository.save(Announcement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean deleteById(Long id) {
        boolean result = false;
        try {
            result = announcementRepository.deleteByAnnouncementId(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Announcement getAnnouncementById(Long id) {
        return announcementRepository.findById(id);
    }
}

