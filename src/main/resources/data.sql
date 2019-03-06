delete from reporting_tasks;
delete from reports;
delete from databases;

insert into databases values ((select nextval('HIBERNATE_SEQUENCE') FROM DUAL),'my_db','jdbc:mysql://localhost:3306/split_me','split_me','password');
insert into reports values ((select nextval('HIBERNATE_SEQUENCE') FROM DUAL),'select * from split_me2.t1','my big report',1);
insert into reports values ((select nextval('HIBERNATE_SEQUENCE') FROM DUAL),'SELECT * FROM split_me.groups','my report',1);

-- SELECT NEXTVAL('HIBERNATE_SEQUENCE') FROM DUAL;
-- SELECT NEXTVAL('HIBERNATE_SEQUENCE') FROM DUAL;

INSERT INTO reporting_tasks values ((select nextval('HIBERNATE_SEQUENCE') from dual), 3, null, 0);
INSERT INTO reporting_tasks values ((select nextval('HIBERNATE_SEQUENCE') from dual), 3, null, 0);
INSERT INTO reporting_tasks values ((select nextval('HIBERNATE_SEQUENCE') from dual), 3, null, 0);
INSERT INTO reporting_tasks values ((select nextval('HIBERNATE_SEQUENCE') from dual), 3, null, 0);
INSERT INTO reporting_tasks values ((select nextval('HIBERNATE_SEQUENCE') from dual), 3, null, 0);