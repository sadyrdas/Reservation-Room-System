DROP TABLE IF EXISTS users, slot, studyroom, user_role CASCADE;

CREATE TABLE roles
(
    id   SERIAL  NOT NULL PRIMARY KEY,
    name VARCHAR(255)
);


CREATE TABLE users
(
    id         SERIAL   primary key                NOT NULL,
    email      VARCHAR(255)         unique     NOT NULL,
    first_name VARCHAR(255)              NOT NULL,
    last_name  VARCHAR(255)              NOT NULL,
    password   VARCHAR(255)              NOT NULL,
    money      DOUBLE PRECISION          NOT NULL

);



CREATE TABLE StudyRoom
(
    id          SERIAL      NOT NULL  PRIMARY KEY ,
    capacity    integer      not null,
    price integer not null ,
    isAvailable boolean not null
);

CREATE TABLE Day
(
    id   SERIAL      NOT NULL PRIMARY KEY,
    posting_date DATE not null unique

);

CREATE TABLE Slot
(
    id        SERIAL     NOT NULL PRIMARY KEY,
    start     varchar(255) NOT NULL,
    finish    varchar(255) NOT NULL,
    price double precision not null ,
    user_id integer ,
    day integer ,
    studyroom_id integer  ,
    paid boolean not null ,

    CONSTRAINT slot_fk_day foreign key (day) references Day (id)  ,
    CONSTRAINT slot_fk_studyroom foreign key (studyroom_id) references StudyRoom (id) ,
    constraint slot_fk_users foreign key (user_id) references users (id)
);

CREATE TABLE User_Role
(
    user_id integer not null primary key ,
    role_id integer not null,
    constraint fk_user foreign key (user_id) references users (id),
    constraint fk_role foreign key (role_id) references roles (id)
);
