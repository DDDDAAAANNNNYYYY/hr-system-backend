package com.hr.hrserver.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        String message;
        System.out.println("get here a");
        try {
            try {
                System.out.println("get here b");
                Files.copy(file.getInputStream(), this.rootLocation.resolve("abcd.pdf"));
                System.out.println("get here c");
            } catch (Exception e) {
                throw new RuntimeException("FAIL!");
            }
            files.add(file.getOriginalFilename());
            System.out.println(file.getOriginalFilename());
            System.out.println(file.getName());


            message = "Successfully uploaded!";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "Failed to upload!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }
}
