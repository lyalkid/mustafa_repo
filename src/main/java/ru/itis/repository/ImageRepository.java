package ru.itis.repository;



import ru.itis.model.Image;

import java.util.List;

public interface ImageRepository {
    Image findAllByAnnouncementId(Long announcementId);
    void save(Image image);
    void deleteByAnnouncementId(Long announcementId);
}
