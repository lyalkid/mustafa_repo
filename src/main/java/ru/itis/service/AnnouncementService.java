package ru.itis.service;

import ru.itis.model.Announcement;
import ru.itis.repository.AnnouncementRepository;
import ru.itis.repository.impl.AnnouncementRepositoryJdbcImpl;


import javax.sql.DataSource;
import java.util.List;

public class AnnouncementService {

    private AnnouncementRepository announcementRepository;
    private UserService userService = new UserService();
    private ImageService imageService = new ImageService();

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

    public List<Announcement>    getAnnouncementByUserId(Long userId){return announcementRepository.findByUserId(userId);}
    public void save(Announcement announcement) {
        try {
            String imagePath = imageService.getImagesByAnnouncementId(announcement.getId()).getFilePath();
            announcementRepository.save(announcement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save(String title, String description, Long user_id) {
        try {
//            String imagePath = imageService.getImagesByAnnouncementId(announcement.getId()).getFilePath();
            announcementRepository.save(title, description, user_id);
        } catch (Exception e) {
            throw new RuntimeException(e);
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

