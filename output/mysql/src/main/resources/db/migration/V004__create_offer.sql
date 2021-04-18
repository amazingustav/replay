CREATE TABLE offer (
    id VARCHAR(36) PRIMARY KEY,
    monthly_payment_amount DECIMAL(10, 2) NOT NULL,
    annual_percentage_rate DOUBLE(5, 2) NOT NULL,
    payment_amount INTEGER NOT NULL,
    created_at DATETIME NOT NULL,
    modified_at DATETIME
) ENGINE = INNODB;

INSERT INTO offer VALUES (uuid(), 377.50, 14.2, 72, now(), NULL);
INSERT INTO offer VALUES (uuid(), 486.72, 13.6, 60, now(), NULL);
INSERT INTO offer VALUES (uuid(), 527.40, 15.8, 72, now(), NULL);
