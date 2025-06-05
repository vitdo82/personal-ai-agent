import React, { useState, useEffect } from "react";
import { Card, CardContent } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import QuestionComponent from "@/components/Question.tsx";

interface Answer {
    id: string;
    text: string;
    is_correct: boolean;
}

interface Question {
    id: string;
    question: string;
    enabled: boolean;
    type: string;
    answers: Answer[];
}

interface Quiz {
    id: string;
    name: string;
    description: string;
    questions: Question[];
}

const QuizForm: React.FC = () => {
    const [quiz, setQuiz] = useState<Quiz | null>(null);
    const [answers, setAnswers] = useState<{ [key: string]: string }>({});
    const [submitted, setSubmitted] = useState(false);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        fetch("http://localhost:8080/api/v1/quiz/quiz1")
            .then((res) => res.json())
            .then((data) => {
                setQuiz(data);
                setLoading(false);
            })
            .catch((error) => {
                console.error("Error fetching quiz:", error);
                setLoading(false);
            });
    }, []);

    const handleChange = (id: string, value: string) => {
        setAnswers((prev) => ({ ...prev, [id]: value }));
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setSubmitted(true);
        if (!quiz) return;

        const submissionData = {
            quizId: quiz.id,
            responses: Object.entries(answers).map(([questionId, answerText]) => ({
                questionId,
                answerText,
            })),
        };
        try {
            const response = await fetch("http://localhost:8080/api/v1/quiz/quiz1", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(submissionData),
            });
            if (!response.ok) {
                throw new Error("Failed to submit quiz");
            }
            console.log("Quiz submitted successfully");
        } catch (error) {
            console.error("Error submitting quiz:", error);
        }
    };

    if (loading) {
        return <p className="text-center">Loading quiz...</p>;
    }
    if (!quiz) {
        return <p className="text-center">No quiz available.</p>;
    }
    return (
        <div className="max-w-lg mx-auto p-4">
            <Card>
                <CardContent className="p-6">
                    <h2 className="text-xl font-bold mb-4">{quiz.name}</h2>
                    <p className="mb-4 text-gray-600">{quiz.description}</p>
                    <form onSubmit={handleSubmit} className="space-y-4">
                        {quiz.questions.map((q) => (
                            q.enabled && (
                                <QuestionComponent
                                    key={q.id}
                                    question={q}
                                    selectedAnswer={answers[q.id] || ""}
                                    onChange={handleChange}
                                    disabled={submitted}
                                />
                            )
                        ))}
                        <Button type="submit" disabled={submitted}>Submit</Button>
                    </form>
                    {/*{submitted && (
                        <div className="mt-4 p-4 bg-gray-100 rounded-lg">
                            <h3 className="font-bold">Results:</h3>
                            {quiz.questions.map((q) => (
                                q.enabled && (
                                    <p key={q.id}>
                                        {q.question} - <span className={q.answers.some(a => a.is_correct && a.text === answers[q.id]) ? "text-green-600" : "text-red-600"}>{answers[q.id] || "No Answer"}</span>
                                    </p>
                                )
                            ))}
                        </div>
                    )}*/}
                </CardContent>
            </Card>
        </div>
    );
};

export default QuizForm;
