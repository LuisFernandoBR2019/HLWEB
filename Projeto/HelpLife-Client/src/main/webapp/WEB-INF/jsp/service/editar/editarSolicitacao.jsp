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
	<a class="logo" href="http://localhost:9005/helplife/dashboards/Usuario">Help Life</a>
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
							href="http://localhost:9005/helplife/service/listar/listarCampanha"
							style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">Listar campanhas</a></li>
						<li><a
							href="http://localhost:9005/helplife/service/listar/usuario/listarCampanhaUsuario"
							style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">Minhas campanhas</a></li>
						<li><a
							href="http://localhost:9005/helplife/service/cadastro/cadastroCampanha"
							style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">Criar campanha</a></li>
					</ul></li>
			</ul>
			<ul class="nav navbar-nav ">
				<li style="color: #111111;" class="dropdown"><a
					class="dropdown-toggle" data-toggle="dropdown" href="#"
					style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">Solicita��o<span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a
							href="http://localhost:9005/helplife/service/listar/listarSolicitacao"
							style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">Listar solicita��es</a></li>
						<li><a
							href="http://localhost:9005/helplife/service/listar/usuario/listarSolicitacaoUsuario"
							style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">Minhas solicita��es</a></li>
						<li><a
							href="http://localhost:9005/helplife/service/cadastro/cadastroSolicitacao"
							style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">Criar solicita��o</a></li>
					</ul></li>
			</ul>
			<ul class="nav navbar-nav ">
				<li style="color: #111111;" class="dropdown"><a id="name-user"
					class="dropdown-toggle" data-toggle="dropdown" href="#"
					style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">Usu�rio:
						${usuarioLogado.nome}<span class="caret"></span>
				</a>
					<ul class="dropdown-menu">
						<li><a
							href="http://localhost:9005/helplife/service/editar/editarUsuario"
							style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">Editar
								usu�rio</a></li>
						<li><a href="http://localhost:9005/helplife/menu/login"
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
			<h1>Editar Cadastro de Solicita��o</h1>
			<div
				class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3"
				align="center">
				<form method="post" class="form-signin">
					<input name="id" value="${solicitacao.id}" type="hidden" />
					<div class="form-group   text-danger" align="left">
						<label for="exampleInputNome">Descri��o</label> <input
							type="descricao" class="form-control" name="descricao"
							value="${solicitacao.descricao}" id="exampleInputNome"
							placeholder="Descricao" required="true">
					</div>
					<c:if test="${not empty erro.descricao}">
						<div class="alert alert-danger" role="alert">
							${erro.descricao}</div>
					</c:if>
					<div class="form-group   text-danger" align="left">
						<label for="exampleInputNome">Data</label> <input type="text"
							class="form-control" name="dataHora"
							value="${solicitacao.dataHora}" id="exampleInputNome"
							placeholder="DataHora" required="true">
					</div>
					<c:if test="${not empty erro.dataHora}">
						<div class="alert alert-danger" role="alert">${erro.dataHora}
						</div>
					</c:if>
					<div class="form-group">
						<label for="exampleInputTipoSanguineo">Tipo sangu�neo</label>
						<div class="form-check">
							<c:forEach items="${tipoSanguineoList}" var="tipoSanguineo">
								<c:set var="selecionado" value="false" scope="request" />
								<c:forEach items="${solicitacaoTipoSanguineoList}"
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
						<c:if test="${not empty erro.tipoSanguineoList}">
							<div class="alert alert-danger" role="alert">
								${erro.tipoSanguineoList}</div>
						</c:if>
					</div>
					<div class="form-group text-danger" align="left">
						<label for="exampleInputhemocentro">Hemocentro</label> <select
							class="form-control" type="checkbox" value="${hemocentro.id}"
							name="hemocentro.id">
							<option value="0">Selecione uma op��o</option>
							<c:forEach items="${hemocentroList}" var="hemocentro">
								<option
									<c:if test="${solicitacao.hemocentro.id eq hemocentro.id}">selected</c:if>
									value="${hemocentro.id}">${hemocentro.nome}</option>
							</c:forEach>
						</select>
						<c:if test="${not empty erro.hemocentro}">
							<div class="alert alert-danger" role="alert">
								${erro.hemocentro}</div>
						</c:if>
					</div>
					<button type="submit" class="btn btn-primary">Enviar</button>
				</form>
			</div>
		</div>
		<div class="col-3" align="center"></div>
	</div>
</div>
<!-- Footer -->
<footer id="footer">
	<div class="inner">
		<div class="content">
			<section>
				<h3>O Poder do Sangue.</h3>
				<p>
					H� quem diga que o sangue fala mais alto quando existe o tal la�o
					familiar. Mas se formos pensar bem, quantas vezes nos identificamos
					com pessoas que nem se quer fazem parte da nossa linhagem? <br />
					<br />S�o tantas que muitas vezes chegam at� a colocar qualquer
					hierarquia � prova. Quando o assunto � sangue, n�o existe la�o, n�o
					existe sangue bom ou ruim, n�o existe educa��o, n�o existe negro ou
					branco. <br />Dentro de n�s existe uma raz�o para estarmos vivos,
					existe um cora��o pulsando e sendo alimentado por veias e art�rias.
					<br />Onde h� mistura, h� uma vida sendo salva por nosso sangue.
				</p>
			</section>
			<section>
				<h4>Informativos e Parceiros</h4>
				<ul class="alt">
					<li><a
						href="https://www.saude.gov.br/saude-de-a-z/doacao-de-sangue">Saiba
							tudo sobre doa��o de Sangue.</a></li>
					<li><a href="https://www.fai-mg.br/portal/">FAI- Centro de
							Ensino Superior em Gest�o, Tecnologia e Educa��o..</a></li>
					<li><a
						href="https://www.saude.gov.br/saude-de-a-z/doacao-de-sangue/hemocentros-no-brasil">Encontre
							Hemocentros perto de voc�.</a></li>
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