drop table if exists reporting_tasks ;
drop table  if exists reports ;
drop table  if exists databases ;


CREATE TABLE if not exists databases(
   id INT NOT NULL,
   name varchar(40000) NOT NULL,
   url varchar(40000) NOT NULL,
   user varchar(40000) NOT NULL,
   password varchar(40000) NOT NULL
);

CREATE TABLE if not exists reports(
   id INT NOT NULL,
   query varchar(40000) NOT NULL,
   name varchar(40000) NOT NULL,
   database_id varchar(40000) NOT NULL,
  constraint database_FK foreign key (database_id)
	references databases(id)
   );

CREATE TABLE if not exists reporting_tasks(
   id INT NOT NULL,
   report_id INT NOT NULL,
   resource_link varchar(40000) ,
   status varchar(40000) NOT NULL,
   constraint report_FK foreign key (report_id)
	references reports(id)
);
DROP SEQUENCE IF EXISTS HIBERNATE_SEQUENCE;
CREATE SEQUENCE HIBERNATE_SEQUENCE;





