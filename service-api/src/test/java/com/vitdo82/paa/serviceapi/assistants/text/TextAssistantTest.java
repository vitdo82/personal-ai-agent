package com.vitdo82.paa.serviceapi.assistants.text;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaApi.Message;

@ExtendWith(MockitoExtension.class)
class TextAssistantTest {

  @Mock
  private OllamaApi ollamaApi;

  @InjectMocks
  private TextAssistant textAssistant;

  @Test
  void translate_Test() {
    OllamaApi.ChatResponse chat = mock(OllamaApi.ChatResponse.class);
    Message message = mock(Message.class);
    when(message.content()).thenReturn("Hello");
    when(chat.message()).thenReturn(message);
    when(ollamaApi.chat(any())).thenReturn(chat);

    String result = textAssistant.translate("Привет");
    assertThat(result.trim()).containsAnyOf("Hello", "Hello!", "hello", "hello!");
  }

  @Test
  void fixLanguage_Test() {
    OllamaApi.ChatResponse chat = mock(OllamaApi.ChatResponse.class);
    Message message = mock(Message.class);
    when(message.content()).thenReturn("Hello, how are you?");
    when(chat.message()).thenReturn(message);
    when(ollamaApi.chat(any())).thenReturn(chat);

    String result = textAssistant.correctText("Helo How are your!");
    assertThat(result.trim()).containsAnyOf("Hello! How are you?", "Hello, how are you?");
  }
}