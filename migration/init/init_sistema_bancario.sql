-----------------------------------------------
--                TABELAS                    --
-----------------------------------------------
create table bancario.clientes(
	id integer not null primary key,
	nome varchar(200) not null,
	tipo_pessoa varchar(1) not null,
	cpf varchar(11),
	cnpj varchar(14),
	score int);

select * from bancario.clientes;

drop table bancario.clientes;
