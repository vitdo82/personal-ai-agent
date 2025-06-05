package com.vitdo82.paa.serviceapi.quize.resource;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitdo82.paa.serviceapi.quize.repository.projection.QuizName;
import com.vitdo82.paa.serviceapi.quize.service.QuizService;
import com.vitdo82.paa.serviceapi.quize.service.QuizeNotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/quiz")
@RequiredArgsConstructor
public class QuizResource {

    private final QuizService quizService;
    private final ModelMapper mapper;

    @Operation(summary = "Get quiz by id", description = "Returns a quiz by id")
    @GetMapping("/{id}")
    public QuizeDTO.QuizResponse getQuiz(@PathVariable("id") String id) throws QuizeNotFoundException {
        return quizService.getQuiz(id)
                .map(q -> mapper.map(q, QuizeDTO.QuizResponse.class))
                .orElseThrow(() -> new QuizeNotFoundException());

//         return quizService.getQuiz(id)
//                 .map(q -> new QuizeDTO.QuizResponse(
//                                 q.getId(),
//                                 q.getName(),
//                                 q.getDescription(),
//                                 q.getQuestions().stream().map(qq -> new QuizeDTO.QuizQuestion(
//                                                 qq.getId(),
//                                                 qq.getQuestion(),
//                                                 qq.isEnabled(),
//                                                 QuizeDTO.QuestionType.SINGLE,
//                                                 qq.getQuizAnswer().stream().map(a -> new QuizeDTO.QuizAnswer(
//                                                         a.getId(),
//                                                         a.getText(),
//                                                         a.isCorrect()
//                                                 )).toList()
//                                         )
//                                 ).toList()
//                         )
//                 )
//                 .orElse(null);
    }

    @Operation(summary = "Get all quizzes", description = "Returns a list of quizzes")
    @GetMapping
    public List<QuizName> getQuizzes() {
        List<QuizName> quizNames = quizService.getQuizNames();
        return quizNames;
    }

    @Operation(summary = "Submit quiz", description = "Submit a quiz and return result")
    @PostMapping
    public QuizeDTO.QuizResult submitQuiz(
                @RequestBody QuizeDTO.QuizCreateRequest createRequest
        ) {
                String quizId = quizService.buildQuize(
                        createRequest.name(), 
                        createRequest.questionCount(),
                        createRequest.domain()
                );
        return new QuizeDTO.QuizResult(quizId,"Test name","Success"
        );
    }
}
