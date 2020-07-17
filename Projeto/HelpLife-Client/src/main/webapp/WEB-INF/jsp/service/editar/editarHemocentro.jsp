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
</head>

<body class="text-center ">
	<header id="header">
		<a class="logo"
			href="http://npdi.ddns.net:9005/helplife/dashboards/Hemocentro">Help
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
							href="http://npdi.ddns.net:9005/helplife/service/listar/HemocentrolistarCampanha"
							style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">Listar
								campanhas</a></li>
						<li><a
							href="http://npdi.ddns.net:9005/helplife/service/listar/listarCampanhaHemocentro"
							style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">Minhas
								campanhas</a></li>
						<li><a
							href="http://npdi.ddns.net:9005/helplife/service/cadastro/cadastroCampanhaHemocentro"
							style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">Criar
								campanha</a></li>
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
							href="http://npdi.ddns.net:9005/helplife/service/editar/editarHemocentro"
							style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">Editar
								usuário</a></li>
						<li><a href="http://npdi.ddns.net:9005/helplife/menu/login"
							style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">Sair</a></li>
					</ul></li>
			</ul>
		</div>
	</nav>
</body>
<div class="container">
	<div class="row justify-content-center">
		<div class="col-4" align="left"></div>
		<div class="col-4" align="center">
			<h1>Editar Dados Hemocentro</h1>
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
							<div class="alert alert-danger" role="alert">
								${erro.nome}</div>
						</c:if>
					</div>
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
					<div class="form-group   text-danger" align="left">
						<label for="exampleInputEmail">Email</label> <input type="email"
							class="form-control" name="email" value="${usuarioLogado.email}"
							id="exampleInputEmail" placeholder="Seu Email" required="true">
						<c:if test="${not empty erro.email}">
							<div class="alert alert-danger" role="alert">
								${erro.email}</div>
						</c:if>
					</div>
					<div class="form-group   text-danger" align="left">
						<label for="exampleInputPassword1">Senha</label> <input
							type="password" class="form-control" name="senha"
							value="${usuarioLogado.senha}" id="exampleInputPassword1"
							placeholder="Senha" required="true">
						<c:if test="${not empty erro.senha}">
							<div class="alert alert-danger" role="alert">
								${erro.senha}</div>
						</c:if>
					</div>
					<div class="form-group   text-danger" align="left">
						<label for="exampleInputEstado">Estado</label> <input type="state"
							class="form-control" name="estado"
							value="${usuarioLogado.estado}" id="exampleInputEstado"
							placeholder="Minas Gerais" required="true">
							<c:if test="${not empty erro.estado}">
							<div class="alert alert-danger" role="alert">
								${erro.estado}</div>
						</c:if>
					</div>
					<div class="form-group   text-danger" align="left">
						<label for="exampleInputCidade">Cidade</label> <input type="city"
							class="form-control" name="cidade"
							value="${usuarioLogado.cidade}" id="exampleInputCidade"
							placeholder="Santa Rita do Sapucaí" required="true">
						<c:if test="${not empty erro.cidade}">
							<div class="alert alert-danger" role="alert">
								${erro.cidade}</div>
						</c:if>
					</div>
					<div class="form-group   text-danger" align="left">
						<label for="exampleInputCEP">CEP</label> <input type="cep"
							class="form-control" name="cep" value="${usuarioLogado.cep}"
							id="exampleInputCEP" placeholder="37540000" required="true">
						<c:if test="${not empty erro.cep}">
							<div class="alert alert-danger" role="alert">
								${erro.cep}</div>
						</c:if>
					</div>
					<br />
					<button type="submit" class="btn btn-primary">Enviar</button>
					<br />
				</form>
			</div>
		</div>
		<div class="col-4" align="right"></div>
	</div>
</div>
<!-- Footer -->
<footer id="footer">
	<div class="inner">
		<div class="content">
			<section>
				<h3>O Poder do Sangue.</h3>
				<p>
					Há quem diga que o sangue fala mais alto quando existe o tal laço
					familiar. Mas se formos pensar bem, quantas vezes nos identificamos
					com pessoas que nem se quer fazem parte da nossa linhagem? <br />
					<br />São tantas que muitas vezes chegam até a colocar qualquer
					hierarquia à prova. Quando o assunto é sangue, não existe laço, não
					existe sangue bom ou ruim, não existe educação, não existe negro ou
					branco. <br />Dentro de nós existe uma razão para estarmos vivos,
					existe um coração pulsando e sendo alimentado por veias e artérias.
					<br />Onde há mistura, há uma vida sendo salva por nosso sangue.
				</p>
			</section>
			<section>
				<h4>Informativos e Parceiros</h4>
				<ul class="alt">
					<li><a
						href="https://www.saude.gov.br/saude-de-a-z/doacao-de-sangue">Saiba
							tudo sobre doação de Sangue.</a></li>
					<li><a href="https://www.fai-mg.br/portal/">FAI- Centro de
							Ensino Superior em Gestão, Tecnologia e Educação..</a></li>
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
<script src="./../../resources/templates/index/assets/js/jquery.min.js"></script>
<script src="./../../resources/templates/index/assets/js/browser.min.js"></script>
<script
	src="./../../resources/templates/index/assets/js/breakpoints.min.js"></script>
<script src="./../../resources/templates/index/assets/js/util.js"></script>
<script src="./../../resources/templates/index/assets/js/main.js"></script>
</html>