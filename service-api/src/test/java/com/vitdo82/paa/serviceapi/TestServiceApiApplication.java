package com.vitdo82.paa.serviceapi;

import org.springframework.boot.SpringApplication;

public class TestServiceApiApplication {

    public static void main(String[] args) {
        SpringApplication.from(ServiceApiApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
