<%@include file="include.jsp" %>

<html>
<body>
	<h2>ABM Usuario</h2>
	
	<form method="POST">
      <table border="1">
      	<c:if test="${not empty usuarios}">
      		<tr>
	      		<th>Nombre</th>
	      		<th>Apellido</th>
	      		<th>Accion</th>
      		</tr>
	      	<c:forEach var="usuario" items="${usuarios}">
	        <tr>
	            <td>${usuario.nombre}</td>
	            <td>${usuario.apellido}</td>
	            <td><a href="./listarUsuarios?accion=Modificar&id=${usuario.id}" >Modificar</a>&nbsp;/&nbsp;<a href="./listarUsuarios?accion=Eliminar&id=${usuario.id}" >Eliminar</a></td>
	        </tr>
	        </c:forEach>
        </c:if>
      </table>
    <br>  
	<input type="submit" name="accion" value="Crear" />
	<input type="submit" name="accion" value="Volver" />
  </form>
</body>
</html>