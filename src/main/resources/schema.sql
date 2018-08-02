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