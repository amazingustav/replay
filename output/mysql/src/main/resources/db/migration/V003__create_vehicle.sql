CREATE TABLE vehicle (
    id VARCHAR(36) PRIMARY KEY,
    make VARCHAR(30) NOT NULL,
    model VARCHAR(30) NOT NULL,
    year INTEGER NOT NULL,
    miles_amount INTEGER NOT NULL,
    created_at DATETIME NOT NULL,
    modified_at DATETIME
) ENGINE = INNODB;

INSERT INTO vehicle VALUES (uuid(), 'Honda', 'Accord', 2019, 45000, now(), NULL);
INSERT INTO vehicle VALUES (uuid(), 'Mercedes Benz', 'Gla Class 4D 4WD 250', 2018, 50000, now(), NULL);
INSERT INTO vehicle VALUES (uuid(), 'Chevrolet', 'Camaro 2D 2LT', 2017, 55000, now(), NULL);
