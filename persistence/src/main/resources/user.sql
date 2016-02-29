  drop table if exists authority;
  drop table if exists users;

  create table users (
	username             	varchar(255) not null primary key,
    	password               	varchar(255) not null);

  create table authority (
	id                  	bigint not null auto_increment,
	username            	varchar(255),
	role			varchar(255),
  constraint pk_authority primary key (id));

  alter table authority add constraint fk_authority_users foreign key(username) references users(username) on delete CASCADE on update restrict;
  create index ix_role_username on authority (username,role);

  INSERT INTO users(username,password) VALUES ('root','$2a$04$FWHb83NzSsXlGowiIogv9.R46Ws.lIUCq9XhZNYXfFNICZ4x7HfKG');
  INSERT INTO users(username,password) VALUES ('admin','$2a$04$wnAPJZJL1xDxJji24UnQcOuaJTHKEMPNbSY2C8I4oIbpFQ6qMRACq');
  INSERT INTO users(username,password) VALUES ('user','$2a$04$rIMwkSS7IF2Fb7baaWUTdOrqPbliWq2cOB1CVjFl5Q1/elVxKVx4u');

  INSERT INTO authority (username, role) VALUES ('root', 'USER');
  INSERT INTO authority (username, role) VALUES ('root', 'ADMIN');
  INSERT INTO authority (username, role) VALUES ('root', 'SUPER_ADMIN');
  INSERT INTO authority (username, role) VALUES ('admin', 'USER');
  INSERT INTO authority (username, role) VALUES ('admin', 'ADMIN');
  INSERT INTO authority (username, role) VALUES ('user', 'USER');

	