package com.bootrestbook.restapi.controllers;

import com.bootrestbook.restapi.helper.FileUploadHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@RestController
public class FileUploadController {
    @Autowired
    private FileUploadHelper fileUploadHelper;

    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {

      try {
          // Validation
          if(file.isEmpty()) {
              return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File can't be empty!");
          }

          // Validation for image files
          if(!Objects.equals(file.getContentType(), "image/jpeg")) {
              return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only JPEG content is allowed!");
          }

          // File upload
          boolean upload = fileUploadHelper.uploadFile(file);

          if(upload) {
              return ResponseEntity.ok("File is successfully uploaded!");
          }
      }catch (Exception e) {
          e.printStackTrace();
      }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Oops! something went wrong try again");
    }
}