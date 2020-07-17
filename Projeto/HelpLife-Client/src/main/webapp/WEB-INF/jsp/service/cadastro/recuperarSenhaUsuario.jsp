<!DOCTYPE HTML>
<html lang="en">
<head>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet"
	href="../../resources/templates/index/assets/css/main.css" />
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
			<div class="col-3" align="left"></div>
			<div class="col-6" align="center">
				<h1>Recuperar Senha</h1>

				<form method="post" class="form-signin">
					<div class="form-group   text-danger" align="left">
						<label for="exampleInputEmail">Email</label> <input type="email"
							class="form-control" name="email" id="exampleInputEmail"
							placeholder="Seu Email" required="true">
						<c:if test="${not empty erro.email}">
							<div class="alert alert-danger" role="alert">${erro.email}</div>
						</c:if>
					</div>
					<br />
					<button type="submit" class="btn btn-primary">Enviar</button>
				</form>
			</div>
			<div class="col-3" align="right"></div>
		</div>
	</div>
</body>
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
<script src="../../resources/templates/index/assets/js/jquery.min.js"></script>
<script src="../../resources/templates/index/assets/js/browser.min.js"></script>
<script
	src="../../resources/templates/index/assets/js/breakpoints.min.js"></script>
<script src="../../resources/templates/index/assets/js/util.js"></script>
<script src="../../resources/templates/index/assets/js/main.js"></script>
</html>
