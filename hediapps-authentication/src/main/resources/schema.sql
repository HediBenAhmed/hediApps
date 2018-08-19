drop table if exists authority;
CREATE TABLE authority (
  id  integer AUTO_INCREMENT,
  authority varchar(255),
  primary key (id)
);
drop table if exists credentials;
CREATE TABLE credentials (
  id  integer AUTO_INCREMENT,
  enabled boolean not null,
  username varchar(255) not null,
  password varchar(255) not null,
  version integer,
  account_non_expired boolean,
  account_non_locked boolean,
  credentials_non_expired boolean,
  primary key (id)
);
drop table if exists credentials_authorities;
CREATE TABLE credentials_authorities (
  credentials_id integer not null,
  authorities_id integer not null
);
