-- CREATE DATABASE ellerød_bank;
USE ellerød_bank;

/*
CREATE TABLE accounts(
-- Id becomes an auto-incrementing primary key
	id int NOT NULL AUTO_INCREMENT,
-- timestamp
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
-- employee (0 = "no" | 1 = "yes")
	employee boolean NOT NULL DEFAULT 0,
-- name
	`name` varchar(80) NOT NULL,
-- account balance
    balance int NOT NULL  DEFAULT 0,
-- username
	username varchar(25) NOT NULL DEFAULT "user",
-- password
	`password` varchar(8) NOT NULL DEFAULT "0000",
    PRIMARY KEY (id)
);
*/

-- INSERT INTO accounts (employee, `name`, username, `password`) VALUES (1, "Kris", "kris12", "wasspord");
-- INSERT INTO accounts (`name`, balance) VALUES ("Test User 1", "4007");

SELECT * FROM accounts;