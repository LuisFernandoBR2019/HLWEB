<!DOCTYPE HTML>
<html lang="en">
<head>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet"
	href="./../resources/templates/index/assets/css/main.css" />
</head>
<body class="is-preload">

	<!-- Header -->
	<header id="header">
		<a class="logo" href="http://npdi.ddns.net:9005/helplife/">Help Life</a>
		<nav>
			<a href="#menu">Menu</a>
		</nav>
	</header>

	<!-- Nav -->
	<nav id="menu">
		<ul class="links">
			<li><a href="http://npdi.ddns.net:9005/helplife/">Home</a></li>
			<li><a href="http://npdi.ddns.net:9005/helplife/menu/apresentacao">Quem
					Somos</a></li>
			<li><a href="http://npdi.ddns.net:9005/helplife/menu/campanhas">Campanhas</a></li>
			<li><a href="http://npdi.ddns.net:9005/helplife/menu/orientacoes">Orientações</a></li>
			<li><a href="http://npdi.ddns.net:9005/helplife/menu/login">Entrar</a></li>
		</ul>
	</nav>
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-4" align="left"></div>
			<div class="col-4" align="center">
			<br/>
				<br/>
				<h2>Aviso de recuperação de e-mail</h2>
				<br/>
				<br/>
				<p>Foi enviado um email com a sua senha para o email informado!
				Não é necessário responder este e-mail, trata-se de um email automático.
				Muito obrigado.</p>
				<a href="http://npdi.ddns.net:9005/helplife/menu/login">Clique aqui!</a><p>para retornar a tela de acesso.</p>
				<br/>
				<br/>
			</div>
			<div class="col-4" align="right"></div>
		</div>
	</div>
</body>
<jsp:include page="../fragments/footer.jsp" />
<!-- Scripts -->
<script src="../resources/templates/index/assets/js/jquery.min.js"></script>
<script src="../resources/templates/index/assets/js/browser.min.js"></script>
<script src="../resources/templates/index/assets/js/breakpoints.min.js"></script>
<script src="../resources/templates/index/assets/js/util.js"></script>
<script src="../resources/templates/index/assets/js/main.js"></script>
</html>