package com.vitdo82.paa.serviceapi.assistants.text;

public enum Language {

    ENGLISH("English"),
    RUSSIAN("Russian"),
    ;

    private final String name;

    Language(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
