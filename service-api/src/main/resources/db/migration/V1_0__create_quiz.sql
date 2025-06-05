
create table IF NOT EXISTS public.quiz_answer_entity (
    is_correct         boolean      not null,
    created_date       timestamp(6) not null,
    last_modified_date timestamp(6) not null,
    version            bigint,
    created_by         varchar(255),
    id                 varchar(255) not null
        primary key,
    last_modified_by   varchar(255),
    text               varchar(255)
);

create table IF NOT EXISTS public.quiz_entity (
    created_date       timestamp(6) not null,
    last_modified_date timestamp(6) not null,
    version            bigint,
    created_by         varchar(255),
    description        varchar(255),
    id                 varchar(255) not null
        primary key,
    last_modified_by   varchar(255),
    name               varchar(255)
);

create table IF NOT EXISTS public.quiz_question_entity (
    enabled            boolean      not null,
    created_date       timestamp(6) not null,
    last_modified_date timestamp(6) not null,
    version            bigint,
    created_by         varchar(255),
    id                 varchar(255) not null
        primary key,
    last_modified_by   varchar(255),
    question           varchar(255),
    questions          varchar(255) not null
        constraint fkb071n4spw7mh695dyd74tnctt
            references public.quiz_entity
);

create table IF NOT EXISTS public.quiz_question_entity_quiz_answer (
    quiz_answer_id          varchar(255) not null
        unique
        constraint fk9i2ptolfufjpiuardfn0w47ed
            references public.quiz_answer_entity,
    quiz_question_entity_id varchar(255) not null
        constraint fkanoy9ewue6e43d9dotox7v8v0
            references public.quiz_question_entity
);
