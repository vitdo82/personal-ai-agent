package com.vitdo82.paa.serviceapi.assistants.text;

import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Service
@ConditionalOnProperty(prefix = "app.assistans", name = "enabled", havingValue = "true", matchIfMissing = true)
public class TextAssistant {

    private final OllamaApi ollamaApi;

    public String correctText(String text) {
        return correctText(text, Language.ENGLISH);
    }

    public String correctText(String text, Language language) {
        language = language == null ? Language.ENGLISH : language;

        OllamaApi.ChatRequest chatRequest = OllamaApi.ChatRequest.builder("deepseek-r1:8b")
                .stream(false)
                .messages(List.of(
                        OllamaApi.Message
                                .builder(OllamaApi.Message.Role.SYSTEM)
                                .content("You are a %s teacher. Fix sentence text. Write only correct answer without description and additional worlds: \n"
                                        .formatted(language.getName()))
                                .build(),
                        OllamaApi.Message
                                .builder(OllamaApi.Message.Role.USER)
                                .content(text)
                                .build()

                )).build();
        OllamaApi.ChatResponse response = ollamaApi.chat(chatRequest);
        return response.message().content();
    }

    public String translate(String text) {
        return translate(text, Language.ENGLISH);
    }

    public String translate(String text, Language language) {
        language = language == null ? Language.ENGLISH : language;

        OllamaApi.ChatRequest chatRequest = OllamaApi.ChatRequest.builder("deepseek-r1:8b")
                .stream(false)
                .messages(List.of(
                        OllamaApi.Message
                                .builder(OllamaApi.Message.Role.SYSTEM)
                                .content("You are a translater. You are working in an IT. Just translate without comments next text to %s language: \n"
                                        .formatted(language.getName()))
                                .build(),
                        OllamaApi.Message
                                .builder(OllamaApi.Message.Role.USER)
                                .content(text)
                                .build()

                )).build();
        OllamaApi.ChatResponse response = ollamaApi.chat(chatRequest);
        return response.message().content();
    }
}
