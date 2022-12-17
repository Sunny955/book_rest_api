package com.bootrestbook.restapi.helper;

import org.aspectj.apache.bcel.util.ClassPath;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadHelper {
//    public final String UPLOAD_DIR = "F:\\restapi\\src\\main\\resources\\static\\images";
public final String UPLOAD_DIR;

    {
        try {
            // Goes to --> target/classes/static/images
            UPLOAD_DIR = String.valueOf(new ClassPathResource("static/images/").getFile().getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public FileUploadHelper() throws IOException {
    }

    public boolean uploadFile(MultipartFile file) {
            boolean upload = false;

            try {

                Files.copy(file.getInputStream(), Paths.get(UPLOAD_DIR+ File.separator+file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
                upload = true;

            }catch (Exception e) {
                e.printStackTrace();
            }

            return upload;
    }
}