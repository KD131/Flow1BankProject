-- CREATE DATABASE ellerød_bank;
USE ellerød_bank;

/*
CREATE TABLE accounts(
-- timestamp
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
-- employee (0 = "no" | 1 = "yes")
	employee boolean NOT NULL DEFAULT 0,
-- bank_id
	bank_id int NOT NULL AUTO_INCREMENT,
-- name
	`name` varchar(80) NOT NULL,
-- city
	city varchar(80) NOT NULL DEFAULT "Blank",
-- account balance
    balance int NOT NULL  DEFAULT 0,
-- username
	username varchar(25) NOT NULL DEFAULT "user",
-- password
	`password` varchar(8) NOT NULL DEFAULT "0000",
    PRIMARY KEY (bank_id)
);
*/

-- INSERT INTO accounts (employee, `name`, city, username, `password`) VALUES (1, "Kris", "B-Town", "kris12", "wasspord");
-- INSERT INTO accounts (bank_id, `name`, city, balance) VALUES ("60982341", "Test User 1", "K-Town", "4007");
-- INSERT INTO accounts (bank_id, `name`, balance) VALUES ("98321732", "Test User 2", "11372");

/*
CREATE TABLE transactions(
-- id
	id int NOT NULL AUTO_INCREMENT,
-- bank_id
	bank_id int NOT NULL DEFAULT -1,
-- amount
	amount int NOT NULL,
-- timestamp
	`date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);
*/

-- UPDATE accounts SET balance = '3900' WHERE bank_id = '60982341';
-- INSERT INTO transactions (bank_id, amount) VALUES ('1', '5000');

 SELECT accounts.bank_id, `name`, city, balance, transactions.amount, transactions.`date` FROM accounts JOIN transactions ON transactions.bank_id = accounts.bank_id ORDER BY `created_at` ASC;