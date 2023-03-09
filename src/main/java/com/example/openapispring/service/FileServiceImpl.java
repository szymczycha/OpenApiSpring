package com.example.openapispring.service;

import com.example.openapispring.model.FileDB;
import com.example.openapispring.repository.FileDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService{
    @Autowired
    private FileDBRepository fileDBRepository;
    @Override
    public void saveFile(MultipartFile file) throws IOException {
        String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        FileDB fileDB = new FileDB(
                filename,
                file.getContentType(),
                System.currentTimeMillis(),
                file.getBytes()
        );
        fileDBRepository.save(fileDB);
    }

    @Override
    public FileDB getFile(String id) {
        return fileDBRepository.getReferenceById(id);
    }

    @Override
    public List<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream().toList();
    }

    @Override
    public void deleteOne(String id) {
        fileDBRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        fileDBRepository.deleteAll();
    }

    @Override
    public Optional<FileDB> findById(String id) {
        return fileDBRepository.findById(id);
    }

    @Override
    public void updateFileData(FileDB fileDB) {
         fileDBRepository.deleteById(fileDB.getId());
         fileDBRepository.save(fileDB);
    }
}
