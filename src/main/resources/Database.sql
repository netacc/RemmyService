CREATE TABLE users(id mediumint not null AUTO_INCREMENT,
name varchar(128) default null,
initials varchar(3) default null,
PRIMARY KEY (id)
)

CREATE TABLE tasks (
  id MEDIUMINT NOT NULL AUTO_INCREMENT,
  doerId MEDIUMINT NOT NULL,
  doerName varchar(64) DEFAULT NULL,
  header varchar(64) DEFAULT NULL,
  text varchar(256) DEFAULT NULL,
  taskDate varchar(64) DEFAULT NULL,
  doerInitials varchar(64) DEFAULT NULL,
  status MEDIUMINT NOT NULL,
  PRIMARY KEY (id)
)

