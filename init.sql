CREATE USER docker;
CREATE DATABASE users;
ALTER USER docker WITH encrypted PASSWORD 'users';
GRANT ALL PRIVILEGES ON DATABASE users TO docker;

CREATE TABLE public.users (
    id int PRIMARY KEY,
    first_name varchar(255),
    last_name varchar(255),
    email varchar(255)
);
ALTER TABLE public.users
    OWNER to docker;