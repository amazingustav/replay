CREATE TABLE offer (
    id VARCHAR(36) PRIMARY KEY,
    annual_percentage_rate DECIMAL(10, 2) NOT NULL,
    monthly_payment_amount DECIMAL(10, 2) NOT NULL,
    payment_amount INTEGER NOT NULL,
    created_at DATETIME NOT NULL,
    modified_at DATETIME
) ENGINE = INNODB;

INSERT INTO offer VALUES (uuid(), 14.2, 377.50, 72, now(), NULL);
INSERT INTO offer VALUES (uuid(), 13.6, 486.72, 60, now(), NULL);
INSERT INTO offer VALUES (uuid(), 15.8, 527.40, 72, now(), NULL);
