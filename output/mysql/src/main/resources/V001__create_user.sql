create TABLE user (
    id varchar(36) primary key,
    email varchar(255) unique,
    name varchar(255) not null,
    created_at datetime not null,
    modified_at datetime
) engine = INNODB;

insert into user (id, email, name, created_at, modified_at) values (uuid(), 'john@doe', 'John Doe', now(), null)
