<!DOCTYPE HTML>
<html lang="en">
<head>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="./../../resources/css/Dashboard.css">
<link rel="stylesheet"
	href="./../../resources/templates/index/assets/css/main.css" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
</head>

<body class="text-center" onload="loaded('${usuarioLogado.estado}','${usuarioLogado.sexo }')">
	<!-- Header -->
	<header id="header">
		<a class="logo"
			href="http://localhost:9005/helplife/dashboards/Usuario">Help
			Life</a>
		<nav>
			<a href="#menu">Menu</a>
		</nav>
	</header>

	<!-- Nav -->
	<nav id="menu">
		<div class="container-fluid">
			<ul class="nav navbar-nav ">
				<li style="color: #111111;" class="dropdown"><a
					class="dropdown-toggle" data-toggle="dropdown" href="#"
					style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">Campanha</a>
					<ul class="dropdown-menu">
						<li><a
							href="http://localhost:9005/helplife/service/listar/listarCampanha"
							style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">Listar
								campanhas</a></li>
						<li><a
							href="http://localhost:9005/helplife/service/listar/usuario/listarCampanhaUsuario"
							style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">Minhas
								campanhas</a></li>
						<li><a
							href="http://localhost:9005/helplife/service/cadastro/cadastroCampanha"
							style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">Criar
								campanha</a></li>
					</ul></li>
			</ul>

			<ul class="nav navbar-nav ">
				<li style="color: #111111;" class="dropdown"><a
					class="dropdown-toggle" data-toggle="dropdown" href="#"
					style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">Solicitação<span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a
							href="http://localhost:9005/helplife/service/listar/listarSolicitacao"
							style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">Listar
								solicitações</a></li>
						<li><a
							href="http://localhost:9005/helplife/service/listar/usuario/listarSolicitacaoUsuario"
							style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">Minhas
								solicitações</a></li>
						<li><a
							href="http://localhost:9005/helplife/service/cadastro/cadastroSolicitacao"
							style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">Criar
								solicitação</a></li>
					</ul></li>
			</ul>
			<ul class="nav navbar-nav ">
				<li style="color: #111111;" class="dropdown"><a id="name-user"
					class="dropdown-toggle" data-toggle="dropdown" href="#"
					style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">Usuário:
						${usuarioLogado.nome}<span class="caret"></span>
				</a>
					<ul class="dropdown-menu">
						<li><a
							href="http://localhost:9005/helplife/service/editar/editarUsuario"
							style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">Editar
								usuário</a></li>
						<li><a href="http://localhost:9005/helplife/menu/login"
							style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">Sair</a></li>
					</ul></li>
			</ul>
		</div>
	</nav>

	<br />
	<div class="container" >
		<div class="row justify-content-center">
			<div class="col-4" align="left"></div>
			<div class="col-4" align="center">
				<h1>Editar Cadastro de Usuario</h1>
				<br />
				<div
					class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3"
					align="center">
					<form method="post" class="form-signin">
						<input name="id" value="${usuarioLogado.id}" type="hidden" />
						<div class="form-group   text-danger" align="left">
							<label for="exampleInputNome">Nome</label> <input type="name"
								class="form-control" name="nome" value="${usuarioLogado.nome}"
								id="exampleInputNome" placeholder="Nome" required="true">
							<c:if test="${not empty erro.nome}">
								<div class="alert alert-danger" role="alert">${erro.nome}</div>
							</c:if>
						</div>
						<br />
						<div class="form-group   text-danger" align="left">
							<label for="exampleInputAddress">Endereço</label> <input
								type="address" class="form-control" name="endereco"
								value="${usuarioLogado.endereco}" id="exampleInputAddress"
								placeholder="Avenida Paulista" required="true">
							<c:if test="${not empty erro.endereco}">
								<div class="alert alert-danger" role="alert">
									${erro.endereco}</div>
							</c:if>
						</div>
						<br />
						<div class="form-group   text-danger" align="left">
							<label for="exampleInputPhone">Telefone</label> <input
								type="phone" class="form-control" name="telefone"
								value="${usuarioLogado.telefone}" id="exampleInputPhone"
								placeholder="35 99700 8080" required="true">
							<c:if test="${not empty erro.telefone}">
								<div class="alert alert-danger" role="alert">
									${erro.telefone}</div>
							</c:if>
						</div>
						<br />
						<div class="form-group   text-danger" align="left">
							<label for="exampleInputEmail">Email</label> <input type="email"
								class="form-control" name="email" value="${usuarioLogado.email}"
								id="exampleInputEmail" placeholder="Seu Email" required="true">
							<c:if test="${not empty erro.email}">
								<div class="alert alert-danger" role="alert">
									${erro.email}</div>
							</c:if>
						</div>
						<br />
						<!--
						<div class="form-group   text-danger" align="left"
							style="width: 100%;">
							<label for="exampleInputPassword1">Senha</label> <input
								type="password" class="form-control" name="senha"
								value="${usuarioLogado.senha}" id="exampleInputPassword1"
								placeholder="Senha" required="true">
							<c:if test="${not empty erro.senha}">
								<div class="alert alert-danger" role="alert">
									${erro.senha}</div>
							</c:if>
						</div>
						  -->
						<br />
						<div class="form-group   text-danger" align="left" >
							<label for="exampleInputEstado">Estado</label> 
							 <select id ="estado_editar_usuario"  name="estado">
								<option value="AC">Acre</option>
								<option value="AL">Alagoas</option>
								<option value="AP">Amapá</option>
								<option value="AM">Amazonas</option>
								<option value="BA">Bahia</option>
								<option value="CE">Ceará</option>
								<option value="DF">Distrito Federal</option>
								<option value="ES">Espírito Santo</option>
								<option value="GO">Goiás</option>
								<option value="MA">Maranhão</option>
								<option value="MT">Mato Grosso</option>
								<option value="MS">Mato Grosso do Sul</option>
								<option value="MG">Minas Gerais</option>
								<option value="PA">Pará</option>
								<option value="PB">Paraíba</option>
								<option value="PR">Paraná</option>
								<option value="PE">Pernambuco</option>
								<option value="PI">Piauí</option>
								<option value="RJ">Rio de Janeiro</option>
								<option value="RN">Rio Grande do Norte</option>
								<option value="RS">Rio Grande do Sul</option>
								<option value="RO">Rondônia</option>
								<option value="RR">Roraima</option>
								<option value="SC">Santa Catarina</option>
								<option value="SP">São Paulo</option>
								<option value="SE">Sergipe</option>
								<option value="TO">Tocantins</option>
							</select>
							<!-- <input
								type="state" class="form-control" name="estado"
								value="${usuarioLogado.estado}" id="exampleInputEstado"
								placeholder="Minas Gerais" required="true"> -->
							<c:if test="${not empty erro.estado}">
								<div class="alert alert-danger" role="alert">
									${erro.estado}</div>
							</c:if>
						</div>
						<br />
						<div class="form-group   text-danger" align="left">
							<label for="exampleInputCidade">Cidade</label> <input type="city"
								class="form-control" name="cidade"
								value="${usuarioLogado.cidade}" id="exampleInputCidade"
								placeholder="Santa Rita do Sapucaí" required="true">
							<c:if test="${not empty erro.cidade}">
								<div class="alert alert-danger" role="alert">${erro.cidade}</div>
							</c:if>
						</div>
						<br />
						<div class="form-group   text-danger" align="left">
							<label for="exampleInputCEP">Cep</label> <input type="cep"
								class="form-control" name="cep" value="${usuarioLogado.cep}"
								id="exampleInputCEP" placeholder="37540000" required="true">
							<c:if test="${not empty erro.cep}">
								<div class="alert alert-danger" role="alert">${erro.cep}</div>
							</c:if>
						</div>
						<br />
						<div class="form-group   text-danger" align="left">
							<label for="exampleInputTipoSangue">Tipo Sanguineo</label> <select
								class="form-control" name="tipoSanguineo.id">
								<option value="0">Selecione uma opção</option>
								<c:forEach items="${tipoSanguineoList}" var="tipoSanguineo">
									<option
										<c:if test="${usuarioLogado.tipoSanguineo.id eq tipoSanguineo.id}">selected</c:if>
										value="${tipoSanguineo.id}">${tipoSanguineo.tipoSangue}</option>
								</c:forEach>
							</select>
						</div>
						<br />

						<div class="form-group   text-danger" align="left">
							<label for="exampleInputSexo">Sexo</label> 
							 <select id ="sexo"  name="sexo">
								<option value="M">Masculino</option>
								<option value="F">Feminino</option>
							</select>
							<!-- 
							 <input type="sexo"
								class="form-control" name="sexo" value="${usuarioLogado.sexo}"
								id="exampleInputSexo" placeholder="Seu Sexo">
							-->
						</div>
						<br />
						<div class="form-group   text-danger" align="left">
							<label for="exampleInputDatanascimento">Data Nascimento</label>
							<!-- coloca date no text muda formato -->
							<input type=text class="form-control" name="dataNascimento"
								required="true" value="${usuarioLogado.dataNascimento}"
								id="dataNascimento" placeholder="xx/xx/xxxx" required="true">
						</div>
						<br />
						<button type="submit" class="btn btn-primary">Salvar</button>
					</form>

				</div>
			</div>
			<div class="col-4" align="right"></div>
		</div>
	</div>
	<br />
	<!-- Footer -->
	<footer id="footer">
		<div class="inner">
			<div class="content">
				<section>
					<h3>O Poder do Sangue.</h3>
					<p>
						Há quem diga que o sangue fala mais alto quando existe o tal laço
						familiar. Mas se formos pensar bem, quantas vezes nos
						identificamos com pessoas que nem se quer fazem parte da nossa
						linhagem? <br /> <br />São tantas que muitas vezes chegam até a
						colocar qualquer hierarquia à prova. Quando o assunto é sangue,
						não existe laço, não existe sangue bom ou ruim, não existe
						educação, não existe negro ou branco. <br />Dentro de nós existe
						uma razão para estarmos vivos, existe um coração pulsando e sendo
						alimentado por veias e artérias. <br />Onde há mistura, há uma
						vida sendo salva por nosso sangue.
					</p>
				</section>
				<section>
					<h4>Informativos e Parceiros</h4>
					<ul class="alt">
						<li><a
							href="https://www.saude.gov.br/saude-de-a-z/doacao-de-sangue">Saiba
								tudo sobre doação de Sangue.</a></li>
						<li><a href="https://www.fai-mg.br/portal/">FAI- Centro
								de Ensino Superior em Gestão, Tecnologia e Educação..</a></li>
						<li><a
							href="https://www.saude.gov.br/saude-de-a-z/doacao-de-sangue/hemocentros-no-brasil">Encontre
								Hemocentros perto de você.</a></li>
					</ul>
				</section>
				<section>
					<h4>Redes Sociais</h4>
					<ul class="plain">
						<li><a href="#"><i class="icon fa-facebook">&nbsp;</i>Facebook</a></li>
						<li><a href="#"><i class="icon fa-instagram">&nbsp;</i>Instagram</a></li>
					</ul>
				</section>
			</div>
			<div class="copyright">&copy; All right reserved. Help Life.</div>


		</div>
	</footer>
	<!-- Scripts -->

<script >
function loaded(estado,sexo){
	selectByTextSexo(sexo);
	selectByText(estado);
}
function selectByTextSexo(text) {
	$("#sexo").val(text);
	console.log(text);
	}

function selectByText(text) {
	$("#estado_editar_usuario").val(text);
	console.log(text);
	}
</script>
	<script src="./../../resources/templates/index/assets/js/jquery.min.js"></script>
	<script
		src="./../../resources/templates/index/assets/js/browser.min.js"></script>
	<script
		src="./../../resources/templates/index/assets/js/breakpoints.min.js"></script>
	<script src="./../../resources/templates/index/assets/js/util.js"></script>
	<script src="./../../resources/templates/index/assets/js/main.js"></script>
</html>