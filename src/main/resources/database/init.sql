CREATE TABLE IF NOT EXISTS ACCOUNTS (
                                        ID BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
                                        NUMB VARCHAR(24) NOT NULL,
                                        SORT VARCHAR(20) NOT NULL,
                                        CURRENCY VARCHAR(3) NOT NULL,
                                        AMOUNT DECIMAL NOT NULL
);
CREATE TABLE IF NOT EXISTS CARDS (
                                     ID BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
                                     ACCOUNTID BIGINT NOT NULL,
                                     NUMB VARCHAR(16) NOT NULL,
                                     SORT VARCHAR(10) NOT NULL,
                                     PAYMENT VARCHAR(10) NOT NULL,
                                     AMOUNT DECIMAL NOT NULL,
                                foreign key (ACCOUNTID) references ACCOUNTS (ID)
);