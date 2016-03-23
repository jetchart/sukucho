<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ page import= "com.jetchart.demo.util.CUtil" %> 

<c:if test="${sessionScope.usuario != null}">
	<c:out value="Usuario: ${sessionScope.usuario.email}"/>
	<br>
	<c:if test="${not empty colMenu}">
		<c:forEach var="menu" items="${colMenu}">
			<c:if test="${menu.visible == '1'}">
				<c:if test="${menu.path == pageContext.request.servletPath}">
					<b><a href="${menu.url}">${menu.descripcion}</a></b>
				</c:if>
				<c:if test="${menu.path != pageContext.request.servletPath}">
					<a href="${menu.url}">${menu.descripcion}</a>
				</c:if>
			</c:if>
		</c:forEach>
		<br>
		<a href="logOut">Salir</a>
	</c:if>	
</c:if>
<c:if test="<%= !CUtil.tienePermiso(request) %>">
		<c:redirect url="errorPermiso"/>
</c:if>		