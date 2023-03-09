package com.example.openapispring.service;

import com.example.openapispring.model.FileDB;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface FileService {
    void saveFile(MultipartFile file) throws IOException;
    FileDB getFile(String id);
    List<FileDB> getAllFiles();
    void deleteOne(String id);
    void deleteAll();
    Optional<FileDB> findById(String id);
    void updateFileData(FileDB fileDB);
}
