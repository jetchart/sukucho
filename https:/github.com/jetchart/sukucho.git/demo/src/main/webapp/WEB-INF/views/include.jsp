<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ page import= "com.jetchart.demo.util.CUtil" %> 

<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>

<c:if test="${sessionScope.usuario != null}">
	<c:out value="Usuario: ${sessionScope.usuario.email}"/>
	<br>
	<c:if test="${not empty colMenu}">
		<c:forEach var="menu" items="${colMenu}">
			<c:if test="${menu.visible == '1'}">
				<c:if test="${menu.path == pageContext.request.servletPath}">
					<b><a class="btn btn-info" href="${menu.url}">${menu.descripcion}</a></b>
				</c:if>
				<c:if test="${menu.path != pageContext.request.servletPath}">
					<a class="btn btn-warning" href="${menu.url}">${menu.descripcion}</a>
				</c:if>
			</c:if>
		</c:forEach>
		<br>
		<a cclass="btn btn-link" href="logOut">Salir</a>
	</c:if>	
</c:if>
<c:if test="<%= !CUtil.tienePermiso(request) %>">
		<c:redirect url="errorPermiso"/>
</c:if>		