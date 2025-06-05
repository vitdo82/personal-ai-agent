package com.vitdo82.paa.serviceapi.quize.resource;

import java.net.URL;
import java.util.List;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.vitdo82.paa.serviceapi.quize.repository.QuizEntity;
import com.vitdo82.paa.serviceapi.quize.service.JsonQuizBuilder;
import com.vitdo82.paa.serviceapi.quize.service.QuizService;

import reactor.core.publisher.Mono;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WithMockUser
@Testcontainers
class QuizResourceTest {

    @Autowired
    private JsonQuizBuilder jsonQuizBuilder;
    @Autowired
    private QuizService quizService;

    // @Container
    // static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine")
    //     .withDatabaseName("mydatabase-test")
    //     .withUsername("test")
    //     .withPassword("test");

    // @DynamicPropertySource
    // static void configureProperties(DynamicPropertyRegistry registry) {
    //     registry.add("spring.datasource.url", postgres::getJdbcUrl);
    //     registry.add("spring.datasource.username", postgres::getUsername);
    //     registry.add("spring.datasource.password", postgres::getPassword);
    // }

    @BeforeAll
    static void beforeAll(@Autowired Flyway flyway) {
        flyway.migrate();
    }

    @Autowired
    private WebTestClient webTestClient;

    // @Test
    void getQuiz() throws Exception {
        URL url = getClass().getClassLoader().getResource("data/import.json");
        List<QuizEntity> quizz = jsonQuizBuilder.buildQuizz(url);
        List<String> ids = quizService.save(quizz);
        String firstId = ids.getFirst();

        webTestClient.get()
            .uri("/api/v1/quiz/" + firstId)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.id").isEqualTo(firstId);
    }

    @Test
    void getQuizzes() {
        webTestClient.get()
            .uri("/api/v1/quiz")
            .exchange()
            .expectStatus().isOk();
    }

    @Test
    void createQuizz() {
        QuizeDTO.QuizCreateRequest createRequest = new QuizeDTO.QuizCreateRequest("TestName", 5, "medicine");
        webTestClient.post()
            .uri("/api/v1/quiz")
            .body(Mono.just(createRequest), QuizeDTO.QuizCreateRequest.class)
            .exchange()
            .expectStatus().isOk();
            // .expectBody()
            // .jsonPath("$.quiz_id").exists()
            // .jsonPath("$.quiz_name").isEqualTo("Test name");
    }
}
