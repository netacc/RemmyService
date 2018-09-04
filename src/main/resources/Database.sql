CREATE TABLE users(id mediumint not null AUTO_INCREMENT,
name varchar(128) default null,
PRIMARY KEY (id)
)

CREATE TABLE tasks (
  id MEDIUMINT NOT NULL AUTO_INCREMENT,
  doerId MEDIUMINT NOT NULL,
  header varchar(64) DEFAULT NULL,
  text varchar(256) DEFAULT NULL,
  taskDate DATETIME DEFAULT NULL,
  PRIMARY KEY (id)
) 