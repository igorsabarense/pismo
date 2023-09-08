 CREATE TABLE IF NOT EXISTS Accounts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    documentNumber BIGINT
);

CREATE TABLE IF NOT EXISTS Operation_Types (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Transactions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    accountId BIGINT,
    operationTypeId BIGINT,
    amount DECIMAL(10, 2),
    eventDate TIMESTAMP,
    FOREIGN KEY (accountId) REFERENCES Accounts(id),
    FOREIGN KEY (operationTypeId) REFERENCES Operation_Types(id)
);
