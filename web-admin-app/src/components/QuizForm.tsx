import React, { useState } from "react";
import { Card, CardContent } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";

const questions = [
    { id: 1, question: "What is the capital of France?", answer: "Paris" },
    { id: 2, question: "What is 2 + 2?", answer: "4" },
    { id: 3, question: "What is the color of the sky?", answer: "Blue" },
];

const QuizForm: React.FC = () => {
    const [answers, setAnswers] = useState<{ [key: number]: string }>({});
    const [submitted, setSubmitted] = useState(false);

    const handleChange = (id: number, value: string) => {
        setAnswers((prev) => ({ ...prev, [id]: value }));
    };

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        setSubmitted(true);
    };

    return (
        <div className="max-w-lg mx-auto p-4">
            <Card>
                <CardContent className="p-6">
                    <h2 className="text-xl font-bold mb-4">Quiz Form</h2>
                    <form onSubmit={handleSubmit} className="space-y-4">
                        {questions.map((q) => (
                            <div key={q.id} className="space-y-2">
                                <Label>{q.question}</Label>
                                <Input
                                    type="text"
                                    value={answers[q.id] || ""}
                                    onChange={(e) => handleChange(q.id, e.target.value)}
                                    disabled={submitted}
                                />
                            </div>
                        ))}
                        <Button type="submit" disabled={submitted}>Submit</Button>
                    </form>
                    {submitted && (
                        <div className="mt-4 p-4 bg-gray-100 rounded-lg">
                            <h3 className="font-bold">Results:</h3>
                            {questions.map((q) => (
                                <p key={q.id}>
                                    {q.question} - <span className={answers[q.id]?.toLowerCase() === q.answer.toLowerCase() ? "text-green-600" : "text-red-600"}>{answers[q.id] || "No Answer"}</span>
                                </p>
                            ))}
                        </div>
                    )}
                </CardContent>
            </Card>
        </div>
    );
};

export default QuizForm;
