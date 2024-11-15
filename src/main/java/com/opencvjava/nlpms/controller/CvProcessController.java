package com.opencvjava.nlpms.controller;

import com.opencvjava.nlpms.service.CvProcessorService;
import com.opencvjava.nlpms.service.NlpService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/cv-processor")
@AllArgsConstructor
public class CvProcessController {

    private final CvProcessorService cvProcessorService;

    @PostMapping("/extract-image")
    public ResponseEntity<byte[]> extractImageFromPdf(@RequestParam("file") MultipartFile file) throws IOException {
        byte[] imageBytes = cvProcessorService.fileControl(file);
        if (imageBytes == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "image/png");
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }

}
