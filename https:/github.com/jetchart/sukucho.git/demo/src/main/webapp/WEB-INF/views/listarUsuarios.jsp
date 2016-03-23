<%@include file="include.jsp" %>

<html>
<body>
	<h2>ABM Usuario</h2>
	
	<form method="POST">
      <table class="table">
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
	<input type="submit" name="accion" class="btn btn-success" value="Crear Usuario" />
	<input type="submit" name="accion" class="btn btn-danger" value="Volver" />
  </form>
</body>
</html>