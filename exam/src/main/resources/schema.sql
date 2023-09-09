 CREATE TABLE IF NOT EXISTS Accounts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    document_number VARCHAR(20) UNIQUE
);

CREATE TABLE IF NOT EXISTS Operation_Types (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255) UNIQUE
);

CREATE TABLE IF NOT EXISTS Transactions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_id BIGINT,
    operation_type_id BIGINT,
    amount DECIMAL(10, 2),
    event_date TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES Accounts(id),
    FOREIGN KEY (operation_type_id) REFERENCES Operation_Types(id)
);
