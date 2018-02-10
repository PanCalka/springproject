package com.PanCalka.Recipe.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    void saveImageFile(Long aLong, MultipartFile file);
}
