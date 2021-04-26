-----------------------------------------------
--                DATABASE                   --
-----------------------------------------------

create database bancario;

-- rollback
drop database bancario;

-----------------------------------------------
--                TABELAS                    --
-----------------------------------------------

create table bancario.clientes(
	id integer not null primary key AUTO_INCREMENT,
	nome varchar(200) not null,
	tipo_pessoa varchar(1) not null,
	cpf varchar(11),
	cnpj varchar(14),
	score int
);

-- rollback
drop table bancario.clientes;

-----------------------------------------------

create table bancario.contas(
	id integer not null primary key AUTO_INCREMENT,
	id_clientes integer not null,
	numero varchar(6) not null,
	tipo_conta varchar(1) not null,
	agencia varchar(11),
	constraint fk_clientes_contas FOREIGN KEY ( id_clientes )
	REFERENCES bancario.clientes ( id )
);

-- rollback
drop table bancario.contas;

-----------------------------------------------

create table bancario.cheques_especiais(
	id integer not null primary key AUTO_INCREMENT,
	id_contas integer not null,
	limite numeric not null,
	ativo boolean default false,
	constraint fk_contas_cheques FOREIGN KEY ( id_contas )
	REFERENCES bancario.contas ( id )
);

-- rollback
drop table bancario.cheques_especiais;

-----------------------------------------------

create table bancario.cartoes_creditos(
	id integer not null primary key AUTO_INCREMENT,
	id_contas integer not null,
	limite numeric not null,
	constraint fk_contas_cartoes FOREIGN KEY ( id_contas )
	REFERENCES bancario.contas ( id )
);

-- rollback
drop table bancario.cartoes_creditos;

-----------------------------------------------

select * from bancario.clientes c ;
select * from bancario.contas c ;
select * from bancario.cheques_especiais c ;
select * from bancario	.cartoes_creditos c ;