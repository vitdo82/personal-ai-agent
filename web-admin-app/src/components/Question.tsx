import React from "react";
import { Label } from "@/components/ui/label";

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

interface QuestionProps {
    question: Question;
    selectedAnswer: string;
    onChange: (id: string, value: string) => void;
    disabled: boolean;
}

const QuestionComponent: React.FC<QuestionProps> = ({ question, selectedAnswer, onChange, disabled }) => {
    return (
        <div className="space-y-2">
            <Label>{question.question}</Label>
            <div className="space-y-2">
                {question.answers.map((a) => (
                    <div key={a.id} className="flex items-center space-x-2">
                        <input
                            type="radio"
                            name={question.id}
                            value={a.text}
                            checked={selectedAnswer === a.text}
                            onChange={(e) => onChange(question.id, e.target.value)}
                            disabled={disabled}
                        />
                        <Label>{a.text}</Label>
                    </div>
                ))}
            </div>
        </div>
    );
};


export default QuestionComponent;
