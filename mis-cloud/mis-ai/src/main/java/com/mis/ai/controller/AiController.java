package com.mis.ai.controller;

import com.mis.ai.service.AiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    @Autowired
    private AiService aiService;

    @PostMapping("/chat")
    public String chat(@RequestBody String message) {
        return aiService.chat(message);
    }

    @PostMapping("/switch/{provider}")
    public void switchProvider(@PathVariable String provider) {
        aiService.switchAiProvider(provider);
    }
} 