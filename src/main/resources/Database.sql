CREATE TABLE users(
  id mediumint not null AUTO_INCREMENT,
  name varchar(128) default null,
  login varchar(128) default null,
  password varchar (128) default null,
  role smallint default 0,
  initials varchar (128) default null,
  PRIMARY KEY (id)
)

CREATE TABLE tasks (
  id MEDIUMINT NOT NULL AUTO_INCREMENT,
  doerId MEDIUMINT NOT NULL,
  doerName varchar (64) default null,
  header varchar(64) DEFAULT NULL,
  text varchar(256) DEFAULT NULL,
  taskDate DATETIME DEFAULT NULL,
  doerInitials varchar (64) default null,
  status smallint default 0,
  PRIMARY KEY (id)
)