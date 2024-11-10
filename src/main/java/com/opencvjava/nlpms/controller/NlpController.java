package com.opencvjava.nlpms.controller;

import com.opencvjava.nlpms.service.NlpService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/nlp")
@AllArgsConstructor
public class NlpController {

    private final  NlpService nlpService;

    @PostMapping("/extract")
    public String extractEntities(@RequestBody String text) {
        nlpService.extractEntities(text);
        return "Entity extraction completed!";
    }
}
