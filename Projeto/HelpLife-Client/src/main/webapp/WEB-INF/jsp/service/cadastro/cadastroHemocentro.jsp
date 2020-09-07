<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Help Life - Ajudando Vidas!</title>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.0/jquery.mask.js"></script>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<link rel="stylesheet"
	href="../../../resources/templates/index/assets/css/main.css" />
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
	<br />
	<br />
	<br />
	<div>
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-3" align="left"></div>
				<div class="col-6" align="center">
					<h1>
						<strong>Cadastrar-se</strong>
					</h1>

					<form method="post" class="form-signin"
						modelAttribute="usuarioHemocentro">
						<div class="form-group   text-danger" align="left">
							<br /> <label for="exampleInputNome">Nome</label> <input
								type="string" class="form-control" name="nome" required="true"
								value="${hemocentro.nome}" id="exampleInputNome"
								placeholder="Nome">
							<c:if test="${not empty erro.nome}">
								<div class="alert alert-danger" role="alert">${erro.nome}</div>
							</c:if>
						</div>

						<div class="form-group   text-danger" align="left">
							<br /> <label for="exampleInputAddress">Endere�o</label> <input
								type="string" class="form-control" name="endereco"
								required="true" value="${hemocentro.endereco}"
								id="exampleInputAddress" placeholder="Avenida Paulista">
							<c:if test="${not empty erro.endereco}">
								<div class="alert alert-danger" role="alert">
									${erro.endereco}</div>
							</c:if>
						</div>

						<div class="form-group   text-danger" align="left">
							<br /> <label for="exampleInputPhone">Telefone</label> <input
								type="string" class="form-control" name="telefone"
								required="true" value="${hemocentro.telefone}"
								id="exampleInputPhone" placeholder="035 99700 8080">
							<c:if test="${not empty erro.telefone}">
								<div class="alert alert-danger" role="alert">
									${erro.telefone}</div>
							</c:if>
						</div>

						<div class="form-group   text-danger" align="left">
							<br /> <label for="exampleInputEmail">Email</label> <input
								type="email" class="form-control" name="email" required="true"
								value="${hemocentro.email}" id="exampleInputEmail"
								placeholder="joao@hotmail.com">
							<c:if test="${not empty erro.email}">
								<div class="alert alert-danger" role="alert">${erro.email}</div>
							</c:if>
						</div>

						<div class="form-group   text-danger" align="left">
							<br /> <label for="exampleInputPassword1">Senha</label> <input
								type="password" class="form-control" name="senha"
								required="true" value="${hemocentro.senha}"
								id="exampleInputPassword1" placeholder="Senha">
							<c:if test="${not empty erro.senha}">
								<div class="alert alert-danger" role="alert">${erro.senha}</div>
							</c:if>
						</div>

						<!-- <div class="form-group   text-danger" align="left">
							<br /> <label for="exampleInputEstado">Estado</label> <input
								type="string" class="form-control" name="estado" required="true"
								value="${hemocentro.estado}" id="exampleInputEstado"
								placeholder="Minas Gerais">
							<c:if test="${not empty erro.estado}">
								<div class="alert alert-danger" role="alert">${erro.estado}</div>
							</c:if>
						</div>-->
						<div class="form-group   text-danger" align="left">
							<br /> <label for="exampleInputEstado">Estado</label> <select
								name="estado">
								<option value="AC">Acre</option>
								<option value="AL">Alagoas</option>
								<option value="AP">Amap�</option>
								<option value="AM">Amazonas</option>
								<option value="BA">Bahia</option>
								<option value="CE">Cear�</option>
								<option value="DF">Distrito Federal</option>
								<option value="ES">Esp�rito Santo</option>
								<option value="GO">Goi�s</option>
								<option value="MA">Maranh�o</option>
								<option value="MT">Mato Grosso</option>
								<option value="MS">Mato Grosso do Sul</option>
								<option value="MG">Minas Gerais</option>
								<option value="PA">Par�</option>
								<option value="PB">Para�ba</option>
								<option value="PR">Paran�</option>
								<option value="PE">Pernambuco</option>
								<option value="PI">Piau�</option>
								<option value="RJ">Rio de Janeiro</option>
								<option value="RN">Rio Grande do Norte</option>
								<option value="RS">Rio Grande do Sul</option>
								<option value="RO">Rond�nia</option>
								<option value="RR">Roraima</option>
								<option value="SC">Santa Catarina</option>
								<option value="SP">S�o Paulo</option>
								<option value="SE">Sergipe</option>
								<option value="TO">Tocantins</option>
							</select>
						</div>
						<div class="form-group   text-danger" align="left">
							<br /> <label for="exampleInputCidade">Cidade</label> <input
								type="string" class="form-control" name="cidade" required="true"
								value="${hemocentro.cidade}" id="exampleInputCidade"
								placeholder="Santa Rita do Sapuca�">
							<c:if test="${not empty erro.cidade}">
								<div class="alert alert-danger" role="alert">${erro.cidade}</div>
							</c:if>
						</div>

						<div class="form-group   text-danger" align="left">
							<br /> <label for="exampleInputCEP">Cep</label> <input
								type="string" class="form-control" name="cep" required="true"
								value="${hemocentro.cep}" id="exampleInputCEP"
								placeholder="37540-000">
						</div>

						<br /> <br />
						<button type="submit" class="btn btn-primary">Enviar</button>
						<br /> <br />
					</form>
				</div>
				<div class="col-3" align="right"></div>
			</div>
		</div>
	</div>
</body>
<br />
<br />
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
<script>
	jQuery(function($) {
		$("#exampleInputPhone").mask("(999) 9-9999-9999");
		$("#exampleInputCEP").mask("99999-999");
	});
</script>
<script src="../../../resources/templates/index/assets/js/jquery.min.js"></script>
<script
	src="../../../resources/templates/index/assets/js/browser.min.js"></script>
<script
	src="../../../resources/templates/index/assets/js/breakpoints.min.js"></script>
<script src="../../../resources/templates/index/assets/js/util.js"></script>
<script src="../../../resources/templates/index/assets/js/main.js"></script>
</html>