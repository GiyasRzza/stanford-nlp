package com.careerhive.file_service.controller;

import com.careerhive.file_service.service.NlpService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



@RestController
@RequestMapping("/api/nlp")
@AllArgsConstructor
public class NlpController {

    private final NlpService nlpService;
    @PostMapping("/extract")
    public String extractEntities(@RequestBody String text) {
        nlpService.extractEntities(text);
        return "Entity extraction completed!";
    }

    @PostMapping("/train-online")
    public ResponseEntity<?> trainModel(@RequestParam("file") MultipartFile file) {
        nlpService.modelTraining(file);
        return new ResponseEntity<>("Train Model => Online",HttpStatus.OK);
    }
    @PostMapping("/train-local")
    public ResponseEntity<?> trainModel() {
        nlpService.modelTraining();
        return new ResponseEntity<>("Train Model => Local",HttpStatus.OK);
    }


}
