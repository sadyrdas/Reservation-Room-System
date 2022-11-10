CREATE TABLE users
(
    id        integer      NOT NULL PRIMARY KEY,
    email     varchar(255) not null,
    firstname varchar(255) not null,
    lastname  varchar(255) not null,
    password  varchar(255) not null,
    money     double PRECISION      not null
);
CREATE TABLE Admin
(
    id        integer      NOT NULL PRIMARY KEY,
    email     varchar(255) not null,
    firstname varchar(255) not null,
    lastname  varchar(255) not null,
    password  varchar(255) not null
);
CREATE TABLE StudyRoom
(
    id          integer      NOT NULL PRIMARY KEY,
    capacity    integer      not null,
    price       double PRECISION       not null,
    reservation varchar(255) not null
);

CREATE TABLE Slot
(
    id        integer      NOT NULL PRIMARY KEY,
    available BIT          NOT NULL,
    day       varchar(255) NOT NULL,
    CONSTRAINT slot_fk_day FOREIGN KEY (day) references Day (name)
);
CREATE TABLE Day
(
    id   integer      NOT NULL PRIMARY KEY,
    name varchar(255) NOT NULL,
    week varchar (255) NOT NULL,
    CONSTRAINT day_fk_week FOREIGN KEY (week) references Week (numberOfWeek)
);

CREATE TABLE Week
(
    id integer NOT NULL PRIMARY KEY ,
    numberOfWeek integer NOT NULL


);

CREATE TABLE Payment
(
    id            integer      NOT NULL PRIMARY KEY,
    paymentMethod varchar(255) NOT NULL,
    status        BIT          NOT NULL
);