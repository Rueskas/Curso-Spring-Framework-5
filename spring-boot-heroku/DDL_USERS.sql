DROP TABLE IF EXISTS AUTHORITIES;
DROP TABLE IF EXISTS USERS;

CREATE TABLE users(
	id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    username VARCHAR(45) NOT NULL UNIQUE,
    password VARCHAR(60) NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE authorities(
	id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	user_id INT,
    CONSTRAINT FK_AUTHORITIES_USER FOREIGN KEY (user_id) REFERENCES users(id),
    authority VARCHAR(20) NOT NULL,
    UNIQUE (user_id, authority)
);

INSERT INTO users(username, password) VALUES('admin', '$2a$10$dsTo5h431M3Xff6aLdCkK.N.FgKw84dGuELrSwm.BZtcQd/TpgpB.');
INSERT INTO users(username,password) VALUES('sergio', '$2a$10$jVCrQDvxjgreny4d45CUGOOXkDs3qQrQHd5LFSklVT.dPcZYNieK6');

INSERT INTO authorities(user_id, authority) VALUES(1, 'ROLE_USER');
INSERT INTO authorities(user_id, authority) VALUES(1, 'ROLE_ADMIN');
INSERT INTO authorities(user_id, authority) VALUES(2, 'ROLE_USER');