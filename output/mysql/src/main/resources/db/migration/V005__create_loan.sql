CREATE TABLE loan (
    id VARCHAR(36) PRIMARY KEY,
    vehicle_id VARCHAR(36) NOT NULL,
    user_id VARCHAR(36) NOT NULL,
    offer_id VARCHAR(36) NOT NULL,
    lender_name VARCHAR(30) NOT NULL,
    paid_amount INTEGER NOT NULL,
    balance DECIMAL(10, 2) NOT NULL,
    created_at DATETIME NOT NULL,
    modified_at DATETIME,

    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (vehicle_id) REFERENCES vehicle (id),
    FOREIGN KEY (offer_id) REFERENCES offer (id),

    UNIQUE KEY uc_vehicle_id (vehicle_id)
) ENGINE = INNODB;

INSERT INTO loan VALUES (uuid(), (SELECT ID FROM vehicle LIMIT 1), (SELECT ID FROM user LIMIT 1), (SELECT ID FROM offer LIMIT 1), 'The Big Bank', 54, 15000.00, now(), NULL);
INSERT INTO loan VALUES (uuid(), (SELECT ID FROM vehicle LIMIT 1,1), (SELECT ID FROM user LIMIT 1), (SELECT ID FROM offer LIMIT 1,1), 'The Credit Union', 50, 18500.00, now(), NULL);
INSERT INTO loan VALUES (uuid(), (SELECT ID FROM vehicle LIMIT 2,2), (SELECT ID FROM user LIMIT 1), (SELECT ID FROM offer LIMIT 2,2), 'The Auto-Lender', 32, 13700.00, now(), NULL);
