<!DOCTYPE HTML>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <%@attribute name="campanha" required="true" type="com.ptc.helplife.Entity.Campanha"%>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <meta http-equiv="content-type" content="text/html; charset=iso-8859-1"/>
    </head>


    <div>
        <div class="card-sanguebom">
            <div class="card-body">
                <h3 class="card-title"><b>${campanha.nome}</b></h3>
                <p class="card-text"><b>Descricao:</b>${campanha.descricao}</p>
                <p class="card-text"><b>Criador:</b>${campanha.usuario.nome}</p>
                <p class="card-text"><b>Tipos Sanguineos:</b>
                    <c:forEach items="${campanha.tipoSanguineoList}" var="tipoSanguineo">
                        ${tipoSanguineo.tipoSangue} 
                    </c:forEach>  
                </p>
                <div class="divider-sanguebom">
                    <br/>
                    <p>
                        <a class="link-card" href="${pageContext.request.contextPath}/service/listar/informacaoCampanha/${campanha.id}">Mais Detalhes</a>  
                        <a class="btn btn-danger" href="${pageContext.request.contextPath}/service/cadastro/cadastroDoacao/${doacao.id}">Participar</a>
                    </p>
                </div>
            </div>
        </div>
    </div>
</html>