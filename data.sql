DROP TABLE IF EXISTS users, roles, day, slot, studyroom CASCADE;

CREATE TABLE roles
(
    id   SERIAL  NOT NULL PRIMARY KEY,
    name VARCHAR(255)
);


CREATE TABLE users
(
    id         SERIAL PRIMARY KEY                      NOT NULL,
    email      VARCHAR(255) UNIQUE                      NOT NULL,
    first_name VARCHAR(255)                             NOT NULL,
    last_name  VARCHAR(255)                             NOT NULL,
    password   VARCHAR(255)                             NOT NULL,
    money      DOUBLE PRECISION                         NOT NULL,
    role_id integer not null,
    CONSTRAINT users_fk_roles foreign key (role_id) references roles (id)
);



CREATE TABLE StudyRoom
(
    id          SERIAL      NOT NULL  PRIMARY KEY ,
    capacity    integer      not null,
    reservation integer unique ,
    isAvailable boolean not null,
    constraint studyroom_fk_slot foreign key (reservation) references slot(id)
);


CREATE TABLE Slot
(
    id        SERIAL     NOT NULL PRIMARY KEY,
    available Boolean      NOT NULL,
    start     varchar(255) NOT NULL,
    finish    varchar(255) NOT NULL,
    price double precision not null ,
    user_email integer ,
    day integer not null,
    studyroom_id integer not null ,
    paid boolean not null ,
    CONSTRAINT slot_fk_day foreign key (day) references Day (id),
--     CONSTRAINT slot_fk_studyroom foreign key (studyroom_id) references StudyRoom (id),
    constraint slot_fk_users foreign key (user_email) references users (id)
);
CREATE TABLE Day
(
    id   SERIAL      NOT NULL PRIMARY KEY,
    posting_date DATE not null unique
);

CREATE TABLE Studyroom_Slot
(
    studyroom_id integer not null primary key ,
    slot_id integer not null unique,
    constraint fk_slot foreign key (slot_id) references Slot (id),
    constraint fk_studyroom foreign key (studyroom_id) references studyroom (id)
);
alter table slot add constraint fk_studyroom_slot foreign key (studyroom_id) references StudyRoom (id);
alter table slot alter column user_email drop not null;