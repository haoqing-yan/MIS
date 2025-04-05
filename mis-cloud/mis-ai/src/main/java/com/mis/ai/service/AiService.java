package com.mis.ai.service;

public interface AiService {
    String chat(String message);
    void switchAiProvider(String provider);
} 