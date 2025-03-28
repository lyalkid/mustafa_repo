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

    public List<Announcement> getAnnouncementByUserId(Long userId) {
        return announcementRepository.findByUserId(userId);
    }

    public Long saveAnnouncement(Announcement announcement) {
        Long res = -1l;
        try {
//            String imagePath = imageService.getImagesByAnnouncementId(announcement.getId()).getFilePath();
            res = announcementRepository.save(announcement);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
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
//
//    public Long getAnnouncementIdByAnnouncement(Announcement announcement){
//        return announcementRepository.findByAnnouncement(announcement);
//    }

    public Announcement getAnnouncementById(Long id) {
        return announcementRepository.findById(id);
    }
}

