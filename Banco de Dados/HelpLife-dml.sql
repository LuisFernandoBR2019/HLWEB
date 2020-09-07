\c helplife

--Tipo Sanguineo
insert into tiposanguineo (tiposangue) values ('O+');
insert into tiposanguineo (tiposangue) values ('O-');
insert into tiposanguineo (tiposangue) values ('A+');
insert into tiposanguineo (tiposangue) values ('A-');
insert into tiposanguineo (tiposangue) values ('B+');
insert into tiposanguineo (tiposangue) values ('B-');
insert into tiposanguineo (tiposangue) values ('AB+');
insert into tiposanguineo (tiposangue) values ('AB-');

--Usuario
insert into usuario (nome, endereco, telefone, email, senha,  estado, cidade, cep, status, tiposanguineo_id, sexo, tipo, datanascimento) 
	values ('Luis Fernando', 'Rua das Pedras', '99887766', 'nando_srs@hotmail.com', '400086719104BDD0DF7DE2FED14D6FCC', 'MG', 'Santa Rita do Sapucai', '37540000', 0,1,'M', 0, '10/10/1995');
insert into usuario (nome, endereco, telefone, email, senha,  estado, cidade, cep, status, tiposanguineo_id, sexo, tipo, datanascimento) 
	values ('Hemocentro Santa Rita','Rua das flores','99557766','hemocentro.santarita@hotmail.com','400086719104BDD0DF7DE2FED14D6FCC','MG','Santa Rita do Sapucai','37540000', 1, 1, NULL, 1,  NULL);

--Solicitacao
insert into solicitacao (datahora,descricao,usuario_id,hemocentro_id,status) 
	values ('19/02/2020','Urgente preciso de sangue O+',1,1,1);
insert into solicitacao (datahora,descricao,usuario_id,hemocentro_id,status) 
	values ('19/02/2020','Urgente preciso de sangue O-',1,1,1);


--Campanha
insert into campanha (descricao,nome,datainicio,datafim,status,hemocentro_id,usuario_id) 
	values ('Uniao dos professores em prol do aumento de sangue no hemocentro Santa Rita','Campanha a favor da vida','10/05/2019','20/08/2019',0,2,1);
insert into campanha (descricao,nome,datainicio,datafim,status,hemocentro_id,usuario_id) 
	values ('Uniao dos ciclistas juntamente com os comerciantes em beneficio ao hemocentro Pouso Alegre.','Campanha a favor Hemocentro Pouso Alegre','10/05/2019','20/08/2019',1,2,1);


--tiposanguineo_solicitacao
insert into tiposanguineo_solicitacao(solicitacao_id,tiposanguineo_id) values (1,1);
insert into tiposanguineo_solicitacao(solicitacao_id,tiposanguineo_id) values (2,2);


--tiposanquineo_campanha
insert into tiposanguineo_campanha(campanha_id,tiposanguineo_id) values (1,1);
insert into tiposanguineo_campanha(campanha_id,tiposanguineo_id) values (2,2);
