package ru.itis.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
public class Image {
    private Long id;
    private String storageFileName;
    private String originalFileName;
    private String filePath;
    private String fileType;
    private Long size;
    private Long announcementId;
}