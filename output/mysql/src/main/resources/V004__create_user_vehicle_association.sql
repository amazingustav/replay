create table user_vehicle_association (
    user_id uuid,
    vehicle_id uuid,
    PRIMARY KEY (user_id, vehicle_id)
) engine  = INNODB;

alter table user_vehicle_association add foreign key (user_id) references user (id);

alter table user_vehicle_association add foreign key (vehicle_id) references vehicle (id);
