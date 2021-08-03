SELECT * FROM cronresult.cronresult;
delete from cronresult;
insert into cronresult (minutes, hours, dayMonth, month, week) values('0', '0', '0', '0', '0');
alter table cronresult MODIFY minutes VARCHAR(45);