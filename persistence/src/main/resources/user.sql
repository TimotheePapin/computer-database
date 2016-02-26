  drop table if exists user;
  drop table if exists authority;

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

  INSERT INTO authority (username, role) VALUES ('root', 'ROLE_USER');
  INSERT INTO authority (username, role) VALUES ('root', 'ROLE_ADMIN');
  INSERT INTO authority (username, role) VALUES ('root', 'ROLE_SUPER_ADMIN');

	