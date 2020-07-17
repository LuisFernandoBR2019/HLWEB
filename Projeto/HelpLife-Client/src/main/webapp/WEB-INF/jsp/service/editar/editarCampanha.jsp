<!DOCTYPE HTML>
<html lang="en">
<head>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="helplife"%>
<link rel="stylesheet"
	href="../../../resources/templates/index/assets/css/main.css" />
</head>
<header id="header">
	<a class="logo" href="http://npdi.ddns.net:9005/helplife/dashboards/Usuario">Help Life</a>
	<nav>
		<a href="#menu">Menu</a>
	</nav>
</header>
<body class="text-center ">

	<!-- Nav -->
	<nav id="menu">
		<div class="container-fluid">
			<ul class="nav navbar-nav ">
				<li style="color: #111111;" class="dropdown"><a
					class="dropdown-toggle" data-toggle="dropdown" href="#"
					style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">Campanha</a>
					<ul class="dropdown-menu">
						<li><a
							href="http://npdi.ddns.net:9005/helplife/service/listar/listarCampanha"
							style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">Listar campanhas</a></li>
						<li><a
							href="http://npdi.ddns.net:9005/helplife/service/listar/usuario/listarCampanhaUsuario"
							style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">Minhas campanhas</a></li>
						<li><a
							href="http://npdi.ddns.net:9005/helplife/service/cadastro/cadastroCampanha"
							style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">Criar campanha</a></li>
					</ul></li>
			</ul>
			<ul class="nav navbar-nav ">
				<li style="color: #111111;" class="dropdown"><a
					class="dropdown-toggle" data-toggle="dropdown" href="#"
					style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">Solicitação<span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a
							href="http://npdi.ddns.net:9005/helplife/service/listar/listarSolicitacao"
							style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">Listar solicitações</a></li>
						<li><a
							href="http://npdi.ddns.net:9005/helplife/service/listar/usuario/listarSolicitacaoUsuario"
							style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">Minhas solicitações</a></li>
						<li><a
							href="http://npdi.ddns.net:9005/helplife/service/cadastro/cadastroSolicitacao"
							style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">Criar solicitação</a></li>
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
							href="http://npdi.ddns.net:9005/helplife/service/editar/editarUsuario"
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
		<div class="col-3" align="left"></div>
		<div class="col-6" align="center">
			<h1 style="margin-left: 200px;">Editar Cadastro</h1>
			<div>
				<form method="post" class="form-signin">
					<input name="id" value="${campanha.id}" type="hidden" />

					<div class="form-group text-danger" align="left">
						<label for="exampleInputNome">Descrição</label> <input
							type="descricao" class="form-control" name="descricao"
							value="${campanha.descricao}" id="exampleInputNome"
							placeholder="Descricao" required="true">
					</div>
					<div class="form-group text-danger" align="left">
						<label for="exampleInputNome">Nome</label> <input type="name"
							class="form-control" name="nome" value="${campanha.nome}"
							id="exampleInputNome" placeholder="Nome" required="true">
					</div>
					<div class="form-group text-danger" align="left">
						<label for="exampleInputNome">Data Inicio</label> <input
							type="text" class="form-control" name="dataInicio"
							value="${campanha.dataInicio}" id="exampleInputNome"
							placeholder="Data Inicio" required="true">
					</div>
					<div class="form-group text-danger" align="left">
						<label for="exampleInputDF">Data Final</label> <input type="text"
							class="form-control" name="dataFinal"
							value="${campanha.dataFinal}" id="exampleInputDF"
							placeholder="Data limite" required="true">
					</div>
					<div class="form-group">
						<label for="exampleInputTipoSanguineo">Tipo Sanguineo</label>
						<div class="form-check">
							<c:forEach items="${tipoSanguineoList}" var="tipoSanguineo">
								<c:set var="selecionado" value="false" scope="request" />
								<c:forEach items="${campanhaTipoSanguineoList}"
									var="tipoSanguineoSelecionado">
									<c:if test="${tipoSanguineoSelecionado.id eq tipoSanguineo.id}">
										<c:set var="selecionado" value="true" scope="request" />
									</c:if>
								</c:forEach>
								<c:if test="${selecionado}">
									<input type="checkbox" class="form-check-input"
										value="${tipoSanguineo.id}" id="${tipoSanguineo.id}"
										name="tipoSanguineoId" checked=true>
								</c:if>
								<c:if test="${selecionado ne true}">
									<input type="checkbox" class="form-check-input"
										value="${tipoSanguineo.id}" id="${tipoSanguineo.id}"
										name="tipoSanguineoId">
								</c:if>
								<label class="form-check-label" for="${tipoSanguineo.id}">
									${tipoSanguineo.tipoSangue} </label>
							</c:forEach>
						</div>
					</div>
					<div class="form-group text-danger" align="left">
						<div class="form-group text-danger">
							<label for="exampleInputhemocentro">Hemocentro</label> <select
								class="form-control" type="checkbox" value="${hemocentro.id}"
								name="hemocentro.id">
								<option value="0">Selecione uma opção</option>
								<c:forEach items="${hemocentroList}" var="hemocentro">
									<option
										<c:if test="${campanha.hemocentro.id eq hemocentro.id}">selected</c:if>
										value="${hemocentro.id}">${hemocentro.nome}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<button type="submit" class="btn btn-primary">Enviar</button>
				</form>
			</div>
		</div>
		<div class="col-3" align="right"></div>
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
<script src="../../../resources/templates/index/assets/js/jquery.min.js"></script>
<script
	src="../../../resources/templates/index/assets/js/browser.min.js"></script>
<script
	src="../../../resources/templates/index/assets/js/breakpoints.min.js"></script>
<script src="../../../resources/templates/index/assets/js/util.js"></script>
<script src="../../../resources/templates/index/assets/js/main.js"></script>
</html>