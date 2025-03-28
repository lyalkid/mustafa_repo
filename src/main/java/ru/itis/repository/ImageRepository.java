package ru.itis.repository;



import ru.itis.model.Image;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

public interface ImageRepository {
    Image findByImage(Image image2);
    Image findById(Long Id);
    Image findByImagePath(String imagePath);
    List<Image> findAllByAnnouncementId(Long announcementId);

    Image saveImageToStorage(InputStream file, String originalName, String contentType, Long size, Long announcementId);
    void save(Image image) throws SQLException;
    void deleteByAnnouncementId(Long announcementId);
}
