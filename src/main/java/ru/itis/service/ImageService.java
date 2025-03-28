package ru.itis.service;

import ru.itis.model.Image;
import ru.itis.repository.ImageRepository;
import ru.itis.repository.impl.ImageRepositoryJdbcImpl;
import ru.itis.utils.Property;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class ImageService {

    private final ImageRepository imageRepository;
    private final String basePath = Property.IMAGES_FOLDER;

    public ImageService() {
        this.imageRepository = new ImageRepositoryJdbcImpl();
    }

    public Image getImagesByImage(Image image) {
        return imageRepository.findByImage(image);
    }
    public Image getImageById(Long id){
        return imageRepository.findById(id);
    }
    public Image getImageByFilePath(String imagePath){
        return imageRepository.findByImagePath(imagePath);
    }
    public Image uploadImage(InputStream image, String originalFileName, String contentType, Long size, Long announcementId) {
        return imageRepository.saveImageToStorage(image, originalFileName, contentType, size, announcementId);
    }

    public void deleteImagesByAnnouncementId(Long announcementId) {
        imageRepository.deleteByAnnouncementId(announcementId);
    }

    public Long getIdByImage(Image image) {
        return imageRepository.findByImage(image).getId();
    }

    public List<Image> getAllByAnnouncement(Long announcementId){
        return imageRepository.findAllByAnnouncementId(announcementId);
    }


    public void writeFileFromStorage(Long fileId, OutputStream outputStream) {
        // нашли файл в базе
        Image fileInfo = imageRepository.findById(fileId);
        // нашли файл на диске
        File file = new File(Property.IMAGES_FOLDER + fileInfo.getStorageFileName() + "." + fileInfo.getFileType().split("/")[1]);
        try {
            // записали его в ответ
            Files.copy(file.toPath(), outputStream);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }
}
