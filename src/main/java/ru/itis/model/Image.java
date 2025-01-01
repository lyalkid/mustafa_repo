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
    private String fileName;
    private String filePath;
    private String type;
}