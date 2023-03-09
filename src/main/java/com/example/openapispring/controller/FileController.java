package com.example.openapispring.controller;

import com.example.openapispring.message.ResponseFile;
import com.example.openapispring.message.ResponseMessage;
import com.example.openapispring.model.FileDB;
import com.example.openapispring.service.FileService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@OpenAPIDefinition(info = @Info(title = "My API",
        description = "opis API",
        version = "v1",
        license = @License(name = "Apache 2.0",
        url = "https://www.apache.org/licenses/LICENSE-2.0")))
@RestController
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping(path = "/api/files", consumes="multipart/form-data")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file){
        try {
            fileService.saveFile(file);
        } catch (IOException e) {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseMessage("error", e.getMessage()));
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseMessage(
                        "ok"
                ));
    }

    @GetMapping("/api/files")
    private ResponseEntity<List<ResponseFile>> listFiles(){
        List<FileDB> files = fileService.getAllFiles();
        ArrayList<ResponseFile> responseFileList = new ArrayList<>();
        for (FileDB file :
                files) {
            ResponseFile responseFile = new ResponseFile(file.getName(), "http://localhost:3000/api/files/"+file.getId(), file.getType(), file.getData().length, file.getTime());
            responseFileList.add(responseFile);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseFileList);
    }
    @GetMapping("/api/files/{id}")
    public ResponseEntity<byte[]> getFiles(@PathVariable String id){
        FileDB fileDB = fileService.getFile(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_JPEG)
                .body(fileDB.getData());
    }
    @DeleteMapping("/api/delete")
    public ResponseEntity<ResponseMessage> deleteAll() {
        try{
            fileService.deleteAll();
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseMessage("error", e.getMessage()));

        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseMessage("ok"));
    }
    @DeleteMapping("/api/delete/{id}")
    private ResponseEntity<ResponseMessage> deleteOne(@PathVariable String id){
        try{
            fileService.deleteOne(id);
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseMessage("error", e.getMessage()));

        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseMessage("ok"));

    }
    @PatchMapping("/api/files")
    private  ResponseEntity<ResponseMessage> editOne(){

        return ResponseEntity
                .status(HttpStatus.NOT_IMPLEMENTED)
                .body(new ResponseMessage("not implemented"));
    }
}

