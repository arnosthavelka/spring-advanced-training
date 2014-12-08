drop table CATCIS if exists;

create table CATCIS (
	Z integer identity primary key, 
	VERSION varchar(10) not null, 
	CODE int not null, 
	ACRONYM varchar(35) not null,
	VALIDFROM timestamp not null,
	NOTVALIDAFTER timestamp not null
);
