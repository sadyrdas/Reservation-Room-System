DROP TABLE IF EXISTS users, roles, user_roles, payment;

CREATE TABLE roles
(
    id   INTEGER  NOT NULL PRIMARY KEY,
    name VARCHAR(255)
);


CREATE TABLE users
(
    id         INTEGER PRIMARY KEY                      NOT NULL,
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
    id          integer      NOT NULL PRIMARY KEY,
    capacity    integer      not null,
    price       double PRECISION       not null,
    reservation integer not null unique ,
    constraint studyroom_fk_slot foreign key (reservation) references slot(id)
);


CREATE TABLE Slot
(
    id        integer      NOT NULL PRIMARY KEY,
    available Boolean      NOT NULL,
    start     varchar(255) NOT NULL,
    finish    varchar(255) NOT NULL,
    price double precision not null ,
    user_email varchar(255) not null ,
    day integer not null,
    studyroom_id integer not null ,
    paid boolean not null ,
    CONSTRAINT slot_fk_day foreign key (day) references Day (id),
    CONSTRAINT slot_fk_studyroom foreign key (studyroom_id) references StudyRoom (id),
    constraint slot_fk_users foreign key (user_email) references users (email)
);
CREATE TABLE Day
(
    id   integer      NOT NULL PRIMARY KEY,
    posting_date DATE not null unique
);



