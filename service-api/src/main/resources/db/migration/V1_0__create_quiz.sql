create table public.quiz_entity (
    id                 varchar(255) not null primary key,
    created_date       timestamp(6) not null,
    last_modified_date timestamp(6) not null,
    version            bigint,
    created_by         varchar(255),
    description        varchar(255),
    last_modified_by   varchar(255),
    name               varchar(255)
);

alter table public.quiz_entity owner to myuser;
