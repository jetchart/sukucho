<%@include file="include.jsp" %>

<html>
<head>
	<title>Usuario</title>
</head>
<body>
<h1>
	Usuario 
</h1>

<P>  Usuario </P>
<form:form commandName="usuario" method="POST">
      <table>
        <tr>
            <td><form:hidden path="id" /></td>
        </tr>
        <tr>
            <td>Nombre:</td>
            <td><form:input path="nombre" /></td>
            <td><form:errors path="nombre" style="color:#FF0000"/></td>
        </tr>
        <tr>
            <td>Apellido:</td>
            <td><form:input path="apellido" /></td>
            <td><form:errors path="apellido" style="color:#FF0000"/></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><form:input path="email" /></td>
            <td><form:errors path="email" style="color:#FF0000"/></td>
        </tr>
        <tr>
        	<c:if test="${sessionScope.usuario.nivel.id == 1}">
	            <td>Nivel:</td>
	            <td>
	                <form:select path="nivel.id">
	                    <form:options items="${nivelDropDown}" />
	                </form:select>
	            </td>
            </c:if>
            <c:if test="${sessionScope.usuario.nivel.id != 1}">
            	<td><form:hidden path="nivel.id" /></td>
            </c:if>
        </tr>
        <tr>
            <td>Contraseña:</td>
            <td><form:input path="contrasenia" /></td>
            <td><form:errors path="contrasenia" style="color:#FF0000"/></td>
        </tr>
        <tr>
            <td colspan="1">
            	<c:if test="${usuario.id == null}">
            		<input type="submit" name="accion" value="Guardar" />
            	</c:if>
            	<c:if test="${usuario.id != null}">
            		<input type="submit" name="accion" value=Modificar />
            	</c:if>
            </td>
            <td colspan="1">
              <c:if test="${sessionScope.usuario.nivel.id == 1}">
              	<input type="submit" name="accion" value="Volver" />
              </c:if>
            </td>
        </tr>
      </table>
  </form:form>
  <span style="color:#00FF00"><p>${accionEjecutada}</p></span>
</body>
</html>
