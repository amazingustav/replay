create TABLE user (
    id uuid primary key,
    email varchar(255) unique,
    name varchar(255) not null
) engine  = INNODB;
