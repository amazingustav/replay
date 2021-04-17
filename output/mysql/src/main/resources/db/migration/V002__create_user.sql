CREATE TABLE user (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    created_at DATETIME NOT NULL,
    modified_at DATETIME
) ENGINE = INNODB;

INSERT INTO user (id, name, created_at, modified_at) VALUES (uuid(), 'John Doe', now(), NULL);
