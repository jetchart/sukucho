<%@include file="include.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Usuarios</title>
</head>
<body>
	<div class="row">
	  <div class="col-md-2"></div>
	  <div class="col-md-8">
			<h2>ABM Usuario</h2>
			
			<form method="POST">
				<div class="table-responsive"> 
					<table class="table table-hover">
						<tr class="success">
						<td colspan="5"><b>Usuarios</b></td>
						</tr>
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
		      </div>
		    <br>  
			<input type="submit" name="accion" class="btn btn-success" value="Crear Usuario" />
			<input type="submit" name="accion" class="btn btn-danger" value="Volver" />
		  </form>
  	  </div>
	  <div class="col-md-2"></div>
</div>
</body>
</html>