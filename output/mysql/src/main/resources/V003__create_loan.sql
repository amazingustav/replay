create table loan (
    id varchar(36) primary key,
    vehicle_id varchar(36),
    user_id varchar(36),
    created_at datetime,
    modified_at datetime,

    FOREIGN KEY (user_id) references user (id),
    FOREIGN KEY (vehicle_id) references vehicle (id),

    UNIQUE KEY uc_vehicle_id (vehicle_id)
) engine = INNODB;

insert into vehicle values (uuid(), (select id from vehicle limit 1), (select id from user limit 1), now(), null);
insert into vehicle values (uuid(), (select id from vehicle limit 2 offset 1), (select id from user limit 2 offset 1), now(), null);
insert into vehicle values (uuid(), (select id from vehicle limit 3 offset 2), (select id from user limit 3 offset 2), now(), null);
