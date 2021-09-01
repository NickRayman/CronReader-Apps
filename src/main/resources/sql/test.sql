SELECT * FROM cronresult.cronresult;
delete from cronresult;
insert into cronresult (cron, cronHuman, data) values('0', '0', '1');
alter table cronresult MODIFY minutes VARCHAR(45);
delete from cronresult.cronresult where ID=3;