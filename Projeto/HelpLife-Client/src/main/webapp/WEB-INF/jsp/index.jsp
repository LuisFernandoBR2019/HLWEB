<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Help Life - Ajudando Vidas!</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<link rel="stylesheet"
	href="./resources/templates/index/assets/css/main.css" />
	<jsp:include page="./fragments/header.jsp" />
</head>
<body class="is-preload">

	<!-- Banner -->
	<section id="banner">
		<div class="inner">
			<h1>Help Life</h1>
			<p>
				Para resolver o problema de doação de sangue no Brasil.<br /> Nós
				conectamos doadores a hemocentros e a quem mais precise de doações<br />
				Uma inovação em tempos de crise, unindo tecnologia e solidariedade.
			</p>
		</div>
		<video autoplay loop muted playsinline
			src="resources/templates/index/images/banner.mp4"></video>
	</section>

	<!-- Highlights -->
	<section class="wrapper">
		<div class="inner">
			<header class="special">
				<h2>O Help Life</h2>
				<p>
					O Projeto Help Life foi idealizado e desenvolvido para interagir
					com o público em geral, visando melhorar a concientização do povo
					quanto a importância da doação sanguínea no Brasil e aumentar o
					número de doadores e a frequência dessas doações.<br /> <br />
					Sabe-se da escassez dos hemocentros e o senso de urgência com que
					uma doação se faz necessária, logo, vislumbrou-se a necessidade da
					criação de uma ferramenta dinâmica e inovadora.<br /> <br /> O
					sistema Help Life foi criado em uma plataforma web, onde os
					hemocentros e doadores podem criar campanhas e doadores podem
					também criar solicitações de doação caso necessitem de algum tipo
					sanguineo em específico, as solicitações podem ser feitas para si,
					ou para parentes e amigos que necessitem de doações sanguíneas com
					urgência.<br /> <br /> O Help Life dispõe de uma plataforma onde
					circulam informações de forma rápida, direcionada e inteligente,
					tanto para os doadores regulares, quanto para os doadores em
					potencial, futuros membros da nossa rede.<br /> <br /> Além dos
					serviços do sistema, por conta das informações dos hemocentros
					parceiros, nossa aplicação web auxilia o doador a se manter
					informado sobre doações e campanhas, o estimulando a participar de
					campanhas e solicitações de doação sanguínea unindo forças para
					juntos podermos melhorar os níveis de estoques de hemocentros e
					tornar a população mais conciente e voltada a ajudar o próximo. <br />
				<h4 style="color: #000000">
					<a style="color: #000000"
						href="http://npdi.ddns.net:9005/helplife/service/cadastro/cadastroHemocentro/"><strong>clique
							aqui</strong></a>, Caso você seja um Hemocentro, e cadastre-se, venha fazer
					parte da nossa equipe.
				</h4>
				<br />
				<h4 style="color: #000000">
					<a style="color: #000000"
						href="http://npdi.ddns.net:9005/helplife/service/cadastro/cadastroUsuario/"><strong>clique
							aqui</strong></a>, Caso você queira criar uma campanha e/ou solicitação, e
					cadastre-se, venha fazer parte da nossa equipe.
				</h4>
				</p>

			</header>
		</div>
	</section>

	<!-- CTA -->
	<section id="cta" class="wrapper">
		<div class="inner">
			<h2>
				<strong>Doação de Sangue</strong>
			</h2>
			<p>
				Mais do que ajudar o próximo, a doação de sangue é um ato de amor
				que pode salvar vidas e, em muitos casos, a transfusão é a única
				esperança dos pacientes.<br /> Muitos Pacientes podem ser
				beneviciados com o sangue que você doar:<br /> Vítimas de diversos
				acidentes, Pacientes com câncer ou tumores, Pacientes hemofílicos, <br />Pacientes
				que serão submetidos a cirurgias e Recém nascidos prematuros.
			</p>
		</div>
	</section>
	
</body>

<jsp:include page="./fragments/footer.jsp" />

<!-- Scripts -->
	<script src="./resources/templates/index/assets/js/jquery.min.js"></script>
	<script src="./resources/templates/index/assets/js/browser.min.js"></script>
	<script src="./resources/templates/index/assets/js/breakpoints.min.js"></script>
	<script src="./resources/templates/index/assets/js/util.js"></script>
	<script src="./resources/templates/index/assets/js/main.js"></script>
</html>