create table IF NOT EXISTS contact(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(255) not null,
    email varchar(255) not null,
    message varchar(1023) not null
);

CREATE TABLE IF NOT EXISTS account (
     id SERIAL PRIMARY KEY,
     login_id VARCHAR(255) unique , 
     name VARCHAR(255),
     password VARCHAR(255)

      );
      
create table IF NOT EXISTS contactuser(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(255) not null,
    email varchar(255) not null,
    message varchar(1023) not null
);

