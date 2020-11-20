<!DOCTYPE HTML>
<html lang="en">
<head>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet"
	href="./../../resources/templates/index/assets/css/main.css" />
</head>
<c:if test="${isHemocentro eq true}">
	<body class="text-center ">
		<nav role="navigation" style="background-color: #ED1A24">
			<div class="container-fluid ">

				<div class="navbar-header ">
					<a class="navbar-brand" href="http://localhost:9005/helplife/dashboards/Usuario">Help Life</a>
				</div>
				<ul class="nav navbar-nav ">
					<li class="active"><a
						href="http://localhost:9005/helplife//dashboards/Hemocentro">P�GINA
							INICIAL</a></li>
				</ul>

				<ul class="nav navbar-nav ">
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#">Campanha<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a
								href="http://localhost:9005/helplife/service/listar/listarCampanha">Listar campanhas</a></li>
							<li><a
								href="http://localhost:9005/helplife/service/listar/listarCampanhaHemocentro">Minhas campanhas</a></li>
							<li><a
								href="http://localhost:9005/helplife/service/cadastro/cadastroCampanhaHemocentro">Criar campanha</a></li>
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
								href="http://localhost:9005/helplife/service/editar/editarHemocentro"
								style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">Editar
									usu�rio</a></li>
							<li><a href="http://localhost:9005/helplife/menu/login"
								style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">Sair</a></li>
						</ul></li>
				</ul>
			</div>
		</nav>
	</body>
</c:if>
<c:if test="${isHemocentro eq false}">
	<link rel="stylesheet"
		href="../../resources/templates/index/assets/css/main.css" />
	<body class="text-center ">

		<!-- Header -->
		<header id="header">
			<a class="logo" href="#">Help Life</a>
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
						style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">CAMPANHA</a>
						<ul class="dropdown-menu">
							<li><a
								href="http://localhost:9005/helplife/service/listar/listarCampanha"
								style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">LISTAR
									TODAS</a></li>
							<li><a
								href="http://localhost:9005/helplife/service/listar/usuario/listarCampanhaUsuario"
								style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">MINHAS
									CAMPANHAS</a></li>
							<li><a
								href="http://localhost:9005/helplife/service/cadastro/cadastroCampanha"
								style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">ADICIONAR
									CAMPANHAS</a></li>
						</ul></li>
				</ul>
				<ul class="nav navbar-nav ">
					<li style="color: #111111;" class="dropdown"><a
						class="dropdown-toggle" data-toggle="dropdown" href="#"
						style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">SOLICITA��O<span
							class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a
								href="http://localhost:9005/helplife/service/listar/listarSolicitacao"
								style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">LISTAR
									TODAS</a></li>
							<li><a
								href="http://localhost:9005/helplife/service/listar/usuario/listarSolicitacaoUsuario"
								style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">MINHAS
									SOLICITA��ES</a></li>
							<li><a
								href="http://localhost:9005/helplife/service/cadastro/cadastroSolicitacao"
								style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">ADICIONAR
									SOLICITA��O</a></li>
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
</c:if>
<div class="container">
	<div class="row justify-content-center">
		<div class="col-3" align="left"></div>
		<div class="col-6" align="center">
			<h1>Cadastrar Campanha</h1>
			<form method="post" class="form-signin" style="inline-size: 100%;">
				<div class="form-group text-danger" align="left">
					<label for="exampleInputDesc">Descri��o</label> <input
						type="string" class="form-control" name="descricao"
						required="true" value="${campanha.descricao}"
						id="exampleInputDesc" placeholder="Descri��o da Campanha">
					<c:if test="${not empty erro.descricao}">
						<div class="alert alert-danger" role="alert">
							${erro.descricao}</div>
					</c:if>
				</div>

				<div class="form-group text-danger" align="left">
					<label for="exampleInputNome">Nome</label> <input type="String"
						class="form-control" name="nome" required="true"
						value="${campanha.nome}" id="exampleInputNome"
						placeholder="Nome da Campanha.">
					<c:if test="${not empty erro.nome}">
						<div class="alert alert-danger" role="alert">${erro.nome}</div>
					</c:if>
				</div>

				<div class="form-group text-danger" align="left">
					<label for="exampleInputDI">Data Inicio</label> <input type="date"
						class="form-control" name="dataInicio" required="true"
						value="${campanha.dataInicio}" id="exampleInputDI"
						placeholder="Data inicial">
					<c:if test="${not empty erro.dataInicio}">
						<div class="alert alert-danger" role="alert">
							${erro.dataInicio}</div>
					</c:if>
				</div>

				<div class="form-group text-danger" align="left">
					<label for="exampleInputDF">Data Final</label> <input type="date"
						class="form-control" name="dataFinal" required="true"
						value="${campanha.dataFinal}" id="exampleInputDF"
						placeholder="Data Final">
					<c:if test="${not empty erro.dataFinal}">
						<div class="alert alert-danger" role="alert">
							${erro.dataFinal}</div>
					</c:if>
				</div>

				<div class="form-group text-danger" align="left">
					<label for="exampleInputhemocentro">Hemocentro</label> <select
						class="form-control" type="checkbox" value="${hemocentro.id}"
						value="${campanha.hemocentro}" name="hemocentro.id">
						<option value="0">Selecione uma op��o</option>
						<c:forEach items="${hemocentroList}" var="hemocentro">
							<option value="${hemocentro.id}">${hemocentro.nome}</option>
						</c:forEach>
					</select>
					<c:if test="${not empty erro.hemocentro}">
						<div class="alert alert-danger" role="alert">
							${erro.hemocentro}</div>
					</c:if>
				</div>

				<div class="form-group">
					<label for="exampleInputTipoSanguineo">Tipo sangu�neo</label>
					<div class="form-check">
						<c:forEach items="${tipoSanguineoList}" var="tipoSanguineo">
							<input type="checkbox" class="form-check-input"
								value="${tipoSanguineo.id}" id="${tipoSanguineo.id}"
								name="tipoSanguineoId">
							<label class="form-check-label" for="${tipoSanguineo.id}">
								${tipoSanguineo.tipoSangue} </label>
						</c:forEach>
					</div>
					<c:if test="${not empty erro.tipoSanguineoList}">
						<div class="alert alert-danger" role="alert">
							${erro.tipoSanguineoList}</div>
					</c:if>
				</div>

				<button type="submit" class="btn btn-primary">Enviar</button>
			</form>
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
<script src="./../../resources/templates/index/assets/js/jquery.min.js"></script>
<script
	src="./../../resources/templates/index/assets/js/browser.min.js"></script>
<script
	src="./../../resources/templates/index/assets/js/breakpoints.min.js"></script>
<script src="./../../resources/templates/index/assets/js/util.js"></script>
<script src="./../../resources/templates/index/assets/js/main.js"></script>
</html>