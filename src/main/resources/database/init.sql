CREATE TABLE IF NOT EXISTS ACCOUNTS (
                                        ID BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
                                        VERSION SMALLINT DEFAULT 0 NOT NULL,
                                        NUMB VARCHAR(24) NOT NULL,
                                        SORT VARCHAR(20) NOT NULL,
                                        CURRENCY VARCHAR(3) NOT NULL,
                                        AMOUNT DECIMAL NOT NULL
);

CREATE INDEX IF NOT EXISTS IDX_NUMB on ACCOUNTS (NUMB);

CREATE TABLE IF NOT EXISTS CARDS (
                                     ID BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
                                     VERSION SMALLINT DEFAULT 0 NOT NULL,
                                     ACCOUNT_ID BIGINT NOT NULL,
                                     NUMB VARCHAR(16) NOT NULL CONSTRAINT cardNumberIdx
                                         UNIQUE,
                                     SORT VARCHAR(10) NOT NULL,
                                     PAYMENT VARCHAR(10) NOT NULL,
                                     AMOUNT DECIMAL NOT NULL,
                                foreign key (ACCOUNT_ID) references ACCOUNTS (ID)
);