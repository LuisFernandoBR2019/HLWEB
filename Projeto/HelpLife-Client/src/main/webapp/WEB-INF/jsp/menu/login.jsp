<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xmlns:th="https://www.thymeleaf.org">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!-- usar if e for -->
<head>
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

	<br />
	<br />
	<br />
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-4" align="left"></div>
			<div class="col-4" align="center">
				<form class="form-horizontal" th:action="@{/login}" method="post">
					<br>
					<div class="control-group" align="left">
						<label class="control-label" for="inputEmail" id="username">E-mail</label>
						<div class="controls">
							<input type="text" class="form-control" id="inputEmail"
								placeholder="E-mail" size="50" name="email" required="true">
							<c:if test="${not empty erro.email}">
								<div class="alert alert-danger" role="alert">${erro.nome}</div>
							</c:if>
						</div>
					</div>
					<div class="control-group" align="left">
						<label class="control-label" for="inputPassword" id="password">Senha</label>
						<div class="controls">
							<input type="password" class="form-control" id="inputPassword"
								placeholder="Senha" size="50" name="senha" required="true">
							<c:if test="${not empty erro.senha}">
								<div class="alert alert-danger" role="alert">${erro.nome}</div>
							</c:if>
						</div>
					</div>
					<c:if test="${not empty erro}">
						<div class="alert alert-danger">
							<a class="close" data-dismiss="alert" href="#"></a>Login ou Senha
							Incorreto!
						</div>
					</c:if>
					<div class="control-group">
						<div class="controls">
							<label class="checkbox" class="form-actions"> </label><br>
							<div align="center">
								<button type="submit" class="btn btn-danger"
									class="col text-center">Entrar</button>
							</div>
						</div>
					</div>
					<div class="container">
						<div class="row justify-content-center">

							<div class="col-sm">
								<div align="left">
									<a 	href="${pageContext.request.contextPath}/service/cadastro/cadastroUsuario/"
										style="color: #8B8989;"><strong>Cadastre-se</strong></a>
								</div>
							</div>
							<div class="col-sm"></div>

							<div class="col-sm">
								<div align="right">
									<a 	href="${pageContext.request.contextPath}/menu/recuperarSenha/"
										style="color: #8B8989"><strong>Esqueci minha
											senha</strong></a>
								</div>
							</div>
						</div>
					</div>
				</form>
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