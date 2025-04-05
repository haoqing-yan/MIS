package com.mis.ai.service.impl;

import com.mis.ai.service.AiService;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AiServiceImpl implements AiService {

    private ChatClient currentChatClient;

    @Autowired
    @Qualifier("openAiChatClient")
    private ChatClient openAiChatClient;

    @Autowired
    @Qualifier("ollamaChatClient")
    private ChatClient ollamaChatClient;

    public AiServiceImpl() {
        this.currentChatClient = openAiChatClient;
    }

    @Override
    public String chat(String message) {
        Prompt prompt = new Prompt(message);
        ChatResponse response = currentChatClient.call(prompt);
        return response.getResult().getOutput().getContent();
    }

    @Override
    public void switchAiProvider(String provider) {
        if ("ollama".equalsIgnoreCase(provider)) {
            currentChatClient = ollamaChatClient;
        } else {
            currentChatClient = openAiChatClient;
        }
    }
} 