<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>Help Life - Ajudando Vidas!</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<link rel="stylesheet"
	href="../resources/templates/index/assets/css/main.css" />
</head>
<body class="is-preload">

	<!-- Header -->
	<header id="header">
		<a class="logo" href="http://localhost:9005/helplife/">Help Life</a>
		<nav>
			<a href="#menu">Menu</a>
		</nav>
	</header>

	<!-- Nav -->
	<nav id="menu">
		<ul class="links">
			<li><a href="http://localhost:9005/helplife/">Home</a></li>
			<li><a href="http://localhost:9005/helplife/menu/apresentacao">Quem
					Somos</a></li>
			<li><a href="http://localhost:9005/helplife/menu/campanhas">Campanhas</a></li>
			<li><a href="http://localhost:9005/helplife/menu/orientacoes">Orienta��es</a></li>
			<li><a href="http://localhost:9005/helplife/menu/login">Entrar</a></li>
		</ul>
	</nav>
	<!-- campanhas -->
	<article id="campanhas" class="wrapper">
		<div class="container">
			<header class="special">
				<h2>
					<strong>Campanhas</strong>
				</h2>
				<p>
					As campanhas podem ser realizadas em parcerias com empresas,
					universidades,<br /> associa��es e outros grupos interessados em
					salvar vidas conosco.<br /> Conhe�a nossas campanhas, participe e
					ajude a salvar ainda mais vidas!
				</p>
			</header>

			<div class="highlights">
				<section>
					<div class="content">
						<header>
							<a href="#" class="icon fa-vcard-o"><span class="label">Icon</span></a>
							<h3>Doa��es no Brasil</h3>
						</header>
						<p>Apenas 1,8% da popula��o brasileira doa sangue. Com isso,
							em m�dia, apenas 3,6 milh�es de bolsas de sangue s�o coletadas
							por ano no Brasil.</p>
					</div>
				</section>
				<section>
					<div class="content">
						<header>
							<a href="#" class="icon fa-files-o"><span class="label">Icon</span></a>
							<h3>Cen�rio ideal</h3>
						</header>
						<p>O ideal, segundo a OMS, � que pelo menos 5% da popula��o
							doe sangue.</p>
					</div>
				</section>
				<section>
					<div class="content">
						<header>
							<a href="#" class="icon fa-line-chart"><span class="label">Icon</span></a>
							<h3>M�nimo necess�rio?</h3>
						</header>
						<p>3,5% da popula��o doando � o m�nimo indicado pela OMS para
							se ter um banco de sangue saud�vel.</p>
					</div>
				</section>
				<section>
					<div class="content">
						<header>
							<a href="#" class="icon fa-paper-plane-o"><span class="label">Icon</span></a>
							<h3>Intervalo entre doa��es</h3>
						</header>
						<p>
							Homens: 60 dias (m�ximo de 4 doa��es nos �ltimos 12 meses).<br />
							Mulheres: 90 dias (m�ximo de 3 doa��es nos �ltimos 12 meses).
						</p>
					</div>
				</section>
				<section>
					<div class="content">
						<header>
							<a href="#" class="icon fa-vcard-o"><span class="label">Icon</span></a>
							<h3>Servi�os de Hemoterapia</h3>
						</header>
						<p><a href="http://www.hemominas.mg.gov.br">Clique aqui</a> e Conhe�a os servi�os de hemoterapia que tamb�m buscam
							doadores de sangue para melhorar capta��o sangu�nea no Brasil.</p>
					</div>
				</section>
				<section>
					<div class="content">
						<header>
							<a href="#" class="icon fa-line-chart"><span class="label">Icon</span></a>
							<h3>Vidas salvas</h3>
						</header>
						<p>Cada bolsa de sangue pode salvar at� quatro vidas, o
							quantitativo doado pode salvar inumeras vidas, seje doador e
							ajude vidas.</p>
					</div>
				</section>
			</div>
		</div>
	</article>
</body>
<jsp:include page="../fragments/footer.jsp" />
<!-- Scripts -->
	<script src="../resources/templates/index/assets/js/jquery.min.js"></script>
	<script src="../resources/templates/index/assets/js/browser.min.js"></script>
	<script src="../resources/templates/index/assets/js/breakpoints.min.js"></script>
	<script src="../resources/templates/index/assets/js/util.js"></script>
	<script src="../resources/templates/index/assets/js/main.js"></script>
</html>