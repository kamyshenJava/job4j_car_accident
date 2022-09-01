CREATE TABLE authorities (
    id serial primary key,
    authority text NOT NULL unique
);

CREATE TABLE users (
    id serial primary key,
    username text NOT NULL unique,
    password text NOT NULL,
    enabled boolean default true,
    authority_id int not null references authorities(id)
);