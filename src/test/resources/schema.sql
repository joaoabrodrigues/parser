DROP TABLE log IF EXISTS;

CREATE TABLE log  (
    id 				NUMBER(19) NOT NULL AUTO_INCREMENT,
    log_date 		TIMESTAMP,
    ip 				VARCHAR(200),
    method 			VARCHAR(200),
    status_code 	VARCHAR(200),
    user_agent 		VARCHAR(200),
    PRIMARY KEY (id),
	UNIQUE (id)
);

DROP TABLE USER IF EXISTS;

CREATE TABLE USER  (
    username		VARCHAR(200) NOT NULL,
    password    	VARCHAR(200),
    PRIMARY KEY (username),
	UNIQUE (username)
);

INSERT INTO USER (username, password) values ('test', '$2a$10$aD6.qHz32bwxHbDC0r52l.1Uflz/ZZ/8AfFoQ4tkLTPFrDS.t24ZS');