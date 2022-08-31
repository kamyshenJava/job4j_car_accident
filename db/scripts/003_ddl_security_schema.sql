CREATE TABLE users (
   username text NOT NULL,
   password text NOT NULL,
   enabled boolean default true,
   PRIMARY KEY (username)
);

CREATE TABLE authorities (
    username text NOT NULL,
    authority text NOT NULL,
    FOREIGN KEY (username) REFERENCES users(username)
);