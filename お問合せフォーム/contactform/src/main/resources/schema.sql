create table IF NOT EXISTS contact(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(255) not null,
    email varchar(255) not null,
    message varchar(1023) not null
);