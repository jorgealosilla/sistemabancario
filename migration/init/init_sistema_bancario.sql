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

create table bancario.cheques_especiais(
	id integer not null primary key AUTO_INCREMENT,
	id_contas integer not null,
	limite numeric not null,
	ativo boolean default false,
	constraint fk_ccontas FOREIGN KEY ( id_contas )
	REFERENCES bancario.contas ( id )
);

-- rollback
drop table bancario.cheques_especiais;

-----------------------------------------------

alter table bancario.contas add id_cheque_especial integer;
alter table bancario.contas
	add constraint fk_cheque_especial FOREIGN KEY ( id_cheque_especial )
	REFERENCES bancario.cheques_especiais ( id ) ;

-- rollback
alter table bancario.contas drop constraint fk_cheque_especial;
alter table bancario.contas drop column id_cheque_especial;

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

alter table bancario.contas add id_cartao_credito integer;
alter table bancario.contas
	add constraint fk_cartao_credito FOREIGN KEY ( id_cartao_credito )
	REFERENCES bancario.cartoes_creditos ( id ) ;

-- rollback
alter table bancario.contas drop constraint fk_cartao_credito;
alter table bancario.contas drop column id_cartao_credito;

select * from bancario.clientes c ;
select * from bancario.contas c ;
select * from bancario.cheques_especiais c ;
select * from bancario.cartoes_creditos c ;
