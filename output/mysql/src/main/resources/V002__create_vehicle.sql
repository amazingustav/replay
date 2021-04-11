create table vehicle (
    id varchar(36) primary key,
    make varchar(30) not null,
    model varchar(30) not null,
    year integer not null,
    miles_amount integer default 0,
    created_at datetime not null,
    modified_at datetime
) engine = INNODB;

insert into vehicle values (uuid(), 'Honda', 'Accord', 2019, 45000, now(), null);
insert into vehicle values (uuid(), 'Mercedes Benz', 'Gla Class 4D 4WD 250', 2018, 50000, now(), null);
insert into vehicle values (uuid(), 'Chevrolet', 'Camaro 2D 2LT', 2017, 55000, now(), null);
