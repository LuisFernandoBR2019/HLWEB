drop database helplife;
create database helplife with encoding 'utf8';

\c helplife;

create table tiposanguineo(
id bigserial primary key,
tiposangue varchar(3) unique not null check (tiposangue in ('O+','O-','A-','A+','B-','B+','AB-','AB+'))
);

create table usuario(
id bigserial primary key,
nome varchar (100) not null,
datanascimento varchar(15),
endereco varchar(100) not null,
telefone varchar(100) not null,
email varchar(100) unique not null,
senha varchar(50) not null,
estado varchar(50) not null,
cidade varchar(50) not null,
cep varchar(20)not null,
tiposanguineo_id bigint references tiposanguineo (id) on update cascade not null,
sexo varchar(1) check (sexo in('F', 'M')),
tipo integer not null check (tipo in( 0 , 1)),
status integer default 0
);

create table campanha (
id bigserial  primary key,
descricao varchar(500) not null,
nome varchar(500) not null,
datainicio varchar(15) not null,
datafim varchar(15) not null,
status integer not null default 0,
usuario_id bigint references  usuario(id) on update cascade not null,
hemocentro_id bigint references usuario(id) on update cascade not null
);

create table solicitacao(
id bigserial primary key,
datahora varchar(15) not null,
descricao varchar(500) not null,
status integer not null default 0,
usuario_id bigint references usuario(id) on update cascade not null,
hemocentro_id bigint references usuario(id) on update cascade not null
);

create table tiposanguineo_solicitacao(
solicitacao_id bigint references solicitacao(id) on update cascade not null,
tiposanguineo_id bigint references tiposanguineo(id) on update cascade not null,
primary key(solicitacao_id, tiposanguineo_id)

);

create table tiposanguineo_campanha(
tiposanguineo_id bigint references tiposanguineo(id) on update cascade not null,
campanha_id bigint references campanha(id) on update cascade not null,
primary key(tiposanguineo_id, campanha_id)
);
