<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="helplife"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
<script src="./../resources/js/Dashboard.js"></script>
<link  rel="stylesheet" href="./../resources/css/Dashboard.css"> 
<link rel="stylesheet"
	href="./../resources/templates/index/assets/css/main.css" />
</head>
</head>
<body onload="chamaJs();">
<header id="header">
	<a class="logo" href="http://localhost:9005/helplife/dashboards/Hemocentro">Help Life</a>
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
						href="http://localhost:9005/helplife/service/listar/HemocentrolistarCampanha"
						style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">Listar todas campanhas</a></li>
					<li><a
						href="http://localhost:9005/helplife/service/listar/listarCampanhaHemocentro"
						style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">Minhas campanhas</a></li>
					<li><a
						href="http://localhost:9005/helplife/service/cadastro/cadastroCampanhaHemocentro"
						style="text-decoration: none; color: rgba(255, 255, 255, 0.5);">Criar campanha</a></li>
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
<br />
<br />
<div class="container">
	<div class="row justify-content-center">
		<div class="col-3" align="left"></div>
		<div class="col-6" align="center">
			<h1>
				<strong>Acompanhamento</strong>
			</h1>
			<canvas id="myChart"></canvas>
		</div>
		<div class="col-3" align="right"></div>
	</div>
</div>
<br />
<br/>
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
<script src="./../resources/templates/index/assets/js/jquery.min.js"></script>
<script src="./../resources/templates/index/assets/js/browser.min.js"></script>
<script
	src="./../resources/templates/index/assets/js/breakpoints.min.js"></script>
<script src="./../resources/templates/index/assets/js/util.js"></script>
<script src="./../resources/templates/index/assets/js/main.js"></script>
<script>
	function chamaJs() {
		contaCampanhaPizza();
		 criaBar ();
	}
</script>
</html>