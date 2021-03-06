<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ page import= "com.jetchart.demo.util.CUtil, com.jetchart.demo.model.CUsuario, com.jetchart.demo.service.usuario.CUsuarioService, com.jetchart.demo.model.CMenu, java.util.Collection" %> 
<!DOCTYPE html>
<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script> <!– Importante llamar antes a jQuery para que funcione bootstrap.min.js   –> 
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script> <!– Llamamos al JavaScript  a través de CDN –>

<%
	/* En caso que el Menu sea NULL, puede ser que sea la primera vez que ingrese, por lo 
	tanto se obtiene el MENU completo y se guarda en sesion */
	Collection<CMenu> colMenu = (Collection<CMenu>) request.getSession(true).getAttribute("colMenu");
	CUsuario usuarioLogueado = (CUsuario) request.getSession(true).getAttribute("usuario");
	if (colMenu == null){
		colMenu = CUsuarioService.getMenuByUsuario(usuarioLogueado);
		request.getSession(true).setAttribute("colMenu",colMenu);
	}
%>
<div class="row">
	<div style="background-color: #D6E1E6; text-align: center;">
		<c:if test="<%= !CUtil.tienePermiso(request) %>">
				<c:redirect url="errorPermiso"/>
		</c:if>
		<c:if test="${sessionScope.usuario != null}">
			<c:out value="Usuario: ${sessionScope.usuario.email}"/>
		</c:if>
			<br>
			<c:if test="${not empty colMenu}">
				<c:forEach var="menu" items="${colMenu}">
					<c:if test="${menu.visible == '1'}">
						<c:if test="${menu.path == pageContext.request.servletPath}">
							<b><a class="btn btn-info" href="${menu.url}">${menu.descripcion}</a></b>
						</c:if>
						<c:if test="${menu.path != pageContext.request.servletPath}">
							<a class="btn btn-default" href="${menu.url}">${menu.descripcion}</a>
						</c:if>
					</c:if>
				</c:forEach>
				<br>
			</c:if>	
			<c:if test="${sessionScope.usuario != null}">
				<a class="btn btn-link" href="logOut">Salir</a>
			</c:if>
			<c:if test="${sessionScope.usuario == null}">
				<p>SuKucho</p>
			</c:if>
	</div>
</div>