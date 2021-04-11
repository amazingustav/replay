create table vehicle (
    uuid uuid primary key,
    make varchar(100) not null,
    model varchar(100) not null,
    year integer,
    miles_amount integer default 0
) engine  = INNODB;
