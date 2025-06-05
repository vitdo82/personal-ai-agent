package com.vitdo82.paa.serviceapi.assistants.text;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TextAssistantTest {

    @Test
    void translate_Test() {
        TextAssistant textAssistant = new TextAssistant();
        String result = textAssistant.translate("Привет");
        assertThat(result.trim()).containsAnyOf("Hello", "Hello!", "hello", "hello!");
    }

    @Test
    void fixLanguage_Test() {
        TextAssistant textAssistant = new TextAssistant();
        String result = textAssistant.correctText("Helo How are your!");
        assertThat(result.trim()).containsAnyOf("Hello! How are you?", "Hello, how are you?");
    }
}