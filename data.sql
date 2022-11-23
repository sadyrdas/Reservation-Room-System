DROP TABLE IF EXISTS users, roles, user_roles, payment;

CREATE TABLE user_roles
(
    role_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    CONSTRAINT pk_user_roles PRIMARY KEY (role_id, user_id)
);

CREATE TABLE roles
(
    id   INTEGER  NOT NULL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE daysOfWeek
(
    number integer not null unique ,
    name varchar(255) unique
);


CREATE TABLE users
(
    id         INTEGER PRIMARY KEY                      NOT NULL,
    email      VARCHAR(255) UNIQUE                      NOT NULL,
    first_name VARCHAR(255)                             NOT NULL,
    last_name  VARCHAR(255)                             NOT NULL,
    password   VARCHAR(255)                             NOT NULL,
    money      DOUBLE PRECISION                         NOT NULL
--     CONSTRAINT users_fk_payment foreign key (payment) references Payment (id)
);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_user FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_user_role FOREIGN KEY (role_id) REFERENCES roles (id);
CREATE TABLE roles
(
    id   INTEGER  NOT NULL PRIMARY KEY,
    name VARCHAR(255)
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
    available Boolean      NOT NULL,
    start     varchar(255) NOT NULL,
    finish    varchar(255) NOT NULL ,
    amountOfSlotForDay integer NOT NULL ,
    num_of_day integer NOT NULL  ,
    CONSTRAINT slot_fk_day FOREIGN KEY (num_of_day) references Day (number)
);
CREATE TABLE Day
(
    id   integer      NOT NULL PRIMARY KEY,
    name varchar(255) NOT NULL,
    week varchar (255) NOT NULL,
    amountOfSlot integer NOT NULL ,
    number integer NOT NULL ,
    CONSTRAINT day_fk_week FOREIGN KEY (week) references Week (numberOfWeek),
    CONSTRAINT day_fk_slot FOREIGN KEY (amountOfSlot) references Slot (amountOfSlotForDay)
);

CREATE TABLE Week
(
    id integer NOT NULL PRIMARY KEY ,
    numberOfWeek integer NOT NULL UNIQUE


);

CREATE TABLE Payment
(
    id            integer      NOT NULL PRIMARY KEY,
    paymentMethod varchar(255) NOT NULL,
    status        boolean         NOT NULL,
    moneyToPay    integer not null,
    userId        integer not null ,
    CONSTRAINT payment_fk_users FOREIGN KEY (userId) references users (id)
);

