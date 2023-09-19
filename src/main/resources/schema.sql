CREATE TABLE IF NOT EXISTS TRANSACTIONS (
    id              UUID            DEFAULT random_uuid() PRIMARY KEY,
    amount          DECIMAL(15, 4)  NOT NULL,
    `timestamp`     TIMESTAMP       NOT NULL,
    transactionDetails       VARCHAR(255)    NOT NULL,
    slogan          VARCHAR(255),
    receiving_user  VARCHAR(255)    NOT NULL
);