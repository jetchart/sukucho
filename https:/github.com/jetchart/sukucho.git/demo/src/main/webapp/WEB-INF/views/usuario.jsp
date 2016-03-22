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
<form:form modelAttribute="usuario" method="POST">
      <table>
        <tr>
            <td><form:hidden path="id" /></td>
        </tr>
        <tr>
            <td>Nombre:</td>
            <td><form:input path="nombre" /></td>
        </tr>
        <tr>
            <td>Apellido:</td>
            <td><form:input path="apellido" /></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><form:input path="email" /></td>
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
              <input type="submit" name="accion" value="Volver" />
            </td>
        </tr>
      </table>
  </form:form>
  <p>${accionEjecutada}</p>
</body>
</html>
