CREATE TABLE accounts(
     `account_id`        BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'account ID',
     `user_name`         VARCHAR(20)  NOT NULL COMMENT 'user_name for login',
     `password`          VARCHAR(255) NOT NULL COMMENT 'password for login',
     `role`              VARCHAR(45)  NOT NULL COMMENT 'role',
     `phone_number`      VARCHAR(255) NOT NULL UNIQUE COMMENT 'phone_number',
     `name`              VARCHAR(255) NOT NULL COMMENT 'full_name or name',
     `age`               INT          NOT NULL COMMENT 'age',
     `created_at`        DATETIME(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT 'created time',
     `updated_at`        DATETIME(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT 'updated time'
);