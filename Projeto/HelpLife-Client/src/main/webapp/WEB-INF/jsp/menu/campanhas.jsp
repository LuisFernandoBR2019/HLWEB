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
			<li><a href="http://localhost:9005/helplife/menu/orientacoes">Orientações</a></li>
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
					universidades,<br /> associações e outros grupos interessados em
					salvar vidas conosco.<br /> Conheça nossas campanhas, participe e
					ajude a salvar ainda mais vidas!
				</p>
			</header>

			<div class="highlights">
				<section>
					<div class="content">
						<header>
							<a href="#" class="icon fa-vcard-o"><span class="label">Icon</span></a>
							<h3>Doações no Brasil</h3>
						</header>
						<p>Apenas 1,8% da população brasileira doa sangue. Com isso,
							em média, apenas 3,6 milhões de bolsas de sangue são coletadas
							por ano no Brasil.</p>
					</div>
				</section>
				<section>
					<div class="content">
						<header>
							<a href="#" class="icon fa-files-o"><span class="label">Icon</span></a>
							<h3>Cenário ideal</h3>
						</header>
						<p>O ideal, segundo a OMS, é que pelo menos 5% da população
							doe sangue.</p>
					</div>
				</section>
				<section>
					<div class="content">
						<header>
							<a href="#" class="icon fa-line-chart"><span class="label">Icon</span></a>
							<h3>Mínimo necessário?</h3>
						</header>
						<p>3,5% da população doando é o mínimo indicado pela OMS para
							se ter um banco de sangue saudável.</p>
					</div>
				</section>
				<section>
					<div class="content">
						<header>
							<a href="#" class="icon fa-paper-plane-o"><span class="label">Icon</span></a>
							<h3>Intervalo entre doações</h3>
						</header>
						<p>
							Homens: 60 dias (máximo de 4 doações nos últimos 12 meses).<br />
							Mulheres: 90 dias (máximo de 3 doações nos últimos 12 meses).
						</p>
					</div>
				</section>
				<section>
					<div class="content">
						<header>
							<a href="#" class="icon fa-vcard-o"><span class="label">Icon</span></a>
							<h3>Serviços de Hemoterapia</h3>
						</header>
						<p><a href="http://www.hemominas.mg.gov.br">Clique aqui</a> e Conheça os serviços de hemoterapia que também buscam
							doadores de sangue para melhorar captação sanguínea no Brasil.</p>
					</div>
				</section>
				<section>
					<div class="content">
						<header>
							<a href="#" class="icon fa-line-chart"><span class="label">Icon</span></a>
							<h3>Vidas salvas</h3>
						</header>
						<p>Cada bolsa de sangue pode salvar até quatro vidas, o
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