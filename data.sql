CREATE TABLE "user" (
    id integer NOT NULL,
    email character varying(255) not null,
    firstname character varying(255) not null,
    lastname character varying(255) not null,
    password character varying(255) not null,
    money double precision
)