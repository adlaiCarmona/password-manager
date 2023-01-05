DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Credentials;

CREATE TABLE IF NOT EXISTS Users
(
    id                  VARCHAR(60)  DEFAULT RANDOM_UUID() PRIMARY KEY,
--     settings_id         VARCHAR(60)  FOREIGN KEY REFERENCES Credentials(id),
    email               VARCHAR(256) NOT NULL UNIQUE,
    password            VARCHAR(256) NOT NULL,
    hashed_password     INT          NOT NULL,
    firstname           VARCHAR(64)  NOT NULL,
    lastname            VARCHAR(64)  NOT NULL,
    date_created        TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_login          TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    password_duration   INT          NOT NULL,
    is_deleted          BIT          NOT NULL
    );

CREATE TABLE IF NOT EXISTS Credentials
(
    id                  VARCHAR(60)  DEFAULT RANDOM_UUID() PRIMARY KEY,
    user_id             VARCHAR(60)  NOT NULL,
    username            VARCHAR(64)  NOT NULL,
    password            VARCHAR(256) NOT NULL,
    url                 VARCHAR(253) NOT NULL,
    expiration_date     TIMESTAMP    NOT NULL,
    is_deleted          BIT          DEFAULT FALSE,
    tags                VARCHAR(256)
    );

-- CREATE TABLE IF NOT EXISTS Settings
-- (
--     id                  VARCHAR(60) DEFAULT RANDOM_UUID() PRIMARY KEY,
--     user_id             VARCHAR(60) FOREIGN KEY REFERENCES Users(id),
--     password_duration   INT         NOT NULL
--     );