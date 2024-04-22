CREATE TABLE accounts(
     `account_id`        BIGINT       NOT NULL AUTO_INCREMENT COMMENT 'account ID',
     `user_name`         VARCHAR(20)  NOT NULL COMMENT 'user_name for login',
     `password`          VARCHAR(255) NOT NULL COMMENT 'password for login',
     `role`              VARCHAR(255) NOT NULL COMMENT 'role',
     `phone_number`      BIGINT       NOT NULL UNIQUE COMMENT 'phone_number',
     `name`              VARCHAR(255) NOT NULL COMMENT 'full_name or name',
     `age`               BIGINT       NOT NULL COMMENT 'age',
    PRIMARY KEY (account_id)
);

ALTER TABLE accounts ADD CONSTRAINT UNQ_PHONE_NUMBER UNIQUE (phone_number);

