CREATE TABLE loan (
    id VARCHAR(36) PRIMARY KEY,
    vehicle_id VARCHAR(36) NOT NULL,
    user_id VARCHAR(36) NOT NULL,
    lender_name VARCHAR(30) NOT NULL,
    paid_amount INTEGER NOT NULL,
    balance DECIMAL(10, 2) NOT NULL,
    created_at DATETIME NOT NULL,
    modified_at DATETIME,

    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (vehicle_id) REFERENCES vehicle (id),

    UNIQUE KEY uc_vehicle_id (vehicle_id)
) ENGINE = INNODB;

INSERT INTO loan VALUES (uuid(), (SELECT ID FROM vehicle LIMIT 1), (SELECT ID FROM user LIMIT 1), 'Santander Consumer USA', 54, 15000.00, now(), NULL);
INSERT INTO loan VALUES (uuid(), (SELECT ID FROM vehicle LIMIT 1,1), (SELECT ID FROM user LIMIT 1), 'Santander Consumer USA', 50, 18500.00, now(), NULL);
INSERT INTO loan VALUES (uuid(), (SELECT ID FROM vehicle LIMIT 2,2), (SELECT ID FROM user LIMIT 1), 'Santander Consumer USA', 32, 13700.00, now(), NULL);
