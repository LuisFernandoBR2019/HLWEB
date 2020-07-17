<!DOCTYPE HTML>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <%@attribute name="solicitacao" required="true" type="com.ptc.helplife.Entity.Solicitacao"%>
        <meta http-equiv="content-type" content="text/html; charset=iso-8859-1"/>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    </head>

    <div>
        <div class="card-sanguebom">
            <div class="card-body">
                <h3 class="card-title"><b>Solicitacao</b></h3>
                <p class="card-text"><b>Descricao:</b>${solicitacao.descricao}</p>
                <p class="card-text"><b>Criador:</b>${solicitacao.usuarioComum.nome}</p>
                <p class="card-text"><b>Tipos Sanguineos:</b>
                    <c:forEach items="${solicitacao.tipoSanguineoList}" var="tipoSanguineo">
                        ${tipoSanguineo.tipoSangue} 
                    </c:forEach>  
                </p>
                <div class="divider-sanguebom">
                    <br/>
                    <p>
                        <a class="link-card" href="${pageContext.request.contextPath}/service/listar/informacaoSolicitacao/${solicitacao.id}">Mais Detalhes</a>   
                        <a class="btn btn-danger" href="${pageContext.request.contextPath}/service/cadastro/cadastroDoacao/${doacao.id}">Participar</a>
                    </p>
                </div>
            </div>
        </div>
    </div>
</html>






