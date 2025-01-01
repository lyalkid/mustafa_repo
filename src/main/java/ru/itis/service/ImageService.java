package ru.itis.service;

import ru.itis.model.Image;
import ru.itis.repository.ImageRepository;
import ru.itis.repository.impl.ImageRepositoryJdbcImpl;

public class ImageService {

    private final ImageRepository imageRepository;

    public ImageService() {
        this.imageRepository = new ImageRepositoryJdbcImpl();
    }

    public Image getImagesByAnnouncementId(Long announcementId) {
        return imageRepository.findAllByAnnouncementId(announcementId);
    }

    public void saveImage(Image image) {
        imageRepository.save(image);
    }

    public void deleteImagesByAnnouncementId(Long announcementId) {
        imageRepository.deleteByAnnouncementId(announcementId);
    }
}
