-----------------------------------------------
--                TABELAS                    --
-----------------------------------------------
create table bancario.clientes(
	id integer not null primary key AUTO_INCREMENT,
	nome varchar(200) not null,
	tipo_pessoa varchar(1) not null,
	cpf varchar(11),
	cnpj varchar(14),
	score int);

-- rollback
drop table bancario.clientes;

-----------------------------------------------

create table bancario.contas(
	id integer not null primary key AUTO_INCREMENT,
	id_clientes integer not null,
	numero varchar(6) not null,
	tipo_conta varchar(1) not null,
	agencia varchar(11),
	constraint fk_clientes FOREIGN KEY ( id_clientes )
	REFERENCES bancario.clientes ( id )
);

-- rollback
drop table bancario.contas;

-----------------------------------------------

alter table bancario.clientes add id_contas integer;
alter table bancario.clientes
	add constraint fk_contas FOREIGN KEY ( id_contas )
	REFERENCES bancario.contas ( id ) ;

-- rollback
alter table bancario.clientes drop constraint fk_contas;
alter table bancario.clientes drop column id_contas;

-----------------------------------------------

select * from bancario.clientes c ;
select * from bancario.contas c ;
