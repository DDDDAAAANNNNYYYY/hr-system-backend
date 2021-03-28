package com.hr.hrserver.controller;
import com.hr.hrserver.service.S3ServiceB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class FileController {
    List<String> files = new ArrayList<String>();
    private final Path rootLocation = Paths.get("./");

    @PostMapping("/savefile")
    public ResponseEntity<String> handleFileUpload( MultipartFile file) {
        S3ServiceB s3ServiceB = new S3ServiceB();
        String message;
        System.out.println("get here a");
        try {
            try {
                System.out.println("get here b");
                Files.copy(file.getInputStream(), this.rootLocation.resolve("a.txt"));

                System.out.println("get here c");


                s3ServiceB.putFromInputStreamToS3(file.getInputStream(), "/" + file.getName());
            } catch (Exception e) {
                System.out.println("runtime exception");
                e.printStackTrace();
                throw new RuntimeException("FAIL!");
            }
            files.add(file.getOriginalFilename());
            System.out.println(file.getOriginalFilename());
            System.out.println(file.getName());



            message = "Successfully uploaded!";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            System.out.println("other exception");
            message = "Failed to upload!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }
}
