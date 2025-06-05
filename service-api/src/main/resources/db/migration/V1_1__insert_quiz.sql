-- Insert data into quiz_entity
DELETE FROM public.quiz_entity;
INSERT INTO public.quiz_entity (id, name, description, created_date, last_modified_date, version, created_by, last_modified_by)
VALUES
    ('quiz1', 'General Knowledge', 'A quiz about general knowledge', NOW(), NOW(), 1, 'admin', 'admin'),
    ('quiz2', 'Science Quiz', 'A quiz about science topics', NOW(), NOW(), 1, 'admin', 'admin');

-- Insert data into quiz_question_entity
DELETE FROM public.quiz_question_entity;
INSERT INTO public.quiz_question_entity (id, question, questions, enabled, created_date, last_modified_date, version, created_by, last_modified_by)
VALUES
    ('q1', 'What is the capital of France?', 'quiz1', TRUE, NOW(), NOW(), 1, 'admin', 'admin'),
    ('q2', 'What is 2 + 2?', 'quiz1', TRUE, NOW(), NOW(), 1, 'admin', 'admin'),
    ('q3', 'What planet is known as the Red Planet?', 'quiz2', TRUE, NOW(), NOW(), 1, 'admin', 'admin');

-- Insert data into quiz_answer_entity
DELETE FROM public.quiz_answer_entity;
INSERT INTO public.quiz_answer_entity (id, text, is_correct, created_date, last_modified_date, version, created_by, last_modified_by)
VALUES
    ('a1', 'Paris', TRUE, NOW(), NOW(), 1, 'admin', 'admin'),
    ('a2', 'London', FALSE, NOW(), NOW(), 1, 'admin', 'admin'),
    ('a3', '4', TRUE, NOW(), NOW(), 1, 'admin', 'admin'),
    ('a4', '5', FALSE, NOW(), NOW(), 1, 'admin', 'admin'),
    ('a5', 'Mars', TRUE, NOW(), NOW(), 1, 'admin', 'admin'),
    ('a6', 'Venus', FALSE, NOW(), NOW(), 1, 'admin', 'admin');

-- Insert data into quiz_question_entity_quiz_answer
DELETE FROM public.quiz_question_entity_quiz_answer;
INSERT INTO public.quiz_question_entity_quiz_answer (quiz_question_entity_id, quiz_answer_id)
VALUES
    ('q1', 'a1'),
    ('q1', 'a2'),
    ('q2', 'a3'),
    ('q2', 'a4'),
    ('q3', 'a5'),
    ('q3', 'a6');
